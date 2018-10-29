package com.revature.utils;

/** This implementation is used to reset the database with 
 * proper variables so testing and modifications can be made.
 * @author Josh Pressley ,Chris Siu - 1807 */
public class DbResetUtil {
	
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
	public static boolean resetDatabase() { 
		return true;
	}
	
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