package com.revature.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.entity.TfBatch;
import com.revature.utils.HibernateUtil;

public class BatchDaoHibernate implements BatchDao {

	// fromdate is a variable we created, batchstartdate is from the
	/**
	 * jkl
	 * @param fromdate hi
	 */
	public List<TfBatch> getBatchDetails(Timestamp fromdate, Timestamp todate) {
		String batchdetails = "from com.revature.entity.TfBatch where (tfBatchStartDate between :fromdate and :todate) or (tfBatchEndDate between  :fromdate and :todate)";
		SessionFactory conn = HibernateUtil.getSession();
		Session obj=conn.getCurrentSession();
		Query<TfBatch> q = obj.createQuery(batchdetails);
		q.setParameter("fromdate", fromdate);
		q.setParameter("from_date", fromdate);
		q.setParameter("todate", todate);
		q.setParameter("to_date", todate);
		List<TfBatch> batch_details = q.list();
		return batch_details;

	}
	
    
    /**
     * Get a batch from the database given its name.
     */
    @Override
    public TfBatch getBatch(String batchName){
        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.openSession();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TfBatch> criteriaQuery = builder.createQuery(TfBatch.class);
        Root<TfBatch> root = criteriaQuery.from(TfBatch.class);
        criteriaQuery.select(root).where(builder.equal(root.get("tfBatchName"), batchName));
        Query<TfBatch> query = session.createQuery(criteriaQuery);
        TfBatch batch = query.getSingleResult();
        
        session.close();
        
        return batch;
    }

}