package com.ylink.ylpay.project.mp.workorder.service;

import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.service.BaseManager;
import com.google.code.lightssh.project.log.entity.EntityChange;
import com.google.code.lightssh.project.security.entity.LoginAccount;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrder;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrderChange;

public interface WorkOrderChangeManager extends BaseManager<WorkOrderChange>{
	
	public ListPage<WorkOrderChange> listTodoAudit(
			ListPage<WorkOrderChange> ecPage, WorkOrderChange workOrderChange);

	public ListPage<WorkOrderChange> findAuditList(
			ListPage<WorkOrderChange> ecPage, WorkOrderChange workOrderChange);

	public void save(LoginAccount user,
			com.google.code.lightssh.project.log.entity.EntityChange.Type type,
			WorkOrder originalWorkOrder, WorkOrder newWorkOrder, String remark);
	
	/**
	 * 更新状态
	 */
	public void updateStatus(long id,EntityChange.Status originalStatus,EntityChange.Status newStatus);

	public boolean findWorkOrderChange(Long id);
	
}
