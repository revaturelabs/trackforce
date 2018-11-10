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
import com.revature.utils.EnvManager;

import io.restassured.response.Response;

/**
 * Rest Assured tests for ensuring that the trainer resource functions properly.
 * 
 * As of November 6, 2018 Trainer Resource maps two separate methods to
 * trainers/{number}: getTrainer and updateTrainer.
 * 
 * getTrainer - GET request, interprets {number} as a trainerId updateTrainer -
 * PUT request, interprets {number} as a userId
 * 
 * This _WORKS_ but is also horribly convoluted, should not be done and allows
 * for the unhappy paths for some methods to actually break other things.
 * 
 * I would (strongly) suggest fixing this in future iterations.
 * 
 * - Katelyn Barnes 1809
 * 
 * @author Jesse, Andy
 * @since 06.18.06.16
 */
public class TrainerResourceTest {

	static final String URL = EnvManager.TomTrackForce_URL + "trainers/";

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
		trainers = new ArrayList<>();
		trainers = trainerService.getAllTrainers();
		knownTrainerId = 0;
		knownUserId = 6;
		knownTrainerIdNoBatch = 5100; // a trainer id for a trainer with no batch
		trainer = new TfTrainer();
		trainer = trainerService.getTrainer(0);
		assertNotNull(trainer);
		trainer.setFirstName("Ava - 2.0");
		
		nonexistantTrainerId = 1000;

	}
	/**
	 * Test that a trainer can be successfully retrieved, that a 200 is returns when
	 * there are no trainers
	 * 
	 * @author Jesse, Andy
	 * @since 06.18.06.18
	 */
	@Test(priority = 2)
<<<<<<< HEAD
	public void testGetTrainerHappyPath() {
		Response response = given().header("Authorization", token).when().get(URL + knownUserId + "/").then().extract()
				.response();

		assertEquals(response.getStatusCode(), 200);
		assertTrue(response.getBody().asString().contains("Ava - 2.0"));
	}

	/**
	 * Test that a trainer tests that if the trainer id is valid but there is no
	 * such trainer a 204 response is returned
	 * 
	 * @author Jesse, Andy
	 * @since 06.18.06.18
	 */
	@Test(priority = 2)
	public void testGetTrainerUnhappyPathTrainerDoesNotExist() {
		Response response = given().header("Authorization", token).when().get(URL + nonexistantTrainerId + "/").then()
				.extract().response();

		assertEquals(response.getStatusCode(), 204);
	}

	/**
	 * Unhappy path testing for getTrainer where a 401 is returned with a bad token
	 */
	@Test(priority = 3)
	public void testGetTrainerUnhappyPathBadToken() {
		Response response = given().header("Authorization", "Bad Token").when().get(URL + knownUserId + "/").then()
				.extract().response();

		assertEquals(response.statusCode(), 401);

		assertTrue(response.asString().contains("Unauthorized"));
	}

	/**
	 * Unhappy path testing for getTrainer where a 404 is returned with a bad URL
	 */
	@Test(priority = 3)
	public void testGetTrainerUnhappyPathBadUrl() {
		Response response = given().header("Authorization", token).when().get(URL + "notAURL/").then().extract()
				.response();

		assertEquals(404, response.statusCode());
	}

	/**
	 * Unhappy path testing where a status code of 405 is given if a post request is
	 * used
	 */
	@Test(priority = 3)
	public void testGetTrainerUnhappyPathBadVerbPost() {
		Response response = given().header("Authorization", token).contentType("application/json").when()
				.post(URL + knownUserId + "/").then().extract().response();

		assertEquals(response.statusCode(), 405);
	}

	/**
	 * Unhappy path testing where a status code of 405 is given if a put request is
	 * used
	 * 
	 * NOTE: Currently autofails as PUT to /{number} goes to update trainer and that
	 * has the potential to break things so we can't even think about running these
	 * tests yet
	 */
	@Test(priority = 3)
	public void testGetTrainerUnhappyPathBadVerbPut() {
		assertTrue(false);
		Response response = given().header("Authorization", token).contentType("application/json").when()
				.put(URL + knownUserId + "/").then().extract().response();

		assertEquals(response.statusCode(), 405);
	}

	/**
	 * Unhappy path testing where a status code of 405 is given if a delete request
	 * is used
	 */
	@Test(priority = 3)
	public void testGetTrainerUnhappyPathBadVerbDelete() {
		Response response = given().header("Authorization", token).contentType("application/json").when()
				.delete(URL + knownUserId + "/").then().extract().response();

		assertEquals(response.statusCode(), 405);
	}

	/**
	 * Test that a trainers primary batch can be successfully retrieved
	 * 
	 * @author Jesse, Andy
	 * @since 06.18.06.18
	 */
	@Test(priority = 4)
	public void testGetTrainerPrimaryBatchesHappyPath() {
		Response response = given().headers("Authorization", token).contentType("application/json").when()
<<<<<<< HEAD
				.post(URL + knownTrainerId + "/batch/").then().extract().response();

		assertEquals(response.getStatusCode(), 200);
		assertTrue(response.getBody().asString().contains("1712 Dec11 Java AP-USF"));

	}

	/**
	 * Test that if a trainer has no batch then a 204 is returned
	 * 
	 * @author Katelyn Barnes
	 */
	@Test(priority = 4)
	public void testGetTrainerPrimaryBatchesUnhappyPathNoBatch() {
		Response response = given().headers("Authorization", token).contentType("application/json").when()
				.post(URL + knownTrainerIdNoBatch + "/batch/").then().extract().response();

		assertEquals(response.getStatusCode(), 204);

	}

	/**
	 * Unhappy path testing for getTrainerPrimaryBatches where that a 401 is
	 * returned with a bad token, that a 404 is returned with a bad URL, a 405 is
	 * returned with a bad verb and a 415 is returned if JSON is not specified.
	 */
	@Test(priority = 5)
	public void testGetTrainerPrimaryBatchesUnhappyPath() {
		Response response = given().header("Authorization", "Bad Token").contentType("application/json").when()
				.post(URL + knownTrainerId + "/batch" + "/").then().extract().response();

		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("Unauthorized"));

		given().header("Authorization", token).contentType("application/json").when()
				.post(URL + knownTrainerId + "/batchBAD/").then().assertThat().statusCode(404);

		given().header("Authorization", token).contentType("application/json").when()
				.put(URL + knownTrainerId + "/batch/").then().assertThat().statusCode(405);

		given().header("Authorization", token).when().post(URL + knownTrainerId + "/batch" + "/").then().assertThat()
				.statusCode(415);
	}

	/**
	 * Test that a containers primary batch can be successfully retrieved, that a
<<<<<<< HEAD
	 * 204 is returned when that trainer has no batch
	 * 
	 * Currently fails due to a Lazy Initialization Exception caused by attempting
	 * to access batches in the getBatchFromCotrainer method in Trainer Resource. An
	 * unhappy path case should be made for this that checks when the trainer has no
	 * batches they are cotrainer for. Currently I cannot find a trainer for which
	 * that is true due to the same Lazy Initialization Exception that causes the
	 * failure of this test! - Katelyn Barnes Nov. 9, 2019
=======
	 * 204 is returned when that trainer has no batch,
>>>>>>> Staging
	 * 
	 * @author Jesse, Andy
	 * @since 06.18.06.18
	 */
	@Test(priority = 6)
	public void testGetTrainerCotrainerBatchHappyPath() {
		Response response = given().headers("Authorization", token).contentType("application/json").when()
				.post(URL + knownTrainerId + "/cotrainerbatch/").then().extract().response();
		assertEquals(response.getStatusCode(), 200);
	}

	/**
	 * Unhappy path testing for getTrainerCotrainerBatch where a 401 is returned
	 * with a bad token
	 */
	@Test(priority = 7)
	public void testGetTrainerCotrainerBatchUnhappyPathBadToken() {
		Response response = given().header("Authorization", "Bad Token").contentType("application/json").when()
				.post(URL + knownTrainerId + "/cotrainerbatch"  + "/").then().extract().response();

		assertEquals(response.statusCode(), 401);
		assertTrue(response.asString().contains("Unauthorized"));
	}

	/**
	 * Unhappy path testing for getTrainerCotrainerBatch where a 404 is returned
	 * with a bad URL
	 */
	@Test(priority = 7)
	public void testGetTrainerCotrainerBatchUnhappyPathBadUrl() {
		Response response = given().header("Authorization", token).contentType("application/json").when()
				.post(URL + knownTrainerId + "/cotrainerbatchBAD/").then().extract().response();

		assertEquals(response.statusCode(), 404);
	}

	/**
	 * Unhappy path testing for getTrainerCotrainerBatch where a 415 is returned if
	 * JSON is not specified.
	 */
	@Test(priority = 7)
	public void testGetTrainerCotrainerBatchUnhappyPathBadContentType() {
		Response response = given().header("Authorization", token).when().post(URL + knownTrainerId + "/batch/")
				.then().extract().response();

		assertEquals(response.statusCode(), 415);
	}

	/**
	 * Unhappy path testing for getTrainerCotrainerBatch where a 405 is returned
	 * with a bad verb, in this case PUT
	 */
	@Test(priority = 7)
	public void testGetTrainerCotrainerBatchUnhappyPathBadVerbPut() {
		Response response = given().header("Authorization", token).contentType("application/json").when()
				.put(URL + knownTrainerId + "/cotrainerbatch/").then().extract().response();

		assertEquals(response.statusCode(), 405);

	}

	/**
	 * Unhappy path testing for getTrainerCotrainerBatch where a 405 is returned
	 * with a bad verb, in this case GET
	 */
	@Test(priority = 7)
	public void testGetTrainerCotrainerBatchUnhappyPathBadVerbGet() {
		Response response = given().header("Authorization", token).contentType("application/json").when()
				.put(URL + knownTrainerId + "/cotrainerbatch/").then().extract().response();

		assertEquals(response.statusCode(), 405);

	}

	/**
	 * Unhappy path testing for getTrainerCotrainerBatch where a 405 is returned
	 * with a bad verb, in this case DELETE
	 */
	@Test(priority = 7)
	public void testGetTrainerCotrainerBatchUnhappyPathBadVerbDelete() {
		Response response = given().header("Authorization", token).contentType("application/json").when()
				.delete(URL + knownTrainerId + "/cotrainerbatch/").then().extract().response();

		assertEquals(response.statusCode(), 405);

	}

	/**
	 * Test that a trainer can be successfully updated
	 * 
	 * @author Jesse, Andy
	 * @since 06.18.06.18
	 */
	@Test(priority = 8)
	public void testUpdateTrainerHappyPath() {
		Response response = given().headers("Authorization", token).contentType("application/json").body(trainer).when()
				.put(URL + knownTrainerId + "/").then().extract().response();

		assertEquals(response.statusCode(), 202);
	}

	/**
	 * Tests that a 204 is returned when there is no trainer with that id to update
	 * 
	 * @author Katelyn Barnes
	 */
	@Test(priority = 8)
	public void testUpdateTrainerUnhappyPathNoTrainer() {
		Response response = given().headers("Authorization", token).contentType("application/json").body("").when()
				.put(URL + nonexistantTrainerId + "/").then().extract().response();

		assertEquals(response.statusCode(), 204);

	}

	/**
	 * Unhappy path testing for testUpdateTrainer where a 401 is returned with a bad
	 * token
	 */
	@Test(priority = 9)
	public void testUpdateTrainerUnhappyPathBadToken() {
		Response response = given().headers("Authorization", "Bad Token").contentType("application/json").body(trainer)
				.when().put(URL + knownTrainerId + "/").then().extract().response();

		assertEquals(response.statusCode(), 401);
		assertTrue(response.asString().contains("Unauthorized"));
	}

	/**
	 * Unhappy path testing for testUpdateTrainer where a 404 is returned with a bad
	 * URL
	 */
	@Test(priority = 9)
	public void testUpdateTrainerUnhappyPathBadUrl() {
		Response response = given().headers("Authorization", "Bad Token").contentType("application/json").body(trainer)
				.when().put(URL + knownTrainerId + "/BADURL/").then().extract().response();

		assertEquals(response.statusCode(), 404);

	}

	/**
	 * Unhappy path testing for testUpdateTrainer where a 405 is returned with a bad
	 * verb. In this case the verb is Post
	 */
	@Test(priority = 9)
	public void testUpdateTrainerUnhappyPathBadVerbPost() {
		Response response = given().header("Authorization", token).contentType("application/json").when()
				.post(URL + knownTrainerId + "/").then().extract().response();

		assertEquals(response.statusCode(), 405);
	}

	/**
	 * Unhappy path testing for testUpdateTrainer where a 405 is returned with a bad
	 * verb. In this case the verb is Get
	 * 
	 * Currently autofails because TrainerResource has multiple methods mapped to
	 * the same URL so performing a GET to /{number} doesn't even call
	 * updateTrainer.
	 */
	@Test(priority = 9)
	public void testUpdateTrainerUnhappyPathBadVerbGet() {
		assertTrue(false);
		Response response = given().header("Authorization", token).contentType("application/json").when()
				.get(URL + knownTrainerId + "/").then().extract().response();

		assertEquals(response.statusCode(), 405);
	}

	/**
	 * Unhappy path testing for testUpdateTrainer where a 405 is returned with a bad
	 * verb. In this case the verb is Delete
	 */
	@Test(priority = 9)
	public void testUpdateTrainerUnhappyPathBadVerbDelete() {
		Response response = given().header("Authorization", token).contentType("application/json").when()
				.delete(URL + knownTrainerId + "/").then().extract().response();

		assertEquals(response.statusCode(), 405);
	}

	/**
	 * Testing to ensure that all trainers can be successfully retrieved and that
	 * the list is what we would expect it to be.
	 */
	@Test(priority = 20)
	public void testGetAllTrainersHappyPath() {
		List<TfTrainer> trainers = trainerService.getAllTrainers();

		Response response = given().headers("Authorization", token).contentType("application/json").when()
				.get(URL + "allTrainers/").then().extract().response();

		assertEquals(response.statusCode(), 200);
		assertEquals(response.contentType(), "application/json");

		given().headers("Authorization", token).contentType("application/json").when().get(URL + "allTrainers/").then()
				.assertThat().body("id", hasSize(trainers.size()));
	}

	/**
	 * Test that attempting to Post to getAllTrainers returns a 405 status code
	 */
	@Test(priority = 25)
	public void testGetAllTrainersUnhappyPathBadVerbPost() {
		given().headers("Authorization", token).contentType("application/json").when().post(URL + "allTrainers/").then()
				.assertThat().statusCode(405);

	}
	
	/**
	 * Test that attempting to Put to getAllTrainers returns a 405 status code
	 */
	@Test(priority = 25)
	public void testGetAllTrainersUnhappyPathBadVerbPut() {
		given().headers("Authorization", token).contentType("application/json").when().put(URL + "allTrainers/").then()
				.assertThat().statusCode(405);
				
	}

	/**
	 * Test that attempting to Delete to getAllTrainers returns a 405 status code
	 */
	@Test(priority = 25)
	public void testGetAllTrainersUnhappyPathBadVerbDelete() {
		given().headers("Authorization", token).contentType("application/json").when().delete(URL + "/allTrainers").then()
				.assertThat().statusCode(405);

	}

	/**
	 * test that a bad token returns a 401
	 */
	@Test(priority = 25)
	public void testGetAllTrainersUnhappyPathBadToken() {
		given().headers("Authorization", "Bad Token").contentType("application/json").when().get(URL + "allTrainers/")
				.then().assertThat().statusCode(401);
	}

	/**
	 * test that a bad URL
	 * returns a 404
	 */
	@Test(priority = 25)
	public void testGetAllTrainersUnhappyPathBadUrl() {
		given().headers("Authorization", token).contentType("application/json").when().get(URL + "allTrainersss/")
				.then().assertThat().statusCode(404);

	}
}
