package com.ylink.ylpay.project.sms.app;

import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.ylink.modules.utils.BeanUtils;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.common.project.sms.app.SmsAppService;
import com.ylink.ylpay.common.project.sms.dto.SmsSendBean;
import com.ylink.ylpay.project.constants.PatternConstant;
import com.ylink.ylpay.project.sms.repository.SmsReceivedRepository;
import com.ylink.ylpay.project.sms.repository.SmsSendRepository;

/**
 * 短信接口.
 * 
 * @author 潘瑞峥
 * @date 2012-11-8
 */
//@Component( "smsAppService" )
public class SmsAppServiceImpl implements SmsAppService {

	@Autowired
	private SmsSendRepository smsSendRepository;

	@Autowired
	private SmsReceivedRepository smsReceivedRepository;

	/**
	 * 发送短信.
	 */
	// @Override
	// public Integer send( SmsSendBean dtoBean ) throws MpCheckedException {
	// Validate.notNull( dtoBean );
	// Validate.notNull( dtoBean.getDestaddr(), "手机号不能为空." );
	// Validate.matchesPattern( dtoBean.getDestaddr(), PatternConstant.MOBILE_PATTERN,
	// PatternConstant.MOBILE_ERROR_MSG );
	// Assert.hasText( dtoBean.getContent(), "发送内容不能为空." );
	// Validate.notNull( dtoBean.getBusinesstype(),
	// "业务类型不能为空.参考com.ylink.ylpay.common.project.mp.constant.SmsBusinessType." );
	//
	// try {
	// Map<String, Object> values = BeanUtils.toMap( dtoBean );
	// Map<String, Object> result = this.smsSendRepository.saveAndFind( values );
	// if ( null == result ) {
	// return null;
	// } else {
	// return Integer.valueOf( result.get( "id" ).toString() );
	// }
	// } catch ( Exception ex ) {
	// ex.printStackTrace();
	// throw new MpCheckedException( ex );
	// }
	// }

	/**
	 * 发送.
	 */
	@Override
	public void send( SmsSendBean dtoBean ) throws MpCheckedException {
		Validate.notNull( dtoBean );
		Validate.notNull( dtoBean.getDestaddr(), "手机号不能为空." );
		Validate.matchesPattern( dtoBean.getDestaddr(), PatternConstant.MOBILE_PATTERN, PatternConstant.MOBILE_ERROR_MSG );
		Assert.hasText( dtoBean.getContent(), "发送内容不能为空." );

		Map<String, Object> values;
		try {
			values = BeanUtils.toMap( dtoBean );
			this.smsSendRepository.save( values );
		} catch ( Exception ex ) {
			ex.printStackTrace();
			throw new MpCheckedException( ex );
		}
	}

	/**
	 * 成功接收短信.
	 */
	@Override
	public void received( Integer id ) throws MpCheckedException {
		Validate.notNull( id );

		// 修改原记录状态.
		this.smsSendRepository.updateType( id );
		// 保存到接收表.
		this.smsReceivedRepository.save( id );
	}

	/* (non-Javadoc)
	 * @see com.ylink.ylpay.common.project.sms.app.SmsAppService#queryBalance()
	 */
	@Override
	public int queryBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

}