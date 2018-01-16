package com.revature.dao;

import static org.testng.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.utils.HibernateUtil;
import com.revature.utils.PasswordStorage;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;

public class UserDaoImplTest {

	Session session;
	Transaction tx;
	
	UserDaoImpl uDao = new UserDaoImpl();

	@BeforeTest
	public void before() throws HibernateException, IOException {
		session = HibernateUtil.getSession().openSession();
		tx = session.beginTransaction();
	}
	
	@AfterTest
	public void after() {
		session.flush();
		tx.rollback();
		session.close();
	}
	
	@DataProvider(name = "userName")
	public String[] userName() {
		return new String[] { "TestAdmin" };
	}
	
	
	@DataProvider(name = "user")
	  public Object[][] user() {
			return new Object[][] {
				new Object[] {new TfUser(1)}
			};
	}


	@Test(dataProvider = "userName")
	public void getUserString(String username) throws IOException {
		TfUser result = uDao.getUser(username, session);
		assertNotNull(result);
		System.out.println("username: " + result.getTfUserUsername());
	}

}
