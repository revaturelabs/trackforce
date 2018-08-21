package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;
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


	//static final String URL = "http://52.87.205.55:8086/TrackForce/trainers";
	static final String URL = "http://localhost:8085/TrackForce/trainers";
	
	TrainerService trainerService = new TrainerService();
	List<TfTrainer> trainers;
	String token;
	int knownTrainerId;
	int knownUserId;
	TfTrainer trainer;
	TfTrainer trainerNull;
	TfUser user;

	@BeforeClass
	public void beforeClass() {
		token = JWTService.createToken("TestAdmin", 1);
		System.out.println(token);
		trainers = new ArrayList<>();
		trainers = trainerService.getAllTrainers();
		knownTrainerId = 24;
		knownUserId = 60302;
	}
	
	@Test(priority = 1)
	public void getTrainer() {
		trainer = new TfTrainer();
		trainer = trainerService.getTrainer(24);
		assertNotNull(trainer);
		trainer.setFirstName("Ava - 2.0");
	}

	/**
	 * Test that a trainer can be successfully retrieved, that a 204 is returns when
	 * there are no trainers, that a 401 is returns with a bad token, that a 404 is
	 * returned with a bad URL and that a 405 is returned when the wrong verb is used.
	 * @author Jesse, Andy
	 * @since 06.18.06.18
	 */
	@Test(priority = 2)
	public void testGetTrainer1() {
		Response response = given().header("Authorization", token).when().get(URL + "/" + knownUserId).then().extract()
				.response();

		assertTrue(response.getStatusCode() == 204 || response.getStatusCode() == 200);
		if(response.statusCode() == 200) {
			assertTrue(response.asString().contains("Ava"));
			assertTrue(response.asString().contains("Trains"));
		}
	}
	
	/**
	 * Unhappy path testing for getTrainer
	 */
	@Test(priority = 3)
	public void testGetTrainer2() {
		Response response = given().header("Authorization", "Bad Token").when().get(URL + "/" + knownUserId).then().extract().response();

		assertEquals(401, response.statusCode());

		assertTrue(response.asString().contains("Unauthorized"));

		given().header("Authorization", token).when().get(URL + "/notAURL").then().assertThat().statusCode(404);

		given().header("Authorization", token).contentType("application/json").when().post(URL + "/" + knownUserId).then()
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
	@Test(priority = 4)
	public void getTrainerPrimaryBatches1() {
		Response response = given().headers("Authorization", token).contentType("application/json").when()
				.post(URL + "/" + knownTrainerId + "/batch").then().extract().response();
		System.out.println(response.getStatusCode());
		assertTrue(response.getStatusCode() == 204 || response.getStatusCode() == 200);
		if (response.statusCode() == 200) {
			assertTrue(response.asString().contains("1701 Jan30 NET"));
			assertTrue(response.asString().contains("1702 Feb27 Java"));
		}
	}
	
	/**
	 * Unhappy path testing for getTrainerPrimaryBatches
	 */
	@Test(priority = 5)
	public void testGetTrainerPrimaryBatches2() {
		Response response = given().header("Authorization", "Bad Token").contentType("application/json").when()
				.post(URL + "/" + knownTrainerId + "/batch").then().extract().response();

		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("Unauthorized"));

		given().header("Authorization", token).contentType("application/json").when().post(URL + "/" + knownTrainerId + "/batchBAD")
				.then().assertThat().statusCode(404);

		given().header("Authorization", token).contentType("application/json").when().put(URL + "/" + knownTrainerId + "/batch")
				.then().assertThat().statusCode(405);

		given().header("Authorization", token).when().post(URL + "/" + knownTrainerId + "/batch").then().assertThat()
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
	@Test(priority = 6)
	public void testGetTrainerCotrainerBatch1() {
		Response response = given().headers("Authorization", token).contentType("application/json").when()
				.post(URL + "/" + knownTrainerId + "/cotrainerbatch").then().extract().response();
		assertTrue(response.getStatusCode() == 204 || response.getStatusCode() == 200);
	}
	
	/**
	 * Unhappy path testing for getTrainerCotrainerBatch
	 */
	@Test(priority = 7)
	public void testGetTrainerCotrainerBatch2() {
		Response response = given().header("Authorization", "Bad Token").contentType("application/json").when()
				.post(URL + "/" + knownTrainerId + "/cotrainerbatch").then().extract().response();

		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("Unauthorized"));

		given().header("Authorization", token).contentType("application/json").when()
				.post(URL + "/" + knownTrainerId + "/cotrainerbatchBAD").then().assertThat().statusCode(404);

		given().header("Authorization", token).contentType("application/json").when()
				.put(URL + "/" + knownTrainerId + "/cotrainerbatch").then().assertThat().statusCode(405);

		given().header("Authorization", token).when().post(URL + "/" + knownTrainerId + "/batch").then().assertThat()
				.statusCode(415);
	}
	
	/**
	 * Test that a trainer can be successfully updated, that a 204 is returned when
	 * there is no trainer, that a 401 is returned with a bad token, that a 404 is returned
	 * with a bad URL, a 405 is returned with a bad verb.
	 * @author Jesse, Andy
	 * @since 06.18.06.18
	 */
	@Test(priority = 8)
	public void testUpdateTrainer1() {
		Response response = given().headers("Authorization", token).contentType("application/json").body(trainer).when()
				.put(URL + "/" + knownTrainerId).then().extract().response();
		
		assertTrue(response.statusCode() == 202);
		
		response = given().headers("Authorization", token).contentType("application/json").when()
		.get(URL + "/" + knownUserId).then().extract().response();
		
		assertTrue(response.asString().contains("Ava - 2.0"));
		
		response = given().headers("Authorization", token).contentType("application/json").body("").when()
				.put(URL + "/" + 1000).then().extract().response();
		
		assertTrue(response.statusCode() == 204);
	}
	
	/**
	 * Unhappy path testing for testUpdateTrainer
	 */
	@Test(priority = 9)
	public void testUpdateTrainer2() {
		Response response = given().headers("Authorization", "Bad Token").contentType("application/json").body(trainer).when()
				.put(URL + "/" + knownTrainerId).then().extract().response();

		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("Unauthorized"));

		given().header("Authorization", token).contentType("application/json").when()
				.put(URL + "/" + knownTrainerId + "BADURL").then().assertThat().statusCode(404);

		given().header("Authorization", token).contentType("application/json").when()
				.post(URL + "/" + knownTrainerId).then().assertThat().statusCode(405);
	}
	
	/**
	 * Testing to ensure that all trainers can be successfully retrieved and that the list
	 * is what we would expect it to be. Also test that a bad token returns a 401, a bad
	 * verb returns a 405, and a bad URL returns a 404
	 */
	@Test(priority = 20)
	public void testGetAllTrainers1() {
		List<TfTrainer> trainers = trainerService.getAllTrainers();
		
		Response response = given().headers("Authorization", token).contentType("application/json").when()
				.get(URL + "/allTrainers").then().extract().response();
		
		System.out.println(response.statusCode());
		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
	
		given().headers("Authorization", token).contentType("application/json").when()
		.get(URL + "/allTrainers").then().assertThat().body("id", hasSize(trainers.size()));
	}
	
	/**
	 * Unhappy path testing for getAllTrainers
	 */
	@Test(priority = 25)
	public void testGetAllTrainers2() {
		given().headers("Authorization", "Bad Token").contentType("application/json").when()
		.get(URL + "/allTrainers").then().assertThat().statusCode(401);
		
		given().headers("Authorization", token).contentType("application/json").when()
		.post(URL + "/allTrainers").then().assertThat().statusCode(405);
		
		given().headers("Authorization", token).contentType("application/json").when()
		.get(URL + "/allTrainersss").then().assertThat().statusCode(404);
	}
}
