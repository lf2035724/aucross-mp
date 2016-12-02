package com.ylink.ylpay.project.constants;

import java.util.HashMap;
import java.util.Map;


public enum PayerOrderType{
    /*快捷支付*/
	SHORTCUT("SHORTCUT","快捷支付"),
    /*网关支付*/
	EBANK("EBANK","网关支付");
	
	private String value;
	private String displayName;
	public static final String ENUM_TYPE = "com.ylink.ylpay.project.constants.PayerOrderType";
	private static Map<String, PayerOrderType> valueMap = new HashMap<String, PayerOrderType>();

	static {
		for ( PayerOrderType _enum : PayerOrderType.values() ) {
			valueMap.put( _enum.value, _enum );
		}
	}
	public static PayerOrderType parseOf( String value ){
		for ( PayerOrderType item : values() )
			if ( item.getValue().equals( value ) )
				return item;
		
		throw new IllegalArgumentException("业务类型枚举值["+value+"]错误！");
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	public PayerOrderType getEnum( String value ) {
		return valueMap.get( value );
	}
	PayerOrderType(String value, String displayName){
		this.value = value ;
		this.displayName = displayName ;
	}
}
