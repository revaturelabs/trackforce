package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterClass;
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
 * As of November 6, 2018 Trainer Resource maps two separate methods to trainers/{number}:
 * getTrainer and updateTrainer.
 * 
 * getTrainer - GET request, interprets {number} as a trainerId
 * updateTrainer - PUT request, interprets {number} as a userId
 * 
 * This _WORKS_ but is also horribly
 * convoluted, should not be done and allows for the unhappy paths for some
 * methods to actually break other things.
 * 
 * I would (strongly) suggest fixing this in future iterations.
 * 
 *  - Katelyn Barnes 1809
 * 
 * @author Jesse, Andy
 * @since 06.18.06.16
 */
public class TrainerResourceTest {


	static final String URL = "http://52.87.205.55:8086/TrackForce/trainers";
//	static final String URL = "http://localhost:8085/TrackForce/trainers";
	
	TrainerService trainerService = new TrainerService();
	List<TfTrainer> trainers;
	String token;
	int knownTrainerId;
	int nonexistantTrainerId; // a trainer id that does not correspond to a trainer
	int knownTrainerIdNoBatch;
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
		knownTrainerId = 0;
		knownUserId = 1;
		knownTrainerIdNoBatch = 5100; // a trainer id for a trainer with no batch
		trainer = new TfTrainer();
		trainer = trainerService.getTrainer(0);
		assertNotNull(trainer);
		trainer.setFirstName("Ava - 2.0");
		
	}

	@AfterClass
	public void afterClass() {
		System.out.println(trainer.getTfUser());
	}
	/**
	 * Test that a trainer can be successfully retrieved, that a 204 is returns when
	 * there are no trainers
	 * @author Jesse, Andy
	 * @since 06.18.06.18
	 */
	@Test(priority = 2)
	public void testGetTrainerHappyPathNoTrainers() {
		Response response = given().header("Authorization", token).when().get(URL + "/" + knownUserId).then().extract()
				.response();

		assertEquals(response.getStatusCode(),204);
	}
	
	/**
	 * Test that a trainer tests that if the trainer id is valid but there is no
	 * such  trainer a 204 response is returned
	 * @author Jesse, Andy
	 * @since 06.18.06.18
	 */
	@Test(priority = 2)
	public void testGetTrainerUnhappyPathTrainerDoesNotExist() {
		Response response = given().header("Authorization", token).when().get(URL + "/" + nonexistantTrainerId).then().extract()
				.response();

		assertEquals(response.getStatusCode(),204);
	}
	
	/**
	 * Unhappy path testing for getTrainer where a 401 is returned with a bad token
	 */
	@Test(priority = 3)
	public void testGetTrainerUnhappyPathBadToken() {
		Response response = given().header("Authorization", "Bad Token").when().get(URL + "/" + knownUserId).then().extract().response();

		assertEquals(response.statusCode(),401);

		assertTrue(response.asString().contains("Unauthorized"));
	}
	/**
	 * Unhappy path testing for getTrainer where a 404 is
	 * returned with a bad URL
	 */
	@Test(priority = 3)
	public void testGetTrainerUnhappyPathBadUrl() {
		Response response = given().header("Authorization", token).when().get(URL + "/notAURL").then().extract().response();

		assertEquals(404, response.statusCode());
	}
	/**
	 * Unhappy path testing where a status code of 405 is given
	 * if a post request is used
	 */
	@Test(priority = 3)
	public void testGetTrainerUnhappyPathBadVerbPost() {
		Response response = given().header("Authorization", token).contentType("application/json").when().post(URL + "/" + knownUserId).then().extract().response();

		assertEquals(response.statusCode(), 405);
	}
	/**
	 * Unhappy path testing where a status code of 405 is given
	 * if a put request is used
	 * 
	 * NOTE: Currently autofails as PUT to /{number} goes to update
	 * trainer and that has the potential to break things so we 
	 * can't even think about running these tests yet
	 */
	@Test(priority = 3)
	public void testGetTrainerUnhappyPathBadVerbPut() {
		assertTrue(false);
		Response response = given().header("Authorization", token).contentType("application/json").when().put(URL + "/" + knownUserId).then().extract().response();

		assertEquals(response.statusCode(), 405);
	}
	
	/**
	 * Unhappy path testing where a status code of 405 is given
	 * if a delete request is used
	 */
	@Test(priority = 3)
	public void testGetTrainerUnhappyPathBadVerbDelete() {
		Response response = given().header("Authorization", token).contentType("application/json").when().delete(URL + "/" + knownUserId).then().extract().response();

		assertEquals(response.statusCode(), 405);
	}
	/**
	 * Test that a trainers primary batch can be successfully retrieved, that a 204 is returned when
	 * that trainer has no batch
	 * @author Jesse, Andy
	 * @since 06.18.06.18
	 */
	@Test(priority = 4)
	public void getTrainerPrimaryBatches() {
		Response response = given().headers("Authorization", token).contentType("application/json").when()
				.post(URL + "/" + knownTrainerId + "/batch").then().extract().response();

		assertTrue(response.getStatusCode() == 204 || response.getStatusCode() == 200);
		if (response.statusCode() == 200) {
			assertTrue(response.getBody().asString().contains("1712 Dec11 Java AP-USF"));
		}
	}
	
	/**
	 * Unhappy path testing for getTrainerPrimaryBatches where that a 401 is returned with a bad token, that a 404 is returned
	 * with a bad URL, a 405 is returned with a bad verb and a 415 is returned if JSON is not 
	 * specified.
	 */
	@Test(priority = 5)
	public void testGetTrainerPrimaryBatchesUnhappyPath() {
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
	 * Test that a containers primary batch can be successfully retrieved, that a 204 is returned when
	 * that trainer has no batch
	 * 
	 * Currently fails due to a Lazy Initialization Exception caused by attempting to access batches 
	 * in the getBatchFromCotrainer method in Trainer Resource
	 * - Katelyn Barnes Nov. 9, 2019
	 * 
	 * @author Jesse, Andy
	 * @since 06.18.06.18
	 */
	@Test(priority = 6)
	public void testGetTrainerCotrainerBatch() {
		Response response = given().headers("Authorization", token).contentType("application/json").when()
				.post(URL + "/" + knownTrainerId + "/cotrainerbatch").then().extract().response();
		assertTrue(response.getStatusCode() == 204 || response.getStatusCode() == 200);
	}
	
	/**
	 * Unhappy path testing for getTrainerCotrainerBatch where a 401 is returned with a bad token, that a 404 is returned
	 * with a bad URL, a 405 is returned with a bad verb and a 415 is returned if JSON is not 
	 * specified.
	 */
	@Test(priority = 7)
	public void testGetTrainerCotrainerBatchUnhappyPath() {
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
	 * there is no trainer
	 * @author Jesse, Andy
	 * @since 06.18.06.18
	 */
	@Test(priority = 8)
	public void testUpdateTrainer() {
		Response response = given().headers("Authorization", token).contentType("application/json").body(trainer).when()
				.put(URL + "/" + knownTrainerId).then().extract().response();
		
		assertTrue(response.statusCode() == 202);
		
		response = given().headers("Authorization", token).contentType("application/json").when()
		.get(URL + "/" + knownUserId).then().extract().response();
		
		response = given().headers("Authorization", token).contentType("application/json").body("").when()
				.put(URL + "/" + 1000).then().extract().response();
		
		assertTrue(response.statusCode() == 204);
	}
	
	/**
	 * Unhappy path testing for testUpdateTrainer where a 401 is returned with a bad token, that a 404 is returned
	 * with a bad URL, a 405 is returned with a bad verb.
	 */
	@Test(priority = 9)
	public void testUpdateTrainerUnhappyPath() {
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
	 * is what we would expect it to be. 
	 */
	@Test(priority = 20)
	public void testGetAllTrainers() {
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
	 * test that a bad token returns a 401, a bad
	 * verb returns a 405, and a bad URL returns a 404
	 */
	@Test(priority = 25)
	public void testGetAllTrainersUnhappyPath() {
		given().headers("Authorization", "Bad Token").contentType("application/json").when()
		.get(URL + "/allTrainers").then().assertThat().statusCode(401);
		
		given().headers("Authorization", token).contentType("application/json").when()
		.post(URL + "/allTrainers").then().assertThat().statusCode(405);
		
		given().headers("Authorization", token).contentType("application/json").when()
		.get(URL + "/allTrainersss").then().assertThat().statusCode(404);
		

	}
}
