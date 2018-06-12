package com.revature.test.services;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.dao.UserDAO;
import com.revature.entity.TfUser;
import com.revature.model.LoginJSON;
import com.revature.model.UserJSON;
import com.revature.request.model.CreateAssociateModel;
import com.revature.services.JWTService;
import com.revature.services.UserService;

/**
 * This class tests major functionalities related to the UserService class using mockito to
 * mock the userDao, UserService, and JWTservice. 
 * @author Jesse
 * @since 6.18.06.08
 */
public class UserServiceTest{

    @Mock
    private UserDAO userDaoMock;
    
    @Mock
    private UserService mockedService;
    
    @Mock
    private JWTService jwtService;
    
    @InjectMocks
    private UserService userService;

    UserJSON ui = new UserJSON();
    
    /**
     * Set up the appropriate mocks
     * @throws IOException
     */
    private void setupMocks() throws IOException {
        MockitoAnnotations.initMocks(this);
        
        ui.setToken("sometoken");
        ui.setTfRoleId(1);
        ui.setUserId(1);
        ui.setUsername("username");
        
        TfUser tfu = new TfUser();
        tfu.setTfUserId(1);
        TfUser tfu2 = new TfUser();
        tfu2.setTfUserId(2);
        
        List<TfUser> dummyUsers = new ArrayList<>();
        dummyUsers.add(tfu);
        
        Mockito.when(userDaoMock.getAllUsers()).thenReturn(dummyUsers);
        Mockito.when(userDaoMock.getUser("username")).thenReturn(tfu);
        Mockito.when(userDaoMock.getUser("username2")).thenReturn(tfu2);
        Mockito.when(userDaoMock.createAssociate(Mockito.any(CreateAssociateModel.class))).thenReturn(true);
        Mockito.when(mockedService.submitCredentials(Mockito.any(LoginJSON.class))).thenReturn(ui);

        userService = new UserService(userDaoMock, jwtService);
    }

    /**
     * Call the setupMocks method
     * @throws IOException
     */
    @BeforeTest
    public void beforeAll() throws IOException {
        setupMocks();
    }

    /**
     * Test that the appropriate list is grabbed when the method is called by testing a known
     * user in that list
     */
    @Test(enabled = true)
    public void testGetUsers() {
    	List<TfUser> list = userService.getAllUsers();
    	assertEquals(list.get(0).getTfUserId(),1);
    }
    
    /**
     * Test that the appropriate message is displayed depending on what is passed into the
     * createNewAssociate method
     */
    @Test(enabled = true)
    public void testCreateNewAssociate() {
    	assertTrue(userService.createNewAssociate(new CreateAssociateModel()).getMessage().equals("success"));
    	assertFalse(userService.createNewAssociate(new CreateAssociateModel()).getMessage().equals("failure"));
    }
    
    /**
     * Test that getUser returns the appropriate type. Test that the appropriate user is being
     * returned by the service method.
     */
    @Test(enabled = true)
    public void testGetUser() {
    	assertTrue(userDaoMock.getUser("username") instanceof TfUser);
    	TfUser user = userDaoMock.getUser("username");
    	assertTrue(user.getTfUserId() == 1);
    	assertFalse(user.getTfUserId() == 2);
    }
    
    /**
     * Test that the appropriate type is being returned. Test that the all the data of the
     * UserJSON is what we would expect to be returned by the testSubmitCredentials method
     * @throws IOException
     */
    @Test(enabled = true)
    public void testSubmitCredentials() throws IOException {
    	assertTrue(mockedService.submitCredentials(new LoginJSON()) instanceof UserJSON);
    	UserJSON uj = mockedService.submitCredentials(new LoginJSON());
    	System.out.println(uj);
    	assertTrue(uj.getUsername().equals("username"));
    	assertFalse(uj.getUsername().equals("USERNAME"));
    	assertTrue(uj.getTfRoleId() == 1);
    	assertTrue(uj.getToken().equals("sometoken"));
    	assertTrue(uj.getUserId() != 2);
    	assertFalse(uj.getToken().equals("anothertoken"));
    }
}
