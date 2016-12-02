package com.ylink.modules.utils.jxl.common.config;

import com.ylink.modules.utils.jxl.common.constants.JxlConstant;

/**
 * Jxl配置类.
 * 
 * @author 潘瑞峥
 * @date 2012-8-1
 */
public class JxlConfig {

	public JxlConfig() {
		super();
	}

	public JxlConfig( String fieldName, String displayTitle ) {
		super();
		this.fieldName = fieldName;
		this.displayTitle = displayTitle;
	}

	public JxlConfig( String fieldName, String displayTitle, int width ) {
		super();
		this.fieldName = fieldName;
		this.displayTitle = displayTitle;
		this.width = width;
	}

	public JxlConfig( String fieldName, String displayTitle, int width, boolean bold ) {
		super();
		this.fieldName = fieldName;
		this.displayTitle = displayTitle;
		this.width = width;
		this.bold = bold;
	}

	/** 字段名 */
	private String fieldName;

	/** 列标题(类似TH) */
	private String displayTitle;

	/** 宽度 */
	private int width = JxlConstant.DEFAULT_CELL_WIDTH;

	/** 加粗 */
	private boolean bold;

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

	public int getWidth() {
		return width;
	}

	public void setWidth( int width ) {
		this.width = width;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold( boolean bold ) {
		this.bold = bold;
	}

}