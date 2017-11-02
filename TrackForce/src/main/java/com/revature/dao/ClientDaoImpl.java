package com.revature.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.revature.entity.TfClient;
import com.revature.model.ClientInfo;

public class ClientDaoImpl implements ClientDao {

	@Override
	public List<TfClient> getAllTfClients() {
		List<TfClient> clients = new ArrayList<>();
		clients.add(new TfClient(new BigDecimal(1), "Client 1"));
		clients.add(new TfClient(new BigDecimal(2), "Client 2"));
		clients.add(new TfClient(new BigDecimal(3), "Client 3"));
		return clients;
	}

	@Override
	public ClientInfo getAllClientInfo() {
		ClientInfo clients = new ClientInfo("All Clients", 100, 95, 75, 107, 145, 23, 65, 72, 15, 34);
		return clients;
	}

	@Override
	public ClientInfo getClientInfo(int id) {
		ClientInfo aClient = new ClientInfo("A Single Client", 5, 17, 34, 12, 6, 44, 23, 25, 16, 12);
		return aClient;
	}

}
