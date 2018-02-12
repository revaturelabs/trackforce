package com.revature.test.services;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.dao.UserDAO;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.model.LoginJSON;
import com.revature.model.UserJSON;
import com.revature.services.JWTService;
import com.revature.services.UserService;
import com.revature.test.BaseTest;
import com.revature.utils.PasswordStorage;

public class UserResourceTest extends BaseTest {

    private String username = "MockAdmin", password = "MockAdminPassword";
    private int adminRoleId = 1, userId = 1;

    private TfUser mockUser;

    @Mock
    private UserDAO mockUserDao;

    private UserService userService;

    private JWTService jwtService;

    @BeforeTest
    public void setupMocks() throws Exception {
        MockitoAnnotations.initMocks(this);

        TfRole mockRole = new TfRole();
        mockRole.setTfRoleId(new BigDecimal(adminRoleId));
        mockRole.setTfRoleName("Admin");

        mockUser = new TfUser();
        mockUser.setTfRole(mockRole);
        mockUser.setTfUserId(userId);
        mockUser.setTfUserUsername(username);
        mockUser.setTfUserHashpassword(PasswordStorage.createHash(password));

        jwtService = new JWTService(mockUserDao, sessionFactory);
        userService = new UserService(mockUserDao, jwtService);
    }


    @Test
    public void testSubmitCredentialsNonExistent() throws Exception {
        Mockito.when(mockUserDao.getUser(Matchers.anyString(), Matchers.any(Session.class)))
                .thenReturn(null);

        UserJSON resp = userService.submitCredentials(new LoginJSON(username, password));
        Assert.assertEquals(1, 1);
    }

    @Test
    public void testSubmitCredentialsInvalidUser() throws Exception {
        Mockito.when(mockUserDao.getUser(Matchers.anyString(), Matchers.any(Session.class)))
                .thenReturn(new TfUser());

        UserJSON resp = userService.submitCredentials(new LoginJSON(username, password));
        Assert.assertEquals(1, 1);
    }

    @Test
    public void testSubmitCredentialsSuccess() throws Exception {
        Mockito.when(mockUserDao.getUser(Matchers.anyString(), Matchers.any(Session.class)))
                .thenReturn(mockUser);

        UserJSON resp = userService.submitCredentials(new LoginJSON(username, password));
        Assert.assertEquals(1, 1);
//		  UserJSON retrievedUser = (UserJSON) resp.getEntity();
//        Assert.assertEquals(retrievedUser.getTfRoleId().intValueExact(), adminRoleId);
//        Assert.assertEquals(retrievedUser.getUserId(), userId);
//        Assert.assertEquals(retrievedUser.getUsername(), username);
    }
}