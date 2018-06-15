package com.revature.test.junit.requestModel;

import static org.junit.Assert.*;
import org.junit.Test;

import com.revature.request.model.AssociateFromClient;

/***
 * jUnit tests for AssociateFromClient class in com.revature.request.model
 * @author David Kim
 *6.18.06.11
 */
public class AssociateFromClientTest {

	AssociateFromClient afc = new AssociateFromClient();
	
	@Test
	public void testGetId() {
		afc.setId(979);
		assertTrue(afc.getId() == 979);
		assertFalse(afc.getId() == 1001);
	}
	
	@Test
	public void testGetMkStatus() {
		afc.setMkStatus(1);
		assertTrue(afc.getMkStatus() == 1);
		assertFalse(afc.getMkStatus() == 0);
	}
	
	@Test
	public void testGetStartDateUnixTime() {
		afc.setStartDateUnixTime(1313131313131313L);
		assertTrue(afc.getStartDateUnixTime() == 1313131313131313L);
		assertFalse(afc.getStartDateUnixTime() == 1515151515151515L);
	}
	
	@Test
	public void testGetClientId() {
		afc.setClientId(503);
		assertTrue(afc.getClientId() == 503);
		assertFalse(afc.getClientId() == 512);
	}
}
