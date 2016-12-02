package com.ylink.modules.utils;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.lightssh.common.ApplicationException;


/**
 * 校验工具.
 * 
 * @author 潘瑞峥
 * @date 2013-3-26
 */
public final class ValidateUtils {

	private static Logger logger = LoggerFactory.getLogger( ValidateUtils.class );

	private static final String MSG = "不能为空";

	/**
	 * 验证不为空或空字符串或空格.
	 * 
	 * @param bean
	 * @param fields
	 */
	public static void notNull( Serializable bean, Map<String, String> fields ) {
		Validate.notNull( bean, "需要验证的对象不能为空" );
		Validate.notNull( fields, "需要验证的字段不能为空" );

		Object value = null;
		String promptMsg = null;

		for ( String key : fields.keySet() ) {
			if ( StringUtils.isNotBlank( key ) ) {
				try {
					value = FieldUtils.readField( bean, key, true );

					promptMsg = fields.get( key );
					if ( StringUtils.isBlank( promptMsg ) ) {
						promptMsg = key;
					}
					promptMsg += MSG;

					Validate.notNull( value, promptMsg );
					if ( value instanceof String ) {
						Validate.notBlank( String.valueOf( value ), promptMsg );
					}
				} catch ( IllegalAccessException e ) {
					logger.warn( "FieldUtils.readField( {}, {} )异常", bean.getClass().getName(), key );
					throw new ApplicationException( e.getMessage() );
				}
			}
		}
	}

	private ValidateUtils() {
	}

}