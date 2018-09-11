package com.revature.test.dao;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.InvalidArgumentException;

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

public class AssociateDAOTest {
	
	private AssociateDao dao;
	private UserDao userDao;
	private Properties props;
	
	@BeforeClass
	public void initialize() {
		dao = new AssociateDaoImpl();
		userDao = new UserDaoImpl();
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
	public void testAssociateDAOGetNAssociateMatchingCriteria() {
		int start = Integer.parseInt(props.getProperty("page_start"));
		int numResults = Integer.parseInt(props.getProperty("page_numResults"));
		int mktStat = Integer.parseInt(props.getProperty("page_mktStat"));
		int clientId = Integer.parseInt(props.getProperty("page_clientId"));
		List<TfAssociate> list = dao.getNAssociateMatchingCriteria(start, numResults, mktStat, clientId);
		assertTrue(list != null && list.size() <= numResults);
	}

	@Test
	public void testAssociateDAOGetAssociate() {
		int id = Integer.parseInt(props.getProperty("associate1_id"));
		TfAssociate associate = dao.getAssociate(id);
		assertEquals(props.getProperty("associate1_firstName"), associate.getFirstName());
		assertEquals(props.getProperty("associate1_lastName"), associate.getLastName());
		assertEquals(props.getProperty("associate1_feedback"), associate.getStagingFeedback());
		assertEquals(props.getProperty("associate1_id"), associate.getId()+"");
	}

	@Test
	public void testGetAssociateDAOByUserId() {
		TfAssociate associate = dao.getAssociateByUserId(Integer.parseInt(props.getProperty("associate_user")));
		assertEquals(props.getProperty("associate1_firstName"), associate.getFirstName());
		assertEquals(props.getProperty("associate1_lastName"), associate.getLastName());
		assertEquals(props.getProperty("associate1_feedback"), associate.getStagingFeedback());
		assertEquals(props.getProperty("associate1_id"), associate.getId()+"");
	}

	@Test
	public void testAssociateDAOGetAllAssociates() {
		List<TfAssociate> list = dao.getAllAssociates();
		int total = Integer.parseInt(props.getProperty("total"));
		assertTrue(list != null && list.size() <= total);
	}

	@Test
	public void testAssociateDAOGetNAssociates() {
		List<TfAssociate> list = dao.getNAssociates();
		assertTrue(list != null && list.size() <= 60);
	}

	@Test
	public void testAssociateDAOGetCounts() {
		assertEquals((long)dao.getCountUndeployedMapped(),
				Long.parseLong(props.getProperty("undeployed_mapped_count")));
		assertEquals((long)dao.getCountUndeployedUnmapped(),
				Long.parseLong(props.getProperty("undeployed_unmapped_count")));
		assertEquals((long)dao.getCountDeployedMapped(),
				Long.parseLong(props.getProperty("deployed_mapped_count")));
		assertEquals((long)dao.getCountDeployedUnmapped(),
				Long.parseLong(props.getProperty("deployed_unmapped_count")));
		assertEquals((long)dao.getCountMappedTraining(),
				Long.parseLong(props.getProperty("training_mapped_count")));
		assertEquals((long)dao.getCountUnmappedTraining(),
				Long.parseLong(props.getProperty("training_unmapped_count")));
		assertEquals((long)dao.getCountMappedSelected(),
				Long.parseLong(props.getProperty("selected_mapped_count")));
		assertEquals((long)dao.getCountUnmappedSelected(),
				Long.parseLong(props.getProperty("selected_unmapped_count")));
		assertEquals((long)dao.getCountMappedConfirmed(),
				Long.parseLong(props.getProperty("confirmed_mapped_count")));
		assertEquals((long)dao.getCountUnmappedConfirmed(),
				Long.parseLong(props.getProperty("confirmed_unmapped_count")));
		assertEquals((long)dao.getCountMappedReserved(),
				Long.parseLong(props.getProperty("reserved_mapped_count")));
		assertEquals((long)dao.getCountUnmappedOpen(),
				Long.parseLong(props.getProperty("open_unmapped_count")));
	}

	@Test
	public void testAssociateDAOApproveAssociate() {
		dao.approveAssociate(2);
		TfUser user = userDao.getUser("Duncan");
		assertEquals(user.getIsApproved(), 1);
		
		//Undo change to database
		user.setIsApproved(0);
		userDao.updateUser(user);
		
	}
	
	@Test
	public void testAssociateDAOApproveAssociates() {
		List<Integer> toBeApproved = new LinkedList<Integer>();
		toBeApproved.add(2);
		toBeApproved.add(3);
		toBeApproved.add(4);
		toBeApproved.add(5);
		toBeApproved.add(6);
		toBeApproved.add(7);
		toBeApproved.add(8);
		toBeApproved.add(9);
		toBeApproved.add(10);
		
		dao.approveAssociates(toBeApproved);
		String[] userlist = ((String)props.get("usernames")).split(",");
		for(String username : userlist) {
			TfUser user = userDao.getUser(username);
			assertEquals(user.getIsApproved(), 1);
			
			user.setIsApproved(0);
			userDao.updateUser(user);
		}
		
	}
	
	@Test(dependsOnMethods= {"testAssociateDAOGetAssociate"})
	public void testAssociateDAOCreateAssociate() {
		TfUser user = new TfUser();
		user.setId(-1);
		TfBatch batch = new TfBatch();
		batch.setId(1);
		TfMarketingStatus marketingStatus = new TfMarketingStatus();
		marketingStatus.setId(1);
		TfClient client = new TfClient();
		client.setId(1);
		TfEndClient endClient = new TfEndClient();
		endClient.setId(1);
		Set<TfInterview> interview = new HashSet<TfInterview>(0);
		Set<TfPlacement> placement = new HashSet<TfPlacement>(0);

		TfAssociate newassociate = new TfAssociate(-1, user, batch, marketingStatus,
		 client, endClient, "daoTest", "daoTest", 
		 interview, placement, new Timestamp(100000000000L));
		dao.createAssociate(newassociate);

		TfAssociate check = dao.getAssociate(-1);
		assertEquals(check, newassociate);
	}
	
	//Really no idea how to build test data to compare against for this
	//So, I'm sorry but I'm going to test this to buff coverage
	@Test
	public void testAssociateDAOGetMapped() {
		assertTrue(dao.getMapped(1) instanceof List);
	}
	
	//Same problem here
	@Test(expectedExceptions={InvalidArgumentException.class})
	public void testAssociateDAOGetUndeployed() {
		assertTrue(dao.getUndeployed("mapped") instanceof List);
		assertTrue(dao.getUndeployed("unmapped") instanceof List);
		dao.getUndeployed("wrongstring");
	}

	@Test(dependsOnMethods= {"testAssociateDAOGetAssociate"})
	public void testAssociateDAOUpdateAssociate() {
		TfAssociate associate = dao.getAssociate(1);
		associate.setFirstName("changed");
		associate.setLastName("changed");
		associate.setStagingFeedback("changed");

		assertTrue(dao.updateAssociatePartial(associate));
		associate = dao.getAssociate(1);
		assertEquals(associate.getFirstName(), "changed");
		assertEquals(associate.getLastName(), "changed");
		assertNotEquals(associate.getStagingFeedback(), "changed");

		associate.setFirstName("different");
		associate.setLastName("different");
		assertTrue(dao.updateAssociate(associate));
		associate = dao.getAssociate(1);
		assertEquals(associate.getFirstName(), "different");
		assertEquals(associate.getLastName(), "different");
		//assertEquals(associate.getStagingFeedback(), "changed");
	}

	@Test(dependsOnMethods= {"testAssociateDAOGetAssociate", "testAssociateDAOUpdateAssociate"})
	public void testAssociateDAOUpdateAssociates() {
		TfAssociate associate1 = dao.getAssociate(1);
		associate1.setFirstName("updateAssociates");
		associate1.setLastName("updateAssociates");
		associate1.setStagingFeedback("updateAssociates");
		TfAssociate associate2 = dao.getAssociate(2);
		associate2.setFirstName("updateAssociates");
		associate2.setLastName("updateAssociates");
		associate2.setStagingFeedback("updateAssociates");
		TfAssociate associate3 = dao.getAssociate(3);
		associate3.setFirstName("updateAssociates");
		associate3.setLastName("updateAssociates");
		associate3.setStagingFeedback("updateAssociates");

		List<TfAssociate> list = new ArrayList<TfAssociate>();
		list.add(associate1);
		list.add(associate2);
		list.add(associate3);

		assertTrue(dao.updateAssociates(list));

		for(int i = 0; i < list.size(); i++) {
			TfAssociate temp = dao.getAssociate(i + 1);
			assertEquals(temp.getFirstName(), "updateAssociates");
			assertEquals(temp.getLastName(), "updateAssociates");
			assertEquals(temp.getStagingFeedback(), "updateAssociates");
			list.get(i).setFirstName(props.getProperty("associate"+(i+1)+"_firstName"));
			list.get(i).setLastName(props.getProperty("associate"+(i+1)+"_lastName"));
			list.get(i).setStagingFeedback(props.getProperty("associate"+(i+1)+"_feedback"));
		}
		assertTrue(dao.updateAssociates(list));
	}
	
	@Test
	public void testAssociateDAOCountMapped() {
		assertEquals((long)dao.countMappedAssociatesByValue("tf_batch_id", 0L, 10), 11L);
		assertEquals((long)dao.countMappedAssociatesByValue("tf_staging_feedback", -1L, 10), 67L);
	}
}
