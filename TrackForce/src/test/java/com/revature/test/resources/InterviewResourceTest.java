package com.revature.test.resources;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfInterview;
import com.revature.entity.TfUser;
import com.revature.resources.InterviewResource;
import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;

import io.jsonwebtoken.Claims;


public class InterviewResourceTest {
  @Mock
  List<TfInterview> interviews;
  @Mock
  AssociateService as;
  @Mock
  BatchService bs;
  @Mock
  ClientService cs;
  @Mock
  CurriculumService cus;
  @Mock
  InterviewService is;
  @Mock
  TrainerService ts;
  @Mock
  UserService us;
  @Mock
  ResponseBuilder rb;
  @Mock
  Response r;
  @Mock
  TfInterview ti;
  @Mock
  TfAssociate ta;
  @Mock
  TfUser tu;
  @Mock 
  Claims c;
  
  @BeforeMethod
  protected void setup() {
	  MockitoAnnotations.initMocks(this);
	  interviews.clear();
  }
  
  @Test
  public void interviewResourceMock_TestAllPositive() throws IOException {
	  InterviewResource irMock = spy(new InterviewResource());
	  doReturn(true).when(irMock).canAccessInterview(anyString(), anyInt());
	  doReturn(true).when(irMock).authorized(anyString(), anyInt(), anyInt(), anyInt());
	  doReturn(is).when(irMock).getInterviewService();
	  interviews.add(new TfInterview());
	  when(is.getAllInterviews()).thenReturn(interviews);
	  when(interviews.isEmpty()).thenReturn(false);
	  irMock.getAllInterviews(anyString(), anyString());
	  verify(interviews).size();
	  verify(interviews).isEmpty();
	  
	  when(is.getInterviewsByAssociate(anyInt())).thenReturn(interviews);
	  doReturn(rb).when(irMock).responseStatus(Status.OK);
	  when(rb.entity(interviews)).thenReturn(rb);
	  irMock.getAllInterviews(anyString(), anyInt());
//	  verify(rb).build();

	  doReturn(as).when(irMock).getAssociateService();
	  when(as.getAssociate(anyInt())).thenReturn(ta);
	  doNothing().when(ti).setAssociate(ta);
	  doNothing().when(ti).setJobDescription(anyString());
	  doReturn(true).when(is).createInterview(ti);
	  irMock.createInterview(1, "", ti);
	  verify(is).createInterview(ti);
	  
	  when(is.getInterviewById(anyInt())).thenReturn(ti);
	  when(ti.getAssociate()).thenReturn(ta);
	  when(rb.entity(ti)).thenReturn(rb);
	  irMock.getInterviewById(anyString(), anyInt());
//	  verify(rb, times(2)).build(); //Checks for this test and a previous one
	  
	  when(is.updateInterview(ti)).thenReturn(true);
	  doReturn(rb).when(irMock).responseStatus(Status.ACCEPTED);
	  irMock.updateInterview(1, "", ti);
	  verify(irMock).responseStatus(Status.ACCEPTED); //Checks for this test and two previous ones
	  verify(rb, times(3)).build();
  }
  
  @Test
  public void interviewResourceGetAll_TestFailure() {
	  InterviewResource irMock = spy(new InterviewResource());
	  doReturn(false).when(irMock).authorized(anyString(), anyInt(), anyInt(), anyInt());
	  doReturn(rb).when(irMock).responseStatus(Status.UNAUTHORIZED);
	  doReturn(rb).when(rb).entity(anyString());
	  doReturn(r).when(rb).build();
	  irMock.getAllInterviews(anyString(), anyString());
	  verify(rb).build();
  }
  
  @Test
  public void interviewResourceGetAll_TestNoContent() throws IOException {
	  InterviewResource irMock = spy(new InterviewResource());
	  doReturn(true).when(irMock).authorized(anyString(), anyInt(), anyInt(), anyInt());
	  doReturn(is).when(irMock).getInterviewService();
	  when(is.getAllInterviews()).thenReturn(interviews);
	  doReturn(true).when(interviews).isEmpty();
	  doReturn(0).when(interviews).size();
	  doReturn(rb).when(irMock).responseStatus(Status.NO_CONTENT);
	  doReturn(rb).when(rb).entity(interviews);
	  doReturn(r).when(rb).build();
	  assertNotNull(irMock.getAllInterviews(anyString(), anyString()));
	  verify(irMock).responseStatus(Status.NO_CONTENT);
  }
  
  @Test
  public void interviewResourceCreate_TestFailure() {
	  InterviewResource irMock = spy(new InterviewResource());
	  doReturn(false).when(irMock).canAccessInterview(anyString(), anyInt());
	  assertEquals(irMock.createInterview(1, "", ti).getStatusInfo(), Status.UNAUTHORIZED);
  }
  
  @Test
  public void interviewResourceGetAll2_TestFailure() throws IOException {
	  InterviewResource irMock = spy(new InterviewResource());
	  doReturn(false).when(irMock).canAccessInterview(anyString(), anyInt());
	  assertEquals(irMock.getAllInterviews(anyString(), anyInt()).getStatusInfo(), Status.UNAUTHORIZED);
	  verify(irMock).jwtInvalidToken(anyString());
  }
  
  @Test
  public void interviewResourceById_TestFailure() throws IOException {
	  InterviewResource irMock = spy(new InterviewResource());
	  doReturn(is).when(irMock).getInterviewService();
	  when(is.getInterviewById(anyInt())).thenReturn(ti);
	  doReturn(ta).when(ti).getAssociate();
	  doReturn(1).when(ta).getId();
	  doReturn(false).when(irMock).canAccessInterview(anyString(), anyInt());
	  assertEquals(irMock.getInterviewById(anyString(), anyInt()).getStatusInfo(), Status.UNAUTHORIZED);
	  verify(irMock).jwtInvalidToken(anyString());
  }
  
  @Test
  public void interviewResourceUpdate_TestFailure() {
	  InterviewResource iMock = spy(new InterviewResource());
	  assertEquals(iMock.updateInterview(anyInt(), anyString(), eq(ti)).getStatusInfo(), Status.BAD_REQUEST);
	  
	  InterviewResource irMock = spy(new InterviewResource());
	  doReturn(false).when(irMock).canAccessInterview(anyString(), anyInt());
	  doReturn(ta).when(ti).getAssociate();
	  doReturn(1).when(ta).getId();
	  assertEquals(irMock.updateInterview(1, "", ti).getStatusInfo(), Status.UNAUTHORIZED);
  }
  
  @Test
  public void interviewResourceCanAccessInterview() {
	  //pass with valid roleId = 5
	  InterviewResource irMock5 = spy(new InterviewResource());
	  doReturn(c).when(irMock5).jwtProcessToken(anyString());
	  when(c.get(anyString())).thenReturn("5");
	  doReturn(5).when(irMock5).parseRole(anyString());
	  doReturn(us).when(irMock5).getUserService();
	  doReturn("five").when(c).getSubject();
	  when(us.getUser(anyString())).thenReturn(tu);
	  doReturn(as).when(irMock5).getAssociateService();
	  when(tu.getId()).thenReturn(44);
	  when(as.getAssociateByUserId(anyInt())).thenReturn(ta);
	  when(ta.getId()).thenReturn(44);
	  assertTrue(irMock5.canAccessInterview("match the numbers", 44));
	  
	  //Pass with valid roleId = 3
	  InterviewResource irMock3 = spy(new InterviewResource());
	  doReturn(c).when(irMock3).jwtProcessToken(anyString());
	  when(c.get(anyString())).thenReturn("3");
	  doReturn(3).when(irMock3).parseRole(anyString());
	  doReturn(us).when(irMock3).getUserService();
	  doReturn("three").when(c).getSubject();
	  assertTrue(irMock3.canAccessInterview("match the numbers", 44));
	  
	  //Fail with invalid role
	  InterviewResource irMock = spy(new InterviewResource());
	  doReturn(c).when(irMock).jwtProcessToken(anyString());
	  when(c.get(anyString())).thenReturn("35");
	  doReturn(35).when(irMock).parseRole(anyString());
	  doReturn(us).when(irMock).getUserService();
	  doReturn("thirty-five").when(c).getSubject();
	  when(us.getUser(anyString())).thenReturn(tu);
	  doReturn(as).when(irMock).getAssociateService();
	  when(tu.getId()).thenReturn(44);
	  when(as.getAssociateByUserId(anyInt())).thenReturn(ta);
	  when(ta.getId()).thenReturn(44);
	  assertFalse(irMock.canAccessInterview("match the numbers", 44));
	  
	  //fail with null Claims
	  InterviewResource badClaim = spy(new InterviewResource());
	  doReturn(null).when(badClaim).jwtProcessToken(anyString());
	  assertFalse(badClaim.canAccessInterview(anyString(), anyInt()));
	  
	  //fail with NumberFormatException
	  InterviewResource nfeMock = spy(new InterviewResource());
	  doReturn(c).when(nfeMock).jwtProcessToken(anyString());
	  doReturn("exception").when(c).get(anyString());
	  assertFalse(nfeMock.canAccessInterview(anyString(), anyInt()));
  }
  
  @Test
  public void interviewResourceUtilsTest() {
	  //Tests the Service getters for code coverage
	  InterviewResource ir = new InterviewResource();
	  assertNotNull(ir.getAssociateService()); //just exist
	  assertNotNull(ir.getInterviewService()); //just exist
	  assertNotNull(ir.getUserService()); //just exist
	  assertNull(ir.jwtProcessToken("notAToken"));//null on invalid token
	  assertFalse(ir.authorized("notAToken", 1,3)); //not authorized
	  assertEquals(ir.parseRole("345"),new Integer(345)); //tests parseInt
  }
}
