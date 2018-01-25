package com.revature.utils;

import com.revature.config.HsqlInMemoryDataSourceBuilder;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
    private static SessionFactory sessionFactory;

    /**
     * initialize hsql in memory database (if not already init) and return the singleton session factory
     *
     * @return
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static SessionFactory getTestSessionFactory() throws IOException, SQLException, ClassNotFoundException {
        if (sessionFactory == null) {
            Properties props = HsqlInMemoryDataSourceBuilder.buildDefaultProperties();
            props.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
            props.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbc.JDBCDriver");
            props.setProperty("hibernate.hbm2ddl.auto", "update");
            props.setProperty("hibernate.show_sql", "true");
            initSchemaForHsql();    // executed before hibernate creates tables, so that ADMIN schema exists by that time
            HibernateUtil.setDataSourceBuilder(new HsqlInMemoryDataSourceBuilder(), props);
            sessionFactory = HibernateUtil.initSessionFactory(props);
        }
        return sessionFactory;
    }

    /**
     * jdbc script that creates ADMIN schema, executed before building the session factory
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private static void initSchemaForHsql() throws ClassNotFoundException, SQLException {
        HsqlInMemoryDataSourceBuilder hsql = new HsqlInMemoryDataSourceBuilder();
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        Connection conn = DriverManager.getConnection(hsql.getUrl(), hsql.getUsername(), hsql.getPassword());
        Statement stmt = conn.createStatement();
        String sql = "CREATE SCHEMA ADMIN AUTHORIZATION DBA";
        stmt.execute(sql);
        stmt.close();
    }
}
