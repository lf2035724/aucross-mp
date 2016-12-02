package com.ylink.ylpay.project.mp.cust.dao;

import java.util.List;

import com.ylink.modules.orm.jpa.CustomJpaDao;
import com.ylink.ylpay.project.mp.cust.entity.MerchantProjectOrder;

/** 
 * @author feng.li
 * @date 2014-4-14
 * @description：MerchantProjectOrderDao
 */

/**
 * @author feng.li
 */
public interface MerchantProjectOrderDao  extends CustomJpaDao<MerchantProjectOrder> {
	public List<MerchantProjectOrder> getPorjectOrdersByMerchantId(String merchantId);
}
	
