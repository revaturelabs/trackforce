package com.revature.services;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.revature.runnable.PSDCacheRunner;
import com.revature.utils.HibernateUtil;

public class ContextListener implements ServletContextListener {
    PersistentServiceDelegator psd;

    public ContextListener(PersistentServiceDelegator psd) {
        this.psd = psd;
    }

    public ContextListener() {
        this.psd = new PersistentServiceDelegator();
    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.shutdown();
	}

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
//    	Thread worker = new Thread(new PSDCacheRunner(psd));
//    	worker.start();
    }
}
