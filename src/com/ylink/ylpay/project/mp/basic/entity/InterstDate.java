/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 */
package com.ylink.ylpay.project.mp.basic.entity;

import java.util.Date;

/**
 * 活期利率生效时间查询中间类
 * @author yanghan
 * @date 2013-6-8
 */
public class InterstDate {

	private String subjectCode;
	
	private Date effectiveBeginTime;

	public InterstDate(){}
	public InterstDate(String subjectCode,Date effectiveBeginTime){
		this.subjectCode = subjectCode;
		this.effectiveBeginTime = effectiveBeginTime;
	}
	/**
	 * @return the subjectCode
	 */
	public String getSubjectCode() {
		return subjectCode;
	}

	/**
	 * @param subjectCode the subjectCode to set
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	/**
	 * @return the effectiveBeginTime
	 */
	public Date getEffectiveBeginTime() {
		return effectiveBeginTime;
	}

	/**
	 * @param effectiveBeginTime the effectiveBeginTime to set
	 */
	public void setEffectiveBeginTime(Date effectiveBeginTime) {
		this.effectiveBeginTime = effectiveBeginTime;
	}
	
	
}
