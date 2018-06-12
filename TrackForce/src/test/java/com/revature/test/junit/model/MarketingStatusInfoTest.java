package com.revature.test.junit.model;

import static org.junit.Assert.*;
import org.junit.Test;

import com.revature.model.MarketingStatusInfo;


/***
 * jUnit test for MarketingStatusInfo class in com.revature.model
 * @author David Kim
 * @since 6.18.06.11
 */
public class MarketingStatusInfoTest {

	MarketingStatusInfo msInfo = new MarketingStatusInfo();
	
	@Test
	public void testGetId() {
		msInfo.setId(1023);
		assertTrue(msInfo.getId() == 1023);
		assertFalse(msInfo.getId() == 1453);
	}
	
	@Test 
	public void testGetName() {
		msInfo.setName("Olivia");
		assertTrue(msInfo.getName().equals("Olivia"));
		assertFalse(msInfo.getName().equals("Patricia"));
	}
	
	@Test
	public void testCompareTo() {
		MarketingStatusInfo msInfo2 = new MarketingStatusInfo();
		msInfo.setId(1023);
		msInfo2.setId(1188);
		assertTrue(msInfo.compareTo(msInfo2) == -165);
		assertFalse(msInfo.compareTo(msInfo2) == 165);
	}
	
}
