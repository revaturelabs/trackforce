package com.revature.daoimpl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.revature.dao.BatchDao;
import com.revature.entity.TfBatch;
import com.revature.utils.HibernateUtil;

/**
 * Implementation of the BatchDao interface that uses Hibernate to retrieve
 * batch information from the database.
 * lskjdflksdf
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
		session.createQuery("from TfBatch b where b.id like :id", TfBatch.class).setParameter("id", id).getSingleResult());
	}


	@Override
	public List<TfBatch> getAllBatches() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfBatch", TfBatch.class).getResultList());
	}

	//TODO add to DAO Interface once it works
	public List<TfBatch> getBatchesForPredictions(String name, Timestamp startDate, Timestamp endDate) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfBatch b WHERE b.curriculumName.name = :name AND b.startDate >= :startdate AND b.endDate <= :enddate", TfBatch.class).
		setParameter("name", name).setParameter("startdate", startDate).setParameter("enddate", endDate).getResultList());
	}
	
	// TODO 1806-Chris_P add
	public Object getBatchCountsForPredictions(String name, Timestamp startDate, Timestamp endDate) {
		Object sumAssociates = null;
		Query sumAssociate = HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("SELECT SUM(associates.size()) from TfBatch b WHERE b.curriculumName.name = :name AND b.startDate >= :startdate AND b.endDate <= :enddate", TfBatch.class).
		setParameter("name", name).setParameter("startdate", startDate).setParameter("enddate", endDate));
		return sumAssociates = sumAssociate.getSingleResult();
	}
	

}