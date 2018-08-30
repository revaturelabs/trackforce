package com.revature.test.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.dao.UserDao;
import com.revature.entity.TfUser;
import com.revature.services.JWTService;
import com.revature.services.UserService;

/**
 * This class tests major functionalities related to the UserService class using
 * mockito to mock the userDao, UserService, and JWTservice.
 * 
 * @author Jesse
 * @since 6.18.06.08
 */
public class UserServiceTest {

	@Mock
	private UserDao userDaoMock;

	@Mock
	private UserService mockedService;

	@Mock
	private JWTService jwtService;

	@InjectMocks
	private UserService userService;

	TfUser tfu = new TfUser();
	TfUser tfu2 = new TfUser();
	
	// new users from 1807
	TfUser testAdmin = new TfUser();
	TfUser trainer = new TfUser();
	TfUser salesTest = new TfUser();
	TfUser bobStage = new TfUser();
	// test insertUser with these
	TfUser sampleUser1 = new TfUser();
	TfUser sampleUser2 = new TfUser();
	
	/**
	 * Set up the appropriate mocks
	 * 
	 * @throws IOException
	 */
	private void setupMocks() throws IOException {
		MockitoAnnotations.initMocks(this);

		// test data matching first 4 users in database
		testAdmin.setId(1);
		testAdmin.setUsername("TestAdmin");
		testAdmin.setPassword("TestAdmin");
		
		trainer.setId(2);
		trainer.setUsername("Trainer");
		trainer.setPassword("Trainer");
		
		salesTest.setId(3);
		salesTest.setUsername("salestest");
		salesTest.setPassword("salestest");
		
		bobStage.setId(4);
		bobStage.setUsername("bobstage");
		bobStage.setPassword("bobstage");
		
		sampleUser1.setId(90);
		sampleUser1.setUsername("Sample1");
		sampleUser1.setPassword("sample1");
		
		// no username/password, insert will fail
		sampleUser2.setId(100);
		
//		tfu.setId(1);
//		tfu.setUsername("TestAdmin");
//		tfu.setPassword("TestAdmin");
//		tfu2.setId(2);

		List<TfUser> dummyUsers = new ArrayList<>();
//		dummyUsers.add(tfu);
//		dummyUsers.add(tfu2);
		
		dummyUsers.add(testAdmin);
		dummyUsers.add(trainer);
		dummyUsers.add(salesTest);
		dummyUsers.add(bobStage);

		Mockito.when(userDaoMock.getAllUsers()).thenReturn(dummyUsers);
//		Mockito.when(userDaoMock.getUser("username")).thenReturn(tfu);
//		Mockito.when(userDaoMock.getUser("username2")).thenReturn(tfu2);
//		Mockito.when(userDaoMock.getUser("username3")).thenReturn(null);
//		Mockito.when(userDaoMock.insertUser(tfu)).thenReturn(true);
//		Mockito.when(userDaoMock.insertUser(tfu2)).thenReturn(false);
		
		Mockito.when(userDaoMock.getUser("testadmin")).thenReturn(testAdmin);
		Mockito.when(userDaoMock.getUser("trainer")).thenReturn(trainer);
		Mockito.when(userDaoMock.getUser("salestest")).thenReturn(salesTest);
		Mockito.when(userDaoMock.getUser("bobstage")).thenReturn(bobStage);
		
		Mockito.when(userDaoMock.insertUser(sampleUser1)).thenReturn(true);
		Mockito.when(userDaoMock.insertUser(sampleUser2)).thenReturn(false);

		Mockito.when(mockedService.submitCredentials(testAdmin)).thenReturn(testAdmin);
		
		userService = new UserService(userDaoMock);
	}

	/**
	 * Call the setupMocks method
	 * 
	 * @throws IOException
	 */
	@BeforeTest
	public void beforeAll() throws IOException {
		setupMocks();
	}

	/**
	 * Test that the method reliably returns a list of users
	 */
	@Test(enabled = true)
	public void testGetUsers() {
		List<TfUser> list = userService.getAllUsers();
<<<<<<< HEAD
		
		// grab the first 4, just to simulate getAllUsers
		if(list.size() >= 4) {
			list = list.subList(0, 4);
		}
		System.out.println("List: " + list);
		List<TfUser> mockUsers = userDaoMock.getAllUsers();
		assertEquals(list, mockUsers);
=======
		assertEquals(list.get(0).getId(), 1);
		assertEquals(list.size(), 2);
		assertEquals(list.get(1).getId(), 4);
		System.out.println(list);
>>>>>>> More test changes 8/30
	}

	/**
	 * Test that getUser returns the appropriate type. Test that the appropriate
	 * user is being returned by the service method.
	 */
	@Test(enabled = true)
	public void testGetUser() {
		assertTrue(userDaoMock.getUser("username") instanceof TfUser);
		TfUser user = userDaoMock.getUser("username");
		assertTrue(user.getId() == 1);
		assertFalse(user.getId() == 2);
		assertEquals(user.getId(), 1);
		TfUser user2 = userDaoMock.getUser("username2");
		assertTrue(user2.getId() == 2);
		assertFalse(user2.getId() == 1);
		TfUser user3 = userDaoMock.getUser("username3");
		assertTrue(user3 == null);
		assertNull(user3);
	}

	/**
	 * Test that the method can handle when a user is successfully inserted
	 */
	@Test(enabled = true)
	public void testInsertUserSuccess() {
		boolean result = userService.insertUser(sampleUser1);
		assertEquals(result, true);
		
//		assertTrue(userDaoMock.insertUser(tfu));
//		assertFalse(userDaoMock.insertUser(tfu2));
	}
	
	/**
	 * Test that the method can handle when a user is not properly initialized
	 */
	@Test(enabled = true)
	public void testInsertUserFail() {
		boolean result = userService.insertUser(sampleUser2);
		assertEquals(result, false);
	}

	/**
	 * Test that a user can submit credentials and that it will return a valid user if the 
	 * credentials are accepted and return null if the credentials are not excepted.
	 */
	@Test(enabled = true)
	public void testSubmitCredentials() {
		TfUser user = userService.getUser("Trainer");
		TfUser verifiedUser = userService.submitCredentials(user);
		
		TfUser mockedUser = userDaoMock.getUser("Trainer");
		TfUser verifiedMockUser = mockedService.submitCredentials(user);
		
		boolean expected = mockedUser.equals(verifiedMockUser);
		boolean actual = user.equals(verifiedUser);
		assertEquals(actual, expected);
		// assertTrue(mockedService.submitCredentials(user) instanceof TfUser);
//		user = userDaoMock.getUser("username3");
//		assertTrue(mockedService.submitCredentials(user) == null);
	}
}
