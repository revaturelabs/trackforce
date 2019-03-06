package com.revature.dao;
import java.sql.Timestamp;
import java.util.List;
import com.revature.entity.TfBatch;

/** Accesses various information for the batches. */
public interface BatchDao {
	TfBatch getBatch(String batchName);
	
	TfBatch getBatchById(Integer id);
	
	List<TfBatch> getAllBatches();
	
	List<TfBatch> getBatchesForPredictions(String name, Timestamp startDate, Timestamp endDate);
	
	Object getBatchCountsForPredictions(String name, Timestamp startDate, Timestamp endDate);

	boolean createBatch(TfBatch newbatch);
}