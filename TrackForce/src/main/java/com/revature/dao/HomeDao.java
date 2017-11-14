package com.revature.dao;

import java.util.List;

import com.revature.entity.TfAssociate;

public interface HomeDao {
	
	/**
	 * Returns a list of TfAssociate objects.
	 * @return a list of TfAssociate objects.
	 */
	List<TfAssociate> getAllTfAssociates();

}
