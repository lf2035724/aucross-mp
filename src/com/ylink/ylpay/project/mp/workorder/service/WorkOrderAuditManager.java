package com.ylink.ylpay.project.mp.workorder.service;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrderAudit;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrderChange;

public interface WorkOrderAuditManager extends BaseManager<WorkOrderAudit>{
	
	/**
	 * 审核
	 */
	public void audit(WorkOrderAudit ea,WorkOrderChange ec );

}
