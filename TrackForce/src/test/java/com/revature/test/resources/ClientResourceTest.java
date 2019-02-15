package com.revature.test.resources;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.revature.entity.TfClient;
import com.revature.resources.ClientResource;
import com.revature.services.AssociateService;
import com.revature.services.ClientService;

import io.jsonwebtoken.Claims;

public class ClientResourceTest {
	@Mock
	ClientService cs;
	@Mock
	AssociateService as;
	@Mock
	List<TfClient> ltc;
	@Mock
	TfClient tc;
	@Mock
	Response r;
	@Mock
	ResponseBuilder rb;
	@Mock
	Claims c;
	@Mock
	HashMap<String, Integer> hmsi;
	
	@BeforeMethod
	protected void setup() {
	  MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllClients_Positive() {
		ClientResource crMock = spy(new ClientResource());
		doReturn(cs).when(crMock).getClientService();
		doReturn(ltc).when(cs).getAllTfClients();
		doReturn("token").when(crMock).jwtInvalidTokenBody(anyString());
		doReturn(rb).when(crMock).responseStatus(anyInt());
		doReturn(rb).when(crMock).responseStatus(Status.OK);
		doReturn(rb).when(rb).entity(anyObject());
		doReturn(r).when(rb).build();
		doReturn(false).when(ltc).isEmpty();
		doReturn(r).when(crMock).authorizeUserToken(anyObject(), anyObject(), anyObject(), anyString());
		crMock.getAllClients(anyString());
		verify(rb, times(3)).build();
		verify(crMock).responseStatus(Status.OK);
		verify(crMock).authorizeUserToken(anyObject(), anyObject(), anyObject(), anyString());
	}
	
	@Test
	public void getAllClients_NoContent() {
		//clients == null path
		ClientResource crMock = spy(new ClientResource());
		doReturn(cs).when(crMock).getClientService();
		doReturn(null).when(cs).getAllTfClients();
		doReturn("token").when(crMock).jwtInvalidTokenBody(anyString());
		doReturn(rb).when(crMock).responseStatus(anyInt());
		doReturn(rb).when(crMock).responseStatus(Status.NO_CONTENT);
		doReturn(rb).when(rb).entity(anyObject());
		doReturn(r).when(rb).build();
		doReturn(r).when(crMock).authorizeUserToken(anyObject(), anyObject(), anyObject(), anyString());
		crMock.getAllClients(anyString());
//		verify(rb, times(3)).build(); //Covered by the other path's verify, total 6 calls
		verify(crMock).responseStatus(Status.NO_CONTENT);
		verify(crMock).authorizeUserToken(anyObject(), anyObject(), anyObject(), anyString());
		
		//isEmpty() returns true path
		ClientResource crMockNull = spy(new ClientResource());
		doReturn(cs).when(crMockNull).getClientService();
		doReturn(ltc).when(cs).getAllTfClients();
		doReturn("token").when(crMockNull).jwtInvalidTokenBody(anyString());
		doReturn(rb).when(crMockNull).responseStatus(anyInt());
		doReturn(rb).when(crMockNull).responseStatus(Status.NO_CONTENT);
		doReturn(rb).when(rb).entity(anyObject());
		doReturn(r).when(rb).build();
		doReturn(true).when(ltc).isEmpty();
		doReturn(r).when(crMockNull).authorizeUserToken(anyObject(), anyObject(), anyObject(), anyString());
		crMockNull.getAllClients(anyString());
		verify(rb, times(6)).build();
		verify(crMockNull).responseStatus(Status.NO_CONTENT);
		verify(crMockNull).authorizeUserToken(anyObject(), anyObject(), anyObject(), anyString());
	}
	
	@Test
	public void getMappedByClientId_getAll_Positive() {
		ClientResource crMock = spy(new ClientResource());
		doReturn(as).when(crMock).getAssociateService();
		doReturn(hmsi).when(as).getStatusCountsMap();
		doReturn(50).when(hmsi).get(anyObject());
		doReturn(40L).when(crMock).longParseLong(anyString());
		doReturn("token").when(crMock).jwtInvalidTokenBody(anyString());
		//Yes, I know, unauthorized doesn't exactly sound like positive testing, but that's handled later. 
		assertEquals(crMock.getMappedAssociatesByClientId((long)-1, "token").getStatusInfo(), Status.UNAUTHORIZED);
	}
	
	@Test
	public void getMappedByClientId_getOne_Positive() {
		ClientResource crMock = spy(new ClientResource());
		doReturn(as).when(crMock).getAssociateService();
		doReturn(33L).when(as).getMappedAssociateCountByClientId(anyLong(), anyInt());
		doReturn("token").when(crMock).jwtInvalidTokenBody(anyString());
		assertEquals(crMock.getMappedAssociatesByClientId((long)1, "token").getStatusInfo(), Status.UNAUTHORIZED);
	}
	
	@Test
	public void getMappedClients_Positive() {
		ClientResource crMock = spy(new ClientResource());
		doReturn(cs).when(crMock).getClientService();
		doReturn(ltc).when(cs).getMappedClients();
		doReturn("token").when(crMock).jwtInvalidTokenBody(anyString());
		doReturn(rb).when(crMock).responseStatus(anyInt());
		doReturn(rb).when(rb).entity(anyObject());
		doReturn(r).when(rb).build();
		doReturn(r).when(crMock).authorizeUserToken(anyObject(), anyObject(), anyObject(), anyString());
		crMock.getMappedClients(anyString());
		verify(rb, times(3)).build();
		verify(crMock).authorizeUserToken(anyObject(), anyObject(), anyObject(), anyString());
	}
	
	@Test
	public void getFirstFifty_Positive() {
		ClientResource crMock = spy(new ClientResource());
		doReturn(cs).when(crMock).getClientService();
		doReturn(ltc).when(cs).getFirstFiftyClients();
		doReturn("token").when(crMock).jwtInvalidTokenBody(anyString());
		doReturn(rb).when(crMock).responseStatus(anyInt());
		doReturn(rb).when(rb).entity(anyObject());
		doReturn(r).when(rb).build();
		doReturn(r).when(crMock).authorizeUserToken(anyObject(), anyObject(), anyObject(), anyString());
		crMock.getFirstFiftyClients(anyString());
		verify(rb, times(3)).build();
		verify(crMock).authorizeUserToken(anyObject(), anyObject(), anyObject(), anyString());
	}
	
	@Test
	public void authorizeToken_Authorized() {
		ClientResource crMock = spy(new ClientResource());
		doReturn(c).when(crMock).jwtProcessToken(anyString());
		doReturn(true).when(crMock).jwtValidateToken(anyString());
		doReturn("token").when(c).get(anyString());
		doReturn(4).when(crMock).intParseInt(anyString());

		assertEquals(crMock.authorizeUserToken(Response.status(Status.UNAUTHORIZED).build(), 
				  Response.status(Status.FORBIDDEN).build(), 
				  Response.status(Status.OK).build(), "token").getStatusInfo(), Status.OK);
	}
	
	@Test
	public void authorizeToken_ForbiddenBadToken() {
		ClientResource crMock = spy(new ClientResource());
		doReturn(c).when(crMock).jwtProcessToken(anyString());
		doReturn(false).when(crMock).jwtValidateToken(anyString());
		doReturn("token").when(c).get(anyString());
		doReturn(4).when(crMock).intParseInt(anyString());

		assertEquals(crMock.authorizeUserToken(Response.status(Status.UNAUTHORIZED).build(), 
				  Response.status(Status.FORBIDDEN).build(), 
				  Response.status(Status.OK).build(), "token").getStatusInfo(), Status.FORBIDDEN);
	}
	
	@Test
	public void authorizeToken_ForbiddenInvaidRoleHigh() {
		ClientResource crMock = spy(new ClientResource());
		doReturn(c).when(crMock).jwtProcessToken(anyString());
		doReturn(true).when(crMock).jwtValidateToken(anyString());
		doReturn("token").when(c).get(anyString());
		doReturn(44).when(crMock).intParseInt(anyString());

		assertEquals(crMock.authorizeUserToken(Response.status(Status.UNAUTHORIZED).build(), 
				  Response.status(Status.FORBIDDEN).build(), 
				  Response.status(Status.OK).build(), "token").getStatusInfo(), Status.FORBIDDEN);
	}
	
	@Test
	public void authorizeToken_ForbiddenInvaidRoleLow() {
		ClientResource crMock = spy(new ClientResource());
		doReturn(c).when(crMock).jwtProcessToken(anyString());
		doReturn(true).when(crMock).jwtValidateToken(anyString());
		doReturn("token").when(c).get(anyString());
		doReturn(-9).when(crMock).intParseInt(anyString());

		assertEquals(crMock.authorizeUserToken(Response.status(Status.UNAUTHORIZED).build(), 
				  Response.status(Status.FORBIDDEN).build(), 
				  Response.status(Status.OK).build(), "token").getStatusInfo(), Status.FORBIDDEN);
	}
	
	@Test
	public void helperMethodTests() {
		ClientResource cr = new ClientResource();
		assertNotNull(cr.getClientService());
		assertNotNull(cr.getAssociateService());
		assertEquals(cr.intParseInt("4"), new Integer(4));
		assertNotEquals(cr.intParseInt("3"), new Integer(4));
		assertEquals(cr.longParseLong("5"), new Long(5));
		assertNotEquals(cr.longParseLong("6"), new Long(8));
		assertEquals(cr.makeLongArray(17).length, 17);
		assertNull(cr.jwtProcessToken("token"));
		assertFalse(cr.jwtValidateToken("token"));
		assertNotNull(cr.jwtInvalidTokenBody("token"));
		assertNotNull(cr.responseStatus(200));
		assertNotNull(cr.responseStatus(Status.FORBIDDEN));
	}
}
