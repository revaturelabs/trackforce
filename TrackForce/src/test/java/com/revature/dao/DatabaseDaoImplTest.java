package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.testng.annotations.Test;

import com.revature.utils.HibernateUtil;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DatabaseDaoImplTest {

	DatabaseDAOImpl dbDao = new DatabaseDAOImpl();
	Session session;
	Transaction tx;

	@Before
	public void before() {
		try {
			session = HibernateUtil.getSession().openSession();
			tx = session.beginTransaction();
		} catch (Exception e) {
			session.close();
		} finally {
			assertNotEquals(null, session);
			assertEquals(true, session.isOpen());
		}
	}

	public void after() {
		session.flush();
		tx.rollback();
		session.close();
	}
	
//	These tests are presumably obsolete

	@Test(enabled = false)
	public void truncateTables() throws IOException {
		
	}

	@Test(enabled = false)
	public void populateTables() throws IOException {

	}

	@Test(enabled = false)
	public void truncateTablesForSF() throws IOException {

	}

	@Test(enabled = false)
	public void populateTablesSF() throws IOException {
	}

}
