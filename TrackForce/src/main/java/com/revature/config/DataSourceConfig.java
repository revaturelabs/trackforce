package com.revature.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.revature.utils.HibernateUtil;

/**
 * utility class to configure DataSource and its connection pool
 * based on info from file 'tomcat-jdbc.properties'
 */
public class DataSourceConfig {

    private static Properties props;
    private static String PASSWORD_KEY = "tf.password-env";
    private static String USERNAME_KEY = "tf.username-env";
    private static String URL_KEY = "tf.url-env";

    static {
        init();
    }

    private static void init() {
        buildProps();
    }

    /**
     * Reads db properties from file tomcat-jdbc.properties and
     * stores info in Property object, props
     *
     * Note that the values associated with URL_KEY, USERNAME_KEY, and PASSWORD_KEY
     * are environment variables. Here we replace these environment variables
     * with their actual values.
     */
    private static void buildProps() {
        try (InputStream is = HibernateUtil.class.getClassLoader().getResourceAsStream("tomcat-jdbc.properties");) {
            props = new Properties();
            props.load(is);

            // read in the environment variables
            String urlEnvironmentVariable = props.getProperty(URL_KEY);
            String usernameEnvironmentVariable = props.getProperty(USERNAME_KEY);
            String passwordEnvironmentVariable = props.getProperty(PASSWORD_KEY);

            // replace environment variable names with their actual values
            props.setProperty(URL_KEY, System.getenv(urlEnvironmentVariable));
            props.setProperty(USERNAME_KEY, System.getenv(usernameEnvironmentVariable));
            props.setProperty(PASSWORD_KEY, System.getenv(passwordEnvironmentVariable));

        } catch (IOException ex) {
            System.err.println("\n\n\n\n\n\n[DataSourceConfig.java] Could not read tomcat-jdbc.properties");
            ex.printStackTrace();
        }
    }

    /**
     * returns Datasource (with connection pool) configured from previously initialized props
     *
     * @return
     */
    public static DataSource getDatasource() {
        DataSource ds = new DataSource();
        // these were previously set in the init function
        ds.setUrl(props.getProperty(URL_KEY));
        ds.setUsername(props.getProperty(USERNAME_KEY));
        ds.setPassword(props.getProperty(PASSWORD_KEY));

        ds.setDriverClassName(props.getProperty("tf.driver-class"));
        ds.setMaxActive(Integer.parseInt(props.getProperty("tomcat.jdbc.max-active", "10")));
        ds.setMaxIdle(Integer.parseInt(props.getProperty("tomcat.jdbc.max-idle", "10")));
        ds.setMaxWait(Integer.parseInt(props.getProperty("tomcat.max-wait", "60000")));
        ds.setTestOnBorrow(Boolean.parseBoolean(props.getProperty("tomcat.test-on-borrow", "true")));
        ds.setValidationInterval(Long.parseLong(props.getProperty("tomcat.validation-interval", "30000")));
        return ds;
    }

    public static String getPassword() {
        return "caleb"; 
    }

    public static String getUsername() {
        return "admin"; 
    }

    public static String getUrl() {
        return "jdbc:oracle:thin:@localhost:1521:xe"; 
    }
}
