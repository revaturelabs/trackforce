package com.revature.services;

import java.util.List;

import com.revature.dao.ClientDao;
import com.revature.daoimpl.ClientDaoImpl;
import com.revature.entity.TfClient;

/**
 * @author Adam L. 
 * <p> </p>
 * @version.date v06.2018.06.13
 *
 */
public class ClientService {
   
	private ClientDao dao;
	
	// public so it can be used for testing 
	public ClientService() {dao = new ClientDaoImpl();};
	public ClientService(ClientDao dao) {this.dao = dao;};
	
	/**
	 * @author Adam L. 
	 * <p>Gets all the clients in the database</p>
	 * @version.date v06.2018.06.13
	 * 
	 * @return List<TfClient>
	 */
	public List<TfClient> getAllTfClients(){
		return dao.getAllTfClients();
	}
	
	/**
	 * @author Adam L. 
	 * <p>Gets a client based on their name</p>
	 * @version.date v06.2018.06.13
	 * 
	 * @param name
	 * @return TfClient
	 */
	public TfClient getClient(String name) {
		return dao.getClient(name);
	}
	
	/**
	 * @author Adam L. 
	 * <p>Gets a client based on their client id</p>
	 * @version.date v06.2018.06.13
	 * 
	 * @param id
	 * @return TfClient
	 */
	public TfClient getClient(int id) {
		return dao.getClient(id);
	}
}
