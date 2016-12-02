/**
 * 版权所有(C) 2015 证联融通电子有限公司
 * 创建:feng.li 2015-1-8
 */

/**
 * SpringContextHolder.java
 * 版权所有(C) 2015 证联融通电子有限公司
 * 创建:feng.li 2015-1-8
 */
package com.google.code.lightssh.project.test;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/** 
 * @author feng.li
 * @date 2015-1-8
 * @description：TODO
 */

/**
 * @author feng.li
 *
 */
public class SpringContextHolder  implements ApplicationContextAware{
		/**
		 * 以静态变量保存Spring ApplicationContext,可在任何代码任何地方任何时候取出ApplicationContext.
		 */
	    private static ApplicationContext applicationContext;
	    /**
	     * 实现ApplicationContextAware接口的context注入函数，将其存入静态变量。
	     */
		@Override
		public void setApplicationContext(ApplicationContext applicationContext)
				throws BeansException {
			SpringContextHolder.applicationContext = applicationContext;
			// TODO Auto-generated method stub
		}
		/**
		 * 取得存储在静态变量中的ApplicationContext.
		 * @return
		 */
	    public static ApplicationContext getApplicationContext(){
	    	checkApplicationContext();
	    	return applicationContext;
	    }
	    /**
	     * 从静态变量ApplicationContext中取得Bean,自动转型为所赋值对象的类型。
	     * @param name
	     * @return
	     */
	    @SuppressWarnings("unchecked")
		public static<T> T getBean(String name){
	    	checkApplicationContext();
	    	return (T)applicationContext.getBean(name);
	    }
	    /**
	     * 从静态变量ApplicationContext中取得Bean,自动转型为所赋值对象的类型。
	     * @param name
	     * @return
	     */
	    @SuppressWarnings("unchecked")
		public static <T> T getBean(Class<T> clazz){
	    	checkApplicationContext();
	    	return (T)applicationContext.getBeansOfType(clazz);
	    }
	    /**
	     * 清除applicationContext静态变量。
	     */
	    public static void cleanApplicationContext(){
	    	applicationContext = null;
	    }
	    private static void checkApplicationContext(){
	    	if(applicationContext == null){
	    		throw new IllegalStateException("applicationContext未注入，请在applicationContext.xml中定义SpringContextHolder");
	    	}
	    }
}
