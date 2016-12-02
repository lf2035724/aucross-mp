package com.ylink.ylpay.project.mp.agency.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.google.code.lightssh.common.model.Sequenceable;
import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.ylpay.common.project.mp.constant.AuditStatus;
import com.ylink.ylpay.common.project.mp.constant.StatusMark;
import com.ylink.ylpay.project.constants.EncodeRulesConstant;
import com.ylink.ylpay.project.constants.entity.BaseEntity;
import com.ylink.ylpay.project.mp.cust.entity.Enterprise;

/**
 * 机构实体.
 * 
 * @author 潘瑞峥
 * @date 2012-12-21
 */
@Entity
@Table( name = "T_CUST_AGENCY" )
public class CustAgency extends BaseEntity implements Sequenceable,Cloneable {

	private static final long serialVersionUID = -6631238539953343627L;

	/** 名称. */
	private String name;

	/** 编号. */
	private String code;

	/** 状态. */
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.StatusMark" ) } )
	private StatusMark status;

	/** 审核状态. */
	@Column( name = "AUDIT_STATUS" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.AuditStatus" ) } )
	private AuditStatus auditStatus;

	/** 企业客户. */
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "CUST_ID" )
	private Enterprise cust;

	/** 备注. */
	private String description;

	/** 创建日期. */
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "CREATED_TIME" )
	private Date createdTime = new Date();

	/**
	 * 状态是否有效
	 */
	public boolean isEffective(){
		return StatusMark.VALID.equals(getStatus());
	}

	public CustAgency clone() {
		try {
			return (CustAgency) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	public CustAgency() {
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode( String code ) {
		this.code = code;
	}

	public StatusMark getStatus() {
		return status;
	}

	public void setStatus( StatusMark status ) {
		this.status = status;
	}

	public AuditStatus getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus( AuditStatus auditStatus ) {
		this.auditStatus = auditStatus;
	}

	public Enterprise getCust() {
		return cust;
	}

	public void setCust( Enterprise cust ) {
		this.cust = cust;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	@Override
	public String getSequenceKey() {
		return EncodeRulesConstant.AGENCY_CODE_PREFIX;
	}

	@Override
	public int getSequenceLength() {
		return EncodeRulesConstant.AGENCY_CODE_LENGTH;
	}

	@Override
	public int getSequenceStep() {
		return EncodeRulesConstant.DEFAULT_SPIKE;
	}

}