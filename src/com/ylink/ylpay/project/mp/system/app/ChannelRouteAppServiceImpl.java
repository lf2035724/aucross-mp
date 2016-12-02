package com.ylink.ylpay.project.mp.system.app;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ylink.ylpay.common.project.mp.app.ChannelRouteAppService;
import com.ylink.ylpay.common.project.mp.constant.ChannelRouteDTO;
import com.ylink.ylpay.common.project.mp.constant.InterBankType;
import com.ylink.ylpay.common.project.mp.constant.OptType;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.mp.system.entity.ChannelRoute;
import com.ylink.ylpay.project.mp.system.service.ChannelRouteManager;

@Component("channelRouteAppService")
public class ChannelRouteAppServiceImpl implements ChannelRouteAppService{
	@Resource(name="channelRouteManager")
	private ChannelRouteManager channelRouteManager;
	
	@Override
	public ChannelRouteDTO getById(String id) throws MpCheckedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelRouteDTO getByDTO(ChannelRouteDTO dto)
			throws MpCheckedException {
		// TODO Auto-generated method stub
		ChannelRouteDTO channelRoutedto=new ChannelRouteDTO();
		ChannelRoute item=channelRouteManager.find(dto.getOptType(), dto.getBankType(), dto.getInterBankType());
		if( item != null ){
			BeanUtils.copyProperties(item, channelRoutedto,new String[]{"optType","bankType","interBankType"});
			channelRoutedto.setOptType(OptType.parseOf(item.getOptType().getValue()));
			channelRoutedto.setBankType(item.getBankType().getValue());
			//channelRoutedto.setBankType(item.getBankType().getValue());
			channelRoutedto.setInterBankType(InterBankType.parseOf(item.getInterBankType().getValue()));
			//channelRoutedto.setIsDefault(WhetherStatus.)
		}
		return channelRoutedto;
	}

	@Override
	public String getDefault(OptType optType)
			throws MpCheckedException {
		return channelRouteManager.findDefaultValue(optType);
	}
}
