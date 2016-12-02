/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.mp.email.entity;


/**
 * 状态
 */
public enum Status{
	NEW("新建"),
	PROCESSING("处理中"),
	FAILURE("失败"),
	RESEND("重发"),
	SUCCESS("成功"),
	;
	
	private String value;
	
	Status( String value ){
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString(){
		return this.value;
	}
}