package com.ylink.ylpay.project.mp.cust.dao;

import com.ylink.modules.orm.jpa.CustomJpaDao;
import com.ylink.ylpay.project.mp.cust.entity.Personal;

/**
 * 个人客户DAO
 * 
 * @author 张磊
 * @date 2012-10-31
 */
public interface PersonalDao extends CustomJpaDao<Personal> {

	/**
	 * 查询具体子类
	 */
	public Personal read( Class<?> clazz,Personal personal );
	
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
	 * @description 校验支付密码
	 * @param custId 客户ID
	 * @param payPassword 支付密码
	 * @return boolean
	 * @author zhanglei
	 * @date 2012-11-20
	 */
	public Personal checkPayPassword(String custId,String payPassword);
	
}
