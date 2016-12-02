package com.ylink.ylpay.project.mp.custbankcard.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

import com.google.code.lightssh.common.entity.Persistence;
import com.google.code.lightssh.common.model.Sequenceable;
import com.ylink.ylpay.project.constants.EncodeRulesConstant;

/** 
 * @author feng.li
 * @date 2014-11-20
 * @description：TODO
 */

/**
 * @author feng.li
 *
 */
@Entity
@Table( name = "T_CAPITAL_ACCOUNT_BANKCARD" )
public class CapitalAccountBankCard implements Persistence<String>, Sequenceable, Cloneable{
	
	private static final long serialVersionUID = 222049387147808457L;

	@Id
	private String id;

	/**
	 * 关联银行卡
	 */
	@ManyToOne
	@JoinColumn(name="CARD_ID")
	private CustBankcard bankCard;

	/**
	 * 资金账户号
	 */
	@Column( name = "CAPITAL_ACCOUNT_NO",  length = 40 )
	private String capitalAccountNO;
	
	/**
	 * 创建时间（绑卡时间）
	 */
	@Column( name = "CREATE_TIME", columnDefinition = "Date" )
	@Temporal( TemporalType.TIMESTAMP )
	private Date createTime;

	@Override
	public boolean isInsert() {
		return StringUtils.isEmpty( id );
	}

	@Override
	public void postInsertFailure() {
	}

	@Override
	public void preInsert() {
		this.id = UUID.randomUUID().toString();
	}

	@Override
	public String getSequenceKey() {
		return EncodeRulesConstant.CUST_CODE_PREFIX;
	}

	@Override
	public int getSequenceLength() {

		return EncodeRulesConstant.CUST_CODE_LENGTH;
	}
	
	@Override
	public int getSequenceStep() {
		return EncodeRulesConstant.DEFAULT_SPIKE;
	}

	public String getCapitalAccountNO() {
		return capitalAccountNO;
	}

	public void setCapitalAccountNO(String capitalAccountNO) {
		this.capitalAccountNO = capitalAccountNO;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String getIdentity() {
		return id;
	}

	public void setIdentity( String id ) {
		this.id = id;
	}

	public CustBankcard getBankCard() {
		return bankCard;
	}

	public void setBankCard(CustBankcard bankCard) {
		this.bankCard = bankCard;
	}
}
