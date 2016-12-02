/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */
package com.google.code.lightssh.project.util.constant;
/** 
 * @author YangHan
 * @date 2012-02-23
 * @description：手工对账对账类型
 */
import java.util.HashMap;
import java.util.Map;

import com.ylink.ylpay.common.project.mp.hibernate.type.PersistentEnum;

public enum BillCheckType  implements PersistentEnum<BillCheckType, String> {
	
	DO("DO","未对账"),
	
	UNDO("UNDO","取消对账");
	
	private String value;
	
	private String displayName;
	
	private static Map<String, BillCheckType> valueMap = new HashMap<String, BillCheckType>();
	
	static {
		for ( BillCheckType _enum : BillCheckType.values() ) {
			valueMap.put( _enum.value, _enum );
		}
	}

	BillCheckType( String value, String displayName ) {
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
	public BillCheckType getEnum(String value) {
		return valueMap.get( value );
	}
	@Override
	public Map<String, BillCheckType> getAllValueMap() {
		return valueMap;
	}
	public static BillCheckType parseOf( String value ) {
		for ( BillCheckType item : values() )
			if ( item.getValue().equals( value ) )
				return item;

		throw new IllegalArgumentException( "枚举值[" + value + "]不匹配!" );
	}
	
}
