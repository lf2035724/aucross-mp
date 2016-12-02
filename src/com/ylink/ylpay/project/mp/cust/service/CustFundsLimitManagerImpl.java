package com.ylink.ylpay.project.mp.cust.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.google.code.lightssh.common.dao.SearchCondition;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.common.project.mp.constant.OptType;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.mp.basic.entity.FundsLimit;
import com.ylink.ylpay.project.mp.basic.service.FundsLimitManager;
import com.ylink.ylpay.project.mp.cust.dao.CustFundsLimitDao;
import com.ylink.ylpay.project.mp.cust.entity.CustFundsLimit;

/**
 * 客户资金限额业务层.
 * 
 * @author 潘瑞峥
 * @date 2012-11-26
 */
@Component( "custFundsLimitManager" )
public class CustFundsLimitManagerImpl extends BaseManagerImpl<CustFundsLimit> implements CustFundsLimitManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = -450788480833153333L;

	private static Logger logger = LoggerFactory.getLogger( CustFundsLimitManagerImpl.class );

	private CustFundsLimitDao custFundsLimitDao;

	@Autowired
	private FundsLimitManager fundsLimitManager;

	@Autowired
	public void setCustFundsLimitDao( CustFundsLimitDao custFundsLimitDao ) {
		this.custFundsLimitDao = custFundsLimitDao;
		super.dao = this.custFundsLimitDao;
	}
	public CustFundsLimitDao getCustFundsLimitDao() {
		return custFundsLimitDao;
	}

	/**
	 * 保存.
	 */
	@Override
	public void save( String custId, Long fundsLimitId ) {
		Assert.hasText( custId );
		Assert.notNull( fundsLimitId );

		FundsLimit FundsLimitEntity = this.fundsLimitManager.get( fundsLimitId );
		CustFundsLimit entity = new CustFundsLimit();
		BeanUtils.copyProperties( FundsLimitEntity, entity, new String[] { "id", "identity" } );
		entity.setCustId( custId );

		this.custFundsLimitDao.create( entity );
	}


	/**
	 * 保存.
	 */
	@Override
	public void save( String custId ) {
		List<FundsLimit> list = this.fundsLimitManager.listAll();
		if ( !CollectionUtils.isEmpty( list ) ) {
			for ( FundsLimit fundsLimitEntity : list ) {
				this.save( custId, fundsLimitEntity.getIdentity() );
			}
		} else {
			logger.debug( "未发现虚户资金限额." );
		}
	}
	


	/**
	 * 查询资金限额
	 * @param custId
	 * @param optType
	 * @author xuwei
	 * @return
	 */
	@Override
	public List<CustFundsLimit> getList(String custId, OptType optType) {
		return getCustFundsLimitDao().getList( custId, optType.getValue() );
	}


	/**
	 * 修改资金限额
	 * @param custId
	 * @param optType
	 * @param singleWarn
	 * @param singleLimit
	 * @param dayWarn
	 * @param dayLimit
	 * @return
	 * @throws MpCheckedException
	 */
	@Override
	public List<CustFundsLimit> updateFundsLimit(String custId,
			OptType optType, Long singleWarn, Long singleLimit, Long dayWarn,
			Long dayLimit) {
		logger.info("updateFundsLimit,custId[{}],optType[{}]",custId,optType);
		SearchCondition sc = new SearchCondition();
		ListPage<CustFundsLimit> page = new ListPage<CustFundsLimit>();
		sc.equal( "custId", custId ).equal( "businessId", optType.getValue() );

		page = dao.list(page, sc);
		if ( page == null || page.getList() == null || page.getList().isEmpty() ){
			
			CustFundsLimit custFundsLimit = new CustFundsLimit();
			//通过optType获取 monthWarn、monthLimit、accType
			FundsLimit fundLimit = fundsLimitManager.findBBusiness(optType.getValue());
			if(null == fundLimit){
				return null;
			}
			
			custFundsLimit.setDayLimit(dayLimit);
			custFundsLimit.setSingleWarn(singleWarn);
			custFundsLimit.setSingleLimit(singleLimit);
			custFundsLimit.setDayWarn(dayWarn);
			custFundsLimit.setAccType(fundLimit.getAccType());
			custFundsLimit.setMonthLimit(fundLimit.getMonthLimit());
			custFundsLimit.setMonthWarn(fundLimit.getMonthWarn());
			custFundsLimit.setBusinessId(optType.getValue());
			custFundsLimit.setCustId(custId);
			dao.create(custFundsLimit);
			
			List<CustFundsLimit> list = new ArrayList<CustFundsLimit>();
			list.add(custFundsLimit);
			
			logger.info("updateFundsLimit,END");
			
			return list;
			
		}else{

			List<CustFundsLimit> listCustFundsLimit = page.getList();
			for(CustFundsLimit custFundsLimit:listCustFundsLimit){
				custFundsLimit.setDayLimit(dayLimit);
				custFundsLimit.setSingleWarn(singleWarn);
				custFundsLimit.setSingleLimit(singleLimit);
				custFundsLimit.setDayWarn(dayWarn);
				dao.update( custFundsLimit );
			}
			logger.info("updateFundsLimit,END");
			return page.getList();
		}			
	}
}