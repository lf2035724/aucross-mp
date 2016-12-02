/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.mp.user.service;

import java.util.List;

import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.service.BaseManager;
import com.google.code.lightssh.project.security.entity.LoginAccount;
import com.ylink.ylpay.common.project.channel.constant.BankType;
import com.ylink.ylpay.common.project.mp.constant.CertificationType;
import com.ylink.ylpay.common.project.mp.dto.Personal;
import com.ylink.ylpay.project.mp.basic.entity.UserVo;
import com.ylink.ylpay.project.mp.user.entity.User;
import com.ylink.ylpay.project.mp.user.entity.UserChange;

/**
 * @author YangXiaojin
 * @date 2012-10-26
 * @description：User业务接口
 */
public interface UserManager extends BaseManager<User> {

	public ListPage<User> list( ListPage<User> page, UserVo t );

	public ListPage<UserChange> listUserChange( ListPage<UserChange> page, UserVo userVo );

	public UserChange getUserChange( String id );

	public void audit( String id, LoginAccount loginUser, boolean rs, String derc );

	/**
	 * @description 通过登录名称查询门户登录帐户
	 * @param email
	 * @return User
	 * @author YangXiaojin
	 * @date 2012-10-26
	 */
	public User getByLoginName( String loginName );

	/**
	 * @description 通过邮箱地址查询门户登录帐户
	 * @param email
	 * @return User
	 * @author YangXiaojin
	 * @date 2012-10-26
	 */
	public User getByEmail( String email );

	/**
	 * @description 通过邮箱地址查询门户登录帐户
	 * @param email
	 * @return User
	 * @author YangXiaojin
	 * @date 2012-10-26
	 */
	public User getByMobile( String mobile );

	/**
	 * 修改密码.
	 * 
	 * @author 潘瑞峥
	 * @date 2012-10-29
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 */
	public void updatePassword( Long userId, String oldPassword, String newPassword ) throws Exception;

	/**
	 * 修改手机号.
	 * 
	 * @author 潘瑞峥
	 * @date 2012-10-30
	 * @param userId
	 * @param oldMobile
	 * @param newMobile
	 */
	public void updateMobile( Long userId, String oldMobile, String newMobile ) throws Exception;

	/**
	 * 禁用、激活用户.
	 * 
	 * @author 潘瑞峥
	 * @date 2012-10-30
	 * @param userId
	 * @param enabled
	 */
	public void activateOrDisable( Long userId, boolean enabled ) throws Exception;

	/**
	 * 根据登录名查询用户信息.<br>
	 * 
	 * <pre>
	 * loginName有三种方式:<br>
	 *     1.手机号;<br>
	 *     2.邮箱;<br>
	 *     3.数据库字段loginName.
	 * </pre>
	 * 
	 * @author 潘瑞峥
	 * @date 2012-10-31
	 * @param loginName
	 * @return
	 */
	public User findByLoginName( String loginName );

	/**
	 * 重写save，加入loginName的编码规则.
	 * 
	 * @author 潘瑞峥
	 * @date 2012-11-1
	 */
	public void save( User entity );

	/**
	 * 找回密码.
	 * 
	 * @author 潘瑞峥
	 * @date 2012-11-1
	 */
	public void recoverPassword( Long userId, String newPassword ) throws Exception;

	/**
	 * 登录失败锁定.
	 * 
	 * @author 潘瑞峥
	 * @date 2012-11-5
	 */
	public void loginLock( Long userId ) throws Exception;

	/**
	 * 通过邮箱或手机注册.
	 * 
	 * @param mobileOrEmailMark
	 * @param mobile
	 * @param email
	 * @param name
	 * @param certificationType
	 * @param certificationNo
	 * @param loginPassword
	 * @param payPassword
	 * @param safeQuestion
	 * @param safeAnswer
	 * @return
	 */
	public User registered( String mobileOrEmailMark, String mobile, String email, String name, CertificationType certificationType,
			String certificationNo, String loginPassword, String payPassword, String safeQuestion, String safeAnswer );

	/**
	 * 修改邮箱.
	 * 
	 * @param userId
	 * @param loginPassword
	 * @param newEmail
	 */
	public void updateEmail( Long userId, String loginPassword, String newEmail ) throws Exception;

	/**
	 * 网关注册.
	 * 
	 * @param name
	 * @param certificationType
	 * @param certificationNo
	 * @param merchantCode
	 * @param bankType
	 * @param cardNo
	 * @param authNo
	 * @return
	 * @throws Exception
	 */
	public Personal signupByGateway( String name, CertificationType certificationType, String certificationNo
			, String merchantCode,String conuterNo,
			BankType bankType, String cardNo, String authNo ) throws Exception;
	/**
	 * 网关注册.
	 * 
	 * @param name
	 * @param certificationType
	 * @param certificationNo
	 * @param merchantCode
	 * @param bankType
	 * @param cardNo
	 * @param authNo
	 * @return
	 * @throws Exception
	 */
	public Personal signupByGateway( String name, CertificationType certificationType, String certificationNo
			, String merchantCode,String conuterNo,
			BankType bankType, String cardNo, String authNo ,String isCert,String firstGeo,
			String secondGeo, String bindSource,String bindCanal,String phone) throws Exception;

	/**
	 * 邮箱绑定
	 * 
	 * @param custId
	 * @param email
	 */
	public void bind( String custId, String email );

	/**
	 * 根据custid查询用户.
	 * 
	 * @param custId
	 * @return
	 */
	public User getByCustId( String custId );

	/**
	 * @description
	 * @param entity
	 * @param uu
	 * @author Leo
	 * @date 2013-2-25
	 */
	public void activateOrDisable( Long userId, LoginAccount loginUser, boolean enabled, String remark ) throws Exception;

	/**
	 * @description 禁用或启用用户
	 * @param userId
	 * @param enabled
	 * @author yu.han
	 * @date 2013-3-13
	 */
	public void activateOrFreezeUser( Long userId, boolean enabled );

	/**
	 * 企业用户注册.
	 * 
	 * @param mobileOrEmailMark
	 * @param mobile
	 * @param email
	 * @param loginPassword
	 * @param payPassword
	 * @param safeQuestion
	 * @param safeAnswer
	 * @return
	 */
	public User registeredEnterprise( String mobileOrEmailMark, String mobile, String email, String loginPassword, String payPassword,
			String safeQuestion, String safeAnswer );

	/**
	 * 根据条件查询.
	 * 
	 * @param termList
	 * @return
	 */
	public User list( List<Term> termList );

	/**
	 * 更新用户状态
	 * @return
	 */
	public void updateUserStatus( Long userId, String status );
}