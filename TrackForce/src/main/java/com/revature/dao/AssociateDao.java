package com.revature.dao;

import java.util.Map;

import com.revature.model.AssociateInfo;

public interface AssociateDao {

	public AssociateInfo getAssociate(Integer associateid);
	public Map<Integer, AssociateInfo> getAssociates();
	public void updateAssociates(Integer[] arr, Integer marketingStatus, Integer client);
}
