package com.ylink.ylpay.project.mp.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.project.mp.user.dao.PotentialUserDao;
import com.ylink.ylpay.project.mp.user.entity.PotentialUser;

/**
 * 潜在客户信息业务层
 * 
 * @author 潘瑞峥
 * @date 2012-10-27
 */
@Component( "potentialUserManager" )
public class PotentialUserManagerImpl extends BaseManagerImpl<PotentialUser> implements PotentialUserManager {

	private static final long serialVersionUID = 7485249261604837024L;

	private PotentialUserDao potentialUserDao;

	@Autowired
	public void setPotentialUserDao( PotentialUserDao potentialUserDao ) {
		this.potentialUserDao = potentialUserDao;
		super.dao = potentialUserDao;
	}

	/**
	 * 根据ID激活用户.
	 * 
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void activeById( String id ) throws Exception {
		PotentialUser entity = this.potentialUserDao.read( id );
		if ( null != entity ) {
			entity.setStatus( PotentialUser.Status.ACTIVE );
			this.potentialUserDao.update( entity );
		} else {
			throw new Exception( "未根据用户id查询到安保问题." );
		}
	}

}