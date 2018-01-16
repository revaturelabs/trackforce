package com.revature.test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.Assert;

import com.revature.dao.BatchDao;
import com.revature.dao.BatchDaoHibernate;
import com.revature.entity.TfBatch;
import com.revature.utils.HibernateUtil;

public class testBatchDaoHibernate {
	Properties properties = new Properties();

	private Session session;
	private Transaction tx;
	
	@BeforeClass
	public void beforeclass() throws IOException {
		File configFile = new File("src/main/resources/tests.properties");
		FileInputStream fileInput = new FileInputStream(configFile);
		properties.load(fileInput);
		fileInput.close();
	}
	
	@BeforeTest
	public void before() throws HibernateException, IOException {
		session = HibernateUtil.getSession().openSession();
		tx = session.beginTransaction();
	}
	
	@AfterTest
	public void after() {
		session.flush();
		tx.rollback();
		session.close();
	}

	@Test 
	public void testgetBatchDetails() throws IOException {
		Timestamp firstDate = Timestamp.valueOf(properties.getProperty("from_date"));
		Timestamp secondDate = Timestamp.valueOf(properties.getProperty("to_date"));
		BatchDao batchdao = new BatchDaoHibernate();
		List<TfBatch> batchlist = batchdao.getBatchDetails(firstDate, secondDate, session);
		Assert.assertFalse(batchlist.isEmpty());
	}

	@Test
	public void testGetBatch() throws IOException {
		BatchDaoHibernate batch = new BatchDaoHibernate();
		TfBatch batchA = batch.getBatch(properties.getProperty("batch_name"), session);
		Assert.assertNotNull(batchA);
	}

	@Test
	public void testgetBatchDetailsNegative() throws IOException {
		//giving the dates in opposite order - is not a date range
		Timestamp firstDate = Timestamp.valueOf(properties.getProperty("to_date"));
		Timestamp secondDate = Timestamp.valueOf(properties.getProperty("from_date"));
		BatchDao batchdao = new BatchDaoHibernate();
		List<TfBatch> batchlist = batchdao.getBatchDetails(firstDate, secondDate, session);
		Assert.assertTrue(batchlist.isEmpty());
	}

	@Test
	public void testGetBatchNegative() throws IOException {
		BatchDao batchDao = new BatchDaoHibernate();
		TfBatch batch = batchDao.getBatch("さいうえぁじぇうjp♫¥=⌐~ë", session);
		Assert.assertNotNull(batch);
		Assert.assertNull(batch.getTfBatchName());
	}
}