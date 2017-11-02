package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.utils.ConnectionUtil;

public class ClientNameDaoHibernate implements ClientNameDao {

	@Override
	public String getClientName(String batch_name) {
		String client_name = "select Client.clientname from com.revature.dao.Client inner join com.revature.dao.Batch on Client.clientid=Batch.clientid and Batch.batchname=:batch_batch_name";
		Session conn = ConnectionUtil.getSession();
		@SuppressWarnings("rawtypes")
		Query q = conn.createQuery(client_name);
		q.setParameter("batch_batch_name", batch_name);
		String name = (String) q.list().get(0);
		return name;
	}
}
