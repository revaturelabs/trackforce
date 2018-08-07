package com.revature.test.services;

import static org.mockito.Matchers.anyInt;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.dao.ClientDao;
import com.revature.entity.TfClient;
import com.revature.services.ClientService;


/**
 * Tests meant to ensure proper functionality of the clientService methods
 * 
 * Reviewed by Daniel Lani
 * 
 * @since 6.06.07.18
 */
public class ClientServiceTest {
	@Mock
	private ClientDao mockClientDao;
	@InjectMocks
	private ClientService service;
	private TfClient client1, client2, client3;
	private ArrayList<TfClient> clients;

	/**
	 * initializes the client service and dao method mocks
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@BeforeClass
	public void beforeTest(){
		service = new ClientService(mockClientDao);
		MockitoAnnotations.initMocks(this);

		// creates three client objects
		client1 = new TfClient();
		client1.setId(1);
		client1.setName("Name1");
		client2 = new TfClient();
		client2.setId(2);
		client2.setName("Name2");
		client3 = new TfClient();
		client3.setId(3);
		client3.setName("Name3");

		Mockito.when(mockClientDao.getClient(anyInt())).thenReturn(client1);
		Mockito.when(mockClientDao.getClient(-1)).thenReturn(null);
		Mockito.when(mockClientDao.getClient("Name1")).thenReturn(client1);
		Mockito.when(mockClientDao.getClient("NotAName")).thenReturn(null);
		
	}

	
	/**
	 * Tests the get clientByName method under
	 * normal conditions
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 1)
	public void testGetclientByName(){
		TfClient expected = service.getClient("Name1");
		Assert.assertEquals(expected, client1);
	}
	
	/**
	 * Tests the get clientByName method when
	 * the name provided is not the name of a client
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 2)
	public void testGetclientByNonExistantName() {
		TfClient expected = service.getClient("NotAName");
		Assert.assertNull(expected);
	}
	
	/**
	 * Tests the get clientByName method when
	 * the name provided is not the name of a client
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
//	@Test(priority = 3)
//	public void testGetclientsByNameNullclientName() {
//		TfClient expected = service.getClient(null);
//		Assert.assertNull(expected);
//	}
	
	/**
	 * Tests the get clientById method under 
	 * normal conditions
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 4)
	public void testGetclientById(){
		TfClient expected = service.getClient(1);
		Assert.assertEquals(expected, client1);
	}
	
	/**
	 * Tests the get clientById method when 
	 * the id provided does not match any known client
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 5)
	public void testGetclientByNonExistantId(){
		TfClient expected = service.getClient(-1);
		Assert.assertNull(expected);
	}
	
	/**
	 * test the getAllclients method when
	 * client list is null
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 6)
	public void testGetAllclientsNullclientList() {
		Mockito.when(mockClientDao.getAllTfClients()).thenReturn(clients);
		List<TfClient> expected = service.getAllTfClients();
		Assert.assertNull(expected);
	}
	
	/**
	 * test the getAllclients method when
	 * client list is empty
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 7)
	public void testGetAllclientsEmptyclientList() {
		clients = new ArrayList<>();
		Mockito.when(mockClientDao.getAllTfClients()).thenReturn(clients);
		List<TfClient> expected = service.getAllTfClients();
		Assert.assertEquals(expected,new ArrayList<>());
	}

	/**
	 * test the getAllclients method under
	 * normal conditions
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 8)
	public void testGetAllclients() {
		clients.add(client1);
		clients.add(client2);
		clients.add(client3);
		Mockito.when(mockClientDao.getAllTfClients()).thenReturn(clients);
		List<TfClient> expected = service.getAllTfClients();
		Assert.assertEquals(expected,clients);
	}
}