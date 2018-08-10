package com.revature.dao;

import java.sql.Timestamp;
import java.util.List;

import com.revature.entity.TfBatch;

/**
 * Accesses various information for the batches.
 */
public interface BatchDao {

	public TfBatch getBatch(String batchName);
	public TfBatch getBatchById(Integer id);
	public List<TfBatch> getAllBatches();
	public List<TfBatch> getBatchesForPredictions(String name, Timestamp startDate, Timestamp endDate);
	public Object getBatchCountsForPredictions(String name, Timestamp startDate, Timestamp endDate);

}
