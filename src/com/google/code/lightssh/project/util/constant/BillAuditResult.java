/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */
package com.google.code.lightssh.project.util.constant;
/** 
 * @author YangHan
 * @date 2012-02-23
 * @description：手工对账审核状态
 */
import java.util.HashMap;
import java.util.Map;

import com.ylink.ylpay.common.project.mp.hibernate.type.PersistentEnum;

public enum BillAuditResult implements PersistentEnum<BillAuditResult, String>{
	
	NEW("NEW","新建"),
	LAST_AUDIT_REJECT("LAST_AUDIT_REJECT","审核拒绝"), 
	LAST_AUDIT_PASSED("LAST_AUDIT_PASSED","审核通过");
	
	private String value;
	
	private String displayName;
	
	private static Map<String,BillAuditResult> valueMap = new HashMap<String, BillAuditResult>();
	
	static {
		for ( BillAuditResult _enum : BillAuditResult.values() ) {
			valueMap.put( _enum.value, _enum );
		}
	}
	
	BillAuditResult( String value, String displayName ) {
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
	public BillAuditResult getEnum(String value) {
		return valueMap.get( value );
	}

	@Override
	public Map<String, BillAuditResult> getAllValueMap() {
		return valueMap;
	}
	public static BillAuditResult parseOf( String value ) {
		for ( BillAuditResult item : values() )
			if ( item.getValue().equals( value ) )
				return item;

		throw new IllegalArgumentException( "枚举值[" + value + "]不匹配!" );
	}
}
