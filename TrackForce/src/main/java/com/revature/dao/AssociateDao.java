package com.revature.dao;

import java.util.List;

import com.revature.entity.TfAssociate;

public interface AssociateDao {

	TfAssociate getAssociate(Integer associateid);
	List<TfAssociate> getAllAssociates();
	
	boolean updateAssociate(TfAssociate associate);
	boolean updateAssociates(List<TfAssociate> associates);
	
	boolean createAssociate(TfAssociate newassociate);

	TfAssociate getAssociateByUserId(int id);
}
