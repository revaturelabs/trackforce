package com.revature.utils;

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

import javax.sql.DataSource;

/**
 * Utility class for configurations and getting a Hibernate SessionFactory
 * object.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    /**
     * Returns the SessionFactory stored in the HibernateUtil class;
     * Initializes it if not already initialized
     *
     * @return the SessionFactory stored in HibernateUtil.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties extraProps = new Properties();
            extraProps.setProperty("hibernate.connection.driver_class", "oracle.jdbc.OracleDriver");
            extraProps.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
            extraProps.setProperty("hibernate.hbm2ddl.auto", "create");
            extraProps.setProperty("hibernate.show_sql", "true");
            DataSource dataSource = new DataSourceBuilder().fromPropertiesFile("tomcat-jdbc.properties");
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
                .addProperties(dbProps)
                .configure();   // hibernate.cfg.xml

        // Inject datasource
        DatasourceConnectionProviderImpl dscpi = new DatasourceConnectionProviderImpl();
        dscpi.setDataSource(dataSource);
        dscpi.configure(config.getProperties());

        // configure the service registry
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties())
                .addService(ConnectionProvider.class, dscpi)
                .configure();   // hibernate.cfg.xml
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