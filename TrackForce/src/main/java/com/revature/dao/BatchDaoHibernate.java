package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.model.Batch;
import com.revature.utils.ConnectionUtil;

public class BatchDaoHibernate implements BatchDao {

	// fromdate is a variable we created, batchstartdate is from the
	@SuppressWarnings("rawtypes")
	public List<Batch> getBatchDetails(String fromdate, String todate) {
		String batchdetails = "from com.revature.batch where (batchstartdate between :fromdate and :todate) or (batchenddate between  :from_date and :to_date)";
		Session conn = ConnectionUtil.getSession();
		Query q = conn.createQuery(batchdetails);
		q.setParameter("fromdate", fromdate);
		q.setParameter("from_date", fromdate);
		q.setParameter("todate", todate);
		q.setParameter("to_date", todate);
		@SuppressWarnings("unchecked")
		List<Batch> batch_details = q.list();
		return batch_details;

	}
}
