package com.revature.dao;

import java.util.List;
import java.util.Map;
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
	public Map<Integer, BatchInfo> getBatchDetails(); 
	List<BatchInfo> getBatchesSortedByDate();
	Set<AssociateInfo> getBatchAssociates(Integer id);
	public Set<BatchInfo> getAllBatches();
	public void cacheAllBatches();
	public Map<Integer, BatchInfo> createBatchesMap(List<TfBatch> batchList); 
	

}