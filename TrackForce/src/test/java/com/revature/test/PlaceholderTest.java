package com.revature.test;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import com.revature.model.ClientInfo;
import com.revature.utils.HibernateUtil;

public class PlaceholderTest {

	public static void main(String[] args) {
//		EntityManager em = HibernateUtil.getSession().createEntityManager();
//		StoredProcedureQuery query = em.createStoredProcedureQuery("ADMIN.GET_ALLCLIENTS_STATUS_COUNT");
//		query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
//		query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
//		query.setParameter(1, 4);
//		query.execute();
//		System.out.println(query.getOutputParameterValue(2));
		
		ClientInfo allClientsInfo = new ClientInfo();
		int[] counts = new int[8];
		for (int i = 1; i <= 8; i++) {
			EntityManager em = HibernateUtil.getSession().createEntityManager();
			StoredProcedureQuery query = em.createStoredProcedureQuery("ADMIN.GET_ALLCLIENTS_STATUS_COUNT");
			query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);
			query.setParameter(1, i);
			query.execute();
			counts[i-1] = (int) query.getOutputParameterValue(2);
//			System.out.println(query.getOutputParameterValue(2));
		}

		for(int i = 0; i < counts.length; i++) {
			System.out.println(counts[i]);
		}
	}

}
