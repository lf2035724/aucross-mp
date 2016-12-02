package com.ylink.ylpay.project.sms.app;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.common.project.sms.app.SmsAppService;
import com.ylink.ylpay.common.project.sms.dto.SmsSendBean;
import com.ylink.ylpay.project.constants.PatternConstant;
import com.ylink.ylpay.project.sms.repository.DreamNetServiceImpl;

/**
 * 梦网短信接口.
 * 
 * @author yu.han
 * @date 2013-02-27
 */
@Component("smsAppService")
public class SmsAppServiceDreamNetImpl implements SmsAppService {

	@Autowired
	private DreamNetServiceImpl dreamNetServiceImpl;

	private static Logger logger = LoggerFactory.getLogger( SmsAppServiceDreamNetImpl.class );
	/**
	 * 发送.
	 */
	@Override
	public void send( SmsSendBean dtoBean ) throws MpCheckedException {
		Validate.notNull( dtoBean );
		String mobiles=dtoBean.getDestaddr();
		Validate.notNull( mobiles, "手机号不能为空." );
		String[] mobileArr=mobiles.split(",");
		Validate.inclusiveBetween(1, 100, mobileArr.length,"发送号码数量只能在1~100之间","");
		for(String mobile:mobileArr){
			Validate.notNull( mobile, "手机号不能为空." );
			Validate.matchesPattern( mobile, PatternConstant.MOBILE_PATTERN, PatternConstant.MOBILE_ERROR_MSG );
		}
		Assert.hasText( dtoBean.getContent(), "发送内容不能为空." );
		logger.info("发送短信息:号码=【"+dtoBean.getDestaddr()+"】,内容=【"+dtoBean.getContent()+"】");
	//	String res=dreamNetServiceImpl.sendSms(dtoBean.getDestaddr(), dtoBean.getContent());
	//	logger.info("发送短信息结束：返回码=【"+res+"】");
	}

	/**
	 * 成功接收短信.
	 */
	@Override
	public void received( Integer id ) throws MpCheckedException {
		Validate.notNull( id );

	}
	/**
	 * 查询余额
	 * @description
	 * @return
	 * @author yu.han
	 * @date 2013-4-3
	 */
	@Override
	public int queryBalance(){
	//	return dreamNetServiceImpl.queryBalance();
		return 0;
	}

}