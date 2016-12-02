package com.ylink.modules.utils.web.struts2;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.ylink.modules.utils.export.common.constants.ExportEnum;
import com.ylink.modules.utils.web.ServletUtils;

/**
 * 针对Struts2的工具类.
 * 
 * @author 潘瑞峥
 * @date 2012-5-8
 */
public class StrutsUtils {

	public static final String HEADER = "Content-Disposition";

	public static final String ISO_ENCODING = "ISO8859-1";

	/** 打开文件 */
	public static final String RESPONSE_HEADER_OPEN = "inline;filename=";

	/** 下载文件 */
	public static final String RESPONSE_HEADER_DOWN = "attachment;filename=";

	/** Content Type 定义 */
	public static final String JSON_TYPE = "application/json";
	public static final String TEXT_TYPE = "text/plain";
	public static final String XML_TYPE = "text/xml";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
	public static final String EXCEL_TYPE = "application/vnd.ms-excel";

	private static final String HEADER_ENCODING = "encoding";
	private static final String HEADER_NOCACHE = "no-cache";
	/** charset 编码格式 */
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final boolean DEFAULT_NOCACHE = true;

	/**
	 * 下载.
	 * 
	 * @param encoding
	 * @param fileName
	 * @param content
	 * @param fileType
	 */
	public static void download( final String encoding, final String fileName, final String content, final ExportEnum.FileType fileType ) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		initResponseHeader( response, fileType.getContentType() );
		try {
			String encodedfileName = ServletUtils.encodeContent( request, fileName );
			response.setHeader( HEADER, RESPONSE_HEADER_DOWN + encodedfileName + "." + fileType.name() );

			/* 字符集. */
			if ( StringUtils.isNotBlank( encoding ) ) {
				response.setCharacterEncoding( encoding );
			}

			response.getWriter().write( content );
			response.getWriter().flush();
		} catch ( IOException e ) {
			throw new RuntimeException( e.getMessage(), e );
		}
	}

	/**
	 * 下载.
	 * 
	 * @param encoding
	 * @param fileName
	 * @param binary
	 * @param fileType
	 */
	public static void download( final String encoding, final String fileName, final byte[] binary, final ExportEnum.FileType fileType ) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		initResponseHeader( response, fileType.getContentType() );
		try {
			String encodedfileName = ServletUtils.encodeContent( request, fileName );
			response.setHeader( HEADER, RESPONSE_HEADER_DOWN + encodedfileName + "." + fileType.name() );

			/* 字符集. */
			if ( StringUtils.isNotBlank( encoding ) ) {
				response.setCharacterEncoding( encoding );
			}

			response.getOutputStream().write( binary );
			response.getOutputStream().flush();
		} catch ( IOException e ) {
			throw new RuntimeException( e.getMessage(), e );
		}
	}

	/**
	 * 下载.
	 * 
	 * @param fileName
	 * @param content
	 * @param fileType
	 */
	public static void download( final String fileName, final String content, final ExportEnum.FileType fileType ) {
		download( null, fileName, content, fileType );
	}

	/**
	 * 下载.
	 * 
	 * @param fileName
	 * @param binary
	 * @param fileType
	 */
	public static void download( final String fileName, final byte[] binary, final ExportEnum.FileType fileType ) {
		download( null, fileName, binary, fileType );
	}

	/**
	 * 在线打开.
	 * 
	 * @param fileName
	 * @param content
	 * @param fileType
	 */
	public static void open( final String fileName, final String content, final ExportEnum.FileType fileType ) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		initResponseHeader( response, fileType.getContentType() );
		try {
			String encodedfileName = ServletUtils.encodeContent( request, fileName );
			response.setHeader( HEADER, RESPONSE_HEADER_OPEN + encodedfileName );

			response.getWriter().write( content );
			response.getWriter().flush();
		} catch ( IOException e ) {
			throw new RuntimeException( e.getMessage(), e );
		}
	}

	/**
	 * 直接输出内容的简便函数.
	 * 
	 * eg. render("text/plain", "hello", "encoding:GBK"); render("text/plain", "hello",
	 * "no-cache:false"); render("text/plain", "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @param headers
	 *            可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void render( final HttpServletResponse response, final String contentType, final String content, final String... headers ) {
		initResponseHeader( response, contentType, headers );
		try {
			response.getWriter().write( content );
			response.getWriter().flush();
		} catch ( IOException e ) {
			throw new RuntimeException( e.getMessage(), e );
		}
	}

	/**
	 * 传入字符串，输出JSON.
	 * 
	 * @param jsonString
	 */
	public static void renderJson( String jsonString ) {
		HttpServletResponse response = initResponseHeader();
		try {
			response.getWriter().write( jsonString );
			response.getWriter().flush();
		} catch ( IOException e ) {
			throw new RuntimeException( e.getMessage(), e );
		}
	}

	/**
	 * 设置contentType、headers.
	 * 
	 * @param response
	 * @return
	 */
	private static HttpServletResponse initResponseHeader() {
		HttpServletResponse response = ServletActionContext.getResponse();

		String fullContentType = JSON_TYPE + ";charset=" + DEFAULT_ENCODING;
		response.setContentType( fullContentType );
		// Http 1.0 header
		response.setDateHeader( "Expires", 1L );
		response.addHeader( "Pragma", "no-cache" );
		// Http 1.1 header
		response.setHeader( "Cache-Control", "no-cache, no-store, max-age=0" );
		return response;
	}

	/**
	 * 分析并设置contentType与headers.
	 */
	private static void initResponseHeader( HttpServletResponse response, final String contentType, final String... headers ) {
		// 分析headers参数
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		for ( String header : headers ) {
			String headerName = StringUtils.substringBefore( header, ":" );
			String headerValue = StringUtils.substringAfter( header, ":" );

			if ( StringUtils.equalsIgnoreCase( headerName, HEADER_ENCODING ) ) {
				encoding = headerValue;
			} else if ( StringUtils.equalsIgnoreCase( headerName, HEADER_NOCACHE ) ) {
				noCache = Boolean.parseBoolean( headerValue );
			} else {
				throw new IllegalArgumentException( headerName + "不是一个合法的header类型" );
			}
		}

		// 设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType( fullContentType );
		if ( noCache ) {
			ServletUtils.setDisableCacheHeader( response );
		}
	}
	/**
	 * 光大银行清算通知文件下载.
	 * 
	 * @param encoding
	 * @param fileName
	 * @param binary
	 * @param fileType
	 */
	public static void download( final String encoding, final String fileName, final byte[] binary, final String fileType ) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String encodedfileName = ServletUtils.encodeContent( request, fileName );
			response.setHeader( HEADER, RESPONSE_HEADER_DOWN + encodedfileName + "." + fileType );

			/* 字符集. */
			if ( StringUtils.isNotBlank( encoding ) ) {
				response.setCharacterEncoding( encoding );
			}

			response.getOutputStream().write( binary );
			response.getOutputStream().flush();
		} catch ( IOException e ) {
			throw new RuntimeException( e.getMessage(), e );
		}
	}

}