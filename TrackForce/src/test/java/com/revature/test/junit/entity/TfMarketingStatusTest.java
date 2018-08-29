package com.revature.test.junit.entity;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfMarketingStatus;

/**
 * Tests to test basic getter and setter functionality for TfMarketingStatus
 * 
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfMarketingStatusTest {
	Set<TfAssociate> associates = new HashSet<>();

	TfMarketingStatus status1 = new TfMarketingStatus(1, "name", associates);
	TfMarketingStatus status2 = new TfMarketingStatus(1, "name", associates);
	TfMarketingStatus tfms = new TfMarketingStatus();

	@Test
	public void testMarketStatusAssociatesGetSet() {
		tfms.setAssociates(new HashSet<TfAssociate>());
		assertTrue(tfms.getAssociates() instanceof HashSet);
	}

	@Test
	public void testMarketStatusIDGetSet() {
		tfms.setId(64);
		assertTrue(tfms.getId() == 64);
		assertFalse(tfms.getId() == 123);
	}

	@Test
	public void testMarketStatusNameGetSet() {
		tfms.setName("MarketStatus");
		assertTrue(tfms.getName().equals("MarketStatus"));
		assertFalse(tfms.getName().equals("marketstatus"));
	}
	
	//Have to hard code SerialID for now as it is private static,
	//always verify this is using the correct SerialID as defined
	//in the TfMarketingStatus.java file
	@Test
	public void testMarketStatusSerialIDGet() {
		long sid = -1638800519652509525L;
		assertTrue(TfMarketingStatus.getSerialversionuid() == sid);
	}

	@Test
	public void testMarketStatusEquivalence() {
		assertTrue(status1.equals(status2));
		assertFalse(status1.equals(new TfMarketingStatus()));
	}

	@Test
	public void testMarketStatusHashCode() {
		assertEquals(status1.hashCode(), status2.hashCode());
		assertNotEquals(status1.hashCode(), new TfMarketingStatus().hashCode());
	}
	
	@Test
	public void testMarketStatusToString() {
		assertEquals(status1.toString(), status2.toString());
		assertNotEquals(status1.toString(), tfms.toString());
	}

}
