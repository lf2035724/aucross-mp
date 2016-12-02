package com.ylink.modules.utils.jxl.format;

import jxl.format.Alignment;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

import org.apache.commons.lang3.StringUtils;

import com.ylink.modules.utils.jxl.common.constants.JxlConstant;

/**
 * 格式化.
 * 
 * @author 潘瑞峥
 * @date 2012-8-1
 */
public class CellFormat {

	/**
	 * Double格式化
	 * 
	 * @return
	 */
	public static WritableCellFormat doubleFormat() {
		return new WritableCellFormat( new NumberFormat( JxlConstant.FORMAT_DOUBLE ) );
	}

	/**
	 * Integer格式化
	 * 
	 * @return
	 */
	public static WritableCellFormat integerFormat() {
		return new WritableCellFormat( new NumberFormat( JxlConstant.FORMAT_INTEGER ) );
	}

	/**
	 * 格式化
	 * 
	 * @param font
	 *            字体
	 * @param size
	 *            字号
	 * @param bold
	 *            是否加粗
	 * @param alignment
	 *            对齐方式
	 * @param border
	 *            是否边框
	 * @return
	 * @throws Exception
	 */
	public static WritableCellFormat format( String font, Integer size, Boolean bold, Alignment alignment, Boolean border )
			throws Exception {
		WritableCellFormat format = new WritableCellFormat();
		// 设置字体
		fontFormat( format, font, size, bold );
		// 设置对齐方式
		alignmentFormat( format, alignment );
		// 设置边框
		borderFormat( format, border );
		return format;
	}

	/**
	 * 对齐方式格式化
	 * 
	 * @param format
	 * @param alignment
	 * @throws Exception
	 */
	private static void alignmentFormat( WritableCellFormat format, Alignment alignment ) throws Exception {
		if ( null != alignment )
			format.setAlignment( alignment );
	}

	/**
	 * 边框格式化
	 * 
	 * @param format
	 * @param mark
	 * @throws Exception
	 */
	private static void borderFormat( WritableCellFormat format, Boolean mark ) throws Exception {
		if ( null != mark && mark )
			format.setBorder( JxlConstant.BORDER_ALL, JxlConstant.BORDER_LINE_THIN );
	}

	/**
	 * 字体格式化
	 * 
	 * @param format
	 * @param font
	 * @param size
	 * @param bold
	 * @throws Exception
	 */
	private static void fontFormat( WritableCellFormat format, String font, Integer size, Boolean bold ) throws Exception {
		// 默认字体
		String fontName = JxlConstant.DEFAULT_FONT_NAME;
		if ( StringUtils.isNotBlank( font ) )
			fontName = font;
		WritableFont wFont = new WritableFont( WritableFont.createFont( fontName ) );
		// 字体大小
		if ( null != size )
			wFont.setPointSize( size );
		// 字体加粗
		if ( null != bold && bold )
			wFont.setBoldStyle( WritableFont.BOLD );
		format.setFont( wFont );
	}

}