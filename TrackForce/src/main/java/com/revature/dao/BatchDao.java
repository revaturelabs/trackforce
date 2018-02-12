package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import com.revature.entity.TfBatch;
import com.revature.model.BatchInfo;

/**
 * Accesses various information for the batches.
 */
public interface BatchDao {
	
    /**
     * Gets a singular batch of the given name.
     * @param batchName - The name of the batch to retrieve.
     * @return - A batch object that represents the retrieve batch.
     */

	TfBatch getBatch(String batchName) throws IOException;
	TfBatch getBatchById(int id);
	Map<BigDecimal, BatchInfo> getBatchDetails() throws IOException;

}