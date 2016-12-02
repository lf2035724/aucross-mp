package com.ylink.ylpay.project.mp.workorder.dao;

import org.springframework.stereotype.Repository;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrderChange;

@Repository("workOrderChangeDao")
public class WorkOrderChangeDaoJpa extends CustomJpaDaoImpl<WorkOrderChange> implements WorkOrderChangeDao{

	private static final long serialVersionUID = -3397603383101134996L;

	

}
