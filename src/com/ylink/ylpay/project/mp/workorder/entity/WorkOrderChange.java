package com.ylink.ylpay.project.mp.workorder.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.code.lightssh.project.log.entity.EntityChange;

/**
 * 企业客户信息变更
 * @author Petter
 */
@Entity
@Table(name="T_WORKORDER_CHANGE")
public class WorkOrderChange extends EntityChange{

	private static final long serialVersionUID = -3794589637209439097L;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="WORKORDER_ID")
	private WorkOrder workOrder;
	
	@Transient
	private Date createdTimeStart;
	
	@Transient
	private Date createdTimeEnd;
	
	public WorkOrder getWorkOrder() {
		return workOrder;
	}
	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}
	
	@Override
	public void preInsert() {
		this.createdTime = Calendar.getInstance();
	}
	
	@Override
	public boolean isInsert() {
		return this.getIdentity() == null;
	}
	public Date getCreatedTimeStart() {
		return createdTimeStart;
	}
	public void setCreatedTimeStart(Date createdTimeStart) {
		this.createdTimeStart = createdTimeStart;
	}
	public Date getCreatedTimeEnd() {
		return createdTimeEnd;
	}
	public void setCreatedTimeEnd(Date createdTimeEnd) {
		this.createdTimeEnd = createdTimeEnd;
	}



}
