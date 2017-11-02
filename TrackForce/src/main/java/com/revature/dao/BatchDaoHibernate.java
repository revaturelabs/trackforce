package com.revature.dao; 

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.revature.dao.BatchDao;
import com.revature.entity.TfBatch;
import com.revature.utils.HibernateUtil;

public class BatchDaoHibernate implements BatchDao {

	// fromdate is a variable we created, batchstartdate is from the
	@SuppressWarnings("rawtypes")
	public List<TfBatch> getBatchDetails(String fromdate, String todate) {
		String batchdetails = "from com.revature.entity where (tfBatchStartDate between :fromdate and :todate) or (tfBatchEndDate between  :from_date and :to_date)";
		SessionFactory conn = HibernateUtil.getSession();
		Session obj=conn.getCurrentSession();
		Query q = obj.createQuery(batchdetails);
		q.setParameter("fromdate", fromdate);
		q.setParameter("from_date", fromdate);
		q.setParameter("todate", todate);
		q.setParameter("to_date", todate);
		@SuppressWarnings("unchecked")
		List<TfBatch> batch_details = q.list();
		return batch_details;

	}
}
