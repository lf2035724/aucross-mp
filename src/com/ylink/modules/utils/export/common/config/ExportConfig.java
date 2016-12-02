package com.ylink.modules.utils.export.common.config;

/**
 * 配置类.
 * 
 * @author 潘瑞峥
 * @date 2012-9-10
 */
public class ExportConfig {

	/** 字段名 */
	private String fieldName;

	/** 列标题(类似TH) */
	private String displayTitle;

	/** 宽度 */
	private Integer width;

	public ExportConfig( String fieldName ) {
		this.fieldName = fieldName;
	}

	public ExportConfig( String fieldName, String displayTitle ) {
		this.fieldName = fieldName;
		this.displayTitle = displayTitle;
	}

	public ExportConfig( String fieldName, String displayTitle, Integer width ) {
		this.fieldName = fieldName;
		this.displayTitle = displayTitle;
		this.width = width;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName( String fieldName ) {
		this.fieldName = fieldName;
	}

	public String getDisplayTitle() {
		return displayTitle;
	}

	public void setDisplayTitle( String displayTitle ) {
		this.displayTitle = displayTitle;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth( Integer width ) {
		this.width = width;
	}

}