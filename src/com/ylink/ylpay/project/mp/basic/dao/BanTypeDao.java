package com.ylink.ylpay.project.mp.basic.dao;

import com.google.code.lightssh.common.dao.Dao;
import com.ylink.ylpay.project.mp.basic.entity.BanType;

/**
 * 银行类别查询
 * @author 徐薇
 *
 */
public interface BanTypeDao extends Dao<BanType> {


	/**
	 * 查询具体子类
	 */
	public BanType read( Class<?> clazz,BanType bantype );
}
