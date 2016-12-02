package com.ylink.ylpay.project.mp.user.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.dao.Term.Type;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.common.project.mp.dto.SafeQuestion;
import com.ylink.ylpay.project.mp.user.dao.UserSafeQuestionDao;
import com.ylink.ylpay.project.mp.user.entity.User;
import com.ylink.ylpay.project.mp.user.entity.UserSafeQuestion;

/**
 * 安全问题及答案业务层.
 * 
 * @author 潘瑞峥
 * @date 2012-10-26
 */
@Component( "userSafeQuestionManager" )
public class UserSafeQuestionManagerImpl extends BaseManagerImpl<UserSafeQuestion> implements UserSafeQuestionManager {

	private static final long serialVersionUID = 8167622291470530151L;

	private UserSafeQuestionDao userSafeQuestionDao;

	@Autowired
	public void setUserSafeQuestionDao( UserSafeQuestionDao userSafeQuestionDao ) {
		this.userSafeQuestionDao = userSafeQuestionDao;
		super.dao = userSafeQuestionDao;
	}

	/**
	 * 查询全部.
	 */
	@Override
	public List<UserSafeQuestion> listAll() {
		return this.userSafeQuestionDao.listAll();
	}

	/**
	 * 新增.
	 */
	@Override
	public void crate( UserSafeQuestion entity ) {
		this.userSafeQuestionDao.create( entity );
	}

	/**
	 * 修改安全问题及密码.
	 */
	@Override
	public void update( Long userId, String oldSafeAnswer, String newQuestion, String newSafeAnswer ) {
		Validate.notNull( userId );
		Assert.hasText( oldSafeAnswer );

		List<Term> termList = new ArrayList<Term>();
		termList.add( new Term( Type.EQUAL, "account.id", userId ) );
		termList.add( new Term( Type.EQUAL, "safeAnswer", oldSafeAnswer ) );

		UserSafeQuestion entity = this.userSafeQuestionDao.findUnique( termList );
		if ( null != entity ) {
			// 新问题
			if ( StringUtils.isNotBlank( newQuestion ) ) {
				entity.setQuestion( newQuestion );
			}
			// 新答案
			if ( StringUtils.isNotBlank( newSafeAnswer ) ) {
				entity.setSafeAnswer( newSafeAnswer );
			}
			this.userSafeQuestionDao.update( entity );
		}
	}

	/**
	 * 通过用户ID查询安保问题.
	 */
	@Override
	public UserSafeQuestion getSafeQuestion( Long userId ) {
		Validate.notNull( userId );

		List<Term> termList = new ArrayList<Term>();
		termList.add( new Term( Type.EQUAL, "account.id", userId ) );

		return this.userSafeQuestionDao.findUnique( termList );
	}

	@Override
	public void create(String userId, SafeQuestion question) throws Exception{
		Validate.notNull(userId);
		Validate.notNull(question);
		List<Term> termList = new ArrayList<Term>();
		termList.add( new Term( Type.EQUAL, "account.id", Long.valueOf(userId) ) );
		termList.add( new Term( Type.EQUAL, "question", question.getQuestion()));
		UserSafeQuestion entity = this.userSafeQuestionDao.findUnique(termList);
		if(entity != null){
			throw new Exception( "已存在相同安全问题." );
		}
		UserSafeQuestion userQuestion = new UserSafeQuestion();
		User user = new User();
		user.setId(Long.valueOf(userId));
		userQuestion.setAccount(user);
		userQuestion.setQuestion(question.getQuestion());
		userQuestion.setSafeAnswer(question.getSafeAnswer());
		this.userSafeQuestionDao.create( userQuestion );
	}
}