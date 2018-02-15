package com.revature.test.services;

import com.revature.dao.UserDAO;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.request.model.CreateUserModel;
import com.revature.services.CreateUserService;
import com.revature.services.JWTService;
import com.revature.test.BaseTest;
import com.revature.utils.PasswordStorage;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class CreateUserServiceTest extends BaseTest {

    private String username = "MockAdmin", password = "MockAdminPassword";
    private int adminRoleId = 1, userId = 1;

    private TfUser mockUser;

    @Mock
    private UserDAO mockUserDao;

    private JWTService jwtService;

    private CreateUserService userService;

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

        userService = new CreateUserService(jwtService, mockUserDao, sessionFactory);
    }

    @Test
    public void testCreateUser() {  // todo: finish
        CreateUserModel newUserModel = new CreateUserModel();
        newUserModel.setUsername(username);
        newUserModel.setUsername(password);
        newUserModel.setRole(new BigDecimal(adminRoleId));

        userService.createUser(newUserModel);

    }
}