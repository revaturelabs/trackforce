package com.revature.dao;
import java.util.List;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;

public interface ClientDao {
	List<TfClient> getAllTfClients();
	
	List<TfClient> getFirstFiftyTfClients();
	
	TfClient getClient(String name);
	
	TfClient getClient(Integer id);
	
	TfEndClient getEndClient(int id);
	
	List<TfClient> getAllTfClients(String[] columns);
	
	List<TfClient> getAllClientsWithMappedAssociates();
}