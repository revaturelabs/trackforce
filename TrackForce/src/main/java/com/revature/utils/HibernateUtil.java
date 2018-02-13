package com.revature.utils;

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

    /**
     * Returns a new session from the sessionFactory
     * @return A newly created session
     */
    public static Session getSession() {
    	return getSessionFactory().openSession();
    }
    
    /**
     * Returns the SessionFactory stored in the HibernateUtil class;
     * Initializes it if not already initialized
     *
     * @return the SessionFactory stored in HibernateUtil.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            DataSource dataSource = new DataSourceBuilder().fromPropertiesFile("tomcat-jdbc.properties");
            Properties extraProps = new Properties();   // empty = default properties
            initSessionFactory(dataSource, extraProps);
        }
        return sessionFactory;
    }

    /**
     * Initializes/configures the SessionFactory.
     * Should be called no more than once, then session factory is consequently retrieved
     * with getSessionFactory()
     *
     * @param dataSource - path to properties file for DataSource
     * @param dbProps    - properties other than those from datasource properties file,
     *                   -- must include hibernate.connection.dialect and hibernate.driver_class
     * @return the SessionFactory stored in HibernateUtil.
     */
    public static SessionFactory initSessionFactory(DataSource dataSource, Properties dbProps) {

        Configuration config = new Configuration()
                .configure()                // hibernate.cfg.xml
                .addProperties(dbProps);    // appends/overwrites hibernate.cfg.xml

        // Inject datasource
        DatasourceConnectionProviderImpl dscpi = new DatasourceConnectionProviderImpl();
        dscpi.setDataSource(dataSource);
        dscpi.configure(config.getProperties());

        // configure the service registry
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder()
                .addService(ConnectionProvider.class, dscpi)
                .configure()                                // hibernate.cfg.xml
                .applySettings(config.getProperties());     // appends/overwrites hibernate.cfg.xml
        ServiceRegistry registry = registryBuilder.build();

        // build session factory
        sessionFactory = config.buildSessionFactory(registry);
        LogUtil.logger.info("SessionFactory successfully built");

        return sessionFactory;
    }

    /**
     * Closes the SessionFactory in HibernateUtil.
     */
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            System.out.println("SessionFactory has been shutdown.");
            // This manually deregisters JDBC driver, which prevents Tomcat 7 from
            // complaining about memory leaks to this class
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                try {
                	System.out.println("Deregistering Driver");
                    DriverManager.deregisterDriver(driver);
                    LogUtil.logger.info(String.format("deregistering jdbc driver: %s", driver));
                    System.out.println("Driver has been Deregistered");
                } catch (SQLException e) {
                    LogUtil.logger.fatal(String.format("Error deregistering driver %s", driver), e);
                    System.out.println("Driver has not been Deregistered");
                }
            }
        }
    }

}