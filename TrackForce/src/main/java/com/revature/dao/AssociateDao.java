package com.revature.dao;

import java.math.BigDecimal;
import java.util.Map;

import com.revature.model.AssociateInfo;

public interface AssociateDao {

	public AssociateInfo getAssociate(BigDecimal associateid);
	public Map<BigDecimal, AssociateInfo> getAssociates();
	public boolean updateAssociate(BigDecimal id, int marketingStatus, int client);
	public boolean updateAssociates(BigDecimal[] id, int marketingStatus, int client);
}
