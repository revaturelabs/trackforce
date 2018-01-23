package com.revature.test.context;

import java.io.IOException;

import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.*;

import static com.revature.config.DataSourceBuilder.Constants.*;

import com.revature.config.TomcatJDBCDataSourceBuilder;
import com.revature.services.PersistentServiceDelegator;
import com.revature.utils.HibernateUtil;

public class PersistenceContext {
	
	public static final String TEST_URL = "tf.test-url";
	public static final String TEST_USER = "tf.test-user";
	public static final String TEST_PASS = "tf.test-pass";
	
	public static final String TEST_USER_DEFAULT = "user1";
	public static final String TEST_PASS_DEFAULT = "pass1";

	@BeforeSuite
	public void beforeSuite() throws IOException {
		
		// Configure DataSource
		Properties props = new Properties();
		try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("mock-tomcat-jdbc.properties")) {
			props.load(is);

			// mock environment variables for test configurations
			props.setProperty(URL_KEY, System.getenv(props.getProperty(TEST_URL)));
			props.setProperty(USERNAME_KEY, props.getProperty(TEST_USER, TEST_USER_DEFAULT));
			props.setProperty(PASS_KEY, props.getProperty(TEST_PASS, TEST_PASS_DEFAULT));

			// override dialect and driver class to use hsqldb
			props.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
			props.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
			props.setProperty("hibernate.hbm2.ddl-auto", "create-drop");
			props.setProperty("hibernate.show_sql", "true");
			props.setProperty("hibernate.default_schema", "admin");
			
			// intialize Hibernate
			HibernateUtil.setDataSourceBuilder(new TomcatJDBCDataSourceBuilder(), props);
			HibernateUtil.initSessionFactory(props);
			
			TestDBLoader.load(props.getProperty(TEST_USER, TEST_USER_DEFAULT));

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
	}

	@AfterSuite
	public void afterSuite() {
		HibernateUtil.shutdown();
	}
}
