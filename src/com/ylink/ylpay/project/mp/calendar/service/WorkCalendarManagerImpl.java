package com.ylink.ylpay.project.mp.calendar.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.dao.SearchCondition;
import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.dao.Term.Type;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.common.project.mp.constant.WorkCalendarSystemType;
import com.ylink.ylpay.common.project.mp.constant.WorkDayType;
import com.ylink.ylpay.project.constants.PatternConstant;
import com.ylink.ylpay.project.mp.calendar.dao.WorkCalendarDao;
import com.ylink.ylpay.project.mp.calendar.entity.WorkCalendar;
import com.ylink.ylpay.project.utils.DateUtils;

/**
 * 工作日历业务层.
 * 
 * @author 潘瑞峥
 * @date 2012-10-31
 */
@Component( "workCalendarManager" )
public class WorkCalendarManagerImpl extends BaseManagerImpl<WorkCalendar> implements WorkCalendarManager {

	private static final long serialVersionUID = 7786073489699373979L;

	private WorkCalendarDao workCalendarDao;

	@Autowired
	public void setWorkCalendarDao( WorkCalendarDao workCalendarDao ) {
		this.workCalendarDao = workCalendarDao;
		super.dao = this.workCalendarDao;
	}

	/**
	 * 初始化工作日历.
	 * 
	 * 创建一年的日期.
	 * 
	 * 默认系统名称（管理平台）
	 */
	@Override
	public void init(String year) {
		Validate.matchesPattern( year, "\\d{4}", "年份不正确." );
		
		
		doInit(year, WorkCalendarSystemType.MP);
	}
	
	/**
	 * 初始化工作日历.
	 * 
	 * 创建一年的日期.
	 */
	@Override
	public void init( String year,String system ) {
		Validate.matchesPattern( year, "\\d{4}", "年份不正确." );
		Validate.notNull(system, "系统参数不能为空。", "请填写正确的系统格式." );
		WorkCalendarSystemType workCalendarSystemType = Enum.valueOf( WorkCalendarSystemType.class, system );
		doInit(year, workCalendarSystemType);
	}

	private void doInit(String year, WorkCalendarSystemType workCalendarSystemType ) {
		WorkCalendar entity = null;
		// 星期几.
		int dayOfWeek;
		// 是否为工作日.
		WorkDayType workDayType = null;

		int unit = 1;
		// 根据传入的年份设置并把月和日设置为1.
		LocalDate date = new LocalDate( Integer.valueOf( year ), unit, unit );
		// 最大天数.
		int maxDay = date.dayOfYear().getMaximumValue();
		// 最小天数.
		int minDay = date.dayOfYear().getMinimumValue();
		// 先减少一天，再从1月1日开始.
		date = date.minusDays( unit );
		for ( int i = minDay; i <= maxDay; i++ ) {
			// 增加一天.
			date = date.plusDays( unit );

			dayOfWeek = date.dayOfWeek().get();
			workDayType = DateUtils.checkWorkDayType( dayOfWeek );

			String id = date.toString( PatternConstant.DATE_SHORT );
			/*entity = this.get( id );*/
			
			entity = this.workCalendarDao.findWorkDayByUnionPk( id, workCalendarSystemType );

			// 新增
			if ( null == entity ) {
				entity = new WorkCalendar( id, workDayType, workCalendarSystemType);
				this.workCalendarDao.create( entity );
			}
			// 修改
			else {
				entity.setWorkDayType( workDayType );
				this.workCalendarDao.update( entity );
			}
		}
	}

	/**
	 * 修改工作日状态，默认修改管理平台(WorkCalendarSystemType.MP)..
	 */
	@Override
	public void edit( String id, WorkDayType workDayType ) {
		WorkCalendar entity = this.get( id );
		entity.setWorkDayType( workDayType );
		entity.setWorkSystem(WorkCalendarSystemType.MP);
		this.workCalendarDao.update( entity );
	}

	/**
	 * 修改工作日状态，增加系统条件.
	 */
	@Override
	public void edit(String id, WorkDayType workDayType, WorkCalendarSystemType systemType) {
		WorkCalendar entity = this.get(id, systemType.getValue().toString());
		entity.setWorkDayType( workDayType );
		entity.setWorkSystem(systemType);
		this.workCalendarDao.update( entity );
	}

	/**
	 * 查询集合，返回默认系统结果（管理平台）.
	 */
	@Override
	public List<WorkCalendar> list( List<Term> termList ) {
		termList.add(buildTermDefaultSystem());
		return this.workCalendarDao.findList( termList );
	}

	private Term buildTermDefaultSystem() {
		return new Term(Type.EQUAL, "workSystem", "管理平台");
	}

	/**
	 * 查询集合.
	 */
	@Override
	public List<WorkCalendar> listNew(List<Term> termList) {
		return this.workCalendarDao.findList( termList );
	}
	
	
	/**
	 * 根据传入工作日返回下一工作日.
	 */
	@Override
	public String findNextWorkCalendar( String time ) {	
		Validate.notBlank( time, "传入的日期不能为空." );
		Validate.matchesPattern( time, "\\d{8}", "传入的日期格式不正确，应该为" + PatternConstant.DATE_SHORT + "." );
		return this.workCalendarDao.findNextWorkCalendar(time);
	}
	
	@Override
	public String findNextWorkCalendar(String time, WorkCalendarSystemType system) {
		Validate.notBlank( time, "传入的日期不能为空." );
		Validate.matchesPattern( time, "\\d{8}", "传入的日期格式不正确，应该为" + PatternConstant.DATE_SHORT + "." );
		Validate.notNull( system, "传入的系统不能为空." );
		String day = this.workCalendarDao.findNextWorkCalendar(time,system);
		if(StringUtils.isBlank(day)){
			day = this.workCalendarDao.findNextWorkCalendar(time);
		}
		return day;
	}

	@Override
	public WorkCalendar get(String identity, String workSystem) {
		Validate.notBlank( identity, "传入的日期不能为空." );
		Validate.notBlank( workSystem, "传入的系统不能为空." );
		WorkCalendarSystemType workCalendarSystemType = null;
		if ( StringUtils.isNotBlank( workSystem ) ) {
			workCalendarSystemType = WorkCalendarSystemType.returnEnum(Integer.parseInt(workSystem));
			
		}else{
			workCalendarSystemType = WorkCalendarSystemType.MP;
		}
		return this.workCalendarDao.findWorkDayByUnionPk( identity, workCalendarSystemType );
	}
	
	/**
	 * 重写get方法，默认返回管理平台
	 */
	@Override
	public WorkCalendar get(Serializable identity) {
		return this.workCalendarDao.findWorkDayByUnionPk( identity.toString(),WorkCalendarSystemType.MP );
	}
	
	@Override
	public ListPage<WorkCalendar> list(ListPage<WorkCalendar> page, Collection<Term> terms) {
		terms.add(buildTermDefaultSystem());
		return super.list(page, terms);
	}

	@Override
	public ListPage<WorkCalendar> listNew(ListPage<WorkCalendar> page, Collection<Term> terms) {
		return super.list(page, terms);
	}

}