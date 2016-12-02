package com.ylink.ylpay.project.constants;

import com.ylink.ylpay.common.project.mp.constant.UserType;

/**
 * 编码规则常量类.
 * 
 * <pre>
 * File: "\documents\需求\证联融通记账规则及科目表 - 新版.xls"<br>
 * Sheet: "编码规则"
 * </pre>
 * 
 * @author 潘瑞峥
 * @date 2012-11-1
 */
public final class EncodeRulesConstant {

	/* 默认. */
	public static final String DEFAULT_PREFIX = "";
	public static final int DEFAULT_SPIKE = 1;

	/* 用户的loginName编码规则: U + 14位序列. */
	public static final String USER_LOGINNAME_PREFIX = "U";
	public static final int USER_LOGINNAME_LENGTH = 14;

	// 个人客户code编码规则: CP + 10位序列.
	public static final String CUSTOMER_INDIVIDUAL_PREFIX = UserType.CP.getValue();
	// 企业客户code编码规则: CB + 10位序列.
	public static final String CUSTOMER_ENTERPRISE_PREFIX = UserType.CB.getValue();
	// 长度.
	public static final int CUSTOMER_CODE_LENGTH = 10;

	/* 商户的code编码规则: B + 8位序列. */
	public static final String CUST_CODE_PREFIX = "B";
	public static final int CUST_CODE_LENGTH = 8;

	/* 机构的code编码规则: AG + 5位序列. */
	public static final String AGENCY_CODE_PREFIX = "AG";
	public static final int AGENCY_CODE_LENGTH = 5;

	/* 单笔借贷转账表T_CREDIT_DEBIT_TRANSFER id编码规则: yyyyMMdd + 24位序列. */
	public static final String CREDIT_DEBIT_TRANSFER_ID_PREFIX = "CREDIT_DEBIT_TRANSFER_KEY";
	public static final int CREDIT_DEBIT_TRANSFER_ID_LENGTH = 20;

	/* 提现T_WITHDRAW_EXPORT_BATCH id补位长度. */
	public static final int WITHDRWA_ID_LENGTH = 4;

	private EncodeRulesConstant() {
	}

}