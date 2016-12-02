package com.ylink.ylpay.project.sms.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 短信发送伪实体.
 * 
 * @author 潘瑞峥
 * @date 2012-11-7
 */
public class SmsSend implements Serializable {

	private static final long serialVersionUID = 5563554013476773988L;

	private Integer id;

	/** 主叫号码(106575660466)(NULL). */
	private String orgaddr;

	/** 被叫手机号. */
	private String destaddr;

	/** 短信内容(NULL). */
	private String content;

	/** 发送状态. */
	private String type;

	/** 发送时间. */
	private Date sendtime;

	/** 创建用户id(NULL). */
	private String creatorid = "5500";

	/** 批次号(NULL). */
	private Integer batchid;

	/** 业务代码(与本系统无关的业务, 运营商自定义的业务). */
	private String businesstype = "01";

	/** 所属区域(省内, 省外). */
	private String area;

	/** 备注(NULL). */
	private String bz;

	/** 插入数据日期(NULL). */
	private Date inst_time;

	/** 收到日期(NULL). */
	private Date recv_time;

	/** 优先级(NULL). */
	private Integer priority;

	/** 端口?(NULL). */
	private Integer depart;

	/** 资金帐户?(NULL). */
	private Long zjzh;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getOrgaddr() {
		return orgaddr;
	}

	public void setOrgaddr( String orgaddr ) {
		this.orgaddr = orgaddr;
	}

	public String getDestaddr() {
		return destaddr;
	}

	public void setDestaddr( String destaddr ) {
		this.destaddr = destaddr;
	}

	public String getContent() {
		return content;
	}

	public void setContent( String content ) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType( String type ) {
		this.type = type;
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime( Date sendtime ) {
		this.sendtime = sendtime;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid( String creatorid ) {
		this.creatorid = creatorid;
	}

	public Integer getBatchid() {
		return batchid;
	}

	public void setBatchid( Integer batchid ) {
		this.batchid = batchid;
	}

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype( String businesstype ) {
		this.businesstype = businesstype;
	}

	public String getArea() {
		return area;
	}

	public void setArea( String area ) {
		this.area = area;
	}

	public String getBz() {
		return bz;
	}

	public void setBz( String bz ) {
		this.bz = bz;
	}

	public Date getInst_time() {
		return inst_time;
	}

	public void setInst_time( Date inst_time ) {
		this.inst_time = inst_time;
	}

	public Date getRecv_time() {
		return recv_time;
	}

	public void setRecv_time( Date recv_time ) {
		this.recv_time = recv_time;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority( Integer priority ) {
		this.priority = priority;
	}

	public Integer getDepart() {
		return depart;
	}

	public void setDepart( Integer depart ) {
		this.depart = depart;
	}

	public Long getZjzh() {
		return zjzh;
	}

	public void setZjzh( Long zjzh ) {
		this.zjzh = zjzh;
	}

}