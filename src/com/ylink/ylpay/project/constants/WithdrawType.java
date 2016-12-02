package com.ylink.ylpay.project.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 资金管理的业务类型.
 * 
 * @author 潘瑞峥
 * @date 2013-3-4
 */
public enum WithdrawType {

	/* 提现类. */
	WITHDRAW( "withdraw", "classpath:config/constant/withdraw.properties" ),

	/* 充值回退类. */
	REVERSAL( "reversal", "classpath:config/constant/reversal.properties" ),

	/*
	 * 提现类跨行.
	 * 
	 * 总账户划拨给基金公司. 由于总账户是建行, 所以都走建行的跨行.
	 */
	WITHDRAW_INTER_BANK( "withdrawInterBank", "classpath:config/constant/withdraw-interbank.properties" );

	private String value;
	private final String configPath;

	private static Map<String, WithdrawType> valueMap = new HashMap<String, WithdrawType>();

	static {
		for ( WithdrawType _enum : WithdrawType.values() ) {
			valueMap.put( _enum.value, _enum );
		}
	}

	WithdrawType( String value, String configPath ) {
		this.value = value;
		this.configPath = configPath;
	}

	public String getValue() {
		return value;
	}

	public String getConfigPath() {
		return configPath;
	}

	public WithdrawType getEnum( String value ) {
		return valueMap.get( value );
	}

	public Map<String, WithdrawType> getAllValueMap() {
		return valueMap;
	}

	@Override
	public String toString() {
		return this.getValue() + ": " + this.getConfigPath();
	}

}