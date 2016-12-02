package com.ylink.modules.utils.export.common.constants;

public class ExportConstant {

	/** 配置文件路径 */
	// public static final String CONFIG_PATH = "classpath:/config/io/export.properties";

	/** 字段间分隔符key */
	public static final String SEPARATOR_FIELD_KEY = "export.separator.field";

	/** 行间分隔符key */
	public static final String SEPARATOR_LINE_KEY = "export.separator.line";

	/** 前补、居中或后补key */
	public static final String PAD_KEY = "export.pad";

	/**
	 * 下载等信息参数
	 */
	public static final String UTF_ENCODING = "UTF-8";
	public static final String ISO_ENCODING = "ISO8859-1";
	public static final String HEADER = "Content-Disposition";

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

}