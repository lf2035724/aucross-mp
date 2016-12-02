/**
 * 版权所有(C) 2015 证联融通电子有限公司
 * 创建:feng.li 2015-1-8
 */

/**
 * AbstractJunitTestCase.java
 * 版权所有(C) 2015 证联融通电子有限公司
 * 创建:feng.li 2015-1-8
 */
package com.google.code.lightssh.project.test;

/** 
 * @author feng.li
 * @date 2015-1-8
 * @description：TODO
 */

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/** 
 * @author feng.li
 * @date 2015-1-8
 * @description：junit测试抽象类
 */
@ContextConfiguration(locations = {
		"classpath*:config/spring/applicationContext.xml",
		"classpath*:config/spring/applicationContext-no-annotation.xml",
		"classpath*:config/spring/applicationContext-cache.xml",
		"classpath*:config/spring/applicationContext-security.xml",
		"classpath*:config/spring/applicationContext-remoting.xml",
		"classpath*:config/spring/applicationContext-scheduler.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public abstract class AbstractJunitTestCase extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private ApplicationContext ctx;

	public ApplicationContext getCtx() {
		return ctx;
	}

	public void setCtx(ApplicationContext ctx) {
		this.ctx = ctx;
	}
	
}

