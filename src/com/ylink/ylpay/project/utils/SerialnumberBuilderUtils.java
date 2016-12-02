package com.ylink.ylpay.project.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.Validate;

import com.ylink.ylpay.common.project.channel.constant.BankType;

/**
 * 编号生成工具类.
 * 
 * @author 潘瑞峥
 * @date 2012-12-1
 */
public final class SerialnumberBuilderUtils {

	public static final SimpleDateFormat DATE_SHORT_FORMAT = new SimpleDateFormat( "yyyyMMdd" );

	private static final SimpleDateFormat FULL_DATE_FORMAT = new SimpleDateFormat( "yyyyMMddHHmmss" );

	/**
	 * 短时间.
	 * 
	 * @return
	 */
	public static String shortDate() {
		return DATE_SHORT_FORMAT.format( new Date() );
	}

	/**
	 * UUID.
	 * 
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll( "-", "" );
	}

	/**
	 * 提现批次号.
	 * 
	 * @return
	 */
	public static String withdrawBatchSerialnumber( BankType bankType ) {
		Validate.notNull( bankType, "行别不能为空." );

		StringBuilder buffer = new StringBuilder();
		buffer.append( DATE_SHORT_FORMAT.format( new Date() ) );
		buffer.append( "_" ).append( bankType.getValue() );
		buffer.append( "_" ).append( uuid() );
		return buffer.toString();
	}

	/**
	 * 18位唯一数
	 * 
	 * 14位yyyyMMddHHmmss + 两个2位随机数.
	 * 
	 * @return
	 */
	public static String serialnumber18() {
		StringBuilder buffer = new StringBuilder();
		buffer.append( FULL_DATE_FORMAT.format( new Date() ) );
		buffer.append( ( int ) ( 100 * Math.random() ) );
		buffer.append( ( int ) ( 100 * Math.random() ) );
		return buffer.toString();
	}

	private SerialnumberBuilderUtils() {
	}
	
	public static void main(String[] args) {
		String s = uuid();
		System.out.println(s);
	}
}