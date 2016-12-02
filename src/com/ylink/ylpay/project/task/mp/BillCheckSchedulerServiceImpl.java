/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.task.mp;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import com.ylink.ylpay.common.project.channel.dto.HeadSavaPayDataDTO;
import com.ylink.ylpay.common.project.settle.constant.CheckMonitorChannelType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.project.scheduler.service.SchedulerService;
import com.ylink.ylpay.common.project.channel.app.CompareAccountEBSAppService;
import com.ylink.ylpay.common.project.settle.app.ChannelReconAppService;
import com.ylink.ylpay.common.project.settle.app.CheckMonitorAppService;
import com.ylink.ylpay.common.project.settle.constant.CheckMonitorCheckStatus;
import com.ylink.ylpay.common.project.settle.dto.CheckMonitorDTO;
import com.ylink.ylpay.common.project.settle.exception.SettleCheckedException;

/** 
 * @author YangXiaojin
 * @date 2012-12-28
 * @description：对账调度
 */

public class BillCheckSchedulerServiceImpl implements SchedulerService{
	
	private static final long serialVersionUID = 5455661796288699484L;
	
	//private static final String CHECK_SUCCESS = "2";
	private static final String DATA_SUCCESS = "2";
	
	private static Logger log = LoggerFactory.getLogger( BillCheckSchedulerServiceImpl.class );
	
	@Resource(name="checkMonitorAppService")
	private CheckMonitorAppService checkMonitorAppService;
	
	@Resource(name="compareAccountEBSAppService")
	private CompareAccountEBSAppService compareAccountEBSAppService;
	
	@Resource(name="channelReconAppService")
	private ChannelReconAppService channelReconAppService;
	
	/**
	 * 监控数据
	 */
	public int listAllSize(){
		ListPage<CheckMonitorDTO> page = new ListPage<CheckMonitorDTO>(0);
		CheckMonitorDTO cc = new CheckMonitorDTO();
		cc.setCheckStatus( CheckMonitorCheckStatus.NOTHAVE.getValue() );
		try {
			page = checkMonitorAppService.findMonitorInfoListPage(page,cc);
			return page.getAllSize();
		}catch (SettleCheckedException e) {
			log.warn("对账调度-清结算系统异常:",e.getMessage());
		}catch (Exception e) {
			log.error("对账调度-系统异常:",e);
		}
		
		return 0;
	}
	
	/**
	 * 监控数据
	 */
	public ListPage<CheckMonitorDTO> list( ListPage<CheckMonitorDTO> page ){
		CheckMonitorDTO cc = new CheckMonitorDTO();
		cc.setCheckStatus( CheckMonitorCheckStatus.NOTHAVE.getValue() );
		try {
			page = checkMonitorAppService.findMonitorInfoListPage(page,cc);
		}catch (SettleCheckedException e) {
			log.warn("对账调度-清结算系统异常:",e.getMessage());
		}catch (Exception e) {
			log.error("对账调度-系统异常:",e);
		}
		
		return page;
	}

	@Override
	public void execute() {
		
		int allsize = listAllSize();
		ListPage<CheckMonitorDTO> page = new ListPage<CheckMonitorDTO>(50);
		page.setAllSize(allsize);
		
		int success = 0;
		int handle = 0;
		for( int i=0;i<page.getAllPage();i++ ){
			page.setNumber(i+1);
			page = list( page );
			
			if( page == null || page.getList() == null 
					|| page.getList().isEmpty() )
				return;
			
			for( CheckMonitorDTO item:page.getList() ){
				Boolean result = doBusiness(item);
				if( Boolean.TRUE.equals( result ) )
					++ success;
				if( result != null )
					++ handle;
					
			}
		}
		
		log.info("对账调度,未对账数据["+allsize+"]条,处理[{}]条，处理成功[{}]条！",handle,success);
	}
	
	/**
	 * 业务处理
	 */
	protected Boolean doBusiness(CheckMonitorDTO dto ){
		if( dto == null)
			return null;
		
		String now = new SimpleDateFormat("yyyyMMdd").format(new Date());
		
		//0：未处理,1：处理中,2：处理成功,3：处理失败
		String dataStatus = dto.getDataStatus(); //对账数据状态
		
		//0：未对账,1：对账中,2：对账成功,3：对账失败
		String checkStatus = dto.getCheckStatus(); //对账状态
		
		//0：快捷,1：网银支付,2：充值退回,3:线上充值退回
		String channelType = dto.getChannelType();//渠道类型
		
		String msg = "监控数据信息[对账日期="+dto.getAccountDate()+",渠道类型="
			+channelType+",数据状态="+dataStatus+",对账状态="+checkStatus+"]";
		if( DATA_SUCCESS.equals(dataStatus) && "0".equals(checkStatus) ){
			//数据准备完成 并且未对账，通知清结算对账
			log.info("通知清结算对账，{}。",msg);
			try {
				channelReconAppService.payAndChannelReconByHand(dto.getId());
				return true;
			} catch (SettleCheckedException e) {
				log.error("清结算对账异常：",e);
				return false;
			}
		}else if( (CheckMonitorChannelType.CHANNELTYPE_CBS.getValue().equals(channelType) 
				|| CheckMonitorChannelType.CHANNELTYPE_ONLINEREFUND.getValue().equals(channelType) 
				|| CheckMonitorChannelType.CHANNELTYPE_B2B_TRANSFER.getValue().equals(channelType) ) 
			&& "0".equals(dataStatus) 
			&& !now.equals(dto.getAccountDate())){ //当天不对账，只对上一工作日及以前的数据
			
			//通知渠道取快捷对账文件
			log.info("通知渠道取快捷对账文件，{}。",msg);
			HeadSavaPayDataDTO param = new HeadSavaPayDataDTO();
			param.setID(dto.getId()+"");
			param.setBankType(dto.getBankType());
			param.setType(dto.getAccountDate());
			
			try {
				compareAccountEBSAppService.CompareAccountEBS(param);
				return true;
			} catch (Exception e) {
				log.error("通知渠道取快捷对账文件：",e);
				return false;
			}
		}
		
		return null;
	}
}
