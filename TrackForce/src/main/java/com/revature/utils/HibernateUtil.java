package com.revature.utils;

import static com.revature.utils.LogUtil.logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Utility class for configurations and getting a Hibernate SessionFactory
 * object.
 */
public class HibernateUtil {

	private HibernateUtil() {}
	private static SessionFactory sessionFactory = buildSessionFactory();
	private static ServiceRegistry registry;

	private static SessionFactory buildSessionFactory(DataSource dataSource,Properties extraProps) {
		Properties props = new Properties();
		Configuration cfg = new Configuration();
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
			cfg.setProperties(props);
		} catch (FileNotFoundException e) {
			logger.error("FNFException in BuildSessionFactory", e);
		} catch (IOException e) {
			logger.error("IOException in BuildSessionFactory", e);
		}
		return cfg.configure().buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			DataSource dataSource = new DataSourceBuilder().fromPropertiesFile("tomcat-jdbc.properties");
			Properties extraProps = new Properties(); // empty = default properties
			buildSessionFactory(dataSource, extraProps);
		}
		return sessionFactory;
	}
	
	public static void shutdown() {
		System.out.println("Shutting down SessionFactory");
		getSessionFactory().close();
		System.out.println("SessionFactory closed");
	}
	
	public static Session getSession() {
		return getSessionFactory().openSession();
	}
	
	public static void closeSession(Session session) {
		if (session != null) {
			session.close();
			System.out.println("Session is" + (session.isOpen() ? " open" : " closed"));
		}
	}
	
	public static void rollbackTransaction(Transaction transaction) {
		if (transaction != null) {
			transaction.rollback();
			System.out.println("Transaction rolled back");
		}
	}
	
	public static boolean runHibernateTransaction(Sessional<Boolean> sessional, Object ... args) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			boolean b = sessional.operate(session, transaction, args);
			transaction.commit();
			if (b) logger.info("Transaction committed!");
			return b;
		} catch (HibernateException e) {
			HibernateUtil.rollbackTransaction(transaction);
			logger.error(e.getMessage(), e);
		}
		finally {
			HibernateUtil.closeSession(session);
		}
		return false;
	}

	public static <T> T runHibernate(Sessional<T> ss, Object ... args) {
		Session session = null;
		Throwable t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return ss.operate(session, args);
		} catch (HibernateException e) {
			logger.error(e.getMessage(), e);
			t = e;
		}
		finally {
			HibernateUtil.closeSession(session);
		}
		throw new HibernateException(t);
	}



	public static <T> List<T> runHibernate(ListOp<T> ss,  Object ... args) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return ss.operate(session, args);
		} catch (HibernateException e) {
			logger.error(e.getMessage(), e);
		}
		finally {
			HibernateUtil.closeSession(session);
		}
		return null;
	}

	public static boolean saveToDB(Object o) {
		return runHibernateTransaction((Session session, Object ... args) -> {
			session.save(args[0]);
			return true;
		}, o);
	}


	public static Sessional ss = (Session ss, Object[] obj) -> true;
	
	
	// =============================================================================
	
	/**
	 * Returns a new session from the sessionFactory
	 *
	 * @return A newly created session
	 */
//	public static Session getSession() {
//		return getSessionFactory().openSession();
//	}

	/**
	 * Returns the SessionFactory stored in the HibernateUtil class; Initializes it
	 * if not already initialized
	 *
	 * @return the SessionFactory stored in HibernateUtil.
	 */

//	public static SessionFactory getSessionFactory() {
//		if (sessionFactory == null) {
//			DataSource dataSource = new DataSourceBuilder().fromPropertiesFile("tomcat-jdbc.properties");
//			Properties extraProps = new Properties(); // empty = default properties
//			initSessionFactory(dataSource, extraProps);
//		}
//		return sessionFactory;
//	}

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
//	private static void initSessionFactory(DataSource dataSource, Properties dbProps) {
//
//		try {
//
//			Configuration config = new Configuration().configure() // hibernate.cfg.xml
//					.addProperties(dbProps); // appends/overwrites hibernate.cfg.xml
//
//			// Inject datasource
//			DatasourceConnectionProviderImpl dscpi = new DatasourceConnectionProviderImpl();
//			dscpi.setDataSource(dataSource);
//			dscpi.configure(config.getProperties());
//
//			//
//			// // build session factory
//			// sessionFactory = config.buildSessionFactory(registry);
//			// logger.info("SessionFactory successfully built");
//			//
//			// configure the service registry
//			StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder()
//					.addService(ConnectionProvider.class, dscpi).configure() // hibernate.cfg.xml
//					.applySettings(config.getProperties()); // appends/overwrites hibernate.cfg.xml
//			registry = registryBuilder.build();
//
//			// build session factory
//			sessionFactory = config.buildSessionFactory(registry);
//			LogUtil.logger.info("SessionFactory successfully built");
//
//		} catch (Exception e) {
//			LogUtil.logger.error("Error caught in creating Hibernate Session");
//			LogUtil.logger.error(e.getMessage());
//			if (registry != null) {
//				LogUtil.logger.info("Destroying registry after failed Hibernate Session creation.");
//				StandardServiceRegistryBuilder.destroy(registry);
//			}
//		}
//	}

	/**
	 * Closes the SessionFactory in HibernateUtil.
	 */
//	public static void shutdown() {
//		if (sessionFactory != null) {
//
//			// logger.info("Shutting down SessionFactory");
//
//			LogUtil.logger.info("Shutting down SessionFactory");
//
//			sessionFactory.close();
//			logger.info("SessionFactory has been shutdown.");
//			// This manually deregisters JDBC driver, which prevents Tomcat 7 from
//			// complaining about memory leaks to this class
//
//			Enumeration<Driver> drivers = DriverManager.getDrivers();
//			while (drivers.hasMoreElements()) {
//				Driver driver = drivers.nextElement();
//				try {
//					// logger.info(String.format("Deregistering jdbc driver: %s", driver));
//					// DriverManager.deregisterDriver(driver);
//					// logger.info(String.format("%s has been deregistered.", driver));
//					LogUtil.logger.info(String.format("Deregistering jdbc driver: %s", driver));
//					DriverManager.deregisterDriver(driver);
//					LogUtil.logger.info(String.format("%s has been deregistered.", driver));
//				} catch (SQLException e) {
//					logger.fatal(String.format("Error deregistering driver %s", driver), e);
//				}
//			}
//
//			if (registry != null) {
//				LogUtil.logger.info("Destroying registry");
//				StandardServiceRegistryBuilder.destroy(registry);
//			}
//		}
//	}

}