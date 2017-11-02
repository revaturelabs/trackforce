package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.revature.utils.HibernateUtil;

public class ClientNameDaoHibernate implements ClientNameDao {

	@Override
	public String getClientName(String batch_name) {
		String client_name = "select TfClient.tfClientName from com.revature.entity.TfClient inner join com.revature.entity.TfBatch on TfClient.tfClientId=TfBatch.tfClient and TfBatch.tfBatchName=:batch_batch_name";
		SessionFactory conn = HibernateUtil.getSession();
		Session obj = conn.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query q = obj.createQuery(client_name);
		q.setParameter("batch_batch_name", batch_name);
		String name = (String) q.list().get(0);
		return name;
	}
}
