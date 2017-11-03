package com.revature.dao;

import org.testng.annotations.Test;

import com.revature.entity.TfClient;
import com.revature.model.ClientInfo;
import com.revature.utils.HibernateUtil;

import static org.testng.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.testng.annotations.DataProvider;

public class ClientDaoImplTest {

  @DataProvider
  public Object[][] clientID() {
    return new Object[][] {
      new Object[] { 1 },
      new Object[] { 2 },
    };
  }
  
  @DataProvider
  public Object[][] countsDummy(){
	  return new Object[][] {
	      new Object[] { 1,2,3,4,5,6,7,8,9,10}
	    };
  }

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

		session.close();
		assertFalse(session.isConnected());
  }
  
  @Test
	public void getAllClientInfo() {
	  final int numberOfStatuses = 10;
	  assertEquals(numberOfStatuses, 10);
		EntityManager em = HibernateUtil.getSession().createEntityManager();
		assertNotNull(em);
		
		int[] counts = new int[numberOfStatuses];
		assertEquals(counts.length, 10);
		for (int i = 1; i <= numberOfStatuses; i++) {
			StoredProcedureQuery query = em.createStoredProcedureQuery("ADMIN.GET_ALLCLIENTS_STATUS_COUNT");
			assertNotNull(query);
			query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
			assertFalse(query.getParameters().isEmpty());
			query.setParameter(1, i);
			assertEquals(query.getParameter(1).getParameterType(),Integer.class);
			assertFalse(query.execute());
			counts[i - 1] = (int) query.getOutputParameterValue(2);
			assertNotNull(counts[i - 1]);
		}
		ClientInfo clientInfo = setClientInfoWithIntArrayHelper(counts);
		assertNotNull(clientInfo);
		clientInfo.setName("All Clients");
		assertEquals(clientInfo.getName(), "All Clients");

		em.close();
		assertFalse(em.isOpen());
	}

  @Test(dataProvider = "clientID")
	public void getClientInfo(int id) {
		EntityManager em = HibernateUtil.getSession().createEntityManager();
		assertNotNull(em);

		final int numberOfStatuses = 10;
		assertEquals(numberOfStatuses, 10);
		
		int[] counts = new int[numberOfStatuses];
		assertEquals(counts.length, 10);
		
		for (int i = 1; i <= numberOfStatuses; i++) {
			StoredProcedureQuery query = em.createStoredProcedureQuery("ADMIN.GET_CLIENT_STATUS_COUNT");
			assertNotNull(query);
			query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
			assertFalse(query.getParameters().isEmpty());
			query.setParameter(1, id);
			assertEquals(query.getParameter(1).getParameterType(),Integer.class);
			query.setParameter(2, i);
			assertEquals(query.getParameter(2).getParameterType(),Integer.class);
			assertFalse(query.execute());
			counts[i - 1] = (int) query.getOutputParameterValue(3);
			assertNotNull(counts[i - 1]);
			assertEquals(query.getOutputParameterValue(3).getClass(), Integer.class);
		}
		ClientInfo clientInfo = setClientInfoWithIntArrayHelper(counts);
		assertNotNull(clientInfo);
		clientInfo.setName("Client name");
		assertEquals(clientInfo.getName(), "Client name");

		em.close();
		assertFalse(em.isOpen());
	}
  
  // Helper method
  private ClientInfo setClientInfoWithIntArrayHelper(int[] counts) {
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setTrainingMapped(counts[0]);
		clientInfo.setReservedMapped(counts[1]);
		clientInfo.setSelectedMapped(counts[2]);
		clientInfo.setConfirmedMapped(counts[3]);
		clientInfo.setDeployedMapped(counts[4]);
		clientInfo.setTrainingUnmapped(counts[5]);
		clientInfo.setOpenUnmapped(counts[6]);
		clientInfo.setSelectedUnmapped(counts[7]);
		clientInfo.setConfirmedUnmapped(counts[8]);
		clientInfo.setDeployedUnmapped(counts[9]);
		return clientInfo;
	}

  @Test(dataProvider = "countsDummy")
  public void setClientInfoWithIntArray(int[] counts) {
	  ClientInfo clientInfo = new ClientInfo();
	  assertNotNull(clientInfo);
	clientInfo.setTrainingMapped(counts[0]);
	assertEquals(clientInfo.getTrainingMapped(),1);
	clientInfo.setReservedMapped(counts[1]);
	assertEquals(clientInfo.getReservedMapped(),2);
	clientInfo.setSelectedMapped(counts[2]);
	assertEquals(clientInfo.getSelectedMapped(),3);
	clientInfo.setConfirmedMapped(counts[3]);
	assertEquals(clientInfo.getConfirmedMapped(),4);
	clientInfo.setDeployedMapped(counts[4]);
	assertEquals(clientInfo.getDeployedMapped(),5);
	clientInfo.setTrainingUnmapped(counts[5]);
	assertEquals(clientInfo.getTrainingUnmapped(),6);
	clientInfo.setOpenUnmapped(counts[6]);
	assertEquals(clientInfo.getOpenUnmapped(),7);
	clientInfo.setSelectedUnmapped(counts[7]);
	assertEquals(clientInfo.getSelectedUnmapped(),8);
	clientInfo.setConfirmedUnmapped(counts[8]);
	assertEquals(clientInfo.getConfirmedUnmapped(),9);
	clientInfo.setDeployedUnmapped(counts[9]);
	assertEquals(clientInfo.getDeployedUnmapped(),10);
	}
}
