package com.revature.services;

import java.io.IOException;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.revature.config.TomcatJDBCDataSourceBuilder;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

import static com.revature.config.DataSourceBuilder.Constants.*;

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
		PersistentServiceDelegator psd = new PersistentServiceDelegator();
		Properties props;
		
		// Start the connection pool
		// Cache initial data
		try {
			if (HibernateUtil.getSessionFactory(false) == null) {
				try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("tomcat-jdbc.properties")) {
					
					props = new Properties();
					props.load(is);
					
			        // read in the environment variables
			        String urlEnvironmentVariable = props.getProperty(URL_KEY);
			        String usernameEnvironmentVariable = props.getProperty(USERNAME_KEY);
			        String passwordEnvironmentVariable = props.getProperty(PASS_KEY);

			        // replace environment variable names with their actual values
			        props.setProperty(URL_KEY, System.getenv(urlEnvironmentVariable));
			        props.setProperty(USERNAME_KEY, System.getenv(usernameEnvironmentVariable));
			        props.setProperty(PASS_KEY, System.getenv(passwordEnvironmentVariable));
			        
					HibernateUtil.setDataSourceBuilder(new TomcatJDBCDataSourceBuilder(), props); 
					HibernateUtil.getSessionFactory();
					
					psd.getAssociates();
					psd.getBatches();
					psd.getClients();
					psd.getCurriculums();
					psd.getMarketingStatuses();
				} catch (IOException e) {
					LogUtil.logger.error(new IOException("Could not initialize SessionFactory", e));
				}
			}
		} catch (IOException e) {
			LogUtil.logger.error(new IOException("Could not get session"), e);
		}
	}
}
