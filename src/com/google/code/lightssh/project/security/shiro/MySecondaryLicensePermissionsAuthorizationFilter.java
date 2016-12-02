package com.google.code.lightssh.project.security.shiro;

import java.io.IOException;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.RegExPatternMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.lightssh.common.support.shiro.MyPermissionsAuthorizationFilter;
import com.google.code.lightssh.project.security.entity.AuthorizedResource;
import com.google.code.lightssh.project.security.entity.AuthorizedTicket;
import com.google.code.lightssh.project.security.service.AuthorizedResourceManager;
import com.google.code.lightssh.project.security.service.AuthorizedTicketManager;

/** 
 * @author feng.li
 * @date 2014-5-19
 * @description：TODO
 */

/**
 * @author feng.li
 *
 */
public class MySecondaryLicensePermissionsAuthorizationFilter extends MyPermissionsAuthorizationFilter{

	
	@Resource( name = "authorizedResourceManager" )
	private AuthorizedResourceManager authorizedResourceManager;
	
	private static Logger log = LoggerFactory.getLogger(MySecondaryLicensePermissionsAuthorizationFilter.class);
	
	private AuthorizedTicketManager authorizedTicketManager;
	
	public MySecondaryLicensePermissionsAuthorizationFilter( ){
		this( true );
	}
	
	public MySecondaryLicensePermissionsAuthorizationFilter( boolean regexExp ){
		super();
		
		this.regexExpMatcher = regexExp;
		if( regexExp ){
			pathMatcher = new RegExPatternMatcher();
		}
	}
	
	 /**
     * 是否允许访问
     */
	@Override
    public boolean isAccessAllowed(ServletRequest request,
    		ServletResponse response, Object mappedValue) throws IOException {

        Subject subject = getSubject(request, response);
        String[] perms = (String[]) mappedValue;

        boolean isPermitted = true;
        String targetUrl = request.getParameter( "targetUrl" );
        if(targetUrl == null){
        	HttpServletRequest httpServletRequest = (HttpServletRequest)request; 
        	targetUrl = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
        }
        AuthorizedResource authResource = authorizedResourceManager.getWithRegexp( "/"+targetUrl );
        if(authResource != null){
        	isPermitted = checkTempAuth((HttpServletRequest)request,perms);
        }else{
        	if(perms != null && perms.length > 0) {
//            	boolean tempAuthed = tempAuthService.authorize(perms,request);
                if (perms.length == 1) {
                    if(!subject.isPermitted(perms[0])) {
                        isPermitted = false;
                    }
                }else{
                    if(!subject.isPermittedAll(perms)) {
                        isPermitted = false;
                    }
                }
            }
        }
        return isPermitted;
    }
	
	private boolean checkTempAuth(HttpServletRequest request,String perms[]){
		String sessionid=request.getSession().getId();
		String ticketid = getTicketValue(request);
		if(StringUtils.isEmpty(ticketid)) {
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
		return true;
	}
	
	/**
	 * 令牌值
	 */
	protected String getTicketValue(ServletRequest req){
		if( req instanceof HttpServletRequest )
			return ((HttpServletRequest)req).getParameter(MyTemporaryAuthorizationService.DEFAULT_TICKET_PARAM_NAME);
		return null;
	}
	
	public void setAuthorizedTicketManager(
			AuthorizedTicketManager authorizedTicketManager) {
		this.authorizedTicketManager = authorizedTicketManager;
	}
}
