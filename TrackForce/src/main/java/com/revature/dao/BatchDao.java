package com.revature.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.engine.jdbc.batch.spi.Batch;

import com.revature.entity.TfBatch;

public interface BatchDao {

	// public List<Batch> selectAll();
	public List<TfBatch> getBatchDetails(Timestamp fromdate, Timestamp todate);
    String getBatchCirriculumName(int batchID);
    int getBatchID(String batchName);
    void insertBatch(Batch batch);
    Batch getBatch(int batchID);
    TfBatch getBatch(String batchName);
}
