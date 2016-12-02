/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 */
package com.ylink.ylpay.project.mp.basic.app;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ylink.ylpay.common.project.mp.app.BankAppService;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.mp.basic.entity.Bank;
import com.ylink.ylpay.project.mp.basic.service.BankManager;

/**
 * @author yanghan
 * @date 2013-3-20上午11:10:07
 * @description
 */
@Component("bankAppService")
public class BankAppServiceImpl implements BankAppService{
	
	private static Logger logger = LoggerFactory.getLogger( BankAppServiceImpl.class );

	
	@Autowired
	private BankManager bankManager;
	
	/* (non-Javadoc)
	 * @see com.ylink.ylpay.common.project.mp.app.BankAppService#getBankListByBankTypeAndGeo(com.ylink.ylpay.common.project.mp.dto.Bank)
	 */
	@Override
	public List<com.ylink.ylpay.common.project.mp.dto.Bank> getBankListByBankTypeAndGeo(com.ylink.ylpay.common.project.mp.dto.Bank bank) throws MpCheckedException {
		Bank entity = new Bank();
		entity.setBankTypeCode(bank.getBankType());
		entity.setGeoCode(bank.getGeoFristCode());
		entity.setGeoSecondCode(bank.getGeoCode());
		List<Bank> list = null;
		try {
			list = bankManager.findBankListByBankType(entity);
		} catch (Exception e) {
			logger.debug("查询银行信息时发生错误：" + e.getMessage());
			throw new MpCheckedException("查询银行信息时发生错误：" + e.getMessage());
		}
		List<com.ylink.ylpay.common.project.mp.dto.Bank> resultList = new ArrayList<com.ylink.ylpay.common.project.mp.dto.Bank>();
		if(list!=null&&!list.isEmpty()){
			for(Bank entityTemp : list){
				com.ylink.ylpay.common.project.mp.dto.Bank dtoBean = new com.ylink.ylpay.common.project.mp.dto.Bank();
				dtoBean.setBankCode(entityTemp.getBankCode());
				dtoBean.setBankName(entityTemp.getBankName());
				dtoBean.setBankType(entityTemp.getBankType().getBankType());
				dtoBean.setGeoFristCode(entityTemp.getGeoCode());
				dtoBean.setGeoCode(entityTemp.getGeo().getCode());
				dtoBean.setGeoName(entityTemp.getGeo().getName());
				resultList.add(dtoBean);
			}
		}else{
			com.ylink.ylpay.common.project.mp.dto.Bank dtoBean = new com.ylink.ylpay.common.project.mp.dto.Bank();
			dtoBean.setBankCode("000000000000");
			dtoBean.setBankName("默认银行");
			resultList.add(dtoBean);
		}
		return resultList;
	}
	
	
}
