package com.ylink.ylpay.project.mp.cust.app;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ylink.ylpay.common.project.mp.app.CustCertAppService;
import com.ylink.ylpay.common.project.mp.constant.CertificationType;
import com.ylink.ylpay.common.project.mp.constant.RecoveryAllowType;
import com.ylink.ylpay.common.project.mp.dto.CustCertBean;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.mp.cust.entity.CustCert;
import com.ylink.ylpay.project.mp.cust.service.CustCertManager;

/**
 * 客户证件接口.
 * 
 * @author 潘瑞峥
 * @date 2013-3-26
 */
@Component( "custCertAppService" )
public class CustCertAppServiceImpl implements CustCertAppService {

	@Autowired
	private CustCertManager custCertManager;

	/**
	 * 根据图片id查询图片.
	 */
	@Override
	public CustCertBean findById( String id ) throws MpCheckedException {
		Validate.notBlank( id, "id不能为空." );

		CustCertBean bean = null;
		CustCert entity = this.custCertManager.get( id );
		if ( null != entity ) {
			bean = new CustCertBean();
			BeanUtils.copyProperties( entity, bean );
		}
		return bean;
	}

	/**
	 * 上传.
	 */
	@Override
	public CustCertBean upload( CertificationType certType, byte[] certFile, RecoveryAllowType certFileType ) throws MpCheckedException {
		CustCert entity = this.custCertManager.save( certType, certFile, certFileType );

		CustCertBean bean = new CustCertBean();
		BeanUtils.copyProperties( entity, bean, new String[] { "certFile" } );

		return bean;
	}

	@Override
	public void updateCertType( String certCustId, byte[] certFile ) throws MpCheckedException {
		this.custCertManager.update( certCustId, certFile );
	}
	

}