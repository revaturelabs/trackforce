package com.revature.dao;

import java.util.List;

import com.revature.entity.TfBatch;

/**
 * Accesses various information for the batches.
 */
public interface BatchDao {

	public TfBatch getBatch(String batchName);
	public TfBatch getBatchById(Integer id);
	public List<TfBatch> getAllBatches();

}
