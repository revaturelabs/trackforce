package com.revature.test.services;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.dao.UserDAO;
import com.revature.entity.TfUser;
import com.revature.model.UserJSON;
import com.revature.services.JWTService;
import com.revature.services.UserService;

public class UserServiceTest{

    @Mock
    private UserDAO userDaoMock;
    
    @Mock
    private JWTService jwtService;
    
    private UserService userService;

    private void setupMocks() throws IOException {
        MockitoAnnotations.initMocks(this);
        
        UserJSON ui = new UserJSON();
        ui.setToken("sometoken");
        ui.setTfRoleId(1);
        ui.setUserId(1);
        ui.setUsername("username");
        
        TfUser tfu = new TfUser();
        tfu.setTfUserId(1);
        
        List<TfUser> dummyUsers = new ArrayList<>();
        dummyUsers.add(tfu);
        
        
        Mockito.when(userDaoMock.getAllUsers()).thenReturn(dummyUsers);
        Mockito.when(userDaoMock.getUser("username")).thenReturn(tfu);

        userService = new UserService(userDaoMock, jwtService);
    }

    @BeforeTest
    public void beforeAll() throws IOException {
        setupMocks();
    }

    @Test(enabled = true)
    public void testGetUsers() throws Exception {
    	List<TfUser> list = userService.getAllUsers();
    	assertEquals(list.get(0).getTfUserId(),1);
    	
    }
}
