/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.constants;

import java.util.HashMap;
import java.util.Map;

import com.ylink.ylpay.common.project.mp.hibernate.type.PersistentEnum;

public enum LineType implements PersistentEnum<LineType, String> {
	/**线上线下*/
	ALL( "ALL", "线上线下" ),
	/**线上*/
	ONLINE( "ONLINE", "线上" ),
	/**线下*/
	OFFLINE( "OFFLINE", "线下" );


	private String value;
	private String displayName;

	private static Map<String, LineType> valueMap = new HashMap<String, LineType>();

	static {
		for ( LineType _enum : LineType.values() ) {
			valueMap.put( _enum.value, _enum );
		}
	}

	/**
	 * 枚举转换
	 */
	public static LineType parseOf( String value ) {
		for ( LineType item : values() )
			if ( item.getValue().equals( value ) )
				return item;

		throw new IllegalArgumentException( "枚举值[" + value + "]不匹配!" );
	}

	public String getValue() {
		return this.value;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	LineType( String value, String displayName ) {
		this.value = value;
		this.displayName = displayName;
	}

	@Override
	public String toString() {
		return this.getDisplayName();
	}

	@Override
	public LineType getEnum( String value ) {
		return valueMap.get( value );
	}

	@Override
	public Map<String, LineType> getAllValueMap() {
		return valueMap;
	}


}