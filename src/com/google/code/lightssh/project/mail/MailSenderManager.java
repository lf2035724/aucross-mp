package com.google.code.lightssh.project.mail;

import com.google.code.lightssh.project.security.entity.LoginAccount;

/**
 * 邮件发送
 *
 */
public interface MailSenderManager{
	
	/**
	 * 忘记用户名
	 */
	public void forgotUsername(String title, String email );
	
	/**
	 * 忘记密码
	 */
	public void forgotPassword(String retrieveUrl, LoginAccount account );
	
}
