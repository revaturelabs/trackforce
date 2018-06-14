package com.revature.test.junit.entity;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfMarketingStatus;

/**
 * Tests to test basic getter and setter functionality for TfMarketingStatus
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfMarketingStatusTest {

	TfMarketingStatus tfms = new TfMarketingStatus();
	
	@Test
	public void test1() {
		tfms.setAssociates(new HashSet<TfAssociate>());
		assertTrue(tfms.getAssociates() instanceof HashSet);
	}
	@Test
	public void test2() {
		tfms.setId(64);
		assertTrue(tfms.getId() == 64);
		assertFalse(tfms.getId() == 123);
	}
	@Test
	public void test3() {
		tfms.setName("MarketStatus");
		assertTrue(tfms.getName().equals("MarketStatus"));
		assertFalse(tfms.getName().equals("marketstatus"));
	}

}
