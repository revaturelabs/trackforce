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
		ClientInfo aClient = new ClientInfo("A Single Client", 5, 17, 34, 12, 6, 44, 23, 25, 16, 12);
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
