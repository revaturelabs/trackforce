package com.revature.services;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.revature.utils.HibernateUtil;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.shutdown();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	}

}
