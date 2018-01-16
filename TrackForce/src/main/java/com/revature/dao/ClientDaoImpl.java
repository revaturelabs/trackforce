package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.revature.model.ClientInfo;
import com.revature.model.CurriculumInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class ClientDaoImpl implements ClientDao {

	/**
	 * Get information about a singular client.
	 * 
	 * @param name
	 *            - The name of the client to retrieve.
	 * @return - A TfClient object with information about the client.
	 * @throws IOException
	 */
	@Override
	public TfClient getClient(Session session, String name) throws IOException {
		TfClient client;

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
		return client;
	}

	@Override
	public Map<BigDecimal, ClientInfo> getAllTfClients(Session session) throws HibernateException, IOException {

		CriteriaQuery<TfClient> cq = session.getCriteriaBuilder().createQuery(TfClient.class);
		cq.from(TfClient.class);
		List<TfClient> clients = session.createQuery(cq).getResultList();
		Map<BigDecimal, ClientInfo> map = new HashMap<>();
		
		for (TfClient client : clients) {
			map.put(client.getTfClientId(), Dao2DoMapper.map(client));
		}

		return map;
	}
}
