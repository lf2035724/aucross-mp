package com.ylink.ylpay.project.mp.custbankcard.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.common.project.account.constant.CustSubject;
import com.ylink.ylpay.common.project.channel.constant.BankType;
import com.ylink.ylpay.common.project.channel.constant.LinkMethod;
import com.ylink.ylpay.common.project.mp.constant.AuditBindingStatus;
import com.ylink.ylpay.common.project.mp.constant.AuditStatus;
import com.ylink.ylpay.common.project.mp.constant.CustBankCardType;
import com.ylink.ylpay.common.project.mp.constant.StatusMark;
import com.ylink.ylpay.project.mp.basic.entity.AccountType;
import com.ylink.ylpay.project.mp.custbankcard.entity.CustBankcard;

/**
 * 个人客户DAO
 * 
 * @author 张磊
 * @date 2012-10-31
 */
@Repository( "custBankcardDao" )
public class CustBankcardDaoJpa extends CustomJpaDaoImpl<CustBankcard> implements CustBankcardDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7650010443072612337L;

	@SuppressWarnings("unchecked")
	@Override
	public List<CustBankcard> getListByBankcard(String custId,String accTypeKey) {
		if( accTypeKey == null  )
			return null;
		
		String hql = " SELECT m FROM " + entityClass.getName() 
			+ " AS m WHERE m.custId = ? AND m.accountType.key = ? AND m.status = 1 ";
		
		//List<CustBankcard> results = getJpaTemplate().find( hql
		//		,new Object[]{custId,accTypeKey} );
		

		Query query = this.getEntityManager().createQuery(hql);
		this.addQueryParams(query,new Object[]{custId,accTypeKey} );
		List<CustBankcard> results = query.getResultList();

		return ( results == null || results.isEmpty() ) ? null : results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustBankcard> listForEnt(String custId,String accTypeKey) {
		if( accTypeKey == null  )
			return null;
		StringBuffer sbf = new StringBuffer();
		sbf.append("SELECT m FROM ");
		sbf.append(entityClass.getName());
		sbf.append(" AS m WHERE m.custId = ? AND m.accountType.key = ? AND ( m.status = 1 OR ( m.status = 0 AND m.auditStatus = ? ) )");
		List<CustBankcard> results = addQueryParams(getEntityManager().createQuery(sbf.toString())
				,new Object[]{custId,accTypeKey,AuditStatus.WAIT_AUDIT} ).getResultList();
		
		return ( results == null || results.isEmpty() ) ? null : results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustBankcard getBankAuthCode(String custId,String bankCardNo,String bankAuthCode) {
		
		String hql = " SELECT m FROM " + entityClass.getName() 
			+ " AS m WHERE m.custId = ? AND m.cardNo = ? AND m.authNo = ?  AND m.status = 1 ";
		
		//List<CustBankcard> results = getJpaTemplate().find( hql
		//		,new Object[]{custId,bankCardNo,bankAuthCode} );

		Query query = this.getEntityManager().createQuery(hql);
		this.addQueryParams(query,new Object[]{custId,bankCardNo,bankAuthCode} );
		List<CustBankcard> results = query.getResultList();

		return ( results == null || results.isEmpty() ) ? null : results.get( 0 );
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustBankcard getCustBankcard(String id, 
			String cardNo) {
		String hql = " SELECT m FROM " + entityClass.getName() 
		+ " AS m WHERE m.id = ?  AND m.cardNo = ?  AND m.status = 1 ";
	
		//List<CustBankcard> results = getJpaTemplate().find( hql
		//	,new Object[]{id,cardNo} );

		Query query = this.getEntityManager().createQuery(hql);
		this.addQueryParams(query,new Object[]{id,cardNo} );
		List<CustBankcard> results = query.getResultList();

		return ( results == null || results.isEmpty() ) ? null : results.get( 0 );
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustBankcard get(String custId, String accountTypeKey,
			String cardNo,String bindCanal) {
		String hql = " SELECT m FROM " + entityClass.getName() 
		+ " AS m WHERE m.custId = ?  AND m.cardNo = ? AND m.accountType.key = ?  AND m.status = 1 ";
	
		//List<CustBankcard> results = getJpaTemplate().find( hql
		//	,new Object[]{custId,accountTypeKey,cardNo} );

		Query query = this.getEntityManager().createQuery(hql);
			this.addQueryParams(query,new Object[]{custId,accountTypeKey,cardNo} );
		List<CustBankcard> results = query.getResultList();
		if(results == null || results.isEmpty()) return null;
		if(results.size() == 1) return results.get(0);
		if(bindCanal == null){
			for(CustBankcard card:results){
				if(card.getBindCanal()!=null && LinkMethod.DIRECT_BANK.getValue().equals(card.getBindCanal())){
					return card;
				}
			}
		}else{
			for(CustBankcard card:results){
				if(bindCanal.equals(card.getBindCanal())){
					return card;
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustBankcard> getBankCardByFund(String custId, String cardNo,String bankType,String isProtocol,String createType) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" SELECT m FROM ");
		sbf.append(entityClass.getName());
		sbf.append(" AS m WHERE m.custId = ?  ");
		sbf.append(" AND m.cardNo = ? ");
		sbf.append(" AND m.bankType=? ");
		sbf.append(" AND m.isProtocol=? ");
		sbf.append(" AND m.createType =? ");
		sbf.append(" AND m.accountType.key = ? ");
		sbf.append(" AND m.isCert=1 ");
		sbf.append(" AND m.status =1 ");
		String hql = sbf.toString();
		//List<CustBankcard> results = getJpaTemplate().find( hql
		//	,new Object[]{custId,cardNo,createType} );
		
		Query query = this.getEntityManager().createQuery(hql);
			this.addQueryParams(query,new Object[]{custId,cardNo,bankType,isProtocol,createType,"2011201"} );
		List<CustBankcard> results = query.getResultList();
		
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustBankcard> getBankCardByInvest(String custId, String cardNo,String bankType,String isProtocol,String createType) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" SELECT m FROM ");
		sbf.append(entityClass.getName());
		sbf.append(" AS m WHERE m.custId = ?  ");
		sbf.append(" AND m.cardNo = ? ");
		sbf.append(" AND m.bankType=? ");
		sbf.append(" AND m.isProtocol=? ");
		sbf.append(" AND m.createType =? ");
		sbf.append(" AND m.accountType.key = ? ");
		sbf.append(" AND m.isCert=1 ");
		sbf.append(" AND m.status =1 ");
		String hql = sbf.toString();
		//List<CustBankcard> results = getJpaTemplate().find( hql
		//	,new Object[]{custId,cardNo,createType} );
		
		Query query = this.getEntityManager().createQuery(hql);
			this.addQueryParams(query,new Object[]{custId,cardNo,bankType,isProtocol,createType,"2011101"} );
		List<CustBankcard> results = query.getResultList();
		
		return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustBankcard> getBankCardByFundGateway(String cardNo,String newCardType, String isCert) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" SELECT m FROM ");
		sbf.append(entityClass.getName());
		sbf.append(" AS m WHERE m.cardNo = ? ");
		sbf.append(" AND m.createType=? ");
		sbf.append(" AND m.isCert=? ");
		Query query = this.getEntityManager().createQuery(sbf.toString());
			this.addQueryParams(query,new Object[]{cardNo,newCardType,isCert} );
		List<CustBankcard> results = query.getResultList();
		return results;
		//return ( results == null || results.isEmpty() ) ? null : results.get( 0 );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public CustBankcard getBankCardByBankAuthCode(String bankAuthCode) {
		String hql = " SELECT m FROM " + entityClass.getName() 
		+ " AS m WHERE m.authNo = ?  AND m.status = 1 order by m.createDate desc";
	
		//List<CustBankcard> results = getJpaTemplate().find( hql
		//	,new Object[]{bankAuthCode} );
		
		List<CustBankcard> results = getEntityManager().createQuery(
				hql).setParameter(1,bankAuthCode).getResultList();

		return ( results == null || results.isEmpty() ) ? null : results.get( 0 );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustBankcard> list(String accType,String custId, String bankType
			, String name,String cardNo) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" SELECT m FROM ");
		sbf.append(entityClass.getName());
		sbf.append(" AS m WHERE m.accountType.key = ? AND m.custId = ?  AND m.cardName = ?  AND m.cardNo = ? ");
		sbf.append(" AND m.bankType = ? AND m.createType = ? AND m.status = ? ");	
	
		String createType = CustBankCardType.CREATE_TYPE1.getValue();//显性的
		String status = CustBankCardType.STATUS1.getValue();//有效
		Object[] params = null;
			params = new Object[]{accType,custId,
					name, cardNo, bankType,createType,status};
		Query query = getEntityManager().createQuery(sbf.toString());
		this.addQueryParams(query, params);
		return query.getResultList();
		
		//return getJpaTemplate().find( hql
		//	,new Object[]{accType,custId, name, cardNo, bankType,createType,status} );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustBankcard> getBankCardByPortal(String custId, String cardNo,
			BankType bankType, String createType,CustSubject custSubject) {
			StringBuffer hql = new StringBuffer("FROM "+entityClass.getName() +" AS m WHERE m.custId = ? AND m.cardNo = ? AND m.bankType = ? AND m.createType = ? AND m.status = ? AND m.accountType = ? ");
		AccountType accountType = new AccountType();
		accountType.setKey(custSubject.getValue());
		Object[] parem = new Object[]{custId,cardNo,bankType.getValue(),createType,StatusMark.VALID.getValue(),accountType};
		Query query = getEntityManager().createQuery(hql.toString());
		List<CustBankcard> result = this.addQueryParams(query, parem).getResultList();
		return result;
	}
	/*
	 * @see com.ylink.ylpay.project.mp.custbankcard.dao.CustBankcardDao#getBankCardByCustIdAtPortal(java.lang.String, com.ylink.ylpay.common.project.account.constant.CustSubject)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CustBankcard> getBankCardByCustIdAtPortal(String custId,
			CustSubject custSubject) {
		String hql = "FROM CustBankcard WHERE custId = ? AND accountType = ?";
		Query query = getEntityManager().createQuery(hql);
		AccountType accountType = new AccountType();
		accountType.setKey(custSubject.getValue());
		List<CustBankcard> list = null;
			list = addQueryParams(query, new Object[]{custId,accountType}).getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustBankcard> getCustBankInfo(String custId, String cardNO, String bankCode) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(" SELECT m FROM ");
		sbf.append(entityClass.getName());
		sbf.append(" AS m WHERE m.custId = ?  AND m.cardNo = ?  AND m.bankType= ? ");
		Query query = this.getEntityManager().createQuery(sbf.toString());
			this.addQueryParams(query,new Object[]{custId,cardNO,bankCode} );
		
		List<CustBankcard> results = query.getResultList();

		return results;
	}

	/* (non-Javadoc)
	 * @see com.ylink.ylpay.project.mp.custbankcard.dao.CustBankcardDao#getCustBankInfo(com.ylink.ylpay.project.mp.custbankcard.entity.CustBankcard)
	 */
	@Override
	public CustBankcard getCustBankInfo(CustBankcard custBankcard) {
		return this.getEntityManager().find(CustBankcard.class, custBankcard);
	}

	/* (non-Javadoc)
	 * @see com.ylink.ylpay.project.mp.custbankcard.dao.CustBankcardDao#getCustBankInfoList(com.ylink.ylpay.project.mp.custbankcard.entity.CustBankcard)
	 */
	@Override
	public List<CustBankcard> getCustBankInfoList(CustBankcard custBankcard) {
		String identity = custBankcard.getIdentity();
		String custId = custBankcard.getCustId();
		AccountType accountType = custBankcard.getAccountType();
		AuditBindingStatus auditBindingStatus = custBankcard.getAuditBindingStatus();
		AuditStatus auditStatus = custBankcard.getAuditStatus();
		String authNo = custBankcard.getAuthNo();
		String bankType = custBankcard.getBankType();
		String bindCanal = custBankcard.getBindCanal();
		String bindSource = custBankcard.getBindSource();
		String branchName = custBankcard.getBranchName();
		String cardName = custBankcard.getCardName();
		String cardNo = custBankcard.getCardNo();
		String cardType = custBankcard.getCardType();
		String contactBankNo = custBankcard.getContactBankNo();
		String createType = custBankcard.getCreateType();
		String firstGeo = custBankcard.getFirstGeo();
		String isCert = custBankcard.getIsCert();
		String isProtocol = custBankcard.getIsProtocol();
		String isSend = custBankcard.getIsSend();
		String originalCert = custBankcard.getOriginalCert();
		String originalProtocol = custBankcard.getOriginalProtocol();
		String secondGeo = custBankcard.getSecondGeo();
		String status = custBankcard.getStatus();
//		custBankcard.getSequenceKey();
//		custBankcard.getSequenceLength();
//		custBankcard.getSequenceStep();
//		custBankcard.getUpdateTime();
//		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
//		CriteriaQuery<CustBankcard> query = cb.;
//		getEntityManager().createQuery(query);
		
		Map<String, Object> map = new HashMap<String,Object>(23);
		map.put("id", identity);
		map.put("custId",custId);
		map.put("accountType",accountType );
		map.put("cardName",cardName );
		map.put("cardNo", cardNo);
		map.put("cardType", cardType);
		map.put("bankType", bankType);
		map.put("isSend", isSend);
		map.put("status",status );
		map.put("isProtocol", isProtocol);
		map.put("isCert",isCert );
		map.put("authNo",authNo );
		map.put("createType", createType);
		map.put("auditStatus",auditStatus );
		map.put("contactBankNo",contactBankNo );
		map.put("branchName",branchName );
		map.put("auditBindingStatus",auditBindingStatus );
		map.put("firstGeo",firstGeo );
		map.put("originalProtocol",originalProtocol );
		map.put("originalCert",originalCert );
		map.put("secondGeo",secondGeo );
		map.put("bindSource", bindSource);
		map.put("bindCanal",bindCanal );
		StringBuffer sbf = new StringBuffer();
		sbf.append(" SELECT m FROM AS m WHERE");
		sbf.append(entityClass.getName());
		Object[] parms=new Object[map.size()];
		int i=0;
		for (Iterator<String> keys = map.keySet().iterator(); keys.hasNext();) {
			String key = (String) keys.next();
			Object value = map.get(key);
			if(value!=null){
				if(i!=0)sbf.append(" and");
				sbf.append(" m.").append(key).append("=?").append(i);
				parms[i]=value;
				i++;
			}
		}
		Query query = this.getEntityManager().createQuery(sbf.toString());
		this.addQueryParams(query, parms);
		
		List<CustBankcard> results = query.getResultList();

		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustBankcard getByCardNoAndCreType(String cardNo, String createType,
			String bankType, String cardName) {
		// TODO Auto-generated method stub
		String hql = " SELECT m FROM " + entityClass.getName() 
		+ " AS m WHERE  m.cardNo = ? and m.createType = ?  AND m.bankType= ? and m.cardName = ? and m.bindCanal=? order by m.createDate desc ";
		Query query = this.getEntityManager().createQuery(hql);
		this.addQueryParams(query,new Object[]{cardNo,createType,bankType,cardName,"0"} );
		List<CustBankcard> results = query.getResultList();

		return ( results == null || results.isEmpty() ) ? null : results.get( 0 );
	}

	@Override
	public void updateContactBankNo(String identity, String contactBankNo) {
		String hql = "update " + entityClass.getName() + " set contactBankNo = ? , updateTime = ?  where id = ?";
		Query query = this.getEntityManager().createQuery(hql);
		this.addQueryParams(query,new Object[]{contactBankNo,new Date(),identity} );
		query.executeUpdate();
	}
}	
