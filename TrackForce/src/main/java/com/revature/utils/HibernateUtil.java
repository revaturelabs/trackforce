package com.revature.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;

import com.revature.config.DataSourceConfig;
import com.revature.entity.*;

/**
 * Utility class for configurations and getting a Hibernate SessionFactory
 * object.
 */

public class HibernateUtil {

	private HibernateUtil() {
	}

	private static SessionFactory sessionfact;

	/**
	 * Returns a SessionFactor objects based on hibernate.cfg.xml
	 * 
	 * @return a new SessionFactory object from hibernate.cfg.xml
	 * @throws IOException
	 */
	private static SessionFactory buildSessionFactory() throws IOException {
		LogUtil.logger.info("Starting connection pool...");
		SessionFactory sf;
		StandardServiceRegistryBuilder builder;
		ServiceRegistry registry;
		
		// we need to use this class to inject our 3rd party datasource
		DatasourceConnectionProviderImpl dscpi = new DatasourceConnectionProviderImpl();
		try (InputStream is = HibernateUtil.class.getClassLoader().getResourceAsStream("tomcat-jdbc.properties")) {

			// initialize datasource
			dscpi.setDataSource(DataSourceConfig.getDatasource());
			
			// initialize configurations
			Configuration conf = new Configuration().configure();
			
			// Register Entities
			registerEntities(conf);
			
			// initialize properties and configurations
			conf.setProperty("hibernate.connection.password", DataSourceConfig.getHibernatePasswordEnvironmentVariable());
		
			// set cfg properties
			dscpi.configure(conf.getProperties());

			// configure the service registry
			builder = new StandardServiceRegistryBuilder();
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

	// Because Configuration is ignoring the mappings in hibernate.cfg.xml for some reason
	private static void registerEntities(Configuration conf) {
		Class<?>[] arr = {TfAssociate.class, TfBatch.class, TfBatchLocation.class,
						TfClient.class, TfCurriculum.class, TfEndClient.class, TfInterview.class,
						TfInterviewType.class, TfMarketingStatus.class, TfPlacement.class,
						TfRole.class, TfUser.class};
		List<Class<?>> entities = Arrays.asList(arr);
		for(Class<?> clazz : entities) {
			conf.addAnnotatedClass(clazz);
		}
	}

	/**
	 * Returns the SessionFactory stored in the HibernateUtil class.
	 * 
	 * @return the SessionFactory stored in HibernateUtil.
	 * @throws IOException
	 */
	public static SessionFactory getSession() throws IOException {
		if (sessionfact == null)
			sessionfact = buildSessionFactory();
		return sessionfact;
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
			// complaining about memory leaks wrto this class
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