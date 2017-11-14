package com.revature.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;
import com.revature.services.BatchesService;

public class testBatchesService {

	Properties properties = new Properties();
	
	  @Test
	  public void testGetBatchChartInfo() {
			BatchesService batchesService = new BatchesService();

			List<Map<String, Object>> chartInfo = batchesService.getBatchChartInfo(
					Timestamp.valueOf(properties.getProperty("from_date")).getTime(),
					Timestamp.valueOf(properties.getProperty("to_date")).getTime());

			for (Map<String, Object> curriculumData : chartInfo) {
				Assert.assertNotNull(curriculumData.get("value"));
			}
	  }
	  
	  @Test
	  public void testGetBatchChartInfoNeg() {
		  
			BatchesService batchesService = new BatchesService();
			//dates given do not make a date range
			List<Map<String, Object>> chartInfo = batchesService.getBatchChartInfo(
					Timestamp.valueOf(properties.getProperty("to_date")).getTime(),
					Timestamp.valueOf(properties.getProperty("from_date")).getTime());
			Assert.assertTrue(chartInfo.isEmpty());
			
		  
	  }
		
	  @Test (enabled = false)
	  public void testGetBatchInfoNeg() {
		  BatchesService batchesService = new BatchesService();
		  BatchInfo batch = batchesService.getBatchInfo(properties.getProperty("improper_batch_name"));
		  
		  Assert.assertEquals(batch, new BatchInfo());
	  }
	  
	  @Test
	  public void testGetBatchInfo() {
		  BatchesService batchesService = new BatchesService();
		  BatchInfo batch = batchesService.getBatchInfo(properties.getProperty("batch_name"));
		  
		  Assert.assertEquals(batch.getBatchName(), properties.getProperty("batch_name"));
	  }
	  
	  
	  @Test
	  public void testGetMappedData() {
		  BatchesService batchesService = new BatchesService();
		  
		  Map<String, Integer> mappedOrUnmapped = batchesService.getMappedData(properties.getProperty("batch_name"));
		  Assert.assertTrue(mappedOrUnmapped.containsKey(properties.getProperty("mapped")));
		  Assert.assertTrue(mappedOrUnmapped.containsKey(properties.getProperty("unmapped")));
	  
	  
	  }
	  
	  @Test
	  public void testGetMappedDataNeg() {
		  BatchesService batchesService = new BatchesService();
		  
		  Map<String, Integer> mappedOrUnmapped = batchesService.getMappedData(properties.getProperty("batch_name"));
		  
		  for(Integer value : mappedOrUnmapped.values()) {
			   Assert.assertFalse(value.intValue() < Integer.parseInt((properties.getProperty("zero"))));
			  
		  }
	  
	  
	  }
	  
	  
	  @Test
	  public void testGetBatches() {
		  long firstDate = Timestamp.valueOf(properties.getProperty("from_date")).getTime();
		  long secondDate = Timestamp.valueOf(properties.getProperty("to_date")).getTime();
		  BatchesService batchesService = new BatchesService();
		  List<BatchInfo> batches = batchesService.getBatches(firstDate, secondDate);
		  
		  for(BatchInfo batch : batches) {
			  Assert.assertNotNull(batch.getBatchName());
		  }
		  
	  }
	  
	  @Test
	  public void testGetBatchesNeg() {
		  //dates in wrong order make it so it's not a date range
		  long firstDate = Timestamp.valueOf(properties.getProperty("to_date")).getTime();
		  long secondDate = Timestamp.valueOf(properties.getProperty("from_date")).getTime();
		  BatchesService batchesService = new BatchesService();
		  List<BatchInfo> batches = batchesService.getBatches(firstDate, secondDate);
		  Assert.assertTrue(batches.size() == new Integer( properties.getProperty("zero")).intValue());
		  
	  }
	  
	  @Test
	  public void testGetAssociates() {
		  
		  BatchesService batchesService = new BatchesService();
		  List<AssociateInfo> associates = batchesService.getAssociates(properties.getProperty("batch_name"));
		  Assert.assertTrue(associates.size() > new Integer( properties.getProperty("zero")).intValue());
		  for(AssociateInfo associate : associates) {
			  Assert.assertNotNull(associate.getId());
		  }
	  }
	  
	  @Test
	  public void testGetAssociatesNeg() {
		  
		  BatchesService batchesService = new BatchesService();
		  String batchName = properties.getProperty("improper_batch_name");
		  ArrayList<AssociateInfo> associates = (ArrayList<AssociateInfo>) batchesService.getAssociates(batchName);
		  Assert.assertTrue(associates.size() == new Integer(properties.getProperty("zero")));
	  }
	  
	  @BeforeClass
	  public void beforeClass() throws IOException {
		  
		    /*
		     * Load properties file
		     */
			File configFile = new File("src/main/resources/tests.properties");
			FileInputStream fileInput = new FileInputStream(configFile);
			properties.load(fileInput);
			fileInput.close();
		  
	  }

}
