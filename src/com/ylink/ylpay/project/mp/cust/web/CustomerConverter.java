/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.mp.cust.web;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.ylink.ylpay.project.mp.cust.entity.Enterprise;
import com.ylink.ylpay.project.mp.cust.entity.Personal;

/** 
 * @author YangXiaojin
 * @date 2012-11-19
 * @description：TODO
 */
public class CustomerConverter extends StrutsTypeConverter {
	//private final String PERSON = "personal";
	private final String ORG = "ENTERPRISE";
	
	@SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String[] values, Class toClass){
		if( values == null || values[0] == null || values[0].equals(ORG))
			return new Enterprise();
		
		return new Personal();
	}

	@SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object o) {
		return o==null?null:o.toString();
	}

}
