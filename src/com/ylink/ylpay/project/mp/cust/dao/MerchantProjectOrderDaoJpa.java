package com.ylink.ylpay.project.mp.cust.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.cust.entity.MerchantProjectOrder;

/** 
 * @author feng.li
 * @date 2014-4-14
 * @description：merchantProjectOrderDao
 */

/**
 * @author feng.li
 */
@Component( "merchantProjectOrderDao" )
public class MerchantProjectOrderDaoJpa extends CustomJpaDaoImpl<MerchantProjectOrder> implements MerchantProjectOrderDao {
	private static final long serialVersionUID = -8250364314282003930L;

	@Override
	@SuppressWarnings("unchecked")
	public List<MerchantProjectOrder> getPorjectOrdersByMerchantId(
			String merchantId) {
		if(merchantId == null) return null;
		StringBuffer sbf = new StringBuffer(" FROM ");
		sbf.append(entityClass.getName());
		sbf.append(" AS m WHERE m.custMerchant.id = ?");
		Query query = getEntityManager().createQuery(sbf.toString());
		return addQueryParams(query,new Object[]{merchantId}).getResultList();
	}
 
}
