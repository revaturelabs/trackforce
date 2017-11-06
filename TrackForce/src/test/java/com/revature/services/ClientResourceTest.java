package com.revature.services;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.revature.dao.ClientDao;
import com.revature.dao.ClientDaoImpl;
import com.revature.entity.TfClient;

public class ClientResourceTest {

	@Mock
	private ClientDao clientDaoImpl = new ClientDaoImpl();

	@InjectMocks
	private ClientResource clientResource = new ClientResource();

	@BeforeMethod(alwaysRun = true)
	public void injectMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void f() {
		List<TfClient> clients = new ArrayList<>();
		
		when(clientDaoImpl.getAllTfClients()).thenReturn(clients);
	}
}
