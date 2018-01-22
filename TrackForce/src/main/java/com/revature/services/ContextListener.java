package com.revature.services;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
        // Cache initial data
        try {
            HibernateUtil.getSessionFactory();
            psd.getAssociates();
            psd.getBatches();
            psd.getClients();
            psd.getCurriculums();
            psd.getMarketingStatuses();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
