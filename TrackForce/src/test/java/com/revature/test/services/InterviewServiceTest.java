package com.revature.test.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.dao.InterviewDao;
import com.revature.entity.TfInterview;
import com.revature.services.InterviewService;

public class InterviewServiceTest {

	@Mock
	private InterviewDao interviewDao;

	@Mock
	private InterviewDao interviewDaoEmpty;

	@InjectMocks
	private InterviewService interviewService;

	@InjectMocks
	private InterviewService interviewServiceEmpty;

	private void setupMocks() throws IOException {
        MockitoAnnotations.initMocks(this);

        // Create interviews to mock
		TfInterview ii1 = new TfInterview();
		ii1.setId(7);
		ii1.setInterviewDate(new Timestamp(1000L));
		TfInterview ii2 = new TfInterview();
		ii2.setId(4);
		ii2.setInterviewDate(new Timestamp(3000L));
		TfInterview ii3 = new TfInterview();
		ii3.setId(9);
		ii3.setInterviewDate(new Timestamp(2000L));

		// Create a list of interviews for mocking
		List<TfInterview> interviewsList = new ArrayList<>();
		interviewsList.add(ii1);
		interviewsList.add(ii2);
		interviewsList.add(ii3);

		// Create an empty set for mocking
		List<TfInterview> interviewsEmpty = new ArrayList<>();

		// Set when each mocked set/map should be used
		Mockito.when(interviewDao.getInterviewsByAssociate(Mockito.anyInt())).thenReturn(interviewsList);
		Mockito.when(interviewDao.getAllInterviews()).thenReturn(interviewsList);
		Mockito.when(interviewDao.createInterview(Mockito.any())).thenReturn(true);
		Mockito.when(interviewDao.updateInterview(Mockito.any())).thenReturn(true);
		Mockito.when(interviewDao.getInterviewById(5)).thenReturn(ii1);
		Mockito.when(interviewDaoEmpty.getInterviewsByAssociate(Mockito.anyInt())).thenReturn(interviewsEmpty);
		Mockito.when(interviewDaoEmpty.getAllInterviews()).thenReturn(interviewsEmpty);
		Mockito.when(interviewDaoEmpty.createInterview(Mockito.any())).thenReturn(false);
		Mockito.when(interviewDaoEmpty.updateInterview(Mockito.any())).thenReturn(false);
		Mockito.when(interviewDaoEmpty.getInterviewById(-1)).thenReturn(null);

		interviewService = new InterviewService(interviewDao);
		interviewServiceEmpty = new InterviewService(interviewDaoEmpty);
	}

	@BeforeClass
	public void beforeClass() throws IOException {
		setupMocks();
	}

	/**
	 * Test to ensure that the appropriate collection of interviews is returned by the
	 * method. If no list is returned, IndexOutOfBoundsException should be thrown
	 * @throws IOException
	 */
	@Test(enabled = true)
	public void testGetInterviewsByAssociate() {
		List<TfInterview> interviews = interviewService.getInterviewsByAssociate(0);
		assertTrue(interviews.size() == 3);
		assertTrue(interviews.get(1).getId() == 4);
		assertTrue(interviews.get(1).getInterviewDate().getTime() == 3000L);
		assertFalse(interviews.size() == 0);
		List<TfInterview> interviewsEmpty = interviewServiceEmpty.getInterviewsByAssociate(0);
		assertTrue(interviewsEmpty.size() == 0);
		try {
			interviewsEmpty.get(1).getId();
			assertFalse(true);
		} catch (IndexOutOfBoundsException npe) {
			assertTrue(true);
		}
		try {
			interviewsEmpty.get(1).getInterviewDate().getTime();
			assertFalse(true);
		} catch (IndexOutOfBoundsException npe) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test to ensure that the appropriate collection of interviews is returned by the
	 * method. If no list is returned, IndexOutOfBoundsException should be thrown
	 * @throws IOException
	 */
	@Test(enabled = true)
	public void testGetAllInterviews() {
		List<TfInterview> interviews = interviewService.getAllInterviews();
		assertTrue(interviews.size() == 3);
		assertTrue(interviews.get(1).getId() == 4);
		assertTrue(interviews.get(1).getInterviewDate().getTime() == 3000L);
		assertFalse(interviews.size() == 0);
		List<TfInterview> interviewsEmpty = interviewServiceEmpty.getInterviewsByAssociate(0);
		assertTrue(interviewsEmpty.size() == 0);
		try {
			interviewsEmpty.get(1).getId();
			assertFalse(true);
		} catch (IndexOutOfBoundsException npe) {
			assertTrue(true);
		}
		try {
			interviewsEmpty.get(1).getInterviewDate().getTime();
			assertFalse(true);
		} catch (IndexOutOfBoundsException npe) {
			assertTrue(true);
		}
	}

	/**
	 * Test to ensure that the service method for accessing the InterviewDao functions
	 * as expected.
	 */
	@Test(enabled = true)
	public void testCreateInterview() {
		assertTrue(interviewService.createInterview(new TfInterview()));
		assertFalse(interviewServiceEmpty.createInterview(new TfInterview()));
	}

	/**
	 * Test to ensure that the service method for accessing the InterviewDao functions
	 * as expected.
	 */
	@Test(enabled = true)
	public void testUpdateInterview() {
		assertTrue(interviewService.updateInterview(new TfInterview()));
		assertFalse(interviewServiceEmpty.updateInterview(new TfInterview()));
	}
	
	/**
	 * Test that the correct interview is passed by this method. Additionally test that the 
	 * service can handle bad input, and that it can return a null interview if not interview
	 * is found.
	 */
	@Test(enabled = true)
	public void testGetInterviewById() {
		TfInterview interview = interviewService.getInterviewById(5);
		assertTrue(interview.getId() == 7);
		assertTrue(interview.getInterviewDate().getTime() == 1000L);
		assertFalse(interview.getId() == 4);
		assertFalse(interview.getInterviewDate().getTime() == 2000L);
		interview = interviewServiceEmpty.getInterviewById(-1);
		assertTrue(interview == null);
		try {
			interview.getId();
			assertTrue(false);
		} catch (NullPointerException npe) {
			assertTrue(true);
		}
	}

}
