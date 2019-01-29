package com.revature.test.orm.util;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;


import com.revature.utils.PasswordStorage;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;
import com.revature.utils.PasswordStorage.InvalidHashException;

public class PasswordStorageTest {
	
	/**
	 * Exception is properly thrown
	 * @author Jesse
	 * @since 06.18.06.15
	 */
	@Test(expectedExceptions = InvalidHashException.class)
	public void testHashException() throws InvalidHashException {
		throw new InvalidHashException("Invalid");
	}
	
	/**
	 * Exception is properly thrown
	 * @author Jesse
	 * @since 06.18.06.15
	 */
	@Test(expectedExceptions = CannotPerformOperationException.class)
	public void testOperationException() throws CannotPerformOperationException {
		throw new CannotPerformOperationException("Cannot Perform");
	}
	
	/**
	 * Test to ensure that the method returns a string and that the value passed in actually
	 * becomes hashed and is not the original string.
	 * @author Jesse
	 * @since 06.18.06.15
	 * @throws CannotPerformOperationException
	 */
	@Test
	public void testCreateHashString() throws CannotPerformOperationException {
		assertTrue(PasswordStorage.createHash("p4ssword") instanceof String);
		assertFalse(PasswordStorage.createHash("p4ssword").equals("p4ssword"));

	}
	
	/**
	 * Test to ensure that the method returns a string and that the value passed in actually
	 * becomes hashed and is not the original string.
	 * @author Jesse
	 * @since 06.18.06.15
	 * @throws CannotPerformOperationException
	 */
	@Test
	public void testCreateHashChar() throws CannotPerformOperationException {
		assertTrue(PasswordStorage.createHash("password".toCharArray()) instanceof String);
		System.out.println(PasswordStorage.createHash("password".toCharArray()));
		assertFalse(PasswordStorage.createHash("password".toCharArray()).equals("password"));
	}
	
	/**
	 * Test to ensure that passing in a string and the correct hash value will return true,
	 * and that an inappropriate hash value will return false.
	 * @author Jesse
	 * @since 06.18.06.15
	 * @throws CannotPerformOperationException
	 * @throws InvalidHashException
	 */
	@Test
	public void testVerifyPasswordString() throws CannotPerformOperationException, InvalidHashException {
		assertTrue(PasswordStorage.verifyPassword("password", PasswordStorage.createHash("password")));
		try{
			PasswordStorage.verifyPassword("password", "password");
			assertFalse(true);
		} catch (InvalidHashException ihe) {
			assertTrue(true);
		}
		try {
			PasswordStorage.verifyPassword("password", "password");
			fail();
		} catch (InvalidHashException ihe) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test to see what multiple values will be verified or not. Tests to ensure that the multiple
	 * checks along the method will catch the appropriate exceptions and handle them accordingly
	 * @author Jesse
	 * @since 06.18.06.15
	 * @throws CannotPerformOperationException
	 * @throws InvalidHashException
	 */
	@Test
	public void testVerifyPasswordChar() throws CannotPerformOperationException, InvalidHashException {
		assertTrue(PasswordStorage.verifyPassword("password".toCharArray(), PasswordStorage.createHash("password")));
		try{
			PasswordStorage.verifyPassword("password".toCharArray(), "password");
			fail();
		} catch (InvalidHashException ihe) {
			assertTrue(true);
		}
		try{
			PasswordStorage.verifyPassword("password".toCharArray(), PasswordStorage.createHash("password").replaceAll("sha1", "sha2"));
			fail();
		} catch(CannotPerformOperationException cpoe) {
			assertTrue(true);
		}
		try {
			PasswordStorage.verifyPassword("password", "sha1:SixtyFour:18:K5n8bK3lr5XnOOUIrcCKEPdHsPA2faHa:pQ+rq20xQCyY6afckT0Od4Rg");
			fail();
		} catch (InvalidHashException ihe) {
			assertTrue(true);
		}
		try {
			PasswordStorage.verifyPassword("password", "sha1:64000:0:K5n8bK3lr5XnOOUIrcCKEPdHsPA2faHa:pQ+rq20xQCyY6afckT0Od4Rg");
			fail();
		} catch (InvalidHashException ihe) {
			assertTrue(true);
		}
		try {
			PasswordStorage.verifyPassword("password", "sha1:64000:18:Penguins:Penguins");
			fail();
		} catch (InvalidHashException iae) {
			assertTrue(true);
		}
	}
}
