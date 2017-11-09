package com.revature.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private HibernateUtil() {
	}

	private static SessionFactory sessionfact = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		return new Configuration().configure().buildSessionFactory();
	}

	public static SessionFactory getSession() {
		return sessionfact;
	}

	public static void shutdown() {
		getSession().close();
	}
}