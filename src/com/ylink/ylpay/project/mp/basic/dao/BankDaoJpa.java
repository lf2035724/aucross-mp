/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 */
package com.ylink.ylpay.project.mp.basic.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.google.code.lightssh.common.dao.jpa.JpaAnnotationDao;
import com.google.code.lightssh.project.geo.entity.GeographicBoundary;
import com.ylink.ylpay.project.mp.basic.entity.BanType;
import com.ylink.ylpay.project.mp.basic.entity.Bank;

/**
 * @author yanghan
 * @date 2013-3-18下午3:37:32
 */
@Repository("bankDao")
public class BankDaoJpa extends JpaAnnotationDao<Bank> implements BankDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5708967859863424092L;

	/* (non-Javadoc)
	 * @see com.ylink.ylpay.project.mp.basic.dao.BankDao#findBankByBankCode(java.lang.String)
	 */
	@Override
	public Bank findBankByBankCode(String BankCode){
		String hql = "FROM Bank bank Where bank.bankCode = ?";
		Query query = getEntityManager().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Bank> results = this.addQueryParams(query, BankCode).getResultList();
		
		return results==null||results.isEmpty()?null:results.get(0);
	}

	/* (non-Javadoc)
	 * @see com.ylink.ylpay.project.mp.basic.dao.BankDao#findBankListByBankType(com.ylink.ylpay.project.mp.basic.entity.Bank)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Bank> findBankListByBankType(Bank bank) throws Exception {
		StringBuffer hql = new StringBuffer("FROM Bank bank Where bank.bankType = ? ");
		List<Object> objList = new ArrayList<Object>();
		BanType bankType = new BanType();
		bankType.setBankType(bank.getBankTypeCode());
		objList.add(bankType);
		if(StringUtils.isNotEmpty(bank.getGeoCode())){
			hql.append("AND bank.geoCode = ? ");
			objList.add(bank.getGeoCode());
		}
		if(StringUtils.isNotEmpty(bank.getGeoSecondCode())){
			hql.append("AND bank.geo = ?");
			GeographicBoundary geo = new GeographicBoundary();
			geo.setCode(bank.getGeoSecondCode());
			objList.add(geo);
		}
		Object[] obj = objList.toArray();
		Query query = getEntityManager().createQuery(hql.toString());
		return this.addQueryParams(query,obj).getResultList();
	}
	
}
