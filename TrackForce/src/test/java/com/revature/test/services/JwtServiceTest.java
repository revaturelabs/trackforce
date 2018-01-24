package com.revature.test.services;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.services.JWTService;

public class JwtServiceTest {
	private static JWTService jwt;
	private static String adminToken;
	private static String vpToken;
	
  @BeforeTest
  public void beforeTest() {
	  jwt = new JWTService();
	  adminToken = jwt.createToken("TestAdmin");
	  vpToken = jwt.createToken("TestVicePresident");
  }

  @AfterTest
  public void afterTest() {
  }

  @Test(priority=1)
  public void createTokenTest() {
	  String token = jwt.createToken("Tester");
	  assertNotNull(token);
  }
  
  @Test(enabled=true)
  public void isAdminTest() throws IOException {
	  boolean status = false;
	  
		status = jwt.isAdmin(adminToken);
	  
	  assertTrue(status);  
	  
  }
  
  @Test()
  public void isAssociateTest() throws IOException {
	  boolean status = true;
	  
		status = jwt.isAssociate(vpToken);

	  
	  assertFalse(status);  
	  
  }
  
  @Test()
  public void isTokenExpiredTest() {
	  Date date = jwt.getExpirationDateFromToken(adminToken);
	  
	  assertNotNull(date);
  }
}
