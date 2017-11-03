package com.revature.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.engine.jdbc.batch.spi.Batch;

import com.revature.entity.TfBatch;

public interface BatchDao {

	// public List<Batch> selectAll();
	public List<TfBatch> getBatchDetails(String fromdate, String todate);
    String getBatchCirriculumName(int batchID);
    BigDecimal getBatchID(String batchName);
    void insertBatch(Batch batch);
    TfBatch getBatch(int batchID);
    TfBatch getBatch(String batchName);
}
