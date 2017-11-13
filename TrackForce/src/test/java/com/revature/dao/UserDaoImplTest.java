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

	UserDaoImpl uDao = new UserDaoImpl();

    @DataProvider(name = "userName")
    public String[] userName() {
        return new String[] { "TestAdmin" };
    }

    @DataProvider
    public TfUser[] user() {
        return new TfUser[] { new TfUser(new BigDecimal(1), new TfRole(new BigDecimal(2)), "jdoe", "password1") };
    }


        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TfUser> criteriaQuery = builder.createQuery(TfUser.class);
        Assert.assertNotNull(criteriaQuery);

        Root<TfUser> root = criteriaQuery.from(TfUser.class);
        criteriaQuery.select(root).where(builder.equal(root.get("tfUserUsername"), username));
        Query<TfUser> query = session.createQuery(criteriaQuery);
}
