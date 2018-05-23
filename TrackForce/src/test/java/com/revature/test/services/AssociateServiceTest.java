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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.dao.AssociateDao;
import com.revature.dao.AssociateDaoHibernate;
import com.revature.model.AssociateInfo;
import com.revature.model.ClientMappedJSON;
import com.revature.model.CurriculumJSON;
import com.revature.services.AssociateService;
import com.revature.test.BaseTest;

public class AssociateServiceTest extends BaseTest {

	private AssociateInfo assoc1, assoc2, assoc3, assoc4, assoc5, assoc6;
	private Map<Integer, AssociateInfo> mockAssociateMap;
	private Set<AssociateInfo> mockAssociateSet;
	private AssociateService associateService;
	private Map<Integer, ClientMappedJSON> mockClientMappedJSON;
	private Set<CurriculumJSON> mockCurriculumJSON;

	@Mock
	private AssociateDao mockAssociateDao;

	@BeforeTest
	public void initDb() throws SQLException, IOException {
		MockitoAnnotations.initMocks(this);

		assoc1 = createAssocInfo(1, 1, "b1", 1, "c1", 1, "s1", 1, "c1");
		assoc2 = createAssocInfo(2, 2, "b2", 2, "c2", 2, "s2", 2, "c2");
		assoc3 = createAssocInfo(3, 3, "b3", 3, "c3", 3, "s3", 3, "c3");
		assoc4 = createAssocInfo(1, 1, "b1", 1, "c1", 1, "s1", -1, "c1");
		assoc5 = createAssocInfo(2, 2, "b2", 2, "c2", 2, "s2", -1, "c2");
		assoc6 = createAssocInfo(3, 3, "b3", 3, "c3", 3, "s3", -1, "c3");

		mockAssociateMap = new HashMap<>();
		mockAssociateMap.put(assoc1.getId(), assoc1);
		mockAssociateMap.put(assoc2.getId(), assoc2);
		mockAssociateMap.put(assoc3.getId(), assoc3);
		mockAssociateMap.put(assoc4.getId(), assoc4);
		mockAssociateMap.put(assoc5.getId(), assoc5);
		mockAssociateMap.put(assoc6.getId(), assoc6);

		mockClientMappedJSON = new HashMap<>();
		mockClientMappedJSON.put(1, createCMJ(1, "c1", 1));

		mockCurriculumJSON = new TreeSet<>();
		mockCurriculumJSON.add(createCUJ("c1", 1, 1));

		mockAssociateSet = new TreeSet<>();
		mockAssociateSet.add(assoc1);
		mockAssociateSet.add(assoc2);
		mockAssociateSet.add(assoc3);

		Mockito.when(mockAssociateDao.getAllAssociates()).thenReturn(mockAssociateSet);

		associateService = new AssociateService(mockAssociateDao);
	}

	@Test
	public void testGetMapppedInfo() {
		Set<ClientMappedJSON> mapped = new TreeSet<>(AssociateService.getMappedInfo(1).values());
		Set<ClientMappedJSON> testMapped = new TreeSet<>(mockClientMappedJSON.values());
		Assert.assertTrue(testMapped.equals(mapped));
	}

	@Test
	public void testGetUnmappedInfo() {
		Set<CurriculumJSON> unmapped = AssociateService.getUnmappedInfo(1);
		Assert.assertEquals(mockCurriculumJSON, unmapped);
	}

	AssociateInfo createAssocInfo(int id, int batchId, String batchName, int clientId, String clientName, int msid,
			String msName, int currId, String currName) {
		AssociateInfo assoc = new AssociateInfo();
		assoc.setId(new Integer(id));
		assoc.setBatchId(new Integer(batchId));
		assoc.setBatchName(batchName);
		assoc.setBid(new Integer(batchId));
		assoc.setClid(new Integer(clientId));
		assoc.setClientId(new Integer(clientId));
		assoc.setClient(clientName);// ----------
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

	CurriculumJSON createCUJ(String name, int count, int id) {
		CurriculumJSON cuj = new CurriculumJSON();
		cuj.setName(name);
		cuj.setCount(count);
		cuj.setId(id);
		return cuj;
	}
}