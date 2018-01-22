package com.revature.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.revature.dao.ClientDaoImpl;
import com.revature.model.ClientInfo;
import com.revature.utils.HibernateUtil;

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
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			List<ClientInfo> clients = new ArrayList<>();
			clients.add(new ClientInfo());

		} finally {
			session.flush();
			tx.rollback();
			session.close();
		}
	}
}
