package com.google.code.lightssh.project.remoting.hessian;

import javax.annotation.Resource;

import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import com.google.code.lightssh.common.config.SystemConfig;

/**
 * 扩展HessianProxyFactoryBean
 * @author YangXiaojin
 *
 */
public class MyHessianProxyFactoryBean extends HessianProxyFactoryBean{
	
	/**
	 * 远程服务系统名称前缀
	 */
	public static final String REMOTING_SYSTEM_PREFIX = "remoting.system.";
	
	/**
	 * 连接超时KEY
	 */
	public static final String REMOTING_CONN_TIMEOUT_KEY = "remoting.connect.timout";
	
	/**
	 * 读超时KEY
	 */
	public static final String REMOTING_READ_TIMEOUT_KEY = "remoting.read.timout";
	
	/**
	 * 连接超时
	 */
	private int connectTimeout = 5000;

	/**
	 * 读超时
	 */
    private int readTimeout = 5000;
	
	/**
	 * 子系统名称
	 */
	private String system;
	
	/** 
	 * 系统参数 
	 */
	@Resource
	private SystemConfig systemConfig;
	
	/**
	 * 扩展Hessian代理
	 */
	private MyHessianProxyFactory proxyFactory = new MyHessianProxyFactory();
	
	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	/**
	 * 系统远程服务地址前缀
	 */
	protected String getServiceUrlPrefix( ){
		if( systemConfig != null ){
			return systemConfig.getProperty(REMOTING_SYSTEM_PREFIX + system );
		}
		
		return null;
	}
	
	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getServiceUrl() {
		String serviceAction = super.getServiceUrl();
		if( serviceAction != null && serviceAction.startsWith("http"))
			return serviceAction;
		
		String prefix = getServiceUrlPrefix();
		if( prefix != null && serviceAction != null ){
			if( prefix.endsWith("/") && serviceAction.startsWith("/") )
				prefix = prefix.substring(0, prefix.length()-1);
			
			if( !prefix.endsWith("/") && !serviceAction.startsWith("/") )
				prefix = prefix + "/";
		}
		
		//System.out.println("--------------------->:"+(prefix==null?"":prefix) + serviceAction);
		return (prefix==null?"":prefix) + serviceAction;
	}
	
	private void initConfig(){
		if( systemConfig != null ){
			try{
				String connTimeoutTxt = systemConfig.getProperty(REMOTING_CONN_TIMEOUT_KEY);
				this.connectTimeout = Integer.parseInt(connTimeoutTxt );
			}catch( Exception e ){
				//ignore
			}
			
			try{
				String readTimeoutTxt = systemConfig.getProperty(REMOTING_READ_TIMEOUT_KEY);
				this.readTimeout = Integer.parseInt(readTimeoutTxt );
			}catch( Exception e ){
				//ignore
			}
		}
	}
	
    public void afterPropertiesSet() {
    	initConfig();
    	
        proxyFactory.setReadTimeout(readTimeout);
        proxyFactory.setConnectTimeout(connectTimeout);
        proxyFactory.setOverloadEnabled( true ); //重载支持
        setProxyFactory(proxyFactory);
        super.afterPropertiesSet();
    }

}
