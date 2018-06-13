package com.revature.dao;

import java.util.List;

import org.hibernate.Session;

import com.revature.entity.TfClient;
import com.revature.utils.HibernateUtil;

public class ClientDaoImpl implements ClientDao {

	
	@Override
	public List<TfClient> getAllTfClients() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from Tf_Client ", TfClient.class).setCacheable(true).getResultList());
	}

	@Override
	public TfClient getClient(String name) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from Tf_Client c where c.tf_client_name like :name", TfClient.class).setParameter("name", name).getSingleResult());
	}

	@Override
	public TfClient getClient(int id) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from Tf_Client c where c.tf_client_id like :id", TfClient.class).setParameter("id", id).getSingleResult());
	}
	
	
}
