package com.revature.test.dao;
import static com.revature.test.utils.Log.Log;
import static org.testng.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;

import com.revature.test.dao.DbUnit;
/*
 * DbUnit framework does clean insert and clean up after database testing of DAO/Hibernate methods
 */
public class DaoTest {
	
	IDatabaseConnection conn;
	IDatabaseTester tester;
	DbUnit test;
	IDataSet serverSideData;
	IDataSet clientSideData;
	@BeforeClass
	public void setUp()
	{
		test = new DbUnit("daoTest");
		Log.info("==============="+test.toString()+"STARTING==================");
		try {
			//Get connection for tester
			tester=test.getDatabaseTester();
			conn = tester.getConnection();
			//transient test data; dirty data from previous data 
			//Jersey services for grabbing data; store into xml; compare with database
			//actual data actually does not get through database
			//create dataset from snapshot of database
			serverSideData=conn.createDataSet(); 

			Log.info("DbUnit Tests: "+ test.countTestCases());
			//tester.getConnection().createDataSet();
			
			//set dataset from database
			//tester.setDataSet(serverSideData);
			//compare database with front end
			//take snapshot of front-end data
			
			//get clientside dataset in XML
			clientSideData=test.getDataSet();
			//inserts client data into database
			tester.setSetUpOperation(test.getSetUpOperation());
			//refreshes dataset.xml contents into database
			tester.getSetUpOperation();
			
			Log.info("connection config: " +conn.getConfig());
			Log.info("connection getSchema:" +conn.getSchema());
			//conn.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void tf_associate_table_test() {
		try {
			System.out.println("TF_ASSOCIATE row count:" +conn.getRowCount("TF_ASSOCIATE"));
			assertEquals(clientSideData.getTable("TF_ASSOCIATE").getRowCount(), serverSideData.getTable("TF_ASSOCIATE").getRowCount());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void tf_batch_table_test() {
		try {
			System.out.println("TF_BATCH ROW COUNT: " +conn.getRowCount("TF_BATCH"));
			assertEquals(clientSideData.getTable("TF_BATCH").getRowCount(), serverSideData.getTable("TF_BATCH").getRowCount());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void tf_client_table_test() {
		try {
			assertEquals(clientSideData.getTable("TF_CLIENT").getRowCount(), serverSideData.getTable("TF_CLIENT").getRowCount());
		} catch (DataSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void tf_interview_table_test() {
		Log.info("Database connected");
		try {
			System.out.println("TF_INTERVIEW row count:" +conn.getRowCount("TF_INTERVIEW"));
			System.out.println(clientSideData.getTableNames());
			System.out.println(serverSideData.getTableNames());
			
			assertEquals(clientSideData.getTable("TF_INTERVIEW").getRowCount(), serverSideData.getTable("TF_INTERVIEW").getRowCount());
		} catch (SQLException e) {
			Log.info("SQL Exception");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Log.info("daoTest end.");
	}
	
	
	@AfterClass
	public void tearDown()
	{
		try {
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
