package com.ylink.modules.utils.jxl;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.biff.CellValue;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.collect.Lists;
import com.ylink.modules.utils.jxl.common.config.JxlConfig;
import com.ylink.modules.utils.jxl.common.constants.JxlConstant;
import com.ylink.modules.utils.jxl.format.CellFormat;

/**
 * Excel生成器.
 * 
 * @author 潘瑞峥
 * @date 2012-8-1
 */
public class JxlBuilder {

	/**
	 * 生成写入Excel文件
	 * 
	 * @param output
	 * @param name
	 * @param dataSource
	 * @param configs
	 * @throws Exception
	 */
	public void createWorkbook( OutputStream output, String name, Collection<? extends Serializable> dataSource,
			Collection<JxlConfig> configs ) throws Exception {

		// 创建Excel文件
		WritableWorkbook wwBook = Workbook.createWorkbook( output );
		// 创建工作薄
		WritableSheet wSheet = wwBook.createSheet( name, 0 );

		/* 插入单元格 */
		List<CellValue> cells = this.buildCell( dataSource, configs );
		for ( CellValue cell : cells ) {
			wSheet.addCell( cell );
		}

		/* 更改列宽度 */
		int y = 0;
		for ( JxlConfig config : configs ) {
			wSheet.setColumnView( y, config.getWidth() );
			y++;
		}
		// 写入
		wwBook.write();
		wwBook.close();
	}

	/**
	 * 构建单元格
	 * 
	 * @param dataSource
	 * @param configs
	 * @return
	 * @throws Exception
	 */
	private List<CellValue> buildCell( Collection<? extends Serializable> dataSource, Collection<JxlConfig> configs ) throws Exception {
		List<CellValue> cells = Lists.newArrayList();
		CellValue cell = null;
		Object value = null;
		String val = null;

		int x = JxlConstant.X_DEFUALT_BEGIN;
		for ( JxlConfig config : configs ) {

			/* Head Titile */
			int y = JxlConstant.Y_DEFUALT_BEGIN;
			cell = new Label( x, y, config.getDisplayTitle() );
			// 设置单元格: 居中 11号字 加粗
			cell.setCellFormat( CellFormat.format( null, JxlConstant.DEFAULT_FONT_SIZE, true, JxlConstant.CENTER, false ) );
			cells.add( cell );

			/* Body */
			// 头占1行
			y = JxlConstant.Y_DEFUALT_BEGIN + 1;
			for ( Serializable bean : dataSource ) {
				value = PropertyUtils.getProperty( bean, config.getFieldName() );
				val = null == value ? "" : String.valueOf( value );

				/* Number */
				if ( value instanceof java.lang.Number ) {
					/* Integer Long */
					if ( ( value instanceof Integer ) || ( value instanceof Long ) ) {
						cell = new Number( x, y, NumberUtils.toLong( val ) );
						// 格式化为Integer
						cell.setCellFormat( CellFormat.integerFormat() );
					}
					/* Double Float */
					else if ( ( value instanceof Double ) || ( value instanceof Float ) ) {
						cell = new Number( x, y, NumberUtils.toDouble( val ) );
						// 格式化为Double
						cell.setCellFormat( CellFormat.doubleFormat() );
					}
				}
				/* 非Number */
				else {
					cell = new Label( x, y, val );
				}
				cells.add( cell );

				y++;
			}

			x++;
		}
		return cells;
	}

}