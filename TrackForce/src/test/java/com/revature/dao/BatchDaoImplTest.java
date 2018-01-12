package com.revature.dao;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.entity.TfBatch;

public class BatchDaoImplTest {

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
	
	@Test(dataProvider="batchName")
	public void getBatch(String batchName) throws IOException {
		TfBatch result = bDao.getBatch(batchName);
		assertNotNull(result);
	}
	
	@Test(dataProvider="timeStamps")
	public void getBatchDetails(Timestamp start, Timestamp end) throws IOException {
		List<TfBatch> result = bDao.getBatchDetails(start, end);
		assertNotNull(result);
	}
}
