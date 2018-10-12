package com.revature.test.services;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.daoimpl.BatchDaoImpl;
import com.revature.entity.TfBatch;
import com.revature.services.BatchService;
import com.revature.test.utils.Log;

public class BatchServicesTest {
	
	@Mock
	private BatchService service;
	private Properties props;

	@BeforeClass
	public void beforeClass() {
		service = new BatchService(new BatchDaoImpl());
		props = new Properties();

		try {
			FileInputStream propFile = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\database_entries.properties");
			props.load(propFile);
			propFile.close();
		} catch(FileNotFoundException e) {
			Log.Log.error(e.getMessage());
		} catch (IOException e) {
			Log.Log.error(e.getMessage());
		}
	}
	
	@Test
	public void testGetBatchByName() {
		TfBatch batch = service.getBatch(props.getProperty("batchName"));
		
		assertEquals(batch.getBatchName(), props.getProperty("batchName"));
	}
	
	@Test
	public void testGetBatchById() {
		TfBatch batch = service.getBatchById(Integer.parseInt(props.getProperty("batchId")));
		
		assertEquals(batch.getBatchName(), props.getProperty("batchIdName"));
	}

	@Test
	public void testGetAllBatches() {
		List<TfBatch> allBatches =  new ArrayList<TfBatch>();
		allBatches.addAll(service.getAllBatches());
		
		assertNotNull(allBatches);
		assertFalse(allBatches.isEmpty());
	}
}