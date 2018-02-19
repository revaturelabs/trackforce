package com.revature.test.dao;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDaoImpl;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.request.model.CreateUserModel;
import com.revature.test.BaseTest;
import org.hibernate.Session;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.*;

public class UserDAOTest extends BaseTest {

    private Session session;
    private UserDAO userDao;

    @BeforeMethod
    public void startTransaction() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        userDao = new UserDaoImpl();
    }

    @AfterMethod
    public void rollback() {
        rollbackAndClose(session);
    }

    @Test
    public void testGetUser() throws Exception {
        TfUser user = userDao.getUser("TestAdmin", session);
        TfRole role = user.getTfRole();
        assertNotNull(role);
        assertNotNull(role.getTfRoleId());
        assertNotNull(role.getTfRoleName());
    }

    @Test
    public void testCreateUser() throws Exception {
        // insert new user
        CreateUserModel model = new CreateUserModel();
        String username = "testingAdmin";
        int roleId = 1;
        model.setUsername(username);
        model.setPassword("testingAdmin");
        model.setRole(new BigDecimal(roleId));   // admin role
        userDao.createUser(model, session);

        // make sure we can retrieve user
        TfUser retrieved = userDao.getUser(username, session);
        assertEquals(username, retrieved.getTfUserUsername());
        assertEquals(roleId, retrieved.getTfRole().getTfRoleId().intValueExact());
    }
}