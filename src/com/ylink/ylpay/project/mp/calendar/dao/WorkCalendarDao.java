package com.ylink.ylpay.project.mp.calendar.dao;

import com.ylink.modules.orm.jpa.CustomJpaDao;
import com.ylink.ylpay.common.project.mp.constant.WorkCalendarSystemType;
import com.ylink.ylpay.project.mp.calendar.entity.WorkCalendar;

/**
 * 工作日历DAO.
 * 
 * @author 潘瑞峥
 * @date 2012-10-31
 */
public interface WorkCalendarDao extends CustomJpaDao<WorkCalendar> {
	/**
	 * 通过传入的时间查询下一个工作日
	 * @param 传入的时间
	 * @author YangHan
	 * @date 2013-01-20
	 * @return
	 */
	public String findNextWorkCalendar(String time);
	public String findNextWorkCalendar(String time, WorkCalendarSystemType system);
	/**
	 * 通过日期和所属系统查找对应的WorkCalendar
	 * @param day格式：yyyyMMdd
	 * @param 传入的系统
	 * @author wenwen
	 * @date 2014-12-01
	 * @return
	 */
	public WorkCalendar findWorkDayByUnionPk(String day, WorkCalendarSystemType workCalendarSystemType);
	
}