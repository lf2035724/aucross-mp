package com.ylink.ylpay.project.mp.basic.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.project.mp.basic.dao.BanTypeDao;
import com.ylink.ylpay.project.mp.basic.entity.BanType;




@Component("banTypeManager")
public class BanTypeManagerImpl extends BaseManagerImpl<BanType> implements BanTypeManager {
	
	private static final long serialVersionUID = 1158064220650555480L;
	
	private BanTypeDao banTypeDao;
	
	@Resource( name = "banTypeDao")
	public void setBanTypeDao(BanTypeDao banTypeDao) {
		this.banTypeDao = banTypeDao;
		super.dao=banTypeDao;
	}

	public BanTypeDao getBanTypeDao() {
		return banTypeDao;
	}

	@Override
	public BanType getByBankType(BanType banType) {
		return getBanTypeDao().read(BanType.class, banType);
	}
}
