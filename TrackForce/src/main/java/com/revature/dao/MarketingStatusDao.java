package com.revature.dao;

import java.util.Map;

import com.revature.entity.TfMarketingStatus;
import com.revature.model.MarketingStatusInfo;

public interface MarketingStatusDao {

    public TfMarketingStatus getMarketingStatus(String status);

	Map<Integer, MarketingStatusInfo> getMarketingStatuses();
}