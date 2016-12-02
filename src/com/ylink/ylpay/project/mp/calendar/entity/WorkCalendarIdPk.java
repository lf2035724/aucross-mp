package com.ylink.ylpay.project.mp.calendar.entity;

import com.ylink.ylpay.common.project.mp.constant.WorkCalendarSystemType;


public class WorkCalendarIdPk implements java.io.Serializable {

	private static final long serialVersionUID = 3817490714261065643L;

	private String identity;

	private WorkCalendarSystemType workSystem;

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public WorkCalendarSystemType getWorkSystem() {
		return workSystem;
	}

	public void setWorkSystem(WorkCalendarSystemType workSystem) {
		this.workSystem = workSystem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identity == null) ? 0 : identity.hashCode());
		result = prime * result
				+ ((workSystem == null) ? 0 : workSystem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkCalendarIdPk other = (WorkCalendarIdPk) obj;
		if (identity == null) {
			if (other.identity != null)
				return false;
		} else if (!identity.equals(other.identity))
			return false;
		if (workSystem == null) {
			if (other.workSystem != null)
				return false;
		} else if (!workSystem.equals(other.workSystem))
			return false;
		return true;
	}
	
}
