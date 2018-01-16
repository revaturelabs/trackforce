package com.revature.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.entity.TfBatch;
import com.revature.model.BatchInfo;
import com.revature.utils.HibernateUtil;

public class BatchDaoImplTest {

	private Session session;
	private Transaction tx;
	BatchDaoHibernate bDao = new BatchDaoHibernate();
	
	@DataProvider(name="batchName")
	public String[] batchNames()
	{
		String[] batchnames = new String[] {"1701 Jan09 Java", "1712 Dec11 Java AP-USF", "1709 Sep11 JTA"};
		return batchnames;
	}
	
	@DataProvider(name="timeStamps")
	public Timestamp[][] timestamps()
	{
		Timestamp[][] timeStamps = new Timestamp[][]{{new Timestamp(01/01/2017), new Timestamp(12/12/2017)}};
		return timeStamps;
	}
	
	@BeforeTest
	public void before() throws HibernateException, IOException {
		session  = HibernateUtil.getSession().openSession();
		tx = session.beginTransaction();
	}
	
	@AfterTest
	public void after() {
		session.flush();
		tx.rollback();
		session.close();
	}
	
	@Test(dataProvider="batchName")
	public void getBatch(String batchName) throws IOException {
		TfBatch result = bDao.getBatch(batchName, session);
		assertNotNull(result);
	}

	@Test
    public void getBatchById() throws IOException {
	    BigDecimal id = new BigDecimal(1L);
	    TfBatch result = bDao.getBatchById(id, session);
	    assertNotNull(result);
	    assertEquals(1L, result.getTfBatchId().longValueExact());
    }
	
	@Test(dataProvider="timeStamps")
	public void getBatchDetails() throws IOException {
		Map<BigDecimal, BatchInfo> result = bDao.getBatchDetails(session);
		assertNotNull(result);
	}
}
