package com.revature.services;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.revature.dao.ClientDao;
import com.revature.dao.ClientDaoImpl;
import com.revature.entity.TfClient;
import com.revature.model.StatusInfo;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class ClientResourceTest {

	@Mock
	private ClientDaoImpl clientDaoImpl = new ClientDaoImpl();

	@InjectMocks
	private ClientResource clientResource = new ClientResource();

	@BeforeMethod(alwaysRun = true)
	public void injectMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllClientsCheckMapIsOfProperForm() throws IOException {
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		try {
			List<TfClient> clients = new ArrayList<>();
			clients.add(new TfClient());
			when(clientDaoImpl.getAllTfClients(session)).thenReturn(clients);

			List<Map<String, Object>> entity = (List<Map<String, Object>>) clientResource.getAllClients().getEntity();
			assertTrue(entity.get(0).containsKey("id"));
			assertTrue(entity.get(0).containsKey("name"));

		} finally {
			session.flush();
			tx.rollback();
			session.close();
		}
	}
}
