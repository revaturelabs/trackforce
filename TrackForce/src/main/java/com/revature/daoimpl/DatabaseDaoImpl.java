package com.revature.daoimpl;

//UNUSED CLASS??

//import java.io.IOException;
//import javax.persistence.StoredProcedureQuery;
//import static com.revature.utils.LogUtil.logger;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//
//public class DatabaseDAOImpl {
//	
//	public String deleteAll(Session session) throws HibernateException, IOException {
//		return runStoredProcedure("admin.truncateAllDevTeam", "Database Emptied Successfully", "Database Empty Error",
//				session);
//	}
//
//	public String populate(Session session) throws HibernateException, IOException {
//		return runStoredProcedure("admin.populateAllTables_PROC", "Database Population Successful",
//				"Error: Data Exists", session);
//	}
//
//	public String populateSF(Session session) throws HibernateException, IOException {
//		return runStoredProcedure("admin.populateAllTablesSF_PROC", "SF - Database Population Successful",
//				"Error: Data Exists", session);
//	}
//
//	private String runStoredProcedure(String queryName, String successMessage, String errorMessage, Session session)
//			throws HibernateException, IOException {
//		String message;
//		try {
//			StoredProcedureQuery query = session.createStoredProcedureQuery(queryName);
//			query.execute();
//			message = successMessage;
//			return message;
//		} catch (Exception e) {
//			logger.error(e);
//			message = errorMessage;
//			return message;
//		}
//	}
//}
