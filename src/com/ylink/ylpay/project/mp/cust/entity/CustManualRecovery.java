package com.ylink.ylpay.project.mp.cust.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.google.code.lightssh.project.geo.entity.GeographicBoundary;
import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.ylpay.common.project.mp.constant.AuditStatus;
import com.ylink.ylpay.common.project.mp.constant.CertificationType;
import com.ylink.ylpay.common.project.mp.constant.RecoveryAllowType;
import com.ylink.ylpay.common.project.mp.constant.RecoveryStatus;
import com.ylink.ylpay.common.project.mp.constant.RecoveryType;
import com.ylink.ylpay.common.project.mp.constant.WhetherStatus;
import com.ylink.ylpay.project.constants.entity.BaseEntity;

/**
 * 人工找回密码实体.
 * 
 * @author 潘瑞峥
 * @date 2013-1-4
 */
@Entity
@Table( name = "T_CUST_MANUAL_RECOVERY" )
public class CustManualRecovery extends BaseEntity {

	private static final long serialVersionUID = 6388647661993820825L;

	/** 关联个人客户. */
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "CUST_ID" )
	private Customer cust;

	/** 真实姓名. */
	@Column( name = "NAME" )
	private String name;

	/** 证件所在地. */
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "CERT_GEO_ID" )
	private GeographicBoundary certGeo;

	/** 证件类型. */
	@Column( name = "CERT_TYPE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.CertificationType" ) } )
	private CertificationType certType;

	/** 证件号码. */
	@Column( name = "CERT_NO" )
	private String certNo;

	/** 证件文件. */
	@Lob
	@Column( name = "CERT_FILE" )
	private byte[] certFile;

	/** 证件文件类型. */
	@Column( name = "CERT_FILE_TYPE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.RecoveryAllowType" ) } )
	private RecoveryAllowType certFileType;

	/** 需找回项目. */
	@Column( name = "RECOVERY_TYPE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.RecoveryType" ) } )
	private RecoveryType recoveryType;

	/** 邮箱. */
	@Column( name = "EMAIL" )
	private String email;

	/** 是否解绑手机. */
	@Column( name = "UNBIND_MOBILE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.WhetherStatus" ) } )
	private WhetherStatus unbindMobile;

	/** 手机. */
	@Column( name = "MOBILE" )
	private String mobile;

	/** 状态. */
	@Column( name = "STATUS" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.RecoveryStatus" ) } )
	private RecoveryStatus status;

	/** 审核状态. */
	@Column( name = "AUDIT_STATUS" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.AuditStatus" ) } )
	private AuditStatus auditStatus;

	/** 备注. */
	@Column( name = "REMARK" )
	private String remark;

	/** 创建日期. */
	@Temporal( TemporalType.DATE )
	@Column( name = "CREATED_TIME" )
	private Date createdTime = new Date();

	public CustManualRecovery() {
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public GeographicBoundary getCertGeo() {
		return certGeo;
	}

	public void setCertGeo( GeographicBoundary certGeo ) {
		this.certGeo = certGeo;
	}

	public CertificationType getCertType() {
		return certType;
	}

	public void setCertType( CertificationType certType ) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo( String certNo ) {
		this.certNo = certNo;
	}

	public byte[] getCertFile() {
		return certFile;
	}

	public void setCertFile( byte[] certFile ) {
		this.certFile = certFile;
	}

	public RecoveryAllowType getCertFileType() {
		return certFileType;
	}

	public void setCertFileType( RecoveryAllowType certFileType ) {
		this.certFileType = certFileType;
	}

	public RecoveryType getRecoveryType() {
		return recoveryType;
	}

	public void setRecoveryType( RecoveryType recoveryType ) {
		this.recoveryType = recoveryType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public WhetherStatus getUnbindMobile() {
		return unbindMobile;
	}

	public void setUnbindMobile( WhetherStatus unbindMobile ) {
		this.unbindMobile = unbindMobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile( String mobile ) {
		this.mobile = mobile;
	}

	public RecoveryStatus getStatus() {
		return status;
	}

	public void setStatus( RecoveryStatus status ) {
		this.status = status;
	}

	public AuditStatus getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus( AuditStatus auditStatus ) {
		this.auditStatus = auditStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark( String remark ) {
		this.remark = remark;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

}