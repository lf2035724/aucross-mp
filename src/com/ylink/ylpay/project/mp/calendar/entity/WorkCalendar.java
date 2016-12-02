package com.ylink.ylpay.project.mp.calendar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.code.lightssh.common.entity.Persistence;
import com.google.code.lightssh.common.util.StringUtil;
import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.ylpay.common.project.mp.constant.WorkCalendarSystemType;
import com.ylink.ylpay.common.project.mp.constant.WorkDayType;
import com.ylink.ylpay.project.constants.PatternConstant;

/**
 * 工作日历实体.
 * 
 * @author 潘瑞峥
 * @date 2012-10-31
 */
@Entity
@Table( name = "T_WORK_CALENDAR" )
@IdClass(value=WorkCalendarIdPk.class)
public class WorkCalendar implements Persistence<String> {

	private static final long serialVersionUID = -5250330850836467535L;

	/** 日期(yyyyMMdd). */
	@Id
	@Column( name = "DAY" )
	private String identity;

	/** 所对应的工作系统 */
	@Id
	@Column( name = "work_system" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.WorkCalendarSystemType" ) } )
	private WorkCalendarSystemType workSystem;	
	
	/** 是否为工作日. */
	@Column( name = "WORK_FLAG" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.WorkDayType" ) } )
	private WorkDayType workDayType;

	/** 星期几. */
	@Transient
	private String week;

	@Transient
	private String workDayTypeName;

	@Transient
	private String workSystemTypeName;

	
	public WorkCalendar() {
	}

	public WorkCalendar( String identity, WorkDayType workDayType ,WorkCalendarSystemType workSystem) {
		this.identity = identity;
		this.workDayType = workDayType;
		this.workSystem = workSystem;
	}

	public void setIdentity( String identity ) {
		this.identity = identity;
	}

	public WorkDayType getWorkDayType() {
		return workDayType;
	}

	public void setWorkDayType( WorkDayType workDayType ) {
		this.workDayType = workDayType;
	}

	public String getWeek() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern( PatternConstant.DATE_SHORT );
		LocalDate date = LocalDate.parse( this.getIdentity(), dateTimeFormatter );
		week = date.dayOfWeek().getAsShortText();
		return week;
	}

	public String getWorkDayTypeName() {
		workDayTypeName = this.getWorkDayType().name();
		return workDayTypeName;
	}

	public WorkCalendarSystemType getWorkSystem() {
		return workSystem;
	}

	public void setWorkSystem(WorkCalendarSystemType workSystem) {
		this.workSystem = workSystem;
	}
	public String getWorkSystemTypeName() {
		if(getWorkSystem() != null){
			workSystemTypeName = this.getWorkSystem().name();
		}
		return workSystemTypeName;
	}

	public void setWorkSystemTypeName(String workSystemTypeName) {
		this.workSystemTypeName = workSystemTypeName;
	}

	@Override
	public void preInsert() {
	}

	@Override
	public void postInsertFailure() {
	}

	@Override
	public boolean isInsert() {
		return StringUtils.isNotBlank( this.getIdentity() );
	}

	@Override
	public String getIdentity() {
		return identity;
	}

}