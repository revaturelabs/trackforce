package com.revature.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.entity.TfClient;
import com.revature.model.ClientInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;

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
	
	/**
	 * Get information about a singular client.
	 * 
	 * @param id
	 *            - The id of the client to retrieve.
	 * @return - A TfClient object with information about the client.
	 * @throws IOException
	 */
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

	/**
	 *  Gets the ClientInfo from the client cache map whose key matches the given id
	 *  
	 *  @param int id
	 *  
	 *  @return ClientInfo
	 */
	@Override
	public ClientInfo getClientFromCache(Integer id) {
		return PersistentStorage.getStorage().getClientAsMap().get(id);
	}
	
	/**
	 * Gets a map of all clients from the cache
	 * 
	 * @return Map<Integer, ClientInfo>
	 */
	public Set<ClientInfo> getAllClientsFromCache(){
		return PersistentStorage.getStorage().getClients();
	}
	
	/**
	 * Get information about all clients.
	 * 
	 * @return - Map<Integer, ClientInfo>
	 * 				-Integer = Client Id of the Client stored in the ClientInfo Object
	 * @throws IOException
	 */
	@Override
	public Map<Integer, ClientInfo> getAllTfClients() {
		Map<Integer, ClientInfo> map = new HashMap<>();
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaQuery<TfClient> cq = session.getCriteriaBuilder().createQuery(TfClient.class);
			cq.from(TfClient.class);
			List<TfClient> clients = session.createQuery(cq).getResultList();
		

			for (TfClient client : clients) {
				map.put(client.getTfClientId(), Dao2DoMapper.map(client));
			}	
		} catch(Exception e) {
			LogUtil.logger.error(e);
		}
		return map;
	}
	
	public Map<Integer, ClientInfo> createClientMap(List<TfClient> clients){
		Map<Integer, ClientInfo> map = new HashMap<>();
		for(TfClient client: clients) {
			map.put(client.getTfClientId(), Dao2DoMapper.map(client));
		}
		return map;
	}
	
	public void cacheAllClients() {
		PersistentStorage.getStorage().setClients(getAllTfClients());
	} 
}
