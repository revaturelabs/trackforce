package com.revature.dao;

import java.sql.Timestamp;
import java.util.List;

import com.revature.entity.TfBatch;

public interface BatchDao {


	public List<TfBatch> getBatchDetails(Timestamp fromdate, Timestamp todate);
    TfBatch getBatch(String batchName);
}
