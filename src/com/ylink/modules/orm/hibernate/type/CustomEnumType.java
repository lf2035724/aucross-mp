package com.ylink.modules.orm.hibernate.type;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.apache.commons.beanutils.MethodUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.CoreMessageLogger;
import org.hibernate.internal.util.ReflectHelper;
import org.hibernate.internal.util.compare.EqualsHelper;
import org.hibernate.type.EnumType;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.jboss.logging.Logger;

/**
 * 自定义Hibernate对Enum的持久化.<br>
 * 支持Enum的value为Number或String, 但Enum必须实现PersistentEnum接口.
 * 
 * @author 潘瑞峥
 * @date 2012-11-2
 */
@SuppressWarnings( "serial" )
public class CustomEnumType implements UserType, ParameterizedType, Serializable {

	private static final CoreMessageLogger LOG = Logger.getMessageLogger( CoreMessageLogger.class, CustomEnumType.class.getName() );

	public static final String ENUM_TYPE = "com.ylink.modules.orm.hibernate.type.CustomEnumType";

	private static final String GETENUM_METHOD_NAME = "getEnum";

	private static final String GETVALUE_METHOD_NAME = "getValue";

	private Class<Enum<?>> enumClass;

	private Object enumObject;

	private int sqlType = Types.VARCHAR;

	@Override
	@SuppressWarnings( "unchecked" )
	public void setParameterValues( Properties parameters ) {
		if ( null != parameters ) {
			String enumClassName = parameters.getProperty( EnumType.ENUM );
			try {
				enumClass = ReflectHelper.classForName( enumClassName, this.getClass() ).asSubclass( Enum.class );
				enumObject = enumClass.getEnumConstants()[ 0 ];
			} catch ( ClassNotFoundException exception ) {
				throw new HibernateException( "Enum class not found", exception );
			}
		}
	}

	@Override
	public Object nullSafeGet( ResultSet rs, String[] names, Object owner ) throws HibernateException,
			SQLException {

		Object param = rs.getObject( names[ 0 ] );
		Object returnVal = null;
		if ( rs.wasNull() ) {
			if ( LOG.isTraceEnabled() )
				LOG.tracev( "Returning null as column {0}", names[ 0 ] );
			return null;
		}

		try {
			/* 转换参数类型. */
			if ( param instanceof Number ) {
				returnVal = MethodUtils.invokeMethod( enumObject, GETENUM_METHOD_NAME, ( ( Number ) param ).intValue() );
			} else {
				returnVal = MethodUtils.invokeMethod( enumObject, GETENUM_METHOD_NAME, param );
			}
		} catch ( NoSuchMethodException e ) {
			e.printStackTrace();
		} catch ( IllegalAccessException e ) {
			throw new IllegalArgumentException( "Unknown name value for enum " + enumClass + ": " + param, e );
		} catch ( InvocationTargetException e ) {
			e.printStackTrace();
		}

		return returnVal;
	}

	@Override
	public void nullSafeSet( PreparedStatement st, Object value, int index) throws HibernateException,
			SQLException {

		/*
		 * 允许枚举映射为NULL.(2012-12-14 update)
		 */
		if ( value == null ) {
			if ( LOG.isTraceEnabled() )
				LOG.tracev( "Binding null to parameter: {0}", index );
			st.setNull( index, sqlType );
			st.setObject( index, null );
		} else {
			try {
				Object enumVal = MethodUtils.invokeMethod( value, GETVALUE_METHOD_NAME, null );
				if ( enumVal instanceof Number ) {
					sqlType = Types.INTEGER;
				}
				st.setObject( index, enumVal, sqlType );
			} catch ( NoSuchMethodException e ) {
				e.printStackTrace();
			} catch ( IllegalAccessException e ) {
				e.printStackTrace();
			} catch ( InvocationTargetException e ) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { sqlType };
	}

	@Override
	public Class<?> returnedClass() {
		return enumClass;
	}

	@Override
	public boolean equals( Object x, Object y ) throws HibernateException {
		return EqualsHelper.equals( x, y );
	}

	@Override
	public int hashCode( Object x ) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public Object deepCopy( Object value ) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble( Object value ) throws HibernateException {
		return ( Serializable ) value;
	}

	@Override
	public Object assemble( Serializable cached, Object owner ) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace( Object original, Object target, Object owner ) throws HibernateException {
		return original;
	}

}