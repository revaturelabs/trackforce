package com.revature.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {
    
    private static Session sessionfact = buildSessionFactory();
    
    private static Session buildSessionFactory() {
        //Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        
        return new Configuration().configure().buildSessionFactory().openSession();
    }
    
    public static Session getSession() {
        return sessionfact;
    }
    
    public static void shutdown() {
        getSession().close();
    }
}
