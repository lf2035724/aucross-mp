package com.google.code.lightssh.project.util.constant;

import java.util.HashMap;
import java.util.Map;

import com.ylink.modules.orm.hibernate.type.PersistentEnum;

public enum RedemptionAuditStatus implements PersistentEnum<RedemptionAuditStatus, String>{
	NEW("NEW","待审核"),
	AUDITING("AUDITING","审核中"),
	AUDIT_PASS("AUDIT_PASS","审核通过"),
	AUDIT_REJECT("AUDIT_REJECT","审核拒绝");
	
	private String value;
	
	private String displayName;
	
	private static Map<String,RedemptionAuditStatus> valueMap = new HashMap<String, RedemptionAuditStatus>();
	
	static {
		for ( RedemptionAuditStatus _enum : RedemptionAuditStatus.values() ) {
			valueMap.put( _enum.value, _enum );
		}
	}
	
	RedemptionAuditStatus( String value, String displayName ) {
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
	public RedemptionAuditStatus getEnum(String value){
		return valueMap.get( value );
	}

	@Override
	public Map<String, RedemptionAuditStatus> getAllValueMap() {
		return valueMap;
	}
	public static RedemptionAuditStatus[] getPassedAndReject(){
		RedemptionAuditStatus[] type = new RedemptionAuditStatus[]{AUDIT_PASS,AUDIT_REJECT};
		return type;
	}
	public static RedemptionAuditStatus parseOf( String value ) {
		for ( RedemptionAuditStatus item : values() )
			if ( item.getValue().equals( value ) )
				return item;

		throw new IllegalArgumentException( "枚举值[" + value + "]不匹配!" );
	}
}
