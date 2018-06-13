package com.revature.services;

import java.util.List;

import com.revature.dao.ClientDao;
import com.revature.dao.ClientDaoImpl;
import com.revature.entity.TfClient;

public class ClientService {
   
	private static ClientDao dao = new ClientDaoImpl();
	private ClientService() {};
	
	public static List<TfClient> getAllTfClients(){
		return dao.getAllTfClients();
	}
	
	public static TfClient getClient(String name) {
		return dao.getClient(name);
	}
	
	public static TfClient getClient(int id) {
		return dao.getClient(id);
	}
}
