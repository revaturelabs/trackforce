package com.revature.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.revature.utils.HibernateUtil;

public class DataSourceConfig {

	private static Properties props;

	private static String PW_KEY = "tf.password-env";
	private static String HBM_PW_ENV;

	public static DataSource getDatasource() throws IOException {
		try {
			if (props == null)
				buildProps();
			DataSource ds = new DataSource();
			ds.setDriverClassName(props.getProperty("tf.driver-class"));
			ds.setUrl(props.getProperty("tf.url"));
			ds.setUsername(props.getProperty("tf.username"));
			ds.setPassword(System.getenv(props.getProperty(PW_KEY)));
			ds.setMaxActive(Integer.parseInt(props.getProperty("tomcat.jdbc.max-active", "10")));
			ds.setMaxIdle(Integer.parseInt(props.getProperty("tomcat.jdbc.max-idle", "10")));
			ds.setMaxWait(Integer.parseInt(props.getProperty("tomcat.max-wait", "60000")));
			ds.setTestOnBorrow(Boolean.parseBoolean(props.getProperty("tomcat.test-on-borrow", "true")));
			ds.setValidationInterval(Long.parseLong(props.getProperty("tomcat.validation-interval", "30000")));
			return ds;
		} catch (IOException ex) {
			throw new IOException("Could not initialize datasource", ex);
		}
	}

	private static void buildProps() throws IOException {
		try (InputStream is = HibernateUtil.class.getClassLoader().getResourceAsStream("tomcat-jdbc.properties");) {
			props = new Properties();
			props.load(is);
		} catch (IOException ex) {
			throw new IOException("Could not initialize properties", ex);
		}
	}

	public static String getHibernatePasswordEnvironmentVariable() throws IOException {
		if (HBM_PW_ENV == null) {
			if (props == null) {
				buildProps();
			}
			HBM_PW_ENV = props.getProperty(PW_KEY);
		}
		return HBM_PW_ENV;
	}
}
