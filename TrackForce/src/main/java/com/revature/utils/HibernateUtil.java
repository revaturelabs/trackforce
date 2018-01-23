package com.revature.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.ServiceRegistry;

import com.revature.config.DataSourceConfig;

/**
 * Utility class for configurations and getting a Hibernate SessionFactory
 * object.
 */
public class HibernateUtil {

	private HibernateUtil() {
	}

	private static SessionFactory sessionfact;

	/**
	 * Returns a SessionFactory objects based on hibernate.cfg.xml
	 * 
	 * @return a new SessionFactory object from hibernate.cfg.xml
	 * @throws IOException
	 */
	private static SessionFactory buildSessionFactory(Configuration conf) throws IOException {
		LogUtil.logger.info("Starting connection pool...");
		SessionFactory sf;
		StandardServiceRegistryBuilder builder;
		ServiceRegistry registry;
		
		// we need to use this class to inject our 3rd party datasource
		DatasourceConnectionProviderImpl dscpi = new DatasourceConnectionProviderImpl();
		try (InputStream is = HibernateUtil.class.getClassLoader().getResourceAsStream("tomcat-jdbc.properties")) {
			// initialize datasource
			dscpi.setDataSource(DataSourceConfig.getDatasource());
			
			// initialize properties and configurations
            conf.setProperty("hibernate.connection.url", DataSourceConfig.getUrl());
            conf.setProperty("hibernate.connection.username", DataSourceConfig.getUsername());
			conf.setProperty("hibernate.connection.password", DataSourceConfig.getPassword());

			// set cfg properties
			dscpi.configure(conf.getProperties());

			// configure the service registry
			builder = new StandardServiceRegistryBuilder();
			builder.configure();    // from hibernate.cfg.xml
			builder.addService(ConnectionProvider.class, dscpi);
			builder.applySettings(conf.getProperties());
			registry = builder.build();

			// build the factory
			sf = conf.buildSessionFactory(registry);
			LogUtil.logger.info("Connection Pool configured");
			LogUtil.logger.info("SessionFactory successfully built");
			return sf;
		}
	}

	/**
	 * Returns the SessionFactory stored in the HibernateUtil class.
	 * @param props 
	 * 
	 * @return the SessionFactory stored in HibernateUtil.
	 * @throws IOException
	 */
	public static SessionFactory initSessionFactory(Properties props) throws IOException {
		if (sessionfact == null) {
			// initialize configurations
			Configuration conf = new Configuration().configure();
			
			// If no args supplied then use default
			if (props == null) {
				props = new Properties();
				props.setProperty("hibernate.hbm2ddl.auto", "validate");
			}

			// set property information 
			conf.setProperties(props); 
			sessionfact = buildSessionFactory(conf);
		}
		return sessionfact;
	}

	/**
	 * Returns the SessionFactory stored in the HibernateUtil class.
	 * @param props 
	 * 
	 * @return the SessionFactory stored in HibernateUtil.
	 * @throws IOException
	 */
	public static SessionFactory getSessionFactory() throws IOException {
		return sessionfact == null ? initSessionFactory(null) : sessionfact;
	}
	
	/**
	 * Closes the SessionFactory in HibernateUtil.
	 * 
	 * @throws IOException
	 */
	public static void shutdown() {
		if (sessionfact != null) {
			sessionfact.close();
			// This manually deregisters JDBC driver, which prevents Tomcat 7 from
			// complaining about memory leaks to this class
			Enumeration<Driver> drivers = DriverManager.getDrivers();
			while (drivers.hasMoreElements()) {
				Driver driver = drivers.nextElement();
				try {
					DriverManager.deregisterDriver(driver);
					LogUtil.logger.info(String.format("deregistering jdbc driver: %s", driver));
				} catch (SQLException e) {
					LogUtil.logger.fatal(String.format("Error deregistering driver %s", driver), e);
				}
			}
		}
	}
}