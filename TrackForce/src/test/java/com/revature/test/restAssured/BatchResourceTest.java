package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.revature.services.BatchService;
import com.revature.services.JWTService;

import io.restassured.response.Response;

public class BatchResourceTest {


	static final String URL = "http://52.87.205.55:8086/TrackForce/batches";
	//static final String URL = "http://localhost:8085/TrackForce/batches";

	private String token;
	BatchService service;


	@BeforeClass
	public void beforeClass() {
		token = JWTService.createToken("TestAdmin", 1);
		System.out.println(token);
		service = new BatchService();
	}

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

		token = JWTService.createToken("TestManger", 3);
		response = given().header("Authorization", token).when().get(URL).then().extract().response();
		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));

		token = JWTService.createToken("TestTrainer", 4);
		response = given().header("Authorization", token).when().get(URL).then().extract().response();
		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
	}

	@Test(priority = 2)
	public void getAllBatchesBackwardsDateTest() {
		Response response = given().header("Authorization", token).queryParam("start", 1600000000000L)
				.queryParam("start", 1490000000000L).when().get(URL).then().extract().response();

		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
	}

	@Test(priority = 2)
	public void getBatchesInARangeTest() {
		Response response = given().header("Authorization", token).queryParam("start", 1480000000000L)
				.queryParam("start", 1490000000000L).when().get(URL).then().extract().response();

		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
	}

	@Test(priority = 3)
	public void getAllBatchesInvalidAuthorizationTest() {
		Response response = given().header("Authorization", "NotAuthorization").when().get(URL).then().extract()
				.response();
		assertTrue(response.getStatusCode() == 401);
	}

	@Test(priority = 4)
	public void getAllBatchesUnauthorizedTest() {
		token = JWTService.createToken("TestAssociate", 5);
		Response response = given().header("Authorization", token).when().get(URL).then().extract().response();
		assertTrue(response.getStatusCode() == 403);
		assertTrue(response.contentType().equals("application/json"));
		given().header("Authorization", token).when().get(URL).then().assertThat().body("batchName",
				Matchers.hasSize(service.getAllBatches().size()));
	}
}