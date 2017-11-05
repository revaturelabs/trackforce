package com.revature.dao;

import java.util.List;

import com.revature.entity.TfBatch;

public interface BatchDao {


	public List<TfBatch> getBatchDetails(String fromdate, String todate);
    public TfBatch getBatch(String batchName);

}
