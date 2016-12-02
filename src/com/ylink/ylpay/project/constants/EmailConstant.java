package com.ylink.ylpay.project.constants;

import com.ylink.modules.utils.PropertiesLoader;

/**
 * 邮件常量类.
 * 
 * @author 潘瑞峥
 * @date 2013-1-6
 */
public final class EmailConstant {

	public static String email_content1;

	public static String email_content2;

	public static String email_content3;

	public static String email_content4;

	public static String email_content5;

	private static final String CONFIG_PATH = "classpath:config/constant/email.properties";

	private static final String EMAIL_CONTENT_KEY1 = "email.content.c1";

	private static final String EMAIL_CONTENT_KEY2 = "email.content.c2";

	private static final String EMAIL_CONTENT_KEY3 = "email.content.c3";

	private static final String EMAIL_CONTENT_KEY4 = "email.content.c4";

	private static final String EMAIL_CONTENT_KEY5 = "email.content.c5";

	static {
		PropertiesLoader propertiesLoader = new PropertiesLoader( CONFIG_PATH );

		email_content1 = propertiesLoader.getProperty( EMAIL_CONTENT_KEY1 );
		email_content2 = propertiesLoader.getProperty( EMAIL_CONTENT_KEY2 );
		email_content3 = propertiesLoader.getProperty( EMAIL_CONTENT_KEY3 );
		email_content4 = propertiesLoader.getProperty( EMAIL_CONTENT_KEY4 );
		email_content5 = propertiesLoader.getProperty( EMAIL_CONTENT_KEY5 );
	}

}