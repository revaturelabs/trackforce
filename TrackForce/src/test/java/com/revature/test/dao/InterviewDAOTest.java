package com.revature.test.dao;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.daoimpl.InterviewDaoImpl;
import com.revature.entity.TfInterview;

/** Test class for testing InterviewDAOImpl
 * 
 * Danger of false negatives in the case of database changes.
 * 
 * Directly refers to existent entries in the database. Be warned that any
 * change in the database may very well cause tests to fail despite the DAO 
 * working just fine.
 */
public class InterviewDAOTest {
	// loads interviews for associate_ID 104905
	List<TfInterview> interviews = null;
	// List of all interviews
	List<TfInterview> allInterviews = null;

	/**
	 * BeforeTest run before each Test to initialize interview lists
	 */
	@BeforeTest
	public void setup() {
		if (interviews == null) {
			interviews = new InterviewDaoImpl().getInterviewsByAssociate(104905);
		}
		if (allInterviews == null) {
			allInterviews = new InterviewDaoImpl().getAllInterviews();
		}

	}

	/**
	 * These tests depend on data in the full SQL script
	 */

	// Tests for getInterviewsByAssociate method
	@Test
	public void interviewDaoGetInterviewByAssociateReturnsCorrectListSize() {
		Assert.assertEquals(interviews.size(), 1);
	}

	@Test
	public void interviewDaoReturnsCorrectAssociateFeedback() {
		Assert.assertEquals(interviews.get(0).getAssociateFeedback(), "Interview was great.");
	}

	@Test
	public void interviewDaoReturnsCorrectClienteFeedback() {
		Assert.assertEquals(interviews.get(0).getClientFeedback(), null);
	}

	@Test
	public void interviewDaoReturnsCorrectInterviewID() {
		Assert.assertTrue(interviews.get(0).getId() == 100004);
	}

	@Test
	public void interviewDaoReturnsCorrectTF_flag() {
		Assert.assertEquals(interviews.get(0).getFlagReason(), null);
	}

	@Test
	public void interviewDaoReturnsCorrectFlagged() {
		Assert.assertTrue(interviews.get(0).getIsInterviewFlagged() == 0);
	}

	@Test(dependsOnMethods = "interviewDaoReturnsCorrectAssociateFeedback")
	public void interviewDaoReturnsCorrectQuestions() {
		Assert.assertEquals(interviews.get(0).getQuestionGiven(), "What is test automation?");
	}

	@Test
	public void interviewDaoReturnsCorrectNoticeStatus() {
		Assert.assertTrue(interviews.get(0).getWas24HRNotice() == 1);
	}

	@Test
	public void interviewDaoReturnsCorrectClient() {
		Assert.assertTrue(interviews.get(0).getClient().getId() == 6);
	}

	@Test
	public void interviewDaoReturnsCorrectEndClient() {
		Assert.assertTrue(interviews.get(0).getEndClient() == null);
	}

	@Test
	public void interviewDaoReturnsCorrectType() {
		Assert.assertTrue(interviews.get(0).getInterviewType().getId() == 1);
	}
	/**
	 * Tests for getAllInterviews method
	 */

	@Test
	public void getAllInterviewsNotNull() {
		Assert.assertNotEquals(allInterviews.size(), 0);
	}

	@Test
	public void getAllInterviewsHasCorrectInterviews() {
		Assert.assertNotEquals(allInterviews.get(0), new InterviewDaoImpl().getInterviewById(0));
	}

	/**
	 * Tests for getInterviewById method
	 */
	@Test
	public void interviewDaoCanGetInterviewByID() {
		Assert.assertTrue(new InterviewDaoImpl().getInterviewById(100004).getAssociate().getId() == 104905);
	}

	/**
	 * Tests for updateInterview method
	 */
	@Test
	public void interviewDaoCanUpdateValidInterview() {

		Assert.assertTrue(new InterviewDaoImpl().updateInterview(new InterviewDaoImpl().getInterviewById(100061)));
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void interviewDaoCannotUpdateInvalidInterview() {
		new InterviewDaoImpl().updateInterview(new InterviewDaoImpl().getInterviewById(234565213));
	}

	/**
	 * Tests for createInterview method
	 */
	@Test
	public void interviewDaoCannotCreateDuplicateInterview() {
		Assert.assertFalse(new InterviewDaoImpl().createInterview((new InterviewDaoImpl().getInterviewById(100004))));
	}
	@Test
	public void interviewDaoCanCreateInterview() {
		Assert.assertTrue(new InterviewDaoImpl().createInterview(new TfInterview(54,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null)));
	}

}
