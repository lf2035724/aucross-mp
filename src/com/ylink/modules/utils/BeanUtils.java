package com.ylink.modules.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BeanUtils {

	/**
	 * 将对象准换为Map.
	 * 
	 * @param bean
	 * @param removeProperties
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings( { "unchecked" } )
	public static Map<String, Object> toMap( Object bean, boolean trimNull, String[] removeProperties ) throws Exception {
		Map<String, Object> values = org.apache.commons.beanutils.BeanUtils.describe( bean );
		/* 删除无用值. */
		values.remove( "class" );
		values.remove( "serialVersionUID" );

		/* 过滤NULL. */
		if ( trimNull ) {
			Set<String> keys = new HashSet<String>( values.keySet() );
			for ( String key : keys ) {
				if ( null == values.get( key ) ) {
					values.remove( key );
				}
			}
		}

		if ( null == removeProperties || 0 == removeProperties.length ) {
			return values;
		} else {
			List<String> removeList = Arrays.asList( removeProperties );
			for ( String removeKey : removeList )
				values.remove( removeKey );
		}

		return values;
	}

	/**
	 * 将对象准换为Map.
	 * 
	 * @param bean
	 * @param removeProperties
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> toMap( Object bean, String[] removeProperties ) throws Exception {
		return toMap( bean, true, removeProperties );
	}

	/**
	 * 将对象准换为Map.
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> toMap( Object bean ) throws Exception {
		return toMap( bean, true, null );
	}

}