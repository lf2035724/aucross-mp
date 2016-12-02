package com.ylink.ylpay.project.mp.cust.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.ApplicationException;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.common.project.mp.constant.CertificationType;
import com.ylink.ylpay.common.project.mp.constant.RecoveryAllowType;
import com.ylink.ylpay.project.mp.cust.dao.CustCertDao;
import com.ylink.ylpay.project.mp.cust.entity.CustCert;

/**
 * 客户证件业务层.
 * 
 * @author 潘瑞峥
 * @date 2013-1-16
 */
@Component( "custCertManager" )
public class CustCertManagerImpl extends BaseManagerImpl<CustCert> implements CustCertManager {
	
	private static final long serialVersionUID = -2093025109770815783L;

	private CustCertDao custCertDao;

	@Autowired
	public void setCustCertDao( CustCertDao custCertDao ) {
		this.custCertDao = custCertDao;
		super.dao = this.custCertDao;
	}

	/**
	 * 保存.
	 */
	@Override
	public void save( CustCert entity ) {
		// 校验.
		this.validateSave( entity );

		this.custCertDao.create( entity );
	}

	/**
	 * 保存实体，返回实体ID;
	 */
	@Override
	public Serializable saveEntity( CustCert custCert ) {

		this.validateSave( custCert );
		if(custCert.getCreatedTime() == null){
			custCert.setCreatedTime(Calendar.getInstance());
		}
		return this.custCertDao.createEntity( custCert );
	}

	/**
	 * 保存并返回.
	 */
	@Override
	public CustCert save( CertificationType certType, byte[] certFile, RecoveryAllowType certFileType ) {
		Validate.notNull( certType, "证件类型不能为空." );
		Validate.notNull( certFile, "证件文件不能为空." );

		CustCert entity = new CustCert();
		entity.setCertType( certType );
		entity.setCertFile( certFile );
		entity.setCertFileType( certFileType );

		this.save( entity );

		return entity;
	}
	
	/**
	 * 保存前校验.
	 * 
	 * @param entity
	 */
	private void validateSave( CustCert entity ) {
		Validate.notNull( entity, "entity不能为空." );

		Validate.notNull( entity.getCertType(), "证件类型不能为空." );
		Validate.notNull( entity.getCertFile(), "证件文件不能为空." );
	}
	
	@Override
	public void update(String certCustId, byte[] certFile) {
		if(StringUtils.isEmpty(certCustId)){
			throw new ApplicationException("证件编号不能为空");
		}
		Validate.notNull(certFile,"证件文件不能为空");
		
		CustCert entity = get( certCustId );
		if( entity == null )
			throw new ApplicationException("证件["+certCustId+"]不存在");
		
		entity.setCertFile(certFile);
		this.update(entity);
	}
}