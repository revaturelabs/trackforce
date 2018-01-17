package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.entity.TfMarketingStatus;
import com.revature.model.MarketingStatusInfo;

public interface MarketingStatusDao {

    public TfMarketingStatus getMarketingStatus(Session session, String status)  throws IOException;

	Map<BigDecimal, MarketingStatusInfo> getMarketingStatuses(Session session) throws HibernateException, IOException;
}