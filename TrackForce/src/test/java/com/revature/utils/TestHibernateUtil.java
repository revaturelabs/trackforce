package com.revature.utils;

import org.hibernate.SessionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TestHibernateUtil {
    private static SessionFactory sessionFactory;

    /**
     * initialize hsql in memory database (if not already init) and return the singleton session factory
     *
     * @return
     * @throws SQLException
     */
    public static SessionFactory getSessionFactory() throws SQLException {
        if (sessionFactory == null) {
            // set/overwrite hibernate.cfg.xml with custom properties for hsql in memory db
            Properties extraProps = new Properties();
            extraProps.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
            extraProps.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbc.JDBCDriver");
            extraProps.setProperty("hibernate.hbm2ddl.auto", "update");
            extraProps.setProperty("hibernate.show_sql", "true");

            DataSource dataSource = new DataSourceBuilder().fromPropertiesFile("hsql-jdbc.properties");

            // before hibernate creates tables, setup ADMIN schema so it exists by that time
            initSchemaForHsql(dataSource);
            sessionFactory = HibernateUtil.initSessionFactory(dataSource, extraProps);
        }
        return sessionFactory;
    }

    /**
     * jdbc script that creates ADMIN schema, executed before building the session factory
     *
     * @param dataSource
     * @throws SQLException
     */
    private static void initSchemaForHsql(DataSource dataSource) throws SQLException {
        Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "CREATE SCHEMA ADMIN AUTHORIZATION DBA";
        stmt.execute(sql);
        stmt.close();
    }
}
