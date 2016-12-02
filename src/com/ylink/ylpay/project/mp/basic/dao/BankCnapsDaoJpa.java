package com.ylink.ylpay.project.mp.basic.dao;

import org.springframework.stereotype.Repository;

import com.google.code.lightssh.common.dao.jpa.JpaDao;
import com.ylink.ylpay.project.mp.basic.entity.BankCnaps;

/**
 * 
 * @author YangXiojin
 * @date 2013-5-22
 * 
 */
@Repository("bankCnapsDao")
public class BankCnapsDaoJpa extends JpaDao<BankCnaps> implements BankCnapsDao{

	private static final long serialVersionUID = -1171136312658106498L;


}
