package com.ylink.ylpay.project.mp.workorder.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.google.code.lightssh.common.dao.jpa.JpaAnnotationDao;
import com.google.code.lightssh.common.model.page.ListPage;
import com.google.code.lightssh.common.util.ParserUtil;
import com.ylink.ylpay.project.mp.workorder.entity.WorkOrder;
/**
 * @author feng.li
 */
@Repository( "workOrderDao" )
public class WorkOrderDaoJpa extends JpaAnnotationDao<WorkOrder> implements WorkOrderDao{

	private static final long serialVersionUID = -5928232947328072032L;
	public void testSave(){
		WorkOrder workOrder = new WorkOrder();
		super.getEntityManager().persist(workOrder);
	}
	
	public int getCount(ListPage<WorkOrder> page,WorkOrder workOrder){
		StringBuffer hql = new StringBuffer("select count(id) from "+this.entityClass.getName()+" where 1=1 ");
		List<Object> params = new ArrayList<Object>( );
    	if(workOrder.getStatus()!=null&&!"".equals(workOrder.getStatus())){
    		hql.append("and status = ? ");
			params.add(workOrder.getStatus());
    	}
		Query query = getEntityManager().createQuery(hql.toString());
		this.addQueryParams(query,params);
		Object count = query.getSingleResult();
		return ( count != null && count instanceof Long)?
			 ParserUtil.parseInt(((Long)count).intValue()):0;
	}

	@Override
	public WorkOrder read(Class<?> clazz, WorkOrder workOrder) {
		if( workOrder == null )
			return null;
		return  (WorkOrder) this.getEntityManager().find(clazz,workOrder.getIdentity());
	}

	@SuppressWarnings("unchecked")
	@Override
	public WorkOrder getWorkOrder(Long id) {
		// TODO Auto-generated method stub
		
		String hql = " SELECT m FROM " + entityClass.getName() 
			+ " AS m WHERE m.id = ?  ";
		
		Query query = this.getEntityManager().createQuery(hql);
		List<WorkOrder> results = this.addQueryParams(query,id).getResultList();
		return ( results == null || results.isEmpty() ) ? null : results.get( 0 );
	}
}
