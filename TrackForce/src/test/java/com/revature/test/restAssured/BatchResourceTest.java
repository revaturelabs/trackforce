package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.hamcrest.Matchers.hasSize;


import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.revature.services.BatchService;
import com.revature.services.JWTService;

import io.restassured.response.Response;

/**
 * Tests to ensure that that batches are only accessible to the right users and that all
 * behavior is intended in relationship to date ranges
 * 
 * @author Daniel L.
 * @since 06.18.06.19
 */
public class BatchResourceTest {


	static final String URL = "http://52.87.205.55:8086/TrackForce/batches";
	//static final String URL = "http://localhost:8085/TrackForce/batches";

	private String token;
	BatchService service;

	/**
	 * Setup to run before any test is run
	 */
	@BeforeClass
	public void beforeClass() {
		token = JWTService.createToken("TestAdmin", 1);
		System.out.println(token);
		service = new BatchService();
	}

	/**
	 * Test to ensure that each of the roles besides an associate is able to access batches.
	 * Verify that the batches returned are what we would expect.
	 */
	@Test(priority = 1)
	public void getAllBatchesTest() {
		int size = service.getAllBatches().size();
		Response response = given().header("Authorization", token).when().get(URL).then().extract().response();
		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));

		given().header("Authorization", token).when().get(URL).then().assertThat().body("batchName",
				Matchers.hasSize(size));

		token = JWTService.createToken("TestSales", 2);
		response = given().header("Authorization", token).when().get(URL).then().extract().response();
		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
	}
	
	/**
	 * Part two of testing getAllBatches
	 */
	@Test(priority = 2)
	public void getAllBatchesTest2() {
		token = JWTService.createToken("TestManger", 3);
		Response response = given().header("Authorization", token).when().get(URL).then().extract().response();
		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));

		token = JWTService.createToken("TestTrainer", 4);
		response = given().header("Authorization", token).when().get(URL).then().extract().response();
		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
	}

	/**
	 * Test to ensure that a backwards date returns no batches
	 */
	@Test(priority = 3)
	public void getAllBatchesBackwardsDateTest() {
		Response response = given().header("Authorization", token).queryParam("start", 1600000000000L)
				.queryParam("start", 1490000000000L).when().get(URL).then().extract().response();

		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
	}

	/**
	 * Test to ensure that batches from a particular time range can be successfully retrieved
	 */
	@Test(priority = 4)
	public void getBatchesInARangeTest() {
		Response response = given().header("Authorization", token).queryParam("start", 1480000000000L)
				.queryParam("start", 1490000000000L).when().get(URL).then().extract().response();

		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
	}

	/**
	 * Test to ensure that an invalid token does not allow access to batches
	 */
	@Test(priority = 5)
	public void getAllBatchesInvalidAuthorizationTest() {
		Response response = given().header("Authorization", "NotAuthorization").when().get(URL).then().extract()
				.response();
		assertTrue(response.getStatusCode() == 401);
	}

	/**
	 * Test to make sure that associates do not have access to batches
	 */
	@Test(priority = 6)
	public void getAllBatchesUnauthorizedTest() {
		token = JWTService.createToken("TestAssociate", 5);
		Response response = given().header("Authorization", token).when().get(URL).then().extract().response();
		assertTrue(response.getStatusCode() == 403);
		assertTrue(response.contentType().equals("application/json"));
		given().header("Authorization", token).when().get(URL).then().assertThat().body("batchName",
				Matchers.hasSize(service.getAllBatches().size()));
	}
}