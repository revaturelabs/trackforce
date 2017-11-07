package com.revature.dao;

import com.revature.entity.TfMarketingStatus;

public interface MarketingStatusDao {

    public TfMarketingStatus getMarketingStatus(String status);
}