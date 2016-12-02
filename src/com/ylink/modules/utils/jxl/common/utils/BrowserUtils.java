package com.ylink.modules.utils.jxl.common.utils;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.ylink.modules.utils.jxl.common.constants.JxlConstant;

/**
 * 浏览器工具类.
 * 
 * @author 潘瑞峥
 * @date 2012-8-1
 */
public class BrowserUtils {

	/**
	 * 通过浏览器类型，返回浏览器支持的中文 ( IE Chrome Mozilla Safari Opera )
	 * 
	 * @param content
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String encodeContent( String content, HttpServletRequest request ) throws Exception {
		String userAgent = request.getHeader( JxlConstant.USER_AGENT ).toUpperCase();
		if ( StringUtils.isNotBlank( userAgent ) && StringUtils.isNotBlank( content ) ) {
			/* IE */
			if ( -1 != userAgent.indexOf( JxlConstant.BROWSER_MSIE.toUpperCase() ) )
				return URLEncoder.encode( content, JxlConstant.UTF_ENCODING );
			/* Chrome */
			else if ( -1 != userAgent.indexOf( JxlConstant.BROWSER_CHROME.toUpperCase() ) )
				return new String( content.getBytes(), JxlConstant.ISO_ENCODING );
			/* Mozilla */
			else if ( -1 != userAgent.indexOf( JxlConstant.BROWSER_MOZILLA.toUpperCase() ) )
				return new String( content.getBytes(), JxlConstant.ISO_ENCODING );
			/* Safari */
			else if ( -1 != userAgent.indexOf( JxlConstant.BROWSER_SAFARI.toUpperCase() ) )
				return new String( content.getBytes(), JxlConstant.ISO_ENCODING );
			/* Opera */
			else if ( -1 != userAgent.indexOf( JxlConstant.BROWSER_OPERA.toUpperCase() ) )
				return new String( content.getBytes(), JxlConstant.ISO_ENCODING );
			/* 其它内核浏览器 */
			else
				return new String( content.getBytes(), JxlConstant.ISO_ENCODING );
		}
		return content;
	}

}