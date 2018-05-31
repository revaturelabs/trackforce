package com.revature.utils;

import static com.revature.utils.LogUtil.logger;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.ServiceRegistry;

/**
 * Utility class for configurations and getting a Hibernate SessionFactory
 * object.
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static ServiceRegistry registry;

	/**
	 * Returns a new session from the sessionFactory
	 *
	 * @return A newly created session
	 */
	public static Session getSession() {
		return getSessionFactory().openSession();
	}

	/**
	 * Returns the SessionFactory stored in the HibernateUtil class; Initializes it
	 * if not already initialized
	 *
	 * @return the SessionFactory stored in HibernateUtil.
	 */

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			DataSource dataSource = new DataSourceBuilder().fromPropertiesFile("tomcat-jdbc.properties");
			Properties extraProps = new Properties(); // empty = default properties
			initSessionFactory(dataSource, extraProps);
		}
		return sessionFactory;
	}

	/**
	 * Initializes/configures the SessionFactory. Should be called no more than
	 * once, then session factory is consequently retrieved with getSessionFactory()
	 *
	 * @param dataSource
	 *            - path to properties file for DataSource
	 * @param dbProps
	 *            - properties other than those from datasource properties file, --
	 *            must include hibernate.connection.dialect and
	 *            hibernate.driver_class
	 */
	private static void initSessionFactory(DataSource dataSource, Properties dbProps) {

		try {

			Configuration config = new Configuration().configure() // hibernate.cfg.xml
					.addProperties(dbProps); // appends/overwrites hibernate.cfg.xml

			// Inject datasource
			DatasourceConnectionProviderImpl dscpi = new DatasourceConnectionProviderImpl();
			dscpi.setDataSource(dataSource);
			dscpi.configure(config.getProperties());

			//
			// // build session factory
			// sessionFactory = config.buildSessionFactory(registry);
			// logger.info("SessionFactory successfully built");
			//
			// configure the service registry
			StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder()
					.addService(ConnectionProvider.class, dscpi).configure() // hibernate.cfg.xml
					.applySettings(config.getProperties()); // appends/overwrites hibernate.cfg.xml
			registry = registryBuilder.build();

			// build session factory
			sessionFactory = config.buildSessionFactory(registry);
			LogUtil.logger.info("SessionFactory successfully built");

		} catch (Exception e) {
			LogUtil.logger.error("Error caught in creating Hibernate Session");
			LogUtil.logger.error(e.getMessage());
			if (registry != null) {
				LogUtil.logger.info("Destroying registry after failed Hibernate Session creation.");
				StandardServiceRegistryBuilder.destroy(registry);
			}
		}
	}

	/**
	 * Closes the SessionFactory in HibernateUtil.
	 */
	public static void shutdown() {
		if (sessionFactory != null) {

			// logger.info("Shutting down SessionFactory");

			LogUtil.logger.info("Shutting down SessionFactory");

			sessionFactory.close();
			logger.info("SessionFactory has been shutdown.");
			// This manually deregisters JDBC driver, which prevents Tomcat 7 from
			// complaining about memory leaks to this class

			Enumeration<Driver> drivers = DriverManager.getDrivers();
			while (drivers.hasMoreElements()) {
				Driver driver = drivers.nextElement();
				try {
					// logger.info(String.format("Deregistering jdbc driver: %s", driver));
					// DriverManager.deregisterDriver(driver);
					// logger.info(String.format("%s has been deregistered.", driver));
					LogUtil.logger.info(String.format("Deregistering jdbc driver: %s", driver));
					DriverManager.deregisterDriver(driver);
					LogUtil.logger.info(String.format("%s has been deregistered.", driver));
				} catch (SQLException e) {
					logger.fatal(String.format("Error deregistering driver %s", driver), e);
				}
			}

			if (registry != null) {
				LogUtil.logger.info("Destroying registry");
				StandardServiceRegistryBuilder.destroy(registry);
			}
		}
	}

}