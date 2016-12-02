package com.ylink.ylpay.project.mp.basic.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ylink.ylpay.project.constants.entity.BaseEntity;

/**
 * 付款行配置
 * @author yu.han
 *
 */
@Entity
@Table( name = "T_PAY_BANK_CONFIG" , uniqueConstraints = { @UniqueConstraint( columnNames = { "bank_type", "opt_type" } ) })
public class PayBankConfig extends BaseEntity{
	private static final long serialVersionUID = -3790065662016382007L;
	/**
	 * 行别
	 */
	@Column( name = "bank_type",length = 50,nullable=false)
	private String bankType;
	
	/**
	 * 业务类型（付款类）
	 */
	@Column( name = "opt_type",length = 50,nullable=false)
	private String optType;
	
	/**
	 * 是否默认（同业务业务，设置唯一）1为true,0为false
	 */
	@Column( name = "is_def",length = 1 )
	private Integer isDef;
	
	/**
	 * 支付类型 ALL:线上线下 ONLINE:线上 OFFLINE:线下

	 */
	@Column( name = "LINE_TYPE",length = 20,nullable=false)
	private String lineType;
	
	/**
	 * 备注
	 */
	@Column( name = "DESCRIPTION",length = 200)
	private String description;
	
	@Column( name = "create_time",nullable=false)
	private Date createTime;
	// -- getters and setters --------------------------------------------------
	
	/**
	 * @return the bankType
	 */
	public String getBankType() {
		return bankType;
	}

	/**
	 * @param bankType the bankType to set
	 */
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	/**
	 * @return the optType
	 */
	public String getOptType() {
		return optType;
	}

	/**
	 * @param optType the optType to set
	 */
	public void setOptType(String optType) {
		this.optType = optType;
	}

	/**
	 * @return the isDef
	 */
	public Integer getIsDef() {
		return isDef;
	}

	/**
	 * @param isDef the isDef to set
	 */
	public void setIsDef(Integer isDef) {
		this.isDef = isDef;
	}

	/**
	 * @return the lineType
	 */
	public String getLineType() {
		return lineType;
	}

	/**
	 * @param lineType the lineType to set
	 */
	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public boolean isInsert() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void postInsertFailure() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preInsert() {
		// TODO Auto-generated method stub
		
	}

}
