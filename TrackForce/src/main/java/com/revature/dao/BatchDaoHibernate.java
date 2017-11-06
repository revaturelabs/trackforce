package com.revature.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.entity.TfBatch;
import com.revature.utils.HibernateUtil;

public class BatchDaoHibernate implements BatchDao {

    /**
     * Get a batch from the database given its name.
     * 
     * @param batchName
     *            - The name of the batch to get information about
     */
    @Override
    public TfBatch getBatch(String batchName) {
        SessionFactory sessionFactory = HibernateUtil.getSession();
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TfBatch> criteriaQuery = builder.createQuery(TfBatch.class);
        Root<TfBatch> root = criteriaQuery.from(TfBatch.class);
        criteriaQuery.select(root).where(builder.equal(root.get("tfBatchName"), batchName));
        Query<TfBatch> query = session.createQuery(criteriaQuery);
        
        TfBatch batch;
        try {
            batch = query.getSingleResult();
        } catch (NoResultException nre) {
            batch = new TfBatch();
        }
        return batch;

    }

    /**
     * Get a list of batches that are running within the given dates
     * 
     * @param fromdate
     *            - the beginning number of the date range
     * @param todate
     *            - the ending date of the date range
     */
    @Override
    public List<TfBatch> getBatchDetails(Timestamp fromdate, Timestamp todate) {
        EntityManager em = HibernateUtil.getSession().createEntityManager();
        TypedQuery<TfBatch> query = em.createQuery(
                "from TfBatch where (tfBatchStartDate >= :fromdate) and (tfBatchEndDate <= :todate)", TfBatch.class);
        query.setParameter("fromdate", fromdate);
        query.setParameter("todate", todate);
        List<TfBatch> batch = query.getResultList();
        return batch;
    }

}
