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

  @Test
  public void getAllTfClients() {
	  Session session = HibernateUtil.getSession().openSession();
		assertNotNull(session);
		CriteriaQuery<TfClient> cq = session.getCriteriaBuilder().createQuery(TfClient.class);
		assertNotNull(cq);
		cq.from(TfClient.class);
		assertNotNull(cq);
		List<TfClient> clients = session.createQuery(cq).getResultList();
		assertFalse(clients.isEmpty());
		for(TfClient client : clients) {
			Hibernate.initialize(client.getTfClientId());
			assertNotNull(client.getTfClientId());
			Hibernate.initialize(client.getTfClientName());
			assertNotNull(client.getTfClientName());
		}

		session.close();
		assertFalse(session.isConnected());
  }
  
  
}
