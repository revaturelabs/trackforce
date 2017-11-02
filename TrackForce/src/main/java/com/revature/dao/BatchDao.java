package com.revature.dao;

import java.sql.Timestamp;
import java.util.List;

import com.revature.entity.TfBatch;

public interface BatchDao {

	// public List<Batch> selectAll();
	public List<TfBatch> getBatchDetails(Timestamp fromdate, Timestamp todate);
    //String getBatchCirriculumName(int batchID);
    //int getBatchID(String batchName);
    //void insertBatch(TfBatch batch);
    TfBatch getBatch(String batchName);
}
