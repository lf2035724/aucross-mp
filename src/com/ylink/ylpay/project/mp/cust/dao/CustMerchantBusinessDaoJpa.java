/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 * 创建:Leo 2013-2-27
 */

/**
 * CustMerchantBusinessChangeJpa.java
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 * 创建:Leo 2013-2-27
 */
package com.ylink.ylpay.project.mp.cust.dao;

import org.springframework.stereotype.Component;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.cust.entity.CustMerchantBusiness;

/** 
 * @author Leo
 * @date 2013-2-27
 * @description：TODO
 */

/**
 * @author Leo
 *
 */
@Component( "custMerchantBusinessDao" )
public class CustMerchantBusinessDaoJpa extends CustomJpaDaoImpl<CustMerchantBusiness> implements CustMerchantBusinessDao {
	private static final long serialVersionUID = -8250364314282003930L;
 
}
