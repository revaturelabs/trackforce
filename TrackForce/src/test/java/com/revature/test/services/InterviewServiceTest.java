//package com.revature.test.services;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import java.io.IOException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.revature.dao.InterviewDao;
//import com.revature.model.InterviewInfo;
//import com.revature.services.InterviewService;
//
//public class InterviewServiceTest {
//
//	@Mock
//	private InterviewDao interviewDao;
//
//	@Mock
//	private InterviewDao interviewDaoEmpty;
//
//	@Mock
//	private InterviewDao interviewDaoNull;
//
//	@InjectMocks
//	private InterviewService interviewService;
//
//	@InjectMocks
//	private InterviewService interviewServiceEmpty;
//
//	@InjectMocks
//	private InterviewService interviewServiceNull;
//
//	private void setupMocks() throws IOException {
//        MockitoAnnotations.initMocks(this);
//
//        // Create interviews to mock
//		InterviewInfo ii1 = new InterviewInfo();
//		ii1.setId(7);
//		ii1.setTfInterviewDate(new Timestamp(1000L));
//		InterviewInfo ii2 = new InterviewInfo();
//		ii2.setId(4);
//		ii2.setTfInterviewDate(new Timestamp(1000L));
//		InterviewInfo ii3 = new InterviewInfo();
//		ii3.setId(9);
//		ii3.setTfInterviewDate(new Timestamp(2000L));
//
//		// Create a set of interviews for mocking
//		Set<InterviewInfo> interviews = new HashSet<>();
//		interviews.add(ii1);
//		interviews.add(ii2);
//		interviews.add(ii3);
//
//		// Create a map of interviews for mocking
//		Map<Integer, InterviewInfo> interviewsMap = new HashMap<>();
//		interviewsMap.put(2, ii1);
//		interviewsMap.put(1, ii2);
//		interviewsMap.put(3, ii3);
//
//		// Create an empty set for mocking
//		Set<InterviewInfo> interviewsEmpty = new HashSet<>();
//
//		// Set when each mocked set/map should be used
//		Mockito.when(interviewDao.getInterviewFromCache()).thenReturn(interviews);
//		Mockito.when(interviewDao.getAllInterviews()).thenReturn(interviewsMap);
//		Mockito.when(interviewDao.getInterviewsByAssociate(Mockito.anyInt())).thenReturn(interviewsMap);
//		Mockito.when(interviewDaoEmpty.getInterviewFromCache()).thenReturn(interviewsEmpty);
//		Mockito.when(interviewDaoNull.getInterviewFromCache()).thenReturn(null);
//
//		interviewService = new InterviewService(interviewDao);
//		interviewServiceEmpty = new InterviewService(interviewDaoEmpty);
//		interviewServiceNull = new InterviewService(interviewDaoNull);
//	}
//
//	@BeforeClass
//	public void beforeClass() throws IOException {
//		setupMocks();
//	}
//
//	/**
//	 * Tests that when getAllInterviews() is called and there is a set in the cache, that
//	 * set should be returned. When getAllInterviews is called and there is no set in the
//	 * cache, the logic dictates that a null set should be returned.
//	 */
//	@Test(enabled = true)
//	public void testGetAllInterviews() {
//		Set<InterviewInfo> interviews = interviewService.getAllInterviews();
//		assertTrue(interviews.size() == 3);
//		assertFalse(interviews.size() == 0);
//		Set<InterviewInfo> interviewsEmpty = interviewServiceEmpty.getAllInterviews();
//		assertTrue(interviewsEmpty.size() == 0);
//		assertFalse(interviewsEmpty.size() == 1);
//		Set<InterviewInfo> interviewsNull = interviewServiceNull.getAllInterviews();
//		assertTrue(interviewsNull == null);
//	}
//
//	/**
//	 * Tests that the the expected list of interviews is returned as well as that they are in
//	 * the specified order. Currently, either the tests logic is flawed or the logic from the
//	 * service method is flawed. This test will fail before any assertion
//	 */
//	@Test(enabled = true)
//	public void testGetAllInterviewsString() {
//		ArrayList<InterviewInfo> interviews = interviewService.getAllInterviews("asc");
//		assertTrue(interviews.size() == 3);
//		assertTrue(interviews.get(0).getId() == 4);
//		assertTrue(interviews.get(1).getId() == 7);
//		assertTrue(interviews.get(2).getId() == 9);
//	}
//
//	/**
//	 * TODO: figure out a way to write a meaningful test for this method without having to
//	 * hit the database.
//	 */
//	@Test(enabled = false)
//	public void testAddInterviewByAssociate() {
//	}
//
//	/**
//	 * TODO: figure out a way to write a meaningful test for this method without having to
//	 * hit the database.
//	 */
//	@Test(enabled = false)
//	public void testUpdateInterview() {
//	}
//
//	/**
//	 * Tests that for a given list in which two interviews conflict, that the final conflicts
//	 * list should be of size two. Currently throws a NoSuchElementException. This is likely
//	 * bugged code. Current reasoning is that it.next() is called two times after an it.hasnext()
//	 * call. If there is only 1 more element in it.next(), the second call to it.next() will
//	 * not find anything and throw the NoSuchElementException
//	 * @throws IOException
//	 */
//	@Test(enabled = true)
//	public void testGetInterviewConflicts() throws IOException {
//		List<InterviewInfo> conflicts = interviewService.getInterviewConflicts(0);
//		assertTrue(conflicts.size() == 2);
//	}
//
//	/**
//	 * This test checks that for some particular specified interview on some specified employee
//	 * that the correct interview is returned.
//	 * @throws IOException
//	 */
//	@Test(enabled = true)
//	public void testGetInterviewByAssociateAndInterviewId() throws IOException {
//		InterviewInfo ii = interviewService.getInterviewByAssociateAndInterviewid(0, 3);
//		assertTrue(ii.getTfInterviewDate().getTime() == 2000L);
//		assertFalse(ii.getTfInterviewDate().getTime() == 1000L);
//		assertTrue(ii.getId() == 9);
//		assertFalse(ii.getId() == 4);
//	}
//
//	/**
//	 * Test to ensure that the appropriate collection of interviews is returned by the
//	 * method.
//	 * @throws IOException
//	 */
//	@Test(enabled = true)
//	public void testGetInterviewsByAssociate() throws IOException {
//		Collection<InterviewInfo> interviews = interviewService.getInterviewsByAssociate(0);
//		assertTrue(interviews.size() == 3);
//		assertFalse(interviews.size() == 0);
//	}
//}
