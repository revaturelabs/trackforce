package com.revature.test.services;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assume;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.services.JWTService;
import com.revature.test.utils.Log;

public class JWTServicesTest {

  private JWTService service;
  Properties prop;
  
  @BeforeClass
  public void init() {
	service = new JWTService();
	prop = new Properties();
	try {
		FileInputStream propFile = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\database_entries.properties");
		prop.load(propFile);
		propFile.close();
	} catch(FileNotFoundException e) {
		Log.Log.error(e.getMessage());
	} catch (IOException e) {
		Log.Log.error(e.getMessage());
	}
  }
	
  @Test
  public void testJWTValidateToken() {
	  //boolean validateToken = service.validateToken();
  }

  @Test
  public void testJWTCreateToken() {

  }

  @Test
  public void testJWTProcessToken() {
	  
  }

  @Test
  public void testJWTGetExpirationDate() {

  }

  @Test
  public void testJWTGetKey() {
	  Assume.assumeTrue(System.getenv("KEY") != null);
	  String key = JWTService.getKey();
	  assertNotNull(key);
	  
	  Assume.assumeTrue(System.getenv("KEY") == null);
	  key = JWTService.getKey();
	  assertEquals(key, "trackforcekey");
  }

  @Test
  public void testJWTInvalidTokenBody() {

  }
}
