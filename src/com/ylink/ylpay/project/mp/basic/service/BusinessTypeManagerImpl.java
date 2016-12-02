package com.ylink.ylpay.project.mp.basic.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.dao.Term.Type;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.common.project.mp.constant.OptType;
import com.ylink.ylpay.project.mp.basic.dao.BusinessTypeDao;
import com.ylink.ylpay.project.mp.basic.entity.BusinessType;

@Component("businessTypeManager")
public class BusinessTypeManagerImpl extends BaseManagerImpl<BusinessType> implements BusinessTypeManager{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3227257354450191681L;
	
	private BusinessTypeDao businessTypeDao;
    
	@Resource( name = "businessTypeDao")
	public void setBusinessTypeDao(BusinessTypeDao businessTypeDao) {
		this.businessTypeDao = businessTypeDao;
		super.dao=businessTypeDao;
	}

	public BusinessTypeDao getBusinessTypeDao() {
		return businessTypeDao;
	}

	@Override
	public BusinessType findByType( OptType type ) {
		Validate.notNull( type );
		
		List<Term> termList = new ArrayList<Term>();
		termList.add( new Term( Type.EQUAL, "optType", type ) );
		return this.businessTypeDao.findUnique( termList );
	}

}