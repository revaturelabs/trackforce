package com.revature.dao;

import org.testng.annotations.Test;

import com.revature.utils.HibernateUtil;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertTrue;

import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class DatabaseDaoImplTest {

	DatabaseDAOImpl dbDao = new DatabaseDAOImpl();

	@Test
	public void deleteTables() {
		String message;
		Session session = HibernateUtil.getSession().openSession();
		assertNotNull(session);
		try {
			StoredProcedureQuery query2 = session.createStoredProcedureQuery("admin.truncateAllDevTeam");
			query2.execute();
			System.out.println("Delete All Executed");
			message = "Database Emptied Successfully";
		} catch (Exception e) {
			message = "Database Empty Error";
		} finally {
			session.close();
		}
	}

	@Test
	public void populateTables() {
		Session session = HibernateUtil.getSession().openSession();
		assertNotNull(session);
		String message;
		try {
			StoredProcedureQuery query2 = session.createStoredProcedureQuery("admin.populateAllTables_PROC");
			query2.execute();
			System.out.println("Dummy Population Executed");
			message = "Database Population Successfull";

		} catch (Exception e) {
			message = "Error: Data Exists";
		} finally {
			session.close();
		}
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeTest
	public void beforeTest() {

	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {
	}

	@AfterSuite
	public void afterSuite() {
	}

}
