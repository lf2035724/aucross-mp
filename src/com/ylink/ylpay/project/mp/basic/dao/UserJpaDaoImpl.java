/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 * 创建:Leo 2013-2-22
 */

/**
 * UserDaoJpa.java
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 * 创建:Leo 2013-2-22
 */
package com.ylink.ylpay.project.mp.basic.dao;

import org.springframework.stereotype.Repository;

import com.google.code.lightssh.common.model.page.ListPage;
import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.basic.entity.UserVo;
import com.ylink.ylpay.project.mp.user.entity.User;

/** 
 * @author Leo
 * @date 2013-2-22
 * @description：TODO
 */

/**
 * @author Leo
 *
 */
@Repository("userJpaDao")
public class UserJpaDaoImpl  extends CustomJpaDaoImpl<User> implements UserJpaDao {

	private static final long serialVersionUID = 7791044823815435321L;

	public ListPage<User> list(ListPage<User> page, UserVo userVo) {

		StringBuffer hql = new StringBuffer();

		hql.append(" FROM User  AS m ");
		hql.append(" WHERE 1=1 ");
		if(userVo!=null){
			if(!"".equals(userVo.getLoginName())&&userVo.getLoginName()!=null){
				hql.append(" and m.loginName like '"+userVo.getLoginName()+"'");
			}
			if(!"".equals(userVo.getCustomerId())&&userVo.getCustomerId()!=null){
				hql.append(" and m.customerId like '"+userVo.getCustomerId()+"'");			
			}
			if(!"".equals(userVo.getName())&&userVo.getName()!=null){
				hql.append(" and m.customerId in (SELECT p.id from Customer AS p where p.name like '%"+userVo.getName()+"%')");
			}
			if(!"".equals(userVo.getMobile())&&userVo.getMobile()!=null){
				hql.append(" and m.mobile like '"+userVo.getMobile()+"'");
			}
			if(!"".equals(userVo.getEmail())&&userVo.getEmail()!=null){
				hql.append(" and m.email like '"+userVo.getEmail()+"'");
			}
			if(userVo.getEnabled()!=null&&userVo.getEnabled()){
				hql.append(" and m.enabled =1");
			}
			if(userVo.getEnabled()!=null&&!userVo.getEnabled()){
				hql.append(" and m.enabled =0");
			}
			if("2".equals(userVo.getStatus())||"0".equals(userVo.getStatus())){
				hql.append(" and m.status ="+userVo.getStatus());
			}
		}
//		if (t.getQuestion() != null && t.getQuestion().trim() != null
//				&& !"".equals(t.getQuestion().trim())) {
//			hql.append(" AND m.question like ? ");
//			params.add("%" + t.getQuestion().trim() + "%");
//		}
//System.out.println(hql.toString());
		return super.query(page, hql.toString());
	}

}