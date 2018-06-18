package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.entity.TfTrainer;
import com.revature.services.JWTService;
import com.revature.services.TrainerService;

import io.restassured.response.Response;

/**
 * Rest Assured tests for ensuring that the trainer resource functions properly.
 * 
 * @author Jesse, Andy
 * @since 06.18.06.16
 */
public class TrainerResourceTest {

	TrainerService TrainerService = new TrainerService();
	List<TfTrainer> trainers;
	String token;

	//static final String URL = "http://52.87.205.55:8086/TrackForce/trainers";
	static final String URL = "http://localhost:8085/TrackForce/trainers";

	@BeforeClass
	public void beforeClass() {
		token = JWTService.createToken("TestAdmin", 1);
		System.out.println(token);
		trainers = new ArrayList<>();
		trainers = TrainerService.getAllTrainers();
	}

	/**
	 * This method current does not exist
	 */
	// @Test(priority = 1)
	// public void testGetAllTrainers() {
	// Response response = given().header("Authorization",
	// token).when().get(URL).then().extract().response();
	//
	// System.out.println(response.statusCode());
	// assertTrue(response.getStatusCode() == 204 || response.getStatusCode() ==
	// 200);
	// assertTrue(response.getContentType().equals(MediaType.APPLICATION_JSON));
	//
	// given().header("Authorization",
	// token).when().get(URL).then().assertThat().body("id",
	// hasSize(trainers.size()));
	//
	// response = given().header("Authorization", "Bad
	// Token").when().get(URL).then().extract().response();
	//
	// assertTrue(response.statusCode() == 401);
	// assertTrue(response.asString().contains("401 – Unauthorized"));
	//
	// given().header("Authorization", token).when().get(URL +
	// "/notAURL").then().assertThat().statusCode(404);
	// }

	/**
	 * Test that a trainer can be successfully retrieved, that a 204 is returns when
	 * there are no trainers, that a 401 is returns with a bad token, that a 404 is
	 * returned with a bad URL and that a 405 is returned when the wrong verb is used.
	 * @author Jesse, Andy
	 * @since 06.18.06.18
	 */
	@Test(priority = 2)
	public void testGetTrainer() {
		Response response = given().header("Authorization", token).when().get(URL + "/" + 1).then().extract()
				.response();

		System.out.println(response.statusCode());
		assertTrue(response.getStatusCode() == 204 || response.getStatusCode() == 200);

		response = given().header("Authorization", "Bad Token").when().get(URL + "/" + 1).then().extract().response();

		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("Unauthorized"));

		given().header("Authorization", token).when().get(URL + "/notAURL").then().assertThat().statusCode(404);

		given().header("Authorization", token).contentType("application/json").when().post(URL + "/" + 1).then()
				.assertThat().statusCode(405);
	}

	/**
	 * Test that a trainers primary batch can be successfully retrieved, that a 204 is returned when
	 * that trainer has no batch, that a 401 is returned with a bad token, that a 404 is returned
	 * with a bad URL, a 405 is returned with a bad verb and a 415 is returned if JSON is not 
	 * specified.
	 * @author Jesse, Andy
	 * @since 06.18.06.18
	 */
	@Test(priority = 3)
	public void getTrainerPrimaryBatches() {
		Response response = given().headers("Authorization", token).contentType("application/json").when()
				.post(URL + "/" + 1 + "/batch").then().extract().response();
		System.out.println(response.statusCode());
		assertTrue(response.getStatusCode() == 204 || response.getStatusCode() == 200);

		System.out.println(response.statusCode());
		response = given().header("Authorization", "Bad Token").contentType("application/json").when()
				.post(URL + "/" + 1 + "/batch").then().extract().response();

		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("Unauthorized"));

		given().header("Authorization", token).contentType("application/json").when().post(URL + "/" + 1 + "/batchBAD")
				.then().assertThat().statusCode(404);

		given().header("Authorization", token).contentType("application/json").when().put(URL + "/" + 1 + "/batch")
				.then().assertThat().statusCode(405);

		given().header("Authorization", token).when().post(URL + "/" + 1 + "/batch").then().assertThat()
				.statusCode(415);
	}

	/**
	 * Test that a cotrainers primary batch can be successfully retrieved, that a 204 is returned when
	 * that trainer has no batch, that a 401 is returned with a bad token, that a 404 is returned
	 * with a bad URL, a 405 is returned with a bad verb and a 415 is returned if JSON is not 
	 * specified.
	 * @author Jesse, Andy
	 * @since 06.18.06.18
	 */
	@Test(priority = 4)
	public void getTrainerCotrainerBatch() {
		Response response = given().headers("Authorization", token).contentType("application/json").when()
				.post(URL + "/" + 1 + "/cotrainerbatch").then().extract().response();
		System.out.println(response.statusCode());
		assertTrue(response.getStatusCode() == 204 || response.getStatusCode() == 200);

		response = given().header("Authorization", "Bad Token").contentType("application/json").when()
				.post(URL + "/" + 1 + "/cotrainerbatch").then().extract().response();

		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("Unauthorized"));

		given().header("Authorization", token).contentType("application/json").when()
				.post(URL + "/" + 1 + "/cotrainerbatchBAD").then().assertThat().statusCode(404);

		given().header("Authorization", token).contentType("application/json").when()
				.put(URL + "/" + 1 + "/cotrainerbatch").then().assertThat().statusCode(405);

		given().header("Authorization", token).when().post(URL + "/" + 1 + "/batch").then().assertThat()
				.statusCode(415);
	}

}
