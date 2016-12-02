package com.ylink.ylpay.project.utils;

/**
 * Long工具类
 * @author YangHan
 * @date 2013-01-24
 */
public class LongUtil {

	public static Long stringToLong(String str){
		try {
			return str == null ? 0 : Long.parseLong(str);
		} catch (Exception e) {
			return 0L;
		}
	}
}
