package com.ylink.modules.orm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.google.code.lightssh.common.dao.Term;
import com.ylink.modules.orm.PropertyFilter.MatchType;
import com.ylink.modules.orm.PropertyFilter.PropertyType;
import com.ylink.modules.utils.reflection.ConvertUtils;
import com.ylink.modules.utils.web.ServletUtils;

/**
 * PropertyFilter构造器.
 * 
 * @author 潘瑞峥
 * @date 2012-9-7
 */
public class PropertyFilterBuilder {

	/** 参数的前缀. */
	private static final String FILTER_PREFIX = "filter_";

	/** 分隔符. */
	private static final String SEPARATOR = "_";

	/** 多个属性间OR关系的分隔符. */
	private static final String OR_SEPARATOR = SEPARATOR + "OR" + SEPARATOR;

	/** 参数的个数. */
	private static final int MAX_AMOUNT = 3;

	private PropertyFilterBuilder() {
	}

	/**
	 * 从HttpRequest中创建PropertyFilter列表, 默认Filter属性名前缀为filter.
	 * 
	 * @see #buildFromHttpRequest(HttpServletRequest, String)
	 * @param request
	 * @return
	 */
	public static List<PropertyFilter> buildFromHttpRequest( final HttpServletRequest request ) {
		return buildFromHttpRequest( request, FILTER_PREFIX );
	}

	/**
	 * 从HttpRequest中创建PropertyFilter列表.<br>
	 * 命名规则为Filter属性前缀_比较类型_属性类型_属性名(或_OR_另属性名).
	 * 
	 * <pre>
	 * eg. filter_EQ_S_name, filter_LIKE_S_name_OR_username, filter_LIKESTART_S_id.no
	 * </pre>
	 * 
	 * @param request
	 * @param filterPrefix
	 * @return
	 */
	public static List<PropertyFilter> buildFromHttpRequest( final HttpServletRequest request, final String filterPrefix ) {
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();

		// 从request中获取含属性前缀名的参数, 构造去除前缀名后的参数Map.
		Map<String, Object> filterParamMap = ServletUtils.getParametersStartingWith( request, filterPrefix );

		// 分析参数Map, 构造PropertyFilter列表
		for ( Map.Entry<String, Object> entry : filterParamMap.entrySet() ) {
			String filterName = entry.getKey();
			String value = ( String ) entry.getValue();
			// 如果value值为空, 则忽略此filter.
			if ( StringUtils.isNotBlank( value ) ) {
				PropertyFilter filter = buildPropertyFilter( filterName, value );
				filterList.add( filter );
			}
		}

		return filterList;
	}

	/**
	 * 将参数的值转为指定类型, 并构建PropertyFilter对象.
	 * 
	 * @param filterName
	 * @param value
	 */
	public static PropertyFilter buildPropertyFilter( final String filterName, final String value ) {
		MatchType matchType = null;
		Object matchValue = null;
		Class<?> propertyClass = null;
		String[] propertyNames = null;

		String errMsg = "filter名称" + filterName + "没有按规则编写, 无法得到属性比较类型(eg.LIKE_S_NAME_OR_USERNAME).";

		String[] params = StringUtils.split( filterName, SEPARATOR, MAX_AMOUNT );
		Assert.isTrue( MAX_AMOUNT == params.length, errMsg );

		String matchTypeCode = params[ 0 ];
		String propertyTypeCode = params[ 1 ];
		String propertyFieldName = params[ 2 ];

		try {
			matchType = Enum.valueOf( MatchType.class, matchTypeCode );
		} catch ( RuntimeException e ) {
			throw new IllegalArgumentException( errMsg, e );
		}

		try {
			propertyClass = Enum.valueOf( PropertyType.class, propertyTypeCode ).getValue();
		} catch ( RuntimeException e ) {
			throw new IllegalArgumentException( errMsg, e );
		}

		Assert.isTrue( StringUtils.isNotBlank( propertyFieldName ), errMsg );
		propertyNames = StringUtils.splitByWholeSeparator( propertyFieldName, OR_SEPARATOR );

		matchValue = ConvertUtils.convertStringToObject( value, propertyClass );

		PropertyFilter propertyFilter = new PropertyFilter( matchType, matchValue, propertyClass, propertyNames );
		return propertyFilter;
	}

	/**
	 * 从HttpRequest中创建Term列表, 默认Filter属性名前缀为filter.
	 * 
	 * @see #buildTermFromHttpRequest(HttpServletRequest, String)
	 * @param request
	 * @return
	 */
	public static List<Term> buildTermFromHttpRequest( final HttpServletRequest request ) {
		return buildTermFromHttpRequest( request, FILTER_PREFIX );
	}

	/**
	 * 从HttpRequest中创建Term列表.<br>
	 * 命名规则为Filter属性前缀_比较类型_属性类型_属性名(暂不支持_OR_另属性名).
	 * 
	 * <pre>
	 * eg. filter_EQ_S_name, filter_LIKESTART_S_id.no, filter_LIKE_S_name_OR_username(暂不支持)
	 * </pre>
	 * 
	 * @param request
	 * @param filterPrefix
	 * @return
	 */
	public static List<Term> buildTermFromHttpRequest( final HttpServletRequest request, final String filterPrefix ) {
		List<PropertyFilter> filterList = buildFromHttpRequest( request, filterPrefix );
		List<Term> termList = ConvertUtils.convertPropertyFilterToTerm( filterList );
		return termList;
	}

}