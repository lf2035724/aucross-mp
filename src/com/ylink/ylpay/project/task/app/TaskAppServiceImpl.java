/**
 * 版权所有(C) 2013 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.task.app;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.lightssh.project.scheduler.entity.PlanDetail;
import com.google.code.lightssh.project.scheduler.service.PlanManager;
import com.ylink.ylpay.common.project.mp.app.TaskAppService;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.task.mp.SchedulerTypeEnum;

/** 
 * @author YangXiaojin
 * @date 2013-1-9
 * @description：TODO
 */
public class TaskAppServiceImpl implements TaskAppService{
	
	private static Logger log = LoggerFactory.getLogger( TaskAppServiceImpl.class );
	
	@Resource(name="planManager")
	private PlanManager planManager;
	
	public void setPlanManager(PlanManager planManager) {
		this.planManager = planManager;
	}

	@Override
	public void callback(String taskid, boolean result)throws MpCheckedException {
		log.info("定时任务回调！taskId:"+taskid+"  result:  "+result);
		callbackWithRemark(taskid,result,null);
	}
	
	@Override
	public void callbackWithRemark(String taskid, boolean result,String errMsg)
			throws MpCheckedException {
		if( StringUtils.isEmpty(taskid) )
			throw new MpCheckedException("任务编码为空！");
		if(!result){
			PlanDetail planDetail = planManager.getDetail(taskid);
			if(planDetail==null){
				throw new MpCheckedException("没有此任务，编码为："+taskid);
			}
			SchedulerTypeEnum planSchedulerTypeEnum = null;
			try{
				planSchedulerTypeEnum = SchedulerTypeEnum.parseOf(planDetail.getPlan().getType().getId());
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		try{
			//更新任务明细状态
			planManager.updateDetailStatus(taskid,result,errMsg);
			
			//执行依赖任务
			planManager.executeRelyOnPlanDetail(result,taskid);
			
			//尝试更新计划任务执行状态
			if( result )
				planManager.updateStatus(taskid);
		}catch( Exception e ){
			log.error("任务回调异常:",e);
			throw new MpCheckedException(e.getMessage());
		}
	}

}
