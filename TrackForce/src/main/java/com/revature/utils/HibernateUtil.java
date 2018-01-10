package com.revature.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Utility class for configurations and getting a Hibernate SessionFactory
 * object.
 */

public class HibernateUtil {

	private static final String HBM_PW_ENV = "AyXBhs3X5z3m2djc";

	private HibernateUtil() {
	}

	private static SessionFactory sessionfact = buildSessionFactory();

	/**
	 * Returns a SessionFactor objects based on hibernate.cfg.xml
	 * 
	 * @return a new SessionFactory object from hibernate.cfg.xml
	 */
	private static SessionFactory buildSessionFactory() {
		Configuration conf = new Configuration().configure();
		Properties props = new Properties();
		props.setProperty("hibernate.connection.password", System.getenv(HBM_PW_ENV));
		conf.addProperties(props);
		return conf.buildSessionFactory();
	}

	/**
	 * Returns the SessionFactory stored in the HibernateUtil class.
	 * 
	 * @return the SessionFactory stored in HibernateUtil.
	 */
	public static SessionFactory getSession() {
		return sessionfact;
	}

	/**
	 * Closes the SessionFactory in HibernateUtil.
	 */
	public static void shutdown() {
		getSession().close();
	}
}