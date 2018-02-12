package com.revature.dao;

import java.math.Integer;
import java.util.Map;
import java.util.Set;

import com.revature.model.AssociateInfo;

public interface AssociateDao {

	public AssociateInfo getAssociate(Integer associateid) throws IOException;
	public Map<Integer, AssociateInfo> getAssociates();
	void updateInfo(Session session, Integer id, int marketingStatus, int client);
	public AssociateInfo getAssociate(BigDecimal associateid);
	public Map<BigDecimal, AssociateInfo> getAssociates();
	public boolean updateAssociate(BigDecimal id, int marketingStatus, int client);
	public boolean updateAssociates(BigDecimal[] id, int marketingStatus, int client);
	public AssociateInfo getAssociate(BigDecimal associateid);
	public Map<BigDecimal, AssociateInfo> getAssociates();
	public boolean updateAssociate(BigDecimal id, int marketingStatus, int client);
	public boolean updateAssociates(BigDecimal[] id, int marketingStatus, int client);
}
