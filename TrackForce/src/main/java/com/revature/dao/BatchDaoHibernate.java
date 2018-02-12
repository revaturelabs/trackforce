package com.revature.dao;

import java.io.IOException;
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
import com.revature.model.BatchInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.LogUtil;

/**
 * Implementation of the BatchDao interface that uses Hibernate to retrieve
 * batch information from the database.
 */
public class BatchDaoHibernate implements BatchDao {

	/**
	 * Get a batch from the database given its name.
	 * 
	 * @param batchName
	 *            - The name of the batch to get information about
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
				throw new IOException("could not get batch", nre);
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
	public Map<Integer, BatchInfo> getBatchDetails(Session session) throws IOException {
		List<TfBatch> batchesEnt;

		Map<Integer, BatchInfo> map = new HashMap<>();
		TypedQuery<TfBatch> tq = session.createQuery(
				"from TfBatch", TfBatch.class);
		
		batchesEnt = tq.getResultList();
		if(batchesEnt != null)
		for(TfBatch tb : batchesEnt) {
			map.put(tb.getTfBatchId(), Dao2DoMapper.map(tb));
		}
		
		return map;
	}
}