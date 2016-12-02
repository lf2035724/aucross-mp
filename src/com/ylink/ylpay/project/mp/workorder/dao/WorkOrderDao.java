package com.ylink.ylpay.project.mp.workorder.dao;

import java.util.Collection;

import com.google.code.lightssh.common.dao.Dao;
import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.model.page.ListPage;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrder;

/**
 * @author feng.li
 */
public interface WorkOrderDao extends Dao<WorkOrder>{
	
	/**
	 * 查询具体子类
	 */
	public WorkOrder read( Class<?> clazz,WorkOrder workOrder );
	
	/**
	 * 获取客户信息
	 */
	public WorkOrder getWorkOrder( Long id );
	
	public int getCount(ListPage<WorkOrder> page,WorkOrder workOrder);
	
	
}
