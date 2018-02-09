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
import com.revature.model.ClientInfo;
import com.revature.model.MarketingStatusInfo;

public interface AssociateDao {

	public AssociateInfo getAssociate(BigDecimal associateid, Session session) throws IOException;
	public Map<BigDecimal, AssociateInfo> getAssociates(Session session);
	public Map<BigDecimal, AssociateInfo> getAssociates();
	void updateInfo(Session session, BigDecimal id, MarketingStatusInfo marketingStatus, ClientInfo client)
			throws IOException;
}
