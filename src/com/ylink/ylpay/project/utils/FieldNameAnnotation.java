package com.ylink.ylpay.project.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author feng.li
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldNameAnnotation {
	//对应FieldName
	String name();
}
