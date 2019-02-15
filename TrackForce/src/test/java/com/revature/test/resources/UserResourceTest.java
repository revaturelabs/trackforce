package com.revature.test.resources;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;
import com.revature.entity.TfUserAndCreatorRoleContainer;
import com.revature.resources.UserResource;
import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;
import com.revature.services.MarketingStatusService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;
import com.revature.utils.PasswordStorage.InvalidHashException;

import io.jsonwebtoken.Claims;

public class UserResourceTest {
	@Mock
	Claims payload;
	@Mock
	TfUserAndCreatorRoleContainer container;
	@Mock
	JWTService jservice;
	@Mock
	AssociateService associateServe;
	@Mock
	BatchService batchServe;
	@Mock
	ClientService clientServe;
	@Mock
	CurriculumService curriculumServe;
	@Mock
	InterviewService interviewServe;
	@Mock
	TrainerService trainerServe;
	@Mock
	UserService userServe;
	@Mock
	MarketingStatusService marketingServe;
	@Mock
	TfAssociate associate;
	@Mock
	TfUser mockUser;
	@Mock
	TfTrainer mockTrainer;
	@Mock
	JSONObject mockJson;
	
	
	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

	//Could probably move the spy from each test to the BeforeMethod, 
	//and possibly many of the doReturns
	@BeforeMethod
	protected void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createAssociateUserPositive() {
		String token = "token";
		UserResource userSpy = spy(new UserResource());
		
		doReturn(associateServe).when(userSpy).getAssociateService();
		doReturn(userServe).when(userSpy).getUserService();
		doReturn(trainerServe).when(userSpy).getTrainerService();
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		
		TfUser user = new TfUser();
		user.setId(1);
		user.setRole(5);
		user.setUsername("will");
		when(container.getUser()).thenReturn(user);
		when(payload.get("roleID")).thenReturn("1");//creatorRole
		when(userServe.getUser("will")).thenReturn(null);//username is available
		when(associateServe.createAssociate(any(TfAssociate.class))).thenReturn(true);
		when(userServe.insertUser(any(TfUser.class))).thenReturn(true);
		when(trainerServe.createTrainer(any(TfTrainer.class))).thenReturn(true);
		
		assertEquals((userSpy.createUser(container, token)).getStatusInfo(),Status.CREATED);
		
		verify(associateServe).createAssociate(any(TfAssociate.class));
		verify(userServe, never()).insertUser(any(TfUser.class));
		verify(trainerServe,never()).createTrainer(any(TfTrainer.class));
	}
	
	@Test
	public void createUserNullPayload() {
		String token = "token";
		UserResource userSpy = spy(new UserResource());
		
		doReturn(associateServe).when(userSpy).getAssociateService();
		doReturn(userServe).when(userSpy).getUserService();
		doReturn(trainerServe).when(userSpy).getTrainerService();
		doReturn(null).when(userSpy).jwtServiceProcessToken(anyString());
		
		TfUser user = new TfUser();
		user.setId(1);
		user.setRole(5);
		user.setUsername("will");
		when(container.getUser()).thenReturn(user);
		when(payload.get("roleID")).thenReturn("1");//creatorRole
		when(userServe.getUser("will")).thenReturn(null);//username is available
		when(associateServe.createAssociate(any(TfAssociate.class))).thenReturn(true);
		when(userServe.insertUser(any(TfUser.class))).thenReturn(true);
		when(trainerServe.createTrainer(any(TfTrainer.class))).thenReturn(true);
		
		assertEquals((userSpy.createUser(container, token)).getStatusInfo(),Status.UNAUTHORIZED);
		
		verify(associateServe,never()).createAssociate(any(TfAssociate.class));
		verify(userServe, never()).insertUser(any(TfUser.class));
		verify(trainerServe,never()).createTrainer(any(TfTrainer.class));
		verify(container, never()).getUser();
	}
	
	@Test
	public void createUserNegativeAssociateCreator() {
		String token = "token";
		UserResource userSpy = spy(new UserResource());
		
		doReturn(associateServe).when(userSpy).getAssociateService();
		doReturn(userServe).when(userSpy).getUserService();
		doReturn(trainerServe).when(userSpy).getTrainerService();
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		
		TfUser user = new TfUser();
		user.setId(1);
		user.setRole(5);
		user.setUsername("will");
		when(container.getUser()).thenReturn(user);
		when(payload.get("roleID")).thenReturn("5");//creatorRole
		when(userServe.getUser("will")).thenReturn(null);//username is available
		when(associateServe.createAssociate(any(TfAssociate.class))).thenReturn(true);
		when(userServe.insertUser(any(TfUser.class))).thenReturn(true);
		when(trainerServe.createTrainer(any(TfTrainer.class))).thenReturn(true);
		
		assertEquals((userSpy.createUser(container, token)).getStatusInfo(), Status.FORBIDDEN);
		
		verify(associateServe, never()).createAssociate(any(TfAssociate.class));
		verify(userServe, never()).insertUser(any(TfUser.class));
		verify(trainerServe,never()).createTrainer(any(TfTrainer.class));
		verify(payload).get("roleID");
		verify(userSpy, never()).getUserService();
	}
	
	@Test
	public void createUserAdminPositive() {
		String token = "token";
		UserResource userSpy = spy(new UserResource());
		
		doReturn(associateServe).when(userSpy).getAssociateService();
		doReturn(userServe).when(userSpy).getUserService();
		doReturn(trainerServe).when(userSpy).getTrainerService();
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		
		TfUser user = new TfUser();
		user.setId(1);
		user.setRole(1);
		user.setUsername("will");
		when(container.getUser()).thenReturn(user);
		when(payload.get("roleID")).thenReturn("1");//creatorRole
		when(userServe.getUser("will")).thenReturn(null);//username is available
		when(associateServe.createAssociate(any(TfAssociate.class))).thenReturn(true);
		when(userServe.insertUser(any(TfUser.class))).thenReturn(true);
		when(trainerServe.createTrainer(any(TfTrainer.class))).thenReturn(true);
		
		assertEquals((userSpy.createUser(container, token)).getStatusInfo(),Status.CREATED);
		
		verify(associateServe, never()).createAssociate(any(TfAssociate.class));
		verify(userServe).insertUser(any(TfUser.class));
		verify(trainerServe,never()).createTrainer(any(TfTrainer.class));
	}
	
	@Test
	public void createUserTrainerPositive() {
		String token = "token";
		UserResource userSpy = spy(new UserResource());
		
		doReturn(associateServe).when(userSpy).getAssociateService();
		doReturn(userServe).when(userSpy).getUserService();
		doReturn(trainerServe).when(userSpy).getTrainerService();
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		
		TfUser user = new TfUser();
		user.setId(1);
		user.setRole(2);
		user.setUsername("will");
		when(container.getUser()).thenReturn(user);
		when(payload.get("roleID")).thenReturn("1");//creatorRole
		when(userServe.getUser("will")).thenReturn(null);//username is available
		when(associateServe.createAssociate(any(TfAssociate.class))).thenReturn(true);
		when(userServe.insertUser(any(TfUser.class))).thenReturn(true);
		when(trainerServe.createTrainer(any(TfTrainer.class))).thenReturn(true);
		
		assertEquals((userSpy.createUser(container, token)).getStatusInfo(),Status.CREATED);
		
		verify(associateServe, never()).createAssociate(any(TfAssociate.class));
		verify(userServe, never()).insertUser(any(TfUser.class));
		verify(trainerServe).createTrainer(any(TfTrainer.class));
	}
	
	@Test
	public void createUserSalesPositive() {
		String token = "token";
		UserResource userSpy = spy(new UserResource());
		
		doReturn(associateServe).when(userSpy).getAssociateService();
		doReturn(userServe).when(userSpy).getUserService();
		doReturn(trainerServe).when(userSpy).getTrainerService();
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		
		TfUser user = new TfUser();
		user.setId(1);
		user.setRole(3);
		user.setUsername("will");
		when(container.getUser()).thenReturn(user);
		when(payload.get("roleID")).thenReturn("1");//creatorRole
		when(userServe.getUser("will")).thenReturn(null);//username is available
		when(associateServe.createAssociate(any(TfAssociate.class))).thenReturn(true);
		when(userServe.insertUser(any(TfUser.class))).thenReturn(true);
		when(trainerServe.createTrainer(any(TfTrainer.class))).thenReturn(true);
		
		assertEquals((userSpy.createUser(container, token)).getStatusInfo(),Status.CREATED);
		
		verify(associateServe, never()).createAssociate(any(TfAssociate.class));
		verify(userServe).insertUser(any(TfUser.class));
		verify(trainerServe,never()).createTrainer(any(TfTrainer.class));
	}
	
	@Test
	public void createUserStagingPositive() {
		String token = "token";
		UserResource userSpy = spy(new UserResource());
		
		doReturn(associateServe).when(userSpy).getAssociateService();
		doReturn(userServe).when(userSpy).getUserService();
		doReturn(trainerServe).when(userSpy).getTrainerService();
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		
		TfUser user = new TfUser();
		user.setId(1);
		user.setRole(4);
		user.setUsername("will");
		when(container.getUser()).thenReturn(user);
		when(payload.get("roleID")).thenReturn("1");//creatorRole
		when(userServe.getUser("will")).thenReturn(null);//username is available
		when(associateServe.createAssociate(any(TfAssociate.class))).thenReturn(true);
		when(userServe.insertUser(any(TfUser.class))).thenReturn(true);
		when(trainerServe.createTrainer(any(TfTrainer.class))).thenReturn(true);
		
		assertEquals((userSpy.createUser(container, token)).getStatusInfo(),Status.CREATED);
		
		verify(associateServe, never()).createAssociate(any(TfAssociate.class));
		verify(userServe).insertUser(any(TfUser.class));
		verify(trainerServe,never()).createTrainer(any(TfTrainer.class));
	}
	
	@Test
	public void createUserInvalidRole() {
		String token = "token";
		UserResource userSpy = spy(new UserResource());
		
		doReturn(associateServe).when(userSpy).getAssociateService();
		doReturn(userServe).when(userSpy).getUserService();
		doReturn(trainerServe).when(userSpy).getTrainerService();
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		
		TfUser user = new TfUser();
		user.setId(1);
		user.setRole(10);
		user.setUsername("will");
		when(container.getUser()).thenReturn(user);
		when(payload.get("roleID")).thenReturn("1");//creatorRole
		when(userServe.getUser("will")).thenReturn(null);//username is available
		when(associateServe.createAssociate(any(TfAssociate.class))).thenReturn(true);
		when(userServe.insertUser(any(TfUser.class))).thenReturn(true);
		when(trainerServe.createTrainer(any(TfTrainer.class))).thenReturn(true);
		
		assertEquals((userSpy.createUser(container, token)).getStatusInfo(),Status.EXPECTATION_FAILED);
		
		verify(associateServe, never()).createAssociate(any(TfAssociate.class));
		verify(userServe, never()).insertUser(any(TfUser.class));
		verify(trainerServe,never()).createTrainer(any(TfTrainer.class));
	}
	
	@Test
	public void checkUsernameTest() {
		UserResource userSpy = spy(new UserResource());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyString())).thenReturn(new TfUser());
		
		userSpy.checkUsername("stuff");
		verify(userServe).getUser(anyString());
	}
	
	@Test
	public void checkUsernameNullTest() {
		UserResource userSpy = spy(new UserResource());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyString())).thenReturn(null);
		
		userSpy.checkUsername("stuff");
		verify(userServe).getUser(anyString());
	}
	
	@Test
	public void createNewAssociateTest() {
		UserResource userSpy = spy(new UserResource());
		doReturn(associateServe).when(userSpy).getAssociateService();
		
		when(associateServe.createAssociate(associate)).thenReturn(true);
		when(associate.getUser()).thenReturn(mockUser);
		when(mockUser.getRole()).thenReturn(5);
		
		assertEquals(userSpy.createNewAssociate(associate).getStatusInfo(), Status.CREATED);
	}
	
	@Test
	public void createNewAssociateNegativeTest() {
		UserResource userSpy = spy(new UserResource());
		doReturn(associateServe).when(userSpy).getAssociateService();
		
		when(associateServe.createAssociate(associate)).thenReturn(false);
		when(associate.getUser()).thenReturn(mockUser);
		when(mockUser.getRole()).thenReturn(5);
		
		assertEquals(userSpy.createNewAssociate(associate).getStatusInfo(), Status.EXPECTATION_FAILED);
	}
	
	@Test
	public void createNewAssociateInvalidRoleTest() {
		UserResource userSpy = spy(new UserResource());
		doReturn(associateServe).when(userSpy).getAssociateService();
		
		when(associateServe.createAssociate(associate)).thenReturn(true);
		when(associate.getUser()).thenReturn(mockUser);
		when(mockUser.getRole()).thenReturn(1);
		
		assertEquals(userSpy.createNewAssociate(associate).getStatusInfo(), Status.FORBIDDEN);
	}
	
	@Test
	public void createTrainerTest() {
		UserResource userSpy = spy(new UserResource());
		when(mockTrainer.getTfUser()).thenReturn(mockUser);
		when(mockUser.getRole()).thenReturn(2);
		doReturn(trainerServe).when(userSpy).getTrainerService();
		when(trainerServe.createTrainer(mockTrainer)).thenReturn(true);
		
		assertEquals(userSpy.createTrainer(mockTrainer).getStatusInfo(),Status.CREATED);
	}
	
	@Test
	public void createTrainerBadInsertTest() {
		UserResource userSpy = spy(new UserResource());
		when(mockTrainer.getTfUser()).thenReturn(mockUser);
		when(mockUser.getRole()).thenReturn(2);
		doReturn(trainerServe).when(userSpy).getTrainerService();
		when(trainerServe.createTrainer(mockTrainer)).thenReturn(false);
		
		assertEquals(userSpy.createTrainer(mockTrainer).getStatusInfo(),Status.EXPECTATION_FAILED);
	}
	
	@Test
	public void createTrainerInvalidUserRoleTest() {
		UserResource userSpy = spy(new UserResource());
		when(mockTrainer.getTfUser()).thenReturn(mockUser);
		when(mockUser.getRole()).thenReturn(5);
		doReturn(trainerServe).when(userSpy).getTrainerService();
		when(trainerServe.createTrainer(mockTrainer)).thenReturn(true);
		
		assertEquals(userSpy.createTrainer(mockTrainer).getStatusInfo(),Status.FORBIDDEN);
	}
	
	@Test
	public void submitCredentialsTest() {
		UserResource userSpy = spy(new UserResource());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.submitCredentials(mockUser)).thenReturn(mockUser);
		
		assertEquals(userSpy.submitCredentials(mockUser).getStatusInfo(), Status.OK);
	}
	
	@Test
	public void submitCredentialsNullUserTest() {
		UserResource userSpy = spy(new UserResource());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.submitCredentials(null)).thenThrow(new NullPointerException());
		
		assertEquals(userSpy.submitCredentials(null).getStatusInfo(), Status.UNAUTHORIZED);
	}
	
	@Test
	public void submitCredentialsNoUserFoundTest() {
		UserResource userSpy = spy(new UserResource());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.submitCredentials(mockUser)).thenReturn(null);
		
		assertEquals(userSpy.submitCredentials(mockUser).getStatusInfo(), Status.UNAUTHORIZED);
	}
	
	@Test
	public void checkCredentialsTest() {
		UserResource userSpy = spy(new UserResource());
		doReturn(payload).when(userSpy).jwtServiceProcessToken("token");
		
		
		assertEquals(userSpy.checkCredentials("token").getStatusInfo(), Status.OK);
	}

	@Test
	public void checkCredentialsInvalidTokenTest() {
		UserResource userSpy = spy(new UserResource());
		doReturn(null).when(userSpy).jwtServiceProcessToken("token");
		
		assertEquals(userSpy.checkCredentials("token").getStatusInfo(), Status.UNAUTHORIZED);
	}
	
	@Test
	public void returnRoleTest() {
		UserResource userSpy = spy(new UserResource());
		doReturn(payload).when(userSpy).jwtServiceProcessToken("token");
		
		assertEquals(userSpy.returnRole("token").getStatusInfo(), Status.OK);
	}
	
	@Test
	public void returnRoleInvalidTokenTest() {
		UserResource userSpy = spy(new UserResource());
		doReturn(null).when(userSpy).jwtServiceProcessToken("token");
		
		assertEquals(userSpy.returnRole("token").getStatusInfo(), Status.UNAUTHORIZED);
	}
	
	@Test
	public void updateUserPasswordTest() throws CannotPerformOperationException, InvalidHashException {
		UserResource userSpy = spy(new UserResource());
		doReturn(mockJson).when(userSpy).getJSONObject(anyString());
		when(mockJson.getInt("userId")).thenReturn(1);
		when(mockJson.getString("oldPassword")).thenReturn("pass");
		when(mockJson.getString("newPassword")).thenReturn("password");
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyInt())).thenReturn(mockUser);
		doReturn(true).when(userSpy).getPasswordStorageVerifyPassword(anyString(), anyString());
		when(userServe.updateUserPassword(mockUser, "password")).thenReturn(true);
		when(mockUser.getPassword()).thenReturn("pass");
		
		assertEquals(userSpy.updateUserPassword("token", "body").getStatusInfo(), Status.OK);
	}
	
	@Test
	public void updateUserPasswordTest2() throws CannotPerformOperationException, InvalidHashException {
		UserResource userSpy = spy(new UserResource());
		doReturn(mockJson).when(userSpy).getJSONObject(anyString());
		when(mockJson.getInt("userId")).thenReturn(1);
		when(mockJson.getString("oldPassword")).thenReturn("pass");
		when(mockJson.getString("newPassword")).thenReturn("password");
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyInt())).thenReturn(mockUser);
		doReturn(false).when(userSpy).getPasswordStorageVerifyPassword(anyString(), anyString());
		when(userServe.updateUserPassword(mockUser, "password")).thenReturn(true);
		when(mockUser.getPassword()).thenReturn("pass");
		
		assertEquals(userSpy.updateUserPassword("token", "body").getStatusInfo(), Status.OK);
	}
	
	@Test
	public void updateUserPasswordNullPayloadTest() throws CannotPerformOperationException, InvalidHashException {
		UserResource userSpy = spy(new UserResource());
		doReturn(mockJson).when(userSpy).getJSONObject(anyString());
		when(mockJson.getInt("userId")).thenReturn(1);
		when(mockJson.getString("oldPassword")).thenReturn("pass");
		when(mockJson.getString("newPassword")).thenReturn("password");
		doReturn(null).when(userSpy).jwtServiceProcessToken(anyString());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyInt())).thenReturn(mockUser);
		doReturn(true).when(userSpy).getPasswordStorageVerifyPassword(anyString(), anyString());
		when(userServe.updateUserPassword(mockUser, "password")).thenReturn(true);
		when(mockUser.getPassword()).thenReturn("pass");
		
		assertEquals(userSpy.updateUserPassword("token", "body").getStatusInfo(), Status.UNAUTHORIZED);
	}
	
	@Test
	public void updateUserPasswordErrorTest() throws CannotPerformOperationException, InvalidHashException {
		UserResource userSpy = spy(new UserResource());
		doReturn(mockJson).when(userSpy).getJSONObject(anyString());
		when(mockJson.getInt("userId")).thenReturn(1);
		when(mockJson.getString("oldPassword")).thenReturn("pass");
		when(mockJson.getString("newPassword")).thenReturn("password");
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyInt())).thenReturn(mockUser);
		doReturn(true).when(userSpy).getPasswordStorageVerifyPassword(anyString(), anyString());
		when(userServe.updateUserPassword(mockUser, "password")).thenReturn(false);
		when(mockUser.getPassword()).thenReturn("pass");
		
		assertEquals(userSpy.updateUserPassword("token", "body").getStatusInfo(), Status.INTERNAL_SERVER_ERROR);
	}
	
	@Test
	public void updateUserPasswordErrorTest2() throws CannotPerformOperationException, InvalidHashException {
		UserResource userSpy = spy(new UserResource());
		doReturn(mockJson).when(userSpy).getJSONObject(anyString());
		when(mockJson.getInt("userId")).thenReturn(1);
		when(mockJson.getString("oldPassword")).thenReturn("pass");
		when(mockJson.getString("newPassword")).thenReturn("password");
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyInt())).thenReturn(mockUser);
		doReturn(false).when(userSpy).getPasswordStorageVerifyPassword(anyString(), anyString());
		when(userServe.updateUserPassword(mockUser, "password")).thenReturn(false);
		when(mockUser.getPassword()).thenReturn("pass");
		
		assertEquals(userSpy.updateUserPassword("token", "body").getStatusInfo(), Status.INTERNAL_SERVER_ERROR);
	}
	
	@Test
	public void updateUserPasswordUnauthorizedTest() throws CannotPerformOperationException, InvalidHashException {
		UserResource userSpy = spy(new UserResource());
		doReturn(mockJson).when(userSpy).getJSONObject(anyString());
		when(mockJson.getInt("userId")).thenReturn(1);
		when(mockJson.getString("oldPassword")).thenReturn("pass");
		when(mockJson.getString("newPassword")).thenReturn("password");
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyInt())).thenReturn(mockUser);
		doReturn(false).when(userSpy).getPasswordStorageVerifyPassword(anyString(), anyString());
		when(userServe.updateUserPassword(mockUser, "password")).thenReturn(true);
		when(mockUser.getPassword()).thenReturn("pass2");
		
		assertEquals(userSpy.updateUserPassword("token", "body").getStatusInfo(), Status.UNAUTHORIZED);
	}
	
	@Test
	public void updateUserPasswordExceptionThrownTest() throws CannotPerformOperationException, InvalidHashException {
		UserResource userSpy = spy(new UserResource());
		doReturn(mockJson).when(userSpy).getJSONObject(anyString());
		when(mockJson.getInt("userId")).thenReturn(1);
		when(mockJson.getString("oldPassword")).thenReturn("pass");
		when(mockJson.getString("newPassword")).thenReturn("password");
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyInt())).thenReturn(mockUser);
		doThrow(CannotPerformOperationException.class).when(userSpy).getPasswordStorageVerifyPassword(anyString(), anyString());
		when(userServe.updateUserPassword(mockUser, "password")).thenReturn(true);
		when(mockUser.getPassword()).thenReturn("pass");
		
		assertEquals(userSpy.updateUserPassword("token", "body").getStatusInfo(), Status.INTERNAL_SERVER_ERROR);
	}
	
	@Test
	public void updateUserPasswordExceptionThrownTest2() throws CannotPerformOperationException, InvalidHashException {
		UserResource userSpy = spy(new UserResource());
		doReturn(mockJson).when(userSpy).getJSONObject(anyString());
		when(mockJson.getInt("userId")).thenReturn(1);
		when(mockJson.getString("oldPassword")).thenReturn("pass");
		when(mockJson.getString("newPassword")).thenReturn("password");
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyInt())).thenReturn(mockUser);
		doThrow(InvalidHashException.class).when(userSpy).getPasswordStorageVerifyPassword(anyString(), anyString());
		when(userServe.updateUserPassword(mockUser, "password")).thenReturn(true);
		when(mockUser.getPassword()).thenReturn("pass");
		
		assertEquals(userSpy.updateUserPassword("token", "body").getStatusInfo(), Status.INTERNAL_SERVER_ERROR);
	}
	
	@Test
	public void updateUserUsernameTest() throws CannotPerformOperationException, InvalidHashException {
		UserResource userSpy = spy(new UserResource());
		doReturn(mockJson).when(userSpy).getJSONObject(anyString());
		when(mockJson.getInt("userId")).thenReturn(1);
		when(mockJson.getString("password")).thenReturn("pass");
		when(mockJson.getString("newUsername")).thenReturn("username");
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyInt())).thenReturn(mockUser);
		when(userServe.getUser(anyString())).thenReturn(null);
		doReturn(true).when(userSpy).getPasswordStorageVerifyPassword(anyString(), anyString());
		when(userServe.updateUsername(mockUser, "username")).thenReturn(true);
		when(mockUser.getPassword()).thenReturn("pass");
		
		assertEquals(userSpy.updateUserUsername("token", "body").getStatusInfo(), Status.OK);
	}
	
	@Test
	public void updateUserUsernameTest2() throws CannotPerformOperationException, InvalidHashException {
		UserResource userSpy = spy(new UserResource());
		doReturn(mockJson).when(userSpy).getJSONObject(anyString());
		when(mockJson.getInt("userId")).thenReturn(1);
		when(mockJson.getString("password")).thenReturn("pass");
		when(mockJson.getString("newUsername")).thenReturn("username");
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyInt())).thenReturn(mockUser);
		when(userServe.getUser(anyString())).thenReturn(null);
		doReturn(false).when(userSpy).getPasswordStorageVerifyPassword(anyString(), anyString());
		when(userServe.updateUsername(mockUser, "username")).thenReturn(true);
		when(mockUser.getPassword()).thenReturn("pass");
		
		assertEquals(userSpy.updateUserUsername("token", "body").getStatusInfo(), Status.OK);
	}
	
	@Test
	public void updateUserUsernameWrongPasswordTest() throws CannotPerformOperationException, InvalidHashException {
		UserResource userSpy = spy(new UserResource());
		doReturn(mockJson).when(userSpy).getJSONObject(anyString());
		when(mockJson.getInt("userId")).thenReturn(1);
		when(mockJson.getString("password")).thenReturn("pass");
		when(mockJson.getString("newUsername")).thenReturn("username");
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyInt())).thenReturn(mockUser);
		when(userServe.getUser(anyString())).thenReturn(null);
		doReturn(false).when(userSpy).getPasswordStorageVerifyPassword(anyString(), anyString());
		when(userServe.updateUsername(mockUser, "username")).thenReturn(true);
		when(mockUser.getPassword()).thenReturn("pass2");
		
		assertEquals(userSpy.updateUserUsername("token", "body").getStatusInfo(), Status.UNAUTHORIZED);
	}
	
	@Test
	public void updateUserUsernameFailedUpdateTest() throws CannotPerformOperationException, InvalidHashException {
		UserResource userSpy = spy(new UserResource());
		doReturn(mockJson).when(userSpy).getJSONObject(anyString());
		when(mockJson.getInt("userId")).thenReturn(1);
		when(mockJson.getString("password")).thenReturn("pass");
		when(mockJson.getString("newUsername")).thenReturn("username");
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyInt())).thenReturn(mockUser);
		when(userServe.getUser(anyString())).thenReturn(null);
		doReturn(true).when(userSpy).getPasswordStorageVerifyPassword(anyString(), anyString());
		when(userServe.updateUsername(mockUser, "username")).thenReturn(false);
		when(mockUser.getPassword()).thenReturn("pass");
		
		assertEquals(userSpy.updateUserUsername("token", "body").getStatusInfo(), Status.INTERNAL_SERVER_ERROR);
	}
	
	@Test
	public void updateUserUsernameFailedUpdateTest2() throws CannotPerformOperationException, InvalidHashException {
		UserResource userSpy = spy(new UserResource());
		doReturn(mockJson).when(userSpy).getJSONObject(anyString());
		when(mockJson.getInt("userId")).thenReturn(1);
		when(mockJson.getString("password")).thenReturn("pass");
		when(mockJson.getString("newUsername")).thenReturn("username");
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyInt())).thenReturn(mockUser);
		when(userServe.getUser(anyString())).thenReturn(null);
		doReturn(false).when(userSpy).getPasswordStorageVerifyPassword(anyString(), anyString());
		when(userServe.updateUsername(mockUser, "username")).thenReturn(false);
		when(mockUser.getPassword()).thenReturn("pass");
		
		assertEquals(userSpy.updateUserUsername("token", "body").getStatusInfo(), Status.INTERNAL_SERVER_ERROR);
	}
	
	@Test
	public void updateUserUsernameExceptionThrownTest() throws CannotPerformOperationException, InvalidHashException {
		UserResource userSpy = spy(new UserResource());
		doReturn(mockJson).when(userSpy).getJSONObject(anyString());
		when(mockJson.getInt("userId")).thenReturn(1);
		when(mockJson.getString("password")).thenReturn("pass");
		when(mockJson.getString("newUsername")).thenReturn("username");
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyInt())).thenReturn(mockUser);
		when(userServe.getUser(anyString())).thenReturn(null);
		doThrow(CannotPerformOperationException.class).when(userSpy).getPasswordStorageVerifyPassword(anyString(), anyString());
		when(userServe.updateUsername(mockUser, "username")).thenReturn(true);
		when(mockUser.getPassword()).thenReturn("pass");
		
		assertEquals(userSpy.updateUserUsername("token", "body").getStatusInfo(), Status.INTERNAL_SERVER_ERROR);
	}
	
	@Test
	public void updateUserUsernameExceptionThrownTest2() throws CannotPerformOperationException, InvalidHashException {
		UserResource userSpy = spy(new UserResource());
		doReturn(mockJson).when(userSpy).getJSONObject(anyString());
		when(mockJson.getInt("userId")).thenReturn(1);
		when(mockJson.getString("password")).thenReturn("pass");
		when(mockJson.getString("newUsername")).thenReturn("username");
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyInt())).thenReturn(mockUser);
		when(userServe.getUser(anyString())).thenReturn(null);
		doThrow(InvalidHashException.class).when(userSpy).getPasswordStorageVerifyPassword(anyString(), anyString());
		when(userServe.updateUsername(mockUser, "username")).thenReturn(true);
		when(mockUser.getPassword()).thenReturn("pass");
		
		assertEquals(userSpy.updateUserUsername("token", "body").getStatusInfo(), Status.INTERNAL_SERVER_ERROR);
	}
	
	@Test
	public void updateUserUsernameUsernameTakenTest() throws CannotPerformOperationException, InvalidHashException {
		UserResource userSpy = spy(new UserResource());
		doReturn(mockJson).when(userSpy).getJSONObject(anyString());
		when(mockJson.getInt("userId")).thenReturn(1);
		when(mockJson.getString("password")).thenReturn("pass");
		when(mockJson.getString("newUsername")).thenReturn("username");
		doReturn(payload).when(userSpy).jwtServiceProcessToken(anyString());
		doReturn(userServe).when(userSpy).getUserService();
		when(userServe.getUser(anyInt())).thenReturn(mockUser);
		when(userServe.getUser(anyString())).thenReturn(mockUser);
		doReturn(true).when(userSpy).getPasswordStorageVerifyPassword(anyString(), anyString());
		when(userServe.updateUsername(mockUser, "username")).thenReturn(true);
		when(mockUser.getPassword()).thenReturn("pass");
		
		assertEquals(userSpy.updateUserUsername("token", "body").getStatusInfo(), Status.BAD_REQUEST);
	}
}
