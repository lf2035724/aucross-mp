package com.ylink.ylpay.project.sms.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 短信接收伪实体.
 * 
 * @author 潘瑞峥
 * @date 2012-11-12
 */
public class SmsReceived implements Serializable {

	private static final long serialVersionUID = 4937764503612964726L;

	private Integer id;

	/** 发送短信的ID. */
	private Integer ms_id;

	/** 原主叫地址(NOT NULL). */
	private String orgaddr;

	/** 原被叫地址(NOT NULL). */
	private String destaddr;

	/** 发送内容. */
	private String ms_content;

	/** 业务代码(与本系统无关的业务, 运营商自定义的业务)(NOT NULL). */
	private String businesstype = "";

	/** 接收时间(NOT NULL). */
	private Date recvtime;

	/** 保留字段(int). */
	private Integer reserve1;

	/** 保留字段(varchar). */
	private String reserve2;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Integer getMs_id() {
		return ms_id;
	}

	public void setMs_id( Integer ms_id ) {
		this.ms_id = ms_id;
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

	public String getMs_content() {
		return ms_content;
	}

	public void setMs_content( String ms_content ) {
		this.ms_content = ms_content;
	}

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype( String businesstype ) {
		this.businesstype = businesstype;
	}

	public Date getRecvtime() {
		return recvtime;
	}

	public void setRecvtime( Date recvtime ) {
		this.recvtime = recvtime;
	}

	public Integer getReserve1() {
		return reserve1;
	}

	public void setReserve1( Integer reserve1 ) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2( String reserve2 ) {
		this.reserve2 = reserve2;
	}

}