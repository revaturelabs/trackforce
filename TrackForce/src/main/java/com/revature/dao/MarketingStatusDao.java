package com.revature.dao;

import java.io.IOException;

import com.revature.entity.TfMarketingStatus;

public interface MarketingStatusDao {

    public TfMarketingStatus getMarketingStatus(String status)  throws IOException;
}