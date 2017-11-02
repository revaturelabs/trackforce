package com.revature.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.utils.HibernateUtil;


public class AssociateDaoHibernate implements AssociateDao{
    
    @Override
	@SuppressWarnings("rawtypes")
	public BigDecimal getNoOfAssociates(BigDecimal associate_batch_id) {
		// count on the batchid based on the batchid condition

		String count_batches = "select count(TfAssociate.tfAssociateId) from com.revature.entity where entity.tfBatchId=:associatebatch_id group by entity.tfAssociateId ";
		SessionFactory conn = HibernateUtil.getSession();
		Session obj=conn.getCurrentSession();
		Query query =obj.createQuery(count_batches);
		query.setParameter("associatebatch_id", associate_batch_id);
		BigDecimal associate_count =  (BigDecimal) query.list().get(0);
		//conn.close();
		return associate_count;
		
	}
	
	/**
	 * Get a list of associates in a batch.
	 * 
	 * @param batchName - The name of the batch
	 * @return - A list of associates.
	 */
	@Override
    public List<TfAssociate> getAssociatesByBatch(String batchName)
    {
        int batchID = new BatchDaoHibernate().getBatchID(batchName);
        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.openSession();
        
//        String hql = "FROM com.revature.Associate associate WHERE associate.batchID = :batchID";
//        TypedQuery<TfAssociate> query = session.createQuery(hql);
//        query.setParameter("batchID", batchID);
//        List<TfAssociate> associates = query.getResultList();
        
        TfBatch batch = new BatchDaoHibernate().getBatch(batchName);
        
        List<TfAssociate> associates = new ArrayList<TfAssociate>();
        associates.addAll(batch.getTfAssociates());
        
        session.close();
        
        return associates;
    }
}
