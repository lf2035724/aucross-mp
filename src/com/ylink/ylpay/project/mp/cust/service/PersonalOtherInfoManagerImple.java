package com.ylink.ylpay.project.mp.cust.service;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.project.mp.cust.dao.PersonalOtherInfoDao;
import com.ylink.ylpay.project.mp.cust.entity.PersonalOtherInfo;
@Component( "personalOtherInfoManager" )
public class PersonalOtherInfoManagerImple extends BaseManagerImpl<PersonalOtherInfo> implements
		PersonalOtherInfoManager {
	
	private static final long serialVersionUID = -9207295217057819399L;
	
	@SuppressWarnings("unused")
	private PersonalOtherInfoDao personalOtherInfoDao;
	
	@Resource( name = "personalOtherInfoDao" )
	public void setPersonalOtherInfoDao(PersonalOtherInfoDao personalOtherInfoDao) {
		this.personalOtherInfoDao = personalOtherInfoDao;
		super.dao = personalOtherInfoDao;
	}
	
	@Override
	public void save(PersonalOtherInfo t) {
		PersonalOtherInfo poi=this.get(t.getCustId());
		if(poi==null){
			this.create(t);
		}else{
			BeanUtils.copyProperties(t, poi, new String[]{"custId"});
			this.update(poi);
		}
	}
}
