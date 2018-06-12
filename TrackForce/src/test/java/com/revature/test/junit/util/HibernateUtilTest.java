package com.revature.test.junit.util;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.revature.utils.HibernateUtil;

public class HibernateUtilTest {

	@Test
	public void testGetSession() {
		try {
			Session session = HibernateUtil.getSession();
			assertTrue(session != null);
			session.close();
			assertTrue(!session.isOpen());
		} catch (HibernateException he) {
			fail("Error opening session");
		}
	}

	@Test
	public void testGetSessionFactory() {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			assertTrue(sf != null);
			assertTrue(sf.isOpen() == true);
			sf.close();
			assertTrue(sf.isClosed() == true);
		} catch (HibernateException he) {
			fail("Error opening session factory");
		}
	}
}
