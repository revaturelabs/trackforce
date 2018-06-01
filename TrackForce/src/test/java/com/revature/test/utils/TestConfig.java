package com.revature.test.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.revature.test.admin.pom.Login;

<<<<<<< HEAD
	//private static String baseURL = "http://localhost:4200";
	//Static website
	//private static String baseURL = "http://52.207.66.231:4200";
	private static String baseURL = "http://34.227.178.103:8090/NGTrackForce";
=======
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
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
	
	public static String getBaseURL() {
		return baseURL;
	}
	
}
