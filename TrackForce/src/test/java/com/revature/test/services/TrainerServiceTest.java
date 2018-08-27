package com.revature.test.services;
import com.revature.dao.TrainerDao;
import com.revature.entity.TfTrainer;
import com.revature.services.TrainerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class TrainerServiceTest {
	private TfTrainer trainer1, trainer2, trainer3, trainer4;
	private List<TfTrainer> mockTrainers;
	
	@Mock
	private TrainerDao mockTrainerDao;
	
	@InjectMocks
	private TrainerService service;
	
	/**
	 * @author andya
	 * @since 6.18.06.15
	 */
	@BeforeTest
	public void initDb() throws SQLException, IOException {
		MockitoAnnotations.initMocks(this);

		// create 4 trainers
		trainer1 = new TfTrainer();
		trainer1.setId(1);
		trainer2 = new TfTrainer();
		trainer2.setId(2);
		trainer3 = new TfTrainer();
		trainer3.setId(3);
		trainer4 = new TfTrainer();
		trainer4.setId(4);

		//sets the mock method for the mockTrainerDao getTrainer method
		when(mockTrainerDao.getTrainer(0)).thenReturn(null);
		service = new TrainerService(mockTrainerDao);
	}
	
	
	/**
	 * Initializes the associates list and the mock for 
	 * the get all associate method
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@BeforeGroups("initTrainerList")
	public void initMockAssociateList() {
		mockTrainers = new ArrayList<>();
		mockTrainers.add(trainer1);
		mockTrainers.add(trainer2);
		mockTrainers.add(trainer3);

		when(mockTrainerDao.getAllTrainers()).thenReturn(mockTrainers);
	}
	/**
	 * Initializes the trainer list to an null and the mock for get all trainers
	 * For negative testing
	 * @author andya
	 * @since 6.18.06.15
	 */
	@BeforeGroups("initNullList")
	public void initNullList() {
		when(mockTrainerDao.getAllTrainers()).thenReturn(null);
	}
	
	/**
	 * Initializes the trainers list to empty
	 * For positive testing
	 * @author andya
	 * @since 6.18.06.15
	 */
	@BeforeGroups("initEmptyList")
	public void initEmptyList() {
		mockTrainers = new ArrayList<>();
		when(mockTrainerDao.getAllTrainers()).thenReturn(mockTrainers);
	}
	
	/**
	 * Tests the getAllAssociates method in the service 
	 * under standard conditions
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 1, groups = { "initTrainerList" })
	public void testGetAllTrainers() {
		ArrayList<TfTrainer> actualTrainers = new ArrayList<>(service.getAllTrainers());
		ArrayList<TfTrainer> expectedTrainers = new ArrayList<>(mockTrainers);
		assertEquals(expectedTrainers,actualTrainers);
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
	public void testGetAllTrainersEmpty() {
		assertEquals(service.getAllTrainers(), new ArrayList<TfTrainer>());
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
	public void testGetAllTrainersNull() {

		assertNull(service.getAllTrainers());
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
	public void testGetTrainersEmpty() {
		when(mockTrainerDao.getTrainer(1)).thenReturn(null);
		TfTrainer actual = service.getTrainer(1);
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
	@Test(priority=5, groups= {"initTrainerList"})
	public void testGetNonExistanceTrainer() {
		TfTrainer actual = service.getTrainer(0);
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
	@Test(priority=6, groups= {"initTrainerList"})
	public void testGetTrainer() {
		when(mockTrainerDao.getTrainer(1)).thenReturn(trainer1);
		TfTrainer actual = service.getTrainer(1);
		assertEquals(trainer1, actual);
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
	public void testGetTrainerNull() {
		when(mockTrainerDao.getTrainer(1)).thenReturn(null);
		TfTrainer actual = service.getTrainer(1);
		assertEquals(null, actual);
	}
	
	/**
	 * Tests the updateAssociate method when an empty associate
	 * is passed to the method
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=8)
	public void testUpdateTrainerWithEmpty() {
		when(mockTrainerDao.updateTrainer(any(TfTrainer.class))).thenReturn(true);
		Boolean actual = service.updateTrainer(new TfTrainer());
		assertTrue(actual);
	}
	
	/**
	 * Tests the updateAssociate method under normal conditions
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=9)
	public void testUpdateTrainer() {
		when(mockTrainerDao.updateTrainer(trainer1)).thenReturn(true);
		Boolean actual = service.updateTrainer(trainer1);
		assertTrue(actual);
	}
	
	/**
	 * Tests the updateAssociate method when 
	 * an associate that doesn't exist is passed for update
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=10)
	public void UpdateNonExistantTrainer() {
		when(mockTrainerDao.updateTrainer(trainer4)).thenReturn(false);
		Boolean actual = service.updateTrainer(trainer4);
		assertFalse(actual);
	}

	/**
	 * Tests the updateAssociate method when a null associate is passed
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=11)
	public void testUpdateTrainerWithNull() {
		when(mockTrainerDao.updateTrainer(null)).thenReturn(false);
		Boolean actual = service.updateTrainer(null);
		assertFalse(actual);
	}
			
	/**
	 * Tests the createAssociate method under normal conditions
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=12)
	public void testCreateTrainer() {
		when(mockTrainerDao.createTrainer(trainer1)).thenReturn(true);
		Boolean actual = service.createTrainer(trainer1);
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
	@Test(priority=13)
	public void testCreateTrainerNull() {
		when(mockTrainerDao.createTrainer(null)).thenReturn(false);
		Boolean actual = service.createTrainer(null);
		assertFalse(actual);
	}
	
	/**
	 * Tests the createAssociate when the passed associate is empty
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=14)
	public void testCreateTrainerEmpty() {
		when(mockTrainerDao.createTrainer(new TfTrainer())).thenReturn(false);
		Boolean actual = service.createTrainer(new TfTrainer());
		assertFalse(actual);
	}
	
	/**
	 * Tests the createAssociate when a duplicate is added
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority=15)
	public void testCreateTrainerDuplicate() {
		when(mockTrainerDao.createTrainer(trainer1)).thenReturn(false);
		Boolean actual = service.createTrainer(trainer1);
		assertFalse(actual);
	}
}