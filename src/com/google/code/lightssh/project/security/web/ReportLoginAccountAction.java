package com.google.code.lightssh.project.security.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.report.jr.DynamicColumn;
import com.google.code.lightssh.common.report.jr.ReportParameter;
import com.google.code.lightssh.common.web.action.NonTemplateReportAction;
import com.google.code.lightssh.project.security.entity.LoginAccount;
import com.google.code.lightssh.project.security.service.LoginAccountManager;

@Component( "reportLoginAccountAction" )
@Scope("prototype")
public class ReportLoginAccountAction extends NonTemplateReportAction<LoginAccount>{
	
	private static final long serialVersionUID = 3862513833381074305L;
	
	private LoginAccountManager manager;
	
	private LoginAccount account;
	
	private ListPage<LoginAccount> page;
	
	@Resource( name="loginAccountManager" )
	public void setManager(LoginAccountManager manager) {
		this.manager = manager;
	}

	public LoginAccount getAccount() {
		return account;
	}

	public void setAccount(LoginAccount account) {
		this.account = account;
	}

	public ListPage<LoginAccount> getPage() {
		return page;
	}

	public void setPage(ListPage<LoginAccount> page) {
		this.page = page;
	}

	static List<DynamicColumn> REPORT_COLUMNS=new ArrayList<DynamicColumn>();
	static{
		REPORT_COLUMNS.add(new DynamicColumn("loginName","登录名",String.class,200,20));
		REPORT_COLUMNS.add(new DynamicColumn("party.name","会员",String.class,100,20));
		REPORT_COLUMNS.add(new DynamicColumn("createDate","创建时间",Date.class,100,20));
		REPORT_COLUMNS.add(new DynamicColumn("period.start","有效期(起)",Date.class,100,20));
		REPORT_COLUMNS.add(new DynamicColumn("period.end","有效期(至)",Date.class,100,20));
	}

	@Override
	public ReportParameter buildReportParameter() {
		ReportParameter reportParm = new ReportParameter();
		reportParm.setTitle("用户登录账户");
		reportParm.setDynamicColumns(REPORT_COLUMNS);
		return reportParm;
	}

	@Override
	public String getTemplateFileName() {
		return ReportLoginAccountAction.class.getCanonicalName();
	}

	@Override
	public List<LoginAccount> getDataSource() {
		if( page == null )
			page = new ListPage<LoginAccount>( );
		
		page.setSize( Integer.MAX_VALUE );
		page = manager.list(page,account);
		return page.getList();
	}

}
