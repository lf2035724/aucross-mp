package com.ylink.ylpay.project.mp.workorder.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.struts2.ServletActionContext;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.google.code.lightssh.common.ApplicationException;
import com.google.code.lightssh.common.config.SystemConfig;
import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.util.IoSerialUtil;
import com.google.code.lightssh.common.web.SessionKey;
import com.google.code.lightssh.common.web.action.CrudAction;
import com.google.code.lightssh.project.security.entity.LoginAccount;
import com.google.code.lightssh.project.sequence.service.SequenceManager;
import com.google.code.lightssh.project.util.constant.AuditResult;
import com.google.code.lightssh.project.util.constant.WorkOrderAuditStatus;
import com.opensymphony.xwork2.ActionContext;
import com.ylink.modules.bean.UploadBean;
import com.ylink.modules.orm.PropertyFilterBuilder;
import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.modules.utils.jxl.JxlBuilder;
import com.ylink.modules.utils.jxl.common.config.JxlConfig;
import com.ylink.ylpay.common.project.portal.constant.EntityStatus;
import com.ylink.ylpay.project.mp.cust.entity.CustCert;
import com.ylink.ylpay.project.mp.cust.service.CustCertManager;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrder;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrderAudit;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrderChange;
import com.ylink.ylpay.project.mp.workorder.service.WorkOrderAuditManager;
import com.ylink.ylpay.project.mp.workorder.service.WorkOrderChangeManager;
import com.ylink.ylpay.project.mp.workorder.service.WorkOrderManager;
import com.ylink.ylpay.project.utils.FieldNameAnnotation;

/**
 * @author feng.li
 *
 */
@Component("workOrderAction")
@Scope("prototype")
public class WorkOrderAction extends CrudAction<WorkOrder>{

	private static final long serialVersionUID = 1744493727534862256L;
	
	protected Logger logger = LoggerFactory.getLogger( this.getClass() );

	private WorkOrderManager workOrderManager;
	
	@Resource(name="workOrderChangeManager")
	private WorkOrderChangeManager workOrderChangeManager;
	
	@Resource(name="workOrderAuditManager")
	private WorkOrderAuditManager workOrderAuditManager;
	
	private WorkOrder workOrder;
	
	private WorkOrder originalObject;
	
	private WorkOrder newObject;
	
	private String ipAmount;
	
	private String pcAmount;
	
	private String originalDeviceAmount;
	
	private String status;
	
	private List<UploadBean> listBean;
	
	private ListPage<WorkOrderChange> ecPage;
	
	private ListPage<WorkOrderAudit> eaPage;
	
	private WorkOrderChange workOrderChange;
	
	private WorkOrderAudit workOrderAudit;
	
	private String imageId;
	
	private String fileName;
	
	@Autowired
	private SequenceManager sequenceManager;
	
	/**
	 * 上传的文件
	 */
	private File upload;
	
	@Autowired
	private CustCertManager custCertManager;
	
	/** 系统参数 */
	@Resource(name="systemConfig")
	private SystemConfig systemConfig;
	
	/**
	 * 列表
	 */
	@Override
	public String list() {
		if ( null == super.getPage() ) {
			super.setPage( new ListPage<WorkOrder>() );
		}
		page.addDescending("createTime");
		return findPage();
	}
	
	/**
	 * 查看
	 */
	public String view(){
		if( workOrder != null && workOrder instanceof WorkOrder ){
			workOrder = workOrderManager.getWorkOrder(workOrder.getId());
		}
		if( workOrder == null )
			return INPUT;
		return SUCCESS;
	}
	
	public void viewImage() throws IOException {
		ActionContext ctx = ActionContext.getContext();     
		  HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE); 
		  CustCert custCert = null;
		  if(imageId != null && !"".equals(imageId)){
			custCert = custCertManager.get( Long.parseLong(imageId));
		  }
		response.setContentType("image/JPEG");
		response.getOutputStream().write(custCert.getCertFile());
	}
	
	/**
	 * 显示图片.
	 */
	public String imageDetail() {
		logger.debug( "显示图片..." );

		if( this.imageId == null )
			return null;
		CustCert entity = this.custCertManager.get( Long.parseLong(imageId) );
		if( entity == null )
			return null;
		byte[] images = entity.getCertFile();
		if( images == null )
			return null;
		OutputStream out = null;
		try {
			out = ServletActionContext.getResponse().getOutputStream();
			IOUtils.write( images, out );
		} catch ( IOException e ) {
			logger.error( "异常:", e );
		} finally {
			IOUtils.closeQuietly( out );
		}
		return null;
	}

	/**
	 * 新增个人客户
	 */
	public String toSave(){
		return SUCCESS;
	}
	
	public String delete(){
		if(workOrder==null&&workOrder.getId()==null) {
			addActionError("没有此工单"+workOrder.getId());
			return SUCCESS;
		}
		LoginAccount loginAccount = (LoginAccount)request.getSession().getAttribute( SessionKey.LOGIN_ACCOUNT );
		workOrder.setStatus(EntityStatus.DISABLED);
		boolean mark = workOrderChangeManager.findWorkOrderChange( workOrder.getId() );
		if(!mark){
			this.addActionError( "已存在删除待审核记录，请审核完毕后再进行修改！" );
			return SUCCESS;
		}
		try{
			workOrderManager.update(loginAccount, workOrder,null);
		}catch (Exception e) {
			e.printStackTrace();
			addActionError("删除失败");
			return SUCCESS;
		}
		addActionMessage("删除成功,等待审核后生效");
		return SUCCESS;
	}
	
	public String detail() {
		Validate.notNull( this.getModel() );
		Validate.notNull( this.getModel().getId() );
		super.model = super.manager.get( super.getModel().getId() );
		if(super.model == null){
			this.saveErrorMessage("工单不存在!");
			return INPUT;
		}
		if(EntityStatus.NEW.equals(super.model.getStatus())){
			this.saveErrorMessage("新建的工单无法修改!");
			return INPUT;
		}
		if(super.model.getWorkOrderAuditStatus().equals(WorkOrderAuditStatus.WAIT_AUDIT)){
			this.saveErrorMessage("待审核的工单不可修改!");
			return INPUT;
		}
		workOrder = super.model;
		return SUCCESS;
	}
	
	public String save(){
		if(workOrder==null) {
			addActionError("保存数据不能为空");
			return INPUT;
		}
		LoginAccount loginAccount = (LoginAccount)request.getSession().getAttribute( SessionKey.LOGIN_ACCOUNT );
		if(StringUtils.isEmpty(workOrder.getCreator())){
			workOrder.setCreator(loginAccount.getLoginName());
		}
		workOrder.setStatus(EntityStatus.NEW);
		workOrder.setCreateTime(new Date());
		try{
			workOrderManager.save(workOrder, listBean);
		} catch ( Exception ex ) {
			super.saveErrorMessage( ex.getMessage() );
			logger.error( ex.getMessage() );
		return INPUT;
	}
		addActionMessage("添加成功！");
		return SUCCESS;
	}
	
	public String listWaitAudit() {
		logger.info( "待复核列表..." );
		if ( null == super.getPage() ) {
			super.setPage( new ListPage<WorkOrder>() );
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		List<Term> termList = PropertyFilterBuilder.buildTermFromHttpRequest( request );
		// 待复核状态.
		termList.add( new Term( Term.Type.IN, "workOrderAuditStatus",WorkOrderAuditStatus.WAIT_AUDIT) );
		termList.add( new Term( Term.Type.IN, "status",EntityStatus.NEW) );
		super.setPage( super.manager.list( super.getPage(), termList ) );

		return SUCCESS;
	}
	
	/**
	 * 复核通过.
	 * 
	 * @return
	 */
	public String pass() {
		logger.info( "复核通过..." );
		return this.modify(WorkOrderAuditStatus.WAIT_AUDIT, WorkOrderAuditStatus.AUDIT_PASS );
	}
	
	/**
	 * 复核拒绝.
	 * 
	 * @return
	 */
	public String refuse() {
		logger.info( "复核拒绝..." );
		return this.modify( WorkOrderAuditStatus.WAIT_AUDIT, WorkOrderAuditStatus.AUDIT_REFUSE );
	}
	
	private String modify(WorkOrderAuditStatus formStatus, WorkOrderAuditStatus toStatus ) {
		Assert.notNull( workOrder, "待审核信息不能为空." );
		Assert.notNull(workOrder.getId(),"待审核id不能为空.");
		Assert.notNull( formStatus, "formStatus不能为空." );
		Assert.notNull( toStatus, "toStatus不能为空." );

		try {
			this.workOrderManager.modify( workOrder.getId(), formStatus, toStatus );
			super.saveSuccessMessage( "操作成功." );
		} catch ( Exception ex ) {
			super.saveErrorMessage( "操作失败." + ex.getMessage() );
			logger.error( "操作失败." + ex.getMessage() );
			return INPUT;
		}
		return SUCCESS;
	}
	
	
	public String update() {
		logger.debug( "update..." );
		Validate.notNull( this.getModel() );
		String hint = "修改成功！审核后生效";
		try {
			boolean mark = workOrderChangeManager.findWorkOrderChange( workOrder.getId() );
			if(mark){
				LoginAccount loginAccount = (LoginAccount)request.getSession().getAttribute( SessionKey.LOGIN_ACCOUNT );
				hint = workOrderManager.update(loginAccount,workOrder,listBean );
				this.saveSuccessMessage( hint );
			}else{
				hint = "已存在修改审核记录，请审核完毕后再进行修改！";
				this.saveErrorMessage( hint );
			}
		} catch ( Exception ex ) {
			super.saveErrorMessage( ex.getMessage() );
			logger.error( ex.getMessage() );
			return INPUT;
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 待审核列表
	 */
	public String todoAuditList(){
		if(ecPage == null )
			ecPage = new ListPage<WorkOrderChange>();
		
		ecPage.addDescending("createdTime");
		ecPage = workOrderChangeManager.listTodoAudit(ecPage,workOrderChange);
		return SUCCESS;
	}
	
	/**
	 * 审核结果
	 */
	public String auditList( ){
		if( eaPage == null )
			eaPage = new ListPage<WorkOrderAudit>();
		
		eaPage.addDescending("createdTime");
		eaPage = workOrderAuditManager.list(eaPage,workOrderAudit);
		return SUCCESS;
	}
	public String todoAudit(){
		workOrderChange = workOrderChangeManager.get(workOrderChange);
		if( workOrderChange == null ){
			this.saveErrorMessage("参数错误，无法查找到变更信息！");
			return INPUT;
		}
		WorkOrderChange.Status status = workOrderChange.getStatus();
		if(WorkOrderChange.Status.FINISHED.equals(status)){
			this.saveErrorMessage("该变更审核已完成！");
			return INPUT;
		}
		if(!workOrderChange.getType().equals(WorkOrderChange.Type.DELETE)&&workOrderChange.getWorkOrder().getStatus().equals(EntityStatus.NEW)&&workOrderChange.getWorkOrder().getWorkOrderAuditStatus().equals(WorkOrderAuditStatus.WAIT_AUDIT)){
			this.saveErrorMessage("该工单信息为新增，请先完成新增复核！");
			return INPUT;
		}
		this.setWorkOrder(workOrderChange.getWorkOrder());
		byte[] originalObject = workOrderChange.getOriginalObject();
		if( originalObject != null ){
			WorkOrder orgObject = (WorkOrder)IoSerialUtil.deserialize(originalObject);
			request.setAttribute("originalObject", orgObject);
		}
		byte[] newObject = workOrderChange.getNewObject();
		if( newObject != null ){
			WorkOrder nObject = (WorkOrder) IoSerialUtil.deserialize(newObject);
			request.setAttribute("newObject", nObject);
		}
		return SUCCESS;
	}
	public String audit(){
		if( workOrderAudit == null || workOrderChange == null ){
			this.saveErrorMessage("审核参数为空！");
			return ERROR;
		}
		this.workOrderChange = this.workOrderChangeManager.get(workOrderChange);
		if(WorkOrderChange.Status.FINISHED.equals(workOrderChange.getStatus())){
			this.saveErrorMessage("工单已审核完毕！");
			return SUCCESS;
		}
		
		workOrderAudit.setWorkOrderChange(workOrderChange);
		LoginAccount loginAccount = (LoginAccount)request.getSession().getAttribute( SessionKey.LOGIN_ACCOUNT );
		workOrderAudit.setUser(loginAccount);
		boolean passed = request.getParameter("passed")!=null;
		workOrderAudit.setResult(passed?AuditResult.LAST_AUDIT_PASSED:AuditResult.LAST_AUDIT_REJECT);
		
		try{
			this.workOrderAuditManager.audit(workOrderAudit,workOrderChange);
		}catch(Exception e ){
			this.saveErrorMessage("审核操作异常："+e.getMessage());
			return INPUT;
		}
		this.saveSuccessMessage("审核成功");
		return SUCCESS;
	}
	
	
	public String findPage() {
		logger.debug( "findPage..." );
		if ( null == super.getPage() ) {
			super.setPage( new ListPage<WorkOrder>() );
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Term> termList = PropertyFilterBuilder.buildTermFromHttpRequest( request );
		for(Term t : termList){
			if("status".equals(t.getKey())){
				t.setValue(EntityStatus.parseOf((String)t.getValue()));
			}else if("workOrderAuditStatus".equals(t.getKey())){
				t.setValue(WorkOrderAuditStatus.parseOf((String)t.getValue()));
			}
		}
		super.setPage( super.manager.list( super.getPage(), termList ) );
		request.setAttribute( "list", super.getPage() );
		return SUCCESS;
	}
	
	public String toImport() {
		return SUCCESS;
	}
	
	public String importWorkOrder() throws Exception{
		if (upload == null)
			throw new ApplicationException("参数错误！");
		 Workbook book = null;
		 WorkOrder workOrder = null;
		 List<WorkOrder> resultList = new ArrayList<WorkOrder>();
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(new Date());
		 try {
			 book = Workbook.getWorkbook(upload);
			 Sheet sheet = book.getSheet(0);
	         for (int i = 1; i < sheet.getRows(); i++) {
	        	 	if("".equals(sheet.getCell(1,i).getContents())||sheet.getCell(1,i).getContents()==null
	        	 			&&"".equals(sheet.getCell(2,i).getContents())||sheet.getCell(2,i).getContents()==null
	        	 					&&"".equals(sheet.getCell(3,i).getContents())||sheet.getCell(3,i).getContents()==null
	        	 							&&"".equals(sheet.getCell(4,i).getContents())||sheet.getCell(4,i).getContents()==null){
	        	 		continue;
	        	 	}
	        	 	workOrder = new WorkOrder();
	        	 	Cell [] cells = sheet.getRow(i);
	        	 	if(cells[0]!=null){
	        	 		workOrder.setOrderNo(cells[0].getContents());
	        	 	}
	        	 	workOrder.setUserName(cells[1].getContents());
	        	 	workOrder.setLineClassification(cells[2].getContents());
	        	 	workOrder.setForwardingAddress(cells[3].getContents());
	        	 	workOrder.setAccessTerminalAddress(cells[4].getContents());
	        	 	workOrder.setContactUserName(cells[5].getContents());
	        	 	workOrder.setContactUserPhone(cells[6].getContents());
	        	 	workOrder.setContactUserMobile(cells[7].getContents());
	        	 	workOrder.setAccessRate(cells[8].getContents());
	        	 	workOrder.setAccessMethod(cells[9].getContents());
	        	 	workOrder.setBusinessType(cells[10].getContents());
	        	 	workOrder.setOrderType(cells[11].getContents());
	        	 	workOrder.setAscendingNodeName(cells[12].getContents());
	        	 	workOrder.setIpAlready(cells[13].getContents());
	        	 	if(	sheet.getCell(14, i).getContents()!=null&&!"".equals(sheet.getCell(14, i).getContents())){
		        	 	workOrder.setIpAmount(Integer.parseInt(sheet.getCell(14, i).getContents()));
	        	 	}
	        	 	if(	sheet.getCell(15, i).getContents()!=null&&!"".equals(sheet.getCell(15, i).getContents())){
		        	 	workOrder.setPcAmount(Integer.parseInt(sheet.getCell(15, i).getContents()));
	        	 	}
	        	 	workOrder.setNewIpDistribution(cells[16].getContents());
	        	 	workOrder.setDistributionSign(cells[17].getContents());
	        	 	if(	sheet.getCell(18, i).getContents()!=null&&!"".equals(sheet.getCell(18, i).getContents())){
	        	 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
	        	 		workOrder.setDistributioDate(sdf.parse(sheet.getCell(18, i).getContents()));
	        	 	}
	        	 	workOrder.setLineTerminalDevice(cells[19].getContents());
	        	 	workOrder.setOriginalDeviceModel(cells[20].getContents());
	        	 	if(	sheet.getCell(21, i).getContents()!=null&&!"".equals(sheet.getCell(21, i).getContents())){
		        	 	workOrder.setOriginalDeviceAmount(Integer.parseInt(sheet.getCell(21, i).getContents()));
	        	 	}
	        	 	workOrder.setDepartmentName(cells[22].getContents());
	        	 	workOrder.setBusinessAgent(sheet.getCell(23, i).getContents());
	        	 	workOrder.setSalesClerk(sheet.getCell(24, i).getContents());
	        	 	workOrder.setCustomerServiceContactPerson(sheet.getCell(25, i).getContents());
	        	 	workOrder.setSalesClerkPhone(sheet.getCell(26, i).getContents());
	        	 	workOrder.setCustomerServiceContactPhone(sheet.getCell(27, i).getContents());
	        	 	workOrder.setBusinessAgentPhone(sheet.getCell(28, i).getContents());
	        	 	workOrder.setSalesClerkContactPhone(sheet.getCell(29, i).getContents());
	        	 	workOrder.setFlowFee(sheet.getCell(30, i).getContents());
	        	 	workOrder.setMonthlyFee(sheet.getCell(31, i).getContents());
	        	 	if(	sheet.getCell(32, i).getContents()!=null&&!"".equals(sheet.getCell(32, i).getContents())){
	        	 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
	        	 		workOrder.setExpiryDate(sdf.parse(sheet.getCell(32, i).getContents()));//截止日期
	        	 	}
	        	 	workOrder.setBillingCycleee(sheet.getCell(33, i).getContents());
	        	 	workOrder.setCustomerServiceContactPersonPhone(sheet.getCell(34, i).getContents());
	        	 	workOrder.setBusinessAgentContactPhone(sheet.getCell(35, i).getContents());
	        	 	if(	sheet.getCell(36, i).getContents()!=null&&!"".equals(sheet.getCell(36, i).getContents())){
	        	 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
	        	 		workOrder.setCreateTime(sdf.parse(sheet.getCell(36, i).getContents()));
	        	 	}
	        	 	if(	sheet.getCell(37, i).getContents()!=null&&!"".equals(sheet.getCell(37, i).getContents())){
	        	 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
	        	 		workOrder.setUpdatedTime(sdf.parse(sheet.getCell(37, i).getContents()));
	        	 	}
	        	 	if(	sheet.getCell(38, i).getContents()!=null&&!"".equals(sheet.getCell(38, i).getContents())){
	        	 		workOrder.setStatus(EntityStatus.parseOfDisplayName(sheet.getCell(38, i).getContents()));
	        	 	}
	        	 	if(	sheet.getCell(39, i).getContents()!=null&&!"".equals(sheet.getCell(39, i).getContents())){
	        	 		workOrder.setWorkOrderAuditStatus(WorkOrderAuditStatus.parseOfDisplayName(sheet.getCell(39, i).getContents()));
	        	 	}
	        	 	workOrder.setCreator(sheet.getCell(40, i).getContents());
	        	 	workOrder.setRemark(sheet.getCell(41, i).getContents());
	        	 	resultList.add(workOrder);
	         }
	         book.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("文件解析异常", e);
		}
		 for(WorkOrder wo : resultList){
			 if(wo.getOrderNo()==null||"".equals(wo.getOrderNo())){
				 wo.setOrderNo(this.sequenceManager.nextSequenceNumber( wo ));
			 }
		 }
		 workOrderManager.create(resultList);
		 addActionMessage("数据导入成功");
		return SUCCESS;
	}
	
	public String export() throws Exception{
		ListPage<WorkOrder> listPageSupper = super.getPage();
		if ( null == listPageSupper ) {
			listPageSupper = new ListPage<WorkOrder>();
		}
		listPageSupper.setAllSize(1000000);
		listPageSupper.setSize(1000000);
		super.setPage( listPageSupper );
		page.addDescending("createTime");
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Term> termList = PropertyFilterBuilder.buildTermFromHttpRequest( request );
		for(Term t : termList){
			if("status".equals(t.getKey())){
				t.setValue(EntityStatus.parseOf((String)t.getValue()));
			}else if("workOrderAuditStatus".equals(t.getKey())){
				t.setValue(WorkOrderAuditStatus.parseOf((String)t.getValue()));
			}
		}
		super.setPage( super.manager.list( super.getPage(), termList ) );
		ListPage<WorkOrder> listPage = super.getPage();
		if(listPage==null||listPage.getList()==null||listPage.getList().size()<1){
			throw new ApplicationException("导出数据为空，根据条件没有找到要导出的数据", new Exception());
		}
		JxlBuilder jxlBuilder = new JxlBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		fileName = sdf.format(new Date());
		fileName+="export.xls";
		FieldNameAnnotation annotation = null;
		List<JxlConfig> jxlConfigList = new ArrayList<JxlConfig>();
		for(Field field : WorkOrder.class.getDeclaredFields()){
			annotation = field.getAnnotation(FieldNameAnnotation.class);
			if(annotation != null){
				jxlConfigList.add(new JxlConfig(field.getName(),annotation.name()));
			}
		}
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		jxlBuilder.createWorkbook(ServletActionContext.getResponse().getOutputStream(), fileName , listPage.getList(), jxlConfigList);
		return null;
	}
	
	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SystemConfig getSystemConfig() {
		return systemConfig;
	}

	public void setSystemConfig(SystemConfig systemConfig) {
		this.systemConfig = systemConfig;
	}
	public static void main(String[] args) throws SecurityException, NoSuchFieldException, ParseException {
		System.out.println(WorkOrder.class.getDeclaredField("orderNo").getAnnotation(FieldNameAnnotation.class).name());
		String ss = "yyyy-MM-dd HH:mm:ss.s";
		String ss2 = "2016-01-30 00:00:00.0";
		SimpleDateFormat sdf = new SimpleDateFormat(ss);
		System.out.println(sdf.parse(ss2));
		String ssss="正常";
		EntityStatus.parseOfDisplayName(ssss);
	}

	public List<UploadBean> getListBean() {
		return listBean;
	}

	public void setListBean(List<UploadBean> listBean) {
		this.listBean = listBean;
	}

	public WorkOrder getNewObject() {
		return newObject;
	}

	public void setNewObject(WorkOrder newObject) {
		this.newObject = newObject;
	}

	public WorkOrderManager getWorkOrderManager() {
		return workOrderManager;
	}

	@Resource( name = "workOrderManager" )
	public void setWorkOrderManager(WorkOrderManager workOrderManager) {
		this.workOrderManager = workOrderManager;
		super.manager = this.workOrderManager;
	}

	public WorkOrderChangeManager getWorkOrderChangeManager() {
		return workOrderChangeManager;
	}

	public void setWorkOrderChangeManager(
			WorkOrderChangeManager workOrderChangeManager) {
		this.workOrderChangeManager = workOrderChangeManager;
	}

	public WorkOrderAuditManager getWorkOrderAuditManager() {
		return workOrderAuditManager;
	}

	public void setWorkOrderAuditManager(WorkOrderAuditManager workOrderAuditManager) {
		this.workOrderAuditManager = workOrderAuditManager;
	}

	public WorkOrder getWorkOrder() {
		super.model = this.workOrder;
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
		super.model = workOrder;
	}

	public WorkOrder getOriginalObject() {
		return originalObject;
	}

	public void setOriginalObject(WorkOrder originalObject) {
		this.originalObject = originalObject;
	}

	public ListPage<WorkOrderChange> getEcPage() {
		return ecPage;
	}

	public void setEcPage(ListPage<WorkOrderChange> ecPage) {
		this.ecPage = ecPage;
	}

	public ListPage<WorkOrderAudit> getEaPage() {
		return eaPage;
	}

	public void setEaPage(ListPage<WorkOrderAudit> eaPage) {
		this.eaPage = eaPage;
	}

	public WorkOrderChange getWorkOrderChange() {
		return workOrderChange;
	}

	public void setWorkOrderChange(WorkOrderChange workOrderChange) {
		this.workOrderChange = workOrderChange;
	}

	public WorkOrderAudit getWorkOrderAudit() {
		return workOrderAudit;
	}

	public void setWorkOrderAudit(WorkOrderAudit workOrderAudit) {
		this.workOrderAudit = workOrderAudit;
	}

	public CustCertManager getCustCertManager() {
		return custCertManager;
	}

	public void setCustCertManager(CustCertManager custCertManager) {
		this.custCertManager = custCertManager;
	}

	public String getIpAmount() {
		if(ipAmount == null || "".equals(ipAmount)){
			if(workOrder != null){
				ipAmount = String.valueOf(workOrder.getIpAmount());
			}
		}
		return ipAmount;
	}

	public void setIpAmount(String ipAmount) {
		if(workOrder != null && ipAmount != null && !"".equals(ipAmount)){
			workOrder.setIpAmount(Integer.valueOf(ipAmount));
		}
		this.ipAmount = ipAmount;
	}

	public String getOriginalDeviceAmount() {
		if(originalDeviceAmount == null || "".equals(originalDeviceAmount)){
			if(workOrder != null){
				originalDeviceAmount = String.valueOf(workOrder.getOriginalDeviceAmount());
			}
	}
		return originalDeviceAmount;
	}

	public void setOriginalDeviceAmount(String originalDeviceAmount) {
		if(workOrder != null && originalDeviceAmount != null && !"".equals(originalDeviceAmount)){
			workOrder.setIpAmount(Integer.valueOf(originalDeviceAmount));
		}
		this.originalDeviceAmount = originalDeviceAmount;
	}

	public String getPcAmount() {
		if(pcAmount == null || "".equals(pcAmount)){
			if(workOrder != null){
				pcAmount = String.valueOf(workOrder.getPcAmount());
			}
		}
		return pcAmount;
	}

	public void setPcAmount(String pcAmount) {
		if(workOrder != null && pcAmount != null && !"".equals(pcAmount)){
			workOrder.setIpAmount(Integer.valueOf(pcAmount));
		}
		this.pcAmount = pcAmount;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public SequenceManager getSequenceManager() {
		return sequenceManager;
	}

	public void setSequenceManager(SequenceManager sequenceManager) {
		this.sequenceManager = sequenceManager;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	

}
