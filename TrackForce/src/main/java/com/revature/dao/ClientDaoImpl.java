package com.revature.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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

	@Override
	public TfClient getClient(String name) {
		SessionFactory sessionFactory = HibernateUtil.getSession();
		Session session = sessionFactory.openSession();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TfClient> criteriaQuery = builder.createQuery(TfClient.class);

		Root<TfClient> root = criteriaQuery.from(TfClient.class);

		criteriaQuery.select(root).where(builder.equal(root.get("tfClientName"), name));

		Query<TfClient> query = session.createQuery(criteriaQuery);

		TfClient client;

		try {
			client = query.getSingleResult();
		} catch (NoResultException nre) {
			client = new TfClient();
		} finally {
			session.close();
		}

		return client;
	}
	
	public void clearClients() {
		clients.clear();
	}
}
