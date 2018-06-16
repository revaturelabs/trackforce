package com.revature.test.junit.entity;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.HashSet;

import org.junit.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;
import com.revature.entity.TfInterview;
import com.revature.entity.TfMarketingStatus;
import com.revature.entity.TfPlacement;

/**
 * Tests to test basic getter and setter functionality for TfAssociate
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfAssociateTest {

	TfAssociate tfassociate = new TfAssociate();
	
	@Test
	public void test2() {
		tfassociate.setFirstName("Bob");
		assertTrue(tfassociate.getFirstName().equals("Bob"));
		assertFalse(tfassociate.getFirstName().equals("bob"));
	}
	@Test
	public void test3() {
		tfassociate.setId(13);
		assertTrue(tfassociate.getId() == 13);
		assertFalse(tfassociate.getId() == 14);
	}
	@Test
	public void test4() {
		tfassociate.setLastName("Bobbert");
		assertTrue(tfassociate.getLastName().equals("Bobbert"));
		assertFalse(tfassociate.getLastName().equals("bobbert"));
	}
	@Test
	public void test5() {
		tfassociate.setBatch(new TfBatch());
		assertTrue(tfassociate.getBatch() instanceof TfBatch);
	}
	@Test
	public void test6() {
		tfassociate.setClient(new TfClient());
		assertTrue(tfassociate.getClient() instanceof TfClient);
	}
	@Test
	public void test7() {
		tfassociate.setClientStartDate(new Timestamp(1000L));
		assertTrue(tfassociate.getClientStartDate().getTime() == 1000L);
		assertFalse(tfassociate.getClientStartDate().getTime() == 2000L);
	}
	@Test
	public void test8() {
		tfassociate.setEndClient(new TfEndClient());
		assertTrue(tfassociate.getEndClient() instanceof TfEndClient);
	}
	@Test
	public void test9() {
		tfassociate.setInterview(new HashSet<TfInterview>());
		assertTrue(tfassociate.getInterview() instanceof HashSet);
	}
	@Test
	public void test10() {
		tfassociate.setMarketingStatus(new TfMarketingStatus());
		assertTrue(tfassociate.getMarketingStatus() instanceof TfMarketingStatus);
	}
	@Test
	public void test11() {
		tfassociate.setPlacement(new HashSet<TfPlacement>());
		assertTrue(tfassociate.getPlacement() instanceof HashSet);
	}

}
