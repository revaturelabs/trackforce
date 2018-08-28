package com.revature.dao;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;
import java.util.List;

public interface ClientDao {
	List<TfClient> getAllTfClients();
	List<TfClient> getFirstFiftyTfClients();
	TfClient getClient(String name);
	TfClient getClient(Integer id);
	TfEndClient getEndClient(int id);
	List<TfClient> getAllTfClients(String[] columns);
	List<TfClient> getAllClientsWithMappedAssociates();
}