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

	@Test(enabled = false)
	public void truncateTables() throws IOException {
		String result = dbDao.deleteAll(session);
		assertEquals("Database Emptied Successfully", result);
		assertNotEquals("Database Empty Error", result);
	}

	@Test(enabled = false)
	public void populateTables() throws IOException {
		String result = dbDao.populate(session);
		assertEquals("Database Population Successful", result);
		assertNotEquals("Error: Data Exists", result);
	}

	@Test(enabled = false)
	public void truncateTablesForSF() throws IOException {
		String result = dbDao.deleteAll(session);
		assertEquals("Database Emptied Successfully", result);
		assertNotEquals("Database Empty Error", result);
	}

	@Test(enabled = false)
	public void populateTablesSF() throws IOException {
		String result = dbDao.populateSF(session);
		assertEquals("SF - Database Population Successful", result);
		assertNotEquals("Error: Data Exists", result);
	}

}
