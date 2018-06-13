//package com.revature.test.junit.entity;
//
//import static org.junit.Assert.assertFalse;
//import static org.testng.Assert.assertTrue;
//
//import java.sql.Timestamp;
//import java.util.HashSet;
//
//import org.junit.Test;
//
//import com.revature.entity.TfAssociate;
//import com.revature.entity.TfBatch;
//import com.revature.entity.TfClient;
//import com.revature.entity.TfEndClient;
//import com.revature.entity.TfInterview;
//import com.revature.entity.TfMarketingStatus;
//import com.revature.entity.TfPlacement;
//
///**
// * Tests to test basic getter and setter functionality for TfAssociate
// * @author Jesse
// * @Since 6.18.06.11
// */
//public class TfAssociateTest {
//
//	TfAssociate tfassociate = new TfAssociate();
//
//	@Test
//	public void test1() {
//		tfassociate.setIsApproved(1);
//		assertTrue(tfassociate.getIsApproved() == 1);
//		assertFalse(tfassociate.getIsApproved() == 0);
//	}
//	@Test
//	public void test2() {
//		tfassociate.setTfAssociateFirstName("Bob");
//		assertTrue(tfassociate.getTfAssociateFirstName().equals("Bob"));
//		assertFalse(tfassociate.getTfAssociateFirstName().equals("bob"));
//	}
//	@Test
//	public void test3() {
//		tfassociate.setTfAssociateId(13);
//		assertTrue(tfassociate.getTfAssociateId() == 13);
//		assertFalse(tfassociate.getTfAssociateId() == 14);
//	}
//	@Test
//	public void test4() {
//		tfassociate.setTfAssociateLastName("Bobbert");
//		assertTrue(tfassociate.getTfAssociateLastName().equals("Bobbert"));
//		assertFalse(tfassociate.getTfAssociateLastName().equals("bobbert"));
//	}
//	@Test
//	public void test5() {
//		tfassociate.setTfBatch(new TfBatch());
//		assertTrue(tfassociate.getTfBatch() instanceof TfBatch);
//	}
//	@Test
//	public void test6() {
//		tfassociate.setTfClient(new TfClient());
//		assertTrue(tfassociate.getTfClient() instanceof TfClient);
//	}
//	@Test
//	public void test7() {
//		tfassociate.setTfClientStartDate(new Timestamp(1000L));
//		assertTrue(tfassociate.getTfClientStartDate().getTime() == 1000L);
//		assertFalse(tfassociate.getTfClientStartDate().getTime() == 2000L);
//	}
//	@Test
//	public void test8() {
//		tfassociate.setTfEndClient(new TfEndClient());
//		assertTrue(tfassociate.getTfEndClient() instanceof TfEndClient);
//	}
//	@Test
//	public void test9() {
//		tfassociate.setTfInterviews(new HashSet<TfInterview>());
//		assertTrue(tfassociate.getTfInterviews() instanceof HashSet);
//	}
//	@Test
//	public void test10() {
//		tfassociate.setTfMarketingStatus(new TfMarketingStatus());
//		assertTrue(tfassociate.getTfMarketingStatus() instanceof TfMarketingStatus);
//	}
//	@Test
//	public void test11() {
//		tfassociate.setTfPlacements(new HashSet<TfPlacement>());
//		assertTrue(tfassociate.getTfPlacements() instanceof HashSet);
//	}
//
//}
