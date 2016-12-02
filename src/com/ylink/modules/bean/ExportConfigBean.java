package com.ylink.modules.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 导出文件配置文件Bean.
 * 
 * @author 潘瑞峥
 * @date 2012-12-7
 */
public class ExportConfigBean {

	/** key. */
	private Object key;

	/** 字符集. */
	private String encoding;

	/** 分隔符. */
	private String separator;

	/** 换行符. */
	private String newline;
	/** 头信息. */
	private String titleinfo;
	/** 尾信息. */
	private String endinfo;
	/** 联行号. */
	private String correspondent;
	/** 结尾是否有分隔符 */
	private Boolean isSeparator;
	
	/** 导出日期的格式 */
	private String dateFormat;
	
	/** 需要导出的字段. */
	private String[] fields;
	
	/** 需要导出的头字段 */
	private String[] titles;
	
	public ExportConfigBean() {
	}

	public ExportConfigBean( Object key, String encoding, String separator, String newline,String titleinfo,String endinfo, String correspondent ,Boolean isSeparator,String[] fields,String[] titles ) {
		this.key = key;
		this.encoding = encoding;
		this.separator = separator;
		this.newline = newline;
		this.titleinfo = titleinfo;
		this.endinfo = endinfo;
		this.correspondent = correspondent;
		this.fields = fields;
		this.titles = titles;
		this.isSeparator = isSeparator;
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

	public String getNewline() {
		return newline;
	}

	public void setNewline( String newline ) {
		this.newline = newline;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields( String[] fields ) {
		this.fields = fields;
	}
	public String[] getTitles() {
		return titles;
	}
	public void setTitles(String[] titles) {
		this.titles = titles;
	}
	
	/**
	 * @return the titleinfo
	 */
	public String getTitleinfo() {
		return titleinfo;
	}

	/**
	 * @param titleinfo the titleinfo to set
	 */
	public void setTitleinfo(String titleinfo) {
		this.titleinfo = titleinfo;
	}

	/**
	 * @return the endinfo
	 */
	public String getEndinfo() {
		return endinfo;
	}

	/**
	 * @param endinfo the endinfo to set
	 */
	public void setEndinfo(String endinfo) {
		this.endinfo = endinfo;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString( this, ToStringStyle.MULTI_LINE_STYLE );
	}

	/**
	 * @return the correspondent
	 */
	public String getCorrespondent() {
		return correspondent;
	}

	/**
	 * @param correspondent the correspondent to set
	 */
	public void setCorrespondent(String correspondent) {
		this.correspondent = correspondent;
	}

	/**
	 * @return the isSeparator
	 */
	public Boolean getIsSeparator() {
		return isSeparator;
	}

	/**
	 * @param isSeparator the isSeparator to set
	 */
	public void setIsSeparator(Boolean isSeparator) {
		this.isSeparator = isSeparator;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	
}