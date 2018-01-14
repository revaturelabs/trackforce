package com.revature.dao;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.entity.TfBatch;
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
	
	@Before
	public void before() throws HibernateException, IOException {
		session  = HibernateUtil.getSession().openSession();
		tx = session.getTransaction();
	}
	
	@After
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
	
	@Test(dataProvider="timeStamps")
	public void getBatchDetails(Timestamp start, Timestamp end) throws IOException {
		List<TfBatch> result = bDao.getBatchDetails(start, end, session);
		assertNotNull(result);
	}
}
