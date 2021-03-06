package com.revature.daoimpl;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.revature.dao.BatchDao;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

import static com.revature.utils.LogUtil.logger;

/** Implementation of the BatchDao interface that uses Hibernate to retrieve
 * batch information from the database.*/
public class BatchDaoImpl implements BatchDao {

	/**
	 * getBatch
	 * 
	 * ToDo:
	 * This method returns the first result of a list while expecting batchName to not be a unique identifier.
	 * If repeated names are in the result set, this will not be known in either testing or user. Implement an
	 * additional method that returns the entire list of batches by batch name.
	 * 
	 * IE: Only gets the first result if searching for Batchnames with duplicates in the database. Need an
	 * iterator capability to return all matched results for Batchnames.
	 */
	@Override
	public TfBatch getBatch(String batchName) {
		logger.info("Getting batch via the batchName: "+batchName);
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfBatch b WHERE b.batchName = :batchName ", TfBatch.class)
				.setParameter("batchName", batchName)
				.setCacheable(true).getResultList()).get(0);
	}

	@Override
	public TfBatch getBatchById(Integer id) {
		logger.trace("Getting batch via the batchId: " + id);
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfBatch b where b.id = :id", TfBatch.class).setParameter("id", id)
				.setCacheable(true).getSingleResult());
	}

	
	/* (non-Javadoc)
	 * @see com.revature.dao.BatchDao#getBatchBySalesforceId(java.lang.String)
	 * 1901 Thomas: Added this method to fetch batches by their salesforce ID
	 */
	@Override
	public TfBatch getBatchBySalesforceId(String sfId) {
		logger.trace("Getting batch via the salesforce ID: " + sfId);
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfBatch b where b.salesforceId = :sfid", TfBatch.class).setParameter("sfid", sfId)
				.setCacheable(true).getSingleResult());
	}

	@Override
	public List<TfBatch> getAllBatches() {
		logger.trace("Getting all batches.");
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfBatch", TfBatch.class)
				.setCacheable(true).getResultList());
	}

	/* @author 1806_Andrew_H
	 * Very similar to the below method, except it doesn't filter by the curriculum name */
	public List<TfBatch> getBatchesWithinDates(Timestamp startDate, Timestamp endDate) {
		logger.trace("Getting batches within Dates start: " + startDate + " - end:" +endDate);
		List<TfBatch> toReturn = HibernateUtil.runHibernate((Session session, Object... args) -> session.createQuery(
				"from TfBatch b WHERE b.startDate >= :startdate AND b.endDate <= :enddate",
				TfBatch.class).setParameter("startdate", startDate).setCacheable(true)
				.setParameter("enddate", endDate).getResultList());
		
		return toReturn;
	}
	
	/** 1806_Chris_P: This method retrieves all of the batches that match the technology 
	 * 	and fall between the dates selected in the Predictions page.
	 *  This is where the initial data for the batch details comes from.
	 * 	Note: This could potentially have uses elsewhere, at which point, please rename this method 
	 * 	to reflect its current use(s) */
	public List<TfBatch> getBatchesForPredictions(String name, Timestamp startDate, Timestamp endDate) {
		logger.trace("Batches for Predictions. Curriculumn: " + name + " within Dates start: " + startDate + " - end:" +endDate);
		return HibernateUtil.runHibernate((Session session, Object... args) -> session.createQuery(
				"from TfBatch b WHERE b.curriculumName.name = :name AND b.startDate >= :startdate AND b.endDate <= :enddate ORDER BY b.endDate",
				TfBatch.class).setParameter("name", name).setParameter("startdate", startDate).setCacheable(true)
				.setParameter("enddate", endDate).getResultList());
	}

	/** 1806_Chris_P: This method is very similar to the above method, except that it grabs the total amount of
	 * 	associates in the batches that match the technology and fall between the dates selected on the Predictions page
	 *  and is used for the Associate Breakdown table. */
	public Object getBatchCountsForPredictions(String name, Timestamp startDate, Timestamp endDate) {
		Session session = null;
		Object results = null;
		//1806_Chris_P: So, this monster here was a nightmare to get to work
		// but it works like a charm. :) Due to the TfBatch returning a set of TfAssociates
		// getting the actual count of all of those associates was a bit tricky. A for-loop could have been used,
		// but handling it through a single query seemed like a better idea at the time.
		// Credit also goes to Andrew H, Cyril M, Austin D and Austin M for their assistance in making this thing finally work
		logger.info("Getting number of Associates for Curriculumn: " + name + "within Dates. Start: " + startDate + " - End: " + endDate);
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			results = session.createNativeQuery(
					"select count(a.tf_associate_id) " + 
					"from admin.tf_associate a where a.tf_marketing_status_id > 5 AND a.tf_batch_id IN " + 
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
		}catch(HibernateException e) {
			logger.error("Hibernate Exception occured" + e.getMessage());
			
		}catch(NullPointerException e) {
			logger.error("Null Pointer Error occured" + e.getMessage());
			
		}finally {
			if (session != null) {
				session.close();
			}
		}
		//logger.info("Result : " + results);
		return results;
	}

	/*
	 * 1901 Daniel 
	 * This method adds batches to the database after being passed a batch object
	 * from the Caliber application.
	 */
	@Override
	public boolean createBatch(TfBatch newbatch) {
		LogUtil.logger.trace("Hibernate Call to Create Batch: " + newbatch.getSalesforceId());
		return HibernateUtil.saveToDB(newbatch);
	}
}
