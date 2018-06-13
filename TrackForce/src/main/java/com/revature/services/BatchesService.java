package com.revature.services;

import java.util.List;

import com.revature.dao.BatchDao;
import com.revature.dao.BatchDaoHibernate;
import com.revature.entity.TfBatch;

public class BatchesService {
	
	private static BatchDao dao = new BatchDaoHibernate();
	private BatchesService() {};

	public static TfBatch getBatch(String batchName) {
		return dao.getBatch(batchName);
	}
	public static TfBatch getBatchById(Integer id) {
		return dao.getBatchById(id);
	}
	public static List<TfBatch> getAllBatches(){
		return dao.getAllBatches();
	}

}
