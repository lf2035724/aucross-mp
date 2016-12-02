package com.ylink.ylpay.project.mp.system.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.ylpay.common.project.channel.constant.BankType;
import com.ylink.ylpay.common.project.mp.constant.InterBankType;
import com.ylink.ylpay.common.project.mp.constant.OptType;
import com.ylink.ylpay.common.project.mp.constant.WhetherStatus;
import com.ylink.ylpay.project.constants.entity.BaseEntity;

/**
 * 渠道路由实体.
 * 
 * @author 潘瑞峥
 * @date 2013-3-14
 */
@Entity
@Table( name = "T_CHANNEL_ROUTE" )
public class ChannelRoute extends BaseEntity {

	private static final long serialVersionUID = 9140254335010425397L;

	/** 产品类型. */
	@Column( name = "OPT_TYPE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.OptType" ) } )
	private OptType optType;

	/** 行别. */
	@Column( name = "BANK_TYPE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.channel.constant.BankType" ) } )
	private BankType bankType;

	/** 跨行类型. */
	@Column( name = "INTERBANK_TYPE" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.InterBankType" ) } )
	private InterBankType interBankType;

	/** 是否为默认. */
	@Column( name = "IS_DEFAULT" )
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.mp.constant.WhetherStatus" ) } )
	private WhetherStatus isDefault;

	/** 渠道接口. */
	@Column( name = "CHANNEL_API" )
	private String channelApi;

	/** 描述. */
	private String description;

	/** 创建日期. */
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "CREATED_TIME" )
	private Date createdTime = new Date();

	public ChannelRoute() {
	}

	public OptType getOptType() {
		return optType;
	}

	public void setOptType( OptType optType ) {
		this.optType = optType;
	}

	public BankType getBankType() {
		return bankType;
	}

	public void setBankType( BankType bankType ) {
		this.bankType = bankType;
	}

	public InterBankType getInterBankType() {
		return interBankType;
	}

	public void setInterBankType( InterBankType interBankType ) {
		this.interBankType = interBankType;
	}

	public WhetherStatus getIsDefault() {
		return isDefault;
	}

	public void setIsDefault( WhetherStatus isDefault ) {
		this.isDefault = isDefault;
	}

	public String getChannelApi() {
		return channelApi;
	}

	public void setChannelApi( String channelApi ) {
		this.channelApi = channelApi;
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

}