package com.revature.test.resources;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.revature.criteria.GraphedCriteriaResult;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;
import com.revature.entity.TfUser;
import com.revature.resources.AssociateResource;
import com.revature.services.AssociateService;
import com.revature.services.ClientService;
import com.revature.services.MarketingStatusService;

import io.jsonwebtoken.Claims;

public class AssociateResourceTest {
	@Mock
	List<TfAssociate> lta;
	@Mock
	AssociateService as;
	@Mock
	ClientService cs;
	@Mock
	MarketingStatusService mss;
	@Mock 
	Claims payload;
	@Mock
	ResponseBuilder rb;
	@Mock 
	Response r;
	@Mock
	JSONObject jo;
	@Mock
	ArrayList<Integer> li;
	@Mock 
	HashMap<String, Integer> hmsi;
	@Mock
	LinkedList<TfAssociate> llta;
	@Mock
	TfAssociate ta;
	@Mock
	TfMarketingStatus tms;
	@Mock
	TfClient tc;
	@Mock
	TfUser tu;
	@Mock
	List<GraphedCriteriaResult> lgcr;
	
    @BeforeMethod
    protected void setup() {
    	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void associateResourceGetAll_TestPositive() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(lta).when(as).getAllAssociates();
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn("1").when(payload).get(anyString()); //return admin role to allow access in positive test
    	doReturn(false).when(lta).isEmpty();
    	assertEquals(arMock.getAllAssociates(anyString()).getStatusInfo(), Status.OK);
    	verify(arMock).responseStatus(Status.OK);
    }
    
    @Test
    public void associateResourceGetAll_TestNoContentEmpty() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(lta).when(as).getAllAssociates();
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn("1").when(payload).get(anyString()); //return admin role to allow access in positive test
    	doReturn(true).when(lta).isEmpty();
    	assertEquals(arMock.getAllAssociates(anyString()).getStatusInfo(), Status.NO_CONTENT);
    	verify(arMock).responseStatus(Status.NO_CONTENT);
    }
    
    @Test
    public void associateResourceGetAll_TestNoContentNull() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(null).when(as).getAllAssociates();
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn("1").when(payload).get(anyString()); //return admin role to allow access in positive test
    	assertEquals(arMock.getAllAssociates(anyString()).getStatusInfo(), Status.NO_CONTENT);
    	verify(arMock).responseStatus(Status.NO_CONTENT);
    }
    
    @Test
    public void associateResourceGetAll_TestForbidden() { //Tests associate access to the function
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(lta).when(as).getAllAssociates();
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn("5").when(payload).get(anyString()); //return admin role to allow access in positive test
    	assertEquals(arMock.getAllAssociates(anyString()).getStatusInfo(), Status.FORBIDDEN);
    	verify(arMock).responseStatus(Status.FORBIDDEN);
    }
    
    @Test
    public void associateResourceGetAll_TestNullPayload() { 
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(null).when(as).getAllAssociates();
    	assertEquals(arMock.getAllAssociates(anyString()).getStatusInfo(), Status.UNAUTHORIZED);
    	verify(arMock).jwtInvalidBody(anyString());
    }
    
    @Test
    public void associateResourceGetCount_TestPositive() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn("1").when(payload).get(anyString());
    	doReturn(jo).when(arMock).getJSONObject();
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(hmsi).when(as).getStatusCountsMap();
    	doReturn(li).when(arMock).getArrayList();
    	int i = 0;
    	doReturn(++i).when(hmsi).get(anyString());
    	doReturn(true).when(li).add(anyInt());
    	doReturn(jo).when(jo).put(anyString(), eq(li));
    	doReturn("ha").when(jo).toString();
    	assertEquals(arMock.getCountAssociates(anyString()).getStatusInfo(), Status.OK);
    	verify(arMock).responseStatus(Status.OK);
    }
    
    @Test
    public void associateResourceGetCount_TestForbidden() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn("5").when(payload).get(anyString());
    	assertEquals(arMock.getCountAssociates(anyString()).getStatusInfo(), Status.FORBIDDEN);
    	verify(arMock).responseStatus(Status.FORBIDDEN);
    }
    
    @Test
    public void associateResourceGetCount_TestNullPayload() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(null).when(arMock).jwtProcessToken(anyString());
    	doReturn("tokenization!").when(arMock).jwtInvalidBody(anyString());
    	assertEquals(arMock.getCountAssociates(anyString()).getStatusInfo(), Status.UNAUTHORIZED);
    	verify(arMock).responseStatus(Status.UNAUTHORIZED);
    	verify(arMock).jwtInvalidBody(anyString());
    }
    
    @Test
    public void associateResourceByUserId_TestPositive() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(ta).when(as).getAssociateByUserId(anyInt());
    	assertEquals(arMock.getAssociateByUserId(anyInt(), anyString()).getStatusInfo(), Status.OK);
    	verify(arMock).responseStatus(Status.OK);
    }
    
    @Test
    public void associateResourceByUserId_TestNoContent() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(null).when(as).getAssociateByUserId(anyInt());
    	assertEquals(arMock.getAssociateByUserId(anyInt(), anyString()).getStatusInfo(), Status.NO_CONTENT);
    	verify(arMock).responseStatus(Status.NO_CONTENT);
    }
    
    @Test
    public void associateResourceByUserId_TestNoResultFromId() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn(as).when(arMock).getAssociateService();
    	doThrow(NoResultException.class).when(as).getAssociateByUserId(anyInt());
    	assertEquals(arMock.getAssociateByUserId(anyInt(), anyString()).getStatusInfo(), Status.NO_CONTENT);
    	verify(arMock).responseStatus(Status.NO_CONTENT);
    }
    
    @Test
    public void associateResourceByUserId_TestNullPayload() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(null).when(arMock).jwtProcessToken(anyString());
    	assertEquals(arMock.getAssociateByUserId(anyInt(), anyString()).getStatusInfo(), Status.UNAUTHORIZED);
    	verify(arMock).responseStatus(Status.UNAUTHORIZED);
    	verify(arMock).jwtInvalidBody(anyString());
    }
    
    @Test
    public void associateResourceGetAssociate_TestPositive() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(ta).when(as).getAssociate(anyInt());
    	assertEquals(arMock.getAssociate(anyInt(), anyString()).getStatusInfo(), Status.OK);
    	verify(arMock).responseStatus(Status.OK);
    }
    
    @Test
    public void associateResourceGetAssociate_TestNoContent() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(null).when(as).getAssociate(anyInt());
    	assertEquals(arMock.getAssociate(anyInt(), anyString()).getStatusInfo(), Status.NO_CONTENT);
    	verify(arMock).responseStatus(Status.NO_CONTENT);
    }
    
    @Test
    public void associateResourceGetAssociate_TestNoResultFromId() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn(as).when(arMock).getAssociateService();
    	doThrow(NoResultException.class).when(as).getAssociate(anyInt());
    	assertEquals(arMock.getAssociate(anyInt(), anyString()).getStatusInfo(), Status.NO_CONTENT);
    	verify(arMock).responseStatus(Status.NO_CONTENT);
    }
    
    @Test
    public void associateResourceGetAssociate_TestNullPayload() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(null).when(arMock).jwtProcessToken(anyString());
    	assertEquals(arMock.getAssociate(anyInt(), anyString()).getStatusInfo(), Status.UNAUTHORIZED);
    	verify(arMock).responseStatus(Status.UNAUTHORIZED);
    	verify(arMock).jwtInvalidBody(anyString());
    }
    
    @Test
    public void associateResourceUpdateAssociate_TestPositive() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn("1").when(payload).get(anyString());
    	doReturn(llta).when(arMock).getLinkedList();
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(ta).when(as).getAssociate(anyInt());
    	doReturn(mss).when(arMock).getMarketingStatusService();
    	doReturn(tms).when(mss).getMarketingStatusById(anyInt());
    	doNothing().when(ta).setMarketingStatus(tms);
    	doReturn(cs).when(arMock).getClientService();
    	doReturn(tc).when(cs).getClient(anyInt());
    	doNothing().when(ta).setClient(tc);
    	doReturn(tu).when(ta).getUser();
    	doNothing().when(tu).setIsApproved(anyInt());
    	doReturn(true).when(llta).add(ta);
    	doReturn(true).when(as).updateAssociates(llta);
    	List<Integer> i = new ArrayList();
    	i.add(54);
    	assertEquals(arMock.updateAssociates("", 0, 0, 0, i).getStatusInfo(), Status.OK);
    	verify(arMock).responseStatus(Status.OK);
    }
    
    @Test
    public void associateResourceUpdateAssociate_TestServerError() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn("1").when(payload).get(anyString());
    	doReturn(llta).when(arMock).getLinkedList();
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(ta).when(as).getAssociate(anyInt());
    	doReturn(mss).when(arMock).getMarketingStatusService();
    	doReturn(tms).when(mss).getMarketingStatusById(anyInt());
    	doNothing().when(ta).setMarketingStatus(tms);
    	doReturn(cs).when(arMock).getClientService();
    	doReturn(tc).when(cs).getClient(anyInt());
    	doNothing().when(ta).setClient(tc);
    	doReturn(tu).when(ta).getUser();
    	doNothing().when(tu).setIsApproved(anyInt());
    	doReturn(true).when(llta).add(ta);
    	doReturn(false).when(as).updateAssociates(llta);
    	List<Integer> i = new ArrayList();
    	i.add(54);
    	assertEquals(arMock.updateAssociates("", 0, 0, 0, i).getStatusInfo(), Status.INTERNAL_SERVER_ERROR);
    	verify(arMock).responseStatus(Status.INTERNAL_SERVER_ERROR);
    }
    
    @Test
    public void associateResourceUpdateAssociate_TestNoParamsSet() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn("1").when(payload).get(anyString());
    	doReturn(llta).when(arMock).getLinkedList();
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(ta).when(as).getAssociate(anyInt());
    	doReturn(mss).when(arMock).getMarketingStatusService();
    	doReturn(tms).when(mss).getMarketingStatusById(anyInt());
    	doNothing().when(ta).setMarketingStatus(tms);
    	doReturn(cs).when(arMock).getClientService();
    	doReturn(tc).when(cs).getClient(anyInt());
    	doNothing().when(ta).setClient(tc);
    	doReturn(tu).when(ta).getUser();
    	doNothing().when(tu).setIsApproved(anyInt());
    	doReturn(true).when(llta).add(ta);
    	doReturn(true).when(as).updateAssociates(llta);
    	List<Integer> i = new ArrayList();
    	i.add(54);
    	assertEquals(arMock.updateAssociates("", -1, -1, -1, i).getStatusInfo(), Status.OK);
    	verify(arMock).responseStatus(Status.OK);
    }
    
    @Test
    public void associateResourceUpdateAssociate_TestUnauthorizedUser() {
    	AssociateResource arMock2 = spy(new AssociateResource());
    	doReturn(payload).when(arMock2).jwtProcessToken(anyString());
    	doReturn("2").when(payload).get(anyString());
    	assertEquals(arMock2.updateAssociates("", -1, -1, -1, li).getStatusInfo(), Status.FORBIDDEN);
    	verify(arMock2).responseStatus(Status.FORBIDDEN);
    	
    	AssociateResource arMock5 = spy(new AssociateResource());
    	doReturn(payload).when(arMock5).jwtProcessToken(anyString());
    	doReturn("5").when(payload).get(anyString());
    	assertEquals(arMock5.updateAssociates("", -1, -1, -1, li).getStatusInfo(), Status.FORBIDDEN);
    	verify(arMock5).responseStatus(Status.FORBIDDEN);
    }
    
    @Test
    public void associateResourceUpdateAssociate_TestNullPayload() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(null).when(arMock).jwtProcessToken(anyString());
    	assertEquals(arMock.updateAssociates("", -1, -1, -1, li).getStatusInfo(), Status.UNAUTHORIZED);
    	verify(arMock).responseStatus(Status.UNAUTHORIZED);
    }
    
    @Test
    public void associateResourceUpdateOneAssociate_TestPositive() { //Tests one associate update
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(true).when(as).updateAssociate(null); //passing eq(ta) makes this call on null. This is a Test-Only case
    	assertEquals(arMock.updateAssociate(anyInt(), eq(ta), anyString()).getStatusInfo(), Status.OK);
    	verify(arMock).responseStatus(Status.OK);
    }
    
    @Test
    public void associateResourceUpdateOneAssociate_TestFailToUpdate() { 
    	AssociateResource nullUpdateMock = spy(new AssociateResource());
    	doReturn(payload).when(nullUpdateMock).jwtProcessToken(anyString());
    	doReturn(as).when(nullUpdateMock).getAssociateService();
    	doReturn(true).when(as).updateAssociate(ta); 
    	assertEquals(nullUpdateMock.updateAssociate(anyInt(), eq(ta), anyString()).getStatusInfo(), Status.INTERNAL_SERVER_ERROR);
    	verify(nullUpdateMock).responseStatus(Status.INTERNAL_SERVER_ERROR);
    	
    	AssociateResource falseUpdateMock = spy(new AssociateResource());
    	doReturn(payload).when(falseUpdateMock).jwtProcessToken(anyString());
    	doReturn(as).when(falseUpdateMock).getAssociateService();
    	doReturn(false).when(as).updateAssociate(null); 
    	assertEquals(falseUpdateMock.updateAssociate(anyInt(), eq(ta), anyString()).getStatusInfo(), Status.INTERNAL_SERVER_ERROR);
    	verify(falseUpdateMock).responseStatus(Status.INTERNAL_SERVER_ERROR);
    }
    
    //TODO: Maybe, that is. 
    /*
     * Having not spent time with this code until the last day, I don't know if adding 
     * a check for the user's permissions here will break code elsewhere, but the fact 
     * is that there is no check in the updateAssociate method for upper-level permissions
     * The other methods verify the roleId is NOT 5 or 2, but this one allows any role to use it. 
     * Add a check if possible. Be better. 
     * @Michael Tinning Batch1811
     */
    
    @Test
    public void associateResourceUpdateOneAssociate_TestNullPayload() { //Tests one associate update
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(null).when(arMock).jwtProcessToken(anyString());
    	assertEquals(arMock.updateAssociate(anyInt(), eq(ta), anyString()).getStatusInfo(), Status.UNAUTHORIZED);
    	verify(arMock).responseStatus(Status.UNAUTHORIZED);
    	verify(arMock).jwtInvalidBody(anyString());
    }
    
    @Test
    public void associateResourceGetMapped_Test() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(lgcr).when(as).getMappedInfo(anyInt());
    	assertEquals(arMock.getMappedInfo(anyInt()).getStatusInfo(), Status.OK);
    	verify(as).getMappedInfo(anyInt());
    	verify(arMock).responseStatus(Status.OK);
    }
    
    @Test
    public void associateResourceGetUndeployed_Test() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(lgcr).when(as).getUndeployed(anyString());
    	assertEquals(arMock.getUndeployed(anyString()).getStatusInfo(), Status.OK);
    	verify(as).getUndeployed(anyString());
    	verify(arMock).responseStatus(Status.OK);
    }
    
    @Test
    public void associateResourceApproveAssociate_TestPositive() {
    	AssociateResource arMock1 = spy(new AssociateResource());
    	doReturn(payload).when(arMock1).jwtProcessToken(anyString());
    	doReturn("1").when(payload).get(anyString());
    	doReturn(as).when(arMock1).getAssociateService();
    	doReturn(true).when(as).approveAssociate(anyInt());
    	assertEquals(arMock1.approveAssociate(anyInt(), anyString()).getStatusInfo(), Status.OK);
    	verify(arMock1).responseStatus(Status.OK);
    	
    	AssociateResource arMock2 = spy(new AssociateResource());
    	doReturn(payload).when(arMock2).jwtProcessToken(anyString());
    	doReturn("2").when(payload).get(anyString());
    	doReturn(as).when(arMock2).getAssociateService();
    	doReturn(true).when(as).approveAssociate(anyInt());
    	assertEquals(arMock2.approveAssociate(anyInt(), anyString()).getStatusInfo(), Status.OK);
    	verify(arMock2).responseStatus(Status.OK);
    	
    	AssociateResource arMock4 = spy(new AssociateResource());
    	doReturn(payload).when(arMock4).jwtProcessToken(anyString());
    	doReturn("4").when(payload).get(anyString());
    	doReturn(as).when(arMock4).getAssociateService();
    	doReturn(true).when(as).approveAssociate(anyInt());
    	assertEquals(arMock4.approveAssociate(anyInt(), anyString()).getStatusInfo(), Status.OK);
    	verify(arMock4).responseStatus(Status.OK);
    }
    
    @Test
    public void associateResourceApproveAssociate_TestFailToApprove() {
    	AssociateResource arMock1 = spy(new AssociateResource());
    	doReturn(payload).when(arMock1).jwtProcessToken(anyString());
    	doReturn("1").when(payload).get(anyString());
    	doReturn(as).when(arMock1).getAssociateService();
    	doReturn(false).when(as).approveAssociate(anyInt());
    	assertEquals(arMock1.approveAssociate(anyInt(), anyString()).getStatusInfo(), Status.INTERNAL_SERVER_ERROR);
    	verify(arMock1).responseStatus(Status.INTERNAL_SERVER_ERROR);
    	
    	AssociateResource arMock2 = spy(new AssociateResource());
    	doReturn(payload).when(arMock2).jwtProcessToken(anyString());
    	doReturn("2").when(payload).get(anyString());
    	doReturn(as).when(arMock2).getAssociateService();
    	doReturn(false).when(as).approveAssociate(anyInt());
    	assertEquals(arMock2.approveAssociate(anyInt(), anyString()).getStatusInfo(), Status.INTERNAL_SERVER_ERROR);
    	verify(arMock2).responseStatus(Status.INTERNAL_SERVER_ERROR);
    	
    	AssociateResource arMock4 = spy(new AssociateResource());
    	doReturn(payload).when(arMock4).jwtProcessToken(anyString());
    	doReturn("4").when(payload).get(anyString());
    	doReturn(as).when(arMock4).getAssociateService();
    	doReturn(false).when(as).approveAssociate(anyInt());
    	assertEquals(arMock4.approveAssociate(anyInt(), anyString()).getStatusInfo(), Status.INTERNAL_SERVER_ERROR);
    	verify(arMock4).responseStatus(Status.INTERNAL_SERVER_ERROR);
    }
    
    @Test
    public void associateResourceApproveAssociate_TestForbidden() {
    	AssociateResource arMock3 = spy(new AssociateResource());
    	doReturn(payload).when(arMock3).jwtProcessToken(anyString());
    	doReturn("3").when(payload).get(anyString());
    	doReturn(as).when(arMock3).getAssociateService();
    	doReturn(true).when(as).approveAssociate(anyInt());
    	assertEquals(arMock3.approveAssociate(anyInt(), anyString()).getStatusInfo(), Status.FORBIDDEN);
    	verify(arMock3).responseStatus(Status.FORBIDDEN);
    	
    	AssociateResource arMock5 = spy(new AssociateResource());
    	doReturn(payload).when(arMock5).jwtProcessToken(anyString());
    	doReturn("5").when(payload).get(anyString());
    	doReturn(as).when(arMock5).getAssociateService();
    	doReturn(true).when(as).approveAssociate(anyInt());
    	assertEquals(arMock5.approveAssociate(anyInt(), anyString()).getStatusInfo(), Status.FORBIDDEN);
    	verify(arMock5).responseStatus(Status.FORBIDDEN);
    }
    
    @Test
    public void associateResourceApproveAssociate_TestNullPayload() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(null).when(arMock).jwtProcessToken(anyString());
    	assertEquals(arMock.approveAssociate(anyInt(), anyString()).getStatusInfo(), Status.UNAUTHORIZED);
    	verify(arMock).responseStatus(Status.UNAUTHORIZED);
    	verify(arMock).jwtInvalidBody(anyString());
    }
    
    @Test
    public void associateResourceGetSixtyAssociates_Test() { //Tests getNAssociates, which gets 60 associates. 
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(lta).when(as).getNAssociates();
    	doReturn(rb).when(arMock).responseStatus(Status.OK);
    	doReturn(rb).when(rb).entity(lta);
    	doReturn(r).when(rb).build();
    	arMock.getNAssociates();
    	verify(arMock).responseStatus(Status.OK);
    	verify(rb).build();
    }
    
    @Test
    public void associateResourceGetAssociatePage_Test() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn("1").when(payload).get(anyString());
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(lta).when(as).getAssociatePage(anyInt(), anyInt(), anyInt(), anyInt(), anyString(), anyString(), anyString());
    	assertEquals(arMock.getAssociatePage(anyInt(), anyInt(), anyInt(), anyInt(), anyString(), anyString(), anyString(), anyString()).getStatusInfo(), Status.OK);
    	verify(arMock).responseStatus(Status.OK);
    }
    
    @Test
    public void associateResourceGetAssociatePage_TestQueryReturnsNull() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn("1").when(payload).get(anyString());
    	doReturn(as).when(arMock).getAssociateService();
    	doReturn(null).when(as).getAssociatePage(anyInt(), anyInt(), anyInt(), anyInt(), anyString(), anyString(), anyString());
    	assertEquals(arMock.getAssociatePage(anyInt(), anyInt(), anyInt(), anyInt(), anyString(), anyString(), anyString(), anyString()).getStatusInfo(), Status.NO_CONTENT);
    	verify(arMock).responseStatus(Status.NO_CONTENT);
    }
    
    @Test
    public void associateResourceGetAssociatePage_TestServerError() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn("1").when(payload).get(anyString());
    	doReturn(as).when(arMock).getAssociateService();
    	doThrow(Exception.class).when(as).getAssociatePage(anyInt(), anyInt(), anyInt(), anyInt(), anyString(), anyString(), anyString());
    	assertEquals(arMock.getAssociatePage(anyInt(), anyInt(), anyInt(), anyInt(), anyString(), anyString(), anyString(), anyString()).getStatusInfo(), Status.INTERNAL_SERVER_ERROR);
    	verify(arMock).responseStatus(Status.INTERNAL_SERVER_ERROR);
    }
    
    @Test
    public void associateResourceGetAssociatePage_TestBadRequest() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn("1").when(payload).get(anyString());
    	doReturn(as).when(arMock).getAssociateService();
    	doThrow(IllegalArgumentException.class).when(as).getAssociatePage(anyInt(), anyInt(), anyInt(), anyInt(), anyString(), anyString(), anyString());
    	assertEquals(arMock.getAssociatePage(anyInt(), anyInt(), anyInt(), anyInt(), anyString(), anyString(), anyString(), anyString()).getStatusInfo(), Status.BAD_REQUEST);
    	verify(arMock).responseStatus(Status.BAD_REQUEST);
    }
    
    @Test
    public void associateResourceGetAssociatePage_TestNoPermission() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(payload).when(arMock).jwtProcessToken(anyString());
    	doReturn("5").when(payload).get(anyString());
    	assertEquals(arMock.getAssociatePage(anyInt(), anyInt(), anyInt(), anyInt(), anyString(), anyString(), anyString(), anyString()).getStatusInfo(), Status.FORBIDDEN);
    	verify(arMock).responseStatus(Status.FORBIDDEN);
    }
    
    @Test
    public void associateResourceGetAssociatePage_TestNullPayload() {
    	AssociateResource arMock = spy(new AssociateResource());
    	doReturn(null).when(arMock).jwtProcessToken(anyString());
    	assertEquals(arMock.getAssociatePage(anyInt(), anyInt(), anyInt(), anyInt(), anyString(), anyString(), anyString(), anyString()).getStatusInfo(), Status.UNAUTHORIZED);
    	verify(arMock).responseStatus(Status.UNAUTHORIZED);
    }

    
    @Test
    public void associateResourceUtils_Test() {
    	//Tests the helper methods in the class explicitly for code coverage
    	AssociateResource ar = new AssociateResource();
    	assertNotNull(ar.getAssociateService());
    	assertNotNull(ar.getClientService());
    	assertNotNull(ar.getMarketingStatusService());
    	assertNotNull(ar.getJSONObject());
    	assertNotNull(ar.getLinkedList());
    	assertNotNull(ar.getArrayList());
    }
}
