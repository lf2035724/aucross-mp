package com.ylink.ylpay.project.mp.basic.dao;

import java.util.List;

import com.google.code.lightssh.common.dao.Dao;
import com.ylink.ylpay.project.mp.basic.entity.FundsLimitBank;

public interface FundsLimitBankDao extends Dao<FundsLimitBank>{

	public List<FundsLimitBank> getList(String value, String value2, String value3);

}
