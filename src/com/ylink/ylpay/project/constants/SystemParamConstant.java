package com.ylink.ylpay.project.constants;

/**
 * 系统参数常量类.
 * 
 * @author 潘瑞峥
 * @date 2013-1-6
 */
public final class SystemParamConstant {

	/** 系统参数表key - 人工找回密码邮件内容里的url. */
	public static final String SYSTEM_PARAM_KEY__MANUALRECOVERY_EMAIL_URL = "PARAM_VALUE_FINDPASSWORD_RETURN_URL";

	/** 系统参数表key - 门户用户登录失败时间范围. */
	public static final String SYSTEM_PARAM_KEY__LOGIN_FAILURE_LOCK_TIME = "PARAM_VALUE_LOGIN_LOCK_TIME";

	/** 默认值 - 门户用户登录失败时间范围(单位: 分). */
	public static final int DEFAULT_LOGIN_FAILURE_LOCK_TIME = 20;

	/** 系统参数表key - 门户用户登录失败次数. */
	public static final String SYSTEM_PARAM_KEY__LOGIN_FAILURE_LOCK_COUNT = "PARAM_VALUE_CAN_LOGIN_FAIL_TIME";

	/** 默认值 - 门户用户登录失败次数. */
	public static final long DEFAULT_LOGIN_FAILURE_LOCK_COUNT = 3L;

}