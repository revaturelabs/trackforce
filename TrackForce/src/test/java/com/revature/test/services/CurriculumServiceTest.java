package com.revature.test.services;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.dao.CurriculumDao;
import com.revature.entity.TfCurriculum;
import com.revature.entity.TfTrainer;
import com.revature.services.CurriculumService;
import com.revature.services.TrainerService;

public class CurriculumServiceTest {
	private TfCurriculum curr1, curr2, curr3, curr4;
	private List<TfCurriculum> mockCurriculum;
	
	@Mock
	private CurriculumDao mockCurriculumDao;
	
	@InjectMocks
	private CurriculumService service;
	
	/**
	 * @author andya
	 * @since 6.18.06.15
	 */
	
	@BeforeTest
	public void initDB() throws SQLException, IOException {
		MockitoAnnotations.initMocks(this);
		
		curr1 = new TfCurriculum();
		curr1.setId(1);
		curr2 = new TfCurriculum();
		curr2.setId(2);
		curr3 = new TfCurriculum();
		curr3.setId(3);
		curr4 = new TfCurriculum();
		curr4.setId(4);
		
//		when(mockCurriculumDao.getCurriculum(0)).thenReturn(null);
		service = new CurriculumService(mockCurriculumDao);
	}
	
	
	/**
	 * Initializes the curriculum list and the mock for 
	 * the get all curriculum method
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@BeforeGroups("initTrainerList")
	public void initMockAssociateList() {
		mockCurriculum = new ArrayList<>();
		mockCurriculum.add(curr1);
		mockCurriculum.add(curr2);
		mockCurriculum.add(curr3);
		mockCurriculum.add(curr4);
		when(mockCurriculumDao.getAllCurriculums()).thenReturn(mockCurriculum);
	}
	/**
	 * Initializes the curriculum list to an null and the mock for get all curriculum
	 * For negative testing
	 * @author andya
	 * @since 6.18.06.15
	 */
	@BeforeGroups("initNullList")
	public void initNullList() {
		when(mockCurriculumDao.getAllCurriculums()).thenReturn(null);
	}
	
	/**
	 * Initializes the curriculum list to empty
	 * For positive testing
	 * @author andya
	 * @since 6.18.06.15
	 */
	@BeforeGroups("initEmptyList")
	public void initEmptyList() {
		mockCurriculum = new ArrayList<>();
		when(mockCurriculumDao.getAllCurriculums()).thenReturn(mockCurriculum);
	}
	/**
	 * Tests the getAllCurriculums method in the service 
	 * under standard conditions
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 1, groups = { "initTrainerList" })
	public void testGetAllCurriculums() {
		ArrayList<TfCurriculum> actualCurriculums = new ArrayList<>(service.getAllCurriculums());
		ArrayList<TfCurriculum> expectedCurriculums = new ArrayList<>(mockCurriculum);
		assertEquals(expectedCurriculums,actualCurriculums);
	}
	

	/**
	 * Tests the getAllCurriculums method in the service 
	 * when an empty list is returned
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 2, groups = { "initEmptyList" })
	public void testGetAllTrainersEmpty() {
		assertEquals(service.getAllCurriculums(), new ArrayList<TfTrainer>());
	}
	
	/**
	 * Tests the getAllCurriculums method in the service 
	 * when a null list is returned
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 3, groups = { "initNullList" })
	public void testGetAllTrainersNull() {
		assertNull(service.getAllCurriculums());
	}
}