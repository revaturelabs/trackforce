package com.revature.dao;

import java.io.IOException;
import java.util.Map;

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
	Map<Integer, ClientInfo> getAllTfClients() throws HibernateException, IOException;

	/**
	 * Returns a single TfClient object
	 * 
	 * @param name - The name of the client to get
	 * @return
	 */
	TfClient getClient(String name) throws IOException;
	TfClient getClient(int id) throws IOException;
}
