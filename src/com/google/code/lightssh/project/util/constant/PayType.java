/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 */
package com.google.code.lightssh.project.util.constant;

import java.util.HashMap;
import java.util.Map;

import com.ylink.modules.orm.hibernate.type.PersistentEnum;

/**
 * 结算，计费付款方式
 * @author yanghan
 * @date 2013-5-9
 */
public enum PayType implements PersistentEnum<PayType,String>{
	ONLINE("00","线上"),
	OFFLINE("01","线下"),
	PROVISIONS("02","备付金"),
	BANKCARD("03","银行卡")
	;
	private String value;
	
	private String displayName;
	
	private static Map<String,PayType> valueMap = new HashMap<String, PayType>();
	
	static {
		for ( PayType _enum : PayType.values() ) {
			valueMap.put( _enum.value, _enum );
		}
	}
	
	PayType( String value, String displayName ) {
		this.value = value;
		this.displayName = displayName;
	}
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return displayName;
	}

	@Override
	public PayType getEnum(String value) {
		// TODO Auto-generated method stub
		return valueMap.get( value );
	}

	@Override
	public Map<String, PayType> getAllValueMap() {
		// TODO Auto-generated method stub
		return valueMap;
	}
	
	public static PayType parseOf( String value ) {
		for ( PayType item : values() )
			if ( item.getValue().equals( value ) )
				return item;

		throw new IllegalArgumentException( "枚举值[" + value + "]不匹配!" );
	}

}
