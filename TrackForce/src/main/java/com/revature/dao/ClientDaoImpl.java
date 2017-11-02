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
		
		
		for (int i = 1; i <= 8; i++) {
			EntityManager em = HibernateUtil.getSession().createEntityManager();
			StoredProcedureQuery query = em.createStoredProcedureQuery("ADMIN.GET_ALLCLIENTS_STATUS_COUNT");
			query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
			query.setParameter(1, i);
			query.execute();
			System.out.println(query.getOutputParameterValue(2));
		}

		ClientInfo clients = new ClientInfo("All Clients", 100, 95, 75, 107, 145, 23, 65, 72, 15, 34);
		return clients;
	}

	@Override
	public ClientInfo getClientInfo(int id) {
		ClientInfo aClient = new ClientInfo("A Single Client", 5, 17, 34, 12, 6, 44, 23, 25, 16, 12);
		return aClient;
	}

}
