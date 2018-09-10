package com.revature.daoimpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.hibernate.Session;
import com.revature.dao.DbResetDao;
import com.revature.utils.HibernateUtil;

/** This implementation is used to reset the database with 
 * proper variables so testing and modifications can be made.
 * @author Josh Pressley ,Chris Siu - 1807 */
public class DbResetDaoImpl implements DbResetDao {
	
	/** Calls the sql script that resets the DB*/
	@Override
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
		if (result != -1) { return true; }
		return false;
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