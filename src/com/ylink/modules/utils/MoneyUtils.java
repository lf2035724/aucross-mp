package com.ylink.modules.utils;

import java.math.BigDecimal;

import org.apache.commons.lang3.Validate;

/**
 * Money工具类.
 * 
 * @author 潘瑞峥
 * @date 2012-11-20
 */
public class MoneyUtils {

	/** 格式化金额. */
	public static final String MONEY_FORMAT = "%.2f";

	/** 进率(其他系统保存的是long, 以double * 100保存). */
	public static final BigDecimal MONEY_FEEDRATE = new BigDecimal( 0.01 ).setScale( 2, BigDecimal.ROUND_HALF_UP );

	/**
	 * 页面调用静态方法.
	 * 
	 * @param money
	 * @return
	 */
	public static String showPage( Long money ) {
		BigDecimal bigDecimal = new BigDecimal( money ).setScale( 2, BigDecimal.ROUND_HALF_UP ).multiply( MONEY_FEEDRATE );
		Double moneyDouble = bigDecimal.doubleValue();
		String creditStr = String.format( MONEY_FORMAT, moneyDouble );
		return creditStr;
	}

	/**
	 * 转换为系统金额.
	 * 
	 * @param money
	 * @return
	 */
	public static long simpleToMoney( String money ) {
		BigDecimal bigDecimal = new BigDecimal( money ).setScale( 2, BigDecimal.ROUND_HALF_UP ).divide( MONEY_FEEDRATE );
		return bigDecimal.longValue();
	}

	/**
	 * 将long型金额除以100，并转为String.
	 * 
	 * @param money
	 * @return
	 */
	public static String toString( Long money ) {
		Validate.notNull( money, "传入的金额不可为空." );

		String creditStr = String.format( MONEY_FORMAT, formMoney( money ) );
		return String.format( creditStr );
	}

	/**
	 * 从其他系统转为本系统金额格式.
	 * 
	 * @param money
	 * @return
	 */
	public static double formMoney( Long money ) {
		Validate.notNull( money, "传入的金额不可为空." );

		BigDecimal bigDecimal = new BigDecimal( money ).setScale( 2, BigDecimal.ROUND_HALF_UP ).multiply( MONEY_FEEDRATE );
		return bigDecimal.doubleValue();
	}

	/**
	 * 从本系统转为其他系统金额格式.
	 * 
	 * @param money
	 * @return
	 */
	public static long toMoney( Double money ) {
		Validate.notNull( money, "传入的金额不可为空." );

		BigDecimal bigDecimal = new BigDecimal( money ).setScale( 2, BigDecimal.ROUND_HALF_UP ).divide( MONEY_FEEDRATE );
		return bigDecimal.longValue();
	}

	/**
	 * 从本系统转为其他系统金额格式.
	 * 
	 * @param money
	 * @return
	 */
	public static long toMoney( String money ) {
		Validate.notNull( money, "传入的金额不可为空." );

		BigDecimal bigDecimal = new BigDecimal( money ).setScale( 2, BigDecimal.ROUND_HALF_UP ).divide( MONEY_FEEDRATE );
		return bigDecimal.longValue();
	}

	/**
	 * Long -> Double.
	 * 
	 * @param money
	 * @return
	 */
	public static Double long2Double( Long money ) {
		if ( null == money ) {
			return null;
		}
		BigDecimal bigDecimal = new BigDecimal( money ).setScale( 2, BigDecimal.ROUND_HALF_UP ).multiply( MONEY_FEEDRATE );
		return bigDecimal.doubleValue();
	}

	/**
	 * Double -> Long.
	 * 
	 * @param money
	 * @return
	 */
	public static Long double2Long( Double money ) {
		if ( null == money ) {
			return null;
		}
		BigDecimal bigDecimal = new BigDecimal( money ).setScale( 2, BigDecimal.ROUND_HALF_UP ).divide( MONEY_FEEDRATE );
		return bigDecimal.longValue();
	}

	/**
	 * Double -> String.
	 * 
	 * @param money
	 * @return
	 */
	public static String double2String( Double money ) {
		String moneyString = "";
		if ( null != money ) {
			moneyString = String.format( MONEY_FORMAT, money );
		}
		return moneyString;
	}

}