package com.revature.test.utils;
import com.revature.test.admin.pom.Login;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {
	private static Properties prop = new Properties();
	static {
		InputStream locProps = Login.class.getClassLoader()
				.getResourceAsStream("tests.properties");
		try {
			prop.load(locProps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * NOTE: to change between using local and hosted url change "urlBeingUsed" property
	 * in tests.properties - line 17
	 */
	private static String baseURL = prop.getProperty("urlBeingUsed");
	
	public static String getBaseURL() {
		return baseURL;
	}
	
}
