package com.revature.test.services;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDaoImpl;
import com.revature.utils.HibernateUtil;
import com.revature.utils.TestDBLoaderUtil;
import com.revature.utils.TestDBUtil;
import org.hibernate.SessionFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.services.JWTService;

public class JwtServiceTest {
    private JWTService jwt;
    private String adminToken;
    private String vpToken;

    @BeforeTest
    public void beforeTest() throws SQLException, IOException, ClassNotFoundException {
        SessionFactory sessionFactory = TestDBUtil.getSessionFactory();
        new TestDBLoaderUtil().load();

        UserDAO userDao = new UserDaoImpl(sessionFactory);
        jwt = new JWTService(userDao, sessionFactory);
        adminToken = jwt.createToken("TestAdmin");
        vpToken = jwt.createToken("TestVicePresident");
    }

    @AfterTest
    public void afterTest() {
        HibernateUtil.shutdown();
    }

    @Test(priority = 1)
    public void createTokenTest() {
        String token = jwt.createToken("Tester");
        assertNotNull(token);
    }

    @Test(enabled = true)
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
