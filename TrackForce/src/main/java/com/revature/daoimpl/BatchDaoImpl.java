package com.revature.daoimpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.TemporalType;

import org.hibernate.Session;

import com.revature.dao.BatchDao;
import com.revature.entity.TfBatch;
import com.revature.utils.HibernateUtil;

/**
 * Implementation of the BatchDao interface that uses Hibernate to retrieve
 * batch information from the database. lskjdflksdf
 */

public class BatchDaoImpl implements BatchDao {

	@Override
	public TfBatch getBatch(String batchName) {
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfBatch", TfBatch.class).getSingleResult());
	}

	@Override
	public TfBatch getBatchById(Integer id) {
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfBatch b where b.id like :id", TfBatch.class).setParameter("id", id)
				.getSingleResult());
	}

	@Override
	public List<TfBatch> getAllBatches() {
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfBatch", TfBatch.class).getResultList());
	}

	// TODO add to DAO Interface once it works
	public List<TfBatch> getBatchesForPredictions(String name, Timestamp startDate, Timestamp endDate) {
		return HibernateUtil.runHibernate((Session session, Object... args) -> session.createQuery(
				"from TfBatch b WHERE b.curriculumName.name = :name AND b.startDate >= :startdate AND b.endDate <= :enddate",
				TfBatch.class).setParameter("name", name).setParameter("startdate", startDate)
				.setParameter("enddate", endDate).getResultList());
	}

	// TODO 1806-Chris_P: add to DAO Interface
	public Object getBatchCountsForPredictions(String name, Timestamp startDate, Timestamp endDate) {
		Session session = null;
		System.out.println("startDate:" + startDate.toString());
		System.out.println("endDate:" + endDate.toString());

		session = HibernateUtil.getSessionFactory().openSession();
		Object tacobell = session.createNativeQuery(
				"select count(a.tf_associate_id) " + 
				"from admin.tf_associate a where a.tf_batch_id IN " + 
				"(" + 
				"    select b.tf_batch_id " + 
				"    from admin.tf_batch b " + 
				"    where b.tf_curriculum_id IN " + 
				"    (" + 
				"        select c.tf_curriculum_id " + 
				"        from admin.tf_curriculum c " + 
				"        where c.tf_curriculum_name = :curriculumName " +
				"    )" + 
				"    AND b.tf_batch_start_date >= TO_TIMESTAMP(:startDate, 'YYYY-MM-DD HH24:MI:SS.FF')" +
				"    AND b.tf_batch_end_date <= TO_TIMESTAMP(:endDate, 'YYYY-MM-DD HH24:MI:SS.FF')" +
				")"
				).setParameter("curriculumName", name)
				.setParameter("startDate", startDate.toString())
				.setParameter("endDate", endDate.toString())
				.getSingleResult();
		System.out.println("OMGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG tacobell equals : " + tacobell.toString());
		return tacobell;
	}

}