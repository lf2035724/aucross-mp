package com.ylink.modules.test.spring;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Spring的支持数据库访问, 事务控制和依赖注入的JUnit4 集成测试基类.
 * 
 * 子类需要定义applicationContext文件的位置, 如:
 * 
 * @Transactional
 * @ActiveProfiles( "test" )
 * @RunWith( SpringJUnit4ClassRunner.class )
 * @ContextConfiguration(locations = { "/applicationContext.xml" })
 * @TransactionConfiguration( transactionManager = "defaultTransactionManager" )
 * 
 * @author 潘瑞峥
 * @date 2012-06-15
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = "classpath:config/spring/applicationContext-sms.xml" )
@TransactionConfiguration( transactionManager = "jdbcTransactionManager" )
public abstract class SpringTransactionalTestCase {}