package com.ylink.modules.bean;

import java.util.HashMap;
import java.util.Map;

import com.ylink.ylpay.common.project.mp.constant.WithdrawExportDetailPayResult;

/**
 * 导入文件配置文件Bean.
 * 
 * @author 潘瑞峥
 * @date 2012-12-11
 */
public class ImportConfigBean {

	/** key. */
	private Object key;

	/** 字符集. */
	private String encoding;

	/** 分隔符. */
	private String separator;

	/** 长度. */
	private int size;

	/** 需要导出的字段. */
	private String[] fields;
	
	/** 正式数据起始行. */
	private int startLine = 0;

	/** 响应码对应枚举. */
	private Map<String, WithdrawExportDetailPayResult> resultMap = new HashMap<String, WithdrawExportDetailPayResult>();

	public ImportConfigBean() {
	}

	public ImportConfigBean( Object key, String encoding, String separator, int size, String[] fields ) {
		this.key = key;
		this.encoding = encoding;
		this.separator = separator;
		this.size = size;
		this.fields = fields;
	}

	public Object getKey() {
		return key;
	}

	public void setKey( Object key ) {
		this.key = key;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding( String encoding ) {
		this.encoding = encoding;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator( String separator ) {
		this.separator = separator;
	}

	public int getSize() {
		return size;
	}

	public void setSize( int size ) {
		this.size = size;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields( String[] fields ) {
		this.fields = fields;
	}

	public Map<String, WithdrawExportDetailPayResult> getResultMap() {
		return resultMap;
	}

	public void setResultMap( Map<String, WithdrawExportDetailPayResult> resultMap ) {
		this.resultMap = resultMap;
	}

	public WithdrawExportDetailPayResult getResultMap( String key ) {
		if ( resultMap.containsKey( key ) )
			return resultMap.get( key );
		return null;
	}

	public int getStartLine() {
		return startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

}