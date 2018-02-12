package com.revature.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.entity.TfClient;
import com.revature.model.ClientInfo;
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
	public TfClient getClient(String name) throws IOException {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TfClient> criteriaQuery = builder.createQuery(TfClient.class);
			Root<TfClient> root = criteriaQuery.from(TfClient.class);
			criteriaQuery.select(root).where(builder.equal(root.get("tfClientName"), name));
			Query<TfClient> query = session.createQuery(criteriaQuery);
			return query.getSingleResult();
		} catch (NoResultException nre) {
			LogUtil.logger.error(nre);
		}
		return new TfClient();
	}
	
	public TfClient getClient(int id) throws IOException{
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TfClient> criteriaQuery = builder.createQuery(TfClient.class);
			Root<TfClient> root = criteriaQuery.from(TfClient.class);
			criteriaQuery.select(root).where(builder.equal(root.get("tfClientId"), id));
			Query<TfClient> query = session.createQuery(criteriaQuery);
			return query.getSingleResult();
		} catch (NoResultException nre) {
			LogUtil.logger.error(nre);
		}
		return new TfClient();
		
	}

	@Override
	public Map<Integer, ClientInfo> getAllTfClients() throws HibernateException, IOException {
		Map<Integer, ClientInfo> map = new HashMap<>();
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaQuery<TfClient> cq = session.getCriteriaBuilder().createQuery(TfClient.class);
			cq.from(TfClient.class);
			List<TfClient> clients = session.createQuery(cq).getResultList();
		

			for (TfClient client : clients) {
				map.put(client.getTfClientId(), Dao2DoMapper.map(client));
			}	
		}
		return map;
	}
}
