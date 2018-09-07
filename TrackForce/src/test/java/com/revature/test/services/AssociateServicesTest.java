package com.revature.test.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.daoimpl.AssociateDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.services.AssociateService;
import com.revature.test.utils.Log;
import com.revature.utils.HibernateUtil;

public class AssociateServicesTest {

	private AssociateService service;
	private Properties props;
	
	@BeforeClass
	public void initialize() {
		service = new AssociateService(new AssociateDaoImpl());
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
	
	@Test(priority=1)
	public void testGetAssociateById() {
		//assertEquals(props.getProperty("associate_id"), "1");
		TfAssociate associate = service.getAssociate(Integer.parseInt(props.getProperty("associate_id")));
		assertEquals(props.getProperty("associate_firstName"), associate.getFirstName());
		assertEquals(props.getProperty("associate_lastName"), associate.getLastName());
		assertEquals(props.getProperty("associate_feedback"), associate.getStagingFeedback());
		assertEquals(props.getProperty("associate_user"), associate.getUser().getId()+"");
		//associate = service.getAssociate(-1);
	}
	
	@Test(priority=2)
	public void testGetAssociateByUserId() {
		TfAssociate associate = service.getAssociateByUserId(Integer.parseInt(props.getProperty("associate_user")));
		assertEquals(props.getProperty("associate_firstName"), associate.getFirstName());
		assertEquals(props.getProperty("associate_lastName"), associate.getLastName());
		assertEquals(props.getProperty("associate_feedback"), associate.getStagingFeedback());
		assertEquals(props.getProperty("associate_id"), associate.getId()+"");
	}
	
	@Test(priority=2)
	public void testGetAllAssociates() {
		List<TfAssociate> list = service.getAllAssociates();
		assertNotNull(list);
		assertTrue(!list.isEmpty());
	}
	
	@Test(priority=2)
	public void testGetNAssociates() {
		List<TfAssociate> list = service.getNAssociates();
		assertNotNull(list);
		assertTrue(!list.isEmpty() && list.size() <= 60);
	}
	
	@Test(priority=2)
	public void testGetAssociatePage() {
		int start = Integer.parseInt(props.getProperty("page_start"));
		int numResults = Integer.parseInt(props.getProperty("page_numResults"));
		int mktStat = Integer.parseInt(props.getProperty("page_mktStat"));
		int clientId = Integer.parseInt(props.getProperty("page_clientId"));
		
		//Test valid inputs
		List<TfAssociate> list = service.getAssociatePage(start, numResults, mktStat, clientId);
		assertNotNull(list);
		assertTrue(!list.isEmpty() && list.size() == numResults);
		
		//Test invalid inputs
		
	}
	
	@Test(priority=2)
	public void testGetMappedAssociateByClient() {
		long count = Long.parseLong(props.getProperty("market_count"));
		long clientId = Long.parseLong(props.getProperty("market_clientId"));
		int markId = Integer.parseInt(props.getProperty("market_id"));
		
		assertEquals((long)service.getMappedAssociateCountByClientId(clientId, markId), count);
	}
	
	@Test(priority=2)
	public void testGetCounts() {
		assertEquals((long)service.getCountUndeployedMapped(),
				Long.parseLong(props.getProperty("undeployed_mapped_count")));
		assertEquals((long)service.getCountUndeployedUnmapped(),
				Long.parseLong(props.getProperty("undeployed_unmapped_count")));
		assertEquals((long)service.getCountDeployedMapped(),
				Long.parseLong(props.getProperty("deployed_mapped_count")));
		assertEquals((long)service.getCountDeployedUnmapped(),
				Long.parseLong(props.getProperty("deployed_unmapped_count")));
		assertEquals((long)service.getCountMappedTraining(),
				Long.parseLong(props.getProperty("training_mapped_count")));
		assertEquals((long)service.getCountUnmappedTraining(),
				Long.parseLong(props.getProperty("training_unmapped_count")));
		assertEquals((long)service.getCountMappedSelected(),
				Long.parseLong(props.getProperty("selected_mapped_count")));
		assertEquals((long)service.getCountUnmappedSelected(),
				Long.parseLong(props.getProperty("selected_unmapped_count")));
		assertEquals((long)service.getCountMappedConfirmed(),
				Long.parseLong(props.getProperty("confirmed_mapped_count")));
		assertEquals((long)service.getCountUnmappedConfirmed(),
				Long.parseLong(props.getProperty("confirmed_unmapped_count")));
		assertEquals((long)service.getCountMappedReserved(),
				Long.parseLong(props.getProperty("reserved_mapped_count")));
		assertEquals((long)service.getCountUnmappedOpen(),
				Long.parseLong(props.getProperty("open_unmapped_count")));
	}
	
	//Testing only if there is a Hibernate error thrown so we do not actually
	//change the database with this test. We CANNOT mock a database connection
	//if that's what we are testing.
	@Test(priority=100)
	public void testUpdateAssociate() {
		TfAssociate toChange = service.getAssociate(1);
		toChange.setFirstName("Hank");
		toChange.setLastName("Pym");
		toChange.setStagingFeedback("Alabama");
		service.updateAssociatePartial(toChange);
		TfAssociate changed = service.getAssociate(1);
		assertEquals("Hank", changed.getFirstName());
		assertEquals("Pym", changed.getLastName());
		assertNotEquals("Alabama", changed.getStagingFeedback());
		
		changed.setFirstName("Karl");
		changed.setLastName("Franz");
		service.updateAssociate(changed);
		assertEquals("Karl", changed.getFirstName());
		assertEquals("Franz", changed.getLastName());
		assertNotEquals("Alabama", changed.getStagingFeedback());
		
		//Undo update change
		toChange.setFirstName(props.getProperty("associate_firstName"));
		toChange.setLastName(props.getProperty("associate_lastName"));
		service.updateAssociatePartial(toChange);
	}
	
	@Test
	public void testCreateAssociate() {
		
	}
	
	@Test
	public void testApproveAssociate() {
		
	}
	
	@AfterClass
	public void closeSession() {
		HibernateUtil.shutdown();
	}
}
