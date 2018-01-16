package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import com.revature.entity.TfBatch;

/**
 * Accesses various information for the batches.
 */
public interface BatchDao {

    TfBatch getBatchById(BigDecimal id, Session session) throws IOException;

    /**
     * Get a list of batches that are active between the given dates.
     * @param fromdate - The beginning date of the search range.
     * @param todate - The ending date of the search range.
     * @return - A list of batches within the given dates.
     */
	List<TfBatch> getBatchDetails(Timestamp fromdate, Timestamp todate, Session session) throws IOException;

    /**
     * Gets a singular batch of the given name.
     * @param batchName - The name of the batch to retrieve.
     * @return - A batch object that represents the retrieve batch.
     */

	TfBatch getBatch(String batchName, Session session) throws IOException;

}