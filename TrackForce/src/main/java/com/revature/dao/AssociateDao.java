package com.revature.dao;

import java.util.List;

import com.revature.criteria.GraphedCriteriaResult;
import com.revature.entity.TfAssociate;

public interface AssociateDao {

	TfAssociate getAssociate(Integer associateid);
	List<TfAssociate> getAllAssociates();
	
	boolean updateAssociatePartial(TfAssociate associate);

	boolean approveAssociate(int associateId);

	boolean approveAssociates(List<Integer> associateIds);

	boolean createAssociate(TfAssociate newassociate);

	TfAssociate getAssociateByUserId(int id);

	List<GraphedCriteriaResult> getMapped(int id);

	List<GraphedCriteriaResult> getUndeployed(String s);

	boolean updateAssociate(TfAssociate associate);

	boolean updateAssociates(List<TfAssociate> associate);
}
