package com.revature.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.entity.TfBatch;
import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
import com.revature.utils.PersistentStorage;

/**
 * Implementation of the BatchDao interface that uses Hibernate to retrieve
 * batch information from the database.
 */
public class BatchDaoHibernate implements BatchDao {
	static final Logger logger = Logger.getLogger(BatchDaoHibernate.class);

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
			logger.error(nre);
		}
		return batch;
	}
	
	@Override
	public BatchInfo getBatchById(Integer id) {
		BatchInfo batch = PersistentStorage.getStorage().getBatch(id);
		if(batch != null)
			return batch;
		else {
			TfBatch tfBatch = null;
			try(Session s = HibernateUtil.getSession()) {
				tfBatch = s.get(TfBatch.class, id);
				return Dao2DoMapper.map(tfBatch);
			} catch (NoResultException e) {
				logger.error(e);
			}
		}
		return null;
		
	}

	// TODO add caching
	public Map<Integer, BatchInfo> getBatchesByCurri(){
		Session session = HibernateUtil.getSession();
		try {
			List<TfBatch> batches;
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<TfBatch> cq = cb.createQuery(TfBatch.class);
			Root<TfBatch> from = cq.from(TfBatch.class);
			CriteriaQuery<TfBatch> all = cq.select(from);
			Query<TfBatch> tq = session.createQuery(all);
			batches = tq.getResultList();
			Map<Integer, BatchInfo> map = new HashMap<>();
			if(batches != null) {
				map = createBatchesMap(batches);
			}
			return map;
		} catch(NoResultException e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return new HashMap<>();
	}
	
	public Map<Integer, BatchInfo> getBatchDetails(){
		Session session = HibernateUtil.getSession();
		try {
			
			List<TfBatch> batches;
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<TfBatch> cq = cb.createQuery(TfBatch.class);
			Root<TfBatch> from = cq.from(TfBatch.class);
			CriteriaQuery<TfBatch> all = cq.select(from);
			Query<TfBatch> tq = session.createQuery(all);
			batches = tq.getResultList();
			Map<Integer, BatchInfo> map = new HashMap<>();
			if(batches != null) {
				map = createBatchesMap(batches);
			}
			return map;
		} catch(NoResultException e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return new HashMap<>();
		
	}
	
	@Override
	public Set<AssociateInfo> getBatchAssociates(Integer id){
		if(PersistentStorage.getStorage().getBatchAsMap().get(id).getAssociates() == null)
			cacheAllBatches();
		return PersistentStorage.getStorage().getBatchAsMap().get(id).getAssociates();
	}
	@Override
	public List<BatchInfo> getBatchesSortedByDate(){
		if(PersistentStorage.getStorage().getBatchesByDate() == null)
			cacheAllBatches();
		return PersistentStorage.getStorage().getBatchesByDate();
	}
	public Set<BatchInfo> getAllBatches() {
		if(PersistentStorage.getStorage().getBatches() == null)
			cacheAllBatches();
		return PersistentStorage.getStorage().getBatches();
	}
	
	
	
	/**
     * Retrieves all associate records from the database and places them into the cache
     * 
     */
    public void cacheAllBatches() {
    	PersistentStorage.getStorage().setBatches(getBatchDetails());
    }
    
    public Map<Integer, BatchInfo> createBatchesMap(List<TfBatch> batchList) {
    	HashMap<Integer, BatchInfo> map = new HashMap<>();
    	for(TfBatch tfb : batchList) {
    		map.put(tfb.getTfBatchId(), Dao2DoMapper.map(tfb));
    	}
    	return map;
    }
}