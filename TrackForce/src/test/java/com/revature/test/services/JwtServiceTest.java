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
  public void isAdminTest() {
	  boolean status = false;
	  
	  try {
		status = jwt.isAdmin(adminToken);
	  } 
	  catch (IOException e) {
		
		e.printStackTrace();
	  }
	  
	  assertTrue(status);  
	  
  }
  
  @Test(enabled=false)
  public void isAssociateTest() {
	  boolean status = true;
	  
	  try {
		status = jwt.isAssociate(vpToken);
	  } 
	  catch (IOException e) {
		
		e.printStackTrace();
	  }
	  
	  assertFalse(status);  
	  
  }
  
  @Test(enabled=false)
  public void isTokenExpiredTest() {
	  Date date = jwt.getExpirationDateFromToken(adminToken);
	  
	  assertNotNull(date);
  }
}
