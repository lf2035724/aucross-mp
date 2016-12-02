package com.ylink.ylpay.project.mp.calendar.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.common.project.mp.constant.WorkCalendarSystemType;
import com.ylink.ylpay.project.mp.calendar.entity.WorkCalendar;

/**
 * 工作日历DAO.
 * 
 * @author 潘瑞峥
 * @date 2012-10-31
 */
@Repository( "workCalendarDao" )
public class WorkCalendarDaoJpa extends CustomJpaDaoImpl<WorkCalendar> implements WorkCalendarDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4387065145362151941L;
	
	@Override
	public String findNextWorkCalendar(String time) {
		return findNextWorkCalendar(time,WorkCalendarSystemType.MP);
	}
	
	@Override
	public String findNextWorkCalendar(String time,
			WorkCalendarSystemType system) {
		String hql =  "select w.identity from WorkCalendar w " +
			 	  "where w.identity > ? and w.workDayType = 1 and w.workSystem = ? and limit 2" +
			 	  " order by w.identity";
		Query query = this.getEntityManager().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<String> list = this.addQueryParams(query, new Object[]{time,system}).getResultList();
		return (list == null || list.isEmpty()) ? null : list.get(0);
	}

	@Override
	public WorkCalendar findWorkDayByUnionPk(String id, WorkCalendarSystemType system) {
		String hql =  "select w from WorkCalendar w " +
			 	  "where w.identity = ? and w.workSystem = ?" +
			 	  " order by w.identity";
		Query query = this.getEntityManager().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<WorkCalendar> list = this.addQueryParams(query, new Object[]{id,system}).getResultList();
		return (list == null || list.isEmpty()) ? null : list.get(0);
	}
	
}