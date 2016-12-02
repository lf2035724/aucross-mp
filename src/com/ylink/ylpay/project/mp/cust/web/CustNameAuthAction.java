package com.ylink.ylpay.project.mp.cust.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.Validate;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.model.page.ListPage;
import com.ylink.modules.orm.PropertyFilterBuilder;
import com.ylink.modules.web.struts2.CustomAction;
import com.ylink.ylpay.common.project.mp.constant.AuditStatus;
import com.ylink.ylpay.project.mp.cust.entity.CustNameAuth;
import com.ylink.ylpay.project.mp.cust.service.CustNameAuthManager;

/**
 * 个人客户实名认证控制层.
 * 
 * @author 潘瑞峥
 * @date 2013-1-16
 */
@Component
@Scope( "prototype" )
public class CustNameAuthAction extends CustomAction<CustNameAuth> {

	private static final long serialVersionUID = -5505985971216584910L;

	private CustNameAuthManager custNameAuthManager;

	private CustNameAuth vo;

	@Autowired
	public void setCustNameAuthManager( CustNameAuthManager custNameAuthManager ) {
		this.custNameAuthManager = custNameAuthManager;
		super.manager = this.custNameAuthManager;
	}

	public CustNameAuth getVo() {
		vo = super.model;
		return vo;
	}

	public void setVo( CustNameAuth vo ) {
		super.model = vo;
		this.vo = vo;
	}

	/**
	 * 查询.
	 */
	@Override
	public String list() {
		return this.list( null );
	}

	/**
	 * 查询待初审记录.
	 * 
	 * @return
	 */
	public String listFirstAudit() {
		logger.debug( "查询待初审记录..." );
		return this.list( AuditStatus.WAIT_AUDIT );
	}

	/**
	 * 初审通过.
	 * 
	 * @return
	 */
	public String modifyFirstAuditPass() {
		logger.debug( "初审通过..." );
		return this.modify( AuditStatus.WAIT_AUDIT, AuditStatus.AUDIT_FIRST_PASS );
	}

	/**
	 * 初审拒绝.
	 * 
	 * @return
	 */
	public String modifyFirstAuditRefuse() {
		logger.debug( "初审拒绝..." );
		return this.modify( AuditStatus.WAIT_AUDIT, AuditStatus.AUDIT_FIRST_REFUSE );
	}

	/**
	 * 查询待复审记录.
	 * 
	 * @return
	 */
	public String listReAudit() {
		logger.debug( "查询待复审记录..." );
		return this.list( AuditStatus.AUDIT_FIRST_PASS );
	}

	/**
	 * 复审通过.
	 * 
	 * @return
	 */
	public String modifyReAuditPass() {
		logger.debug( "复审通过..." );
		return this.modify( AuditStatus.AUDIT_FIRST_PASS, AuditStatus.AUDIT_PASS );
	}

	/**
	 * 复审拒绝.
	 * 
	 * @return
	 */
	public String modifyReAuditRefuse() {
		logger.debug( "复审拒绝..." );
		return this.modify( AuditStatus.AUDIT_FIRST_PASS, AuditStatus.AUDIT_REFUSE );
	}

	/**
	 * 查询.
	 * 
	 * @param status
	 * @return
	 */
	private String list( AuditStatus status ) {
		if ( null == super.getPage() ) {
			super.setPage( new ListPage<CustNameAuth>() );
		}

		/* 自动根据request参数转换为com.google.code.lightssh.common.dao.Term对象. */
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Term> termList = PropertyFilterBuilder.buildTermFromHttpRequest( request );

		/* 审核状态. */
		if ( null != status ) {
			termList.add( new Term( Term.Type.EQUAL, "auditStatus", status ) );
		}

		super.setPage( super.manager.list( super.getPage(), termList ) );
		request.setAttribute( "list", super.getPage() );

		return SUCCESS;
	}

	/**
	 * 修改.
	 * 
	 * @param id
	 * @param formStatus
	 * @param toStatus
	 * @return
	 */
	private String modify( AuditStatus formStatus, AuditStatus toStatus ) {
		Validate.notNull( this.getVo(), "vo不能为空." );

		String id = this.getVo().getIdentity();
		Validate.notBlank( id, "id不能为空." );
		Validate.notNull( formStatus, "formStatus不能为空." );
		Validate.notNull( toStatus, "toStatus不能为空." );

		try {
			this.custNameAuthManager.modify( id, formStatus, toStatus );
			super.saveSuccessMessage( "操作成功." );
		} catch ( Exception ex ) {
			super.saveErrorMessage( "操作失败." + ex.getMessage() );
			logger.error( "操作失败." + ex.getMessage() );
			return INPUT;
		}
		return SUCCESS;
	}

}