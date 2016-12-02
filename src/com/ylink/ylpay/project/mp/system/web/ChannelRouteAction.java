package com.ylink.ylpay.project.mp.system.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.dao.Term.Type;
import com.google.code.lightssh.common.model.page.ListPage;
import com.ylink.modules.orm.PropertyFilterBuilder;
import com.ylink.modules.web.struts2.CustomAction;
import com.ylink.ylpay.common.project.channel.constant.BankType;
import com.ylink.ylpay.common.project.mp.constant.InterBankType;
import com.ylink.ylpay.common.project.mp.constant.OptType;
import com.ylink.ylpay.project.mp.system.entity.ChannelRoute;
import com.ylink.ylpay.project.mp.system.service.ChannelRouteManager;

/**
 * 渠道路由控制层.
 * 
 * @author 潘瑞峥
 * @date 2013-3-15
 */
@Component
@Scope( "prototype" )
public class ChannelRouteAction extends CustomAction<ChannelRoute> {

	private static final long serialVersionUID = 3302761006097142756L;

	private ChannelRouteManager channelRouteManager;

	private ChannelRoute vo;

	@Autowired
	public void setChannelRouteManager( ChannelRouteManager channelRouteManager ) {
		this.channelRouteManager = channelRouteManager;
		super.manager = this.channelRouteManager;
	}

	public ChannelRoute getVo() {
		vo = super.model;
		return vo;
	}

	public void setVo( ChannelRoute vo ) {
		super.model = vo;
		this.vo = vo;
	}

	/**
	 * 列表.
	 */
	@Override
	public String list() {
		if ( null == super.getPage() ) {
			super.setPage( new ListPage<ChannelRoute>() );
		}
		super.getPage().addAscending( "bankType" );
		super.getPage().addAscending( "optType" );
		super.getPage().addAscending( "interBankType" );
		super.getPage().addDescending( "isDefault" );

		/* 自动根据request参数转换为com.google.code.lightssh.common.dao.Term对象. */
		HttpServletRequest request = ServletActionContext.getRequest();

		List<Term> termList = PropertyFilterBuilder.buildTermFromHttpRequest( request );

		/* 产品类型. */
		String optTypeKey = request.getParameter( "optType" );
		if ( StringUtils.isNotBlank( optTypeKey ) ) {
			OptType optType = Enum.valueOf( OptType.class, optTypeKey );
			termList.add( new Term( Type.EQUAL, "optType", optType ) );
		}

		/* 行别类型. */
		String bankTypeKey = request.getParameter( "bankType" );
		if ( StringUtils.isNotBlank( bankTypeKey ) ) {
			BankType bankType = Enum.valueOf( BankType.class, bankTypeKey );
			termList.add( new Term( Type.EQUAL, "bankType", bankType ) );
		}

		/* 跨行类型. */
		String interBankTypeKey = request.getParameter( "interBankType" );
		if ( StringUtils.isNotBlank( interBankTypeKey ) ) {
			InterBankType interBankType = Enum.valueOf( InterBankType.class, interBankTypeKey );
			termList.add( new Term( Type.EQUAL, "interBankType", interBankType ) );
		}

		super.setPage( this.channelRouteManager.list( super.getPage(), termList ) );
		request.setAttribute( "list", super.getPage() );

		return SUCCESS;
	}

}