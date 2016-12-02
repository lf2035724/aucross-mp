package com.ylink.ylpay.project.mp.system.dao;

import org.springframework.stereotype.Component;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.system.entity.AcceptBankMessage;

/**
 * AcceptBankMessageDaoJpa
 * 
 * @author feng.li
 * @date 2014-11-12
 */ 
@Component( "acceptBankMessageDao" )
public class AcceptBankMessageDaoJpa extends CustomJpaDaoImpl<AcceptBankMessage> implements AcceptBankMessageDao {

	private static final long serialVersionUID = -195177996493264070L;


}