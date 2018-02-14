package com.revature.dao;

import java.util.Set;

import com.revature.model.MarketingStatusInfo;

public interface MarketingStatusDao {


	Set<MarketingStatusInfo> getAllMarketingStatuses();
	MarketingStatusInfo getMarketingStatus(String status);

}