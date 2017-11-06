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
		EntityManager em = HibernateUtil.getSession().createEntityManager();
		StoredProcedureQuery query = em.createStoredProcedureQuery("ADMIN.GET_ALLCLIENTS_STATUS_COUNT");
		registerOutputParameters(query);
		query.execute();
		ClientInfo clientInfo = getClientInfo(query);
		clientInfo.setName("All Clients");
		em.close();
		return clientInfo;
	}

	@Override
	public ClientInfo getClientInfo(int id) {

		/*
		 * Returns an empty set of info for this client if the given ID is invalid (0 or
		 * less)
		 */
		if (id < 1) {
			return new ClientInfo();
		}

		EntityManager em = HibernateUtil.getSession().createEntityManager();
		StoredProcedureQuery query = em.createStoredProcedureQuery("ADMIN.GET_CLIENT_STATUS_COUNT");
		registerOutputParameters(query);
		query.registerStoredProcedureParameter(11, Integer.class, ParameterMode.IN);
		query.setParameter(11, id);
		query.execute();
		ClientInfo clientInfo = getClientInfo(query);
		TfClient client = em.find(TfClient.class, id);
		clientInfo.setName(client.getTfClientName());

		em.close();
		return clientInfo;
	}
	
	/**
	 * Register out parameters for the client status stored procedures.
	 * 
	 * @param query to register duplicate stored procedure parameters
	 */
	private void registerOutputParameters(StoredProcedureQuery query) {
		query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(3, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(4, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(6, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(7, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(8, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(9, Integer.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter(10, Integer.class, ParameterMode.OUT);
	}
	
	/**
	 * Get a 
	 * 
	 * @param query to get out parameter values from
	 * @return a ClientInfo object with status counts set 
	 */
	private ClientInfo getClientInfo(StoredProcedureQuery query) {
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setTrainingMapped((int) query.getOutputParameterValue(1));
		clientInfo.setReservedMapped((int) query.getOutputParameterValue(2));
		clientInfo.setSelectedMapped((int) query.getOutputParameterValue(3));
		clientInfo.setConfirmedMapped((int) query.getOutputParameterValue(4));
		clientInfo.setDeployedMapped((int) query.getOutputParameterValue(5));
		clientInfo.setTrainingUnmapped((int) query.getOutputParameterValue(6));
		clientInfo.setOpenUnmapped((int)query.getOutputParameterValue(7));
		clientInfo.setSelectedUnmapped((int) query.getOutputParameterValue(8));
		clientInfo.setConfirmedUnmapped((int) query.getOutputParameterValue(9));
		clientInfo.setDeployedUnmapped((int) query.getOutputParameterValue(10));
		return clientInfo;
	}
}
