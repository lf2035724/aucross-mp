package com.google.code.lightssh.project.security.service;

import java.util.List;

import com.google.code.lightssh.common.service.BaseManager;
import com.google.code.lightssh.project.log.entity.Access;
import com.google.code.lightssh.project.party.entity.Party;
import com.google.code.lightssh.project.security.entity.LoginAccount;
import com.google.code.lightssh.project.security.entity.Permission;

/**
 * LoginAccount Manager
 * 
 * @author YangXiaojin
 * 
 */
public interface LoginAccountManager extends BaseManager<LoginAccount> {

	/**
	 * 根据名称查询登录账号
	 */
	public LoginAccount get( String name );

	/**
	 * 根据电子邮箱查登录帐号
	 */
	public LoginAccount getByEmail( String email );

	/**
	 * 根据名称查询登录账号,只查询当前实现（不关联查询）
	 */
	public LoginAccount getLight( String name );

	/**
	 * 根据名称查询登录账号，如果关联了Party一并带出
	 */
	public LoginAccount getWithParty( String name );

	/**
	 * 初始化系统管理员登录账号
	 */
	public void initLoginAccount();

	/**
	 * 保存登录帐号
	 */
	public void save( LoginAccount account, LoginAccount operator );

	/**
	 * 更新密码
	 */
	public void updatePassword( String name, String password, String newPassword );

	/**
	 * 重置密码
	 * 
	 * @param name
	 *            登录帐号
	 * @param newPassword
	 *            新密码
	 */
	public void resetPassword( String name, String newPassword );

	/**
	 * 查询交易所管理员
	 */
	public List<LoginAccount> listAdmin();

	/**
	 * 查询 拥有某个权限的 登录账户
	 */
	public List<LoginAccount> listByPermission( Permission permission );

	/**
	 * 查询 拥有某个权限的 登录账户
	 */
	public List<LoginAccount> listByPermission( String permission );

	/**
	 * 启用或禁用CA登录
	 */
	public void toggleCa( LoginAccount account, Access log );

	/**
	 * 查询会员登录帐户
	 */
	public List<LoginAccount> listByParty( Party party );

	/**
	 * 登录失败锁定时间
	 */
	public void updateLockTime( LoginAccount la );

	/**
	 * 删除登录帐号
	 */
	public void remove( LoginAccount account, LoginAccount operator, String remark );

	/**
	 * 排除ID, 并根据属性名和属性值查询.
	 * 
	 * @author 潘瑞峥
	 * @date 2013-04-25
	 * 
	 * @param id
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	public LoginAccount get( Long id, String propertyName, String propertyValue );

}