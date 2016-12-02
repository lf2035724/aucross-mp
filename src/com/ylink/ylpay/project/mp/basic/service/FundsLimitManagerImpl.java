package com.ylink.ylpay.project.mp.basic.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.google.code.lightssh.common.dao.SearchCondition;
import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.service.BaseManagerImpl;
import com.ylink.ylpay.project.mp.basic.dao.FundsLimitDao;
import com.ylink.ylpay.project.mp.basic.entity.FundsLimit;

@Component("fundsLimitManager")
public class FundsLimitManagerImpl extends BaseManagerImpl<FundsLimit>
		implements FundsLimitManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2933284363949919811L;
	private FundsLimitDao fundsLimitDao;

	@Resource(name = "fundsLimitDao")
	public void setFundsLimitDao(FundsLimitDao fundsLimitDao) {
		this.fundsLimitDao = fundsLimitDao;
		super.dao = fundsLimitDao;
	}

	public FundsLimitDao getFundsLimitDao() {
		return fundsLimitDao;
	}

	@Override
	public boolean isUniqueAccountAndBusiness(FundsLimit fund) {
		if (fund == null || fund.getAccType() == null
				|| fund.getBusinessId() == null)
			return false;

		ListPage<FundsLimit> page = new ListPage<FundsLimit>();
		SearchCondition sc = new SearchCondition();
		sc.equal("accType", fund.getAccType()).equal("businessId",
				fund.getBusinessId());
		page = dao.list(page, sc);

		FundsLimit exists = (page.getList() == null || page.getList().isEmpty()) ? null
				: page.getList().get(0);

		return exists == null
				|| exists.getIdentity().equals(fund.getIdentity());
	}

	public ListPage<FundsLimit> list(ListPage<FundsLimit> page, FundsLimit t) {
		SearchCondition sc = new SearchCondition();
		if (t != null) {
			if (StringUtils.isNotBlank(t.getAccType()))
				sc.equal("accType", t.getAccType().trim());

			if (StringUtils.isNotBlank(t.getBusinessId()))
				sc.equal("businessId", t.getBusinessId().trim());
		}

		return dao.list(page, sc);
	}

	@Override
	public List<FundsLimit> listAll() {
		return this.fundsLimitDao.listAll();
	}

	/**
	 * 查询是否已经存在账户和交易类型
	 */
	@Override
	public FundsLimit findByAccountAndBusiness(String id, String accType,
			String businessId) {
		Assert.hasText(accType, "帐户类型不能为空.");
		Assert.hasText(businessId, "交易类型不能为空.");

		List<Term> termList = new ArrayList<Term>();
		if (StringUtils.isNotBlank(id)) {
			termList.add(new Term(Term.Type.NOT_EQUAL, "id", id));
		}
		termList.add(new Term(Term.Type.EQUAL, "accType", accType));
		termList.add(new Term(Term.Type.EQUAL, "businessId", businessId));
		return this.fundsLimitDao.findUnique(termList);
	}

	@Override
	public FundsLimit findBBusiness(String businessId) {
		// TODO Auto-generated method stub
		Assert.hasText(businessId, "交易类型不能为空.");

		List<Term> termList = new ArrayList<Term>();
		termList.add(new Term(Term.Type.EQUAL, "businessId", businessId));
		
		return this.fundsLimitDao.findUnique(termList);
	}
}
