package com.revature.dao;

import static org.testng.Assert.assertNotNull;

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
	
	@Test(dataProvider="batchName")
	public void getBatch(String batchName) {
		TfBatch result = bDao.getBatch(batchName);
		assertNotNull(result);
	}
}
