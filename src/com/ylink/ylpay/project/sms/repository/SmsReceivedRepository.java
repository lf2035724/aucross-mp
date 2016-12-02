package com.ylink.ylpay.project.sms.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Repository;

import com.ylink.modules.jdbc.JdbcDao;
import com.ylink.ylpay.project.sms.constant.Constant;

/**
 * 短信接收领域层.
 * 
 * @author 潘瑞峥
 * @date 2012-11-12
 */
@Repository
public class SmsReceivedRepository extends JdbcDao {

	/**
	 * 保存.
	 * 
	 * @param id
	 */
	public void save( Integer id ) {
		Validate.notNull( id );

		// 发送表.
		Map<String, Object> entity = super.get( Constant.SMS_SEND_TABLE_NAME, id );

		Map<String, Object> values = new HashMap<String, Object>();
		if ( null != entity ) {
			// 接收时间.
			values.put( "recvtime", new Date() );
			// 发送短信ID.
			values.put( "sm_id", entity.get( "id" ) );
			// 原主叫地址.
			values.put( "orgaddr", entity.get( "orgaddr" ) );
			// 原被叫地址.
			values.put( "destaddr", entity.get( "destaddr" ) );
			// 发送内容.
			values.put( "sm_content", entity.get( "content" ) );
			// 业务代码, 跟平台无关, 运营商的业务.
			values.put( "businesstype", "" );
		}

		super.insert( Constant.SMS_RECEIVED_TABLE_NAME, values );
	}

}