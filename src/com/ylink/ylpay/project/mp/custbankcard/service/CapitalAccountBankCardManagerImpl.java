/**
 * 版权所有(C) 2012 深圳市雁联计算系统有限公司
 */

package com.ylink.ylpay.project.mp.custbankcard.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.google.code.lightssh.common.dao.SearchCondition;
import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.dao.Term.Type;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.common.project.mp.dto.BankCard;
import com.ylink.ylpay.common.project.mp.dto.CapitalAccountBankCardDTO;
import com.ylink.ylpay.common.project.mp.exception.MpCheckedException;
import com.ylink.ylpay.project.mp.custbankcard.dao.CapitalAccountBankCardDao;
import com.ylink.ylpay.project.mp.custbankcard.dao.CustBankcardDao;
import com.ylink.ylpay.project.mp.custbankcard.entity.CapitalAccountBankCard;
import com.ylink.ylpay.project.mp.custbankcard.entity.CustBankcard;

/** 
 * @author feng.li
 * @date 2014-11-21
 * @description：TODO
 */
@Component("capitalAccountBankCardManager")
public class CapitalAccountBankCardManagerImpl extends BaseManagerImpl<CapitalAccountBankCard> implements CapitalAccountBankCardManager{

	private static final long serialVersionUID = 309428623795046002L;

	//private static Logger log = LoggerFactory.getLogger(CapitalAccountBankCardManagerImpl.class);
	
	@Resource(name="custBankcardDao")
	private CustBankcardDao custBankcardDao;
	
	@Resource(name="capitalAccountBankCardDao")
	public void setDao( CapitalAccountBankCardDao dao ){
		this.dao = dao;
	}
	
	public CapitalAccountBankCardDao getDao(){
		return (CapitalAccountBankCardDao)this.dao;
	}
	
	@Resource(name="custBankcardManager")
	private CustBankcardManager custBankcardManager;
	
//	@Resource( name = "custBankcardManager" )
//	private CustBankcardManager custBankcardManager;
	
	/**
	 * 判断是否存在绑定关系
	 * 
	 * @param merchant 商户编码 
	 * @param custId 客户ID
	 * @author feng.li
	 * @date 2014-11-21
	 */
	public boolean isExists( String bankCardId,String capitalAccountNO) throws MpCheckedException{
		Validate.notNull( bankCardId );
		Validate.notNull( capitalAccountNO );
		CapitalAccountBankCard capitalAccountBankCard = getDao().get(bankCardId, capitalAccountNO);
		return capitalAccountBankCard != null;
	}
	
	/**
	 * 是否第一次绑定
	 * @param bankCardId 银行卡ID
	 * @author feng.li
	 * @date 2014-11-21
	 */
	public boolean isFirstBind( String bankCardId) throws MpCheckedException{
		Validate.notNull( bankCardId );
		List<CapitalAccountBankCard> list = getDao().listBankCardBindInfo(bankCardId);
		if(list!=null&&list.size()>0) return false;
		return true;
	}
	
	/**
	 * 是否只有一条绑定记录
	 * @param bankCardId 银行卡ID
	 * @author feng.li
	 * @date 2014-11-21
	 */
	public boolean isOnlyOne( String bankCardId) throws MpCheckedException{
		Validate.notNull( bankCardId );
		List<CapitalAccountBankCard> list = getDao().listBankCardBindInfo(bankCardId);
		if(list==null||list.size() != 1) return false;
		return true;
	}
	
	@Override
	public ListPage<CapitalAccountBankCardDTO> list(ListPage<CapitalAccountBankCardDTO> page,CapitalAccountBankCardDTO t ) {
		SearchCondition sc = new SearchCondition();
		if(t == null) t = new CapitalAccountBankCardDTO();
		ListPage<CapitalAccountBankCard> listPage = new ListPage<CapitalAccountBankCard>();
		listPage.setAllSize(page.getAllSize());
		listPage.setNumber(page.getNumber());
		listPage.setOrderBy(page.getOrderBy());
		listPage.setSize(page.getSize());
		if( t != null ){
			if(!StringUtils.isEmpty(t.getCapitalAccountNO())){
				sc.equal("capitalAccountNO", t.getCapitalAccountNO());
			}
			if((t.getBankCard()!=null&&!StringUtils.isEmpty(t.getBankCard().getId()))){
				sc.equal("bankCard.id", t.getBankCard().getId());
			}
			if((t.getBankCard()!=null&&!StringUtils.isEmpty(t.getBankCard().getCardNo()))){
				sc.equal("bankCard.cardNo", t.getBankCard().getCardNo());
			}
			if((t.getBankCard()!=null&&!StringUtils.isEmpty(t.getBankCard().getCustId()))){
				sc.equal("bankCard.custId", t.getBankCard().getCustId());
			}
			if((t.getBankCard()!=null&&!StringUtils.isEmpty(t.getBankCard().getIsCert()))){
				sc.equal("bankCard.isCert", t.getBankCard().getIsCert());
			}
			if((t.getBankCard()!=null&&!StringUtils.isEmpty(t.getBankCard().getStatus()))){
				sc.equal("bankCard.status", t.getBankCard().getStatus());
			}
		}
		ListPage<CapitalAccountBankCard> resultPage = dao.list(listPage,sc);
		return convertPage(resultPage);
	}
	
	/**
	 * 查询绑定关系
	 */
	public CapitalAccountBankCardDTO get(String bankCardId,String capitalAccountNO) throws MpCheckedException{
		Validate.notNull( bankCardId );
		Validate.notNull( capitalAccountNO );
		CapitalAccountBankCard card = getDao().get(bankCardId, capitalAccountNO);
		return convertToDTOFromEntity(card);
	}
	
	private CapitalAccountBankCard convertToEntityFromDTO(CapitalAccountBankCardDTO dto){
		if(dto == null ) return null;
		CapitalAccountBankCard entity = new CapitalAccountBankCard();
		CustBankcard cardEntity = new CustBankcard();
		BeanUtils.copyProperties(dto.getBankCard(), cardEntity,new String[]{"accountType","auditStatus","auditBindingStatus"});
		entity.setBankCard(cardEntity);
		entity.setCapitalAccountNO(dto.getCapitalAccountNO());
		entity.setCreateTime(dto.getCreateTime());
		entity.setIdentity(dto.getId());
		return entity;
	}
	
	@Override
	public void save(CapitalAccountBankCardDTO dto)
			throws MpCheckedException {
		CapitalAccountBankCard capitalAccountBankCard = convertToEntityFromDTO(dto);
		CustBankcard cardEntity = new CustBankcard();
		cardEntity.setIdentity(dto.getBankCard().getId());
		cardEntity = custBankcardManager.get(cardEntity);
		capitalAccountBankCard.setBankCard(cardEntity);
		getDao().create(capitalAccountBankCard);
	}
	
	@Override
	public void remove(String bankCardId,String capitalAccountNO) throws MpCheckedException{
		Validate.notNull( bankCardId );
		Validate.notNull( capitalAccountNO );
		boolean isOnly = this.isOnlyOne(bankCardId);
		getDao().delete(bankCardId,capitalAccountNO);
		if(isOnly){
			List<Term> termList = new ArrayList<Term>();
			termList.add( new Term( Type.EQUAL, "id", bankCardId ) );
			CustBankcard entity = this.custBankcardDao.findUnique( termList );
			if(entity != null&&entity.getStatus() != "0"){
				entity.setStatus( "0" );
				entity.setUpdateTime( new Date() );
				this.custBankcardDao.update( entity );
			}
		}
	}
	
	private CapitalAccountBankCardDTO convertToDTOFromEntity(CapitalAccountBankCard entity){
		if(entity == null ) return null;
		CapitalAccountBankCardDTO dto = new CapitalAccountBankCardDTO();
		BankCard cardDTO = new BankCard();
		BeanUtils.copyProperties(entity.getBankCard(), cardDTO,new String[]{"accountType","auditStatus","auditBindingStatus"});
		dto.setBankCard(cardDTO);
		dto.setCapitalAccountNO(entity.getCapitalAccountNO());
		dto.setCreateTime(entity.getCreateTime());
		dto.setId(entity.getIdentity());
		return dto;
	}
	
	private ListPage<CapitalAccountBankCardDTO> convertPage(ListPage<CapitalAccountBankCard> page){
		if(page == null) return null;
		List<CapitalAccountBankCard> entityList = page.getList();
		if(entityList == null || entityList.size() == 0) return null;
		List<CapitalAccountBankCardDTO> resultList = new ArrayList<CapitalAccountBankCardDTO>();
		ListPage<CapitalAccountBankCardDTO> resultPage = new ListPage<CapitalAccountBankCardDTO>();
		CapitalAccountBankCardDTO dto = null;
		for(CapitalAccountBankCard entity : entityList){
			dto = this.convertToDTOFromEntity(entity);
			resultList.add(dto);
		}
		resultPage.setList(resultList);
		resultPage.setAllSize(page.getAllSize());
		resultPage.setNumber(page.getNumber());
		resultPage.setSize(page.getSize());
		resultPage.setOrderBy(page.getOrderBy());
		return resultPage;
	}
}