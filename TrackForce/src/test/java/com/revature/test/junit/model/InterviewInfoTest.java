package com.revature.test.junit.model;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Test;

import com.revature.model.AssociateInfo;
import com.revature.model.InterviewInfo;

/***
 * jUnit tests for InterviewInfo class in com.revature.model
 * @author David Kim
 * @since 6.18.06.11
 */
public class InterviewInfoTest {

	InterviewInfo info = new InterviewInfo();
	
	@Test
	public void testGetId() {
		info.setId(111);
		assertTrue(info.getId() == 111);
		assertFalse(info.getId() == 45);
	}
	
	@Test
	public void testGetTfAssociate() {
		info.setTfAssociate(new AssociateInfo());
		assertTrue(info.getTfAssociate() instanceof AssociateInfo);	
	}
	
	@Test
	public void testGetTfClientName() {
		info.setTfClientName("Client");
		assertTrue(info.getTfClientName().equals("Client"));
		assertFalse(info.getTfClientName().equals("Another Client"));
	}
	
	@Test
	public void testGetTypeId() {
		info.setTypeId(39);
		assertTrue(info.getTypeId() == 39);
	}
	
	@Test
	public void testGetTypeName() {
		info.setTypeName("type");
		assertTrue(info.getTypeName().equals("type"));
		assertFalse(info.getTypeName().equals("different"));
	}
	
	@Test
	public void testGetTfInterviewDate() {
		info.setTfInterviewDate(new Timestamp(12121212));
		assertTrue(info.getTfInterviewDate() instanceof Timestamp);
	}
	
	@Test
	public void testTfInterviewFeedback() {
		info.setTfInterviewFeedback("Interview went well.");
		assertTrue(info.getTfInterviewFeedback().equals("Interview went well."));
		assertFalse(info.getTfInterviewFeedback().equals("Interview went bad."));
	}
	
	@Test
	public void testCompareTo() {
		InterviewInfo info2 = new InterviewInfo();
		info.setId(89);
		info2.setId(80);
		assertTrue(info.compareTo(info2) == 9);
		assertFalse(info.compareTo(info2) == 6);
	}
	
	@Test
	public void testGetClientFeedback() {
		info.setClientFeedback("Excellent");
		assertTrue(info.getClientFeedback().equals("Excellent"));
		assertFalse(info.getClientFeedback().equals("Poor"));
	}
	
	@Test
	public void testGetJobDescription() {
		info.setJobDescription("Software Engineer");
		assertTrue(info.getJobDescription().equals("Software Engineer"));
		assertFalse(info.getJobDescription().equals("Systems Engineer"));
	}
	
	@Test
	public void testGetDateSalesIssued() {
		info.setDateSalesIssued(new Timestamp(151515));
		assertTrue(info.getDateSalesIssued() instanceof Timestamp);
	}
	
	@Test
	public void testGetDateAssociateIssued() {
		info.setDateAssociateIssued(new Timestamp(141414141414L));
		assertTrue(info.getDateAssociateIssued() instanceof Timestamp);
	}
	
	@Test
	public void testIsInterviewFlagged() {
		info.setIsInterviewFlagged(1);
		assertTrue(info.getIsInterviewFlagged() == 1);
		assertFalse(info.getIsInterviewFlagged() == 0);
	}
	
	@Test
	public void testGetFlagReason() {
		info.setFlagReason("Unknown");
		assertTrue(info.getFlagReason().equals("Unknown"));
		assertFalse(info.getFlagReason().equals("Valid"));
	}
	
	@Test
	public void testGetIsClientFeedbackVisible() {
		info.setIsClientFeedbackVisible(1);
		assertTrue(info.getIsClientFeedbackVisible() == 1);
		assertFalse(info.getIsClientFeedbackVisible() == 0);
	}
}
