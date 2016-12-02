/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 */
package com.ylink.ylpay.project.mp.basic.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.ApplicationException;
import com.google.code.lightssh.common.dao.SearchCondition;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.google.code.lightssh.project.geo.entity.GeographicBoundary;
import com.google.code.lightssh.project.geo.service.GeographicBoundaryManager;
import com.ylink.ylpay.common.project.mp.constant.StatusMark;
import com.ylink.ylpay.project.mp.basic.dao.BankDao;
import com.ylink.ylpay.project.mp.basic.entity.BanType;
import com.ylink.ylpay.project.mp.basic.entity.Bank;

/**
 * @author yanghan
 * @date 2013-3-18下午3:54:37
 */
@Component("bankManager")
public class BankManagerImpl extends BaseManagerImpl<Bank> implements BankManager{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1358125130927918882L;
	
	@Autowired
	private BanTypeManager banTypeManager;
	
	@Autowired
	private GeographicBoundaryManager geographicBoundaryManager;
	
	@Resource(name="bankDao")
	public void setDao(BankDao dao){
		super.dao = dao;
	}
	public BankDao getDao(){
		return (BankDao)this.dao;
	}
	/* (non-Javadoc)
	 * @see com.ylink.ylpay.project.mp.basic.service.BankManager#checkRepeatBankCode(java.lang.String)
	 */
	@Override
	public boolean checkRepeatBankCode(String bankCode) {
		try {
			Bank bank = getDao().findBankByBankCode(bankCode);
			if(bank != null)
				return true;
		} catch (Exception e) {
			throw new ApplicationException("检查银行行号发生异常:" + e.getMessage());
		}
		return false;
	}
	/* (non-Javadoc)
	 * @see com.ylink.ylpay.project.mp.basic.service.BankManager#findBankListByBankType(com.ylink.ylpay.project.mp.basic.entity.BanType)
	 */
	@Override
	public List<Bank> findBankListByBankType(Bank bank) throws Exception{
		Validate.notNull(bank,"银行行别不能为空");
		Validate.notNull(bank.getBankTypeCode(),"银行行号不能为空");
		return getDao().findBankListByBankType(bank);
	}
	/* (non-Javadoc)
	 * @see com.ylink.ylpay.project.mp.basic.service.BankManager#saveEntity(com.ylink.ylpay.project.mp.basic.entity.Bank)
	 */
	@Override
	public void saveEntity(Bank bank) throws Exception {
		Validate.notNull(bank,"银行信息不能为空请检查数据");
		Validate.notNull(bank.getBankCode(),"银行行号不能为空请检查数据");
		Validate.notNull(bank.getBankName(),"银行名称不能为空请检查数据");
		Validate.notNull(bank.getStatus(),"状态不能为空请检查数据");
		Validate.notNull(bank.getBankTypeCode(),"银行行别不能为空请检查数据");
		bank.setStatusMark(StatusMark.parseOf(bank.getStatus()));
		try{
			BanType bankType = banTypeManager.get(bank.getBankTypeCode());
			Validate.notNull(bankType,"银行行别信息为空,请检查BankTypeCode为["+bank.getBankTypeCode()+"]的数据");
			bank.setBankType(bankType);
			if(bank.getGeoSecondCode() != null) {
				GeographicBoundary geo = geographicBoundaryManager.get(bank.getGeoSecondCode());
				bank.setGeo(geo);
			}else if(bank.getGeoCode()!=null){
				GeographicBoundary geo = geographicBoundaryManager.get(bank.getGeoCode());
				bank.setGeo(geo);
			}
		}catch (Exception e) {
			throw new ApplicationException("保存银行信息的时候发生错误:" + e.getMessage());
		}
		save(bank);
	}
	public ListPage<Bank> list(ListPage<Bank> page,Bank bank){
		SearchCondition sc = new SearchCondition();
		if(StringUtils.isEmpty(bank.getBankCode()))
			bank.setBankCode(null);
		sc.equal("bankCode", bank.getBankCode());
		if(StringUtils.isEmpty(bank.getBankName()))
			bank.setBankName(null);
		sc.equal("bankName", bank.getBankName());
		
		if(StringUtils.isNotEmpty(bank.getBankTypeCode())){
			BanType bankType = banTypeManager.get(bank.getBankTypeCode());
			bank.setBankType(bankType);
		}
		if(StringUtils.isNotEmpty(bank.getStatus()))
			bank.setStatusMark(StatusMark.parseOf(bank.getStatus()));
		sc.equal("statusMark", bank.getStatusMark());
		sc.equal("bankType", bank.getBankType());
		page = getDao().list(page, sc);
		return page;
	}
	
	public Bank getByUbankNo(String ubankNo){
		return this.getDao().findBankByBankCode(ubankNo);
	}
}
