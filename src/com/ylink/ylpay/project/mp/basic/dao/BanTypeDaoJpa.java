package com.ylink.ylpay.project.mp.basic.dao;

import org.springframework.stereotype.Repository;
import com.google.code.lightssh.common.dao.jpa.JpaAnnotationDao;
import com.ylink.ylpay.project.mp.basic.entity.BanType;



@Repository("banTypeDao")
public class BanTypeDaoJpa extends JpaAnnotationDao<BanType> implements BanTypeDao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7902941148285862670L;

	@Override
	public BanType read(Class<?> clazz, BanType bantype) {
		if( bantype == null )
			return null;
		return  (BanType) this.getEntityManager().find(clazz,bantype);
	}
}
