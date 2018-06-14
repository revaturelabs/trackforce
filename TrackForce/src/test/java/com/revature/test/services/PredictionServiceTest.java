//package com.revature.test.services;
//
//import static org.testng.Assert.assertTrue;
//
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.Test;
//
//import com.revature.dao.PredictionDao;
//import com.revature.request.model.AssociatesWithTech;
//import com.revature.services.PredictionService;
//
///**
// * This method creates a mock for the predictionService to ensure the proper return type of
// * the service method. Additionally, it tests that the size of list returned for invalid dates
// * in 0 since it should not return any predictions
// * @author Jesse
// * @since 6.18.06.08
// */
//public class PredictionServiceTest {
//
//	@Mock
//	private PredictionDao mockedPreDao;
//
//	private PredictionService predictionServiceNoMock = new PredictionService();;
//
//	@InjectMocks
//	private PredictionService predictionServiceWithMock;
//
//	List<AssociatesWithTech> myList = new ArrayList<>();
//
//	/**
//	 * Set up mocking data as needed
//	 */
//	private void setupMocks() {
//		MockitoAnnotations.initMocks(this);
//
//		Mockito.when(mockedPreDao.getTotalAssociatesByTechBetweenDates(new Date(1000L), new Date(5000L)))
//				.thenReturn(myList);
//	}
//
//	/**
//	 * Call the mocking setups
//	 */
//	@BeforeSuite
//	public void beforeSuite() {
//		setupMocks();
//	}
//
//	/**
//	 * Test that the proper type is returned
//	 * Test that an improper date is not returning valid values
//	 */
//	@Test(enabled = true)
//	public void testGetAvailableAssociatesByTech() {
//		assertTrue(mockedPreDao.getTotalAssociatesByTechBetweenDates(new Date(1000L),
//				new Date(5000L)) instanceof ArrayList);
//		try {
//			assertTrue(predictionServiceNoMock.getAvailableAssociatesByTech(new Date(1528477894), new Date(1508477894)).size() == 0);
//		} catch (Exception e) {
//			System.out.println("No database connection");
//		}
//	}
//}
