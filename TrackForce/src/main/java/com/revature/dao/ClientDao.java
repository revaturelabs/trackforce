package com.revature.dao;

import java.util.List;

import com.revature.entity.TfClient;
import com.revature.model.StatusInfo;

public interface ClientDao {

	/**
	 * Returns a list of TfClient objects, most notably the name and id of the client.
	 * 
	 * @return
	 * A list of TfClient objects
	 */
	List<TfClient> getAllTfClients();
	
	/**
	 * Returns a single TfClient object
	 * @param name - The name of the client to get
	 * @return
	 */
    TfClient getClient(String name);
}
