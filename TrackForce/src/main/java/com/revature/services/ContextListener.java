package com.revature.services;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.revature.runnable.PSDCacheRunner;
import com.revature.utils.HibernateUtil;

public class ContextListener implements ServletContextListener {

    public ContextListener() {
    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.shutdown();
	}

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
    	Thread worker = new Thread(new PSDCacheRunner());
    	worker.start();
    }
}
