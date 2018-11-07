package com.revature.test.dao;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.daoimpl.InterviewDaoImpl;
import com.revature.entity.TfInterview;

public class InterviewDAOTest {

	private static final String INTEGER_CLASS_SIMPLE_NAME = "Integer";
	// loads interviews for associate_ID 710
	List<TfInterview> interviews = null;
	// List of all interviews
	List<TfInterview> allInterviews = null;

	/**
	 * BeforeTest run before each Test to initialize interview lists
	 */
	@BeforeTest
	public void setup() {
		if (interviews == null) {
			interviews = new InterviewDaoImpl().getInterviewsByAssociate(710);
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
		Assert.assertEquals(interviews.get(0).getAssociateFeedback(), "Prepared");
	}

	@Test
	public void interviewDaoReturnsCorrectClienteFeedback() {
		Assert.assertEquals(interviews.get(0).getClientFeedback(), "nondescript");
	}

	@Test
	public void interviewDaoReturnsCorrectInterviewID() {
		Assert.assertTrue(interviews.get(0).getId() == 0);
	}

	@Test
	public void interviewDaoReturnsCorrectTF_flag() {
		Assert.assertEquals(interviews.get(0).getFlagReason(), "No Reason");
	}

	@Test
	public void interviewDaoReturnsCorrectFlagged() {
		Assert.assertTrue(interviews.get(0).getIsInterviewFlagged() == 1);
	}

	@Test(dependsOnMethods = "interviewDaoReturnsCorrectAssociateFeedback")
	public void interviewDaoReturnsCorrectQuestions() {
		Assert.assertEquals(interviews.get(0).getQuestionGiven(), "Questions...");
	}

	@Test
	public void interviewDaoReturnsCorrectNoticeStatus() {
		Assert.assertTrue(interviews.get(0).getWas24HRNotice() == 1);
	}

	@Test
	public void interviewDaoReturnsCorrectClient() {
		Assert.assertTrue(interviews.get(0).getClient().getId() == 577);
	}

	@Test
	public void interviewDaoReturnsCorrectEndClient() {
		Assert.assertTrue(interviews.get(0).getEndClient().getId() == 1251);
	}

	@Test
	public void interviewDaoReturnsCorrectType() {
		Assert.assertTrue(interviews.get(0).getInterviewType().getId() == 3);
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
		Assert.assertTrue(new InterviewDaoImpl().getInterviewById(0).getAssociate().getId() == 710);
	}

	/**
	 * Tests for updateInterview method
	 */
	@Test
	public void interviewDaoCanUpdateValidInterview() {

		Assert.assertTrue(new InterviewDaoImpl().updateInterview(new InterviewDaoImpl().getInterviewById(2)));
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void interviewDaoCannotUpdateInvalidInterview() {
		new InterviewDaoImpl().updateInterview(new InterviewDaoImpl().getInterviewById(234565213));
	}

	/**
	 * Tests for createInterview method
	 */
	@Test(expectedExceptions = NullPointerException.class)
	public void interviewDaoCannotCreateDuplicateInterview() {
		Assert.assertTrue(new InterviewDaoImpl().createInterview((new InterviewDaoImpl().getInterviewById(3))));
	}

}
