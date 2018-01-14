package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;

import org.hibernate.Session;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;

public interface AssociateDao {

    public void updateInfo(Session session, BigDecimal id, TfMarketingStatus marketingStatus, TfClient client) throws IOException;
    public TfAssociate getAssociate(BigDecimal associateid) throws IOException;
}
