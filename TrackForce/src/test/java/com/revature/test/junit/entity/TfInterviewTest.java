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

public class TfInterviewTest {

	TfInterview interview = new TfInterview();
	
	@Test
	public void test1() {
		interview.setTfAssociate(new TfAssociate());
		assertTrue(interview.getTfAssociate() instanceof TfAssociate);
	}
	
	@Test
	public void test2() {
		interview.setTfAssociateFeedback("Great");
		assertTrue(interview.getTfAssociateFeedback().equals("Great"));
		assertFalse(interview.getTfAssociateFeedback().equals("great"));
	}
	
	@Test
	public void test3() {
		interview.setTfClient(new TfClient());
		assertTrue(interview.getTfClient() instanceof TfClient);
	}
	
	@Test
	public void test4() {
		interview.setTfClientFeedback("Great");
		assertTrue(interview.getTfClientFeedback().equals("Great"));
		assertFalse(interview.getTfClientFeedback().equals("great"));
	}
	
	@Test
	public void test5() {
		interview.setTfDateAssociateIssued(new Timestamp(5000L));
		assertTrue(interview.getTfDateAssociateIssued().getTime() == 5000L);
		assertFalse(interview.getTfDateAssociateIssued().getTime() == 200L);
	}
	
	@Test
	public void test6() {
		interview.setTfDateSalesIssued(new Timestamp(2356L));
		assertTrue(interview.getTfDateSalesIssued().getTime() == 2356L);
		assertFalse(interview.getTfDateSalesIssued().getTime() == 1235L);
	}

	@Test
	public void test7() {
		interview.setTfEndClient(new TfEndClient());
		assertTrue(interview.getTfEndClient() instanceof TfEndClient);
	}
	
	@Test
	public void test8() {
		interview.setTfFlagReason("Weak");
		assertTrue(interview.getTfFlagReason().equals("Weak"));
		assertFalse(interview.getTfFlagReason().equals("weak"));
	}
	
	@Test
	public void test9() {
		interview.setTfInterviewDate(new Timestamp(5000L));
		assertTrue(interview.getTfInterviewDate().getTime() == 5000L);
		assertFalse(interview.getTfInterviewDate().getTime() == 4000L);
	}
	
	@Test
	public void test10() {
		interview.setTfInterviewId(77);
		assertTrue(interview.getTfInterviewId() == 77);
		assertFalse(interview.getTfInterviewId() == 55);
	}
	
	@Test
	public void test11() {
		interview.setTfInterviewType(new TfInterviewType());
		assertTrue(interview.getTfInterviewType() instanceof TfInterviewType);
	}
	
	@Test
	public void test12() {
		interview.setTfIsClientFeedbackVisible(1);
		assertTrue(interview.getTfIsClientFeedbackVisible() == 1);
		assertFalse(interview.getTfIsClientFeedbackVisible() == 0);
	}
	
	@Test
	public void test13() {
		interview.setTfIsInterviewFlagged(1);
		assertTrue(interview.getTfIsInterviewFlagged() == 1);
		assertFalse(interview.getTfIsInterviewFlagged() == 0);
	}
	
	@Test
	public void test14() {
		interview.setTfJobDescription("Programmer");
		assertTrue(interview.getTfJobDescription().equals("Programmer"));
		assertFalse(interview.getTfJobDescription().equals("programmer"));
	}
	
	@Test
	public void test15() {
		interview.setTfQuestionGiven("Why?");
		assertTrue(interview.getTfQuestionGiven().equals("Why?"));
		assertFalse(interview.getTfQuestionGiven().equals("why?"));
	}
	
	@Test
	public void test16() {
		interview.setTfWas24HRNotice(1);
		assertTrue(interview.getTfWas24HRNotice() == 1);
		assertFalse(interview.getTfWas24HRNotice() == 0);
	}
}
