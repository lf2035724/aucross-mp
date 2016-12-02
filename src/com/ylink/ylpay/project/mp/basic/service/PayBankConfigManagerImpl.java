/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 */
package com.ylink.ylpay.project.mp.basic.service;

import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.ApplicationException;
import com.google.code.lightssh.common.dao.SearchCondition;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.mp.basic.dao.PayBankConfigDao;
import com.ylink.ylpay.project.mp.basic.entity.PayBankConfig;

/**
 * 
 * @author yu
 *
 */
@Component("payBankConfigManager")
public class PayBankConfigManagerImpl extends BaseManagerImpl<PayBankConfig> implements PayBankConfigManager{
	private static final long serialVersionUID = -1358125130927918882L;
	
//	@Autowired
//	private BanTypeManager banTypeManager;
	
	@Override
	public ListPage<PayBankConfig> list(ListPage<PayBankConfig> page,PayBankConfig bank){
		SearchCondition sc = new SearchCondition();
		if(StringUtils.isNotBlank(bank.getBankType())){
			sc.equal("bankType", bank.getBankType());
		}
		if(StringUtils.isNotBlank(bank.getOptType())){
			sc.equal("optType", bank.getOptType());
		}
		if(bank.getIsDef()!=null){
			sc.equal("isDef",bank.getIsDef());
		}
		page = getDao().list(page, sc);
		return page;
	}
	
	@Resource(name="payBankConfigDao")
	public void setDao(PayBankConfigDao dao){
		super.dao = dao;
	}
	public PayBankConfigDao getDao(){
		return (PayBankConfigDao)this.dao;
	}

	/* (non-Javadoc)
	 * @see com.ylink.ylpay.project.mp.basic.service.PayBankConfigManager#saveEntity(com.ylink.ylpay.project.mp.basic.entity.PayBankConfig)
	 */
	@Override
	public void saveEntity(PayBankConfig payBankConfig) throws Exception {
		Validate.notNull(payBankConfig,"银行信息不能为空请检查数据");
		Validate.notNull(payBankConfig.getBankType(),"银行行别不能为空请检查数据");
		Validate.notNull(payBankConfig.getOptType(),"业务类型不能为空请检查数据");
		Validate.notNull(payBankConfig.getLineType(),"支付类型不能为空请检查数据");
		PayBankConfig checkBank = new PayBankConfig();
		ListPage<PayBankConfig> page=new ListPage<PayBankConfig>();
		try{
//			BanType bankType = banTypeManager.get(payBankConfig.getBankType());
//			Validate.notNull(bankType,"银行行别信息为空,请检查BankTypeCode为["+payBankConfig.getBankType()+"]的数据");
			checkBank.setBankType(payBankConfig.getBankType());
			checkBank.setOptType(payBankConfig.getOptType());
			if(list(page,checkBank).getAllSize()>0){
				throw new MpCheckedException("该银行同种业务类型已经配置,不能重复保存");
			}
			if(payBankConfig.getIsDef()==1&&payBankConfig.getIdentity()!=null){
				checkBank.setOptType(payBankConfig.getOptType());
				checkBank.setIsDef(1);
				page=list(page,checkBank);
				if(page!=null&&page.getList()!=null&&page.getAllSize()>0){
					for (Iterator<PayBankConfig> iterator = page.getList().iterator(); iterator.hasNext();) {
						PayBankConfig c = (PayBankConfig) iterator.next();
						if(!payBankConfig.getIdentity().equals(c.getIdentity())){
							throw new MpCheckedException("该业务类型已经存在默认配置，业务类型为【"+payBankConfig.getOptType()+"】");
						}
						
					}
				}
			}
		}catch (Exception e) {
			throw new ApplicationException("保存银行信息的时候发生错误:" + e.getMessage());
		}
		save(payBankConfig);
	}
	/*
	 * (non-Javadoc)
	 * @see com.ylink.ylpay.project.mp.basic.service.PayBankConfigManager#getMatchConfig(java.lang.String, java.lang.String)
	 */
	@Override
	public PayBankConfig getMatchConfig(String bankType,String optType){
		//先根据行别和业务编码查
		PayBankConfig checkConfig= new PayBankConfig();
		checkConfig.setBankType(bankType);
		checkConfig.setOptType(optType);
		ListPage<PayBankConfig> page=list(new ListPage<PayBankConfig>(),checkConfig);
		if(page!=null&&page.getList()!=null&&page.getAllSize()>0){
			return page.getFirst();
		}
		//查不到再根据业务编码查默认付款行
		checkConfig.setBankType(null);
		checkConfig.setIsDef(1);
		page=list(new ListPage<PayBankConfig>(),checkConfig);
		return page.getFirst();
	}
	
}
