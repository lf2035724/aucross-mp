package com.ylink.ylpay.project.mp.cust.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ylink.ylpay.common.project.mp.app.ManualRecoveryAppService;
import com.ylink.ylpay.common.project.mp.dto.ManualRecovery;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.mp.cust.service.CustManualRecoveryManager;

/**
 * 人工找回密码接口.
 * 
 * @author 潘瑞峥
 * @date 2013-1-4
 */
@Component( "manualRecoveryAppService" )
public class ManualRecoveryAppServiceImpl implements ManualRecoveryAppService {

	@Autowired
	private CustManualRecoveryManager custManualRecoveryManager;

	/**
	 * 通过id查询.
	 */
	@Override
	public ManualRecovery find( String id ) throws MpCheckedException {
		try {
			return this.custManualRecoveryManager.find( id );
		} catch ( Exception ex ) {
			throw new MpCheckedException( ex );
		}
	}

	/**
	 * 通过custId查询待审核且未找回的记录.
	 */
	@Override
	public ManualRecovery findValidByCustId( String custId ) throws MpCheckedException {
		try {
			return this.custManualRecoveryManager.findValidByCustId( custId );
		} catch ( Exception ex ) {
			throw new MpCheckedException( ex );
		}
	}

	/**
	 * 保存.
	 */
	@Override
	public void save( ManualRecovery dtoBean ) throws MpCheckedException {
		try {
			this.custManualRecoveryManager.save( dtoBean );
		} catch ( Exception ex ) {
			throw new MpCheckedException( ex );
		}
	}

	/**
	 * 通过id修改为找回密码成功状态.
	 */
	@Override
	public void modifySuccess( String id ) throws MpCheckedException {
		try {
			this.custManualRecoveryManager.modifySuccess( id );
		} catch ( Exception ex ) {
			throw new MpCheckedException( ex );
		}
	}

}