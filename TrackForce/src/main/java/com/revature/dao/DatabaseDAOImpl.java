package com.revature.dao;

import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;

import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class DatabaseDAOImpl {
	public String deleteAll() {
		String message;
		try (Session session = HibernateUtil.getSession().openSession()) {

			try {
				StoredProcedureQuery query2 = session.createStoredProcedureQuery("admin.truncateAllDevTeam");
				query2.execute();
				message = "Database Emptied Successfully";
				return message;
			} catch (Exception e) {
				LogUtil.logger.error(e);
				message = "Database Empty Error";
				return message;
			}
		}
	}

	public String populate() {
		try (Session session = HibernateUtil.getSession().openSession()) {
			String message;
			try {
				StoredProcedureQuery query2 = session.createStoredProcedureQuery("admin.populateAllTables_PROC");
				query2.execute();
				message = "Database Population Successful";
				return message;
			} catch (Exception e) {
				message = "Error: Data Exists";
				return message;
			}
		}
	}

	public String populateSF() {
		try (Session session = HibernateUtil.getSession().openSession()) {
			String message;
			try {
				StoredProcedureQuery query2 = session.createStoredProcedureQuery("admin.populateAllTablesSF_PROC");
				query2.execute();
				message = "SF - Database Population Successful";
				return message;
			} catch (Exception e) {
				message = "Error: Data Exists";
				return message;
			}
		}
	}
}
