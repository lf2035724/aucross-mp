package com.ylink.ylpay.project.mp.basic.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.dao.SearchCondition;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.common.project.channel.constant.BankType;
import com.ylink.ylpay.common.project.channel.constant.ChannelType;
import com.ylink.ylpay.common.project.mp.constant.CustBankCardType;
import com.ylink.ylpay.project.mp.basic.dao.FundsLimitBankDao;
import com.ylink.ylpay.project.mp.basic.entity.FundsLimitBank;

@Component("fundsLimitBankManager")
public class FundsLimitBankManagerImpl extends BaseManagerImpl<FundsLimitBank> implements FundsLimitBankManager{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3799545510651838073L;
	private FundsLimitBankDao fundsLimitBankDao;

	@Resource( name = "fundsLimitBankDao")
	public void setFundsLimitBankDao(FundsLimitBankDao fundsLimitBankDao) {
		this.fundsLimitBankDao = fundsLimitBankDao;
		super.dao=fundsLimitBankDao;
	}

	public FundsLimitBankDao getFundsLimitBankDao() {
		return fundsLimitBankDao;
	}

	/**
	 * 判断唯一性
	 */
	public boolean isUniqueBankIdAndCardTypeAndChannelTypeAndBusinessId(
			FundsLimitBank fund) {
			if( fund == null || fund.getBankId() == null || fund.getCardType() == null || fund.getChannelType() == null || fund.getBusinessId() == null)
				return false;
			
			ListPage<FundsLimitBank> page = new ListPage<FundsLimitBank>();
			SearchCondition sc = new SearchCondition();
			sc.equal("bankId", fund.getBankId() ).equal("cardType",fund.getCardType() ).equal("businessId",fund.getBusinessId() ).equal("channelType",fund.getChannelType() );
			page = dao.list(page, sc);
			
			FundsLimitBank exists = (page.getList()==null||page.getList().isEmpty())
				?null:page.getList().get(0);
		
			return exists == null || exists.getIdentity().equals(fund.getIdentity() );
		}
		
		public ListPage<FundsLimitBank> list(ListPage<FundsLimitBank> page,FundsLimitBank t ) {
			SearchCondition sc = new SearchCondition();
			if( t != null ){
				if( !StringUtils.isEmpty(t.getBankId()))
					sc.equal("bankId",t.getBankId().trim() );
				
				if( t.getCardType() != null )
					sc.equal("cardType",t.getCardType() );
				
				if( t.getChannelType() != null )
					sc.equal("channelType",t.getChannelType() );
				
				if( t.getBusinessId() != null )
					sc.equal("businessId",t.getBusinessId() );
			}
			
			return dao.list(page,sc);
		}

		/**
		 * 查询银行卡限额
		 */
		public List<FundsLimitBank> getList(BankType bankType, CustBankCardType cardType,
				ChannelType channelType) {
			return getFundsLimitBankDao().getList( bankType.getValue(), cardType.getValue(),channelType.getValue() );
		}



	
	
}
