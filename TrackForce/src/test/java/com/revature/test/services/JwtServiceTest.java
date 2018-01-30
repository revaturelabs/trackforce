package com.revature.test.services;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDaoImpl;
import com.revature.test.BaseTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.services.JWTService;

public class JwtServiceTest extends BaseTest {
    private JWTService jwt;
    private String adminToken;
    private String vpToken;
    private UserDAO userDao;

    @BeforeTest
    public void beforeTest() {
        userDao = new UserDaoImpl();
        jwt = new JWTService(userDao, sessionFactory);
        adminToken = jwt.createToken("TestAdmin");
        vpToken = jwt.createToken("TestVicePresident");
    }

    @Test
    public void createTokenTest() {
        String token = jwt.createToken("Tester");
        assertNotNull(token);
    }

    @Test
    public void isAdminTest() throws IOException {
        boolean status = jwt.isAdmin(adminToken);
        assertTrue(status);
    }

    @Test()
    public void isAssociateTest() throws IOException {
        boolean status = jwt.isAssociate(vpToken);
        assertFalse(status);
    }

    @Test()
    public void isTokenExpiredTest() {
        Date date = jwt.getExpirationDateFromToken(adminToken);
        assertNotNull(date);
    }
}
