package com.revature.test.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;
import com.revature.services.ClientService;
import com.revature.test.utils.Log;

public class ClientServicesTest {
	
	private ClientService service;
	private Properties prop;
	List<TfClient> clients;
	
	@BeforeClass
	public void initialize() {
		service = new ClientService();
		prop = new Properties();
		clients = service.getAllTfClients();
		try {
			FileInputStream propFile = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\database_entries.properties");
			prop.load(propFile);
			propFile.close();
		} catch(FileNotFoundException e) {
			Log.Log.error(e.getMessage());
		} catch (IOException e) {
			Log.Log.error(e.getMessage());
		}
	}
	
  @Test
  public void testClientGetClientByName() {
	  String name = prop.getProperty("tf_client_name");
	  TfClient existingClient = service.getClient(name);
	  assertNotNull(existingClient);
	  assertEquals(prop.getProperty("tf_client_id"), existingClient.getId());
	  assertEquals(name, existingClient.getName());
  }
  
  @Test
  public void testGetClientById() {
	  Integer id = Integer.parseInt(prop.getProperty("tf_client_id"));
	  TfClient client = service.getClient(id);
	  assertNotNull(client);
	  assertEquals(client.getId(), id);
	  assertEquals(client.getName(), prop.getProperty("tf_client_name"));
  }
  
  @Test
  public void testClientGetEndClient() {
	  Integer id = Integer.parseInt(prop.getProperty("tf_end_client_id"));
	  String name = prop.getProperty("tf_end_client_name");
	  TfEndClient client = service.getEndClient(id);
	  assertNotNull(client);
	  assertEquals(client.getId(), id);
	  assertEquals(client.getName(), name);
  }
  
  @Test
  public void testClientGetAllClients() {
	  assertNotNull(clients);
	  assertTrue(!clients.isEmpty());
  }

  @Test
  public void testClientGetFifty() {
	  List<TfClient> first50 = service.getFirstFiftyClients();
	  boolean result;
	  if(first50.size() < 50) 
		  result = first50.size() == clients.size();
	  else
		  result = first50 != null && first50.equals(clients.subList(0, 51));
	  assertTrue(result);
  }

  @Test
  public void testClientGetMapped() {
	  List<TfClient> mappedClients = service.getMappedClients();
	  boolean result = true;
	  assertNotNull(mappedClients);
	  assertTrue(!mappedClients.isEmpty());
	  for(TfClient tfc : mappedClients) {
		  for(TfAssociate tfa : tfc.getAssociate()) {
			  if(tfa.getClient().getId() != tfc.getId())
				  result = false;
		  }
	  }
	  assertTrue(result);
  }
}
