package com.revature.daoimpl;

import java.util.List;

import org.hibernate.Session;

import com.revature.dao.BatchDao;
import com.revature.entity.TfBatch;
import com.revature.utils.HibernateUtil;

/**
 * Implementation of the BatchDao interface that uses Hibernate to retrieve
 * batch information from the database.
 */
public class BatchDaoImpl implements BatchDao {
	
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


	@Override
	public List<TfBatch> getAllBatches() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfBatch", TfBatch.class).getResultList());
	}
	
	
	

}