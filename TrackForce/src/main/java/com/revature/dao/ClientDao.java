package com.revature.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;

import com.revature.entity.TfClient;
import com.revature.model.ClientInfo;

public interface ClientDao {

	/**
	 * Returns a list of TfClient objects, most notably the name and id of the
	 * client.
	 * 
	 * @return A list of TfClient objects
	 */
	public Map<Integer, ClientInfo> getAllTfClients() throws HibernateException, IOException;

	/**
	 * Returns a single TfClient object
	 * 
	 * @param name - The name of the client to get
	 * @return
	 */
	public TfClient getClient(String name) throws IOException;
	public TfClient getClient(int id) throws IOException;
	
	/**
	 * gets a ClientInfo object from the cache whose key matches id
	 * 
	 * @param id
	 * 		-int: the client id of the requested client
	 * @return
	 * 		-ClientInfo object
	 */
	public ClientInfo getClientFromCache(Integer id);
	
	/**
	 * Gets a map of all clients from the cache
	 * 
	 * @return
	 * 		Map<Integer, ClientInfo>
	 */
	public Set<ClientInfo> getAllClientsFromCache();
	
	public Map<Integer, ClientInfo> createClientMap(List<TfClient> clients);
	public void cacheAllClients();
	
}
