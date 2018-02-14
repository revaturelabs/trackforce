package com.revature.dao;

import java.util.List;
import java.util.Map;

import com.revature.model.AssociateInfo;

public interface AssociateDao {

	public AssociateInfo getAssociate(Integer associateid);
	public Map<Integer, AssociateInfo> getAssociates();
	void updateAssociates(List<Integer> ids, Integer marketingStatus, Integer clientid);
	void updateAssociates(List<AssociateInfo> associates);
}
