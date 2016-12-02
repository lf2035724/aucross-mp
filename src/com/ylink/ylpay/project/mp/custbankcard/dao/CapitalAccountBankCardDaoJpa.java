/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.mp.custbankcard.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.google.code.lightssh.common.dao.jpa.JpaAnnotationDao;
import com.ylink.ylpay.project.mp.custbankcard.entity.CapitalAccountBankCard;

/** 
 * @author YangXiaojin
 * @date 2012-11-21
 * @description：TODO
 */
@Repository("capitalAccountBankCardDao")
public class CapitalAccountBankCardDaoJpa extends JpaAnnotationDao<CapitalAccountBankCard> implements CapitalAccountBankCardDao{
	
	private static final long serialVersionUID = -7497141999845183176L;

	@Override
	@SuppressWarnings("unchecked")
	public List<CapitalAccountBankCard> listBankCardBindInfo( String bankCardId){
		StringBuffer sbf = new StringBuffer();
		sbf.append(" SELECT m FROM ");
		sbf.append(this.entityClass.getName());
		sbf.append(" AS m WHERE");
		sbf.append(" m.bankCard.id = ?");
		Query query = this.getEntityManager().createQuery(sbf.toString());
		return this.addQueryParams(query,new Object[]{bankCardId}).getResultList();
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<CapitalAccountBankCard> listCapitalAccountBindInfo( String capitalAccountNO){
		Object[] objects = new Object[]{
				capitalAccountNO};
		
		StringBuffer sbf = new StringBuffer();
		sbf.append(" SELECT m FROM ");
		sbf.append(this.entityClass.getName());
		sbf.append(" AS m ");
		sbf.append(" WHERE m.capitalAccountNO = ? ");
		Query query = this.getEntityManager().createQuery(sbf.toString());
		return this.addQueryParams(query,objects).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public int delete(  String bankCardId,String capitalAccountNO ){
		int result = 0;
		String hql = " SELECT m FROM " + this.entityClass.getName() + " AS m "
			+ " WHERE m.bankCard.id = ? AND m.capitalAccountNO = ? ";
		
		Query query = this.getEntityManager().createQuery(hql);
		List<CapitalAccountBankCard> list = this.addQueryParams(query,new Object[]{
				bankCardId,capitalAccountNO,}).getResultList();
		
		if( list != null && !list.isEmpty() ){
			result = list.size();
			this.delete(list);
		}
		return result;
	}
	
	/**
	 * 查询绑定关系
	 */
	@SuppressWarnings("unchecked")
	public CapitalAccountBankCard get(String bankCardId,String capitalAccountNO){
		String hql = " SELECT m FROM " + this.entityClass.getName() + " AS m "
				+ " WHERE m.bankCard.id = ? AND m.capitalAccountNO = ? ";
			
			Query query = this.getEntityManager().createQuery(hql);
			List<CapitalAccountBankCard> list = this.addQueryParams(query,new Object[]{
					bankCardId,capitalAccountNO,}).getResultList();
			if(list==null||list.size()==0) return null;
		return list.get(0);
	}
}
