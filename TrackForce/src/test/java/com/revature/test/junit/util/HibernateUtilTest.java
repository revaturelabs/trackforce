package com.revature.test.junit.util;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.revature.utils.HibernateUtil;

// Needs more tests, HibernateUtil got updated
public class HibernateUtilTest {

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
