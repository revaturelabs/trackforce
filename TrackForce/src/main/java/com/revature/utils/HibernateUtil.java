package com.revature.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Utility class for configurations and getting a Hibernate SessionFactory object.
 */
public class HibernateUtil {
	
	private HibernateUtil() {
	}

	private static SessionFactory sessionfact = buildSessionFactory();

	/**
	 * Returns a SessionFactor objects based on hibernate.cfg.xml
	 * 
	 * @return a new SessionFactory object from hibernate.cfg.xml
	 */
	private static SessionFactory buildSessionFactory() {
		return new Configuration().configure().buildSessionFactory();
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