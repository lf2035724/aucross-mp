package com.ylink.ylpay.project.mp.workorder.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.ApplicationException;
import com.google.code.lightssh.common.dao.SearchCondition;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.google.code.lightssh.common.util.IoSerialUtil;
import com.google.code.lightssh.project.log.entity.EntityChange;
import com.google.code.lightssh.project.util.constant.AuditResult;
import com.google.code.lightssh.project.util.constant.WorkOrderAuditStatus;
import com.ylink.ylpay.common.project.portal.constant.EntityStatus;
import com.ylink.ylpay.project.mp.workorder.dao.WorkOrderAuditDao;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrder;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrderAudit;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrderChange;

@Component("workOrderAuditManager") 
public class WorkOrderAuditManagerImpl extends BaseManagerImpl<WorkOrderAudit> implements WorkOrderAuditManager{

	private static final long serialVersionUID = -8219134739997237462L;
	
	private static Logger logger = LoggerFactory.getLogger( WorkOrderAuditManagerImpl.class );
	
	@Resource(name="workOrderChangeManager")
	private WorkOrderChangeManager workOrderChangeManager;
	
	@Resource(name="workOrderManager")
	private WorkOrderManager workOrderManager;
	
	@Resource(name="workOrderAuditDao")
	public void setDao(WorkOrderAuditDao dao){
		this.dao = dao;
	}
	
	public WorkOrderAuditDao getDao(){
		return (WorkOrderAuditDao)this.dao;
	}
	
	/**
	 * 审核
	 */
	public void audit(WorkOrderAudit ea,WorkOrderChange workOrderChange ){
		if( ea == null )
			throw new ApplicationException("参数为空！");
		
		if( workOrderChange == null || workOrderChange.getId()==null )
			throw new ApplicationException("工单变更信息为空！");
		
		WorkOrderChange ec = this.workOrderChangeManager.get(workOrderChange);
		if( ec == null )
			throw new ApplicationException("工单变更信息["+workOrderChange.getId()+"]不存在！");
		
		WorkOrder dbWorkOrder = workOrderManager.get(workOrderChange.getWorkOrder());
		WorkOrder newWorkOrder = null;
		byte[] newObject = ec.getNewObject();
		if( newObject != null )
			newWorkOrder = (WorkOrder)IoSerialUtil.deserialize(newObject);
		if( newObject == null || newWorkOrder == null )
			throw new ApplicationException("数据异常，变更工单信息为空！");
		
		boolean passed = AuditResult.LAST_AUDIT_PASSED.equals(ea.getResult());
		newWorkOrder.setOrderNo(dbWorkOrder.getOrderNo());
		if(passed){
			if(EntityStatus.DISABLED.equals(newWorkOrder.getStatus())){
				workOrderManager.remove(dbWorkOrder);
			}else{
				newWorkOrder.setWorkOrderAuditStatus(WorkOrderAuditStatus.AUDIT_PASS);
				workOrderManager.update(newWorkOrder);
			}
		}
		dao.create(ea);
		
		workOrderChangeManager.updateStatus(ec.getId()
				,EntityChange.Status.NEW,EntityChange.Status.FINISHED);
	}

	public ListPage<WorkOrderAudit> list(ListPage<WorkOrderAudit> page,WorkOrderAudit ea){
		SearchCondition sc = new SearchCondition();
		if( ea != null ){
			if( ea.getWorkOrderChange() != null ){
				WorkOrderChange ec = ea.getWorkOrderChange();
				if( ec.getWorkOrder() != null ){
					if( !StringUtils.isEmpty(ec.getWorkOrder().getOrderNo()) ){
						sc.like("workOrderChange.workOrder.orderNo", ec.getWorkOrder().getOrderNo());
					}
					if( ea.getCreatedTimeStart() != null ){
						sc.greateThanOrEqual("createdTime", ea.getCreatedTimeStart());
					}
					if( ea.getCreatedTimeEnd() != null ){
						sc.lessThan("createdTime", ea.getCreatedTimeEnd());
					}
				}
			}
		}
		return dao.list(page, sc);
	}

}
