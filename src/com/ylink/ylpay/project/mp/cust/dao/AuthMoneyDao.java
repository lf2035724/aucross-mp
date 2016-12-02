package com.ylink.ylpay.project.mp.cust.dao;

import com.google.code.lightssh.common.dao.Dao;
import com.ylink.ylpay.project.mp.cust.entity.AuthMoney;

/** 
 * @author feng.li
 * @date 2013-10-25
 * @description：TODO
 */

/**
 * @author feng.li
 *
 */
public interface AuthMoneyDao extends Dao<AuthMoney>{
	
	public AuthMoney getByCustId(String custId);
	
}
