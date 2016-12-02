package com.ylink.ylpay.project.sms.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Repository;

import com.ylink.modules.jdbc.JdbcDao;
import com.ylink.ylpay.common.project.mp.constant.SmsMobileArea;
import com.ylink.ylpay.common.project.mp.constant.SmsSendType;
import com.ylink.ylpay.project.sms.constant.Constant;

/**
 * 短信发送领域层.
 * 
 * @author 潘瑞峥
 * @date 2012-11-7
 */
@Repository
public class SmsSendRepository extends JdbcDao {

	/**
	 * 保存, 并返回.
	 * 
	 * @param values
	 */
	public Map<String, Object> saveAndFind( Map<String, Object> values ) {
		Validate.notNull( values );

		/* 短信内容. */
		String content = ( String ) values.get( "content" );
		if ( StringUtils.isEmpty( content ) ) {
			content = Constant.smsContentSuffix;
		} else {
			content += Constant.smsContentSuffix;
		}
		values.put( "content", content );

		// 主叫号码, 统一的.
		values.put( "orgaddr", Constant.smsCallingPartyMobile );
		// 创建ID.
		values.put( "creatorid", Constant.smsCreatorId );
		// 业务代码.
		values.put( "businesstype", Constant.smsBusinessType );
		// 发送状态: 初始值.
		values.put( "type", SmsSendType.S3.getValue() );
		// 所属营业区域: 省外.
		values.put( "area", SmsMobileArea.S2.getValue() );

		super.insert( Constant.SMS_SEND_TABLE_NAME, values );
		// 插入后返回.
		return super.findUnique( Constant.SMS_SEND_TABLE_NAME, values );
	}

	/**
	 * 保存.
	 * 
	 * @param values
	 */
	public void save( Map<String, Object> values ) {
		Validate.notNull( values );

		/* 短信内容. */
		String content = ( String ) values.get( "content" );
		if ( StringUtils.isEmpty( content ) ) {
			content = Constant.smsContentSuffix;
		} else {
			content += Constant.smsContentSuffix;
		}
		values.put( "content", content );

		// 主叫号码, 统一的.
		values.put( "orgaddr", Constant.smsCallingPartyMobile );
		// 创建ID.
		values.put( "creatorid", Constant.smsCreatorId );
		// 业务代码.
		values.put( "businesstype", Constant.smsBusinessType );
		// 发送状态: 初始值.
		values.put( "type", SmsSendType.S3.getValue() );
		// 所属营业区域: 省外.
		values.put( "area", SmsMobileArea.S2.getValue() );

		super.insert( Constant.SMS_SEND_TABLE_NAME, values );
	}

	/**
	 * 修改为成功状态.
	 * 
	 * @param id
	 */
	public void updateType( Integer id ) {
		Validate.notNull( id );

		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> values = new HashMap<String, Object>();

		params.put( "id", id );
		values.put( "type", SmsSendType.S3.getValue() );

		super.update( Constant.SMS_SEND_TABLE_NAME, params, values );
	}

}