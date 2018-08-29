package com.revature.test.junit.entity;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.sql.Timestamp;

import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;
import com.revature.entity.TfInterview;
import com.revature.entity.TfInterviewType;

/**
 * Tests to test basic getter and setter functionality for TfInterviewTest
 * 
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfInterviewTest {
	TfAssociate associate = new TfAssociate();
	TfClient client = new TfClient();
	TfEndClient endClient = new TfEndClient();
	TfInterviewType interviewType = new TfInterviewType();
	Timestamp interviewDate = new Timestamp(1L);
	Timestamp dateSalesIssued = new Timestamp(2L);
	Timestamp dateAssociateIssued = new Timestamp(3L);

	TfInterview interview1 = new TfInterview(1, associate, client, endClient, interviewType, interviewDate,
			"associateFeedback", "questionGiven", "clientFeedback", "jobDescription", dateSalesIssued,
			dateAssociateIssued, 0, 0, "flagReason", 0);
	TfInterview interview2 = new TfInterview(1, associate, client, endClient, interviewType, interviewDate,
			"associateFeedback", "questionGiven", "clientFeedback", "jobDescription", dateSalesIssued,
			dateAssociateIssued, 0, 0, "flagReason", 0);
	TfInterview interview = new TfInterview();

	@Test
	public void testInterivewAssociateGetSet() {
		interview.setAssociate(new TfAssociate());
		assertTrue(interview.getAssociate() instanceof TfAssociate);
	}

	@Test
	public void testInterviewAssociateFeedbackGetSet() {
		interview.setAssociateFeedback("Great");
		assertTrue(interview.getAssociateFeedback().equals("Great"));
		assertFalse(interview.getAssociateFeedback().equals("great"));
	}

	@Test
	public void testInterviewClientGetSet() {
		interview.setClient(new TfClient());
		assertTrue(interview.getClient() instanceof TfClient);
	}

	@Test
	public void testInterviewClientFeedbackGetSet() {
		interview.setClientFeedback("Great");
		assertTrue(interview.getClientFeedback().equals("Great"));
		assertFalse(interview.getClientFeedback().equals("great"));
	}

	@Test
	public void testInterviewDateAssociateGetSet() {
		interview.setDateAssociateIssued(new Timestamp(5000L));
		assertTrue(interview.getDateAssociateIssued().getTime() == 5000L);
		assertFalse(interview.getDateAssociateIssued().getTime() == 200L);
	}

	@Test
	public void testInterviewDateSalesGetSet() {
		interview.setDateSalesIssued(new Timestamp(2356L));
		assertTrue(interview.getDateSalesIssued().getTime() == 2356L);
		assertFalse(interview.getDateSalesIssued().getTime() == 1235L);
	}

	@Test
	public void testInterviewEndClientGetSet() {
		interview.setEndClient(new TfEndClient());
		assertTrue(interview.getEndClient() instanceof TfEndClient);
	}

	@Test
	public void testFlagReasonGetSet() {
		interview.setFlagReason("Weak");
		assertTrue(interview.getFlagReason().equals("Weak"));
		assertFalse(interview.getFlagReason().equals("weak"));
	}

	@Test
	public void testInterviewDateGetSet() {
		interview.setInterviewDate(new Timestamp(5000L));
		assertTrue(interview.getInterviewDate().getTime() == 5000L);
		assertFalse(interview.getInterviewDate().getTime() == 4000L);
	}
	
	@Test
	public void testInterviewTypeGetSet() {
		interview.setInterviewType(new TfInterviewType());
		assertTrue(interview.getInterviewType() instanceof TfInterviewType);
		
	}

	@Test
	public void testInterviewClientFeedVisGetSet() {
		interview.setIsClientFeedbackVisible(1);
		assertTrue(interview.getIsClientFeedbackVisible() == 1);
		assertFalse(interview.getIsClientFeedbackVisible() == 0);
	}

	@Test
	public void testInterviewFlaggedGetSet() {
		interview.setIsInterviewFlagged(1);
		assertTrue(interview.getIsInterviewFlagged() == 1);
		assertFalse(interview.getIsInterviewFlagged() == 0);
	}

	@Test
	public void testInterviewJobDescGetSet() {
		interview.setJobDescription("Programmer");
		assertTrue(interview.getJobDescription().equals("Programmer"));
		assertFalse(interview.getJobDescription().equals("programmer"));
	}

	@Test
	public void testInterviewQuestionGetSet() {
		interview.setQuestionGiven("Why?");
		assertTrue(interview.getQuestionGiven().equals("Why?"));
		assertFalse(interview.getQuestionGiven().equals("why?"));
	}

	@Test
	public void testInterview24HRGetSet() {
		interview.setWas24HRNotice(1);
		assertTrue(interview.getWas24HRNotice() == 1);
		assertFalse(interview.getWas24HRNotice() == 0);
	}
	
	//Have to hard code SerialID for now as it is private static,
	//always verify this is using the correct SerialID as defined
	//in the TfInterview.java file
	@Test
	public void testInterviewSerialIDGet() {
		long sid = -4148475604579144144L;
		assertTrue(TfInterview.getSerialversionuid() == sid);
	}

	@Test
	public void testInterviewEquivalence() {
		assertTrue(interview1.equals(interview2));
		assertFalse(interview1.equals(new TfInterview()));
	}

	@Test
	public void testInterviewHashCode() {
		assertEquals(interview1.hashCode(), interview2.hashCode());
		assertNotEquals(interview1.hashCode(), new TfInterview());
	}
	
	@Test
	public void testInterviewToString() {
		assertEquals(interview1.toString(), interview2.toString());
		assertNotEquals(interview1.toString(), interview.toString());
	}
}
