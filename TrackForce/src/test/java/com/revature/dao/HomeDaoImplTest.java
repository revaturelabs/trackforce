package com.revature.dao;

import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.utils.HibernateUtil;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.*;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeSuite;

public class HomeDaoImplTest {
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
  }
  @BeforeMethod
  public void beforeMethod() {
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  @BeforeClass
  public void beforeClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }


  @Test
  public void getAllTfAssociates() {
	  Session session = HibernateUtil.getSession().openSession();
	  assertNotNull(session);
		CriteriaQuery<TfAssociate> cq = session.getCriteriaBuilder().createQuery(TfAssociate.class);
		assertNotNull(cq);
		cq.from(TfAssociate.class);
		assertNotNull(cq);
		assertEquals(cq.getRoots().size(), 1);
		List<TfAssociate> associates = session.createQuery(cq).getResultList();
		assertFalse(associates.isEmpty());

		session.close();
		assertFalse(session.isConnected());
  }
}
