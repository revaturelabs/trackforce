package com.revature.test.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.revature.test.pom.Login;
import com.revature.utils.EnvManager;


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
	private static String baseURL;
	
	//11/5/2018 Courie Changed this to use the URL from Mussab's EnvManager class instead 
	//of using the hard-coded test.properties urlBeingUsed
	public static String getBaseURL() {
		if(baseURL == null) {
			baseURL = EnvManager.NGTrackForce_URL;
		}
		return baseURL;
	}
	
}
