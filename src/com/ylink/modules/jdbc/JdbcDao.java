package com.ylink.modules.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

/**
 * Curd Jdbc.
 * 
 * @author 潘瑞峥
 * @date 2012-11-9
 */
public class JdbcDao extends JdbcSupport {

	/**
	 * 查询所有记录数.
	 * 
	 * @param tableName
	 * @return
	 */
	public long countAll( final String tableName ) {
		return super.count( tableName, null, null );
	}

	/**
	 * 查询全部.
	 * 
	 * @param tableName
	 * @return
	 */
	public List<Map<String, Object>> findAll( final String tableName ) {
		return super.find( tableName, null, null );
	}

	/**
	 * 条件统计记录数.
	 * 
	 * @param tableName
	 * @param params
	 * @return
	 */
	public long count( final String tableName, final Map<String, Object> params ) {
		return super.count( tableName, params, null );
	}

	/**
	 * 条件统计记录数, 带过滤.
	 * 
	 * @param tableName
	 * @param params
	 * @param ignoreProperties
	 * @return
	 */
	public long count( final String tableName, final Map<String, Object> params, final String[] ignoreProperties ) {
		return super.count( tableName, params, ignoreProperties );
	}

	/**
	 * 条件查询.
	 * 
	 * @param tableName
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> find( final String tableName, final Map<String, Object> params ) {
		return super.find( tableName, params, null );
	}

	/**
	 * 条件查询, 带过滤.
	 * 
	 * @param tableName
	 * @param params
	 * @param ignoreProperties
	 * @return
	 */
	public List<Map<String, Object>> find( final String tableName, final Map<String, Object> params, final String[] ignoreProperties ) {
		return super.find( tableName, params, ignoreProperties );
	}

	/**
	 * 条件查询.
	 * 
	 * @param tableName
	 * @param params
	 * @return
	 */
	public Map<String, Object> findUnique( final String tableName, final Map<String, Object> params ) {
		return super.findUnique( tableName, params, null );
	}

	/**
	 * 条件查询, 带过滤.
	 * 
	 * @param tableName
	 * @param params
	 * @param ignoreProperties
	 * @return
	 */
	public Map<String, Object> findUnique( final String tableName, final Map<String, Object> params, final String[] ignoreProperties ) {
		return super.findUnique( tableName, params, ignoreProperties );
	}

	/**
	 * 插入.
	 * 
	 * @param tableName
	 * @param values
	 */
	public void insert( final String tableName, final Map<String, Object> values ) {
		super.insert( tableName, values, null );
	}

	/**
	 * 插入, 带过滤.
	 * 
	 * @param tableName
	 * @param values
	 * @param ignoreProperties
	 */
	public void insert( final String tableName, final Map<String, Object> values, final String[] ignoreProperties ) {
		super.insert( tableName, values, ignoreProperties );
	}

	/**
	 * 修改.
	 */
	public void update( final String tableName, final Map<String, Object> params, final Map<String, Object> values ) {
		super.update( tableName, params, null, values, null );
	}

	/**
	 * 修改, 带过滤.
	 */
	public void update( final String tableName, final Map<String, Object> params, final String[] ignoreParamsProperties,
			final Map<String, Object> values ) {
		super.update( tableName, params, ignoreParamsProperties, values, null );
	}

	/**
	 * 修改, 带过滤.
	 */
	public void update( final String tableName, final Map<String, Object> params, final Map<String, Object> values,
			final String[] ignoreValuesProperties ) {
		super.update( tableName, params, null, values, ignoreValuesProperties );
	}

	/**
	 * 修改, 带过滤.
	 */
	public void update( final String tableName, final Map<String, Object> params, final String[] ignoreParamsProperties,
			final Map<String, Object> values, final String[] ignoreValuesProperties ) {
		super.update( tableName, params, ignoreParamsProperties, values, ignoreValuesProperties );
	}

	public static void main(String[] args) {
		String driverName = "com.mysql.jdbc.Driver"; 
		String dbURL = "jdbc:mysql://localhost/evaluation?characterEncoding=utf8"; 
		String userName = "root"; 
		String userPwd = "123qwe"; 
		Connection dbConn; 
		try { 
		Class.forName(driverName); 
		dbConn = DriverManager.getConnection(dbURL, userName, userPwd); 
		System.out.println("Connection Successful!"); 
		}catch (Exception e) { 
		e.printStackTrace(); 
		} 
		} 
	
}