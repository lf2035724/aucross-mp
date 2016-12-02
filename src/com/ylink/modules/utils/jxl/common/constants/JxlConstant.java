package com.ylink.modules.utils.jxl.common.constants;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;

/**
 * 配置常量类.
 * 
 * @author 潘瑞峥
 * @date 2012-8-1
 */
public final class JxlConstant {

	private JxlConstant() {
	}

	/** x起始值 */
	public static final int X_DEFUALT_BEGIN = 0;

	/** y起始值 */
	public static final int Y_DEFUALT_BEGIN = 0;

	/** 默认宽度 */
	public static final int DEFAULT_CELL_WIDTH = 20;

	/** 字体大小 */
	public static final int DEFAULT_FONT_SIZE = 11;

	/** 默认字体 */
	public static final String DEFAULT_FONT_NAME = "Arial";

	/**
	 * jxl的对齐方式
	 */
	public static final Alignment CENTER = Alignment.CENTRE;
	public static final Alignment LEFT = Alignment.LEFT;
	public static final Alignment RIGHT = Alignment.RIGHT;
	public static final Alignment FILL = Alignment.FILL;
	public static final Alignment GENERAL = Alignment.GENERAL;
	public static final Alignment JUSTIFY = Alignment.JUSTIFY;

	/**
	 * jxl下载等信息参数
	 */
	public static final String UTF_ENCODING = "UTF-8";
	public static final String ISO_ENCODING = "ISO8859-1";
	public static final String CONTENT_TYPE = "application/vnd.ms-excel";
	public static final String HEADER = "Content-Disposition";

	/**
	 * 边框位置
	 */
	public static final Border BORDER_ALL = Border.ALL;
	public static final Border BORDER_TOP = Border.TOP;
	public static final Border BORDER_BOTTOM = Border.BOTTOM;
	public static final Border BORDER_LEFT = Border.LEFT;
	public static final Border BORDER_RIGHT = Border.RIGHT;
	public static final Border BORDER_NONE = Border.NONE;

	/**
	 * 边框样式
	 */
	public static final BorderLineStyle BORDER_LINE_THICK = BorderLineStyle.THICK;
	public static final BorderLineStyle BORDER_LINE_THIN = BorderLineStyle.THIN;

	/** Integer类型NumberFormat */
	public static final String FORMAT_INTEGER = "0";

	/** Double类型NumberFormat */
	public static final String FORMAT_DOUBLE = "0.00";

	/** 打开文件 */
	public static final String RESPONSE_HEADER_OPEN = "inline;filename=";

	/** 下载文件 */
	public static final String RESPONSE_HEADER_DOWN = "attachment;filename=";

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

	/** Excel文件后缀 */
	public static final String EXCEL_SUFFIX = ".xls";

}