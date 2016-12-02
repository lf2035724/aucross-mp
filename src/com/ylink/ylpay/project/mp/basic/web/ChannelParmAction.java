package com.ylink.ylpay.project.mp.basic.web;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.web.SessionKey;
import com.google.code.lightssh.common.web.action.BaseAction;
import com.google.code.lightssh.project.security.entity.LoginAccount;
import com.ylink.ylpay.common.project.channel.app.MPChannelParmAppService;
import com.ylink.ylpay.common.project.channel.dto.ChannelParmDTO;

@Component("channelParmAction")
@Scope("prototype")
public class ChannelParmAction extends BaseAction {

	private static final long serialVersionUID = -4962227476312056910L;

	@Resource(name = "mPChannelParmAppService")
	private MPChannelParmAppService mPChannelParmAppService;
	
	@Resource(name = "mPChannelParmAppService1")
	private MPChannelParmAppService mPChannelParmAppService1;

	@Resource(name = "mPChannelParmAppService2")
	private MPChannelParmAppService mPChannelParmAppService2;


	private ChannelParmDTO channelParmDTO;

	private ListPage<ChannelParmDTO> page = new ListPage<ChannelParmDTO>();

	public void setmPChannelParmAppService(
			MPChannelParmAppService mPChannelParmAppService) {
		this.mPChannelParmAppService = mPChannelParmAppService;
	}

	public void setChannelParmDTO(ChannelParmDTO channelParmDTO) {
		this.channelParmDTO = channelParmDTO;
	}

	public ChannelParmDTO getChannelParmDTO() {
		return channelParmDTO;
	}

	public ListPage<ChannelParmDTO> getPage() {
		return page;
	}

	public void setPage(ListPage<ChannelParmDTO> page) {
		this.page = page;
	}
	
	/**
	 * 更新渠道参数
	 * @return
	 */
	public String refresh(){
		try{
			mPChannelParmAppService1.initParam();
			mPChannelParmAppService2.initParam();
			this.saveSuccessMessage("刷新渠道参数成功！");
		}catch(Exception e ){
			this.saveErrorMessage("刷新渠道参数异常："+e.getMessage());
		}
		
		return SUCCESS;
	}

	/**
	 * 渠道参数查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		if (channelParmDTO == null)
			channelParmDTO = new ChannelParmDTO();

		try{
			page = this.mPChannelParmAppService.queryListPage(page, channelParmDTO);
		}catch(Exception e){
			this.addActionError("渠道参数问题:"+e.getMessage());
		}
		
		request.setAttribute("list", page);
		return SUCCESS;
	}
	/**
	 * 渠道参数修改
	 * @return
	 */
	public String edit(){
		if( channelParmDTO == null && channelParmDTO.getCode() == null ){
			this.saveErrorMessage("渠道参数不正确！");
			return INPUT;
		}
		try {
			channelParmDTO = findchannelParmDTOByCode(channelParmDTO);
		} catch (Exception e) {
			this.saveErrorMessage("渠道参数修改发生错误！"+e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	public String save(){
		if( channelParmDTO == null && channelParmDTO.getCode() == null ){
			this.saveErrorMessage("渠道参数不正确！");
			return INPUT;
		}
		try{
			ChannelParmDTO entity = findchannelParmDTOByCode(channelParmDTO);
			entity.setParvalue(channelParmDTO.getParvalue());
			entity.setUserCode(getLoginAccount().getLoginName());
			mPChannelParmAppService.updata(entity);
		}catch(Exception e ){
			this.addActionError( "更新渠道参数异常:"+e.getMessage() );
			return INPUT;
		}
		saveSuccessMessage("修改成功");
		return SUCCESS;
	}
	private ChannelParmDTO findchannelParmDTOByCode(ChannelParmDTO channelParmDTO) throws Exception{
		page = mPChannelParmAppService.queryListPage(page,channelParmDTO);
		if(page!=null && page.getList()!=null&&!page.getList().isEmpty()&&page.getList().size()==1)
			return  page.getList().get(0);
		return null;
	}
	private LoginAccount getLoginAccount(){
		return (LoginAccount)request.getSession().getAttribute( SessionKey.LOGIN_ACCOUNT ); 
	}
}