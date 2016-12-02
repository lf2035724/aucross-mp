package com.ylink.ylpay.project.mp.basic.service;

import com.google.code.lightssh.common.service.BaseManager;
import com.ylink.ylpay.common.project.mp.constant.OptType;
import com.ylink.ylpay.project.mp.basic.entity.BusinessType;

public interface BusinessTypeManager extends BaseManager<BusinessType>{

	/**
	 * 通过交易类型查询.
	 * 
	 * @param type
	 * @return
	 */
	public BusinessType findByType ( OptType type );

}