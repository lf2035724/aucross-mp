/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 */
package com.google.code.lightssh.project.util.constant;

import java.util.HashMap;
import java.util.Map;

import com.ylink.modules.orm.hibernate.type.PersistentEnum;

/**
 * 付款方式
 * @author yanghan
 * @date 2013-4-9上午11:56:01
 * @description
 */
public enum IncomeType implements PersistentEnum<IncomeType,String>{
	VIRTUAL_ACCOUNT("01","备付金"),
	BANK_CARD("02","银行卡")
	;
	private String value;
	private final String displayName;
	
	private static Map<String, IncomeType> valueMap = new HashMap<String, IncomeType>();

	static {
		for ( IncomeType _enum : IncomeType.values() ) {
			valueMap.put( _enum.value, _enum );
		}
	}
	
	IncomeType(String vlaue,String displayName) {
		this.value = vlaue;
		this.displayName = displayName;
	}
	@Override
	public String getValue() {
		return value;
	}
	@Override
	public String getDisplayName() {
		return displayName;
	}
	@Override
	public IncomeType getEnum(String value) {
		return valueMap.get(value);
	}
	@Override
	public Map<String, IncomeType> getAllValueMap() {
		return valueMap;
	}
	@Override
	public String toString() {
		return this.getDisplayName();
	}
	public static IncomeType parseOf( String value ) {
		for ( IncomeType item : values() )
			if ( item.getValue().equals( value ) )
				return item;

		throw new IllegalArgumentException( "枚举值[" + value + "]不匹配!" );
	}
}
