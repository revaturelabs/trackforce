package com.revature.test.dao;
import static com.revature.test.utils.Log.Log;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;

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
import org.dbunit.dataset.ITableIterator;

import com.revature.services.AssociateService;
import com.revature.services.BatchesService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.UserService;
import com.revature.test.dao.DbUnit;
/*
 * DbUnit framework does clean insert and clean up after database testing of DAO/Hibernate methods
 * clientSideData is grabbed using Jersey services 
 * serverSideData is directly taken from database at time of testing
 */
public class databaseTest {
	
	IDatabaseConnection conn; //driver manager connection
	IDatabaseTester tester;
	DbUnit test;
	IDataSet serverSideData; //database data
	IDataSet clientSideData; //client side/angular data
	
	AssociateService aserv = new AssociateService(); //associate service test
	/*
	//get associate list count
	public native int f();
	
	//load library for native methods written in javascript
	static {
		System.loadLibrary("NGTrackforce");
	}
	*/
	@BeforeClass
	public void setUp()
	{
		
		test = new DbUnit("Database Not-Only-Persistence Test");
		Log.info("==============="+test.toString()+"STARTING==================");
		try {
			//Get connection for tester
			tester=test.getDatabaseTester();
		    
			conn = tester.getConnection();
			
			//transient test data; dirty data from previous data 
			
			//grab data; store into xml; compare with database
			
			
			//actual data actually does not get through database
			//create dataset from snapshot of database
			//Jersey response compare
			

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
			this.getServerData();
			Log.info("connection config: " +conn.getConfig());
			Log.info("connection getSchema:" +conn.getSchema());
			//conn.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//util method to grab database data
	@Test(enabled=false)
	public void getServerData() {
		try {
			serverSideData=conn.createDataSet();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	//get table meta data
	@Test(enabled=false)
	public void getAllTables() throws DataSetException {
		ITableIterator it = serverSideData.iterator();
		
		while(it.next()) 
		{
			try {
				Log.info(it.getTableMetaData().getTableName());
				Log.info(it.getTableMetaData());
				Log.info(it.getTable().getRowCount());
				
				//System.out.println(it.getTableMetaData().getTableName());
				//System.out.println(it.getTable().getRowCount());
			} catch (DataSetException e) {
				e.printStackTrace();
			}
		}
	}
	
	//register new associate, fails because not implemented
	@Test
	public void tf_associate_table_test() {
		try {
			ITableIterator it =clientSideData.iterator();
			serverSideData.getTable("TF_ASSOCIATE").getRowCount();
			aserv.getAllAssociates().size();
			//assertEquals(this.f(), serverSideData.getTable("TF_ASSOCIATE").getRowCount());
			assertEquals(aserv.getAllAssociates().size(), serverSideData.getTable("TF_ASSOCIATE").getRowCount());
			assertEquals(clientSideData.getTable("TF_ASSOCIATE").getRowCount(), serverSideData.getTable("TF_ASSOCIATE").getRowCount());
	
		} catch (DataSetException e) {
			e.printStackTrace();
		}
	}
	
	//test service calls with db
	@Test
	public void tf_batch_table_test() {
		try {
			System.out.println("TF_BATCH ROW COUNT: " +conn.getRowCount("TF_BATCH"));
			BatchesService bserv = new BatchesService();
			assertEquals(bserv.getAllBatches().size(), serverSideData.getTable("TF_BATCH").getRowCount());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DataSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void tf_client_table_test() throws IOException {
		try {
			ClientService cserv = new ClientService();
			assertEquals(cserv.getClients().size(), serverSideData.getTable("TF_CLIENT").getRowCount());
			//assertEquals(clientSideData.getTable("TF_CLIENT").getRowCount(), serverSideData.getTable("TF_CLIENT").getRowCount());
		} catch (DataSetException e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void tf_interview_table_test() {
		try {
			InterviewService sserv = new InterviewService();
			assertEquals(sserv.getAllInterviews().size(), serverSideData.getTable("TF_INTERVIEW").getRowCount());
			//assertEquals(clientSideData.getTable("TF_INTERVIEW").getRowCount(), serverSideData.getTable("TF_INTERVIEW").getRowCount());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void tf_curriculum_table_test() {
		try {
			CurriculumService crserv = new CurriculumService();
			assertEquals(crserv.getCurriculums().size(), serverSideData.getTable("TF_CURRICULUM").getRowCount());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void tf_user_table_test() {
		try {
			UserService userv = new UserService();
			assertEquals(userv.getAllUsers().size(), serverSideData.getTable("TF_USER").getRowCount());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	@Test
	public void tf_role_table_test() {
		try {
			assertEquals(5, serverSideData.getTable("TF_ROLE").getRowCount());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@AfterClass
	public void tearDown()
	{
		try {
			conn.close();
			Log.info("===============================Test END========================================");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
