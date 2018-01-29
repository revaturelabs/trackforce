package com.revature.test.context;

import java.io.IOException;

import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.*;

import static com.revature.config.DataSourceBuilder.Constants.*;

import com.revature.config.TomcatJDBCDataSourceBuilder;
import com.revature.services.PersistentServiceDelegator;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class PersistenceContext {
	
	public static final String TEST_URL = "tf.test-url";
	public static final String TEST_USER = "tf.test-user";
	public static final String TEST_PASS = "tf.test-pass";

	@BeforeSuite
	public void beforeSuite() throws IOException {
		
		// Configure DataSource
		Properties props = new Properties();
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("mock-tomcat-jdbc.properties");
			props.load(is);
			
			is.close();

			// mock environment variables for test configurations
			props.setProperty(URL_KEY, System.getenv(props.getProperty(TEST_URL)));
			props.setProperty(USERNAME_KEY, System.getenv(props.getProperty(TEST_USER)));
			props.setProperty(PASS_KEY, System.getenv(props.getProperty(TEST_PASS)).trim());

			// override dialect and driver class to use hsqldb
			props.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
			props.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
			props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
			props.setProperty("hibernate.show_sql", "true");
			props.setProperty("hibernate.default_schema", "admin");
			
			// intialize Hibernate
			HibernateUtil.setDataSourceBuilder(new TomcatJDBCDataSourceBuilder(), props);
			HibernateUtil.initSessionFactory(props);
			
			
			TestDBLoader.load();

			// Initialize persistent storages
			PersistentServiceDelegator psd = new PersistentServiceDelegator();
			psd.getAssociates();
			psd.getBatches();
			psd.getBatchesSortedByDate();
			psd.getClients();
			psd.getCurriculums();
			psd.getMarketingStatuses();
			psd.getTotals();
	}

	@AfterSuite
	public void afterSuite() {
		HibernateUtil.shutdown();
	}
}
