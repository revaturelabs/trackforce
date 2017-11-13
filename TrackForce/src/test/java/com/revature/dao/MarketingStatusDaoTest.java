package com.revature.dao;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.entity.TfMarketingStatus;

public class MarketingStatusDaoTest {

	MarketingStatusDaoHibernate msdao = new MarketingStatusDaoHibernate();
	
	@DataProvider(name = "MarketingStatus")
	public static String[] marketingStatus() {
		String[] dp = new String[] {"MAPPED: TRAINING", "MAPPED: RESERVED", "MAPPED: SELECTED", "MAPPED: CONFIRMED", "MAPPED: DEPLOYED", "UNMAPPED: TRAINING", "UNMAPPED: OPEN", "UNMAPPED: SELECTED", "UNMAPPED: CONFIRMED", "UNMAPPED: DEPLOYED", "DIRECTLY PLACED", "TERMINATED"};
		return dp;
	}
	
	@Test(dataProvider="MarketingStatus")
	public void getMarketingStatus(String marketingStatus) {
		TfMarketingStatus tfms = msdao.getMarketingStatus(marketingStatus);
		assertNotNull(tfms);
	}
}
