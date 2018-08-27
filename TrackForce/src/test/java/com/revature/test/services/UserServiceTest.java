package com.revature.test.services;
import com.revature.dao.UserDao;
import com.revature.entity.TfUser;
import com.revature.services.JWTService;
import com.revature.services.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

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

	/**
	 * Set up the appropriate mocks
	 * 
	 * @throws IOException
	 */
	private void setupMocks() throws IOException {
		MockitoAnnotations.initMocks(this);

		tfu.setId(1);
		tfu.setUsername("TestAdmin");
		tfu.setPassword("TestAdmin");
		tfu2.setId(2);

		List<TfUser> dummyUsers = new ArrayList<>();
		dummyUsers.add(tfu);
		dummyUsers.add(tfu2);

		Mockito.when(userDaoMock.getAllUsers()).thenReturn(dummyUsers);
		Mockito.when(userDaoMock.getUser("username")).thenReturn(tfu);
		Mockito.when(userDaoMock.getUser("username2")).thenReturn(tfu2);
		Mockito.when(userDaoMock.getUser("username3")).thenReturn(null);
		Mockito.when(userDaoMock.insertUser(tfu)).thenReturn(true);
		Mockito.when(userDaoMock.insertUser(tfu2)).thenReturn(false);

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
		assertEquals(list.get(0).getId(), 1);
		assertTrue(list.size() == 2);
		assertFalse(list.get(1).getId() == 4);
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
		TfUser user2 = userDaoMock.getUser("username2");
		assertTrue(user2.getId() == 2);
		assertFalse(user2.getId() == 1);
		TfUser user3 = userDaoMock.getUser("username3");
		assertTrue(user3 == null);
	}

	/**
	 * Test that the method can handle when both a user is successfully inserted and
	 * when
	 */
	@Test(enabled = true)
	public void testInsertUser() {
		assertTrue(userDaoMock.insertUser(tfu));
		assertFalse(userDaoMock.insertUser(tfu2));
	}

	/**
	 * Test that a user can submit credentials and that it will return a valid user if the 
	 * credentials are accepted and return null if the credentials are not excepted.
	 */
	@Test(enabled = true)
	public void testSubmitCredentials() {
		TfUser user = userDaoMock.getUser("username");
		// assertTrue(mockedService.submitCredentials(user) instanceof TfUser);
		user = userDaoMock.getUser("username3");
		assertTrue(mockedService.submitCredentials(user) == null);
	}
}
