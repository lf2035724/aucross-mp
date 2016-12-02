package com.ylink.ylpay.project.mp.agency.dao;

import java.util.List;

import com.ylink.modules.orm.jpa.CustomJpaDao;
import com.ylink.ylpay.project.mp.agency.entity.CustAgency;

/**
 * 机构Dao.
 * 
 * @author 潘瑞峥
 * @date 2012-12-22
 */
public interface CustAgencyDao extends CustomJpaDao<CustAgency> {

	/**
	 * code或name查询有效机构.
	 * 
	 * @param codeAndName
	 * @return
	 */
	public List<CustAgency> findCodeOrName( String codeOrName );

}