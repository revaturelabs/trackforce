package com.revature.test.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.revature.daoimpl.AssociateDaoImpl;
import com.revature.daoimpl.InterviewDaoImpl;
import com.revature.entity.TfBatch;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;
import com.revature.entity.TfInterview;
import com.revature.entity.TfMarketingStatus;
import com.revature.entity.TfPlacement;
import com.revature.entity.TfUser;

public class InterviewDAOTest {

	
  /**
   *  These tests depend on data in the full SQL script
   */

  // loads interviews for associate_ID 710	
  List <TfInterview> interviews = new InterviewDaoImpl().getInterviewsByAssociate(710);

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
	Assert.assertTrue(interviews.get(0).getWas24HRNotice()== 1);
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
  
  // List of all interviews
  List<TfInterview> allInterviews = new InterviewDaoImpl().getAllInterviews();
  @Test
  public void getAllInterviewsNotNull() {
	  Assert.assertNotEquals(allInterviews.size(), 0);
  }

  @Test
  public void getAllInterviewsHasCorrectSize() {
	  Assert.assertEquals(allInterviews.size(), 54);
  }
  
  @Test
  public void getAllInterviewsHasCorrectInterviews() {
	  Assert.assertNotEquals(allInterviews.get(0), new InterviewDaoImpl().getInterviewById(0));
  }

  /**
   * Tests for getInterviewById method  
   */

  @Test
  public void interviewDaoCangetInterviewByID() {
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
