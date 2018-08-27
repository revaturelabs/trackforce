package com.revature.test.junit.util;
import com.revature.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

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
	
	@Ignore
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