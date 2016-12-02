/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 */
package com.ylink.ylpay.project.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 报表生成样式
 * @author yanghan
 * @date 2013-4-3上午11:53:46
 * @description
 */
public enum ReportStyleType{
	
	LEFTANDRIGHT("1","左右结构"),
	TOPANDDOWN("2","上下结构");
	
	private String value;
	private String displayName;
	
	private static Map<String, ReportStyleType> valueMap = new HashMap<String, ReportStyleType>();

	static {
		for ( ReportStyleType _enum : ReportStyleType.values() ) {
			valueMap.put( _enum.value, _enum );
		}
	}
	ReportStyleType( String value, String displayName ) {
		this.value = value;
		this.displayName = displayName;
	}
	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public ReportStyleType getEnum( String value ) {
		return valueMap.get( value );
	}

	public Map<String, ReportStyleType> getAllValueMap() {
		return valueMap;
	}
}
