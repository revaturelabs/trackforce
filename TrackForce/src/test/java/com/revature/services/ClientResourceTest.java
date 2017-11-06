package com.revature.services;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.revature.dao.ClientDao;
import com.revature.dao.ClientDaoImpl;
import com.revature.entity.TfClient;
import com.revature.model.ClientInfo;

public class ClientResourceTest {

	@Mock
	private ClientDao clientDaoImpl = new ClientDaoImpl();

	@InjectMocks
	private ClientResource clientResource = new ClientResource();

	@BeforeMethod(alwaysRun = true)
	public void injectMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllClientsCheckMapIsOfProperForm() {
		List<TfClient> clients = new ArrayList<>();
		clients.add(new TfClient());
		when(clientDaoImpl.getAllTfClients()).thenReturn(clients);

		List<Map<String, Object>> entity = (List<Map<String, Object>>) clientResource.getAllClients().getEntity();
		assertTrue(entity.get(0).containsKey("id"));
		assertTrue(entity.get(0).containsKey("name"));
	}

	@Test
	public void getClientInfoNegativeInt() {
		assertEquals(clientResource.getClientInfo(-1), new ClientInfo(""));
	}
}
