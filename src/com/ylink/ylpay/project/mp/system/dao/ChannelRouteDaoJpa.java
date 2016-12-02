package com.ylink.ylpay.project.mp.system.dao;

import org.springframework.stereotype.Component;

import com.ylink.modules.orm.jpa.CustomJpaDaoImpl;
import com.ylink.ylpay.project.mp.system.entity.ChannelRoute;

/**
 * 渠道路由DAO.
 * 
 * @author 潘瑞峥
 * @date 2013-3-15
 */
@Component( "channelRouteDao" )
public class ChannelRouteDaoJpa extends CustomJpaDaoImpl<ChannelRoute> implements ChannelRouteDao {

	private static final long serialVersionUID = -2693386208870538355L;

}