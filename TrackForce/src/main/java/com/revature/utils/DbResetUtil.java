package com.revature.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.hibernate.Session;

/** This implementation is used to reset the database with 
 * proper variables so testing and modifications can be made.
 * @author Josh Pressley ,Chris Siu - 1807 */
public class DbResetUtil {
	
	/** Calls the sql script that resets the DB*/
	public boolean resetDatabase() {
		Session session = null;
		int result = -1;
		try {
			String script = getScript();
			session = HibernateUtil.getSessionFactory().openSession();
			result = session.createQuery(script).executeUpdate();
		} catch (IOException ioe) {
			System.out.println("Script loading had an issue");
		} finally {
			if (session != null) { session.close(); }
		}
		return (result != -1);
	}

	/** Reads in sql reset script */
	private String getScript() throws IOException{
		StringBuilder script = new StringBuilder();
		BufferedReader reader = null;
		String localCopy;
		script.append("BEGIN");
		try {
			reader = new BufferedReader(new FileReader(new File("reInitDB.sql")));
			while((localCopy = reader.readLine()) != null) { 
				script.append(localCopy); 
				}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException occurred locating DB-Reset script");
		} catch (IOException e) {
			System.out.println("IOException occurred reading in DB-Reset script");
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		script.append("END;");
		return script.toString();
	}
}