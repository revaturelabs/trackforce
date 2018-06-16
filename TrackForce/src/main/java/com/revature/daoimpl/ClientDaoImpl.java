package com.revature.daoimpl;

import java.util.List;

import org.hibernate.Session;

import com.revature.dao.ClientDao;
import com.revature.entity.TfClient;
import com.revature.utils.HibernateUtil;

public class ClientDaoImpl implements ClientDao {


	@Override
	public List<TfClient> getAllTfClients() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
				session.createQuery("from TfClient ", TfClient.class).getResultList());
	}

	@Override
	public TfClient getClient(String name) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
				session.createQuery("from TfClient c where c.name like :name", TfClient.class).setParameter("name", name).getSingleResult());
	}

	@Override
	public TfClient getClient(int id) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
				session.createQuery("from TfClient c where c.id like :id", TfClient.class).setParameter("id", id).getSingleResult());
	}


}