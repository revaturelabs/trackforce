package com.revature.dao;

import java.util.List;

import com.revature.model.Batch;

public interface BatchDao {

	// public List<Batch> selectAll();
	public List<Batch> getBatchDetails(String fromdate, String todate);
}
