package com.revature.test.services;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.services.JWTService;
import com.revature.test.utils.Log;

import io.jsonwebtoken.Claims;

public class JWTServicesTest {

	private JWTService service;
	Properties prop;
	static String token;

	@BeforeClass
	public void init() {
		service = new JWTService();
		prop = new Properties();
		try {
			FileInputStream propFile = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\database_entries.properties");
			prop.load(propFile);
			propFile.close();
		} catch (FileNotFoundException e) {
			Log.Log.error(e.getMessage());
		} catch (IOException e) {
			Log.Log.error(e.getMessage());
		}
	}

	@Test(dependsOnMethods = { "testJWTCreateToken" })
	public void testJWTValidateToken() {
		String key = JWTService.getKey();
		JWTService serv = new JWTService();
		assertFalse(serv.validateToken(key));//default key (invalid) with no env variable set
		assertTrue(serv.validateToken(token));
	}

	@Test(priority=0)
	public void testJWTCreateToken() {
		token = JWTService.createToken("cyril", 5);//associate "cyril"
		assertNotNull(token);

	}

	@Test(dependsOnMethods = { "testJWTCreateToken" })
	public void testJWTProcessToken() {
		Claims payload = JWTService.processToken(token);
		assertNotNull(payload);
	}

	@Test(dependsOnMethods = { "testJWTCreateToken" })
	public void testJWTGetExpirationDate() {
		Date exp = JWTService.getExpirationDateFromToken(token);
		assertNotNull(exp);
	}

	@Test
	public void testJWTGetKey() {
		if (System.getenv("KEY") == null) {
			String key = JWTService.getKey();
			assertNotNull(key);
		} else {

			String key = JWTService.getKey();
			assertEquals(key, "trackforcekey");
		}
	}

	@Test(dependsOnMethods = { "testJWTCreateToken" })
	public void testJWTInvalidTokenBody() {
		JWTService serv = new JWTService();
		assertFalse(serv.validateToken(null));
		assertFalse(serv.validateToken(JWTService.getKey()));
	}
}