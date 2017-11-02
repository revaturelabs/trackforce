package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import com.revature.utils.ConnectionUtil;

public class AssociateDaoHibernate {
	@SuppressWarnings("rawtypes")
	public int getNoOfAssociates(int associate_batch_id) {
		// count on the batchid based on the batchid condition

		String count_batches = "select count(Associate.associateid) from com.revature.dao.Associate where Associate.batchid=:associatebatch_id group by Associate.associateid ";
		Session conn = ConnectionUtil.getSession();
		Query query = conn.createQuery(count_batches);
		query.setParameter("associatebatch_id", associate_batch_id);
		int associate_count = (Integer) query.list().get(0);
		//conn.close();
		return associate_count;
		
	}
}
