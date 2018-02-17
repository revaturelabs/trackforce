package com.revature.test.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.dao.AssociateDao;
import com.revature.model.AssociateInfo;
import com.revature.model.ClientMappedJSON;
import com.revature.services.AssociateService;



public class AssociateServiceTest {// extends BaseTest {
	private AssociateInfo assoc1, assoc2, assoc3;
	private Map<Integer, AssociateInfo> mockAssociateMap;
	private Set<AssociateInfo> mockAssociateSet;
	private AssociateService associateService;
	private Map<Integer, ClientMappedJSON> mockClientMappedJSON;

	@Mock
	private AssociateDao mockAssociateDao;

	// private Session session;

	@BeforeTest
	public void initDb() throws SQLException, IOException {
		// resetCaches();
		MockitoAnnotations.initMocks(this);
//		mockAssociateDao = new AssociateDaoHibernate();
		
		System.out.println("Associates");
		assoc1 = createAssocInfo(1, 1, "b1", 1, "c1", 1, "s1", 1, "c1");
		assoc2 = createAssocInfo(2, 2, "b2", 2, "c2", 2, "s2", 2, "c2");
		assoc3 = createAssocInfo(3, 3, "b3", 3, "c3", 3, "s3", 3, "c3");

		System.out.println("map");
		mockAssociateMap = new HashMap<>();
		mockAssociateMap.put(assoc1.getId(), assoc1);
		mockAssociateMap.put(assoc2.getId(), assoc2);
		mockAssociateMap.put(assoc3.getId(), assoc3);

		System.out.println("cmj");
		mockClientMappedJSON = new HashMap<>();
		mockClientMappedJSON.put(1, createCMJ(1, "c1", 1));
//		mockClientMappedJSON.put(2, createCMJ(2, "c2", 2));
//		mockClientMappedJSON.put(3, createCMJ(3, "c3", 3));

		System.out.println("set");
		mockAssociateSet = new TreeSet<>();
		mockAssociateSet.add(assoc1);
		mockAssociateSet.add(assoc2);
		mockAssociateSet.add(assoc3);

		System.out.println("mockito");
		Mockito.when(mockAssociateDao.getAllAssociates()).thenReturn(mockAssociateSet);
		// Mockito.when(mockAssociateDao.getAssociate(Matchers.anyInt())).thenReturn(assoc1);

		System.out.println("set Service");
		associateService = new AssociateService(mockAssociateDao);
	}

	@BeforeMethod
	public void openSessionInitAssocService() {
		// session = sessionFactory.openSession();
		// session.beginTransaction();

		
	}

	@AfterMethod
	public void rollbackChanges() {
		// rollbackAndClose(session);
	}

	// Method has no logic to test
	// @Test
	// public void testGetAssociate() throws IOException {
	// Integer id = new Integer(1);
	// AssociateInfo associate = associateService.getAssociate(id);
	// Assert.assertNotNull(associate);
	// }

	@Test
	public void testGetMapppedInfo() {
		Set<ClientMappedJSON> mapped = new TreeSet<>(associateService.getMappedInfo(1).values());
		Set<ClientMappedJSON> testMapped = new TreeSet<>(mockClientMappedJSON.values());
		Assert.assertTrue(testMapped.equals(mapped));
	}

	public void testGetUnmappedInfo() {

	}

	// Method has no logic to test
	// public void testUpdateAssocites() {
	//
	// }

	// @Test(expectedExceptions = IOException.class)
	// public void testgetAssociateNegative() throws IOException {
	// Integer Integer = new Integer(-25);
	// associateService.getAssociate(Integer);
	// }

	// @Test
	// public void testUpdateAssociatePositive() throws IOException {
	// List<Integer> associates = new ArrayList<>();
	// associates.add(16);
	// associateService.updateAssociates(associates, 3, 2);
	//
	// AssociateInfo associateInfo = associateService.getAssociate(new Integer(16));
	//
	// Assert.assertEquals(associateInfo.getMsid(), new Integer(3));
	// Assert.assertEquals(associateInfo.getMarketingStatus(), "MAPPED: SELECTED");
	// Assert.assertEquals(associateInfo.getClid(), new Integer(2));
	// Assert.assertEquals(associateInfo.getClient(), "Infosys");
	// }

	// @Test
	// public void testUpdateAssociateNegative() throws IOException {
	// List<Integer> associates = new ArrayList<>();
	// associates.add(15);
	// associateService.updateAssociates(associates, -1, -1);
	// AssociateInfo associateInfo = associateService.getAssociate(new Integer(15));
	// Assert.assertEquals(associateInfo.getId(), new Integer(15));
	// Assert.assertTrue(associateInfo.getClid() > -1);
	// Assert.assertNotNull(associateInfo.getMarketingStatus());
	// Assert.assertNotEquals(associateInfo.getClient(), "None");
	// }

	AssociateInfo createAssocInfo(int id, int batchId, String batchName, int clientId, String clientName, int msid,
			String msName, int currId, String currName) {
		AssociateInfo assoc = new AssociateInfo();
		assoc.setId(new Integer(id));
		assoc.setBatchId(new Integer(batchId));
		assoc.setBatchName(batchName);
		assoc.setBid(new Integer(batchId));
		assoc.setClid(new Integer(clientId));
		assoc.setClientId(new Integer(clientId));
		assoc.setClient(clientName);//----------
		assoc.setMarketingStatusId(new Integer(msid));
		assoc.setMsid(new Integer(msid));
		assoc.setMarketingStatus(msName);
		assoc.setCurriculumId(new Integer(currId));
		assoc.setCurriculumName(currName);

		return assoc;
	}
	
	ClientMappedJSON createCMJ(int id, String name, int count) {
		ClientMappedJSON cmj = new ClientMappedJSON();
		cmj.setId(id);
		cmj.setName(name);
		cmj.setCount(count);
		return cmj;
	}
}