package com.ylink.ylpay.project.constants;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;

import com.ylink.modules.bean.ExportConfigBean;
import com.ylink.modules.bean.ImportConfigBean;
import com.ylink.modules.utils.PropertiesLoader;
import com.ylink.ylpay.common.project.mp.constant.WithdrawExportDetailPayResult;

/**
 * 提现导出Config.
 * 
 * @author 潘瑞峥
 * @date 2012-12-7
 */
public class WithdrawConfig {

	/** 默认缓冲分页数. */
	public static final int DEFAULT_PAGE_BUFFER = 50;

	/** 响应码字段. */
	public static final String PAY_RESULT = "payResult";

	/** 若配置文件不存在最大记录数, 则使用该记录数. */
	public static final int MAX_LINE = 2000;

	/** 导出配置Bean. */
	public static final Map<MultiKey, ExportConfigBean> exportConfig = new HashMap<MultiKey, ExportConfigBean>();

	/** 导入配置Bean. */
	public static final Map<MultiKey, ImportConfigBean> importConfig = new HashMap<MultiKey, ImportConfigBean>();

	/** 导出配置Bean. */
	private static ExportConfigBean exportConfigBean = null;

	/** 导入配置Bean. */
	private static ImportConfigBean importConfigBean = null;

	/** 导出最大记录数. */
	public static final String EXPORT_MAX_LINE_KEY_SUFFIX = ".export.maxLine";

	/** 字符集key的后缀. */
	private static final String EXPORT_ENCODING_KEY_SUFFIX = ".export.encoding";

	/** 分隔符key的后缀. */
	private static final String EXPORT_SEPARATOR_KEY_SUFFIX = ".export.separator";
	
	/** 换行符key的后缀. */
	private static final String EXPORT_NEWLINE_KEY_SUFFIX = ".export.newline";

	/** 需要导出的字段key的后缀. */
	private static final String EXPORT_FIELD_KEY_SUFFIX = ".export.field";
	/** 文件头 */
	private static final String EXPORT_TITLE_KEY_SUFFIX = ".export.title";
	/** 文件头信息 */
	private static final String EXPORT_TITLEINFO_KEY_SUFFIX = ".export.titleinfo";
	/** 文件尾信息 */
	private static final String EXPORT_ENDINFO_KEY_SUFFIX = ".export.endinfo";
	/** 联行号信息 */
	private static final String EXPORT_CORRESPONDENT_KEY_SUFFIX = ".export.correspondent";
	
	private static final String EXPORT_ISSEPARATOR_KEY_SUFFIX = ".export.isSeparator";
	
	/**正式数据开始行. */
	private static final String EXPORT_DATEFORMAT_KEY_SUFFIX = ".export.dateFormat";
	
	/** 字符集key的后缀. */
	private static final String IMPORT_ENCODING_KEY_SUFFIX = ".import.encoding";

	/** 分隔符key的后缀. */
	private static final String IMPORT_SEPARATOR_KEY_SUFFIX = ".import.separator";

	/** 需要导出的字段key的后缀. */
	private static final String IMPORT_FIELD_KEY_SUFFIX = ".import.field";

	/** 响应码字段key的后缀. */
	private static final String IMPORT_RESULT_KEY_SUFFIX = ".import.payResult";
	
	/**正式数据开始行. */
	private static final String IMPORT_START_KEY_SUFFIX = ".import.startLine";
	
	/** 字段分隔符. */
	private static final String FILED_SEPARATOR = ";";

	/** 响应分隔符. */
	private static final String RESULT_SEPARATOR = ":";

	/** 配置文件. */
	private static PropertiesLoader propertiesLoader = null;

	/** 配置文件. */
	private static Properties properties = null;

	/** 临时key. */
	private static Set<String> bankTypes = null;

	/** 临时的map的key. 第一个key: 行别, 第二个key: WithdrawType枚举的value. */
	private static MultiKey multiKey = null;

	static {
		for ( WithdrawType withdrawType : WithdrawType.values() ) {
			init( withdrawType );
		}
	}

	/**
	 * 读取配置文件并保存在map里.
	 * 
	 * @param withdrawType
	 */
	public static void init( WithdrawType withdrawType ) {

		/* 导出key. */
		String exportEncodingKey;
		String exportSeparatorKey;
		String exportNewlineKey;
		String exportFieldKey;
		String exportTitleKey;
		String exportTitelInfoKey;
		String exportEndInfoKey;
		String exportCorrespondentKey;
		String exportisSeparatorKey;
		String exportDateFormatKey;
		/* 导入key. */
		String importEncodingKey;
		String importSeparatorKey;
		String importFieldKey;
		String importResultKey;
		String importStartLineKey;

		/* 导出value. */
		String exportEncoding;
		String exportSeparator;
		String exportNewline;
		String exportTitleInfo;
		String exportEndInfo;
		String exportCorrespondent;
		Boolean exportisSeparator = null;
		String[] exportField;
		String[] exportTitle;
		String exportDateFormat;

		/* 导入value. */
		String importEncoding;
		String importSeparator;
		String importFields;
		int importFieldSize;
		String[] importField;
		String importResult;
		String importStartLine;

		/*
		 * 1.读取配置文件.
		 */
		propertiesLoader = new PropertiesLoader( withdrawType.getConfigPath() );
		properties = propertiesLoader.getProperties();

		/*
		 * 2.遍历所有的key. 将第一个"."前的行别号作为key. 并保存在bankTypes里.
		 */
		Set<String> names = properties.stringPropertyNames();
		bankTypes = new HashSet<String>();
		for ( String name : names ) {
			bankTypes.add( StringUtils.split( name, "." )[ 0 ] );
		}

		/*
		 * 3.循环bankTypes.
		 */
		for ( String bankType : bankTypes ) {

			// key.
			multiKey = new MultiKey( bankType, withdrawType.getValue() );

			/*
			 * 4.拼接各参数完整的key.
			 */
			exportEncodingKey = new StringBuffer( bankType ).append( EXPORT_ENCODING_KEY_SUFFIX ).toString();
			exportSeparatorKey = new StringBuffer( bankType ).append( EXPORT_SEPARATOR_KEY_SUFFIX ).toString();
			exportNewlineKey = new StringBuffer( bankType ).append( EXPORT_NEWLINE_KEY_SUFFIX ).toString();
			exportFieldKey = new StringBuffer( bankType ).append( EXPORT_FIELD_KEY_SUFFIX ).toString();
			exportTitleKey = new StringBuffer(bankType).append(EXPORT_TITLE_KEY_SUFFIX).toString();
			exportTitelInfoKey = new StringBuffer(bankType).append(EXPORT_TITLEINFO_KEY_SUFFIX).toString();
			exportEndInfoKey = new StringBuffer(bankType).append(EXPORT_ENDINFO_KEY_SUFFIX).toString();
			exportCorrespondentKey = new StringBuffer(bankType).append(EXPORT_CORRESPONDENT_KEY_SUFFIX).toString();
			exportisSeparatorKey = new StringBuffer(bankType).append(EXPORT_ISSEPARATOR_KEY_SUFFIX).toString();
			exportDateFormatKey = new StringBuffer(bankType).append(EXPORT_DATEFORMAT_KEY_SUFFIX).toString();
			importEncodingKey = new StringBuffer( bankType ).append( IMPORT_ENCODING_KEY_SUFFIX ).toString();
			importSeparatorKey = new StringBuffer( bankType ).append( IMPORT_SEPARATOR_KEY_SUFFIX ).toString();
			importFieldKey = new StringBuffer( bankType ).append( IMPORT_FIELD_KEY_SUFFIX ).toString();
			importResultKey = new StringBuffer( bankType ).append( IMPORT_RESULT_KEY_SUFFIX ).toString();
			importStartLineKey = new StringBuffer( bankType).append( IMPORT_START_KEY_SUFFIX).toString();
			/*
			 * 5.若存在导出这些key, 则读取key对应的value.
			 */
			if ( properties.containsKey( exportEncodingKey ) && properties.containsKey( exportSeparatorKey )
					&& properties.containsKey( exportNewlineKey ) && properties.containsKey( exportFieldKey ) ) {
				exportEncoding = propertiesLoader.getProperty( exportEncodingKey, CharEncoding.UTF_8 );
				exportSeparator = propertiesLoader.getProperty( exportSeparatorKey );
				exportNewline = propertiesLoader.getProperty( exportNewlineKey );
				exportTitleInfo = propertiesLoader.getProperty(exportTitelInfoKey);
				exportEndInfo = propertiesLoader.getProperty(exportEndInfoKey);
				exportCorrespondent = propertiesLoader.getProperty(exportCorrespondentKey);
				exportDateFormat = propertiesLoader.getProperty(exportDateFormatKey);
				if(StringUtils.isNotBlank(propertiesLoader.getProperty(exportisSeparatorKey)))
					exportisSeparator = Boolean.valueOf(propertiesLoader.getProperty(exportisSeparatorKey));
				exportField = StringUtils.split( propertiesLoader.getProperty( exportFieldKey ), FILED_SEPARATOR );
				exportTitle = StringUtils.split(propertiesLoader.getProperty(exportTitleKey),FILED_SEPARATOR);
				/*
				 * 6.将各value保存在导出bean里, 并保存在map里.
				 */
				exportConfigBean = new ExportConfigBean( multiKey, exportEncoding, exportSeparator, exportNewline,exportTitleInfo,exportEndInfo, exportCorrespondent,exportisSeparator,exportField ,exportTitle);
				exportConfigBean.setDateFormat(exportDateFormat);
				// 将配置Bean加入到Map里.
				exportConfig.put( multiKey, exportConfigBean );
			}

			/*
			 * 7.若存在导入这些key, 则读取key对应的value.
			 */
			if ( properties.containsKey( importEncodingKey ) && properties.containsKey( importSeparatorKey )
					&& properties.containsKey( importFieldKey ) && properties.containsKey( importResultKey ) ) {
				importEncoding = propertiesLoader.getProperty( importEncodingKey, CharEncoding.UTF_8 );
				importSeparator = propertiesLoader.getProperty( importSeparatorKey );
				importFields = propertiesLoader.getProperty( importFieldKey );
				importFieldSize = StringUtils.countMatches( importFields, FILED_SEPARATOR );
				importField = StringUtils.split( importFields, FILED_SEPARATOR );
				importResult = propertiesLoader.getProperty( importResultKey );
				importStartLine = propertiesLoader.getProperty( importStartLineKey ); 
				/*
				 * 8.将各value保存在导入bean里, 并保存在map里.
				 */
				importConfigBean = new ImportConfigBean( multiKey, importEncoding, importSeparator, importFieldSize, importField );
				if(importStartLine !=null && importStartLine.length() > 0)
				importConfigBean.setStartLine(Integer.parseInt(importStartLine));
				/* 成功失败标志. */
				if ( StringUtils.isNotBlank( importResult ) ) {
					String[] payResults = StringUtils.split( importResult, FILED_SEPARATOR );
					String[] results;
					for ( String result : payResults ) {
						results = StringUtils.split( result, RESULT_SEPARATOR );
						String value = results[ 1 ];
						if("Y".equals(value)){
							value = "SUCCESS";
						}else if("N".equals(value)){
							value = "FAILURE";
						}
						// 转换为枚举.
						WithdrawExportDetailPayResult resultEnum = WithdrawExportDetailPayResult.valueOf( value );
						importConfigBean.getResultMap().put( results[ 0 ], resultEnum );
					}
				}

				// 将配置Bean加入到Map里.
				importConfig.put( multiKey, importConfigBean );
			}
		}
	}

}