package com.revature.test;

import static org.testng.AssertJUnit.assertNotNull;

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
		Assert.assertNotNull(batchlist);
		System.out.println("Test Get Batch Details Positive");
		System.out.println(batchlist);
	}

	@Test
	public void testGetBatch() {
		BatchDaoHibernate batch = new BatchDaoHibernate();
		TfBatch batchA = batch.getBatch("1709 Sept11 JTA");
		System.out.println(batchA.toString());
		System.out.println(batchA.getTfBatchStartDate());
		for (TfAssociate asc : batchA.getTfAssociates()) {
			System.out.println(asc.getTfAssociateFirstName());
		}
		Assert.assertNotNull(batchA);
	}

	@Test
	public void testgetBatchDetailsNegative() {
		Timestamp firstDate = Timestamp.valueOf("2017-12-30 10:10:10.0");
		Timestamp secondDate = Timestamp.valueOf("2017-09-10 10:10:10.0");
		BatchDao batchdao = new BatchDaoHibernate();
		List<TfBatch> batchlist = batchdao.getBatchDetails(firstDate, secondDate);
		Assert.assertNotNull(batchlist);
		System.out.println("Test Get Batch Details Negative");
		System.out.println(batchlist);
	}

	@Test
	public void testGetBatchNegative() {
		BatchDao batchDao = new BatchDaoHibernate();
		TfBatch batch = batchDao.getBatch("さいうえぁじぇうjp♫¥=⌐~ë");
		assertNotNull(batch);
		System.out.println("Test Get Batch Negative");
		System.out.println(batch);
	}
}