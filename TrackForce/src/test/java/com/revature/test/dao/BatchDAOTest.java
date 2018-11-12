package com.revature.test.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;


import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

//import com.revature.dao.BatchDao;
import com.revature.daoimpl.BatchDaoImpl;
import com.revature.entity.TfBatch;
import com.revature.test.utils.Log;

/** Test class for testing BatchDAOImpl
 * 
 * Danger of false negatives in the case of database changes.
 * 
 * Depends on Properties file referring to existent entries in database.
 * Also directly refers to existent entries in the database. Be warned that any
 * change in the database may very well cause tests to fail despite the DAO 
 * working just fine.
 */
public class BatchDAOTest {
	
	private BatchDaoImpl dao;
	//PLEASTE NOTE: The file referenced by this variable upon initialization can be out of date.
	//Check that this is not out of date with the database being accessed before troubleshooting
	//failing tests. Due to lambdas inside lambdas (HibernateUtil's Callable, Dao's Sessionals)
	private Properties props;
	
	@BeforeSuite
	public void initialize() {
		dao = new BatchDaoImpl();
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
	public void testBatchDAOGetBatch() {
		TfBatch batch = dao.getBatch(props.getProperty("batch_name"));
		assertEquals((int)batch.getId(), Integer.parseInt(props.getProperty("batch_id")));
		
	}
	
	@Test
	public void testBatchDAOGetById() {
		TfBatch batch = dao.getBatchById(Integer.parseInt(props.getProperty("batch_id")));
		assertEquals(batch.getBatchName(), props.getProperty("batch_name"));
	}
	
	@Test
	public void testBatchDAOGetAll() {
		List<TfBatch> list = dao.getAllBatches();
		assertNotEquals(list, null);
		assertEquals(list.size(), 59);
	}
	
	@Test
	public void testBatchDAOGetWithinDates() {
		List<TfBatch> list = dao.getBatchesWithinDates(
				new Timestamp(Long.parseLong(props.getProperty("batch_startDate"))),
				new Timestamp(Long.parseLong(props.getProperty("batch_endDate"))));
		assertNotEquals(list, null);
		assertEquals(list.size(), Integer.parseInt(props.getProperty("batches_betweenDates")));
	}
	
	@Test
	public void testBatchDAOGetListForPredictions() {
		List<TfBatch> list = dao.getBatchesForPredictions(
				"Java",
				new Timestamp(Long.parseLong(props.getProperty("batch_startDate"))),
				new Timestamp(Long.parseLong(props.getProperty("batch_endDate"))));
		assertNotEquals(list, null);
		assertEquals(list.size(), Integer.parseInt(props.getProperty("batches_betweenDates_java")));		
	}
	
	@Test
	public void testBatchDAOGetObjectForPredictions() {
		long startDate = Long.parseLong(props.getProperty("batch_startDate"));
		long endDate = Long.parseLong(props.getProperty("batch_endDate"));
		BigDecimal count = new BigDecimal(Long.parseLong(props.getProperty("batches_betweenDates_associates")));
		assertEquals(dao.getBatchCountsForPredictions("Java",	new Timestamp(startDate), new Timestamp(endDate)), count);
	}
	
}
