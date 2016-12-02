package com.ylink.modules.orm.hibernate.type;

import java.util.Map;

/**
 * 自定义Hibernate持久化枚举接口.<br>
 * 实体需要用枚举关联, 枚举必须实现该接口.
 * 
 * <pre>
 * 实体映射eg.
 * <code>@Column( name = "WORK_FLAG" )</code>
 * <code>@Type( type = CustomEnumType.ENUM_TYPE,</code>
 * <code>  parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.WorkDayType" ) } )</code>
 * </pre>
 * 
 * @author 潘瑞峥
 * @date 2012-11-3
 */
public interface PersistentEnum<E extends Enum<?>, T> {

	T getValue();

	String getDisplayName();

	E getEnum( T value );

	Map<T, E> getAllValueMap();

}