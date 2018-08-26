package com.revature.services;
import com.revature.dao.ClientDao;
import com.revature.daoimpl.ClientDaoImpl;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;
import java.util.List;

/** @author Adam L.
 * @version v6.18.06.13 */
public class ClientService {
   
	private ClientDao dao;

	public ClientService() { dao = new ClientDaoImpl(); }// public so it can be used for testing

    public ClientService(ClientDao dao) { this.dao = dao; }

    /** @author Adam L.
	 * <p>Gets all the clients in the database</p>
	 * @version v6.18.06.13 */
	public List<TfClient> getAllTfClients() { return dao.getAllTfClients(); }
	
	public List<TfClient> getFirstFiftyClients() { return dao.getFirstFiftyTfClients(); }
	
	public List<TfClient> getMappedClients() { return dao.getAllClientsWithMappedAssociates(); }
	
	public List<TfClient> getAllTfClients(String... columns) { return dao.getAllTfClients(columns); }

	/** @author Adam L.
	 * <p>Gets a client based on their name</p>
	 * @version v6.18.06.13 */
	public TfClient getClient(String name) { return dao.getClient(name); }
	
	/** @author Adam L.
	 * <p>Gets a client based on their client id</p>
	 * @version v6.18.06.13 */
	public TfClient getClient(Integer id) { return dao.getClient(id); }

	public TfEndClient getEndClient(int id) {return dao.getEndClient(id);}
}