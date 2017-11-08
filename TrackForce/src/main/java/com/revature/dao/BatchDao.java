package com.revature.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;

/**
 * Accesses various information for the batches.
 */
public interface BatchDao {

    /**
     * Get a list of batches that are active between the given dates.
     * @param fromdate - The beginning date of the search range.
     * @param todate - The ending date of the search range.
     * @return - A list of batches within the given dates.
     */
	public List<TfBatch> getBatchDetails(Timestamp fromdate, Timestamp todate);
	
	/**
	 * Gets a singular batch of the given name.
	 * @param batchName - The name of the batch to retrieve.
	 * @return - A batch object that represents the retrieve batch.
	 */
    public TfBatch getBatch(String batchName);
  }
