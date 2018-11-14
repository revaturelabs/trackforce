package com.revature.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.revature.daoimpl.MarketingStatusDaoImpl;
import com.revature.entity.TfMarketingStatus;



public class MarketingStatusDAOTest {

  @Test
  public void canGetMarketingStatusByValidID() {
	  TfMarketingStatus statusObj = new MarketingStatusDaoImpl().getMarketingStatusById(1);
	  Assert.assertEquals(statusObj.getName(), "MAPPED: TRAINING");
  }
  
  @Test(expectedExceptions = NullPointerException.class)
  public void invalidIDReturnsNullMarketingStatus() {  
	  TfMarketingStatus statusObj = new MarketingStatusDaoImpl().getMarketingStatusById(1321312243);
	  Assert.assertEquals(statusObj.getName(), null);
  }
  
  @Test
  public void allMarketingStatusDoesnotReturnEmptyList() {  
	  List<TfMarketingStatus> list = new MarketingStatusDaoImpl().getAllMarketingStatuses();
	  Assert.assertNotEquals(list.size(), 0);
  }
  
  @Test
  public void allMarketingStatusReturnsCorrectSizeList() {  
	  List<TfMarketingStatus> list = new MarketingStatusDaoImpl().getAllMarketingStatuses();
	  Assert.assertEquals(list.size(), 12);
  }
  
  @Test
  public void allMarketingListContainsAllStatuses() {  
	  List<TfMarketingStatus> list = new MarketingStatusDaoImpl().getAllMarketingStatuses();
	  List<String> statusesActual = new ArrayList<String>();
	  for(TfMarketingStatus x : list) {
		  statusesActual.add(x.getName());
	  }
	  String[] statuses = {"MAPPED: TRAINING",
			  "MAPPED: RESERVED",
			  "MAPPED: SELECTED",
			  "MAPPED: CONFIRMED",
			  "MAPPED: DEPLOYED",
			  "UNMAPPED: TRAINING",
			  "UNMAPPED: OPEN",
			  "UNMAPPED: SELECTED",
			  "UNMAPPED: CONFIRMED",
			  "UNMAPPED: DEPLOYED",
			  "DIRECTLY PLACED",
			  "TERMINATED"};
	  
	  List<String> statusesExpected = new ArrayList<String>();
	  
	  for(String x : statuses) {
		  statusesExpected.add(x);
	  }

	  Assert.assertEquals(statusesExpected, statusesActual);
  }
  
}
