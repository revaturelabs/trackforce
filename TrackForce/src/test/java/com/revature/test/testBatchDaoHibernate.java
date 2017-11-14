package com.revature.test;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;

import com.revature.dao.BatchDao;
import com.revature.dao.BatchDaoHibernate;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;

public class testBatchDaoHibernate {
	Properties properties = new Properties();

	@BeforeClass
	public void beforeclass() throws IOException {
		File configFile = new File("src/main/resources/tests.properties");
		FileInputStream fileInput = new FileInputStream(configFile);
		properties.load(fileInput);
		fileInput.close();
	}

	@Test 
	public void testgetBatchDetails() {
		Timestamp firstDate = Timestamp.valueOf(properties.getProperty("from_date"));
		Timestamp secondDate = Timestamp.valueOf(properties.getProperty("to_date"));
		BatchDao batchdao = new BatchDaoHibernate();
		List<TfBatch> batchlist = batchdao.getBatchDetails(firstDate, secondDate);
		Assert.assertFalse(batchlist.isEmpty());
	}

	@Test
	public void testGetBatch() {
		BatchDaoHibernate batch = new BatchDaoHibernate();
		TfBatch batchA = batch.getBatch(properties.getProperty("batch_name"));
		Assert.assertNotNull(batchA);
	}

	@Test
	public void testgetBatchDetailsNegative() {
		//giving the dates in opposite order - is not a date range
		Timestamp firstDate = Timestamp.valueOf(properties.getProperty("to_date"));
		Timestamp secondDate = Timestamp.valueOf(properties.getProperty("from_date"));
		BatchDao batchdao = new BatchDaoHibernate();
		List<TfBatch> batchlist = batchdao.getBatchDetails(firstDate, secondDate);
		Assert.assertTrue(batchlist.isEmpty());
	}

	@Test
	public void testGetBatchNegative() {
		BatchDao batchDao = new BatchDaoHibernate();
		TfBatch batch = batchDao.getBatch("さいうえぁじぇうjp♫¥=⌐~ë");
		Assert.assertNotNull(batch);
		Assert.assertNull(batch.getTfBatchName());
	}
}