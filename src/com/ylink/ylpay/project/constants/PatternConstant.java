package com.ylink.ylpay.project.constants;

import com.opensymphony.xwork2.validator.validators.EmailValidator;

/**
 * 正则表达式常量类.
 * 
 * @author 潘瑞峥
 * @date 2012-10-29
 */
public final class PatternConstant {

	private PatternConstant() {
	}

	/** Email. */
	public static final String EMAIL_PATTERN = EmailValidator.EMAIL_ADDRESS_PATTERN;
	public static final String EMAIL_ERROR_MSG = "必须为邮箱！";

	/** 手机: 11位数字. */
	public static final String MOBILE_PATTERN = "\\d{11}";
	public static final String MOBILE_ERROR_MSG = "手机号必须为11位的数字！";

	/** 精简日期. */
	public static final String DATE_SHORT = "yyyyMMdd";

}