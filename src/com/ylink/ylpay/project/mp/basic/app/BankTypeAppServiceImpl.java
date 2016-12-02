package com.ylink.ylpay.project.mp.basic.app;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.ylink.ylpay.common.project.mp.app.BankTypeAppService;
import com.ylink.ylpay.common.project.mp.dto.BankType;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.mp.basic.entity.BanType;
import com.ylink.ylpay.project.mp.basic.service.BanTypeManager;
/**
 * 银行信息查询接口
 * @author xuwei
 *
 */
@Component("bankTypeAppService")
public class BankTypeAppServiceImpl implements BankTypeAppService{
	
	@Resource( name = "banTypeManager")
	private BanTypeManager banTypeManager;

	public void setBanTypeManager(BanTypeManager banTypeManager) {
		this.banTypeManager = banTypeManager;
	}

	public BanTypeManager getBanTypeManager() {
		return banTypeManager;
	}
	
	/**
	 * 通过银行类型查询银行信息
	 */
	public BankType getBankType(String bankType) throws MpCheckedException {

		Assert.hasText( bankType );

		BanType entity = this.banTypeManager.get( bankType );
		com.ylink.ylpay.common.project.mp.dto.BankType dtoBean = null;
		if ( null != entity ) {
			dtoBean = new com.ylink.ylpay.common.project.mp.dto.BankType();
			BeanUtils.copyProperties( entity, dtoBean );
		}
		return dtoBean;
	}

}
