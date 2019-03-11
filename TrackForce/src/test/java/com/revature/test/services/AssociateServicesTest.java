package com.revature.test.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.revature.dao.AssociateDao;
import com.revature.daoimpl.AssociateDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfUser;
import com.revature.services.AssociateService;
import com.revature.test.utils.Log;
import com.revature.utils.HibernateUtil;

public class AssociateServicesTest {

	private AssociateService service;
	private Properties props;
	private AssociateDao dao;

	@Mock
	TfUser tUser;
	@Mock
	TfAssociate nassociate;

	@BeforeClass
	public void initialize() {
		dao = spy(new AssociateDaoImpl());
		service = spy(new AssociateService(dao));

		props = new Properties();

		try {
			FileInputStream propFile = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\database_entries.properties");
			props.load(propFile);
			propFile.close();
		} catch (FileNotFoundException e) {
			Log.Log.error(e.getMessage());
		} catch (IOException e) {
			Log.Log.error(e.getMessage());
		}
	}

	@BeforeMethod
	protected void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(priority = 0)
	public void testGetAssociateById() {
		TfAssociate associate = service.getAssociate(Integer.parseInt(props.getProperty("associate3_id")));

		assertEquals(props.getProperty("associate3_firstName"), associate.getFirstName());
		assertEquals(props.getProperty("associate3_lastName"), associate.getLastName());
		assertEquals(props.getProperty("associate3_feedback"), associate.getStagingFeedback());
	}

	@Test(priority = 2)
	public void testGetAssociateByUserId() {
		TfAssociate associate = service.getAssociateByUserId(Integer.parseInt(props.getProperty("associate50_user")));
		System.out.println(associate.getFirstName());

		assertEquals(associate.getFirstName(), props.getProperty("associate50_userFirst"));
		assertEquals(associate.getLastName(), props.getProperty("associate50_userLast"));
		assertEquals(associate.getStagingFeedback(), props.getProperty("associate50_userFeed"));

	}

	@Test(priority = 1)
	public void testGetAllAssociates() {
		List<TfAssociate> list = service.getAllAssociates();

		assertNotNull(list);
		assertTrue(!list.isEmpty());
	}

	@Test(priority = 2)
	public void testGetNAssociates() {
		List<TfAssociate> list = service.getNAssociates();

		assertNotNull(list);
		assertTrue(!list.isEmpty() && list.size() <= 60);
	}

	@Test(priority = 2)
	public void testGetAssociatePage() {
		int start = Integer.parseInt(props.getProperty("page_start"));
		int numResults = Integer.parseInt(props.getProperty("page_numResults"));
		int mktStat = Integer.parseInt(props.getProperty("page_mktStat"));
		int clientId = Integer.parseInt(props.getProperty("page_clientId"));

		// Test valid inputs
		List<TfAssociate> list = service.getAssociatePage(start, numResults, mktStat, clientId, "", "", "");
		assertNotNull(list);
		assertTrue(!list.isEmpty() && list.size() == numResults);

		// Test invalid inputs

	}

	@Test(priority = 2)
	public void testGetMappedAssociateByClient() {
		long count = Long.parseLong(props.getProperty("market_count"));
		long clientId = Long.parseLong(props.getProperty("market_clientId"));
		int markId = Integer.parseInt(props.getProperty("market_id"));

		assertTrue((long) service.getMappedAssociateCountByClientId(clientId, markId) >= count);
	}

	// Testing only if there is a Hibernate error thrown so we do not actually
	// change the database with this test. We CANNOT mock a database connection
	// if that's what we are testing.
	@Test(priority = 100)
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

		// Undo update change
		toChange.setFirstName(props.getProperty("associate_firstName"));
		toChange.setLastName(props.getProperty("associate_lastName"));
		service.updateAssociatePartial(toChange);
	}

	// Have not figured out how to stub dao method effectively.
	@Test(enabled = false)
	public void testCreateAssociate() {
		TfAssociate createAssociate = new TfAssociate();
		createAssociate.setFirstName(props.getProperty("createAssociate_firstName"));
		createAssociate.setLastName(props.getProperty("createAssociate_lastName"));
		createAssociate.setStagingFeedback(props.getProperty("createAssociate_feedback"));
		TfUser user = new TfUser();
		user.setPassword("password");
		createAssociate.setUser(user);

		service.createAssociate(nassociate);
		when(nassociate.getUser()).thenReturn(tUser);
		when(nassociate.getUser().getPassword()).thenReturn("password");
		Mockito.doNothing().when(tUser).setPassword(any());
		verify(dao).createAssociate(any(TfAssociate.class));
		Mockito.doReturn(true).when(dao).createAssociate(any(TfAssociate.class));
//		assertEquals(createAssociate.getFirstName(), 
//				service.getAssociateByUserId(Integer.parseInt(props.getProperty("createAssociate_Id"))).getFirstName());
//		assertEquals(createAssociate.getLastName(), 
//				service.getAssociateByUserId(Integer.parseInt(props.getProperty("createAssociate_Id"))).getLastName());
//		assertEquals(createAssociate.getStagingFeedback(), 
//				service.getAssociateByUserId(Integer.parseInt(props.getProperty("createAssociate_Id"))).getStagingFeedback());
	}

	@Test
	public void testApproveAssociate() {
		service.approveAssociate(Integer.parseInt(props.getProperty("approveAssociate_Id")));
		TfAssociate approvedAssociate = service
				.getAssociate(Integer.parseInt(props.getProperty("approveAssociate_Id")));

		assertEquals(approvedAssociate.getUser().getIsApproved(), Integer.parseInt(props.getProperty("approvedValue")));

		// undo approved status
		TfAssociate associate1 = service.getAssociate(Integer.parseInt(props.getProperty("approveAssociate_Id")));
		associate1.getUser().setIsApproved(0);
	}

	@AfterClass
	public void closeSession() {
		HibernateUtil.shutdown();
	}
}