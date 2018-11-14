package com.revature.test.orm.util;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.annotations.Test;

import com.revature.utils.HibernateUtil;

//@PrepareForTest(HibernateUtil.class)
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
	
	@Test
	public void testShutdown() {
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			HibernateUtil.shutdown();
			assertTrue(sf.isClosed() == true);
			assertFalse(sf.isOpen() == true);
		} catch (HibernateException he) {
			fail("Error opening session factory");
		}
	}
	
	@Test(enabled=false)
	public void testRollbackTransaction() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		sf.openSession();
		Transaction t = sf.getCurrentSession().beginTransaction();
		try {
			t.rollback();
		} catch (HibernateException he) {
			fail();
		}
	}
}