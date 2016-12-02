package com.ylink.ylpay.project.mp.cust.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.dao.Term.Type;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.google.common.collect.Lists;
import com.ylink.ylpay.project.constants.bean.CertificateBean;
import com.ylink.ylpay.project.mp.cust.dao.CustMerchantCertDao;
import com.ylink.ylpay.project.mp.cust.entity.CustMerchant;
import com.ylink.ylpay.project.mp.cust.entity.CustMerchantCert;
import com.ylink.ylpay.project.utils.CertificateUtils;

/**
 * 商户证件业务层.
 * 
 * @author 潘瑞峥
 * @date 2013-4-20
 */
@Component( "custMerchantCertManager" )
public class CustMerchantCertManagerImpl extends BaseManagerImpl<CustMerchantCert> implements CustMerchantCertManager {

	private static final long serialVersionUID = 7571221362182940796L;

	private CustMerchantCertDao custMerchantCertDao;

	@Autowired
	public void setCustMerchantCertDao( CustMerchantCertDao custMerchantCertDao ) {
		this.custMerchantCertDao = custMerchantCertDao;
		super.dao = this.custMerchantCertDao;
	}

	/**
	 * 根据商户查询证书.
	 */
	@Override
	public CustMerchantCert find( CustMerchant custMerchant ) {
		Validate.notNull( custMerchant, "企业商户不能为空." );

		List<Term> termList = Lists.newArrayList( new Term( Type.EQUAL, "custMerchant", custMerchant ) );
		CustMerchantCert entity = this.custMerchantCertDao.findUnique( termList );
		return entity;
	}

	/**
	 * 根据商户保存证书.
	 */
	@Override
	public void save( CustMerchant custMerchant ) throws Exception {
		Validate.notNull( custMerchant, "企业商户不能为空." );

		CertificateBean certBean = new CertificateBean( custMerchant.getName() );
		// 私钥JKS.
		byte[] privateKeyBytes = CertificateUtils.toPrivateKeyBytes( certBean );
		// 公钥CER.
		byte[] publicKeyBytes = CertificateUtils.toPublicKeyBytes( privateKeyBytes );

		CustMerchantCert entity = new CustMerchantCert();
		BeanUtils.copyProperties( certBean, entity );
		entity.setCustMerchant( custMerchant );
		// 私钥.
		entity.setPrivateKeyFile( privateKeyBytes );
		// 公钥.
		entity.setPublicKeyFile( publicKeyBytes );

		// 保存证书.
		this.custMerchantCertDao.create( entity );
	}

	/**
	 * 根据商户修改证书.
	 */
	@Override
	public void modify( CustMerchant custMerchant ) throws Exception {
		Validate.notNull( custMerchant, "企业商户不能为空." );

		CertificateBean certBean = new CertificateBean( custMerchant.getName() );
		// 私钥JKS.
		byte[] privateKeyBytes = CertificateUtils.toPrivateKeyBytes( certBean );
		// 公钥CER.
		byte[] publicKeyBytes = CertificateUtils.toPublicKeyBytes( privateKeyBytes );

		CustMerchantCert entity = this.find( custMerchant );
		if ( null == entity ) {
			entity = new CustMerchantCert();
			BeanUtils.copyProperties( certBean, entity );
			entity.setCustMerchant( custMerchant );
		}
		BeanUtils.copyProperties( certBean, entity );
		// 私钥.
		entity.setPrivateKeyFile( privateKeyBytes );
		// 公钥.
		entity.setPublicKeyFile( publicKeyBytes );
		// 更新时间.
		entity.setUpdatedTime( new Date() );

		this.custMerchantCertDao.update( entity );
	}

}