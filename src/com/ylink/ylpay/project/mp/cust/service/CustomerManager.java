/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.mp.cust.service;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.project.mp.cust.entity.Customer;

/** 
 * @author YangXiaojin
 * @date 2012-11-19
 * @description：TODO
 */

public interface CustomerManager extends BaseManager<Customer>{
	
	/**
	 * 更新认证状态
	 */
	public void updateCertStatus( String custid,boolean flag);

	/**
	 * 更新支付密码
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 */
	public boolean updatePassword( String userId, String oldPassword, String newPassword );
	
	/**
	 * 根据证件类型及号码查询
	 */
	public Customer get(Class<?> clazz, String certType, String certNo );
	
	public Customer checkPassword(String custId,String password);
}
