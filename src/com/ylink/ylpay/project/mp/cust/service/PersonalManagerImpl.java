package com.ylink.ylpay.project.mp.cust.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.google.code.lightssh.common.ApplicationException;
import com.google.code.lightssh.common.dao.SearchCondition;
import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.dao.Term.Type;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.google.code.lightssh.project.geo.entity.GeographicBoundary;
import com.google.code.lightssh.project.geo.service.GeographicBoundaryManager;
import com.ylink.ylpay.common.project.mp.constant.AuthenticationStatus;
import com.ylink.ylpay.project.mp.cust.dao.PersonalDao;
import com.ylink.ylpay.project.mp.cust.entity.Customer;
import com.ylink.ylpay.project.mp.cust.entity.Personal;
import com.ylink.ylpay.project.mp.cust.entity.PersonalOtherInfo;

/**
 * 个人客户业务层.
 * 
 * @author 张磊
 * @date 2012-10-31
 */
@Component( "personalManager" )
public class PersonalManagerImpl extends BaseManagerImpl<Personal> implements PersonalManager {

	private static final long serialVersionUID = -5925485968629564531L;

	private static Logger logger = LoggerFactory.getLogger( PersonalManagerImpl.class );

	// @Resource( name = "sequenceManager" )
	// private SequenceManager sequenceManager;

	@Autowired
	private GeographicBoundaryManager geographicBoundaryManager;

	private PersonalDao personalDao;
	@Resource( name = "personalOtherInfoManager" )
	private PersonalOtherInfoManager personalOtherInfoManager;

	@Resource( name = "personalDao" )
	public void setPersonalDao( PersonalDao personalDao ) {
		this.personalDao = personalDao;
		super.dao = personalDao;
	}

	public PersonalDao getDao() {
		return ( PersonalDao ) super.dao;
	}

	@SuppressWarnings( "unused" )
	@Override
	public void save( Personal personal ) {
		if ( personal == null )
			throw new ApplicationException( "数据为空，不能进行保存！" );

		Customer original = null;
		/*
		 * boolean inserted = personal.isInsert();
		 * 
		 * if ( inserted ) { if(personal.getId() == null){ personal.setId(
		 * sequenceManager.nextSequenceNumber( personal ) ); }else{
		 * 
		 * }
		 */
		personal.setCreateDate( new Date() );
		dao.create( personal );
		/*
		 * } else { Personal old = dao.read( personal ); if ( old != null ) { original =
		 * old.clone(); ReflectionUtil.assign( personal, old ); dao.update( old ); } }
		 */
	}

	/**
	 * 修改客户支付密码.
	 * 
	 * @author 张磊
	 * @date 2012-11-01
	 */
	@Override
	public void updatePassword( String userId, String oldPassword, String newPassword ) {
		Validate.notNull( userId );
		Assert.hasText( oldPassword );
		Assert.hasText( newPassword );

		List<Term> termList = new ArrayList<Term>();
		termList.add( new Term( Type.EQUAL, "id", userId ) );
		termList.add( new Term( Type.EQUAL, "payPassword", oldPassword ) );

		Personal entity = this.personalDao.findUnique( termList );
		if ( null != entity ) {
			entity.setPayPassword( newPassword );
			this.personalDao.update( entity );
		}
	}

	@Override
	public Personal getPersonal( Personal personal ) {
		return getDao().read( Personal.class, personal );
	}

	@Override
	public void saveOrUpdate( com.ylink.ylpay.common.project.mp.dto.Personal personalDtoBean ) {
		if ( personalDtoBean == null )
			throw new ApplicationException( "数据为空，不能进行保存！" );

		/*
		 * Customer original = null; boolean inserted = personalDtoBean.isInsert();
		 * 
		 * if ( inserted ) { personalDtoBean.setId( sequenceManager.nextSequenceNumber(
		 * personalDtoBean ) ); dao.create( personalDtoBean ); } else { Personal old = dao.read(
		 * personalDtoBean ); if ( old != null ) { original = old.clone(); ReflectionUtil.assign(
		 * personalDtoBean, old ); dao.update( old ); } }
		 */
	}

	/**
	 * 获取客户信息
	 * 
	 * @author 张磊
	 * @date 2012-11-01
	 * @param identityCardNumber
	 * @param credentialsType
	 * @param name
	 */
	@Override
	public Personal getCustomerInfo( String id ) {
		return getDao().getCustomerInfo( id );
	}

	/**
	 * xinxiguanli ........
	 */
	@Override
	public void save( com.ylink.ylpay.common.project.mp.dto.Personal personalDtoBean ) {
		// TODO Auto-generated method stub

	}

	@Override
	public Personal checkPayPassword( String custId, String payPassword ) {
		return getDao().checkPayPassword( custId, payPassword );
	}

	/**
	 * 修改身份证姓名
	 * 
	 * @param custId
	 * @param idNo
	 * @param idType
	 * @param trueName
	 * @return
	 */
	@Override
	public void updateCert( String custId, String idNo, String idType, String trueName ) {
		Validate.notNull( custId );
		Assert.hasText( idNo );
		Assert.hasText( idType );
		Assert.hasText( trueName );

		List<Term> termList = new ArrayList<Term>();
		termList.add( new Term( Type.EQUAL, "id", custId ) );
//		termList.add( new Term( Type.EQUAL, "identityCardNumber", idNo ) );
//		termList.add( new Term( Type.EQUAL, "credentialsType", idType ) );
		// termList.add( new Term( Type.EQUAL, "name", trueName ) );

		Personal entity = this.personalDao.findUnique( termList );
		if ( null != entity ) {
			entity.setName( trueName );
			
			/* 潘瑞峥 2013-03-08 UPDATE. 之前未修改证件号码和证件类型. */
			// 证件号码.
			entity.setIdentityCardNumber( idNo );
			// 证件类型.
			entity.setCredentialsType( idType );
			/* 潘瑞峥2013-03-08. 之前未修改证件号码和证件类型.end. */
			this.personalDao.update( entity );
		}

	}

	@Override
	public ListPage<Personal> list( ListPage<Personal> page, Personal t ) {
		SearchCondition sc = new SearchCondition();
		if ( t != null ) {
			if ( !StringUtils.isEmpty( t.getCredentialsType() ) ) {
				sc.equal( "credentialsType", t.getCredentialsType() );
			}
			if ( !StringUtils.isEmpty( t.getIdentityCardNumber() ) ) {
				sc.equal( "identityCardNumber", t.getIdentityCardNumber() );
			}
			if ( !StringUtils.isEmpty( t.getName() ) ) {
				sc.equal( "name", t.getName() );
			}
			if ( !StringUtils.isEmpty( t.getRegisteredType() ) ) {
				sc.equal( "registeredType", t.getRegisteredType() );
			}
		}

		return dao.list( page, sc );
	}

	/**
	 * 修改个人信息 xuwei
	 */
	@Override
	public void updatePersonal( com.ylink.ylpay.common.project.mp.dto.Personal personal ) {

		if ( personal == null )
			throw new ApplicationException( "数据为空，不能进行保存！" );

		List<Term> termList = new ArrayList<Term>();
		termList.add( new Term( Type.EQUAL, "id", personal.getId() ) );
		Personal entity = this.personalDao.findUnique( termList );
		if ( personal.getRegisteredType() != null ) {
			entity.setRegisteredType( personal.getRegisteredType() );
		}
		if ( personal.getCertExpiryDate() != null ) {
			entity.setCertExpiryDate( personal.getCertExpiryDate() );
		}
		if ( personal.getNickName() != null ) {
			entity.setNickName( personal.getNickName() );
		}
		if ( personal.getGender() != null ) {
			entity.setGender( personal.getGender() );
		}
		if ( personal.getBirthday() != null ) {
			entity.setBirthday( personal.getBirthday() );
		}
		if ( personal.getMaritalStatus() != null ) {
			entity.setMaritalStatus( personal.getMaritalStatus() );
		}
		if ( personal.getEthnicGroup() != null ) {
			entity.setEthnicGroup( personal.getEthnicGroup() );
		}
		if ( personal.getPartyAffiliation() != null ) {
			entity.setPartyAffiliation( personal.getPartyAffiliation() );
		}
		if ( personal.getBloodType() != null ) {
			entity.setBloodType( personal.getBloodType() );
		}
		if ( personal.getHeight() != null ) {
			entity.setHeight( personal.getHeight() );
		}
		if ( personal.getWeight() != null ) {
			entity.setWeight( personal.getWeight() );
		}
		if ( personal.getProfession() != null ) {
			entity.setProfession( personal.getProfession() );
		}
		if ( personal.getInvestmentExp() != null ) {
			entity.setInvestmentExp( personal.getInvestmentExp() );
		}
		if ( personal.getAnnualIncome() != null ) {
			entity.setAnnualIncome( personal.getAnnualIncome() );
		}
		if ( personal.getCountryId() != null ) {
			GeographicBoundary geographicBoundary = this.geographicBoundaryManager.get( personal.getCountryId() );
			entity.setCountryId( geographicBoundary );
		}
		if ( personal.getSecondaryGeoId() != null ) {
			GeographicBoundary geographicBoundary = this.geographicBoundaryManager.get( personal.getSecondaryGeoId() );
			entity.setSecondaryGeoId( geographicBoundary );
		}

		if ( personal.getThirdGeoId() != null ) {
			GeographicBoundary geographicBoundary = this.geographicBoundaryManager.get( personal.getThirdGeoId() );
			entity.setThirdGeoId( geographicBoundary );
		}
		if ( personal.getFourthGeoId() != null ) {
			GeographicBoundary geographicBoundary = this.geographicBoundaryManager.get( personal.getFourthGeoId() );
			entity.setFourthGeoId( geographicBoundary );
		}
		// 个人客户其他信息保存
		PersonalOtherInfo poi = new PersonalOtherInfo();

		BeanUtils.copyProperties( personal, poi );
		poi.setCustId( personal.getId() );

		this.personalDao.update( entity );
		this.personalOtherInfoManager.save( poi );
	}

	/**
	 * 条件查询.
	 * 
	 * @param termList
	 * @return
	 * 
	 * @author 潘瑞峥
	 * @date 2013-01-15
	 */
	@Override
	public Personal find( List<Term> termList ) {
		return this.getDao().findUnique( termList );
	}

	/**
	 * 条件查询.
	 * 
	 * @param termList
	 * @return
	 * 
	 * @author 潘瑞峥
	 * @date 2013-01-15
	 */
	@Override
	public List<Personal> findList( List<Term> termList ) {
		return this.getDao().findList( termList );
	}

	/**
	 * 修改认证状态.
	 */
	@Override
	public void modifyAuthStatus( String id, AuthenticationStatus authStatus ) {
		Personal entity = this.getDao().read( id );
		if ( null != entity ) {
			/* 认证中. */
			if ( AuthenticationStatus.AUTHING.getValue().equals( entity.getCertStatus() ) ) {
				entity.setCertStatus( authStatus.getValue() );
				this.getDao().update( entity );
			} else {
				AuthenticationStatus _status = AuthenticationStatus.parseOf( entity.getCertStatus() );
				logger.error( "该记录认证状态[{}]不可修改.", _status.getValue() + "." + _status.getDisplayName() );
			}
		} else {
			throw new ApplicationException( "未根据id[" + id + "]查询到个人客户." );
		}
	}

}