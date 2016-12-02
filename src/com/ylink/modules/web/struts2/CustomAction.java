package com.ylink.modules.web.struts2;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.Validate;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.lightssh.common.dao.Term;
import com.google.code.lightssh.common.entity.Persistence;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.web.SessionKey;
import com.google.code.lightssh.common.web.action.CrudAction;
import com.google.code.lightssh.project.security.entity.LoginAccount;
import com.ylink.modules.orm.PropertyFilterBuilder;

/**
 * 扩展CrudAction.
 * 
 * @author 潘瑞峥
 * @date 2012-10-29
 */
public class CustomAction<T extends Persistence<?>> extends CrudAction<T> {

	private static final long serialVersionUID = 2282387301869562917L;

	protected Logger logger = LoggerFactory.getLogger( this.getClass() );

	public static final String JASPER = "jasper";

	/**
	 * 获取当前登录用户.
	 * 
	 * @return
	 */
	public LoginAccount getCurrentLoginAccount() {
		return ( LoginAccount ) request.getSession().getAttribute( SessionKey.LOGIN_ACCOUNT );
	}

	/**
	 * 从HttpRequest中创建com.google.code.lightssh.common.dao.Term集合. <br>
	 * 
	 * 命名规则为Filter属性前缀_比较类型_属性类型_属性名.
	 * 
	 * <pre>
	 * eg. < input type="text" name="filter_LIKE_S_loginName" value="${param['filter_LIKE_S_loginName'] }" />
	 *     < input type="text" name="filter_LIKE_S_user.id" value="${param['filter_LIKE_S_user.id'] }" />
	 * </pre>
	 */
	public String findPage() {
		logger.debug( "findPage..." );
		if ( null == super.getPage() ) {
			super.setPage( new ListPage<T>() );
		}

		/* 自动根据request参数转换为com.google.code.lightssh.common.dao.Term对象. */
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Term> termList = PropertyFilterBuilder.buildTermFromHttpRequest( request );
		super.setPage( super.manager.list( super.getPage(), termList ) );
		request.setAttribute( "list", super.getPage() );
		return SUCCESS;
	}

	/**
	 * 自动根据模型驱动的主键查询实体.
	 * 
	 * @return
	 */
	public String detail() {
		logger.debug( "detail..." );

		Validate.notNull( this.getModel() );
		Validate.notNull( this.getModel().getIdentity() );

		super.model = super.manager.get( super.getModel().getIdentity() );

		return SUCCESS;
	}

	/**
	 * 插入.
	 */
	public String save() {
		logger.debug( "save..." );

		Validate.notNull( this.getModel() );

		try {
			super.manager.save( super.getModel() );
		} catch ( Exception ex ) {
			super.saveErrorMessage( ex.getMessage() );
			logger.error( ex.getMessage() );
			return INPUT;
		}

		String hint = "新增成功！";
		// String hint = ( model instanceof Nameable ) ? ( "[" + ( ( Nameable ) model ).getName() +
		// "]" ) : "" + "新增成功！";
		super.saveSuccessMessage( hint );
		return SUCCESS;
	}

	/**
	 * 修改.
	 */
	public String update() {
		logger.debug( "update..." );

		Validate.notNull( this.getModel() );

		try {
			super.manager.update( super.getModel() );
		} catch ( Exception ex ) {
			super.saveErrorMessage( ex.getMessage() );
			logger.error( ex.getMessage() );
			return INPUT;
		}

		String hint = "修改成功！";
		// String hint = ( model instanceof Nameable ) ? ( "[" + ( ( Nameable ) model ).getName() +
		// "]" ) : "" + "修改成功！";
		super.saveSuccessMessage( hint );
		return SUCCESS;
	}

	/**
	 * 跳转页面.
	 */
	public String gotoPage() {
		return SUCCESS;
	}

}