package com.revature.services;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.glassfish.hk2.utilities.reflection.Logger;

import com.revature.utils.HibernateUtil;

public class ContextListener implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.shutdown();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		PersistentServiceDelegator psd = new PersistentServiceDelegator();
//		Start the connection pool
//		Cache initial data
		try {
			HibernateUtil.getSession();
			psd.getAssociates();
			psd.getBatches();
			psd.getClients();
			psd.getCurriculums();
			psd.getMarketingStatuses();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
