package com.ylink.ylpay.project.mp.user.app;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.ylink.ylpay.common.project.mp.app.PotentialUserAppService;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.constants.PatternConstant;
import com.ylink.ylpay.project.mp.user.entity.PotentialUser;
import com.ylink.ylpay.project.mp.user.entity.PotentialUser.Status;
import com.ylink.ylpay.project.mp.user.service.PotentialUserManager;

/**
 * 潜在客户接口.
 * 
 * @author 潘瑞峥
 * @date 2012-10-29
 */
@Component( "potentialUserAppService" )
public class PotentialUserAppServiceImpl implements PotentialUserAppService {

	private static Logger logger = LoggerFactory.getLogger( PotentialUserAppServiceImpl.class );

	@Resource( name = "potentialUserManager" )
	private PotentialUserManager potentialUserManager;

	/**
	 * 保存潜在客户信息.
	 * 
	 * <pre>
	 * 接口: <b>2.1.6</b>
	 * 规则: ALL NOT NULL.
	 * </pre>
	 * 
	 * @param id
	 * @param email
	 * @param mobile
	 * @throws MpCheckedException
	 */
	public void savePotentialUser( String id, String email, String mobile ) throws MpCheckedException {
		logger.debug( "保存潜在客户信息." );

		Assert.hasText( id );
		Validate.notNull( email );
		Validate.notNull( mobile );
		Validate.matchesPattern( email, PatternConstant.EMAIL_PATTERN, PatternConstant.EMAIL_ERROR_MSG );
		Validate.matchesPattern( mobile, PatternConstant.MOBILE_PATTERN, PatternConstant.MOBILE_ERROR_MSG );

		PotentialUser puEntity = this.potentialUserManager.get( id );
		Assert.isNull( puEntity, "该id[" + id + "]已经存在." );

		PotentialUser entity = new PotentialUser();
		entity.setId( id );
		entity.setEmail( email );
		entity.setMobile( mobile );
		entity.setStatus( Status.NEW );
		entity.setCreatedTime( DateUtils.toCalendar( new Date() ) );

		this.potentialUserManager.save( entity );
	}

	/**
	 * 查询潜在客户信息.
	 * 
	 * <pre>
	 * 接口: <b>2.1.7</b>
	 * 规则: 1.NOT NULL.
	 * </pre>
	 * 
	 * @author 潘瑞峥
	 * @date 2012-10-29
	 * @param id
	 * @return
	 */
	@Override
	public com.ylink.ylpay.common.project.mp.dto.PotentialUser getPotentialUser( String id ) throws MpCheckedException {
		logger.debug( "查询潜在客户信息." );

		Assert.hasText( id );

		PotentialUser entity = this.potentialUserManager.get( id );
		com.ylink.ylpay.common.project.mp.dto.PotentialUser dtoBean = null;
		if ( null != entity ) {
			dtoBean = new com.ylink.ylpay.common.project.mp.dto.PotentialUser();
			BeanUtils.copyProperties( entity, dtoBean, new String[] { "status" } );
			dtoBean.setStatus( entity.getStatus().name() );
		}
		return dtoBean;
	}

	/**
	 * 激活潜在客户.
	 * 
	 * <pre>
	 * 接口: <b>2.1.7</b>
	 * 规则: 1.NOT NULL.
	 * </pre>
	 * 
	 * @author 潘瑞峥
	 * @date 2012-10-29
	 * @param id
	 */
	@Override
	public void activePotentialUser( String id ) throws MpCheckedException {
		logger.debug( "激活潜在用户." );

		Assert.hasText( id );

		try {
			this.potentialUserManager.activeById( id );
		} catch ( Exception ex ) {
			ex.printStackTrace();
			throw new MpCheckedException( ex.getMessage() );
		}
	}

}