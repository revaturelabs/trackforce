package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.entity.TfBatch;
import com.revature.utils.LogUtil;

/**
 * Implementation of the BatchDao interface that uses Hibernate to retrieve
 * batch information from the database.
 */
public class BatchDaoHibernate implements BatchDao {

    /**
     * Get a batch from the database given its name.
     *
     * @param batchName - The name of the batch to get information about
     * @throws IOException
     */
    @Override
    public TfBatch getBatch(String batchName, Session session) throws IOException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TfBatch> criteriaQuery = builder.createQuery(TfBatch.class);
        Root<TfBatch> root = criteriaQuery.from(TfBatch.class);
        criteriaQuery.select(root).where(builder.equal(root.get("tfBatchName"), batchName));
        Query<TfBatch> query = session.createQuery(criteriaQuery);

        TfBatch batch;
        try {
            batch = query.getSingleResult();
        } catch (NoResultException nre) {
            LogUtil.logger.error(nre);
            batch = new TfBatch();
        }
        return batch;
    }


    @Override
    public TfBatch getBatchById(BigDecimal id, Session session) throws IOException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TfBatch> criteriaQuery = builder.createQuery(TfBatch.class);
        Root<TfBatch> root = criteriaQuery.from(TfBatch.class);
        criteriaQuery.select(root).where(builder.equal(root.get("tfBatchId"), id));
        Query<TfBatch> query = session.createQuery(criteriaQuery);

        TfBatch batch;
        try {
            batch = query.getSingleResult();
        } catch (NoResultException nre) {
            LogUtil.logger.error(nre);
            batch = null;
        }
        return batch;
    }

    /**
     * Get a list of batches that are running within the given dates
     *
     * @param fromdate - the beginning number of the date range
     * @param todate   - the ending date of the date range
     * @throws IOException
     */
    @Override
    public List<TfBatch> getBatchDetails(Timestamp fromdate, Timestamp todate, Session session) throws IOException {
        TypedQuery<TfBatch> query = session.createQuery(
                "from TfBatch where (tfBatchStartDate >= :fromdate) and (tfBatchEndDate <= :todate)", TfBatch.class);
        query.setParameter("fromdate", fromdate);
        query.setParameter("todate", todate);
        List<TfBatch> batch = query.getResultList();
        return batch;
    }
}