package com.ylink.modules.utils.jxl;

import java.io.Serializable;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ylink.modules.utils.jxl.common.config.JxlConfig;
import com.ylink.modules.utils.jxl.common.constants.JxlConstant;
import com.ylink.modules.utils.jxl.common.utils.BrowserUtils;

/**
 * WEB Excel生成.
 * 
 * @author 潘瑞峥
 * @date 2012-8-1
 */
public class JxlWebBuilder {

	private static JxlBuilder jxlBuilder = new JxlBuilder();

	/**
	 * 生成Excel, 并下载
	 * 
	 * @param fileName
	 * @param dataSource
	 * @param configs
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void buildExcel( String fileName, Collection<? extends Serializable> dataSource, Collection<JxlConfig> configs,
			HttpServletRequest request, HttpServletResponse response ) throws Exception {
		// sheet的名字，fileName会改变编码格式，所以先复制个
		String sheetName = new String( fileName );
		// 转换为当前浏览器支持的中文
		fileName = BrowserUtils.encodeContent( fileName, request );
		response.setCharacterEncoding( JxlConstant.UTF_ENCODING );
		response.setContentType( JxlConstant.CONTENT_TYPE );
		response.setHeader( JxlConstant.HEADER, responseHeaderDown( fileName ) );
		jxlBuilder.createWorkbook( response.getOutputStream(), sheetName, dataSource, configs );
	}

	/**
	 * 生成Excel, 并下载
	 * 
	 * @param fileName
	 * @param dataSource
	 * @param configs
	 * @throws Exception
	 */
	public static void buildExcel( String fileName, Collection<? extends Serializable> dataSource, Collection<JxlConfig> configs )
			throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		buildExcel( fileName, dataSource, configs, request, response );
	}

	/**
	 * 下载响应
	 * 
	 * @param fileName
	 * @return
	 */
	private static String responseHeaderDown( String fileName ) {
		return new StringBuffer( JxlConstant.RESPONSE_HEADER_DOWN ).append( fileName ).append( JxlConstant.EXCEL_SUFFIX ).toString();
	}

	/**
	 * 在线打开响应
	 * 
	 * @param fileName
	 * @return
	 */
	@SuppressWarnings( "unused" )
	private static String responseHeaderOpen( String fileName ) {
		return new StringBuffer( JxlConstant.RESPONSE_HEADER_OPEN ).append( fileName ).append( JxlConstant.EXCEL_SUFFIX ).toString();
	}

}