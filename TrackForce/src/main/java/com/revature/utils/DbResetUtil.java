package com.revature.utils;

import java.io.IOException;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.stat.Statistics;

/** This implementation is used to reset the database with 
 * proper variables so testing and modifications can be made.
 * @author Josh Pressley ,Chris Siu - 1807 */
public class DbResetUtil {
	
	//Open sessions have this long to finish once a database reset is initiated
	public static final long SESSION_RESET_TIMEOUT_MILLS = 30000;
	
	/**
	 * Remove all transactional data and reinitialize the database to a known working state
	 * This method will do the following in order:
	 * 1. Prevent future access to the SessionFactory; any call to getSessionFactory() will receive a HibernateException
	 * 2. Determine if any Session objects are still open - any open Sessions will be given SESSION_RESET_TIMEOUT_MILLS to finish
	 * 3. Close the SessionFactory invalidating any open session and evicting all cache
	 * 4. Run the database reinitialize script
	 * 5. Unlock getSessionFactory()
	 * 6. Get an instance of SessionFactory to ensure it starts properly
	 * @throws IOException if the reinitialize script can no be opened
	 * @throws SQLException if the script does not execute successfuly 
	 */
	public static void resetDatabase() throws IOException, SQLException {
		Statistics stats = HibernateUtil.getSessionFactory().getStatistics();
		HibernateUtil.setDatabaseLock(true);
		long openSessions = stats.getSessionOpenCount() - stats.getSessionCloseCount();

		//If openSessions > 0 then there are sessions currently open, give them some time to finish
		if (openSessions > 0) {
			System.out.println("resetDatabase() called with open session count == " + openSessions + ", " + SESSION_RESET_TIMEOUT_MILLS + " mills timeout started");
			try {
				Thread.sleep(SESSION_RESET_TIMEOUT_MILLS);
			} catch (InterruptedException ex) {
				//Do nothing
			}
		}
		
		try {
			HibernateUtil.shutdown();
			
			//Execute script
			
			HibernateUtil.setDatabaseLock(false);
			HibernateUtil.getSessionFactory();
		} catch (HibernateException he) {
			throw he; //FIXME what should I do with this?
		} finally {
			//No matter what happens, make sure the database is unlocked before leaving
			HibernateUtil.setDatabaseLock(false);
		}
	}
	
	
	/* The method implementations below both have issues with the script file.
	 * Method 1:
	 * 		Hibernate has to read and execute every line individually. Latest error hints that the script needs to use
	 * 		entity names instead of table names.
	 * Method 2:
	 * 		Flyway cannot read the script file even though it is finding a file in the proper directory. 
	 * 		Assumptions on this error are that the file needs some type of prefix on the file name or a special formatting to be read.
	 * 
	 * Time ran out for us to finish the implementation of this feature but you should only need to get the script to read. The 
	 * rest of the process should be in place. 
	 * 
	 * Best of luck -1807 */
	
	//Dummy method for functionalities sake.
//	public static void resetDatabase() throws IOException, SQLException { 
//		
//	}
	
	/** Calls the sql script that resets the DB
	* Hibernate reformatting version */
//	public static boolean resetDatabase() throws IOException {
//		BufferedReader reader = null;
//		String localCopy;		
//		Session session = null;
//		try {
//			File f = new File("/Users/Josh/Documents/GitHub/trackforce/TrackForce/src/main/resources/reInitDB.sql");
//			reader = new BufferedReader(new FileReader(f));
//			session = HibernateUtil.getSessionFactory().openSession();
//			session.beginTransaction();
//			
//			while((localCopy = reader.readLine()) != null) { 
//				if (localCopy.isEmpty() || localCopy.contains("--")) { continue; }
//				localCopy = localCopy.substring(0, localCopy.length()-1); //removes the semicolon
//				session.createQuery(localCopy).executeUpdate();
//			}
//			session.getTransaction().commit();
//			return true;
//		} catch (FileNotFoundException e) {
//			System.out.println("FileNotFoundException occurred locating DB-Reset script");
//		} catch (IOException e) {
//			System.out.println("IOException occurred reading in DB-Reset script");
//		} finally {
//			if (session != null) { session.close(); }
//			if (reader != null) { reader.close(); }
//		}
//		return false;
//	}
	
	/** Calls the sql script that resets the DB
	 * flyway version */
//	public static boolean resetDatabase() {
//		Flyway flyway = new Flyway();
//		flyway.setDataSource(System.getenv("TRACKFORCE_DB_URL"),System.getenv("TRACKFORCE_DB_USERNAME"),System.getenv("HBM_PW_ENV"));
//		flyway.setLocations("filesystem:src/main/resources/");
//		flyway.migrate();
//		return true;
//	}
	
	

}