package com.revature.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.revature.entity.TfAssociate;
import com.revature.model.AssociateInfo;
import com.revature.model.InterviewInfo;
import com.revature.request.model.AssociateFromClient;

public interface AssociateDao {

	public AssociateInfo getAssociate(Integer associateid);
	public AssociateInfo getAssociateFromDB(Integer associateid);
	public Map<Integer, AssociateInfo> getAssociates();
	public Set<AssociateInfo> getAllAssociates();
	public void cacheAllAssociates();
	void updateAssociates(List<Integer> associateids, Integer marketingStatus, Integer clientid);
	public Map<Integer, AssociateInfo> createAssociatesMap(List<TfAssociate> associateList);
	public void updateAssociate(AssociateFromClient afc);
	public Set<InterviewInfo> getInterviewsByAssociate(Integer associateId);
	public void updateAssociateVerification(int associateid);

}
