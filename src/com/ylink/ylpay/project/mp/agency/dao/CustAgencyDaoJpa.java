package com.ylink.ylpay.project.mp.agency.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.common.project.mp.constant.AuditStatus;
import com.ylink.ylpay.project.mp.agency.entity.CustAgency;

/**
 * 机构Dao.
 * 
 * @author 潘瑞峥
 * @date 2012-12-22
 */
@Component( "custAgencyDao" )
public class CustAgencyDaoJpa extends CustomJpaDaoImpl<CustAgency> implements CustAgencyDao {

	private static final long serialVersionUID = 775919406502198069L;

	/**
	 * code或name查询有效机构.
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public List<CustAgency> findCodeOrName( String codeOrName ) {
		StringBuffer jpql = new StringBuffer();
		jpql.append( "FROM CustAgency e" );
		jpql.append( " WHERE e.status = '" ).append( AuditStatus.AUDIT_PASS.getValue() ).append( "'" );
		if ( StringUtils.isNotBlank( codeOrName ) ) {
			jpql.append( " AND e.code LIKE '%" ).append( codeOrName ).append( "%'" );
			jpql.append( " OR e.name LIKE '%" ).append( codeOrName ).append( "%'" );
		}

		return super.getEntityManager().createQuery( jpql.toString() ).getResultList();
	}

}