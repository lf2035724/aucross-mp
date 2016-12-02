package com.ylink.ylpay.project.mp.cust.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ylink.ylpay.common.project.mp.app.CustNameAuthAppService;
import com.ylink.ylpay.common.project.mp.dto.CustNameAuthBean;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.mp.cust.service.CustNameAuthManager;

/**
 * 个人客户实名认证接口.
 * 
 * @author 潘瑞峥
 * @date 2013-1-16
 */
@Component( "custNameAuthAppService" )
public class CustNameAuthAppServiceImpl implements CustNameAuthAppService {

	@Autowired
	private CustNameAuthManager custNameAuthManager;

	/**
	 * 保存.
	 */
	@Override
	public void save( CustNameAuthBean dtoBean ) throws MpCheckedException {
		try {
			this.custNameAuthManager.save( dtoBean );
		} catch ( Exception ex ) {
			throw new MpCheckedException( ex );
		}
	}

}