package com.ylink.ylpay.project.mp.workorder.service;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.google.code.lightssh.common.ApplicationException;
import com.google.code.lightssh.common.dao.SearchCondition;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.model.page.ListPage.OrderType;
import com.google.code.lightssh.common.model.page.OrderBy;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.google.code.lightssh.project.security.entity.LoginAccount;
import com.google.code.lightssh.project.sequence.service.SequenceManager;
import com.google.code.lightssh.project.util.constant.WorkOrderAuditStatus;
import com.ylink.modules.bean.UploadBean;
import com.ylink.ylpay.common.project.account.exception.AccountCheckedException;
import com.ylink.ylpay.common.project.mp.constant.AuditStatus;
import com.ylink.ylpay.common.project.mp.constant.CertificationType;
import com.ylink.ylpay.common.project.portal.constant.EntityStatus;
import com.ylink.ylpay.common.project.portal.dto.WorkOrderDTO;
import com.ylink.ylpay.project.mp.cust.entity.CustCert;
import com.ylink.ylpay.project.mp.cust.service.CustCertManager;
import com.ylink.ylpay.project.mp.workorder.dao.WorkOrderDao;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrder;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrderChange;

/**
 * @author feng.li
 */
@Component("workOrderManager")
public class WorkOrderManagerImpl extends BaseManagerImpl<WorkOrder> implements WorkOrderManager{

	private static final long serialVersionUID = -7735650097523062984L;
	
	private static Logger logger = LoggerFactory.getLogger(WorkOrderManagerImpl.class);
	
	@Resource( name = "workOrderChangeManager" )
	private WorkOrderChangeManager workOrderChangeManager;
	
	@Autowired
	private SequenceManager sequenceManager;
	
	@Autowired
	private CustCertManager custCertManager;
	
	private WorkOrderDao workOrderDao;
	
	@Resource(name="workOrderDao")
	public void setDao(WorkOrderDao dao){
		this.dao = dao;
		this.workOrderDao = dao;
	}
	
	public WorkOrderDao getDao(){
		return (WorkOrderDao)this.dao;
	}
	
	@Override
	public WorkOrder getWorkOrder( Long id ) {
		return getDao().getWorkOrder(id);
	}
	
	@Override
	public String create(WorkOrderDTO workOrderDTO, LoginAccount user)
			throws Exception {
		Assert.notNull( workOrderDTO, "workOrderDTO不能为空." );
		Assert.notNull( workOrderDTO.getOrderNo(), "orderNo不能为空." );
		Assert.notNull( workOrderDTO.getContactUserPhone(), "ContactUserPhone不能为空." );
		Assert.notNull( workOrderDTO.getAscendingNodeName(), "AscendingNodeName不能为空." );
		Assert.notNull( workOrderDTO.getIpAlready(), "IpAlready不能为空." );
		Assert.notNull( workOrderDTO.getNewIpDistribution(), "NewIpDistribution不能为空." );
		Assert.notNull( workOrderDTO.getDistributionSign(), "DistributionSign不能为空." );
		Assert.notNull( workOrderDTO.getDistributioDate(), "DistributioDate不能为空." );
		Assert.notNull( workOrderDTO.getLineTerminalDevice(), "LineTerminalDevice不能为空." );
		Assert.notNull( workOrderDTO.getOriginalDeviceModel(), "OriginalDeviceModel不能为空." );
		Assert.notNull( workOrderDTO.getOriginalDeviceAmount(), "OriginalDeviceAmount不能为空." );

		WorkOrder workOrder = this.dtoToEntity(workOrderDTO);
		workOrder.setCreateTime(new Date());
		dao.create(workOrder);
		return "success";
	}
	
	
    public ListPage<WorkOrder> list(ListPage<WorkOrder> page,WorkOrder workOrder){
    	if(page == null){
    		page = new ListPage<WorkOrder>();
    		OrderBy ob = new OrderBy();
    		ob.setProperty("createTime");
    		ob.setType(OrderType.DESCENDING);
    		page.setOrderBy(ob);
    	}
    	SearchCondition sc = new SearchCondition();
    	if(workOrder==null){
    		workOrder = new WorkOrder();
    		page = dao.list(page, sc);
    		return page;
    	}
    	if(workOrder.getCreator()!=null&&!"".equals(workOrder.getCreator())){
    		sc.equal("creator", workOrder.getCreator());
    	}
    	int count = 0;
    	count = getDao().getCount(page,workOrder);
    	page = dao.list(page, sc);
    	if(page!=null){
    		page.setAllSize(count);
    	}
    	return page;
    }
    
    public String update(LoginAccount user,WorkOrder workOrder,
			List<UploadBean> listBean) throws Exception {
		if ( workOrder == null )
			throw new ApplicationException( "参数为空！" );
		
		WorkOrder dbWorkOrder = this.getDao().getWorkOrder(workOrder.getId());
		// 原对象，新对象
		WorkOrder originalWorkOrder = dbWorkOrder, newWorkOrder = workOrder;
		// 创建日期.
		newWorkOrder.setCreateTime( dbWorkOrder.getCreateTime());
		if(workOrder.getStatus() != null){
			newWorkOrder.setStatus( workOrder.getStatus() );
		}else{
			newWorkOrder.setStatus(dbWorkOrder.getStatus());
		}
		//审核状态
		newWorkOrder.setWorkOrderAuditStatus(dbWorkOrder.getWorkOrderAuditStatus());
		newWorkOrder.setCreator(dbWorkOrder.getCreator());
		if ( listBean != null && !listBean.isEmpty() ) {
			int size = listBean.size();
			int i = 0;
			while ( i < size ) {
				CustCert custCert = new CustCert();
				byte[] fileBytes = null;
				if ( listBean.get( i ) != null ) {
					fileBytes = FileUtils.readFileToByteArray( listBean.get( i ).getUpload() );
					custCert.setCertFile( fileBytes );
				}
				custCert.setAuditStatus( AuditStatus.WAIT_AUDIT );
				switch ( i ) {
					case 0:
						custCert.setCertType( CertificationType.P01 );
					case 1:
						custCert.setCertType( CertificationType.P01 );
					default:
						break;
				}
				custCert = null;
				i++;
			}
		} 
		// 变更记录
		String remark = "工单信息变更，审核通过后生效！";
		if(workOrder.getRemark()!=null){
			remark = workOrder.getRemark();
		}
		WorkOrderChange.Type type = WorkOrderChange.Type.UPDATE;
		if(newWorkOrder.getStatus()!=null&&EntityStatus.DISABLED.equals(newWorkOrder.getStatus())){
			type = WorkOrderChange.Type.DELETE;
		}
		workOrderChangeManager.save( user, type, originalWorkOrder, newWorkOrder, remark );
		return "修改成功！";
	}
	
    private WorkOrder dtoToEntity(WorkOrderDTO workOrderDTO){
		if(workOrderDTO == null) return null;
		WorkOrder workOrder = new WorkOrder();
		BeanUtils.copyProperties(workOrderDTO, workOrder, new String[]{"type","status","plate","id"});
		if(workOrderDTO.getId()!=null){
			workOrder.setId(Long.parseLong(workOrderDTO.getId()));
		}
		return workOrder;
	}
	
	private WorkOrderDTO entityToDTO(WorkOrder workOrder){
		if(workOrder == null) return null;
		WorkOrderDTO workOrderDTO = new WorkOrderDTO();
		BeanUtils.copyProperties(workOrder, workOrderDTO, new String[]{"type","status","plate","id"});
		if(workOrder.getId()!=null){
			workOrderDTO.setId(workOrder.getId().toString());
		}
		return workOrderDTO;
	}

	@Override
	public void save(WorkOrder workOrder, List<UploadBean> listBean)
			throws Exception {
		logger.debug( "新增工单..." );

		Assert.notNull( workOrder, "workOrderDTO不能为空." );
		Assert.notNull( workOrder.getContactUserPhone(), "ContactUserPhone不能为空." );
		Assert.notNull( workOrder.getAscendingNodeName(), "AscendingNodeName不能为空." );
		Assert.notNull( workOrder.getIpAlready(), "IpAlready不能为空." );
		Assert.notNull( workOrder.getNewIpDistribution(), "NewIpDistribution不能为空." );
		Assert.notNull( workOrder.getDistributionSign(), "DistributionSign不能为空." );
		Assert.notNull( workOrder.getDistributioDate(), "DistributioDate不能为空." );
		Assert.notNull( workOrder.getLineTerminalDevice(), "LineTerminalDevice不能为空." );
		Assert.notNull( workOrder.getOriginalDeviceModel(), "OriginalDeviceModel不能为空." );
		Assert.notNull( workOrder.getOriginalDeviceAmount(), "OriginalDeviceAmount不能为空." );

		workOrder.setOrderNo( this.sequenceManager.nextSequenceNumber( workOrder ));
		// 冻结状态: 冻结.
		workOrder.setStatus(EntityStatus.NEW);
		// 审核状态: 待复核.
		workOrder.setWorkOrderAuditStatus( WorkOrderAuditStatus.WAIT_AUDIT );
		// 创建时间
		workOrder.setCreateTime( new Date() );
		if ( listBean != null && !listBean.isEmpty() )
			for ( int i = 0; i < listBean.size(); i++ ) {
				CustCert custCert = new CustCert();
				byte[] fileBytes = FileUtils.readFileToByteArray( listBean.get( i ).getUpload() );
				custCert.setCertFile( fileBytes );
				custCert.setAuditStatus( AuditStatus.WAIT_AUDIT );
				if(i==0){
					custCert.setCertType(CertificationType.C01);
					workOrder.setBusinessLicenseCertId((Long)custCertManager.saveEntity( custCert ));
				}else{
					custCert.setCertType(CertificationType.P01);
					workOrder.setIdCardCertId((Long)custCertManager.saveEntity( custCert ));
				}
			}
		this.dao.create( workOrder );
	}
	
	/**
	 * 修改状态.
	 * @throws AccountCheckedException 
	 */
	@Override
	@Transactional
	public void modify( Long id, WorkOrderAuditStatus fromStatus, WorkOrderAuditStatus toStatus ) {
		Assert.notNull( id, "id不能为空." );
		Assert.notNull( fromStatus, "fromStatus不能为空." );
		Assert.notNull( toStatus, "toStatus不能为空." );
		WorkOrder entity = this.workOrderDao.read( id );
		if ( null != entity ) {
			if ( fromStatus.getValue().equals( entity.getWorkOrderAuditStatus().getValue())) {
				entity.setWorkOrderAuditStatus( toStatus);
				/* 复核通过. */
				if ( WorkOrderAuditStatus.AUDIT_PASS.equals( toStatus )) {
					//有效.
					entity.setStatus( EntityStatus.NORMAL);
				}
				this.workOrderDao.update(entity);
			} else {
				throw new ApplicationException( "该记录状态[" + entity.getStatus() + "]不能发起[" + toStatus + "]操作." );
			}
		} else {
			throw new ApplicationException( "未根据id[" + id + "]查询到信息." );
		}
	}

	public SequenceManager getSequenceManager() {
		return sequenceManager;
	}

	public void setSequenceManager(SequenceManager sequenceManager) {
		this.sequenceManager = sequenceManager;
	}
}
