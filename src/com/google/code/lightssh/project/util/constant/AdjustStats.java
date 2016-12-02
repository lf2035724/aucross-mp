/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */
package com.google.code.lightssh.project.util.constant;

import java.util.HashMap;
import java.util.Map;

import com.ylink.ylpay.common.project.mp.hibernate.type.PersistentEnum;

/**
 * @author YangHan
 * @date 2013-2-28
 */
public enum AdjustStats  implements PersistentEnum<AdjustStats, String> {
	
	REVERSE("00","冲正"),
	
	RECORD_AND_REFUND("01","补录和退款");
	
private String value;
	
	private String displayName;
	
	private static Map<String, AdjustStats> valueMap = new HashMap<String, AdjustStats>();
	
	static {
		for ( AdjustStats _enum : AdjustStats.values() ) {
			valueMap.put( _enum.value, _enum );
		}
	}

	AdjustStats( String value, String displayName ) {
		this.value = value;
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
	public AdjustStats getEnum(String value) {
		return valueMap.get( value );
	}
	@Override
	public Map<String, AdjustStats> getAllValueMap() {
		return valueMap;
	}
	public static AdjustStats parseOf( String value ) {
		for ( AdjustStats item : values() )
			if ( item.getValue().equals( value ) )
				return item;

		throw new IllegalArgumentException( "枚举值[" + value + "]不匹配!" );
	}
}
