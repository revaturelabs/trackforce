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
		tfms.setTfAssociates(new HashSet<TfAssociate>());
		assertTrue(tfms.getTfAssociates() instanceof HashSet);
	}
	@Test
	public void test2() {
		tfms.setTfMarketingStatusId(64);
		assertTrue(tfms.getTfMarketingStatusId() == 64);
		assertFalse(tfms.getTfMarketingStatusId() == 123);
	}
	@Test
	public void test3() {
		tfms.setTfMarketingStatusName("MarketStatus");
		assertTrue(tfms.getTfMarketingStatusName().equals("MarketStatus"));
		assertFalse(tfms.getTfMarketingStatusName().equals("marketstatus"));
	}

}
