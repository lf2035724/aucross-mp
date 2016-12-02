package com.ylink.ylpay.project.mp.user.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.web.SessionKey;
import com.google.code.lightssh.project.security.entity.LoginAccount;
import com.ylink.modules.web.struts2.CustomAction;
import com.ylink.ylpay.project.mp.basic.entity.UserVo;
import com.ylink.ylpay.project.mp.user.entity.User;
import com.ylink.ylpay.project.mp.user.entity.UserChange;
import com.ylink.ylpay.project.mp.user.service.UserManager;

/**
 * 门户用户业务层.
 * 
 * @author 潘瑞峥
 * @date 2012-10-27
 */
@Component( "userAction" )
@Scope( "prototype" )
public class UserAction extends CustomAction<User> {

	private static final long serialVersionUID = -1945277635638326053L;

	private UserManager userManager;
	private ListPage<User> page = new ListPage<User>();
	ListPage<UserChange> pageUserChange = new ListPage<UserChange>();
	private User user;
	private UserVo userVo;
	private UserChange userChange;
	private String remark;
	private String userChangeId;
	@Resource( name = "userManager" )
	public void setUserManager( UserManager userManager ) {
		this.userManager = userManager;
		super.manager = this.userManager;
	}

	public User getUser() {
		user = super.model;
		return user;
	}

	public void setUser( User user ) {
		super.model = user;
		this.user = user;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	public ListPage<User> getPage() {
		return page;
	}

	public void setPage(ListPage<User> page) {
		this.page = page;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ListPage<UserChange> getPageUserChange() {
		return pageUserChange;
	}

	public void setPageUserChange(ListPage<UserChange> pageUserChange) {
		this.pageUserChange = pageUserChange;
	}
	public String getUserChangeId() {
		return userChangeId;
	}

	public void setUserChangeId(String userChangeId) {
		this.userChangeId = userChangeId;
	}

	public UserChange getUserChange() {
		return userChange;
	}

	public void setUserChange(UserChange userChange) {
		this.userChange = userChange;
	}

	/**
	 * 列表.
	 */
	@Override
	public String list() {
//		if ( null == super.getPage() ) {
//			super.setPage( new ListPage<User>() );
//		}
//		return 
		request.setAttribute("userVo", userVo);
			this.page=userManager.list(page, userVo);
			return SUCCESS;
		
 
	}
	/**
	 * 冻结用户管理.
	 */
	public String freezeList() {
		request.setAttribute("userVo", userVo);
		if(userVo!=null&&!StringUtils.isEmpty(userVo.getStatus())){
			if("1".equals(userVo.getStatus())){
				userVo.setEnabled(true);
			}else{
				userVo.setEnabled(false);
			}
		}
		ListPage<User> list = userManager.list(page, userVo);
		List<User> resultList = new ArrayList<User>();
		if(list == null||list.getList().size()<1){
			this.page = list;
		}else{
			List<User> users = list.getList();
			for(User u: users){
				if(u.getEnabled()){
					u.setStatus("1");
				}else if(u.getStatus()==null){
					u.setStatus("0");
				}
				resultList.add(u);
			}
			list.setList(resultList);
		}
		this.page=list;
		return SUCCESS;
	}
	/**冻结或解冻用户*/
	public String activateOrFreezeUser(){
		Long userId = userVo.getId();
		boolean enabled = userVo.getEnabled();
		String status = userVo.getStatus();
		this.userManager.activateOrFreezeUser(userId,enabled);
		this.userManager.updateUserStatus(userId, status);
		return SUCCESS;
	}
	
	/**注销用户*/
	public String cancelUser(){
		Long userId = userVo.getId();
		boolean enabled = userVo.getEnabled();
		String status = userVo.getStatus();
		this.userManager.activateOrFreezeUser(userId,enabled);
		this.userManager.updateUserStatus(userId,status);
		return SUCCESS;
	}
	
	/**恢复用户*/
	public String unCancelUser(){
		Long userId = userVo.getId();
		boolean enabled = userVo.getEnabled();
		String status = userVo.getStatus();
		this.userManager.activateOrFreezeUser(userId,enabled);
		this.userManager.updateUserStatus(userId, status);
		return SUCCESS;
	}
	/**
	 * 禁用或激活的明细.
	 * @deprecated
	 */
	public String activateOrDisableDetail() {
		return super.detail();
	}

//	/**
//	 * 禁用或激活.
//	 */
//	public String activateOrDisable() throws Exception {
//		Long userId = this.getModel().getId();
//		boolean enabled = this.getModel().getEnabled();
//		this.userManager.activateOrDisable( userId, enabled );
//		return SUCCESS;
//	}
	/**
	 * 登录帐户
	 */
	public LoginAccount getLoginAccount( ){
		return (LoginAccount)request.getSession().getAttribute( SessionKey.LOGIN_ACCOUNT );
	}
	/**
	 * 禁用或激活.
	 * @deprecated
	 */
	public String activateOrDisable() throws Exception {
		Long userId = this.getModel().getId();
		boolean enabled = this.getModel().getEnabled();
//		this.userManager.activateOrDisable( userId, enabled );
		this.userManager.activateOrDisable(userId, this.getLoginAccount(), enabled, this.remark);
		return SUCCESS;
	}
	
	/**
	 * 所有待禁用或激活审核列表
	 * @description 
	 * @return  
	 * @author Leo
	 * @date 2013-2-25
	 * @deprecated
	 */
	public String listUserChange() {
			request.setAttribute("userVo", userVo);
			this.pageUserChange=userManager.listUserChange(pageUserChange, userVo );
			return SUCCESS;
	}
	
	public String todoAudit(){
		this.userChange=userManager.getUserChange(userChangeId);
		request.setAttribute("userChange", userManager.getUserChange(userChangeId));
		return SUCCESS;
	}
	/**
	 * 审核
	 */
	public String audit(){
		if( userChangeId == null ||"".equals(userChangeId)){
			this.saveErrorMessage("审核参数为空！");
			return ERROR;
		}
		
		boolean passed = request.getParameter("passed")!=null;
		boolean reject = request.getParameter("reject")!=null;
		String description = request.getParameter("description");
		try{
			boolean rs=false;
			if(passed){
				rs= true;
			}
			if(reject){
				rs= false;
			}
			userManager.audit(userChangeId,this.getLoginAccount(),rs, description);
		}catch(Exception e ){
			this.saveErrorMessage("审核操作异常："+e.getMessage());
			return INPUT;
		}
		
		return SUCCESS;
	}

}