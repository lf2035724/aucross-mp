package com.ylink.ylpay.project.sms.constant;

import com.ylink.modules.utils.PropertiesLoader;

/**
 * 常量类.
 * 
 * @author 潘瑞峥
 * @date 2012-11-7
 */
public final class Constant {

	/** 短信发送表. */
	public static final String SMS_SEND_TABLE_NAME = "tab_ms_send";

	/** 短信接收表. */
	public static final String SMS_RECEIVED_TABLE_NAME = "tab_ms_smreceived";

	/** 短信配置文件路径. */
	public static final String SMS_CONFIG_PATH = "classpath:/config/sms.properties";

	/** 短信主叫的配置文件Key. */
	public static final String SMS_CALLING_PARTY_KEY = "constant.callingparty";

	/**SMS平台的编号Key. */
	public static final String SMS_CREATOR_ID_KEY = "constant.creatorid";

	/** SMS平台默认的业务编号Key. */
	public static final String SMS_BUSINESS_TYPE_KEY = "constant.businesstype";

	/** 短信内容后缀Key. */
	public static final String SMS_CONTENT_SUFFIX_KEY = "constant.content.suffix";

	/** 短信主叫手机号. */
	public static String smsCallingPartyMobile;

	/**在SMS平台的编号. */
	public static String smsCreatorId;

	/** SMS平台默认的业务编号. */
	public static String smsBusinessType;

	/** 短信内容后缀. */
	public static String smsContentSuffix;

	/** 短信配置文件. */
	private static PropertiesLoader smsPropertiesLoader = null;

	/** 短信主叫的手机号默认值, 若未从配置文件里读取到, 则返回该值, 有值则以配置文件里的值为准(防止误删配置文件). */
	private static final String DEFAULT_SMS_CALLING_PARTY_MOBILE = "106575660466";

	/**SMS平台的编号默认值, 若未从配置文件里读取到, 则返回该值, 有值则以配置文件里的值为准(防止误删配置文件). */
	private static final String DEFAULT_SMS_CREATOR_ID = "5500";

	/** SMS平台默认的业务编号默认值, 若未从配置文件里读取到, 则返回该值, 有值则以配置文件里的值为准(防止误删配置文件). */
	private static final String DEFAULT_SMS_BUSINESS_TYPE = "01";

	/** 短信内容后缀默认值, 若未从配置文件里读取到, 则返回该值, 有值则以配置文件里的值为准(防止误删配置文件). */
	private static final String DEFAULT_SMS_CONTENT_SUFFIX = "[证联融通]";
	/**梦网短信接口用户名*/
	public static String DREAMNET_UESRNAME;
	/**梦网短信接口用户密码*/
	public static String DREAMNET_PASSWORD;
	/**梦网短信接口 扩展号码*/
	public static String DREAMNET_SUBPORT;
	/**梦网短信接口用户名 配置文件名称*/
	public static final String DREAMNET_UESRNAME_KEY="dreamnet.username";
	/**梦网短信接口用户密码 配置文件名称*/
	public static final String DREAMNET_PASSWORD_KEY="dreamnet.password";
	/**梦网短信接口 扩展号码 配置文件名称*/
	public static final String DREAMNET_SUBPORT_KEY="dreamnet.subPort";
	/**梦网短信接口 wsdl接口 配置文件名称*/
	public static final String DREAMNET_SERVERURL_KEY="dreamnet.serverurl";
	/**梦网短信接口用户名 默认值*/
	public static final String DREAMNET_UESRNAME_DEFAULT="J21529";
	/**梦网短信接口用户密码 默认值*/
	public static final String DREAMNET_PASSWORD_DEFAULT="635212";
	/**梦网短信接口 扩展号码 默认值“*”*/
	public static final String DREAMNET_SUBPORT_DEFAULT="*";
	static {
		smsPropertiesLoader = new PropertiesLoader( SMS_CONFIG_PATH );
		smsCallingPartyMobile = smsPropertiesLoader.getProperty( SMS_CALLING_PARTY_KEY, DEFAULT_SMS_CALLING_PARTY_MOBILE );
		smsCreatorId = smsPropertiesLoader.getProperty( SMS_CREATOR_ID_KEY, DEFAULT_SMS_CREATOR_ID );
		smsBusinessType = smsPropertiesLoader.getProperty( SMS_BUSINESS_TYPE_KEY, DEFAULT_SMS_BUSINESS_TYPE );
		smsContentSuffix = smsPropertiesLoader.getProperty( SMS_CONTENT_SUFFIX_KEY, DEFAULT_SMS_CONTENT_SUFFIX );
		
		DREAMNET_UESRNAME = smsPropertiesLoader.getProperty( DREAMNET_UESRNAME_KEY, DREAMNET_UESRNAME_DEFAULT );
		DREAMNET_PASSWORD = smsPropertiesLoader.getProperty( DREAMNET_PASSWORD_KEY, DREAMNET_PASSWORD_DEFAULT );
		DREAMNET_SUBPORT = smsPropertiesLoader.getProperty( DREAMNET_SUBPORT_KEY, DREAMNET_SUBPORT_DEFAULT );
	}

	private Constant() {
	}

}