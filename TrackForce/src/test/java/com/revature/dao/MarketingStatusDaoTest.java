package com.revature.dao;

import static org.testng.Assert.assertNotNull;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.entity.TfMarketingStatus;
import com.revature.utils.HibernateUtil;

import java.io.IOException;

public class MarketingStatusDaoTest {

	MarketingStatusDaoHibernate msdao = new MarketingStatusDaoHibernate();
	
	Session session;
	Transaction tx;
	
	@BeforeClass
	public void before() throws HibernateException, IOException {
		session = HibernateUtil.getSession().openSession();
		tx = session.beginTransaction();
	}
	
	@AfterClass
	public void after() {
		session.flush();
		tx.rollback();
		session.close();
	}
	
	@DataProvider(name = "MarketingStatus")
	public static String[] marketingStatus() {
		String[] dp = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		return dp;
	}
	
	@Test(dataProvider="MarketingStatus")
	public void getMarketingStatus(String marketingStatus) throws IOException {
		TfMarketingStatus tfms = msdao.getMarketingStatus(session, marketingStatus);
		assertNotNull(tfms);
	}
}
