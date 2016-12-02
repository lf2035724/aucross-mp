/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.task.mp;

import java.util.Arrays;
import java.util.List;


/** 
 * @author YangXiaojin
 * @date 2012-12-22
 * @description：TODO
 */

public enum SchedulerTypeEnum {
	
	SETTLEMENT_DAY_END("PSDE","清结算日终处理"),
	SETTLEMENT_DAY_CUT("SETTLEMENT_DAY_CUT","清结算日切"),
	SETTLEMENT_TRIAL_BALANCE("SETTLEMENT_TRIAL_BALANCE","清结算试算平衡"),
	SETTLEMENT_YEAR_END("SETTLEMENT_YEAR_END","清结算年终结转"),
	SETTLEMENT_INTEREST_CAL("SETTLEMENT_INTEREST_CAL","清结算计息"),
	SETTLEMENT_INTEREST_SET("SETTLEMENT_INTEREST_SET","清结算结息"),
	SETTLEMENT_NETTING("SETTLEMENT_NETTING","清结算轧差"),
	SETTLEMENT_FEE_CAL("SETTLEMENT_FEE_CAL","清结算计费"),
	SETTLEMENT_REC_PAY("SETTLEMENT_REC_PAY","清结算支付对帐"),
	SETTLEMENT_REC_CHANNEL("SETTLEMENT_REC_CHANNEL","清结算渠道对帐"),
	SETTLEMENT_PRE_J03("SETTLEMENT_PRE_J03","J03数据准备"),
	SETTLEMENT_PRE_J04("SETTLEMENT_PRE_J04","J04数据准备"),
	
	SETTLEMENT_MERCHANT_GENERATE_REPORT("SETTLEMENT_MERCHANT_GENERATE_REPORT","商户日结算对账单生成"),
	SETTLEMENT_MERCHANT_REPORT("SETTLEMENT_MERCHANT_REPORT","商户日结算报表处理"),
	SETTLEMENT_FUND_TRADE_REPORT("SETTLEMENT_FUND_TRADE_REPORT","基金交易日报表处理"),
	SETTLEMENT_FUND_BUSINESS_REPORT("SETTLEMENT_FUND_BUSINESS_REPORT","基金运营日报表处理"),
	SETTLEMENT_TRANS_STATUS_REPORT("SETTLEMENT_TRANS_STATUS_REPORT","统计交易状态信息"),
	SETTLEMENT_MCH_TRADE_REPORT("SETTLEMENT_MCH_TRADE_REPORT","消费商户日交易报表"),
	
	FUND_DAY_END("PFDE","基金日终处理"),
	FUND_DAY_CUT("FUND_DAY_CUT","基金日切"),
	FUND_BILL_CHECK("FUND_BILL_CHECK","基金与支付系统对账"),
	FUND_INVESTOR_RECORD("FUND_INVESTOR_RECORD","基金投资者备案"),
	FUND_SALER_RECORD("FUND_SALER_RECORD","基金销售机构备案"),
	FUND_FUNDS_ALL("FUND_FUNDS_ALL","基金备付金明细和汇总报备"),
	FUND_FUNDS_NETTING("FUND_FUNDS_NETTING","基金轧差明细和汇总报备"),
	FUND_J03("FUND_J03","基金支付机构和基金销售机构划款J03报备"),
	FUND_J04("FUND_J04","基金支付机构和投资者划款j04报备"),
	FUND_REDEEM_BILL_CHECK("FUND_REDEEM_BILL_CHECK","实时赎回对账"),
	FUND_CHECK_FILE_TIMELY_REDEMPTION("FUND_CHECK_FILE_TIMELY_REDEMPTION","基金实时赎回生成对账单前检查"),
	FUND_STOCK_BILL_CHECK("FUND_STOCK_BILL_CHECK","股交所资金流转对账"),
	
	OTCBB_DAY_END("PODE","交易所前置日终处理"),
	OTCBB_DAY_CUT("OTCBB_DAY_CUT","交易所前置日切"),
	OTCBB_BILL_CHECK("OTCBB_BILL_CHECK","交易所前置与支付系统对账"),
	
	FUND_QUICKLY_WITHDRAW_NETTING("FQWN","基金快速提现轧差"),
	FUND_QUICKLY_WITHDRAW_RECORD("FUND_QUICKLY_WITHDRAW_RECORD","基金快速提现备案"),
	FUND_QUICKLY_WITHDRAW_TRANSFER("FUND_QUICKLY_WITHDRAW_TRANSFER","基金快速提现头寸调拨"),
	FUND_QUICKLY_WITHDRAW_PAY("FUND_QUICKLY_WITHDRAW_PAY","基金快速提现支付"),
	MARGINPAY_QUICKLY_WITHDRAW_ENTER("MARGINPAY_QUICKLY_WITHDRAW_ENTER","保证金快速提现录入"),
	MARGINPAY_QUICKLY_WITHDRAW_TRANSFER("MARGINPAY_QUICKLY_WITHDRAW_TRANSFER","保证金快速提现头寸调拨"),
	MARGINPAY_QUICKLY_WITHDRAW_PAY("MARGINPAY_QUICKLY_WITHDRAW_PAY","保证金快速提现支付"),
	
	INVEST_DAY_END("IVDE","慧投日终处理"),
	INVEST_DAY_CUT("INVEST_DAY_CUT","慧投日切"),
	INVEST_BANK_BILL("INVEST_BANK_BILL","对账"),
	; 
	
	SchedulerTypeEnum(String key,String name ){
		this.key = key;
		this.name=name;
	}
	
	private String key;
	
	private String name;

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}
	
	/**
	 * 枚举转化
	 */
	public static SchedulerTypeEnum parseOf(String value){
		for(SchedulerTypeEnum item : values())
			if(item.getKey().equals(value))
				return item;
		
		throw new IllegalArgumentException("任务类型["+value+"]不匹配!");
	}
	
	public static List<SchedulerTypeEnum> getNeedMonitorList(){
		return Arrays.asList(new SchedulerTypeEnum [] {
				SETTLEMENT_DAY_END,FUND_DAY_END,OTCBB_DAY_END
		});
	}

}
