package com.revature.test.services;


import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.entity.TfClient;
import com.revature.entity.TfInterview;
import com.revature.services.InterviewService;
import com.revature.test.utils.Log;

public class InterviewServicesTest {

	private InterviewService service;
	private Properties prop;
	List<TfInterview> interviews;
	
	@BeforeClass
	public void initialize() {
		service = new InterviewService();
		prop = new Properties();
		interviews = service.getAllInterviews();
		try {
			FileInputStream propFile = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\database_entries.properties");
			prop.load(propFile);
			propFile.close();
		} catch(FileNotFoundException e) {
			Log.Log.error(e.getMessage());
		} catch (IOException e) {
			Log.Log.error(e.getMessage());
		}
	}
	
  @Test
  public void testInterviewGetByAssociate() {
	  Integer aId = Integer.parseInt(prop.getProperty("interview_associate_id"));
	  List<TfInterview> interviews = service.getInterviewsByAssociate(aId);
	  boolean result = true;
	  for(TfInterview tfi : interviews) {
		  result = tfi.getAssociate().getId().equals(aId);
		  if(result == false) {
			  break;
		  }
	  }
	  assertTrue(result);
  }

  @Test
  public void testInterviewGetById() {
	  Integer id = Integer.parseInt(prop.getProperty("interview_id"));
	  
	  TfInterview interview = service.getInterviewById(id);
	  assertNotNull(interview);
  }

  @Test
  public void testInterviewGetAll() {
	  assertNotNull(interviews);
	  assertTrue(!interviews.isEmpty());
  }

  //Disabling test until it can be properly stubbed to prevent inserting to database
  @Test(enabled=false)
  public void testInterviewCreate() {
	  TfInterview interview = new TfInterview();
	  interview.setAssociateFeedback("Unique string : associate feedback");
	  interview.setFlagReason("Unique string : flag reason");
	  interview.setJobDescription("SDET");
	  interview.setIsInterviewFlagged(1);
	  //this ends up false
	  boolean result = service.createInterview(interview);
	  assertTrue(result);
  }
   /*
    * this test creates a NullPointerException service.createInterview can't currently handle null
    * */
  @Test
  public void testInterviewCreateNull() {
	  boolean result = service.createInterview(null);
	  assertFalse(result);
  }

  /*
   * Not idempotent because old information gets in the way of new 
   * but there isnt a simple way to delete a Client at the moment so fix that
   * */
  @Test(enabled=false)//disabled until dao method can be properly stubbed.
  public void testInterviewUpdate() {
	  TfInterview interview = service.getInterviewById(0);
	  TfClient sampleClient = new TfClient();
	  sampleClient.setId(4004);
	  interview.setAssociateFeedback("Update A-Feedback");
	  interview.setClientFeedback("Update C-Feedback");
	  interview.setClient(sampleClient);
	  service.updateInterview(interview);
	  TfInterview newInterview = service.getInterviewById(0);
	  assertTrue(newInterview.getAssociateFeedback().equals("Update A-Feedback"));
	  assertTrue(newInterview.getClientFeedback().equals("Update C-Feedback"));
	  assertTrue(newInterview.getClient().getId() == 4000);
  }
}
