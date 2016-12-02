/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.mp.cust.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.common.project.mp.constant.AuthenticationStatus;
import com.ylink.ylpay.project.mp.cust.dao.CustomerDao;
import com.ylink.ylpay.project.mp.cust.entity.Customer;

/** 
 * @author YangXiaojin
 * @date 2012-11-19
 * @description：TODO
 */
@Component( "customerManager" )
public class CustomerManagerImpl extends BaseManagerImpl<Customer> implements CustomerManager{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2468588289843480904L;

	@Resource(name="customerDao")
	public void setDao(CustomerDao dao){
		super.dao = dao;
	}
	
	public CustomerDao getDao(){
		return (CustomerDao)dao;
	}
	
	/**
	 * 更新认证状态
	 */
	public void updateCertStatus( String custid,boolean flag){
		AuthenticationStatus status = flag?AuthenticationStatus.PASSED:AuthenticationStatus.NOT_PASSED;
		getDao().updateCertStatus(custid,status);
	}
	
	/**
	 * 根据证件类型及号码查询
	 */
	public Customer get(Class<?> clazz, String certType, String certNo ){
		return getDao().get(clazz, certType, certNo);
	}
	
	@Override
	public boolean updatePassword( String userId, String oldPassword, String newPassword ) {
		return getDao().updatePassword(userId, oldPassword, newPassword) > 0;
	}
	/**
	 * @see com.ylink.ylpay.project.mp.cust.service.CustomerManager#checkPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public Customer checkPassword(String custId, String password) {
		return getDao().checkPassword(custId, password);
	}
}
