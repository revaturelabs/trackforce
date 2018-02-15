package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import org.hibernate.Session;

import com.revature.model.AssociateInfo;
import com.revature.model.ClientInfo;
import com.revature.model.MarketingStatusInfo;

public interface AssociateDao {

	public AssociateInfo getAssociate(BigDecimal associateid, Session session) throws IOException;
	public Map<BigDecimal, AssociateInfo> getAssociates(Session session);
	void updateInfo(Session session, BigDecimal id, MarketingStatusInfo marketingStatus, ClientInfo client)
			throws IOException;
}
