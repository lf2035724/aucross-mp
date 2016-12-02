package com.ylink.ylpay.project.task.mp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.lightssh.project.scheduler.service.SchedulerService;
import com.ylink.ylpay.common.project.account.app.AccountParamAppService;
import com.ylink.ylpay.common.project.account.constant.ParamValue;
import com.ylink.ylpay.common.project.account.dto.AccountParamDTO;
import com.ylink.ylpay.common.project.supervision.app.DayEndAppService;

/** 
 * @author feng.li
 * @date 2014-11-11
 * @description：监管系统日终调度
 */

/**
 * @author feng.li
 *
 */
public class DayEndSupervisionSchedulerServiceImpl implements SchedulerService{
	
	private static final long serialVersionUID = 8951782521070954116L;

	private static SimpleDateFormat SDF_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

	private static Logger log = LoggerFactory.getLogger(DayEndSupervisionSchedulerServiceImpl.class);
	
//	@Resource(name="supervisionDayEndAppService")
	@Resource(name="dayEndAppService")
	private DayEndAppService dayEndAppService;
	
	@Resource(name="accountParamAppService")
	private AccountParamAppService accountParamAppService;

	@Override
	public void execute() {
		log.info("监管系统日终调度……");
		this.initSupervision();
	}
	
	//取工作日
	protected String getWorkdate(){
		AccountParamDTO dto = null;
		try{
			List<AccountParamDTO> params = accountParamAppService.findParamList();
			if( params != null )
				for( AccountParamDTO item:params )
					if( ParamValue.ACCOUNTDATE.getValue().equals(item.getId()) )
						dto = item;
		}catch( Exception e ){
			log.warn("获取账务日期异常,使用系统时间为当前帐户日期：{}",e.getMessage());
		}
		String accountDate=null;
		Date accountDate2=null;
		if( dto != null )
			accountDate=dto.getValue();
		try {
			accountDate2=SDF_YYYYMMDD.parse(accountDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(accountDate2==null){
			accountDate2=new Date();
		}
		return SDF_YYYYMMDD.format(DateUtils.addDays(accountDate2,-1));
	}
	
	/**
	 * 监管系统日终调度
	 */
	protected void initSupervision(){
		try {
			dayEndAppService.createMonitor(getWorkdate());
		} catch (Exception e) {
			log.error("监管系统日终调度异常！"+e.getMessage());
			e.printStackTrace();
		}
	}

	public DayEndAppService getDayEndAppService() {
		return dayEndAppService;
	}

	public void setDayEndAppService(DayEndAppService dayEndAppService) {
		this.dayEndAppService = dayEndAppService;
	}

}
