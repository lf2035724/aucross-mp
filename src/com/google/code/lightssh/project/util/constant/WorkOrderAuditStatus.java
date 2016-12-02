package com.google.code.lightssh.project.util.constant;

import java.util.HashMap;
import java.util.Map;

import com.ylink.ylpay.common.project.mp.hibernate.type.PersistentEnum;

/**
 * 审核状态
 * @author feng.li
 */
public enum WorkOrderAuditStatus implements PersistentEnum<WorkOrderAuditStatus, String>{
	
	WAIT_AUDIT( "0", "待审核" ),
	AUDIT_PASS( "1", "审核通过" ),
	AUDIT_REFUSE( "2", "审核拒绝" ),
	;
	
	private String value;
	private final String displayName;

	private static Map<String, WorkOrderAuditStatus> valueMap = new HashMap<String, WorkOrderAuditStatus>();
	static {
		for ( WorkOrderAuditStatus _enum : WorkOrderAuditStatus.values() ) {
			valueMap.put( _enum.value, _enum );
		}
	}

	WorkOrderAuditStatus( String value, String displayName ) {
		this.value = value;
		this.displayName = displayName;
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	@Override
	public WorkOrderAuditStatus getEnum( String value ) {
		return valueMap.get( value );
	}

	@Override
	public Map<String, WorkOrderAuditStatus> getAllValueMap() {
		return valueMap;
	}

	@Override
	public String toString() {
		return this.getDisplayName();
	}

	/**
	 * 枚举转换
	 */
	public static WorkOrderAuditStatus parseOf(String value){
		for(WorkOrderAuditStatus item : values())
			if(item.getValue().equals(value))
				return item;
		
		throw new IllegalArgumentException("["+value+"]不匹配!");
	}
	
	/**
	 * 枚举转换
	 */
	public static WorkOrderAuditStatus parseOfDisplayName(String value){
		for(WorkOrderAuditStatus item : values())
			if(item.getDisplayName().equals(value))
				return item;
		
		throw new IllegalArgumentException("["+value+"]不匹配!");
	}
	
	
}
