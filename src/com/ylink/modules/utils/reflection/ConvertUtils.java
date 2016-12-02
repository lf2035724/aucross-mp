package com.ylink.modules.utils.reflection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.google.code.lightssh.common.dao.Term;
import com.ylink.modules.orm.PropertyFilter;

/**
 * 转换工具类.
 * 
 * @author 潘瑞峥
 */
public class ConvertUtils {

	/**
	 * 转换字符串到相应类型.
	 * 
	 * @param value
	 *            待转换的字符串.
	 * @param toType
	 *            转换目标类型.
	 */
	public static Object convertStringToObject( String value, Class<?> toType ) {
		try {
			if( toType == Date.class ) {
				return DateUtils.parseDate(value, "yyyyMMdd");
			}
			return org.apache.commons.beanutils.ConvertUtils.convert( value, toType );
		} catch ( Exception e ) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked( e );
		}
	}

	/**
	 * 将PropertyFilter集合转为Term集合.
	 * 
	 * @param filterList
	 * @return
	 */
	public static List<Term> convertPropertyFilterToTerm( Collection<PropertyFilter> filterList ) {
		List<Term> termList = new ArrayList<Term>();
		Term term = null;
		for ( PropertyFilter filter : filterList ) {
			term = convertPropertyFilterToTerm( filter );
			termList.add( term );
		}
		return termList;
	}

	/**
	 * 将PropertyFilter类型转为Term类型.
	 * 
	 * @param filter
	 * @return
	 */
	public static Term convertPropertyFilterToTerm( PropertyFilter filter ) {
		Term.Type matchType = null;
		switch ( filter.getMatchType() ) {
			case EQ:
				matchType = Term.Type.EQUAL;
				break;
			case LE:
				matchType = Term.Type.LESS_THAN_EQUAL;
				break;
			case LT:
				matchType = Term.Type.LESS_THAN;
				break;
			case GE:
				matchType = Term.Type.GREATE_THAN_EQUAL;
				break;
			case GT:
				matchType = Term.Type.GREATE_THAN;
				break;
			/* '%value%' */
			case LIKE:
				matchType = Term.Type.LIKE;
				break;
			/* 'value%' */
			case LIKESTART:
				matchType = Term.Type.LIKE_RIGHT;
				break;
			/* '%value' */
			case LIKEEND:
				matchType = Term.Type.LIKE_LEFT;
				break;
			default:
				break;
		}
		return new Term( matchType, filter.getPropertyName(), filter.getMatchValue() );
	}

}