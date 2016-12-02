package com.ylink.modules.utils.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * Http与Servlet工具类.
 * 
 * @author 潘瑞峥
 */
public class ServletUtils {

	public static final String UTF_ENCODING = "UTF-8";

	public static final String ISO_ENCODING = "ISO8859-1";

	/** header 浏览器key */
	public static final String USER_AGENT = "USER-AGENT";

	/** header IE */
	public static final String BROWSER_MSIE = "MSIE";

	/** header Chrome */
	public static final String BROWSER_CHROME = "CHROME";

	/** header Mozilla */
	public static final String BROWSER_MOZILLA = "MOZILLA";

	/** header Safari */
	public static final String BROWSER_SAFARI = "SAFARI";

	/** header Opera */
	public static final String BROWSER_OPERA = "OPERA";

	/**
	 * 取得带相同前缀的Request Parameters.
	 * 
	 * 返回的结果的Parameter名已去除前缀.
	 */
	public static Map<String, Object> getParametersStartingWith( ServletRequest request, String prefix ) {
		Assert.notNull( request, "Request must not be null" );
		Enumeration<?> paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if ( prefix == null ) {
			prefix = "";
		}
		while ( paramNames != null && paramNames.hasMoreElements() ) {
			String paramName = ( String ) paramNames.nextElement();
			if ( "".equals( prefix ) || paramName.startsWith( prefix ) ) {
				String unprefixed = paramName.substring( prefix.length() );
				String[] values = request.getParameterValues( paramName );
				if ( values == null || values.length == 0 ) {
					// Do nothing, no values found at all.
				} else if ( values.length > 1 ) {
					params.put( unprefixed, values );
				} else {
					params.put( unprefixed, values[ 0 ] );
				}
			}
		}
		return params;
	}

	/**
	 * 设置禁止客户端缓存的Header.
	 */
	public static void setDisableCacheHeader( HttpServletResponse response ) {
		// Http 1.0 header
		response.setDateHeader( "Expires", 1L );
		response.addHeader( "Pragma", "no-cache" );
		// Http 1.1 header
		response.setHeader( "Cache-Control", "no-cache, no-store, max-age=0" );
	}

	/**
	 * 通过浏览器类型，返回浏览器支持的中文 ( IE Chrome Mozilla Safari Opera ).
	 * 
	 * @param request
	 * @param content
	 * @return
	 */
	public static String encodeContent( HttpServletRequest request, String content ) {
		try {
			String userAgent = request.getHeader( USER_AGENT ).toUpperCase();
			if ( StringUtils.isNotBlank( userAgent ) && StringUtils.isNotBlank( content ) ) {
				/* IE */
				if ( -1 != userAgent.indexOf( BROWSER_MSIE.toUpperCase() ) )
					return URLEncoder.encode( content, UTF_ENCODING );
				else if ( -1 != userAgent.indexOf( BROWSER_CHROME.toUpperCase() ) )
					return new String( content.getBytes(), ISO_ENCODING );
				/* Mozilla */
				else if ( -1 != userAgent.indexOf( BROWSER_MOZILLA.toUpperCase() ) )
					return new String( content.getBytes(), ISO_ENCODING );
				/* Safari */
				else if ( -1 != userAgent.indexOf( BROWSER_SAFARI.toUpperCase() ) )
					return new String( content.getBytes(), ISO_ENCODING );
				/* Opera */
				else if ( -1 != userAgent.indexOf( BROWSER_OPERA.toUpperCase() ) )
					return new String( content.getBytes(), ISO_ENCODING );
				/* 其它内核浏览器 */
				else
					return new String( content.getBytes(), ISO_ENCODING );
			}
			return content;
		} catch ( UnsupportedEncodingException e ) {
			return "";
		}
	}

}