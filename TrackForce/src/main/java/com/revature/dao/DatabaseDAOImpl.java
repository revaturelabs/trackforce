package com.revature.dao;

import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;

import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class DatabaseDAOImpl {
	public String deleteAll() {
		return runStoredProcedure("admin.truncateAllDevTeam", "Database Emptied Successfully", "Database Empty Error");
	}

	public String populate() {
		return runStoredProcedure("admin.populateAllTables_PROC", "Database Population Successful",
				"Error: Data Exists");
	}

	public String populateSF() {
		return runStoredProcedure("admin.populateAllTablesSF_PROC", "SF - Database Population Successful",
				"Error: Data Exists");
	}

	private String runStoredProcedure(String queryName, String successMessage, String errorMessage) {
		try (Session session = HibernateUtil.getSession().openSession()) {
			String message;
			try {
				StoredProcedureQuery query = session.createStoredProcedureQuery(queryName);
				query.execute();
				message = successMessage;
				return message;
			} catch (Exception e) {
				LogUtil.logger.error(e);
				message = errorMessage;
				return message;
			}
		}
	}
}
