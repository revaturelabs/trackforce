package com.revature.test.dao;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.daoimpl.ClientDaoImpl;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;
import com.revature.test.utils.Log;

public class ClientDAOTest {
	
	private ClientDaoImpl dao;
	private Properties props;
	
	@BeforeClass
	public void initialize() {
		dao = new ClientDaoImpl();
		props = new Properties();
		try {
			FileInputStream propFile = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\database_entries.properties");
			props.load(propFile);
			propFile.close();
		} catch(FileNotFoundException e) {
			Log.Log.error(e.getMessage());
		} catch (IOException e) {
			Log.Log.error(e.getMessage());
		}
	}
	
	@Test
	public void testClientDAOGetClient() {
		TfClient client = dao.getClient(Integer.parseInt(props.getProperty("client_id")));
		assertEquals(client.getName(), props.getProperty("client_name"));
		client = dao.getClient(props.getProperty("client_name"));
		assertEquals((int)client.getId(), Integer.parseInt(props.getProperty("client_id")));
	}
	
	@Test
	public void testClientDAOGetAll() {
		List<TfClient> list = dao.getAllTfClients();
		assertEquals(list.size(), Integer.parseInt(props.getProperty("client_total")));
		
		//If someone understands what getAllTfClients(String[] columns) does,
		//please test it because I'm not sure what to pass it to not get null
		String[] columns = {"tf_associate"};
		list = dao.getAllTfClients(columns);
		assertEquals(list, null);
	}
	
	@Test
	public void testClientDAOGetFirstFifty() {
		List<TfClient> list = dao.getFirstFiftyTfClients();
		assertEquals(list.size(), 50);
	}
	
	@Test
	public void testClientDAOGetAllWithMappedAssociates() {
		List<TfClient> list = dao.getAllClientsWithMappedAssociates();
		TfClient infosys = list.get(Integer.parseInt(props.getProperty("client_infosys_index")));
		assertEquals(infosys.getName(), "Infosys");
		assertEquals(list.size(), Integer.parseInt(props.getProperty("client_withAssociates")));
	}
	
	@Test
	public void testClientDAOGetEndClient() {
		TfEndClient client = dao.getEndClient(Integer.parseInt(props.getProperty("end_client_id")));
		assertEquals(client.getName(), props.getProperty("end_client_name"));
		
	}
	
}
