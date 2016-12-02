package com.ylink.ylpay.project.mp.cust.service;

import java.util.List;

import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.common.project.mp.constant.AuthenticationStatus;
import com.ylink.ylpay.project.mp.cust.entity.Personal;

/**
 * 个人客户业务层.
 * 
 * @author 张磊
 * @date 2012-10-31
 */
public interface PersonalManager extends BaseManager<Personal> {

	/**
	 * 保存客户信息.
	 * 
	 * @author 张磊
	 * @date 2012-11-01
	 * @param personal
	 */
	public void save( Personal personal );

	/**
	 * 修改客户支付密码.
	 * 
	 * @author 张磊
	 * @date 2012-11-01
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 */
	public void updatePassword( String userId, String oldPassword, String newPassword );

	/**
	 * 查询 Personal
	 */
	public Personal getPersonal( Personal personal );

	/**
	 * 修改个人信息.
	 */
	public void saveOrUpdate( com.ylink.ylpay.common.project.mp.dto.Personal personalDtoBean );

	/**
	 * 获取客户信息
	 * 
	 * @author 张磊
	 * @date 2012-11-01
	 * @param identityCardNumber
	 * @param credentialsType
	 * @param name
	 */
	public Personal getCustomerInfo( String id );

	/**
	 * 保存客户信息.
	 * 
	 * @author 张磊
	 * @date 2012-11-01
	 * @param personal
	 */
	public void save( com.ylink.ylpay.common.project.mp.dto.Personal personalDtoBean );

	/**
	 * @description 校验支付密码
	 * @param custId
	 *            客户ID
	 * @param payPassword
	 *            支付密码
	 * @return boolean
	 * @author zhanglei
	 * @date 2012-11-20
	 */
	public Personal checkPayPassword( String custId, String payPassword );

	/**
	 * 修改身份证姓名
	 * 
	 * @param custId
	 * @param idNo
	 * @param idType
	 * @param trueName
	 * @return
	 */
	public void updateCert( String custId, String idNo, String idType, String trueName );

	/**
	 * 修改个人信息
	 * 
	 * @author xuwei
	 * @param personalDtoBean
	 */
	public void updatePersonal( com.ylink.ylpay.common.project.mp.dto.Personal personalDtoBean );

	/**
	 * 条件查询.
	 * 
	 * @param termList
	 * @return
	 * 
	 * @author 潘瑞峥
	 * @date 2013-01-15
	 */
	Personal find( List<Term> termList );

	/**
	 * 条件查询.
	 * 
	 * @param termList
	 * @return
	 * 
	 * @author 潘瑞峥
	 * @date 2013-01-15
	 */
	List<Personal> findList( List<Term> termList );

	/**
	 * 修改认证状态.
	 */
	void modifyAuthStatus( String id, AuthenticationStatus authStatus );

}