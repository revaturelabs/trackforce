package com.revature.test.services;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
	  Integer i = existingClient.getId();
	  Integer iii = Integer.parseInt(prop.getProperty("tf_client_id"));
	  
	  assertNotNull(existingClient);
	  assertEquals(name, existingClient.getName());
	  assertEquals(i,iii);
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
	  Integer id = Integer.parseInt(prop.getProperty("end_client_id"));
	  String name = prop.getProperty("end_client_name");
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
	  
	  assertNotNull(first50);
	  assertFalse(first50.isEmpty());
  }

  @Test
  public void testClientGetMapped() {
	  List<TfClient> mappedClients = service.getMappedClients();
	 
	  assertNotNull(mappedClients);
	  assertTrue(!mappedClients.isEmpty());
  }
}