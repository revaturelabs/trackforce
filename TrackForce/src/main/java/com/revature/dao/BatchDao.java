package com.revature.dao;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;

import com.revature.entity.TfBatch;

public interface BatchDao {


	public List<TfBatch> getBatchDetails(String fromdate, String todate);
    TfBatch getBatch(String batchName);

}
