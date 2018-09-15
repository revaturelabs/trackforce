package com.revature.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import org.hibernate.stat.Statistics;

import static com.revature.utils.LogUtil.logger;

/**
 * This implementation is used to reset the database with proper variables so
 * testing and modifications can be made.
 */
public class DbResetUtil {

	// Open sessions have this long to finish once a database reset is initiated
	public static final long SESSION_RESET_TIMEOUT_MILLS = 10000;
	public static final String SQL_RESET_SCRIPT = System.getenv("TRACKFORCE_RESOURCE_DIR") + "/reInitDB.sql";
	//TRACKFORCE_RESOURCE_DIR
	
	/**
	 * Remove all transactional data and reinitialize the database to a known
	 * working state This method will do the following in order: 
	 * 1. Prevent future access to the SessionFactory; any call to getSessionFactory() will receive a HibernateException 
	 * 2. Determine if any Session objects are still open - any open Sessions will be given SESSION_RESET_TIMEOUT_MILLS to finish 
	 * 3. Close the SessionFactory invalidating any open session and evicting all cache 
	 * 4. Run the database reinitialize script 
	 * 5. Unlock getSessionFactory() 
	 * 6. Get an instance of SessionFactory to ensure it starts properly
	 * 
	 * @throws IOException  if the reinitialize script can no be opened
	 * @throws SQLException if the script does not execute successfuly
	 * @throws ClassNotFoundException if the JDBC class can not be instantiated
	 */
	public static void resetDatabase() throws IOException, SQLException, ClassNotFoundException {
		// If the database is already locked, this line will throw. Let it float
		Statistics stats = HibernateUtil.getSessionFactory().getStatistics();
		HibernateUtil.setDatabaseLocked(true);
		long openSessions = stats.getSessionOpenCount() - stats.getSessionCloseCount();

		// If openSessions > 0 then there are sessions currently open, give them some time to finish
		if (openSessions > 0) {
			logger.info("resetDatabase() called with open session count == " + openSessions + ", " + SESSION_RESET_TIMEOUT_MILLS + " mills timeout started");
			try {
				Thread.sleep(SESSION_RESET_TIMEOUT_MILLS);
			} catch (InterruptedException ex) {
				// Do nothing
			}
		}

		Scanner scanner = null;
		Connection connection = null;
		try {
			HibernateUtil.shutdown();

			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(
					System.getenv("TRACKFORCE_DB_URL"), 
					System.getenv("TRACKFORCE_DB_USERNAME"), 
					System.getenv("HBM_PW_ENV"));

			//We will only commit when the script completes
			connection.setAutoCommit(false);
			
			// This will parse the SQL script
			scanner = new Scanner(new File(SQL_RESET_SCRIPT));

			// Loop through the SQL file statements
			Statement currentStatement = null;
			while (scanner.hasNext()) {

				// Get statement
				String rawStatement = scanner.nextLine();
				try {
					// Execute statement
					//System.out.println("SQL Statement: " + rawStatement);
					currentStatement = connection.createStatement();
					currentStatement.execute(rawStatement);
				} catch (SQLException e) {
					// That statement did not work...
					e.printStackTrace();
				} finally {
					// Release resources
					if (currentStatement != null) {
						try {
							currentStatement.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					currentStatement = null;
				}
			}
			
			connection.commit();
			HibernateUtil.setDatabaseLocked(false);
			HibernateUtil.getSessionFactory();
		} catch (Exception ex) {
			//Only catching to rollback if nessicary
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException sqlException) {
					//Nothing can be done about this
				}
			}
			
			throw ex;
		} finally {
			// No matter what happens, make sure the database is unlocked before leaving
			HibernateUtil.setDatabaseLocked(false);
	
			if (scanner != null)
				scanner.close();

			try {
				connection.close();
			} catch (Exception e) {
				// do nothing
			}
		}
	}
}