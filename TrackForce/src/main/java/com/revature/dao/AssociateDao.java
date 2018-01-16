package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;
import com.revature.model.AssociateInfo;

public interface AssociateDao {

    public void updateInfo(Session session, BigDecimal id, TfMarketingStatus marketingStatus, TfClient client) throws IOException;
	public AssociateInfo getAssociate(BigDecimal associateid, Session session) throws IOException;
	public Map<BigDecimal, AssociateInfo> getAssociates(Session session);
}
