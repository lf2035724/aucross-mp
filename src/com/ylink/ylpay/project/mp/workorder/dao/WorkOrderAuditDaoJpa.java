package com.ylink.ylpay.project.mp.workorder.dao;

import org.springframework.stereotype.Repository;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrderAudit;

@Repository("workOrderAuditDao")
public class WorkOrderAuditDaoJpa extends CustomJpaDaoImpl<WorkOrderAudit> implements WorkOrderAuditDao{

	private static final long serialVersionUID = 4965047737673722656L;


}
