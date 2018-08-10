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
 * batch information from the database.
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

	/**
	 * 1806_Chris_P: This method retrieves all of the batches that match the technology 
	 * 	and fall between the dates selected in the Predictions page.
	 *  This is where the initial data for the batch details comes from.
	 * 		Note: This could potentially have uses elsewhere, at which point, please rename this method 
	 * 		to reflect its current use(s)
	 */
	public List<TfBatch> getBatchesForPredictions(String name, Timestamp startDate, Timestamp endDate) {
		return HibernateUtil.runHibernate((Session session, Object... args) -> session.createQuery(
				"from TfBatch b WHERE b.curriculumName.name = :name AND b.startDate >= :startdate AND b.endDate <= :enddate",
				TfBatch.class).setParameter("name", name).setParameter("startdate", startDate)
				.setParameter("enddate", endDate).getResultList());
	}

	/**
	 * 1806_Chris_P: This method is very similar to the above method, except that it grabs the total amount of
	 * 	associates in the batches that match the technology and fall between the dates selected on the Predictions page
	 *  and is used for the Associate Breakdown table.  
	 */
	public Object getBatchCountsForPredictions(String name, Timestamp startDate, Timestamp endDate) {
		//1806_Chris_P: Setup the session
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		
		//1806_Chris_P: So, this monster here was a nightmare to get to work
		// but it works like a charm. :) Due to the TfBatch returning a set of TfAssociates
		// getting the actual count of all of those associates was a bit tricky. A for-loop could have been used,
		// but handling it through a single query seemed like a better idea at the time.
		// Credit also goes to Andrew H, Cyril M, Austin D and Austin M for their assistance in making this thing finally work
		Object associateCount = session.createNativeQuery( // Using a Native Query because this was a bit much to handle with hibernate
				"select count(a.tf_associate_id) " +       // without having some really complex joins and whatnot. If you want to take on the challenge, by all means go for it!
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
				.setParameter("startDate", startDate.toString()) // This needs to be a string. It acts weird otherwise
				.setParameter("endDate", endDate.toString()) // This needs to be a string. It acts weird otherwise
				.getSingleResult();
		
		return associateCount;
	}

}