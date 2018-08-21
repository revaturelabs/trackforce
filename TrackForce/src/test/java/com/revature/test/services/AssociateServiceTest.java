package com.revature.test.services;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.dao.AssociateDao;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.services.AssociateService;
import com.revature.services.UserService;

/**
 * Tests the various methods in the AssociateService to ensure that they are
 * functioning properly
 * 
 * @author Daniel Lani
 * 
 * @since 6.06.14.18
 */
public class AssociateServiceTest {

	private TfAssociate assoc1, assoc2, assoc3, assoc4, associate;
	private TfUser user;
	private TfRole role;
	private List<TfAssociate> mockAssociates;
	UserService userService = new UserService();

	@Mock // creates a mock of the associateDao
	private AssociateDao mockAssociateDao;

	@InjectMocks // injects the mocked dao into the service class
	private AssociateService service;

	/**
	 * Initializes the associates and mock methods for get associates
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@BeforeTest
	public void initDb() throws SQLException, IOException {
		service = new AssociateService(mockAssociateDao);
		MockitoAnnotations.initMocks(this);

		// create 3 associates
		assoc1 = new TfAssociate();
		assoc1.setId(1);
		assoc2 = new TfAssociate();
		assoc1.setId(2);
		assoc3 = new TfAssociate();
		assoc3.setId(3);
		assoc4 = new TfAssociate();
		assoc4.setId(4);
		
		role = new TfRole();
		role = userService.getRole(1);
		TfRole conRole = new TfRole();
		conRole.setTfRoleId(1);
		
		user = new TfUser();
		user.setIsApproved(1);
		user.setPassword("password");
		user.setUsername("TestUsernameChris");
		user.setTfRole(role);
		user.setRole(1);
		
		associate = new TfAssociate();
		associate.setFirstName("AssociateServerTest");
		associate.setLastName("Associate");

		//sets the mock method for the mockAssociateDao getAssociate method
		when(mockAssociateDao.getAssociate(0)).thenReturn(null);
	}

	/**
	 * Initializes the associates list and the mock for 
	 * the get all associate method
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@BeforeClass
	public void initMockAssociateList() {
		mockAssociates = new ArrayList<>();
		mockAssociates.add(assoc1);
		mockAssociates.add(assoc2);
		mockAssociates.add(assoc3);

		when(mockAssociateDao.getAllAssociates()).thenReturn(mockAssociates);
	}

	/**
	 * Initializes the associates list to null and the mock for the get all
	 * associate method
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@BeforeGroups("initNullList")
	public void initNullList() {
		when(mockAssociateDao.getAllAssociates()).thenReturn(null);
	}

	/**
	 * Initializes the associates list to an empty list and the mock for the get all
	 * associate method
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@BeforeGroups("initEmptyList")
	public void initEmptyList() {
		mockAssociates = new ArrayList<>();
		when(mockAssociateDao.getAllAssociates()).thenReturn(mockAssociates);
	}

	/**
	 * Tests the getAllAssociates method in the service 
	 * under standard conditions
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 1, groups = { "initAssociateList" })
	public void testGetAllAssociates() {
		ArrayList<TfAssociate> actualAssociates = new ArrayList<>(service.getAllAssociates());
		ArrayList<TfAssociate> expectedAssociates = new ArrayList<>(mockAssociates);
		assertEquals(expectedAssociates,actualAssociates);
	}

	/**
	 * Tests the getAllAssociates method in the service 
	 * when an empty list is returned
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 2, groups = { "initEmptyList" })
	public void testGetAllAssociatesEmpty() {
		assertEquals(service.getAllAssociates(), new ArrayList<TfAssociate>());
	}
	
	/**
	 * Tests the getAllAssociates method in the service 
	 * when a null list is returned
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 3, groups = { "initNullList" })
	public void testGetAllAssociatesNull() {

		assertNull(service.getAllAssociates());
	}
	
	/**
	 * Tests the getAssociate method in the service 
	 * when there is an empty list of Associates
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=4, groups= {"initEmptyList"})
	public void testGetAssociateEmpty() {
		when(mockAssociateDao.getAssociate(1)).thenReturn(null);
		TfAssociate actual = service.getAssociate(1);
		assertEquals(null, actual);
	}
	
	/**
	 * Tests the getAssociate method in the service 
	 * when there is an attempt to get an associate that doesn't exist
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=5, groups= {"initAssociateList"})
	public void testGetNonExistanceAssociate() {
		TfAssociate actual = service.getAssociate(0);
		assertEquals(null, actual);
	}
	
	/**
	 * Tests the getAssociate method in the service 
	 * under normal conditions
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=6, groups= {"initAssociateList"})
	public void testGetAssociate() {
		when(mockAssociateDao.getAssociate(1)).thenReturn(assoc1);
		TfAssociate actual = service.getAssociate(1);
		assertEquals(assoc1, actual);
	}
	
	/**
	 * Tests the getAssociate method in the service 
	 * when there is a null list of Associates
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=7, groups= {"initNullList"})
	public void testGetAssociateNull() {
		when(mockAssociateDao.getAssociate(1)).thenReturn(null);
		TfAssociate actual = service.getAssociate(1);
		assertEquals(null, actual);
	}
	
	/**
	 * Tests the updateAssociatePartial method when an empty associate
	 * is passed to the method
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=8)
	public void testUpdateAssociateWithEmpty() {
		when(mockAssociateDao.updateAssociatePartial(any(TfAssociate.class))).thenReturn(true);
		Boolean actual = service.updateAssociatePartial(new TfAssociate());
		assertTrue(actual);
	}
	
	/**
	 * Tests the updateAssociatePartial method under normal conditions
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=9)
	public void testUpdateAssociate() {
		when(mockAssociateDao.updateAssociatePartial(assoc1)).thenReturn(true);
		Boolean actual = service.updateAssociatePartial(assoc1);
		assertTrue(actual);
	}
	
	/**
	 * Tests the updateAssociatePartial method when
	 * an associate that doesn't exist is passed for update
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=10)
	public void UpdateNonExistantAssociate() {
		when(mockAssociateDao.updateAssociatePartial(assoc4)).thenReturn(false);
		Boolean actual = service.updateAssociate(assoc4);
		assertFalse(actual);
	}

	/**
	 * Tests the updateAssociatePartial method when a null associate is passed
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=11)
	public void testUpdateAssociateWithNull() {
		when(mockAssociateDao.updateAssociatePartial(null)).thenReturn(false);
		Boolean actual = service.updateAssociate(null);
		assertFalse(actual);
	}
	
	/**
	 * Tests the updateAssociates method under normal conditions
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=12,groups = {"initAssociateList"})
	public void testUpdateAssociates() {
		
		when(mockAssociateDao.updateAssociates(Matchers.anyListOf(TfAssociate.class))).thenReturn(true);
		Boolean actual = service.updateAssociates(mockAssociates);
		assertTrue(actual);
	}
	
	/**
	 * 1806_Chris_P: Updated test to expect a NullPointer Exception (8/15/18)
	 * Tests the updateAssociatePartial method when the list consists of
	 * both empty and valid associates
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=13)
	public void testUpdateAssociatesAndEmpty() {
		when(mockAssociateDao.updateAssociates(Matchers.anyListOf(TfAssociate.class))).thenReturn(false);
		Boolean actual = service.updateAssociates(mockAssociates);
		assertFalse(actual);
	}
	
	/**
	 * Tests the updateAssociatePartial method when the list consists of
	 * both null and valid associates
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=14)
	public void testUpdateAssociatesAndNull() {
		when(mockAssociateDao.updateAssociates(Matchers.anyListOf(TfAssociate.class))).thenReturn(false);
		mockAssociates.add(null);
		Boolean actual = service.updateAssociates(mockAssociates);
		assertFalse(actual);
	}
	
	/**
	 * Tests the updateAssociates method when a list of
	 * empty associates is passed to the method
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=15, groups= {"initEmptyList"})
	public void testUpdateAssociatesWithEmpty() {
		when(mockAssociateDao.updateAssociates(Matchers.anyListOf(TfAssociate.class))).thenReturn(false);
		
		Boolean actual = service.updateAssociates(mockAssociates);
		assertFalse(actual);
	}
	
	/**
	 * Tests the updateAssociates method when a list of
	 * null associates is passed to the method
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=16, groups= {"initNullList"})
	public void testUpdateAssociatesWithNull() {
		when(mockAssociateDao.updateAssociates(Matchers.anyListOf(TfAssociate.class))).thenReturn(false);
		Boolean actual = service.updateAssociates(mockAssociates);
		assertFalse(actual);
	}
	
	/**
	 * Tests the createAssociate method under normal conditions
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=1)
	public void testCreateAsssociate() {
		user.setRole(4);
		user.setUsername("Associate2");
		associate.setUser(user);
		associate.setFirstName("Carlsbad");
		
		when(mockAssociateDao.createAssociate(associate)).thenReturn(true);
		Boolean actual = service.createAssociate(associate);
		assertTrue(actual);
	}
	
	/**
	 * Tests the createAssociate method when a
	 * null associate is passed to the method
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=18, expectedExceptions = NullPointerException.class)
	public void testCreateAsssociateNull() {
		when(mockAssociateDao.createAssociate(null)).thenReturn(false);
		Boolean actual = service.createAssociate(null);
		assertFalse(actual);
	}
	
	/**
	 * Tests the createAssociate when the passed associate is empty
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=19, expectedExceptions = NullPointerException.class)
	public void testCreateAsssociateEmpty() {
		when(mockAssociateDao.createAssociate(new TfAssociate())).thenReturn(false);
		Boolean actual = service.createAssociate(new TfAssociate());
		assertFalse(actual);
	}
	
	/**
	 * Tests the createAssociate when a duplicate is added
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=2)
	public void testCreateAsssociateDuplicate() {
		user.setRole(4);
		user.setUsername("Associate2");
		
		assoc1.setUser(user);
		assoc1.setFirstName("Carlsbad");
		
		assoc2.setUser(user);
		assoc3.setFirstName("Carlbad");
		
		
		when(mockAssociateDao.createAssociate(assoc1)).thenReturn(true);
		Boolean actual = service.createAssociate(assoc2);
		assertFalse(actual);
	}
}