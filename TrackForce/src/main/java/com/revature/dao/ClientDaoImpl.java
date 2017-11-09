package com.revature.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.revature.entity.TfClient;
import com.revature.utils.HibernateUtil;

public class ClientDaoImpl implements ClientDao {

	private static List<TfClient> clients;

	@Override
	public List<TfClient> getAllTfClients() {
		if (clients == null || clients.isEmpty()) {
			Session session = HibernateUtil.getSession().openSession();
			CriteriaQuery<TfClient> cq = session.getCriteriaBuilder().createQuery(TfClient.class);
			cq.from(TfClient.class);
			clients = session.createQuery(cq).getResultList();
			for (TfClient client : clients) {
				Hibernate.initialize(client.getTfClientId());
				Hibernate.initialize(client.getTfClientName());
			}
			session.close();
		}
		return clients;
	}

}
