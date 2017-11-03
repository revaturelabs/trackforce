package com.revature.dao; 

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.hibernate.query.Query;

import com.revature.entity.TfBatch;
import com.revature.utils.HibernateUtil;

public class BatchDaoHibernate implements BatchDao {

	// fromdate is a variable we created, batchstartdate is from the
	@SuppressWarnings("rawtypes")
	public List<TfBatch> getBatchDetails(String fromdate, String todate) {
		String batchdetails = "from com.revature.entity where (tfBatchStartDate between :fromdate and :todate) or (tfBatchEndDate between  :from_date and :to_date)";
		SessionFactory conn = HibernateUtil.getSession();
		Session obj=conn.getCurrentSession();
		Query q = obj.createQuery(batchdetails);
		q.setParameter("fromdate", fromdate);
		q.setParameter("from_date", fromdate);
		q.setParameter("todate", todate);
		q.setParameter("to_date", todate);
		@SuppressWarnings("unchecked")
		List<TfBatch> batch_details = q.list();
		return batch_details;

	}
	
	/**
	 * Gets the cirriculum name from a batch ID
	 */
	@Override
    public String getBatchCirriculumName(int batchID) {
//        SessionFactory sessionFactory = HibernateUtil.getSession();
//        Session session = sessionFactory.openSession();
//
//        String hql = "select curriculum.tfCurriculumName FROM com.revature.model.TfBatch as batch, com.revature.model.TfCurriculum as curriculum WHERE batch.tfCurriculumId = Curruculumn.tfCurriculumId AND batch.tfCurriculumId = :batchID";
//        Query query = session.createQuery(hql);
//        query.setParameter("batchID", batchID);
//        TfBatch batch = (TfBatch)query.list().get(0);
//        
//        session.close();
	    
	    TfBatch batch = getBatch(batchID);
        
        return batch.getTfCurriculum().getTfCurriculumName();
    }
    
	/**
	 * Gets a batch's ID from its name
	 */
    @Override
    public BigDecimal getBatchID(String batchName){
//        SessionFactory sessionFactory = HibernateUtil.getSession();
//        Session session = sessionFactory.openSession();
//
//        String hql = "select batch.id FROM com.revature.model.Batch batch WHERE batch.batchname = :batchName";
//        Query query = session.createQuery(hql);
//        query.setParameter("batchName", batchName);
//        Integer id = (Integer)query.list().get(0);
//        
//        session.close();
        
        TfBatch batch = getBatch(batchName);
        
        return batch.getTfBatchId();
    }

    /**
     * Insert a batch into the database.
     */
    @Override
    public void insertBatch(Batch batch) {
        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.openSession();
        
        Transaction transaction = session.beginTransaction();
        session.save(batch);
        transaction.commit();
        
        session.close();
    }
    
    /**
     * Get a batch from the database given its ID.
     */
    @Override
    public TfBatch getBatch(int batchID) {
        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.openSession();

//        String hql = "FROM com.revature.model.Batch batch where batch.id = :batchID";
//        Query query = session.createQuery(hql);
//        query.setParameter("batchID", batchID);
//        TfBatch batch = (TfBatch)query.list().get(0);
        
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<TfBatch> criteriaQuery = builder.createQuery(TfBatch.class);
//        Root<TfBatch> root = criteriaQuery.from(TfBatch.class);
//        criteriaQuery.select(root).where(builder.equal(root.get("tfBatchId"), batchID));
//        Query<TfBatch> query = session.createQuery(criteriaQuery);
//        TfBatch batch = query.getSingleResult();
        
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TfBatch> criteriaQuery = builder.createQuery(TfBatch.class);
        Root<TfBatch> root = criteriaQuery.from(TfBatch.class);
        criteriaQuery.select(root).where(builder.equal(root.get("tfBatchId"), batchID));
        Query<TfBatch> query = session.createQuery(criteriaQuery);
        TfBatch batch = query.getSingleResult();
        
        session.close();
        
        return batch;
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
