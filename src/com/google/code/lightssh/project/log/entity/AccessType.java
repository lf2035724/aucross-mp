package com.google.code.lightssh.project.log.entity;


public enum AccessType {		
	GENERAL("通用日志")
	,CREATE("创建")
	,UPDATE("更新")
	,DELETE("删除")
	,CREATE_ACCOUNT("创建登录帐户")
	,UPDATE_ACCOUNT("更新登录帐户")
	,UPDATE_PASSWORD("修改密码")
	//,STARTUP("系统启动")
	//,STOP("系统停止")
	;
	
	private String value;
		
	AccessType( String value ){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public String toString(){
		return this.value;
	}
}
	