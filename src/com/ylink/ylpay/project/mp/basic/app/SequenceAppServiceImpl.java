/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.mp.basic.app;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.code.lightssh.project.sequence.service.SequenceManager;
import com.ylink.ylpay.common.project.mp.app.SequenceAppService;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;

/** 
 * @author YangXiaojin
 * @date 2012-11-2
 * @description：TODO
 */
@Component("sequenceAppService")
public class SequenceAppServiceImpl implements SequenceAppService{
	
	/**
	 * 门户序列号
	 */
	public static final String PORTAL_SEQUENCE_NAME = "PORTAL_SEQUENCE";
	public static final String ZERO_PAD = "00000000000000000000";

	@Resource(name="sequenceManager")
	private SequenceManager sequenceManager;

	@Override
	public String getPortalNextValue(int length) throws MpCheckedException{
		length = Math.max(6, length);
		length = Math.min(20, length);
		String db_seq = ZERO_PAD + sequenceManager.nextDatabaseSerialNumber(PORTAL_SEQUENCE_NAME);
		return db_seq.substring(db_seq.length()-length);
	}

}
