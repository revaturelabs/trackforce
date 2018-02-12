package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.entity.TfBatch;
import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;

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
	public TfBatch getBatch(String batchName) {
		TfBatch batch = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TfBatch> criteriaQuery = builder.createQuery(TfBatch.class);
			Root<TfBatch> root = criteriaQuery.from(TfBatch.class);
			criteriaQuery.select(root).where(builder.equal(root.get("tfBatchName"), batchName));
			Query<TfBatch> query = session.createQuery(criteriaQuery);
			batch = query.getSingleResult();
			return batch;
		} catch (NoResultException nre) {
			LogUtil.logger.error(nre);
		}
		return batch;
	}
	
	public TfBatch getBatchById(int id) {
		TfBatch batch = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()) {
			batch = s.get(TfBatch.class, id);
			return batch;
		} catch (NoResultException e) {
			LogUtil.logger.error(e);
		}
		return batch;
	}

    /**
     * Get a list of batches that are running within the given dates
     *
     * @param session
     * @return
     * @throws IOException
     */
	@Override
	public Map<BigDecimal, BatchInfo> getBatchDetails() {
		List<TfBatch> batchesEnt = null;
		Map<BigDecimal, BatchInfo> map = new HashMap<>();
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			TypedQuery<TfBatch> tq = session.createQuery("from TfBatch", TfBatch.class);			
			batchesEnt = tq.getResultList();
			if(batchesEnt != null) {
				for(TfBatch tb : batchesEnt) {
					map.put(tb.getTfBatchId(), Dao2DoMapper.map(tb));
				}
			}
			return map;
		} catch(NoResultException e) {
			LogUtil.logger.error(e);
		}
		return map;
	}
	
	public static Map<BigDecimal, BatchInfo> getBatchDetails() {
		List<TfBatch> batchesEnt;
		Session session = HibernateUtil.getSession();
		Map<BigDecimal, BatchInfo> map = new HashMap<>();
		TypedQuery<TfBatch> tq = session.createQuery(
				"from TfBatch", TfBatch.class);
		
		batchesEnt = tq.getResultList();
		if(batchesEnt != null)
		for(TfBatch tb : batchesEnt) {
			map.put(tb.getTfBatchId(), Dao2DoMapper.map(tb));
		}
		
		return map;
	}
	
	/**
     * Retrieves all associate records from the database and places them into the cache
     * 
     */
    public static void cacheAllBatches() {
    	
    	PersistentStorage.getStorage().setBatches(getBatchDetails());//Look into getBatchDetails()
    	PersistentStorage.getStorage().setTotals(AssociateInfo.getTotals());
    }
}