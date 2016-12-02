package com.ylink.ylpay.project.mp.user.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.dao.Term.Type;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.project.constants.SystemParamConstant;
import com.ylink.ylpay.project.mp.user.dao.UserLoginFailureDao;
import com.ylink.ylpay.project.mp.user.entity.User;
import com.ylink.ylpay.project.mp.user.entity.UserLoginFailure;

/**
 * 登录失败记录业务层.
 * 
 * @author 潘瑞峥
 * @date 2012-10-27
 */
@Component( "userLoginFailureManager" )
public class UserLoginFailureManagerImpl extends BaseManagerImpl<UserLoginFailure> implements UserLoginFailureManager {

	private static final long serialVersionUID = -4685700086104138083L;

	private static Logger logger = LoggerFactory.getLogger( UserLoginFailureManagerImpl.class );

	private UserLoginFailureDao userLoginFailureDao;

	@Autowired
	private UserManager userManager;

//	@Autowired
//	private SystemParamManager systemParamManager;

	@Autowired
	public void setUserLoginFailureDao( UserLoginFailureDao userLoginFailureDao ) {
		this.userLoginFailureDao = userLoginFailureDao;
		super.dao = userLoginFailureDao;
	}

	/**
	 * 登录失败日志.
	 * 
	 * <pre>
	 * 规则: 若sessionId相同，则当作已存在，update；则反之create.
	 * </pre>
	 */
	@Override
	public void loginFailure( Long userId, String loginName, String ip, String sessionId ) {
		Validate.notNull( userId );
		Validate.notBlank( loginName );
		Validate.notNull( ip );
		Validate.notBlank( sessionId );

		/* 主要根据sessionId */
		List<Term> termList = new ArrayList<Term>();
		termList.add( new Term( Type.EQUAL, "sessionId", sessionId ) );
		//termList.add( new Term( Type.EQUAL, "loginName", loginName ) );
		UserLoginFailure entity = this.userLoginFailureDao.findUnique( termList );
		// 不存在
		if ( null == entity ) {
			User userEntity = this.userManager.get( userId );
			entity = new UserLoginFailure();
			entity.setAccount( userEntity );
			entity.setLoginName( loginName );
			entity.setIp( ip );
			entity.setSessionId( sessionId );	
			entity.setFailureCount( 1 );
			entity.setCreatedTime( Calendar.getInstance() );
			this.userLoginFailureDao.create( entity );
		}else {
			// 存在
			entity.setFailureCount( entity.getFailureCount() + 1 );
			this.userLoginFailureDao.update( entity );
		}
		
		// 锁定用户.
		this.lock( entity );

	}

	/**
	 * 锁定用户.
	 * 
	 * @param entity
	 */
	private void lock( UserLoginFailure entity ) {
		// 时间范围(分).
		int loginFailureLockTime = SystemParamConstant.DEFAULT_LOGIN_FAILURE_LOCK_TIME;
		// 允许的失败次数.
		long loginFailureLockCount = SystemParamConstant.DEFAULT_LOGIN_FAILURE_LOCK_COUNT;

//		SystemParam lockTime = this.systemParamManager.getByName( SystemParamConstant.SYSTEM_PARAM_KEY__LOGIN_FAILURE_LOCK_TIME );
//		SystemParam lockCount = this.systemParamManager.getByName( SystemParamConstant.SYSTEM_PARAM_KEY__LOGIN_FAILURE_LOCK_COUNT );
//		if ( null == lockTime ) {
//			logger.warn( "未找到系统参数[{}].", SystemParamConstant.SYSTEM_PARAM_KEY__LOGIN_FAILURE_LOCK_TIME );
//		} else {
//			loginFailureLockTime = NumberUtils.toInt( lockTime.getValue(), loginFailureLockTime );
//			logger.info(" NumberUtils.toInt()---"+loginFailureLockTime);
//		}
//		if ( null == lockCount ) {
//			logger.warn( "未找到系统参数[{}].", SystemParamConstant.SYSTEM_PARAM_KEY__LOGIN_FAILURE_LOCK_COUNT );
//		} else {
//			loginFailureLockCount = NumberUtils.toLong( lockCount.getValue(), loginFailureLockCount );
//			logger.info(" NumberUtils.toLong()---"+loginFailureLockCount);
//		}
//		

		// 当前时间.
		Calendar nowCalendar = Calendar.getInstance();

		/* 之前的时间. */
		LocalDateTime localDate = LocalDateTime.fromCalendarFields( nowCalendar );
		localDate = localDate.minusMinutes( loginFailureLockTime );
		Calendar beforeCalendar = DateUtils.toCalendar( localDate.toDate() );

		logger.info("termList:account="+entity.getAccount()+"----nowCalendar="+nowCalendar+"------beforeCalendar="+beforeCalendar);
		logger.info("termList:accountId="+entity.getAccount().getIdentity());
		logger.info("----nowCalendar="+nowCalendar.getTime()+"------beforeCalendar="+beforeCalendar.getTime());
		
		List<Term> termList = new ArrayList<Term>();

		termList.add(new Term(Type.EQUAL,"account",entity.getAccount()));
		// 小于当前时间.
		termList.add( new Term( Type.LESS_THAN_EQUAL, "createdTime", nowCalendar ) );
		// 大于之前时间.
		termList.add( new Term( Type.GREATE_THAN_EQUAL, "createdTime", beforeCalendar ) );
		ListPage<UserLoginFailure> page = new ListPage<UserLoginFailure>( Integer.MAX_VALUE );
		
		
		// sum失败次数.
		page.addAggregateSum( "failureCount" );	
		page = this.userLoginFailureDao.list( page, termList );
	
		if(page == null || CollectionUtils.isEmpty(page.getList())){
			logger.info("登录失败次数查询数据为空...................................................!");
			return;
		}
		// 当前失败次数.
		long currentFailureCount = ( Long ) page.getSumValue( "failureCount" );

		/* 当前失败次数大于等于允许的失败次数时, 需要锁定. */
		if ( currentFailureCount >= loginFailureLockCount ) {
			User user = entity.getAccount();
			user.setLastLoginLockTime( nowCalendar );

			this.userManager.update( user );
		}
	}

}