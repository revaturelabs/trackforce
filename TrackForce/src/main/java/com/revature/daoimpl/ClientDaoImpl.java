package com.revature.daoimpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static com.revature.utils.LogUtil.logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.dao.ClientDao;
import com.revature.entity.TfClient;
import com.revature.model.ClientInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
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
	public List<TfClient> getAllTfClients() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfClient ", TfClient.class).getResultList());
	}

	/**
	 *  Gets the ClientInfo from the client cache map whose key matches the given id
	 *  
	 *  @param int id
	 *  
	 *  @return ClientInfo
	 */
	@Override
	public TfClient getClient(String name) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfClient c where c.tf_client_name like :name", TfClient.class).setParameter("name", name).getSingleResult());
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
	public TfClient getClient(int id) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfClient c where c.tf_client_id like :id", TfClient.class).setParameter("id", id).getSingleResult());
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
