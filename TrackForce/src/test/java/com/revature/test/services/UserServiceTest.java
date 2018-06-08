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

    @BeforeTest
    public void beforeAll() throws IOException {
        setupMocks();
    }

    @Test(enabled = true)
    public void testGetUsers() {
    	List<TfUser> list = userService.getAllUsers();
    	assertEquals(list.get(0).getTfUserId(),1);
    }
    
    @Test(enabled = true)
    public void testCreateNewAssociate() {
    	assertTrue(userService.createNewAssociate(new CreateAssociateModel()).getMessage().equals("success"));
    	assertFalse(userService.createNewAssociate(new CreateAssociateModel()).getMessage().equals("failure"));
    }
    
    @Test(enabled = true)
    public void testGetUser() {
    	assertTrue(userDaoMock.getUser("username") instanceof TfUser);
    	TfUser user = userDaoMock.getUser("username");
    	assertTrue(user.getTfUserId() == 1);
    	assertFalse(user.getTfUserId() == 2);
    }
    
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
    }
}
