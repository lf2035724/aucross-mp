package com.google.code.lightssh.project.security.shiro;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.lightssh.common.support.shiro.TemporaryAuthorizationService;
import com.google.code.lightssh.project.security.entity.AuthorizedResource;
import com.google.code.lightssh.project.security.entity.AuthorizedTicket;
import com.google.code.lightssh.project.security.service.AuthorizedResourceManager;
import com.google.code.lightssh.project.security.service.AuthorizedTicketManager;

/** 
 * @author YangXiaojin
 * @date 2013-3-7
 * @description：TODO
 */
public class MyTemporaryAuthorizationService implements TemporaryAuthorizationService{
	
	private static Logger log = LoggerFactory.getLogger(MyTemporaryAuthorizationService.class);
	
	public static final String DEFAULT_TICKET_PARAM_NAME = "auth.ticket";
	
	/**
	 * 参数名称
	 */
	private String ticketName = DEFAULT_TICKET_PARAM_NAME;
	
	private AuthorizedTicketManager authorizedTicketManager;
	
	@Resource( name = "authorizedResourceManager" )
	private AuthorizedResourceManager authorizedResourceManager;

	public void setAuthorizedTicketManager(
			AuthorizedTicketManager authorizedTicketManager) {
		this.authorizedTicketManager = authorizedTicketManager;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}
	
	/**
	 * 令牌值
	 */
	protected String getTicketValue(ServletRequest req){
		if( req instanceof HttpServletRequest )
			return ((HttpServletRequest)req).getParameter(ticketName);
		
		return null;
	}
	
	/**
	 * 是否检查令牌
	 */
	protected boolean isCheckTicket(ServletRequest req){
		//String ticket_id = getTicketValue( req );
		HttpServletRequest request = (HttpServletRequest)req;
		AuthorizedResource authorizedResource = authorizedResourceManager.getWithRegexp(request.getRequestURI());
		return authorizedResource != null;
	}

	@Override
	public boolean authorize(String perms[],ServletRequest req) {
		if( perms == null || perms.length != 1 )
			return false;
		
		if( !(req instanceof HttpServletRequest ) )
			return false;
		
		HttpServletRequest request = ((HttpServletRequest)req);
		if( isCheckTicket(request) ){
			String sessionid=request.getSession().getId();
			String ticketid = getTicketValue(request);
			if(ticketid == null) {
				log.info("没有授权的请求："+request.getRequestURI());
				return false;
			}
			AuthorizedTicket ticket = authorizedTicketManager.get(
					ticketid,perms[0],request.getRequestURI(), sessionid);
			
			if( ticket == null ){
				log.debug("无法查询到有效授权令牌,[url="+request.getRequestURI()
						+"ticket="+ticketid+",sessionid="+sessionid+"]。");
				return false;
			}
			
			if( !ticket.isEffective() ){
				log.info("授权令牌[Scope={}][ticket="+getTicketValue(request)
						+"]已失效！",ticket.getScope().name());
				return false;
			}
			
			if( ticket.isOnceScope() ){
				if( authorizedTicketManager.invalidTicketAfterAuth(
						ticketid,sessionid,Calendar.getInstance()) )
					log.info("授权令牌[Scope=ONCE][ticket="+ticketid+"]使用后标记为失效！");
			}else{
				if( authorizedTicketManager.incAuthCount(ticketid,sessionid) )
					log.info("授权令牌[Scope={}][ticket="+ticketid
							+"]使用后认证次数+1！",ticket.getScope().name());
			}
		}
		return true;
	}

}
