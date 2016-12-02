/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.mp.cust.dao;

import com.google.code.lightssh.common.dao.Dao;
import com.ylink.ylpay.common.project.mp.constant.AuthenticationStatus;
import com.ylink.ylpay.project.mp.cust.entity.Customer;

/** 
 * @author YangXiaojin
 * @date 2012-11-19
 * @description：TODO
 */

public interface CustomerDao extends Dao<Customer> {
	
	/**
	 * 更新认证状态
	 */
	public void updateCertStatus( String custid,AuthenticationStatus status);
	
	/**
	 * 更新支付密码
	 */
	public int updatePassword( String userId, String oldPassword, String newPassword );
	
	/**
	 * 根据证件类型及号码查询
	 */
	public Customer get(Class<?> clazz, String certType, String certNo );
	/**
	 * 检查支付密码
	 * @param custId
	 * @param password
	 * @return
	 */
	public Customer checkPassword(String custId,String password);
}
