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

  @DataProvider
  public Object[][] userID() {
    return new Object[][] {
      new Object[] { new BigDecimal(1) }
    };
  }
  
  @DataProvider
  public Object[][] userName() {
    return new Object[][] {
      new Object[] { "TestAdmin" }
    };
  }
  
  @DataProvider
  public Object[][] user() {
		return new Object[][] {
			new Object[] {new TfUser( new BigDecimal(1), new TfRole(new BigDecimal(2)),
					"jdoe", "password1")}
		};
  }

  /*@Test(dataProvider = "userID")
	public void getUserBigDecimal(BigDecimal userid) {

		SessionFactory sessionFactory = HibernateUtil.getSession();
		assertNotNull(sessionFactory);
		Session session = sessionFactory.openSession();
		assertNotNull(session);

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TfUser> criteriaQuery = builder.createQuery(TfUser.class);
		assertNotNull(criteriaQuery);

		Root<TfUser> root = criteriaQuery.from(TfUser.class);
		criteriaQuery.select(root).where(builder.equal(root.get("tfUserId"), userid));
		Query<TfUser> query = session.createQuery(criteriaQuery);
		//assertEquals(query., TfUser.class);

		TfUser user;

		try {
			user = query.getSingleResult();
		} catch (NoResultException nre) {
			user = new TfUser();
		} finally {
			session.close();
			assertFalse(session.isConnected());
		}

		assertEquals(user.getTfUserId(), userid);
	}*/

  @Test(dataProvider = "userName")
  public void getUser(String username) {
	  SessionFactory sessionFactory = HibernateUtil.getSession();
	  assertNotNull(sessionFactory);
	  TfUser user;
		try (Session session = sessionFactory.openSession()) {
			assertNotNull(session);

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TfUser> criteriaQuery = builder.createQuery(TfUser.class);
			assertNotNull(criteriaQuery);

			Root<TfUser> root = criteriaQuery.from(TfUser.class);
			criteriaQuery.select(root).where(builder.equal(root.get("tfUserUsername"), username));
			Query<TfUser> query = session.createQuery(criteriaQuery);

			try {
				user = query.getSingleResult();
			} catch (NoResultException nre) {
				user = new TfUser();
			}
		}
	  assertEquals(user.getTfUserUsername(), username);
	  System.out.println("Grabbed username: " + user.getTfUserUsername());
  }

  /*@Test(dataProvider = "user")
  public void getUserHash(TfUser user) {
	  // create method for hashing passwords here
	  
	  try {
			System.out.println("Password: " + user.getTfUserHashpassword() + " Hashed password: " 
	  + PasswordStorage.createHash(user.getTfUserHashpassword()));
		} catch (CannotPerformOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  String result = new String();
	  assertFalse(result.isEmpty());
  }*/

  /*@Test(dataProvider = "user")
  public void getUserRole(TfUser user) {
    String role = new String();
    assertFalse(role.isEmpty());
  }*/
}
