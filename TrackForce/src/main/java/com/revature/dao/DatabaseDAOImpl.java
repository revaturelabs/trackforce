package com.revature.dao;

import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;

import com.revature.utils.HibernateUtil;
public class DatabaseDAOImpl {
	public void deleteAll() {
		Session session = HibernateUtil.getSession().openSession();

		StoredProcedureQuery query2 =  session.createStoredProcedureQuery( "admin.truncateAllDevTeam");
		query2.execute();
		System.out.println("Delete All Executed");

		session.close();
		
	}
	public void populate() {
		Session session = HibernateUtil.getSession().openSession();
				
		StoredProcedureQuery query2 =  session.createStoredProcedureQuery( "admin.populateAllTables_PROC");
		query2.execute();
		System.out.println("Dummy Population Executed");

		session.close();
		
	}
}
