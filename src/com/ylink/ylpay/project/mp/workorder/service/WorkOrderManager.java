package com.ylink.ylpay.project.mp.workorder.service;

import java.util.List;

import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.service.BaseManager;
import com.google.code.lightssh.project.security.entity.LoginAccount;
import com.google.code.lightssh.project.util.constant.WorkOrderAuditStatus;
import com.ylink.modules.bean.UploadBean;
import com.ylink.ylpay.common.project.portal.dto.WorkOrderDTO;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrder;

/**
 * @author feng.li
 */
public interface WorkOrderManager extends BaseManager<WorkOrder>{
	
	public String create( WorkOrderDTO workOrderDTO,LoginAccount user ) throws Exception;
	
	public ListPage<WorkOrder> list(ListPage<WorkOrder> page,WorkOrder workOrder);
	
	public void save( WorkOrder workOrder, List<UploadBean> listUpload ) throws Exception;

	public WorkOrder getWorkOrder( Long id );
	
	public String update(LoginAccount user,WorkOrder workOrder,
			List<UploadBean> listBean) throws Exception;
	public void modify( Long id, WorkOrderAuditStatus fromStatus, WorkOrderAuditStatus toStatus );

}
