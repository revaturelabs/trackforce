package com.revature.test.dao;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.dao.AssociateDao;
import com.revature.dao.UserDao;
import com.revature.daoimpl.AssociateDaoImpl;
import com.revature.daoimpl.UserDaoImpl;
import com.revature.entity.TfAssociate;
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
		int id = Integer.parseInt(props.getProperty("associate_id"));
		TfAssociate associate = dao.getAssociate(id);
		assertEquals(props.getProperty("associate_firstName"), associate.getFirstName());
		assertEquals(props.getProperty("associate_lastName"), associate.getLastName());
		assertEquals(props.getProperty("associate_feedback"), associate.getStagingFeedback());
		assertEquals(props.getProperty("associate_id"), associate.getId()+"");
	}

	@Test
	public void testGetAssociateDAOByUserId() {
		TfAssociate associate = dao.getAssociateByUserId(Integer.parseInt(props.getProperty("associate_user")));
		assertEquals(props.getProperty("associate_firstName"), associate.getFirstName());
		assertEquals(props.getProperty("associate_lastName"), associate.getLastName());
		assertEquals(props.getProperty("associate_feedback"), associate.getStagingFeedback());
		assertEquals(props.getProperty("associate_id"), associate.getId()+"");
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
		
	}
	
	@Test
	public void testAssociateDAOGetMapped() {
		
	}
	
	@Test
	public void testAssociateDAOGetUndeployed() {
		
	}

	@Test
	public void testAssociateDAOUpdateAssociate() {

	}

	@Test
	public void testAssociateDAOUpdateAssociates() {

	}
	
	@Test
	public void testAssociateDAOCountMapped() {
		
	}
}
