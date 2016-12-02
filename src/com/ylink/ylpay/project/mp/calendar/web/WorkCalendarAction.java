package com.ylink.ylpay.project.mp.calendar.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.dao.Term.Type;
import com.google.code.lightssh.common.model.page.ListPage;
import com.ylink.modules.orm.PropertyFilterBuilder;
import com.ylink.modules.web.struts2.CustomAction;
import com.ylink.ylpay.common.project.mp.constant.WorkCalendarSystemType;
import com.ylink.ylpay.common.project.mp.constant.WorkDayType;
import com.ylink.ylpay.project.mp.calendar.entity.WorkCalendar;
import com.ylink.ylpay.project.mp.calendar.service.WorkCalendarManager;

/**
 * 工作日志控制层.
 * 
 * @author 潘瑞峥
 * @date 2012-10-31
 */
@Component( "workCalendarAction" )
@Scope( "prototype" )
public class WorkCalendarAction extends CustomAction<WorkCalendar> {

	private static final long serialVersionUID = -2276446811878968294L;

	private WorkCalendarManager workCalendarManager;

	private WorkCalendar vo;
	
	private List<WorkCalendar> vos = new ArrayList<WorkCalendar>();
	
	//保存查询条件，修改完之后恢复之前的页面
	private String systemSearch;
	private String workDayTypeSearch;
	private String identitySearch; 
	private String pageNumber;

	@Autowired
	public void setWorkCalendarManager( WorkCalendarManager workCalendarManager ) {
		this.workCalendarManager = workCalendarManager;
		super.manager = this.workCalendarManager;
	}

	public WorkCalendar getVo() {
		vo = super.model;
		return vo;
	}

	public void setVo( WorkCalendar vo ) {
		super.model = vo;
		this.vo = vo;
	}

	public List<WorkCalendar> getVos() {
		return vos;
	}

	public void setVos( List<WorkCalendar> vos ) {
		this.vos = vos;
	}

	public String getSystemSearch() {
		return systemSearch;
	}

	public void setSystemSearch(String systemSearch) {
		this.systemSearch = systemSearch;
	}

	public String getWorkDayTypeSearch() {
		return workDayTypeSearch;
	}

	public void setWorkDayTypeSearch(String workDayTypeSearch) {
		this.workDayTypeSearch = workDayTypeSearch;
	}

	public String getIdentitySearch() {
		return identitySearch;
	}

	public void setIdentitySearch(String identitySearch) {
		this.identitySearch = identitySearch;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * 列表
	 */
	@Override
	public String list() {
		if ( null == super.getPage() ) {
			super.setPage( new ListPage<WorkCalendar>() );
		}
		/* 自动根据request参数转换为com.google.code.lightssh.common.dao.Term对象. */
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Term> termList = PropertyFilterBuilder.buildTermFromHttpRequest( request );

		/* 是否工作日. */
		String type = request.getParameter( "workDayType" );
		if ( StringUtils.isNotBlank( type ) ) {
			WorkDayType workDayType = Enum.valueOf( WorkDayType.class, type );
			termList.add( new Term( Type.EQUAL, "workDayType", workDayType ) );
		}
		/* 所属系统. */
		String system = request.getParameter( "system" );
		if ( StringUtils.isNotBlank( system ) ) {
			WorkCalendarSystemType systemType = Enum.valueOf( WorkCalendarSystemType.class, system );
			termList.add( new Term( Type.EQUAL, "workSystem", systemType ) );
		}
		super.setPage( this.workCalendarManager.listNew( super.getPage(), termList ) );
		request.setAttribute( "list", super.getPage() );

		return SUCCESS;
	}

	/**
	 * 初始化工作日历的页面.
	 */
	public String gotoInit() {
		return SUCCESS;
	}

	/**
	 * 初始化工作日历.
	 */
	public String init() {
		String year = request.getParameter( "year" );
		String system = request.getParameter( "system" );
		this.workCalendarManager.init( year,system );
		return SUCCESS;
	}

	/**
	 * 修改的明细.
	 */
	public String editDetail() {
		Validate.notNull( this.getModel() );
		Validate.notNull( this.getModel().getIdentity() );

		super.model = this.workCalendarManager.get(model.getIdentity(),model.getWorkSystemTypeName());
		systemSearch = request.getParameter("systemSearch");
		workDayTypeSearch = request.getParameter("workDayTypeSearch");
		identitySearch = request.getParameter("identitySearch");
		pageNumber = request.getParameter("pageNumber");
		return SUCCESS;
	}

	/**
	 * 修改.
	 */
	@Override
	public String edit() {
		String id = super.getModel().getIdentity();
		/* 是否工作日. */
		String type = request.getParameter( "vo.workDayTypeName" );
		/* 所属系统. */
		String system = request.getParameter( "vo.workSystemTypeName" );
		if ( StringUtils.isNotBlank( type ) && StringUtils.isNotBlank( system ) ) {
			WorkDayType workDayType = Enum.valueOf( WorkDayType.class, type );
			WorkCalendarSystemType systemType = Enum.valueOf( WorkCalendarSystemType.class, system );
			this.workCalendarManager.edit( id, workDayType ,systemType );
		}
		return SUCCESS;
	}

	/**
	 * 跳转到根据年份修改批量页面.
	 * 
	 * @return
	 */
	public String gotoModify() {
		return SUCCESS;
	}

	/**
	 * 根据年份修改批量.
	 * 
	 * @return
	 */
	public String modifyDetail() {
		String year = request.getParameter( "year" );
		String system = request.getParameter( "system" );

		List<Term> termList = new ArrayList<Term>();
		termList.add( new Term( Type.LIKE_RIGHT, "identity", year ) );
		if ( StringUtils.isNotBlank( system ) ) {
			WorkCalendarSystemType workCalendarSystemType = Enum.valueOf( WorkCalendarSystemType.class, system );
			termList.add( new Term( Type.EQUAL, "workSystem", workCalendarSystemType ) );
		}

		List<WorkCalendar> list = this.workCalendarManager.listNew( termList );
		if ( CollectionUtils.isEmpty( list ) ) {
			addActionError( "未找到对应的年份和系统，请先初始化后再修改！" );
			return INPUT;
		} else {
			this.setVos( list );
			return SUCCESS;
		}
	}

	/**
	 * AJAX修改是否工作日状态.
	 * 
	 * @return
	 */
	public String modify() {
		String id = super.getModel().getIdentity();
		/* 是否工作日. */
		String type = request.getParameter( "vo.workDayTypeName" );
		/* 所属系统. */
		String workSystem = request.getParameter( "vo.workSystemTypeName" );
		if ( StringUtils.isNotBlank( type ) &&  StringUtils.isNotBlank( workSystem ) ) {
			WorkDayType workDayType = WorkDayType.returnEnum( Integer.valueOf( type ) );
			WorkCalendarSystemType systemType = Enum.valueOf( WorkCalendarSystemType.class, workSystem );
			this.workCalendarManager.edit( id, workDayType, systemType );
		}
		return null;
	}

}