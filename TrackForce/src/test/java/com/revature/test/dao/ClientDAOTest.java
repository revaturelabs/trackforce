package com.revature.test.dao;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.revature.daoimpl.ClientDaoImpl;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;
import com.revature.test.utils.Log;

/** Test class for testing ClientDAOImpl
 * 
 * Danger of false negatives in the case of database changes.
 * 
 * Depends on Properties file referring to existent entries in database.
 * Also directly refers to existent entries in the database. Be warned that any
 * change in the database may very well cause tests to fail despite the DAO 
 * working just fine.
 */
public class ClientDAOTest {
	
	private ClientDaoImpl dao;
	//PLEASTE NOTE: The file referenced by this variable upon initialization can be out of date.
	//Check that this is not out of date with the database being accessed before troubleshooting
	//failing tests. Due to lambdas inside lambdas (HibernateUtil's Callable, Dao's Sessionals)
	private Properties props;
	
	@BeforeSuite
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
		
		//this test will need to be perfected to verify the contents of the list
		String[] columns = {"c.id","c.name"};
		list = dao.getAllTfClients(columns);
		assertNotNull (list);
	}
	
	@Test
	public void testClientDAOGetFirstFifty() {
		List<TfClient> list = dao.getFirstFiftyTfClients();
		assertEquals(list.size(), 50);
	}
	
	@Test
	public void testClientDAOGetAllWithMappedAssociates() {
		List<TfClient> list = dao.getAllClientsWithMappedAssociates();
		assertNotNull(list);
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void testClientDAOGetEndClient() {
		TfEndClient client = dao.getEndClient(Integer.parseInt(props.getProperty("end_client_id")));
		assertEquals(client.getName(), props.getProperty("end_client_name"));
		
	}
}
