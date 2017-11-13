package com.revature.dao;

import org.testng.annotations.Test;

import com.revature.entity.TfClient;
import com.revature.model.StatusInfo;
import com.revature.utils.HibernateUtil;

import static org.testng.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.testng.annotations.DataProvider;

public class ClientDaoImplTest {

	ClientDaoImpl cDao = new ClientDaoImpl();
	
	@DataProvider(name="Clients")
	public String[] clients() {
		String[] clientList = new String[]{"22nd Century Staffing Inc", "Accenture / Fannie Mae", "PepsiCo", "Toyota Financial Services"};
		return clientList;
	}
	
	@Test
	public void getAllTfClients() {
		List<TfClient> result = cDao.getAllTfClients();
		assertNotNull(result);
	}
	
	@Test(dataProvider="Clients")
	public void getTfClient(String client) {
		TfClient result = cDao.getClient(client);
		assertNotNull(result);
	}

}
