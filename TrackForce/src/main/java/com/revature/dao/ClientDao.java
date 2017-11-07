package com.revature.dao;

import java.util.List;

import com.revature.entity.TfClient;
import com.revature.model.StatusInfo;

public interface ClientDao {

	/**
	 * Returns a list of TfClient objects, most notably the name and id of the client.
	 * 
	 * @return
	 * a list of TfClient objects
	 */
	List<TfClient> getAllTfClients();

	/**
	 * Returns a StatusInfo object representing the sums of each status for all clients.
	 * 
	 * @return
	 * a StatusInfo object for all clients
	 */
	StatusInfo getAllClientInfo();

	/**
	 * Returns a StatusInfo object representing the sums of each status for a specified client.
	 * 
	 * @param id
	 * the id of a TfClient record
	 * @return
	 * a StatusInfo object for a specified client
	 */
	StatusInfo getClientInfo(int id);

}
