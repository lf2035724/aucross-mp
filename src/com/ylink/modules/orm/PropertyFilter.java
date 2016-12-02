package com.ylink.modules.orm;

import java.util.Date;

import org.springframework.util.Assert;

/**
 * 属性过滤条件封装类.
 * 
 * @author 潘瑞峥
 * @date 2012-9-7
 */
public class PropertyFilter {

	/** 比较方式. */
	private MatchType matchType = null;

	/** 比较值. */
	private Object matchValue = null;

	/** 比较值的类型. */
	private Class<?> propertyClass = null;

	/** 比较属性名称列表. */
	private String[] propertyNames = null;

	/** 属性比较类型. */
	public enum MatchType {
		EQ, LE, LT, GE, GT,

		// '%value%'
		LIKE,

		// 'value%'
		LIKESTART,

		// '%value'
		LIKEEND;
	}

	/** 属性数据类型. */
	public enum PropertyType {
		S( String.class ), I( Integer.class ), L( Long.class ), N( Double.class ), D( Date.class ), B( Boolean.class );

		private Class<?> clazz;

		private PropertyType( Class<?> clazz ) {
			this.clazz = clazz;
		}

		public Class<?> getValue() {
			return clazz;
		}
	}

	public PropertyFilter( MatchType matchType, Object matchValue, String propertyName ) {
		this.matchType = matchType;
		this.matchValue = matchValue;
		this.propertyNames = new String[] { propertyName };
		this.propertyClass = matchValue.getClass();
	}

	public PropertyFilter( MatchType matchType, Object matchValue, String[] propertyNames ) {
		this.matchType = matchType;
		this.matchValue = matchValue;
		this.propertyNames = propertyNames;
		this.propertyClass = matchValue.getClass();
	}

	public PropertyFilter( MatchType matchType, Object matchValue, Class<?> propertyClass, String[] propertyNames ) {
		this.matchType = matchType;
		this.matchValue = matchValue;
		this.propertyClass = propertyClass;
		this.propertyNames = propertyNames;
	}

	public MatchType getMatchType() {
		return matchType;
	}

	public Object getMatchValue() {
		return matchValue;
	}

	public Class<?> getPropertyClass() {
		return propertyClass;
	}

	public String[] getPropertyNames() {
		return propertyNames;
	}

	/**
	 * 获取唯一的比较属性名称.
	 */
	public String getPropertyName() {
		Assert.isTrue( propertyNames.length == 1, "There are not only one property in this filter." );
		return propertyNames[ 0 ];
	}

	/**
	 * 是否比较多个属性.
	 */
	public boolean hasMultiProperties() {
		return ( propertyNames.length > 1 );
	}

}