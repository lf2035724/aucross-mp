package com.ylink.ylpay.project.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ylink.ylpay.common.project.mp.constant.WorkDayType;

/**
 * 日期工具类.
 * 
 * @author 潘瑞峥
 * @date 2012-10-31
 */
public class DateUtils {
	
	/**
	 * 日期格式化
	 */
	public static String dateFormat( String date ){
		if( date == null || date.length() != 8 )
			return date;
		
		return date.substring(0,4) + "-" +date.substring(4,6)+ "-" +date.substring(6);
	}

	/**
	 * 校验dayOfWeek是不工作日.
	 * 
	 * @param dayOfWeek
	 * @return
	 */
	public static WorkDayType checkWorkDayType( int dayOfWeek ) {
		switch ( dayOfWeek ) {
			/* 非工作日. */
			case 6:
			case 7:
				return WorkDayType.NO;
			/* 工作日. */
			default:
				return WorkDayType.YES;
		}
	}
	
	/**
	 * 时间格式化
	 */
	public static String timeFormat(String time){
		if( time == null || time.length() != 6 )
			return time;
		
		return time.substring(0,2) + ":" +time.substring(2,4)+ ":" +time.substring(4);
	}
	
	public static String newDate(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(new Date());
	}
	
}