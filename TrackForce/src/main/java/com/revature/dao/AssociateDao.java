package com.revature.dao;
import java.util.HashMap;
import java.util.List;
import com.revature.criteria.GraphedCriteriaResult;
import com.revature.entity.TfAssociate;

public interface AssociateDao {
	TfAssociate getAssociate(Integer associateid);
	
	List<TfAssociate> getNAssociateMatchingCriteria(int startIdx, int numRes, int mktStatus, int clientId, String sortText, String firstName, String lastName);

	List<TfAssociate> getAllAssociates();
	
	List<TfAssociate> getNAssociates();
	
	HashMap<String,Integer> getStatusCountsMap();

	boolean updateAssociatePartial(TfAssociate associate);

	boolean approveAssociate(int associateId);

	boolean approveAssociates(List<Integer> associateIds);

	boolean createAssociate(TfAssociate newassociate);

	TfAssociate getAssociateByUserId(int id);

	List<GraphedCriteriaResult> getMapped(int id);

	List<GraphedCriteriaResult> getUndeployed(String s);

	boolean updateAssociate(TfAssociate associate);

	boolean updateAssociates(List<TfAssociate> associate);

	long countMappedAssociatesByValue(String column, String value, int mappedStatus);
	
	public void deleteAssociate(TfAssociate associate);
	
	public void deleteOldAssociateProcedure();


}
