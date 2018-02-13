package com.revature.dao;

import java.util.List;
import java.util.Set;

import com.revature.entity.TfBatch;
import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;

/**
 * Accesses various information for the batches.
 */
public interface BatchDao {

	TfBatch getBatch(String batchName);
	BatchInfo getBatchById(Integer id);
	List<BatchInfo> getBatchesSortedByDate();
	Set<AssociateInfo> getBatchAssociates(Integer id);
	

}