package com.revature.config;

import static com.revature.config.DataSourceBuilder.Constants.PASS_KEY;
import static com.revature.config.DataSourceBuilder.Constants.URL_KEY;
import static com.revature.config.DataSourceBuilder.Constants.USERNAME_KEY;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.revature.utils.LogUtil;


/**
 * utility class to configure DataSource and its connection pool
 * based on info from file 'tomcat-jdbc.properties'
 */
public class TomcatJDBCDataSourceBuilder implements DataSourceBuilder {

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

        tds.setDriverClassName(props.getProperty("tf.driver-class"));
        tds.setMaxActive(Integer.parseInt(props.getProperty("tomcat.jdbc.max-active", "10")));
        tds.setMaxIdle(Integer.parseInt(props.getProperty("tomcat.jdbc.max-idle", "10")));
        tds.setMaxWait(Integer.parseInt(props.getProperty("tomcat.max-wait", "60000")));
        tds.setTestOnBorrow(Boolean.parseBoolean(props.getProperty("tomcat.test-on-borrow", "true")));
        tds.setValidationInterval(Long.parseLong(props.getProperty("tomcat.validation-interval", "30000")));
        
        this.ds = tds;
        
        return this.ds;
    }
    
    @Override
    public javax.sql.DataSource getDataSource(){
    	if(ds == null) {
    		buildDataSource(buildDefaultProperties());
    	}
    		return ds;
    }

    private Properties buildDefaultProperties() {
    	Properties defaultProps = new Properties();
		try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("tomcat-jdbc.properties")) {
			
			defaultProps = new Properties();
			defaultProps.load(is);
			
	        // read in the environment variables
	        String urlEnvironmentVariable = defaultProps.getProperty(URL_KEY);
	        String usernameEnvironmentVariable = defaultProps.getProperty(USERNAME_KEY);
	        String passwordEnvironmentVariable = defaultProps.getProperty(PASS_KEY);

	        // replace environment variable names with their actual values
	        defaultProps.setProperty(URL_KEY, System.getenv(urlEnvironmentVariable));
	        defaultProps.setProperty(USERNAME_KEY, System.getenv(usernameEnvironmentVariable));
	        defaultProps.setProperty(PASS_KEY, System.getenv(passwordEnvironmentVariable));

		} catch (IOException e) {
			LogUtil.logger.error(new IOException("Could not read properties file", e));
		}
		
        return defaultProps;
	}

	/**
     * gets password from already initialized prop object
     * @return
     */
    @Override
    public String getPassword() {
        return props.getProperty(Constants.PASS_KEY); 
    }

    /**
     * gets password from
     * @return
     */
    @Override
    public String getUsername() {
        return props.getProperty(Constants.USERNAME_KEY); 
    }

    @Override
    public String getUrl() {
        return props.getProperty(Constants.URL_KEY); 
    }
    
    
}
