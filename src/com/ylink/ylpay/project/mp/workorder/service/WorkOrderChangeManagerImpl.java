
package com.ylink.ylpay.project.mp.workorder.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.lightssh.common.ApplicationException;
import com.google.code.lightssh.common.dao.SearchCondition;
import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.google.code.lightssh.common.util.IoSerialUtil;
import com.google.code.lightssh.project.log.entity.EntityChange.Status;
import com.google.code.lightssh.project.log.entity.EntityChange.Type;
import com.google.code.lightssh.project.security.entity.LoginAccount;
import com.google.code.lightssh.project.util.constant.WorkOrderAuditStatus;
import com.ylink.ylpay.common.project.portal.constant.EntityStatus;
import com.ylink.ylpay.project.mp.workorder.dao.WorkOrderChangeDao;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrder;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrderChange;

@Component("workOrderChangeManager")
public class WorkOrderChangeManagerImpl extends BaseManagerImpl<WorkOrderChange> implements WorkOrderChangeManager{

	private static final long serialVersionUID = 965045405616354133L;
	
	@Resource(name="workOrderChangeDao")
	public void setDao(WorkOrderChangeDao dao ){
		this.dao = dao;
	}
	
	public WorkOrderChangeDao getDao(){
		return (WorkOrderChangeDao)this.dao;
	}

	@Override
	public ListPage<WorkOrderChange> listTodoAudit(
			ListPage<WorkOrderChange> ecPage, WorkOrderChange workOrderChange) {
		SearchCondition sc = new SearchCondition();
		if( workOrderChange != null ){
			if( workOrderChange.getWorkOrder() != null ){
				if( !StringUtils.isEmpty(workOrderChange.getWorkOrder().getSalesClerk()) ){
					sc.like("workOrder.salesClerk",workOrderChange.getWorkOrder().getSalesClerk());
				}
				if( !StringUtils.isEmpty(workOrderChange.getWorkOrder().getOrderNo()) ){
					sc.equal("workOrder.orderNo",workOrderChange.getWorkOrder().getOrderNo());
				}
				if( !StringUtils.isEmpty(workOrderChange.getWorkOrder().getContactUserName()) ){
					sc.like("workOrder.contactUserName",workOrderChange.getWorkOrder().getContactUserName());
				}
					
				if( workOrderChange.getCreatedTimeStart() != null ){
					sc.greateThanOrEqual("createdTime", workOrderChange.getCreatedTimeStart());
				}
				if( workOrderChange.getCreatedTimeEnd() != null ){
					sc.lessThan("createdTime", workOrderChange.getCreatedTimeEnd());
				}
			}
		}
		sc.in("status",WorkOrderChange.Status.NEW.name(),WorkOrderChange.Status.PROCESSING.name());
		return getDao().list(ecPage,sc);
	}

	@Override
	public ListPage<WorkOrderChange> findAuditList(
			ListPage<WorkOrderChange> ecPage, WorkOrderChange workOrderChange) {
		SearchCondition sc = new SearchCondition();
		if( workOrderChange != null ){
			if( workOrderChange.getWorkOrder() != null ){
				if( !StringUtils.isEmpty(workOrderChange.getWorkOrder().getOrderNo()) ){
					sc.like("workOrder.orderNo",workOrderChange.getWorkOrder().getOrderNo());
				}
			}
		}
		return getDao().list(ecPage,sc);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(LoginAccount user, Type type, WorkOrder originalWorkOrder,
			WorkOrder newWorkOrder, String remark) {
		if( originalWorkOrder == null && newWorkOrder == null )
			throw new ApplicationException("原始角色或新角色为空！");
		
		WorkOrderChange ec = new WorkOrderChange();
		ec.setStatus(WorkOrderChange.Status.NEW);
		ec.setOperator(user);
		ec.setType(type);
		ec.setDescription(remark);
		ec.setWorkOrder(originalWorkOrder);
		
		if( newWorkOrder != null )
			ec.setNewObject( IoSerialUtil.serialize(newWorkOrder) );
		
		if( originalWorkOrder != null )
			ec.setOriginalObject(IoSerialUtil.serialize(originalWorkOrder) );
		
		dao.create(ec);
	}

	@Override
	public void updateStatus(long id, Status originalStatus, Status newStatus) {
		getDao().update("id",id,"status",originalStatus, newStatus);
	}

	@Override
	public boolean findWorkOrderChange(Long id) {
		boolean mark = false;
		List<Term> termList = new ArrayList<Term>();
		termList.add( new Term( Term.Type.EQUAL, "workOrder.id", id ) );
		termList.add( new Term( Term.Type.IN, "status", new Object[]{WorkOrderChange.Status.NEW.name(),WorkOrderChange.Status.PROCESSING.name()} ) );
		WorkOrderChange entity = getDao().findUnique(termList);
		if ( null == entity ) {
			mark = true;
		}
		return mark;
	}

}
