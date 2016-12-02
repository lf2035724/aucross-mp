package com.ylink.ylpay.project.mp.calendar.service;

import java.util.Collection;
import java.util.List;

import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.common.project.mp.constant.WorkCalendarSystemType;
import com.ylink.ylpay.common.project.mp.constant.WorkDayType;
import com.ylink.ylpay.project.mp.calendar.entity.WorkCalendar;

/**
 * 工作日历业务层.
 * 
 * @author 潘瑞峥
 * @date 2012-10-31
 */
public interface WorkCalendarManager extends BaseManager<WorkCalendar> {
	/**
	 * 初始化工作日历.
	 * 
	 * @param year
	 */
	public void init( String year);

	/**
	 * 初始化工作日历.
	 * 
	 * @param year
	 * @param system 
	 */
	public void init( String year, String system );

	/**
	 * 修改工作日状态，默认修改管理平台(WorkCalendarSystemType.MP).
	 * 
	 * @param id
	 * @param workDayType
	 */
	public void edit( String id, WorkDayType workDayType );
	/**
	 * 修改工作日状态，增加系统条件.
	 * @param id
	 * @param workDayType
	 * @param systemType
	 */
	public void edit(String id, WorkDayType workDayType,WorkCalendarSystemType systemType);

	/**
	 * 查询集合，返回默认系统结果（管理平台）.
	 * 
	 * @param termList
	 * @return
	 */
	public List<WorkCalendar> list( List<Term> termList );
	/**
	 * 查询集合，多系统查询.
	 * 
	 * @param termList
	 * @return
	 */
	public List<WorkCalendar> listNew( List<Term> termList );

	/**
	 * 根据传入工作日返回下一工作日.
	 * 
	 * @param time
	 * @return
	 */
	public String findNextWorkCalendar( String time );
	
	public String findNextWorkCalendar(String time, WorkCalendarSystemType system);
	
	/**
	 * 根据日期和系统返回唯一工作日
	 * 
	 * @param identity
	 * @param workSystem
	 * @return
	 */
	public WorkCalendar get(String identity, String workSystem);
	/**
	 * 查询所有系统的日历，list方法返回默认系统
	 * @param page
	 * @param terms
	 * @return
	 */
	ListPage<WorkCalendar> listNew(ListPage<WorkCalendar> page, Collection<Term> terms);

}