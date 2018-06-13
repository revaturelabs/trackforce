//package com.revature.test.junit.requestModel;
//
//import static org.junit.Assert.assertFalse;
//import static org.testng.Assert.assertTrue;
//
//import org.junit.Test;
//
//import com.revature.request.model.InterviewFromClient;
//
///**
// * Tests to test basic getter and setter functionality for InterviewFromClient
// * @author Jesse
// * @Since 6.18.06.11
// */
//public class InterviewFromClientTest {
//
//	InterviewFromClient ifc = new InterviewFromClient();
//
//	@Test
//	public void test1() {
//		ifc.setAssociateFeedback("Strong");
//		assertTrue(ifc.getAssociateFeedback().equals("Strong"));
//		assertFalse(ifc.getAssociateFeedback().equals("strong"));
//	}
//
//	@Test
//	public void test2() {
//		ifc.setClientFeedback("Strong");
//		assertTrue(ifc.getClientFeedback().equals("Strong"));
//		assertFalse(ifc.getClientFeedback().equals("STRONG"));
//	}
//
//	@Test
//	public void test3() {
//		ifc.setClientId(1);
//		assertTrue(ifc.getClientId() == 1);
//		assertFalse(ifc.getClientId() == 0);
//	}
//
//	@Test
//	public void test4() {
//		ifc.setDateAssociateIssued(5000L);
//		assertTrue(ifc.getDateAssociateIssued() == 5000L);
//		assertFalse(ifc.getDateAssociateIssued() == 4000L);
//	}
//
//	@Test
//	public void test5() {
//		ifc.setDateSalesTeamIssued(5000L);
//		assertTrue(ifc.getDateSalesTeamIssued() == 5000L);
//		assertFalse(ifc.getDateSalesTeamIssued() == 1000L);
//	}
//
//	@Test
//	public void test6() {
//		ifc.setEndClientId("Revature");
//		assertTrue(ifc.getEndClientId().equals("Revature"));
//		assertFalse(ifc.getEndClientId().equals("revature"));
//	}
//
//	@Test
//	public void test7() {
//		ifc.setFlagAlert("Warn");
//		assertTrue(ifc.getFlagAlert().equals("Warn"));
//		assertFalse(ifc.getFlagAlert().equals("Info"));
//	}
//
//	@Test
//	public void test8() {
//		ifc.setInterviewDate(9000L);
//		assertTrue(ifc.getInterviewDate() == 9000L);
//		assertFalse(ifc.getInterviewDate() == 90L);
//	}
//
//	@Test
//	public void test9() {
//		ifc.setIntervieweId(61);
//		assertTrue(ifc.getInterviewId() == 61);
//		assertFalse(ifc.getInterviewId() == 16);
//	}
//
//	@Test
//	public void test10() {
//		ifc.setInterviewFeedback("Did Well");
//		assertTrue(ifc.getInterviewFeedback().equals("Did Well"));
//		assertFalse(ifc.getInterviewFeedback().equals("didwell"));
//	}
//
//	@Test
//	public void test11() {
//		ifc.setJobDescription("Programmer");
//		assertTrue(ifc.getJobDescription().equals("Programmer"));
//		assertFalse(ifc.getJobDescription().equals("programmer"));
//	}
//
//	@Test
//	public void test12() {
//		ifc.setQuestions("Why?");
//		assertTrue(ifc.getQuestions().equals("Why?"));
//		assertFalse(ifc.getQuestions().equals("How?"));
//	}
//
//	@Test
//	public void test13() {
//		ifc.setReasonForFlag("Broken");
//		assertTrue(ifc.getReasonForFlag().equals("Broken"));
//		assertFalse(ifc.getReasonForFlag().equals("broken"));
//	}
//
//	@Test
//	public void test14() {
//		ifc.setTypeId(-10);
//		assertTrue(ifc.getTypeId() == -10);
//		assertFalse(ifc.getTypeId() == 10);
//	}
//
//	@Test
//	public void test15() {
//		ifc.setWas24HRNotice(-1);
//		assertTrue(ifc.getWas24HRNotice() == -1);
//		assertFalse(ifc.getWas24HRNotice() == 1);
//		ifc.setWas24HRNotice(10000);
//		assertTrue(ifc.getWas24HRNotice() == 10000);
//	}
//}
