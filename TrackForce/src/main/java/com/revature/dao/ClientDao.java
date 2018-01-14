package com.revature.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.entity.TfClient;

public interface ClientDao {

	/**
	 * Returns a list of TfClient objects, most notably the name and id of the
	 * client.
	 * 
	 * @return A list of TfClient objects
	 */
	List<TfClient> getAllTfClients(Session session) throws HibernateException, IOException;

	/**
	 * Returns a single TfClient object
	 * 
	 * @param name
	 *            - The name of the client to get
	 * @return
	 */
	TfClient getClient(Session session, String name) throws IOException;
}
