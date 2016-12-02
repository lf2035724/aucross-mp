package com.ylink.ylpay.project.mp.cust.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.google.code.lightssh.common.entity.base.UUIDModel;
import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.ylpay.common.project.mp.constant.AuditStatus;
import com.ylink.ylpay.common.project.mp.constant.CertificationType;
import com.ylink.ylpay.common.project.mp.constant.RecoveryAllowType;
@Entity
@Table( name = "T_WORKORDER_CERT" )
public class CustCert extends UUIDModel {

	private static final long serialVersionUID = -7182949549295123814L;

	/** 客户(不做关联, 若开成EAGER会造成死循环). */
	@Column( name = "WORKORDER_ID" )
	private String workorderId;

	/** 证件类型. */
	@Column( name = "CERT_TYPE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.CertificationType" ) } )
	private CertificationType certType;

	/** 证件名称(暂时可不用). */
	@Column( name = "CERT_NAME" )
	private String certName;

	/** 证件号码. */
	@Column( name = "CERT_NO" )
	private String certNo;

	/** 证件文件. */
	@Lob
	@Column( name = "CERT_FILE" )
	private byte[] certFile;

	/** 文件类型. */
	@Column( name = "CERT_FILE_TYPE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.RecoveryAllowType" ) } )
	private RecoveryAllowType certFileType;

	/** 审核状态. */
	@Column( name = "AUDIT_STATUS" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.AuditStatus" ) } )
	private AuditStatus auditStatus;

	/** 证件文件更新时间. */
	@Temporal( TemporalType.DATE )
	@Column( name = "CERT_UPDATE_TIME" )
	private Date certUpdateTime = new Date();

	public CustCert() {
	}

	public CertificationType getCertType() {
		return certType;
	}

	public void setCertType( CertificationType certType ) {
		this.certType = certType;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName( String certName ) {
		this.certName = certName;
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

	public AuditStatus getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus( AuditStatus auditStatus ) {
		this.auditStatus = auditStatus;
	}

	public Date getCertUpdateTime() {
		return certUpdateTime;
	}

	public String getWorkorderId() {
		return workorderId;
	}

	public void setWorkorderId(String workorderId) {
		this.workorderId = workorderId;
	}

	@Override
	public void preInsert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInsert() {
		if(id == null){
			return true;
		}
		return false;
	}

}