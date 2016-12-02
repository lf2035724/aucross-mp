package com.ylink.ylpay.project.mp.cust.dao;

import org.springframework.stereotype.Repository;

import com.google.code.lightssh.common.dao.jpa.JpaAnnotationDao;
import com.ylink.ylpay.project.mp.cust.entity.PersonalOtherInfo;
@Repository( "personalOtherInfoDao" )
public class PersonalOtherInfoDaoJpa extends JpaAnnotationDao<PersonalOtherInfo> implements
		PersonalOtherInfoDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8040623415740608860L;
	
}
