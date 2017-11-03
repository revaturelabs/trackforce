package com.revature.dao;

import java.util.List;

import com.revature.entity.TfClient;
import com.revature.model.ClientInfo;

public interface ClientDao {

	/**
	 * Returns a list of TfClient objects, most notably the name and id of the client.
	 * 
	 * @return
	 * a list of TfClient objects
	 */
	List<TfClient> getAllTfClients();

	/**
	 * Returns a ClientInfo object representing the sums of each status for all clients.
	 * 
	 * @return
	 * a ClientInfo object for all clients
	 */
	ClientInfo getAllClientInfo();

	/**
	 * Returns a ClientInfo object representing the sums of each status for a specified client.
	 * 
	 * @param id
	 * the id of a TfClient record
	 * @return
	 * a ClientInfo object for a specified client
	 */
	ClientInfo getClientInfo(int id);

}
