package com.revature.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.revature.entity.TfAssociate;
import com.revature.model.AssociateInfo;

public interface AssociateDao {

	public AssociateInfo getAssociate(Integer associateid);
	public Map<Integer, AssociateInfo> getAssociates();
	public Set<AssociateInfo> getAllAssociates();
	public void cacheAllAssociates();
	void updateAssociates(List<Integer> associateids, Integer marketingStatus, Integer clientid);
	void updateAssociates(List<AssociateInfo> associates);
	public Map<Integer, AssociateInfo> createAssociatesMap(List<TfAssociate> associateList);
	public void updateAssociate(Integer associateid, Long startDate);
}
