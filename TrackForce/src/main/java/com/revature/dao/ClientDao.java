package com.revature.dao;

import java.util.List;

import com.revature.entity.TfClient;

public interface ClientDao {

	
	public List<TfClient> getAllTfClients();
	public TfClient getClient(String name);
	public TfClient getClient(int id);


}
