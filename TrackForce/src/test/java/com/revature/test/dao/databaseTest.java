package com.revature.test.dao;
import static com.revature.test.utils.Log.Log;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.revature.test.admin.pom.AssociateListTab;
import com.revature.test.admin.pom.BatchListTab;
import com.revature.test.admin.pom.ClientListTab;
import com.revature.test.admin.pom.Logout;
import com.revature.test.utils.LoginUtil;
import com.revature.test.utils.TestConfig;
import com.revature.test.utils.WebDriverUtil;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
//import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

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
			databaseTestHelper.beforeSuite();
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
			AssociateService aserv = new AssociateService(); //associate service test
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
	
	//very rough test of if new associate is added to table by comparing number of associates in data grabbed from front end to database count
	@Test
	public void tf_new_associate() throws DataSetException {
		
		databaseTestHelper.getAssociateCount();
		System.out.println(databaseTestHelper.clientSideRowCount);
		assertTrue(serverSideData.getTable("TF_ASSOCIATE").getRowCount()>databaseTestHelper.clientSideRowCount);
	}
	//front-end vs database automated
	@Test
	public void tf_batch_table() throws DataSetException {
		
		databaseTestHelper.getBatchCount();
		System.out.println(databaseTestHelper.clientSideRowCount);
		assertTrue(serverSideData.getTable("TF_BATCH").getRowCount()==databaseTestHelper.clientSideRowCount);
	}
	
	@Test
	public void tf_client_table() throws DataSetException {
		
		databaseTestHelper.getClientCount();
		System.out.println(databaseTestHelper.clientSideRowCount);
		assertTrue(serverSideData.getTable("TF_CLIENT").getRowCount()==databaseTestHelper.clientSideRowCount);
	}
	@AfterClass
	public void tearDown()
	{
		try {
			databaseTestHelper.afterSuite();
			conn.close();
			Log.info("===============================Test END========================================");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//helper class to automate testing
	@RunWith(Cucumber.class)
	@CucumberOptions(features="src/test/resources/AdminFeatureFiles/Login.feature",
					 glue="src/test/java/test/admin/cukes/LoginCukes.java")
	static
	class databaseTestHelper extends AbstractTestNGCucumberTests{
		
		public static WebDriver wd = null;
		private static String baseURL = TestConfig.getBaseURL(); //gets the website URL
		static int clientSideRowCount;
		
		public static void beforeSuite() {
			wd = new WebDriverUtil().getChromeDriver();
			System.out.println("================== TRACKFORCE ==================");
			//wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Logging In");
			try {
				Thread.sleep(5000);
				wd.get(baseURL);
				LoginUtil.loginAsAdmin(wd);
				//wd.findElement(By.xpath("//*[@li[6]]")).click();
				AssociateListTab.getAssociateListTab(wd).click();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		public static int getClientCount() {
			ClientListTab.getClientTab(wd).click();
			//clientSideRowCount=ClientListTab.getViewDataForAllClientsButton(wd).getSize().getWidth();
			clientSideRowCount=wd.findElements(By.cssSelector("ul:li")).size();
			
			return clientSideRowCount;
			
		}
		//navigate to associate page and grab row count
		public static int getAssociateCount() {
			AssociateListTab.getAssociateListTab(wd).click();
			clientSideRowCount=AssociateListTab.associateIdList(wd).size();
			return clientSideRowCount;
		}
		//navigate to batch list page and grab batch count
		public static int getBatchCount() {
			BatchListTab.clickBatchListTab(wd).click();
			clientSideRowCount = wd.findElements(By.cssSelector("tr")).size();
			return clientSideRowCount;
		}
		public static void afterSuite() {
			System.out.println("Logging out");
			Logout.logout(wd);
			wd.quit();

		}
	}
}
