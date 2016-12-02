package com.ylink.ylpay.project.mp.basic.dao;

import org.springframework.stereotype.Repository;

import com.google.code.lightssh.common.dao.jpa.JpaAnnotationDao;
import com.ylink.ylpay.project.mp.basic.entity.PayBankConfig;

/**
 * 
 * @author yu
 *
 */
@Repository("payBankConfigDao")
public class PayBankConfigDaoImpl  extends JpaAnnotationDao<PayBankConfig> implements PayBankConfigDao {

	private static final long serialVersionUID = 7791044823815435321L;


}