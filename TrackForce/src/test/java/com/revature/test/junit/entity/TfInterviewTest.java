package com.revature.test.junit.entity;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;
import com.revature.entity.TfInterview;
import com.revature.entity.TfInterviewType;

/**
 * Tests to test basic getter and setter functionality for TfInterviewTest
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfInterviewTest {

	TfInterview interview = new TfInterview();
	
	@Test
	public void test1() {
		interview.setAssociate(new TfAssociate());
		assertTrue(interview.getAssociate() instanceof TfAssociate);
	}
	
	@Test
	public void test2() {
		interview.setAssociateFeedback("Great");
		assertTrue(interview.getAssociateFeedback().equals("Great"));
		assertFalse(interview.getAssociateFeedback().equals("great"));
	}
	
	@Test
	public void test3() {
		interview.setClient(new TfClient());
		assertTrue(interview.getClient() instanceof TfClient);
	}
	
	@Test
	public void test4() {
		interview.setClientFeedback("Great");
		assertTrue(interview.getClientFeedback().equals("Great"));
		assertFalse(interview.getClientFeedback().equals("great"));
	}
	
	@Test
	public void test5() {
		interview.setDateAssociateIssued(new Timestamp(5000L));
		assertTrue(interview.getDateAssociateIssued().getTime() == 5000L);
		assertFalse(interview.getDateAssociateIssued().getTime() == 200L);
	}
	
	@Test
	public void test6() {
		interview.setDateSalesIssued(new Timestamp(2356L));
		assertTrue(interview.getDateSalesIssued().getTime() == 2356L);
		assertFalse(interview.getDateSalesIssued().getTime() == 1235L);
	}

	@Test
	public void test7() {
		interview.setEndClient(new TfEndClient());
		assertTrue(interview.getEndClient() instanceof TfEndClient);
	}
	
	@Test
	public void test8() {
		interview.setFlagReason("Weak");
		assertTrue(interview.getFlagReason().equals("Weak"));
		assertFalse(interview.getFlagReason().equals("weak"));
	}
	
	@Test
	public void test9() {
		interview.setInterviewDate(new Timestamp(5000L));
		assertTrue(interview.getInterviewDate().getTime() == 5000L);
		assertFalse(interview.getInterviewDate().getTime() == 4000L);
	}
	
	@Test
	public void test10() {
		interview.setId(77);
		assertTrue(interview.getId() == 77);
		assertFalse(interview.getId() == 55);
	}
	
	@Test
	public void test11() {
		interview.setInterviewType(new TfInterviewType());
		assertTrue(interview.getInterviewType() instanceof TfInterviewType);
	}
	
	@Test
	public void test12() {
		interview.setIsClientFeedbackVisible(1);
		assertTrue(interview.getIsClientFeedbackVisible() == 1);
		assertFalse(interview.getIsClientFeedbackVisible() == 0);
	}
	
	@Test
	public void test13() {
		interview.setIsInterviewFlagged(1);
		assertTrue(interview.getIsInterviewFlagged() == 1);
		assertFalse(interview.getIsInterviewFlagged() == 0);
	}
	
	@Test
	public void test14() {
		interview.setJobDescription("Programmer");
		assertTrue(interview.getJobDescription().equals("Programmer"));
		assertFalse(interview.getJobDescription().equals("programmer"));
	}
	
	@Test
	public void test15() {
		interview.setQuestionGiven("Why?");
		assertTrue(interview.getQuestionGiven().equals("Why?"));
		assertFalse(interview.getQuestionGiven().equals("why?"));
	}
	
	@Test
	public void test16() {
		interview.setWas24HRNotice(1);
		assertTrue(interview.getWas24HRNotice() == 1);
		assertFalse(interview.getWas24HRNotice() == 0);
	}
}
