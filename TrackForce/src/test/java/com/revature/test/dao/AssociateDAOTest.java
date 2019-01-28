package com.revature.test.dao;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.InvalidArgumentException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.dao.AssociateDao;
import com.revature.dao.UserDao;
import com.revature.daoimpl.AssociateDaoImpl;
import com.revature.daoimpl.UserDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;
import com.revature.entity.TfInterview;
import com.revature.entity.TfMarketingStatus;
import com.revature.entity.TfPlacement;
import com.revature.entity.TfUser;
import com.revature.test.utils.Log;

/** Test class for testing AssociateDAOImpl
 * 
 * Danger of false negatives in the case of database changes.
 * 
 * Depends on Properties file referring to existent entries in database.
 * Also directly refers to existent entries in the database. Be warned that any
 * change in the database may very well cause tests to fail despite the DAO 
 * working just fine.
 */
public class AssociateDAOTest {
	
	private AssociateDao dao;
	private UserDao userDao;
	//PLEASTE NOTE: The file referenced by this variable upon initialization can be out of date.
	//Check that this is not out of date with the database being accessed before troubleshooting
	//failing tests. Due to lambdas inside lambdas (HibernateUtil's Callable, Dao's Sessionals)
	private Properties props;
	
	@BeforeClass
	public void initialize() {
		dao = new AssociateDaoImpl();
		userDao = new UserDaoImpl();
		props = new Properties();
		try {
			//Please check the file indicated here when troubleshooting failing tests to make sure that hardcoded values correspond.
			FileInputStream propFile = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\database_entries.properties");
			props.load(propFile);
			propFile.close();
		} catch(FileNotFoundException e) {
			Log.Log.error(e.getMessage());
		} catch (IOException e) {
			Log.Log.error(e.getMessage());
		}
	}
	
	@AfterClass
	public void teardown() {
		//Resetting the single update entry
		TfAssociate associate = dao.getAssociate(0);
		associate.setFirstName("TestFirstName");
		associate.setLastName("TestLastName");
		associate.setStagingFeedback("Prepared");
		dao.updateAssociate(associate);
		//Resetting the batch update entries
		for (int i = 0;  i < 3; i++) {
			associate = dao.getAssociate(i + 191);
			associate.setFirstName("Roger" + i);
			associate.setLastName("William");
			associate.setStagingFeedback("Ghastly");
			dao.updateAssociate(associate);
		}
	}

	@Test(groups= {"getters"})
	public void testAssociateDAOGetNAssociateMatchingCriteria() {
		int start = Integer.parseInt(props.getProperty("page_start"));
		int numResults = Integer.parseInt(props.getProperty("page_numResults"));
		int mktStat = Integer.parseInt(props.getProperty("page_mktStat"));
		int clientId = Integer.parseInt(props.getProperty("page_clientId"));
		List<TfAssociate> list = dao.getNAssociateMatchingCriteria(start, numResults, mktStat, clientId, "", "", "");
		assertTrue(list != null);
		assertTrue(list.size() <= numResults);
	}

	@Test(groups= {"getters"})
	public void testAssociateDAOGetAssociate() {
		int id = Integer.parseInt(props.getProperty("associate1_id"));
		TfAssociate associate = dao.getAssociate(id);
		assertEquals(props.getProperty("associate1_firstName"), associate.getFirstName());
		assertEquals(props.getProperty("associate1_lastName"), associate.getLastName());
		assertEquals(props.getProperty("associate1_feedback"), associate.getStagingFeedback());
		assertEquals(props.getProperty("associate1_id"), associate.getId()+"");
	}

	@Test(groups= {"getters"})
	public void testGetAssociateDAOByUserId() {
		TfAssociate associate = dao.getAssociateByUserId(Integer.parseInt(props.getProperty("associate_user")));
		assertEquals(props.getProperty("associate1_firstName"), associate.getFirstName());
		assertEquals(props.getProperty("associate1_lastName"), associate.getLastName());
		assertEquals(props.getProperty("associate1_feedback"), associate.getStagingFeedback());
		assertEquals(props.getProperty("associate1_id"), associate.getId()+"");
	}

	@Test(groups= {"getters"})
	public void testAssociateDAOGetAllAssociates() {
		List<TfAssociate> list = dao.getAllAssociates();
		int total = Integer.parseInt(props.getProperty("total"));
		assertTrue(list != null);
		assertTrue(list.size() >= 0);
	}

	@Test(groups= {"getters"})
	public void testAssociateDAOGetNAssociates() {
		List<TfAssociate> list = dao.getNAssociates();
		assertTrue(list != null);
		assertTrue(list.size() >= 0);
	}
	
	
//	TODO: Replace this test with one testing the functionality of the 'getStatusCountsMap' method
//	that replaced these methods - Art B.
//	@Test(groups= {"getters"})
//	public void testAssociateDAOGetCounts() {
//		assertEquals((long)dao.getCountUndeployedMapped(),
//				Long.parseLong(props.getProperty("undeployed_mapped_count")));
//		assertEquals((long)dao.getCountUndeployedUnmapped(),
//				Long.parseLong(props.getProperty("undeployed_unmapped_count")));
//		assertEquals((long)dao.getCountDeployedMapped(),
//				Long.parseLong(props.getProperty("deployed_mapped_count")));
//		assertEquals((long)dao.getCountDeployedUnmapped(),
//				Long.parseLong(props.getProperty("deployed_unmapped_count")));
//		assertEquals((long)dao.getCountMappedTraining(),
//				Long.parseLong(props.getProperty("training_mapped_count")));
//		assertEquals((long)dao.getCountUnmappedTraining(),
//				Long.parseLong(props.getProperty("training_unmapped_count")));
//		assertEquals((long)dao.getCountMappedSelected(),
//				Long.parseLong(props.getProperty("selected_mapped_count")));
//		assertEquals((long)dao.getCountUnmappedSelected(),
//				Long.parseLong(props.getProperty("selected_unmapped_count")));
//		assertEquals((long)dao.getCountMappedConfirmed(),
//				Long.parseLong(props.getProperty("confirmed_mapped_count")));
//		assertEquals((long)dao.getCountUnmappedConfirmed(),
//				Long.parseLong(props.getProperty("confirmed_unmapped_count")));
//		assertEquals((long)dao.getCountMappedReserved(),
//				Long.parseLong(props.getProperty("reserved_mapped_count")));
//		assertEquals((long)dao.getCountUnmappedOpen(),
//				Long.parseLong(props.getProperty("open_unmapped_count")));
//	}

	@Test
	public void testAssociateDAOApproveAssociate() {
		dao.approveAssociate(209);
		TfUser user = userDao.getUser("Luciana");
		assertEquals(user.getIsApproved(), 1);
		
		//Undo change to database
		user.setIsApproved(0);
		userDao.updateUser(user);
		
	}
	
	@Test
	public void testAssociateDAOApproveAssociates() {
		List<Integer> toBeApproved = new LinkedList<Integer>();
		toBeApproved.add(191);
		toBeApproved.add(192);
		toBeApproved.add(193);
		toBeApproved.add(194);
		toBeApproved.add(195);
		toBeApproved.add(196);
		toBeApproved.add(197);
		toBeApproved.add(198);
		toBeApproved.add(199);
		
		dao.approveAssociates(toBeApproved);
		
		String[] userlist = ((String)props.get("usernames")).split(",");
		for(String username : userlist) {
			TfUser user = userDao.getUser(username);
			assertEquals(user.getIsApproved(), 1);
			
			user.setIsApproved(0);
			userDao.updateUser(user);
		}
		
	}
	
	/*
	 * TODO Fix this 
	 * This test throws a NullPointerException, just in case you couldn't tell from running the test.
	 * Upon further inspection, it looks like the exception is coming from 
	 * 'com.revature.utils.HibernateUtil.runHibernateTransaction(Sessional<Boolean> sessional, Object... args)'
	 * where 'return threadUtil.submitCallable(caller);' is returning null due to ThreadUtil catching a
	 * InterruptedException or ExecutionException, causing the submitCallable(Callable<T> caller) to return a null,
	 * causing a chain reaction... Not sure why InterruptedException or ExecutionException is being thrown.
	 */
	@Test(dependsOnGroups= {"getters"})
	public void testAssociateDAOCreateAssociate() {
		TfAssociate newassociate = new TfAssociate();
		assertTrue(dao.createAssociate(newassociate));
//		===================CODE TO BE DELETED BELOW==========================
//		TfUser user = new TfUser();
//		user.setId(791);
//		TfBatch batch = new TfBatch();
//		batch.setId(1);
//		TfMarketingStatus marketingStatus = new TfMarketingStatus();
//		marketingStatus.setId(1);
//		TfClient client = new TfClient();
//		client.setId(1);
//		TfEndClient endClient = new TfEndClient();
//		endClient.setId(1);
//		Set<TfInterview> interview = new HashSet<TfInterview>(0);
//		Set<TfPlacement> placement = new HashSet<TfPlacement>(0);

		
//		int total = Integer.parseInt(props.getProperty("total"));
//		TfAssociate newassociate = new TfAssociate(total+1, user, batch, marketingStatus,
//		 client, endClient, "daoTest", "daoTest", 
//		 interview, placement, new Timestamp(100000000000L));
//		dao.createAssociate(newassociate);

//		TfAssociate check = dao.getAssociate(total+1);
//		assertEquals(check, newassociate);
//		===============================================================
		
		
		
//		<=====the line below will not work unlsess you have  the appropriate DB privlidges...so for now it is commented out===>
//		As it is, the test will create a record, pass, and then not be able to delete the record after the test. Uncomment
//		the line below if you have permission to delete from the database, and the test should pass.
//		dao.deleteAssociate(newassociate);
	}
	
	@Test
	public void testAssociateDAOGetMapped() {
		assertTrue(dao.getMapped(1) instanceof List);
	}
	
	@Test(expectedExceptions={InvalidArgumentException.class})
	public void testAssociateDAOGetUndeployed() {
		assertTrue(dao.getUndeployed("mapped") instanceof List);
		assertTrue(dao.getUndeployed("unmapped") instanceof List);
		dao.getUndeployed("wrongstring");
	}

	@Test(dependsOnMethods= {"testAssociateDAOGetAssociate"})
	public void testPartialAssociateDAOUpdateAssociate() {
		TfAssociate associate = dao.getAssociate(0);
		associate.setFirstName("changed");
		associate.setLastName("changed");
		associate.setStagingFeedback("corgi");

		assertTrue(dao.updateAssociatePartial(associate));
		associate = dao.getAssociate(0);
		assertEquals(associate.getFirstName(), "changed");
		assertEquals(associate.getLastName(), "changed");
		assertNotEquals(associate.getStagingFeedback(), "corgi");
	}
	
	@Test(dependsOnMethods= {"testAssociateDAOGetAssociate"})
	public void testAssociateDAOUpdateAssociate() {
		TfAssociate associate = dao.getAssociate(0);
		associate.setFirstName("different");
		associate.setLastName("different");
		associate.setStagingFeedback("changed");
		
		boolean updateTrue =  dao.updateAssociate(associate);
		
		assertTrue(updateTrue);
		associate = dao.getAssociate(0);
		assertEquals(associate.getFirstName(), "different");
		assertEquals(associate.getLastName(), "different");
		assertEquals(associate.getStagingFeedback(), "changed");
	}

	@Test(dependsOnMethods= {"testAssociateDAOGetAssociate", "testAssociateDAOUpdateAssociate", "testPartialAssociateDAOUpdateAssociate"})
	public void testAssociateDAOUpdateAssociates() {
		TfAssociate associate1 = dao.getAssociate(191);
		associate1.setFirstName("updateAssociates");
		associate1.setLastName("updateAssociates");
		associate1.setStagingFeedback("updateAssociates");
		TfAssociate associate2 = dao.getAssociate(192);
		associate2.setFirstName("updateAssociates");
		associate2.setLastName("updateAssociates");
		associate2.setStagingFeedback("updateAssociates");
		TfAssociate associate3 = dao.getAssociate(193);
		associate3.setFirstName("updateAssociates");
		associate3.setLastName("updateAssociates");
		associate3.setStagingFeedback("updateAssociates");

		List<TfAssociate> list = new ArrayList<TfAssociate>();
		list.add(associate1);
		list.add(associate2);
		list.add(associate3);

		assertTrue(dao.updateAssociates(list));

		for(int i = 0; i < list.size(); i++) {
			TfAssociate temp = dao.getAssociate(i + 191);
			assertEquals(temp.getFirstName(), "updateAssociates");
			assertEquals(temp.getLastName(), "updateAssociates");
			assertEquals(temp.getStagingFeedback(), "updateAssociates");
			list.get(i).setFirstName(props.getProperty("associate"+(i+1)+"_firstName"));
			list.get(i).setLastName(props.getProperty("associate"+(i+1)+"_lastName"));
			list.get(i).setStagingFeedback(props.getProperty("associate"+(i+1)+"_feedback"));
		}
		assertTrue(dao.updateAssociates(list));
	}
	
	@Test(groups= {"getters"})
	public void testAssociateDAOCountMapped() {
		assertEquals(dao.countMappedAssociatesByValue("tf_batch_id", "38", 6), 14);
		assertEquals(dao.countMappedAssociatesByValue("tf_staging_feedback", "Eager", 6), 1);
	}
}
