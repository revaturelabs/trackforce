package com.revature.dao;

import java.io.IOException;

import org.hibernate.Session;

import com.revature.entity.TfMarketingStatus;

public interface MarketingStatusDao {

    public TfMarketingStatus getMarketingStatus(Session session, String status)  throws IOException;
}