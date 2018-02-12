package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

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

	TfBatch getBatch(String batchName, Session session) throws IOException;

	Map<Integer, BatchInfo> getBatchDetails(Session session) throws IOException;

}