package com.revature.utils;

import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Utility class for configurations and getting a Hibernate SessionFactory
 * object.
 */
public class HibernateUtil {
	private static String HBM_PW_ENV = "HBM_PW_ENV";
	private static String TRACKFORCE_DB_URL = "TRACKFORCE_DB_URL";
	private static String TRACKFORCE_DB_USERNAME = "TRACKFORCE_DB_USERNAME";
	
	private HibernateUtil() {
		// do nothing
	}

	private static SessionFactory sessionfact = buildSessionFactory();

	/**
	 * Returns a SessionFactory objects based on hibernate.cfg.xml
	 * @return a new SessionFactory object from hibernate.cfg.xml
	 * @throws IOException
	 */
	private static SessionFactory buildSessionFactory() {
		SessionFactory factory = null;
		Configuration conf = new Configuration().configure();
		String password = System.getenv(HBM_PW_ENV);
		String username = System.getenv(TRACKFORCE_DB_USERNAME);
		String url = System.getenv(TRACKFORCE_DB_URL);
		
		// initialize properties and configurations
        conf.setProperty("hibernate.connection.url", url);
        conf.setProperty("hibernate.connection.username", username);
        conf.setProperty("hibernate.connection.password", password);
        factory = conf.buildSessionFactory();
        
		return factory;
	}

	/**
	 * Returns the SessionFactory stored in the HibernateUtil class.
	 *
	 * @return the SessionFactory stored in HibernateUtil.
	 * @throws IOException
	 */
	public static SessionFactory getSessionFactory() {
		// initialize configurations
		return sessionfact == null ? sessionfact = buildSessionFactory() : sessionfact;
	}
	
	/**
	 * Closes the SessionFactory in HibernateUtil.
	 * 
	 * @throws IOException
	 */
	public static void shutdown() {
		if (sessionfact != null) {
			sessionfact.close();
			// This manually deregisters JDBC driver, which prevents Tomcat 7 from
			// complaining about memory leaks to this class
			Enumeration<Driver> drivers = DriverManager.getDrivers();
			while (drivers.hasMoreElements()) {
				Driver driver = drivers.nextElement();
				try {
					DriverManager.deregisterDriver(driver);
					LogUtil.logger.info(String.format("deregistering jdbc driver: %s", driver));
				} catch (SQLException e) {
					LogUtil.logger.fatal(String.format("Error deregistering driver %s", driver), e);
				}
			}
		}
	}
}