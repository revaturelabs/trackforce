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

import static com.revature.utils.LogUtil.logger;
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
	

	/**
	 * Get a batch from the database given its name.
	 * 
	 * @param batchName - The name of the batch to get information about
	 * @throws IOException
	 */
	@Override
	public TfBatch getBatch(String batchName) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfBatch", TfBatch.class).getSingleResult());
	}
	
	@Override
	public TfBatch getBatchById(Integer id) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfBatch b where b.tf_batch_id like :id", TfBatch.class).setParameter("id", id).getSingleResult());
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
	public List<TfBatch> getAllBatches() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfBatch", TfBatch.class).setCacheable(true).getResultList());
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