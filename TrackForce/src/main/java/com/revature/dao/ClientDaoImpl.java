package com.revature.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.revature.entity.TfClient;
import com.revature.model.ClientInfo;
import com.revature.utils.HibernateUtil;

public class ClientDaoImpl implements ClientDao {

	@Override
	public List<TfClient> getAllTfClients() {
		Session session = HibernateUtil.getSession().openSession();
		CriteriaQuery<TfClient> cq = session.getCriteriaBuilder().createQuery(TfClient.class);
		cq.from(TfClient.class);
		List<TfClient> clients = session.createQuery(cq).getResultList();
		session.close();
		return clients;
	}

	@Override
	public ClientInfo getAllClientInfo() {
		final int numberOfStatuses = 10;
		
		int[] counts = new int[numberOfStatuses];
		for (int i = 1; i <= numberOfStatuses; i++) {
			EntityManager em = HibernateUtil.getSession().createEntityManager();
			StoredProcedureQuery query = em.createStoredProcedureQuery("ADMIN.GET_ALLCLIENTS_STATUS_COUNT");
			query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
			query.setParameter(1, i);
			query.execute();
			counts[i - 1] = (int) query.getOutputParameterValue(2);
		}
		ClientInfo clientInfo = setClientInfoWithIntArray(counts);
		clientInfo.setName("all");
		return clientInfo;
	}

	@Override
	public ClientInfo getClientInfo(int id) {
		
		EntityManager em = HibernateUtil.getSession().createEntityManager();
		
        StoredProcedureQuery query1 = em.createStoredProcedureQuery("ADMIN.GET_CLIENT_STATUS_COUNT");
        query1.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query1.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query1.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
        query1.setParameter(1, id);
        query1.setParameter(2, 1);
        query1.execute();
        
        StoredProcedureQuery query2 = em.createStoredProcedureQuery("ADMIN.GET_CLIENT_STATUS_COUNT");
        query2.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query2.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query2.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
        query2.setParameter(1, id);
        query2.setParameter(2, 2);
        query2.execute();
        
        StoredProcedureQuery query3 = em.createStoredProcedureQuery("ADMIN.GET_CLIENT_STATUS_COUNT");
        query3.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query3.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query3.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
        query3.setParameter(1, id);
        query3.setParameter(2, 3);
        query3.execute();
        
        StoredProcedureQuery query4 = em.createStoredProcedureQuery("ADMIN.GET_CLIENT_STATUS_COUNT");
        query4.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query4.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query4.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
        query4.setParameter(1, id);
        query4.setParameter(2, 4);
        query4.execute();
        
        StoredProcedureQuery query5 = em.createStoredProcedureQuery("ADMIN.GET_CLIENT_STATUS_COUNT");
        query5.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query5.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query5.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
        query5.setParameter(1, id);
        query5.setParameter(2, 5);
        query5.execute();
        
        StoredProcedureQuery query6 = em.createStoredProcedureQuery("ADMIN.GET_CLIENT_STATUS_COUNT");
        query6.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query6.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query6.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
        query6.setParameter(1, id);
        query6.setParameter(2, 6);
        query6.execute();
        
        StoredProcedureQuery query7 = em.createStoredProcedureQuery("ADMIN.GET_CLIENT_STATUS_COUNT");
        query7.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query7.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query7.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
        query7.setParameter(1, id);
        query7.setParameter(2, 7);
        query7.execute();
        
        StoredProcedureQuery query8 = em.createStoredProcedureQuery("ADMIN.GET_CLIENT_STATUS_COUNT");
        query8.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query8.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query8.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
        query8.setParameter(1, id);
        query8.setParameter(2, 8);
        query8.execute();
        
        StoredProcedureQuery query9 = em.createStoredProcedureQuery("ADMIN.GET_CLIENT_STATUS_COUNT");
        query9.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query9.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query9.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
        query9.setParameter(1, id);
        query9.setParameter(2, 9);
        query9.execute();
        
        StoredProcedureQuery query10 = em.createStoredProcedureQuery("ADMIN.GET_CLIENT_STATUS_COUNT");
        query10.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query10.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
        query10.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
        query10.setParameter(1, id);
        query10.setParameter(2, 10);
        query10.execute();
        
        
        //ClientInfo aClient = new ClientInfo("A Single Client", 5, 17, 34, 12, 6, 44, 23, 25, 16, 12);
        ClientInfo aClient = new ClientInfo();
      		
      	aClient.setName("A Single Client");
        aClient.setTrainingMapped((int) query1.getOutputParameterValue(3));
        aClient.setTrainingUnmapped((int) query2.getOutputParameterValue(3));
        aClient.setReservedMapped((int) query3.getOutputParameterValue(3));
        aClient.setReservedUnmapped((int) query4.getOutputParameterValue(3));
        aClient.setSelectedMapped((int) query5.getOutputParameterValue(3));
        aClient.setSelectedUnmapped((int) query6.getOutputParameterValue(3));
        aClient.setConfirmedMapped((int) query7.getOutputParameterValue(3));
        aClient.setConfirmedUnmapped((int) query8.getOutputParameterValue(3));
        aClient.setDeployedMapped((int) query9.getOutputParameterValue(3));
        aClient.setDeployedUnmapped((int) query10.getOutputParameterValue(3));
		
		return aClient;
	}

	private ClientInfo setClientInfoWithIntArray(int[] counts) {
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

}
