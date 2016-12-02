package com.ylink.ylpay.project.mp.system.service;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.dao.Term.Type;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.google.common.collect.Lists;
import com.ylink.ylpay.common.project.channel.constant.BankType;
import com.ylink.ylpay.common.project.mp.constant.InterBankType;
import com.ylink.ylpay.common.project.mp.constant.OptType;
import com.ylink.ylpay.common.project.mp.constant.WhetherStatus;
import com.ylink.ylpay.project.mp.system.dao.ChannelRouteDao;
import com.ylink.ylpay.project.mp.system.entity.ChannelRoute;

/**
 * 渠道路由业务层.
 * 
 * @author 潘瑞峥
 * @date 2013-3-15
 */
@Component( "channelRouteManager" )
public class ChannelRouteManagerImpl extends BaseManagerImpl<ChannelRoute> implements ChannelRouteManager {

	private static final long serialVersionUID = 4952599611370413938L;

	private static Logger logger = LoggerFactory.getLogger( ChannelRouteManagerImpl.class );

	private ChannelRouteDao channelRouteDao;

	@Autowired
	public void setChannelRouteDao( ChannelRouteDao channelRouteDao ) {
		this.channelRouteDao = channelRouteDao;
		super.dao = this.channelRouteDao;
	}

	/**
	 * 查询默认值.
	 */
	@Override
	public ChannelRoute findDefault( OptType optType ) {
		Validate.notNull( optType, "产品类型不能为空." );

		List<Term> termList = Lists.newArrayList();
		termList.add( new Term( Type.EQUAL, "isDefault", WhetherStatus.YES ) );
		termList.add( new Term( Type.EQUAL, "optType", optType ) );

		ChannelRoute entity = this.channelRouteDao.findUnique( termList );

		if ( null != entity ) {
			return entity;
		} else {
			logger.debug( "没有查询到{}默认的渠道接口(该接口的跨行类型必须即支持同行又支持跨行).", optType );
			//throw new ApplicationException( "没有查询到" + optType.getDisplayName() + "默认的渠道接口(该接口的跨行类型必须即支持同行又支持跨行)." );
			return null;
		}
	}

	/**
	 * 查询.
	 */
	@Override
	public ChannelRoute find( OptType optType, BankType bankType, InterBankType interBankType ) {
		Validate.notNull( optType, "产品类型不能为空." );
		Validate.notNull( bankType, "行别不能为空." );
		Validate.notNull( interBankType, "跨行类型不能为空." );

		logger.debug( "OptType[{}.{}], BankType[{}.{}], InterBankType[{}.{}]", new Object[] { optType.getValue(), optType.getDisplayName(),
				bankType.getValue(), bankType.getDisplayName(), interBankType.getValue(), interBankType.getDisplayName() } );

		List<Term> termList = Lists.newArrayList();
		termList.add( new Term( Type.EQUAL, "optType", optType ) );
		termList.add( new Term( Type.EQUAL, "bankType", bankType ) );
		termList.add( new Term( Type.EQUAL, "interBankType", interBankType ) );

		return this.channelRouteDao.findUnique( termList );
	}

	/**
	 * 查询默认值.
	 */
	@Override
	public String findDefaultValue( OptType optType ) {
		ChannelRoute entity = this.findDefault( optType );
		if ( null != entity ) {
			return entity.getChannelApi();
		}
		return null;
	}

	/**
	 * 查询.
	 */
	@Override
	public String findValue( OptType optType, BankType bankType, InterBankType interBankType ) {
		ChannelRoute entity = this.find( optType, bankType, interBankType );
		if ( null != entity ) {
			return entity.getChannelApi();
		}else{
			return findDefaultValue(optType);
		}
	}

	@Override
	public ChannelRoute find(OptType optType, String bankType,
			InterBankType interBankType) {
	    BankType bankTypee=BankType.parseOf(bankType);
		Validate.notNull( optType, "产品类型不能为空." );
		Validate.notNull( bankTypee, "行别不能为空." );
		Validate.notNull( interBankType, "跨行类型不能为空." );

		logger.debug( "OptType[{}.{}], BankType[{}.{}], InterBankType[{}.{}]", new Object[] { optType.getValue(), optType.getDisplayName(),
				bankTypee.getValue(),bankTypee.getDisplayName(), interBankType.getValue(), interBankType.getDisplayName() } );

		List<Term> termList = Lists.newArrayList();
		termList.add( new Term( Type.EQUAL, "optType", optType ) );
		termList.add( new Term( Type.EQUAL, "bankType", bankTypee ) );
		termList.add( new Term( Type.EQUAL, "interBankType", interBankType ) );

		return this.channelRouteDao.findUnique( termList );
	}

	@Override
	public String findValue(OptType optType, String bankType,
			InterBankType interBankType) {
		// TODO Auto-generated method stub
		ChannelRoute entity = this.find( optType, bankType, interBankType );
		if ( null != entity ) {
			return entity.getChannelApi();
		}else{
			return findDefaultValue(optType);
		}
	}

}