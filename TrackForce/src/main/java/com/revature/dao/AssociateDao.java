package com.revature.dao;

import java.util.List;

import com.revature.entity.TfAssociate;

public interface AssociateDao {

	public TfAssociate getAssociate(Integer associateid);
	public List<TfAssociate> getAllAssociates();
	
	public boolean updateAssociate(TfAssociate associate);
	public boolean updateAssociates(List<TfAssociate> associates);
	
	public boolean createAssociate(TfAssociate newassociate);

}
