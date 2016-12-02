package com.ylink.ylpay.project.mp.basic.dao;

import com.google.code.lightssh.common.model.page.ListPage;
import com.ylink.modules.orm.jpa.CustomJpaDao;
import com.ylink.ylpay.project.mp.basic.entity.UserVo;
import com.ylink.ylpay.project.mp.user.entity.User;

/**
 * 安全问题
 * 
 * @author XuWei
 * 
 */

public interface UserJpaDao extends CustomJpaDao<User> {
	public ListPage<User> list(ListPage<User> page, UserVo t);
}