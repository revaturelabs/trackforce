package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.entity.TfBatch;
import com.revature.services.BatchService;
import com.revature.services.JWTService;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Tests to ensure that that batches are only accessible to the right users and that all
 * behavior is intended in relationship to date ranges
 * 
 * 1808_Seth_L
 * Warning GET /batches works and returns with all batches but if you add "start" and "end" date query parameters
 * the server throws an exception.  Use GET /batches/withindates with these query parameters to get batches within dates
 * 
 * 1809_Katelyn_B
 * Added tests for /batch/{id} end point
 * 
 * @author Daniel L.
 * @since 06.18.06.19
 */
public class BatchRestTest {


	static final String URL = "http://52.87.205.55:8086/TrackForce/batches";
	//static final String URL = "http://localhost:8085/TrackForce/batches";

	private String adminToken, trainerToken, salesToken, stagingToken, assocToken;
	BatchService service;
	private int knownBatchId;
	private Long startDate , endDate;
	/**
	 * Setup to run before any test is run
	 */
	@BeforeClass
	public void beforeClass() {
		service = new BatchService();
		
		adminToken = JWTService.createToken("TestAdmin", 1); 
		trainerToken = JWTService.createToken("TestTrainer", 2);
		salesToken = JWTService.createToken("TestSales", 3);
		stagingToken = JWTService.createToken("TestStaging", 4);
		assocToken = JWTService.createToken("TestAssoc", 5);
		
		knownBatchId = 0;
		
		startDate = 1490000000000L;
		endDate = 1600000000000L;
	}

	/**
	 * A positive test to ensure that admins are able to access batches.
	 * Verify that the batches returned are what we would expect.
	 * @author Katelyn B 
	 * Written Nov. 4, 2018, Batch 1809
	 */
	@Test(enabled = true,priority = 1)
	public void testGetAllBatchesAdminHappyPath() {
		int size = service.getAllBatches().size();
		Response response = given().header("Authorization", adminToken).when().get(URL).then().extract().response();
		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.contentType(),"application/json");

		given().header("Authorization", adminToken).when().get(URL).then().assertThat().body("batchName",
				Matchers.hasSize(size));
	}
	
	/**
	 * A positive test to ensure that trainers are able to access batches.
	 * Verify that the batches returned are what we would expect.
	 * @author Katelyn B 
	 * Written Nov. 4, 2018, Batch 1809
	 */
	@Test(enabled = true,priority = 1)
	public void testGetAllBatchesTrainerHappyPath() {
		int size = service.getAllBatches().size();
		Response response = given().header("Authorization", trainerToken).when().get(URL).then().extract().response();
		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.contentType(),"application/json");

		given().header("Authorization", trainerToken).when().get(URL).then().assertThat().body("batchName",
				Matchers.hasSize(size));
	}
	
	/**
	 * Another positive test to ensure that Staging can access batches
	 * @author Katelyn B 
	 * Written Nov. 4, 2018, Batch 1809
	 */
	@Test(enabled = true,priority = 2)
	public void testGetAllBatchesTestSalesHappyPath() {
		int size = service.getAllBatches().size();
		Response response = given().header("Authorization", salesToken).when().get(URL).then().extract().response();
		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.contentType(),"application/json");

	}
	
	/**
	 * Another positive test to ensure that Staging can access batches
	 * @author Katelyn B 
	 * Written Nov. 4, 2018, Batch 1809
	 */
	@Test(enabled = true,priority = 2)
	public void testGetAllBatchesTestStagingHappyPath() {
		Response response = given().header("Authorization", stagingToken).when().get(URL).then().extract().response();
		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.contentType(),"application/json");
	}

	/**
	 * Test to ensure that a backwards date returns no batches
	 * Bad path
	 * @author Katelyn B 
	 * Written Nov. 4, 2018, Batch 1809
	 */
	@Test(enabled = true,priority = 3)
	public void testGetAllBatchesInARangeBadRange() {
		Response response = given().header("Authorization", adminToken).queryParam("start", endDate)
				.queryParam("end", startDate).when().get(URL + "/withindates").then().extract().response();
		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.contentType(),"application/json");
		assertEquals(response.body().jsonPath().getList("courseBatches").size(), 0);
	}

	/**
	 * Test to ensure that batches from a particular time range can be successfully retrieved
	 * @author Katelyn B 
	 * Written Nov. 4, 2018, Batch 1809
	 */
	@Test(enabled = true,priority = 4)
	public void testGetBatchesInARangeHappyPath() {
		Response response = given().header("Authorization", adminToken).queryParam("start", startDate)
				.queryParam("end", endDate).when().get(URL + "/withindates").then().extract().response();
		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.contentType(),"application/json");
	}

	/**
	 * Test to ensure that an invalid token does not allow access to batches
	 * @author Katelyn B 
	 * Written Nov. 4, 2018, Batch 1809
	 */
	@Test(enabled = true, priority = 5)
	public void testGetAllBatchesBadToken() {
		Response response = given().header("Authorization", "NotAuthorization").when().get(URL).then().extract()
				.response();
		assertEquals(response.getStatusCode(), 401);
	}

	/**
	 * Test to make sure that associates do not have access to batches
	 * Disabled because unhappypathtest checks this method
	 * @author Katelyn B 
	 * Written Nov. 4, 2018, Batch 1809
	 */
	@Test(enabled = false, priority = 6)
	public void testGetAllBatchesBadAuthorization() {
		Response response = given().header("Authorization", assocToken).when().get(URL).then().extract().response();
		assertEquals(response.getStatusCode(), 403);
		assertEquals(response.contentType(),"application/json");
		given().header("Authorization", assocToken).when().get(URL).then().assertThat().body("batchName",
				Matchers.hasSize(service.getAllBatches().size()));
	}
	
	/**
	 * This positive test checks that the get associates method works for admin and that every associate returned belongs to that batch
	 * @author Seth L.
	 */
	@Test(enabled = true,priority = 7)
	public void testGetAssociatesByBatchIdHappyPath() {
		given().header("Authorization", adminToken).when().get(URL + "/" + knownBatchId + "/associates")
			.then().assertThat().statusCode(200).and().assertThat().body("batch.id", everyItem(equalTo(0)));

	}
	
	/**
	 * This positive test checks if the getbatchdetails method works and that the data returned is valid
	 * @author Seth L.
	 */
	@Test(enabled = true,priority = 8)
	public void testGetDetailsHappyPath() {
		Response res = given().header("Authorization", adminToken).queryParam("start", startDate).queryParam("end", endDate)
		.queryParam("courseName", "Java").get(URL + "/details");
		assertEquals(res.getStatusCode(), 200);
		for(Object o : res.getBody().jsonPath().getList("courseBatches")) { //read response body as a JSON List
			HashMap<String, Object> h = (HashMap<String, Object>) o;
			Long sDate = (Long) h.get("startDate");
			Long eDate = (Long) h.get("endDate");
			String name = (String) h.get("batchName");
			//the end date for each batch should be within range of the start and end date
			assertTrue(startDate <= eDate && eDate <= endDate );
			// the start date for each batch should be within range of the start and end date
			assertTrue(startDate <= sDate && sDate <= endDate );
			//every batch must have "java" in its name
			assertTrue(name.toUpperCase().contains("JAVA"));
		}
	}
	
	/**
	 * This positive test checks if the countby works as intended, which the same as /details but its an aggregate count of all
	 * associates from list of batches.  Thus, this test will compare the result of /countby and compare with the calculated result
	 * of counting associates from /details
	 * @author Seth L.
	 */
	@Test(enabled = true,priority = 9, dependsOnMethods = {"testGetDetailsHappyPath"})
	public void testVerifyDetailsCount() {
		Response detsRes = given().header("Authorization", adminToken).queryParam("start", startDate).queryParam("end", endDate)
				.queryParam("courseName", "Java").get(URL + "/details");
		Integer total = 0;
		for(Object o : detsRes.getBody().jsonPath().getList("courseBatches")) {
			HashMap<String, Object> h = (HashMap<String, Object>) o;
			Integer count = (Integer) h.get("associateCount");
			total += count;
		}
		Response res = given().header("Authorization", adminToken).queryParam("start", startDate).queryParam("end", endDate)
				.queryParam("courseName", "Java").get(URL + "/countby");
		Integer resultCount = res.getBody().jsonPath().getInt("associateCount");
		assertEquals(total, resultCount);
	}
	
	/**
	 * Happy path testing for getBatchInfo with admin token
	 * checks that the response has the correct status code, content type, and actually contains
	 * the correct batch information.
	 * 
	 * @author Katelyn B 
	 * Written Nov. 4, 2018, Batch 1809
	 */
	@Test(enabled=true, priority=1)
	public void testGetBatchInfoAdminHappyPath() {
		TfBatch batch = service.getBatchById(knownBatchId);
		Response response = given().header("Authorization", adminToken).when().get(URL+"/batch/"+knownBatchId).then().extract().response();
		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.contentType(),"application/json");
	
		assertEquals(response.getBody().jsonPath().getString("batchName"), batch.getBatchName());
		assertEquals(response.getBody().jsonPath().getString("id"), batch.getId().toString());
	}
	
		/**
	 * Happy path testing for getBatchInfo with trainer token
	 * checks that the response has the correct status code, content type, and actually contains
	 * the correct batch information.
	 * 
	 * @author Katelyn B 
	 * Written Nov. 4, 2018, Batch 1809
	 */
	@Test(enabled=true, priority=1)
	public void testGetBatchInfoTrainerHappyPath() {
		TfBatch batch = service.getBatchById(knownBatchId);
		Response response = given().header("Authorization", trainerToken).when().get(URL+"/batch/"+knownBatchId).then().extract().response();
		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.contentType(),"application/json");
		
		assertEquals(response.getBody().jsonPath().getString("batchName"), batch.getBatchName());
		assertEquals(response.getBody().jsonPath().getString("id"), batch.getId().toString());
	}
	
	/**
	 * Happy path testing for getBatchInfo with sales token
	 * checks that the response has the correct status code, content type, and actually contains
	 * the correct batch information.
	 * 
	 * @author Katelyn B 
	 * Written Nov. 4, 2018, Batch 1809
	 */
	@Test(enabled=true, priority=1)
	public void testGetBatchInfoSalesHappyPath() {
		TfBatch batch = service.getBatchById(knownBatchId);
		Response response = given().header("Authorization", salesToken).when().get(URL+"/batch/"+knownBatchId).then().extract().response();
		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.contentType(),"application/json");
		
		assertEquals(response.getBody().jsonPath().getString("batchName"), batch.getBatchName());
		assertEquals(response.getBody().jsonPath().getString("id"), batch.getId().toString());

	}
	/**
	 * Happy path testing for getBatchInfo with staging token
	 * checks that the response has the correct status code, content type, and actually contains
	 * the correct batch information.
	 * 
	 * @author Katelyn B 
	 * Written Nov. 4, 2018, Batch 1809
	 */
	@Test(enabled=true, priority=1)
	public void testGetBatchInfoStagingHappyPath() {
		TfBatch batch = service.getBatchById(knownBatchId);
		Response response = given().header("Authorization", stagingToken).when().get(URL+"/batch/"+knownBatchId).then().extract().response();
		assertEquals(response.getStatusCode(), 200);
		assertEquals(response.contentType(),"application/json");
		
		assertEquals(response.getBody().jsonPath().getString("batchName"), batch.getBatchName());
		assertEquals(response.getBody().jsonPath().getString("id"), batch.getId().toString());
	}
	
	/**
	 * Unhappy path testing for getBatchInfo, tests to check that an Associate cannot view batch info
	 * 
	 * @author Katelyn B
	 * Written Nov. 4 2018, Batch 1809
	 */
	@Test(enabled=true, priority=10)
	public void testGetBatchInfoBadAuthorization() {
		Response response = given().header("Authorization", assocToken).when().get(URL+"/batch/"+knownBatchId).then().extract().response();
		assertEquals(response.getStatusCode(), 403);
	}
	
	/**
	 * Unhappy path testing for getBatchInfo, tests to check that a bad token cannot access batch info
	 * @author Katleyn B
	 * Written Nov. 4 2018, Batch 1809
	 */
	@Test(enabled=true, priority=10)
	public void testGetBatchInfoBadToken() {
		Response response = given().header("Authorization", "not a real token").when().get(URL+"/batch/"+knownBatchId).then().extract().response();
		assertEquals(response.getStatusCode(), 401);
	}
	
	/**
	 * Unhappy path testing for getBatchInfo, tests that a bad url gets a 404
	 * page
	 * @author Katelyn B
	 * Written Nov. 4 2018, Batch 1809
	 */
	@Test(enabled=true, priority=10)
	public void testGetBatchInfoBadUrl() {
		Response response = given().header("Authorization", adminToken).when().get(URL+"/badbatchurl/"+knownBatchId).then().extract().response();
		assertEquals(response.getStatusCode(), 404);
	}

	/**
	 * Unhappy path testing for getBatchInfo, tests that a Bad batch id gets a 404 page
	 * Using grossly large value for a bad batch value because batch 0 apparently exists
	 * @author Katelyn B
	 * Written Nov. 4 2018, Batch 1809
	 */
	@Test(enabled=true, priority=10)
	public void testGetBatchInfoBadId() {
		Response response = given().header("Authorization", adminToken).when().get(URL+"/batch/"+"9999999999999999999999999999999999999999999999999999").then().extract().response();
		assertEquals(response.getStatusCode(), 404);
	}

	/**
	 * An unhappy test method that checks every method but login to assure that those that need security must
	 * block the user if he:
	 * 1. uses an unsupported verb
	 * 2. uses a service without a token
	 * 3. uses a bad token
	 * 4. the role in the token deemed the user unauthorized to use the service
	 * As this method of unhappy testing is consistent, I (Seth L.) suggest using this and its dependent methods to perform unhappy tests for all
	 * Jersey resources.
	 * Note: GET /details fails because it expects an associate to be unauthorized but is authorized because in BatchResource.java getBatchDetails()
	 * has a method to check authorization that is commented out.  I leave it to the next group whether or not this method should not authorize associates.
	 * Same goes for /batch/{id}
	 * @author Seth L.
	 * @param method - comma-separated list of HTTP Verbs that the service uses
	 * @param url - the uri for the service
	 * @param needAuth - if the user has to be a higher level of authorization than Associate like Admin to use the service, this value is true;
	 */
	@Test(enabled = true, priority = 10, dataProvider = "urls")
	public void unhappyPathTest(String method, String url, Boolean needAuth) {
		String[] verbs = method.split(", ");
		url = URL + url;
		//test no token
		for(String verb : verbs) {
			sendRequest(verb,url, null).then().assertThat().statusCode(401);
			//test invalid token
			sendRequest(verb, url, new Header("Authorization", "badtoken")).then().assertThat().statusCode(401);
			//test with associate token
			if(needAuth)
				sendRequest(verb, url, new Header("Authorization", assocToken)).then().assertThat().statusCode(403);
		}
		//look for an HTTP verb not used
		String knownVerbs[] = new String[] {"GET", "POST", "PUT", "DELETE"};
		for(String verb : knownVerbs) {
			if(!method.contains(verb)) {
				//test unsupported verb
				sendRequest(verb, url, new Header("Authorization", adminToken)).then().assertThat().statusCode(405);
				break;
			}
		}
		
	}
	/**
	 * This method performs the appropriate request based on the HTTP verb and returns the response
	 */
	private Response sendRequest(String method, String url, Header h) {
		RequestSpecification given = given().contentType("application/json");
		if(h != null)
			given.header(h);
		switch(method) {
		case "GET":
			return given.when().get(url);
		case "POST":
			return given.when().post(url);
		case "PUT":
			return given.when().put(url);
		case "DELETE":
			return given.when().delete(url);
		default:
			return given().when().get();
		}
	}
	/**
	 * An array of arrays consisting of the HTTP verb, a url, and a boolean that tells if an authentication
	 * level higher than associate is needed to view a page
	 * @return
	 */
	@DataProvider(name = "urls") 
	public Object[][] getURLs() {
		return new Object[][]  {{"GET", "/", new Boolean(true)},
								{"GET", "?start=1490000000000&end=1600000000000", new Boolean(true)}, 
								{"GET", "/0/associates", new Boolean(true)},
								{"GET", "/details?start=1490000000000&end=1600000000000&courseName=Java", new Boolean(false)},
								{"GET", "/countby?start=1490000000000&end=1600000000000&courseName=Java", new Boolean(true)},
								{"GET", "/withindates?start=1490000000000&end=1600000000000", new Boolean(true)},
								{"GET", "/batch/0", new Boolean(false)}};
	}
}