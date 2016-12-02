package com.ylink.ylpay.project.mp.basic.web;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.Nameable;
import com.google.code.lightssh.project.web.action.GenericAction;
import com.ylink.modules.utils.web.struts2.StrutsUtils;
import com.ylink.ylpay.project.mp.basic.entity.FundsLimit;
import com.ylink.ylpay.project.mp.basic.service.FundsLimitManager;

@Component( "fundsLimitAction" )
@Scope( "prototype" )
public class FundsLimitAction extends GenericAction<FundsLimit>{

	private static final long serialVersionUID = 1L;
	private FundsLimit fundsLimit;
	

	public FundsLimitManager getFundsLimitManager() {
		return fundsLimitManager;
	}

	private FundsLimitManager fundsLimitManager;
	
	@Resource( name = "fundsLimitManager" )
	public void setFundsLimitManager(FundsLimitManager fundsLimitManager) {
		this.fundsLimitManager = fundsLimitManager;
		super.manager=fundsLimitManager;
	}
	
	public String list() {
		return super.list();
     }

	public void setFundsLimit(FundsLimit fundsLimit) {
		this.fundsLimit = fundsLimit;
		super.model=this.fundsLimit;
	}

	public FundsLimit getFundsLimit() {
		this.fundsLimit=super.model;
		return fundsLimit;
	}
	
	@JSON(name="unique")
	public boolean isUnique() {
		return unique;
	}
	
  	public String unique( ){
		this.unique = this.getFundsLimitManager().isUniqueAccountAndBusiness(fundsLimit);
		return SUCCESS;
	}
  	
  	/**
  	 * 查询是否已经存在账户和交易类型
  	 * @return
  	 */
  	public String checkAccountAndBusiness(){
  		String json = "false";
		String accType = request.getParameter("accType");
		String businessId = request.getParameter("businessId");
		String id = request.getParameter("id");
		FundsLimit entity = this.fundsLimitManager.findByAccountAndBusiness(id, accType,businessId);
		/* 不存在. */
		if (null == entity) {
			json = "true";
		}
		StrutsUtils.renderJson(json);
		return null;
  	}
  	 public String save( ){    	
         if( model == null ){
             return INPUT;
         }
         if( fundsLimit.getSingleWarn()>fundsLimit.getSingleLimit()){
        	 this.saveErrorMessage("单笔警告额大于单笔限制！");
        	 return INPUT ;
         }
         if( fundsLimit.getDayWarn() > fundsLimit.getDayLimit() ){
        	 this.saveErrorMessage("每天警告额大于每天限制！");
 			return INPUT ;
         }
         if( fundsLimit.getMonthWarn()>fundsLimit.getMonthLimit()){
        	 this.saveErrorMessage("每月警告额大于每月限制！");
        	 return INPUT ;
         }
         
         boolean isInsert = model.isInsert();
         
         try{
             manager.save(model);
         }catch( Exception e ){ //other exception
         	//GenerationType.SEQUENCE 未插入成功,Oracle 也会生成ID
         	if( isInsert )
         		model.postInsertFailure();
         	
             addActionError( e.getMessage() );
             return INPUT;
         } 
         
         String hint =  (model instanceof Nameable)?("["+((Nameable)model).getName()+"]"):""+"保存成功！";
         saveSuccessMessage( hint );
         String saveAndNext = request.getParameter("saveAndNext");
         if( saveAndNext != null && !"".equals( saveAndNext.trim() ) ){
         	return NEXT;
         }else{        	
         	return SUCCESS;
         }
     }

}
