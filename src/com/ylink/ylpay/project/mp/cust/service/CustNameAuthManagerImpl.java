package com.ylink.ylpay.project.mp.cust.service;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.ApplicationException;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.common.project.mp.constant.AuditStatus;
import com.ylink.ylpay.common.project.mp.constant.AuthenticationStatus;
import com.ylink.ylpay.common.project.mp.constant.CertificationType;
import com.ylink.ylpay.common.project.mp.dto.CustCertBean;
import com.ylink.ylpay.common.project.mp.dto.CustNameAuthBean;
import com.ylink.ylpay.project.mp.cust.dao.CustNameAuthDao;
import com.ylink.ylpay.project.mp.cust.entity.CustCert;
import com.ylink.ylpay.project.mp.cust.entity.CustNameAuth;
import com.ylink.ylpay.project.mp.cust.entity.Personal;

/**
 * 个人客户实名认证业务层.
 * 
 * @author 潘瑞峥
 * @date 2013-1-16
 */
@Component( "custNameAuthManager" )
public class CustNameAuthManagerImpl extends BaseManagerImpl<CustNameAuth> implements CustNameAuthManager {

	private static final long serialVersionUID = -6602489486344164530L;

	private static Logger logger = LoggerFactory.getLogger( CustNameAuthManagerImpl.class );

	private CustNameAuthDao custNameAuthDao;

	@Autowired
	private CustCertManager custCertManager;

	@Autowired
	private PersonalManager personalManager;

	@Autowired
	public void setCustNameAuthDao( CustNameAuthDao custNameAuthDao ) {
		this.custNameAuthDao = custNameAuthDao;
		super.dao = this.custNameAuthDao;
	}

	/**
	 * 保存.
	 */
	@Override
	public void save( CustNameAuth entity ) {
		// 校验.
		this.validateSave( entity );

		/* 个人客户. */
		String custId = entity.getCust().getIdentity();
		Personal personal = this.personalManager.get( custId );
		if ( null != personal ) {
			entity.setCust( personal );
		} else {
			throw new ApplicationException( "未根据id[" + custId + "]查询到个人客户信息." );
		}

		// 认证状态.
		String authStatus = personal.getCertStatus();
		/* 未认证或认证失败. */
		if ( AuthenticationStatus.NEW.getValue().equals( authStatus ) || AuthenticationStatus.NOT_PASSED.getValue().equals( authStatus ) ) {
			/* 修改认证状态: 认证中. */
			personal.setCertStatus( AuthenticationStatus.AUTHING.getValue() );
			this.personalManager.update( personal );
		} else {
			AuthenticationStatus _authStatus = AuthenticationStatus.parseOf( authStatus );
			String message = "该记录认证状态[" + _authStatus.getValue() + "." + _authStatus.getDisplayName() + "]不可发起认证操作.";
			logger.error( message );
			throw new ApplicationException( message );
		}

		/* 客户证件. */
		CustCert custCert = entity.getCert();
		custCert.setCertType( CertificationType.parseOf( personal.getCredentialsType() ) );
		this.custCertManager.save( custCert );

		// 审核状态: 待复核.
		entity.setAuditStatus( AuditStatus.WAIT_AUDIT );

		this.custNameAuthDao.create( entity );
	}

	/**
	 * 保存前校验.
	 * 
	 * @param entity
	 */
	private void validateSave( CustNameAuth entity ) {
		Validate.notNull( entity, "entity不能为空." );

		Validate.notNull( entity.getCert(), "cert不能为空." );

		/* 个人客户. */
		Personal personal = entity.getCust();
		Validate.notNull( personal, "cust不能为空." );
		Validate.notBlank( personal.getIdentity(), "custId不能为空." );
	}

	/**
	 * 通过dto保存.
	 */
	@Override
	public void save( CustNameAuthBean dtoBean ) {
		Validate.notNull( dtoBean, "dtoBean不能为空." );

		CustCertBean certDtoBean = dtoBean.getCustCertBean();
		Validate.notNull( certDtoBean, "certDtoBean不能为空." );

		CustNameAuth entity = new CustNameAuth();

		/* 个人客户. */
		Personal personal = new Personal();
		personal.setId( dtoBean.getCustId() );
		entity.setCust( personal );

		/* 客户证件. */
		CustCert certEntity = new CustCert();
		// 证件文件.
		certEntity.setCertFile( certDtoBean.getCertFile() );
		// 文件类型.
		certEntity.setCertFileType( certDtoBean.getCertFileType() );
		entity.setCert( certEntity );

		this.save( entity );
	}

	/**
	 * 修改审核状态.
	 */
	@Override
	public void modify( String id, AuditStatus fromStatus, AuditStatus toStatus ) {
		Validate.notBlank( id, "id不能为空." );
		Validate.notNull( fromStatus, "fromStatus不能为空." );
		Validate.notNull( toStatus, "toStatus不能为空." );

		CustNameAuth entity = this.custNameAuthDao.read( id );
		if ( null != entity ) {
			if ( fromStatus.equals( entity.getAuditStatus() ) ) {
				entity.setAuditStatus( toStatus );

				/* 复核通过. */
				if ( AuditStatus.AUDIT_PASS.equals( toStatus ) ) {

					// 修改个人客户认证状态: 已认证.
					this.personalManager.modifyAuthStatus( entity.getCust().getIdentity(), AuthenticationStatus.PASSED );
				}
				/* 初审拒绝或复审拒绝. */
				else if ( AuditStatus.AUDIT_FIRST_REFUSE.equals( toStatus ) || AuditStatus.AUDIT_REFUSE.equals( toStatus ) ) {

					// 修改个人客户认证状态: 认证失败.
					this.personalManager.modifyAuthStatus( entity.getCust().getIdentity(), AuthenticationStatus.NOT_PASSED );
				}

				this.custNameAuthDao.update( entity );
			} else {
				throw new ApplicationException( "该记录状态[" + entity.getAuditStatus() + "]不能发起[" + toStatus + "]操作." );
			}
		} else {
			throw new ApplicationException( "未根据id[" + id + "]查询到信息." );
		}
	}

}