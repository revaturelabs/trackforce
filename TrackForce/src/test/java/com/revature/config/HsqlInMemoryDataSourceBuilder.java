package com.revature.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.revature.utils.LogUtil;


/**
 * utility class to configure DataSource and its connection pool
 * based on info from file 'tomcat-jdbc.properties'
 */
public class HsqlInMemoryDataSourceBuilder implements DataSourceBuilder {

    private Properties props;
    private javax.sql.DataSource ds;

    /**
     * returns Datasource (with connection pool) configured from previously initialized props
     *
     * @return
     */
    public javax.sql.DataSource buildDataSource(Properties props) {
        DataSource tds = new DataSource();
        // set the properties file used for this datasource
        this.props = props;
        tds.setUrl(getUrl());
        tds.setUsername(getUsername());
        tds.setPassword(getPassword());
        tds.setDriverClassName(getDriverClassName());

        this.ds = tds;
        return this.ds;
    }

    @Override
    public javax.sql.DataSource getDataSource() {
        if (ds == null) {
            buildDataSource(buildDefaultProperties());
        }
        return ds;
    }

    public static Properties buildDefaultProperties() {
        Properties defaultProps = new Properties();
        try (InputStream is = HsqlInMemoryDataSourceBuilder.class.getClassLoader()
                .getResourceAsStream("mock-tomcat-jdbc.properties")) {    // TODO: refactor mock-tomcat-jdbc.properties

            defaultProps = new Properties();
            defaultProps.load(is);

        } catch (IOException e) {
            LogUtil.logger.error(new IOException("Could not read properties file", e));
        }
        return defaultProps;
    }

    /**
     * in-memory hsql database does not require username, password, but HibernateUtil initSession uses
     * a hash table meaning no nulls, so empty string it is
     *
     * @return
     */
    @Override
    public String getPassword() {
        return "";
    }

    /**
     * in-memory hsql database does not require username, password, but HibernateUtil initSession uses
     * a hash table meaning no nulls, so empty string it is
     *
     * @return
     */
    @Override
    public String getUsername() {
        return "";
    }

    /**
     * note the mem designates it as an in-memory database/datasource
     *
     * @return
     */
    @Override
    public String getUrl() {
        return "jdbc:hsqldb:mem:mockdb";
    }

    /**
     * hsql jdbc driver class
     *
     * @return
     */
    public String getDriverClassName() {
        return "org.hsqldb.jdbc.JDBCDriver";
    }

}
