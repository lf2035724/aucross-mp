package com.ylink.modules.jdbc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

/**
 * 简单的封装原生的JdbcTemplate.
 * 
 * @author 潘瑞峥
 * @date 2012-11-8
 */
public class JdbcSupport {

	private static Logger logger = LoggerFactory.getLogger( JdbcSupport.class );

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String CONNECTOR = ", ";

	/**
	 * 通过ID查询.
	 * 
	 * @param tableName
	 * @param id
	 * @return
	 */
	public Map<String, Object> get( final String tableName, final Serializable id ) {
		StringBuffer countSql = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		countSql.append( "SELECT COUNT( * ) FROM " ).append( tableName ).append( " WHERE id = ?" );
		sql.append( "SELECT * FROM " ).append( tableName ).append( " WHERE id = ?" );

		int count = this.jdbcTemplate.queryForObject( countSql.toString(),new Object[]{id},Integer.class  );
		if ( 0 == count ) {
			return null;
		} else {
			return this.jdbcTemplate.queryForMap( sql.toString(), id );
		}
	}

	/**
	 * 根据条件统计记录数.
	 * 
	 * @param tableName
	 * @param params
	 * @param ignoreProperties
	 * @return
	 */
	protected long count( final String tableName, final Map<String, Object> params, final String[] ignoreProperties ) {

		Assert.hasText( tableName, "表名不能为空." );

		List<Object> paramList = new ArrayList<Object>();
		// 过滤.
		Map<String, Object> ignoreParams = ignore( params, ignoreProperties );

		StringBuffer sql = new StringBuffer( "SELECT COUNT( * ) FROM " ).append( tableName );

		// 含有条件.
		if ( null != params ) {
			sql.append( " WHERE 1 = 1" );
			for ( String key : ignoreParams.keySet() ) {
				// 过滤为NULL.
				if ( null != params.get( key ) ) {
					sql.append( " AND " ).append( key ).append( " = ?" );
					paramList.add( params.get( key ) );
				}
			}
		}

		logger.info( "count() SQL: " + sql.toString() + "; params: " + paramList );

		return this.jdbcTemplate.queryForObject( sql.toString(),paramList.toArray(),Long.class );
	}

	/**
	 * 根据条件查询记录.
	 * 
	 * @param tableName
	 * @param params
	 * @param ignoreProperties
	 * @return
	 */
	protected List<Map<String, Object>> find( final String tableName, final Map<String, Object> params, final String[] ignoreProperties ) {

		Assert.hasText( tableName, "表名不能为空." );

		List<Object> paramList = new ArrayList<Object>();
		// 过滤.
		Map<String, Object> ignoreParams = ignore( params, ignoreProperties );

		StringBuffer sql = new StringBuffer( "SELECT * FROM " ).append( tableName );

		// 含有条件.
		if ( null != params ) {
			sql.append( " WHERE 1 = 1" );
			for ( String key : ignoreParams.keySet() ) {
				// 过滤为NULL.
				if ( null != params.get( key ) ) {
					sql.append( " AND " ).append( key ).append( " = ?" );
					paramList.add( ignoreParams.get( key ) );
				}
			}
		}
		// 默认id升序.
		sql.append( " ORDER BY id ASC" );

		logger.info( "find() SQL: " + sql.toString() + "; params: " + paramList );

		return this.jdbcTemplate.queryForList( sql.toString(), paramList.toArray() );
	}

	/**
	 * 根据条件查询唯一记录.
	 * 
	 * @param tableName
	 * @param params
	 * @param ignoreProperties
	 * @return
	 */
	protected Map<String, Object> findUnique( final String tableName, final Map<String, Object> params, final String[] ignoreProperties ) {

		Assert.hasText( tableName, "表名不能为空." );

		List<Object> paramList = new ArrayList<Object>();
		// 过滤.
		Map<String, Object> ignoreParams = ignore( params, ignoreProperties );

		StringBuffer sql = new StringBuffer( "SELECT * FROM " ).append( tableName );

		// 含有条件.
		if ( null != params ) {
			sql.append( " WHERE 1 = 1" );
			for ( String key : ignoreParams.keySet() ) {
				// 过滤为NULL.
				if ( null != params.get( key ) ) {
					sql.append( " AND " ).append( key ).append( " = ?" );
					paramList.add( ignoreParams.get( key ) );
				}
			}
		}
		// 默认id升序.
		sql.append( " ORDER BY id ASC" );

		logger.info( "find() SQL: " + sql.toString() + "; params: " + paramList );

		return this.jdbcTemplate.queryForMap( sql.toString(), paramList.toArray() );
	}

	/**
	 * 插入.
	 * 
	 * @param tableName
	 * @param values
	 * @param ignoreProperties
	 */
	protected void insert( final String tableName, final Map<String, Object> values, final String[] ignoreProperties ) {
		StringBuffer sql = new StringBuffer();
		StringBuffer keySql = new StringBuffer();
		StringBuffer valueSql = new StringBuffer();

		List<Object> valueList = new ArrayList<Object>();
		// 过滤.
		Map<String, Object> ignoreValues = ignore( values, ignoreProperties );
		for ( String key : ignoreValues.keySet() ) {
			keySql.append( key ).append( CONNECTOR );
			valueSql.append( "?" ).append( CONNECTOR );
			valueList.add( values.get( key ) );
		}
		String key = StringUtils.removeEnd( keySql.toString(), CONNECTOR );
		String value = StringUtils.removeEnd( valueSql.toString(), CONNECTOR );

		sql.append( "INSERT INTO " ).append( tableName );
		sql.append( "( " ).append( key ).append( " )" );
		sql.append( " VALUES( " ).append( value ).append( " )" );

		logger.info( "insert() SQL: " + sql.toString() + "; params: " + valueList );

		this.jdbcTemplate.update( sql.toString(), valueList.toArray() );
	}

	/**
	 * 修改.
	 * 
	 * @param tableName
	 * @param params
	 * @param ignoreParamsProperties
	 * @param values
	 * @param ignoreValuesProperties
	 */
	protected void update( final String tableName, final Map<String, Object> params, final String[] ignoreParamsProperties,
			final Map<String, Object> values, final String[] ignoreValuesProperties ) {
		StringBuffer sql = new StringBuffer();
		StringBuffer paramSql = new StringBuffer();
		StringBuffer valueSql = new StringBuffer();

		List<Object> list = new ArrayList<Object>();

		/* 过滤. */
		Map<String, Object> ignoredParams = ignore( params, ignoreParamsProperties );
		Map<String, Object> ignoredValues = ignore( values, ignoreValuesProperties );
		/* 值. */
		for ( String key : ignoredValues.keySet() ) {
			valueSql.append( key ).append( " = ?" ).append( CONNECTOR );
			list.add( values.get( key ) );
		}
		/* 条件. */
		for ( String key : ignoredParams.keySet() ) {
			paramSql.append( " AND " ).append( key ).append( " = ?" );
			list.add( params.get( key ) );
		}
		String value = StringUtils.removeEnd( valueSql.toString(), CONNECTOR );

		sql.append( "UPDATE " ).append( tableName );
		sql.append( " SET " ).append( value );
		sql.append( " WHERE 1 = 1" ).append( paramSql.toString() );

		logger.info( "update() SQL: " + sql.toString() + "; values, params: " + list );

		this.jdbcTemplate.update( sql.toString(), list.toArray() );
	}

	/**
	 * 将需要忽略的字段从参数里过滤掉.
	 * 
	 * @param params
	 * @param ignoreProperties
	 * @return
	 */
	public static Map<String, Object> ignore( final Map<String, Object> params, final String[] ignoreProperties ) {
		// 忽略参数为NULL或长度为0或参数为NULL.
		if ( null == ignoreProperties || 0 == ignoreProperties.length || null == params ) {
			return params;
		}
		// 非NULL.
		else {
			List<String> ignoreList = Arrays.asList( ignoreProperties );
			for ( String ignoreKey : ignoreList )
				if ( params.containsKey( ignoreKey ) )
					params.remove( ignoreKey );
			return params;
		}
	}

}