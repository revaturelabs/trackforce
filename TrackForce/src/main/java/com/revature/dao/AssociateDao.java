package com.revature.dao;

import java.util.List;

import com.revature.criteria.GraphedCriteriaResult;
import com.revature.entity.TfAssociate;

public interface AssociateDao {

	TfAssociate getAssociate(Integer associateid);

	List<TfAssociate> getAllAssociates();
	
	List<TfAssociate> getNAssociates();
	
	Object getCountUndeployedMapped();
	
	Object getCountUndeployedUnmapped();
	
	Object getCountDeployedMapped();
	
	Object getCountDeployedUnmapped();

	Object getCountUnmappedTraining();
	
	Object getCountUnmappedOpen();
	
	Object getCountUnmappedSelected();
	
	Object getCountUnmappedConfirmed();
	
	Object getCountMappedTraining();
	
	Object getCountMappedReserved();
	
	Object getCountMappedSelected();
	
	Object getCountMappedConfirmed();

	boolean updateAssociatePartial(TfAssociate associate);

	boolean approveAssociate(int associateId);

	boolean approveAssociates(List<Integer> associateIds);

	boolean createAssociate(TfAssociate newassociate);

	TfAssociate getAssociateByUserId(int id);

	List<GraphedCriteriaResult> getMapped(int id);

	List<GraphedCriteriaResult> getUndeployed(String s);

	boolean updateAssociate(TfAssociate associate);

	boolean updateAssociates(List<TfAssociate> associate);

	<T> T countMappedAssociatesByValue(String column, T value, Integer mappedStatus);

	List<TfAssociate> getAssociatesByTrainerUsername(String username);
}
