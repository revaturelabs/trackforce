package com.revature.test;

import java.sql.Timestamp;

import java.util.List;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.revature.entity.TfBatch;
import com.revature.services.BatchesService;

public class testBatchesService {
  @Test
  public void testGetBatchInfo() {
	  BatchesService batchesService = new BatchesService();
	  TfBatch batch = batchesService.getBatchInfo("1709 Sept11 JTA");
	  System.out.println(batch);
  }
  
  
  @Test
  public void testGetBatches() {
	  long firstDate = Timestamp.valueOf("2017-09-10 10:10:10.0").getTime();
	  long secondDate = Timestamp.valueOf("2017-12-30 10:10:10.0").getTime();
	  BatchesService batchesService = new BatchesService();
	  List<TfBatch> batches = batchesService.getBatches(firstDate, secondDate);
	  Assert.assertNotNull(batches);
  }
  
  
}
