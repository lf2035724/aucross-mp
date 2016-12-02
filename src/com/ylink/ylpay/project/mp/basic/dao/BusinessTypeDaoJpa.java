package com.ylink.ylpay.project.mp.basic.dao;

import org.springframework.stereotype.Repository;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.basic.entity.BusinessType;

@Repository("businessTypeDao")
public class BusinessTypeDaoJpa extends CustomJpaDaoImpl<BusinessType> implements BusinessTypeDao{

	private static final long serialVersionUID = 3539428770378778486L;
}