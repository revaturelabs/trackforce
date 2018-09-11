package com.revature.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.flywaydb.core.Flyway;
import org.hibernate.Session;

/** This implementation is used to reset the database with 
 * proper variables so testing and modifications can be made.
 * @author Josh Pressley ,Chris Siu - 1807 */
public class DbResetUtil {
	
//	/** Calls the sql script that resets the DB*/
//	public static boolean resetDatabase() throws IOException {
//		BufferedReader reader = null;
//		String localCopy;		
//		Session session = null;
//		try {
//			File f = new File("/Users/Josh/Documents/GitHub/trackforce/TrackForce/src/main/resources/reInitDB.sql");
//			reader = new BufferedReader(new FileReader(f));
//			
//			session = HibernateUtil.getSessionFactory().openSession();
//			session.beginTransaction();
//			
//			while((localCopy = reader.readLine()) != null) { 
//				if (localCopy.isEmpty() || localCopy.contains("--")) { continue; }
//				localCopy = localCopy.substring(0, localCopy.length()-1); //removes the semicolon
//				session.createQuery(localCopy).executeUpdate();
//			}
//			
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
	
	/** Calls the sql script that resets the DB*/
	public static boolean resetDatabase() {
		Flyway flyway = new Flyway();
		flyway.setDataSource(System.getenv("TRACKFORCE_DB_URL"),System.getenv("TRACKFORCE_DB_USERNAME"),System.getenv("HBM_PW_ENV"));
		flyway.setLocations("filesystem:src/main/resources/");
		flyway.migrate();
		return true;
	}
}