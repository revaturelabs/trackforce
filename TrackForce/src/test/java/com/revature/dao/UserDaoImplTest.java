package com.revature.dao;

import static org.testng.Assert.*;

import java.math.BigDecimal;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.utils.HibernateUtil;
import com.revature.utils.PasswordStorage;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;

public class UserDaoImplTest {

    UserDAO uDao = new UserDaoImpl();

    @DataProvider(name = "userName")
    public String[] userName() {
        return new String[] { "TestAdmin" };
    }

    @DataProvider
    public TfUser[] user() {
        return new TfUser[] { new TfUser(new BigDecimal(1), new TfRole(new BigDecimal(2)), "jdoe", "password1") };
    }

    @Test(dataProvider = "userName")
    public void getUserString(String username) {
        SessionFactory sessionFactory = HibernateUtil.getSession();
        assertNotNull(sessionFactory);
        Session session = sessionFactory.openSession();
        assertNotNull(session);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TfUser> criteriaQuery = builder.createQuery(TfUser.class);
        assertNotNull(criteriaQuery);

        Root<TfUser> root = criteriaQuery.from(TfUser.class);
        criteriaQuery.select(root).where(builder.equal(root.get("tfUserUsername"), username));
        Query<TfUser> query = session.createQuery(criteriaQuery);

        TfUser user;

        try {
            user = query.getSingleResult();
        } catch (NoResultException nre) {
            user = new TfUser();
        } finally {
            session.close();
            assertFalse(session.isConnected());
        }
        assertEquals(user.getTfUsername(), username);
    }

    @Test(dataProvider = "user")
    public void getUserHash(TfUser user) {
        // create method for hashing passwords here

        try {
            System.out.println("Password: " + user.getTfHashpassword() + " Hashed password: "
                    + PasswordStorage.createHash(user.getTfHashpassword()));
        } catch (CannotPerformOperationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String result = new String();
        assertFalse(result.isEmpty());
    }

    @Test(dataProvider = "user")
    public void getUserRole(TfUser user) {
        String role = new String();
        assertFalse(role.isEmpty());
    }
}
