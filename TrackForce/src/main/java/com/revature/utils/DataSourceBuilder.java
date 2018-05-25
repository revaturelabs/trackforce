package com.revature.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.revature.utils.LogUtil.logger;
import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * DataSource configurations for an injectable DataSource
 * Using principles IoC and coding to an interface
 * This uses DI to make datasources more configurable
 * and allows for ease of testing
 *
 * @author Vaeth
 */
public class DataSourceBuilder {
	
    public static final String URL_KEY = "url-env";
    public static final String USERNAME_KEY = "username-env";
    public static final String PASS_KEY = "password-env";

    /**
     * returns Datasource (with connection pool) configured from previously initialized props
     *
     * @param resourceName
     * @return
     */
    public javax.sql.DataSource fromPropertiesFile(String resourceName) {
        DataSource ds = new DataSource();

        // get properties file used for this datasource
        Properties dsProps = propertiesFromFile(resourceName);

        if (dsProps != null) {
        	ds.setDriverClassName(dsProps.getProperty("driver-class"));
            ds.setMaxActive(Integer.parseInt(dsProps.getProperty("jdbc.max-active", "10")));
            ds.setMaxIdle(Integer.parseInt(dsProps.getProperty("jdbc.max-idle", "10")));
            ds.setMaxWait(Integer.parseInt(dsProps.getProperty("max-wait", "60000")));
            ds.setTestOnBorrow(Boolean.parseBoolean(dsProps.getProperty("test-on-borrow", "true")));
            ds.setValidationInterval(Long.parseLong(dsProps.getProperty("validation-interval", "30000")));
            ds.setRemoveAbandoned(Boolean.parseBoolean(dsProps.getProperty("removeAbandoned")));
            ds.setRemoveAbandonedTimeout(Integer.parseInt(dsProps.getProperty("removeAbandonedTimeout")));
            ds.setInitialSize(Integer.parseInt(dsProps.getProperty("initialSize")));

            ds.setUrl(dsProps.getProperty(URL_KEY));
            ds.setUsername(dsProps.getProperty(USERNAME_KEY));
            ds.setPassword(dsProps.getProperty(PASS_KEY));
        }
        
        return ds;
    }

    /**
     * resource
     *
     * @param resourceName
     * @return
     */
    private Properties propertiesFromFile(String resourceName) {
        try (InputStream is = DataSourceBuilder.class.getClassLoader().getResourceAsStream(resourceName)) {
            Properties props = new Properties();
            props.load(is);

            // read in the environment variable names
            String urlEnvironmentVariable = props.getProperty(URL_KEY);
            String usernameEnvironmentVariable = props.getProperty(USERNAME_KEY);
            String passwordEnvironmentVariable = props.getProperty(PASS_KEY);

            // replace environment variable names with their actual values
            props.setProperty(URL_KEY, System.getenv(urlEnvironmentVariable));
            props.setProperty(USERNAME_KEY, System.getenv(usernameEnvironmentVariable));
            // trim to account for the fact that windows doesn't allow empty environment variables
            props.setProperty(PASS_KEY, System.getenv(passwordEnvironmentVariable));

            return props;
        } catch (IOException e) {
            logger.error(new IOException("Could not read properties file", e));
        }
        return null;
    }


}
