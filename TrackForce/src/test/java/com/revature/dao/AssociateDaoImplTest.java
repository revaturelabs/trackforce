package com.revature.dao;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.math.BigDecimal;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.model.AssociateInfo;
import com.revature.utils.HibernateUtil;

public class AssociateDaoImplTest {

	AssociateDaoHibernate aDao = new AssociateDaoHibernate();

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
	
	@DataProvider(name="associateId")
	public BigDecimal[] associateIds() {
		BigDecimal[] ids = new BigDecimal[] {new BigDecimal(212), new BigDecimal(213), new BigDecimal(215)};
		return ids;
	}
	
	@Test(dataProvider="associateId")
	public void getAssociate(BigDecimal id) {
        try {
            AssociateInfo result = aDao.getAssociate(id, session);
            assertNotNull(result);
        } catch (IOException e) {
            fail("file-io exception");
        }
	}
}
