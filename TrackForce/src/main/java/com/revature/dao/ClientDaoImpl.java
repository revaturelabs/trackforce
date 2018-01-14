package com.revature.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.entity.TfClient;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class ClientDaoImpl implements ClientDao {

	private static List<TfClient> clients;

	/**
	 * Get information about a singular client.
	 * 
	 * @param name
	 *            - The name of the client to retrieve.
	 * @return - A TfClient object with information about the client.
	 * @throws IOException 
	 */
	@Override
	public TfClient getClient(String name) throws IOException {
		TfClient client;
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		
		try {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TfClient> criteriaQuery = builder.createQuery(TfClient.class);

			Root<TfClient> root = criteriaQuery.from(TfClient.class);

			criteriaQuery.select(root).where(builder.equal(root.get("tfClientName"), name));

			Query<TfClient> query = session.createQuery(criteriaQuery);

			try {
				client = query.getSingleResult();
			} catch (NoResultException nre) {
				client = new TfClient();
				LogUtil.logger.error(nre);
			}
		} finally {
			session.flush();
			tx.commit();
			session.close();
		}
		return client;
	}

	@Override
	public List<TfClient> getAllTfClients(Session session) throws HibernateException, IOException {
		if (clients == null || clients.isEmpty()) {
				CriteriaQuery<TfClient> cq = session.getCriteriaBuilder().createQuery(TfClient.class);
				cq.from(TfClient.class);
				clients = session.createQuery(cq).getResultList();
				for (TfClient client : clients) {
					Hibernate.initialize(client.getTfClientId());
					Hibernate.initialize(client.getTfClientName());
					Hibernate.initialize(client.getTfAssociates());
				}
			}
		return clients;
	}

	/**
	 * Clears clients list in ClientDaoImpl class.
	 */
	public void clearClients() {
		if (clients != null) {
			clients.clear();
		}
	}
}
