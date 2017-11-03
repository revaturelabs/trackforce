package com.revature.dao;

import java.util.List;

import com.revature.entity.TfClient;
import com.revature.model.ClientInfo;

public interface ClientDao {

	List<TfClient> getAllTfClients();

	ClientInfo getAllClientInfo();

	ClientInfo getClientInfo(int id);

}
