package com.revature.dao;

import static org.testng.Assert.assertNotNull;

import org.hibernate.Session;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.entity.TfMarketingStatus;
import com.revature.utils.HibernateUtil;

import java.io.IOException;

public class MarketingStatusDaoTest {

	MarketingStatusDaoHibernate msdao = new MarketingStatusDaoHibernate();
	
	@DataProvider(name = "MarketingStatus")
	public static String[] marketingStatus() {
		String[] dp = new String[] {"MAPPED: TRAINING", "MAPPED: RESERVED", "MAPPED: SELECTED", "MAPPED: CONFIRMED", "MAPPED: DEPLOYED", "UNMAPPED: TRAINING", "UNMAPPED: OPEN", "UNMAPPED: SELECTED", "UNMAPPED: CONFIRMED", "UNMAPPED: DEPLOYED", "DIRECTLY PLACED", "TERMINATED"};
		return dp;
	}
	
	@Test(dataProvider="MarketingStatus")
	public void getMarketingStatus(String marketingStatus) throws IOException {
		Session session = HibernateUtil.getSession().openSession();
		TfMarketingStatus tfms = msdao.getMarketingStatus(session, marketingStatus);
		assertNotNull(tfms);
	}
}
