package com.ylink.ylpay.project.utils;

import com.ylink.ylpay.project.constants.EmailConstant;

/**
 * 邮件工具类.
 * 
 * @author 潘瑞峥
 * @date 2013-1-6
 */
public class EmailUtils {

	/**
	 * 构建邮件内容.
	 * 
	 * @param userName
	 * @param content
	 * @return
	 */
	public static String buildContent( String userName, String content ) {
		StringBuffer buffer = new StringBuffer();
		buffer.append( EmailConstant.email_content1 ).append( userName );
		buffer.append( EmailConstant.email_content2 );
		buffer.append( content );
		buffer.append( EmailConstant.email_content3 ).append( EmailConstant.email_content4 ).append( EmailConstant.email_content5 );

		return buffer.toString();
	}

}