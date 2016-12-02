package com.ylink.ylpay.project.mp.workorder.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.code.lightssh.project.log.entity.EntityAudit;

@Entity
@Table(name="T_WORKORDER_AUDIT")
public class WorkOrderAudit extends EntityAudit{

	private static final long serialVersionUID = 7614302353703249298L;
	/**
	 * 企业客户变更
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="WORKORDER_CHANGE_ID")
	private WorkOrderChange workOrderChange;
	
	@Transient
	private Date createdTimeStart;
	
	@Transient
	private Date createdTimeEnd;
	
	@Override
	public void preInsert() {
		this.createdTime = Calendar.getInstance();
	}
	
	@Override
	public boolean isInsert() {
		return this.getIdentity() == null;
	}

	public WorkOrderChange getWorkOrderChange() {
		return workOrderChange;
	}

	public void setWorkOrderChange(WorkOrderChange workOrderChange) {
		this.workOrderChange = workOrderChange;
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
