/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司-证联融通电子有限公司
 * 创建:yu.han 2013-2-27
 */

/**
 * DreamNetServiceImpl.java
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司-证联融通电子有限公司 
 * 创建:yu.han 2013-2-27
 */
package com.ylink.ylpay.project.sms.repository;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
//import org.tempuri.Wmgw;

import com.google.code.lightssh.common.config.SystemConfig;
import com.ylink.ylpay.project.sms.constant.Constant;

/** 
 * @author yu.han
 * @date 2013-2-27
 * @description：短信发送实现类
 */

/**
 * @author yu
 * 
 */
@Repository
public class DreamNetServiceImpl {
//
//	/** 系统参数 */
//	@Resource(name="systemConfig")
//	private SystemConfig systemConfig;
//	private final static Log log = LogFactory.getLog(DreamNetServiceImpl.class);
//	private String userId=null;
//	private String password=null;
//	private String subPort=null;
//	private String serverurl;
//	private Wmgw wmgw ;
//	/**
//	 * 发送短信
//	 *
//	 * @description 梦网发送短信
//	 * @param mobiles
//	 * @param msg
//	 * @return
//	 * @author yu.han
//	 * @date 2013-2-27
//	 */
//	public String sendSms(String mobiles, String msg) {
//		init();
//		String[] mobile=mobiles.split(",");
//		return wmgw.getWmgwSoap().mongateCsSpSendSmsNew(userId,password,mobiles, msg, mobile.length, subPort);
//	}
//
//	/**
//	 * 查询余额
//	 * @description
//	 * @return
//	 * @author yu.han
//	 * @date 2013-4-3
//	 */
//	public int queryBalance() {
//		init();
//		return wmgw.getWmgwSoap().mongateQueryBalance(userId, password);
//	}
//
//	void init(){
//		if(serverurl==null){
//			serverurl=systemConfig.getProperty(Constant.DREAMNET_SERVERURL_KEY);
//		}
//		if(userId==null){
//			userId=systemConfig.getProperty(Constant.DREAMNET_UESRNAME_KEY);
//		}
//		if(password==null){
//			password=systemConfig.getProperty(Constant.DREAMNET_PASSWORD_KEY);
//		}
//		if(subPort==null){
//			subPort=systemConfig.getProperty(Constant.DREAMNET_SUBPORT_KEY);
//		}
//		if(wmgw==null){
//			try {
//				wmgw =new Wmgw(new URL(serverurl));
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//				log.error(e.getMessage(),e);
//				throw new RuntimeException(e);
//			}
//		}
//	}
}
