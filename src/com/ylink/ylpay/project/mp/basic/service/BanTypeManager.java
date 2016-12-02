package com.ylink.ylpay.project.mp.basic.service;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.project.mp.basic.entity.BanType;


public interface BanTypeManager extends BaseManager<BanType>{
	
	public BanType getByBankType( BanType bankType );
}
