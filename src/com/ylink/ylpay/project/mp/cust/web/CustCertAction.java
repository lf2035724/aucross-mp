package com.ylink.ylpay.project.mp.cust.web;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ylink.modules.web.struts2.CustomAction;
import com.ylink.ylpay.project.mp.cust.entity.CustCert;
import com.ylink.ylpay.project.mp.cust.service.CustCertManager;

/**
 * 客户证件控制层.
 * 
 * @author 潘瑞峥
 * @date 2013-1-16
 */
@Component
@Scope( "prototype" )
public class CustCertAction extends CustomAction<CustCert> {

	private static final long serialVersionUID = -2705812689445252633L;

	private CustCertManager custCertManager;

	private CustCert vo;

	@Autowired
	public void setCustCertManager( CustCertManager custCertManager ) {
		this.custCertManager = custCertManager;
		super.manager = this.custCertManager;
	}

	public CustCert getVo() {
		vo = super.model;
		return vo;
	}

	public void setVo( CustCert vo ) {
		super.model = vo;
		this.vo = vo;
	}

	/**
	 * 显示图片.
	 */
	public String imageDetail() {
		logger.debug( "显示图片..." );

		//Validate.notNull( this.getVo(), "vo不能为空." );

		if( this.getVo() == null )
			return null;
		
		Long id = this.getVo().getId();
		//Validate.notBlank( id, "id不能为空." );

		CustCert entity = this.custCertManager.get( id );
		//Validate.notNull( entity, "未根据id查询到记录." );
		if( entity == null )
			return null;

		byte[] images = entity.getCertFile();
		//Validate.notNull( images, "图片信息为空." );
		if( images == null )
			return null;

		OutputStream out = null;
		try {
			out = ServletActionContext.getResponse().getOutputStream();
			IOUtils.write( images, out );
		} catch ( IOException e ) {
			logger.error( "异常:", e );
		} finally {
			IOUtils.closeQuietly( out );
		}

		return null;
	}

}