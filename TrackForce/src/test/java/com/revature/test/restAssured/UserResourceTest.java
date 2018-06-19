package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfMarketingStatus;
import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;
import com.revature.services.JWTService;
import com.revature.services.TrainerService;

import io.restassured.response.Response;

/**
 * Rest assured tests for the login resource. Note that many of these tests are
 * creating users so they are by default set to not run. Only run these tests if
 * you are in a testing environment or are comfortable adding additional entries
 * to your tables!
 * 
 * @author Jesse
 * @since 06.18.06.16
 */
public class UserResourceTest {

	// static final String URL = "http://52.87.205.55:8086/TrackForce/users";
	static final String URL = "http://localhost:8085/TrackForce/users";

	String token;
	TfUser user1;
	TfUser user2;
	TfUser user3;
	TfUser user4;
	TfAssociate associate1;
	TfAssociate associate2;
	TfTrainer trainer;
	TfMarketingStatus ms;

	@BeforeClass
	public void beforeClass() {
		token = JWTService.createToken("TestAdmin", 1);
		System.out.println(token);
		
		ms = new TfMarketingStatus();
//		ms.setId(1);
//		ms.setName("MAPPED: TRAINING");
		
		user1 = new TfUser();
		user1.setIsApproved(1);
		user1.setPassword("password");
		user1.setRole(5);
		user1.setUsername("A Very Unique Name");

		user2 = new TfUser();
		user2.setIsApproved(1);
		user2.setPassword("password");
		user2.setRole(5);
		user2.setUsername("Another Test");

		user3 = new TfUser();
		user3.setIsApproved(1);
		user3.setPassword("password");
		user3.setRole(2);
		user3.setUsername("Testing Trainer");
		
		user4 = new TfUser();
		user4.setIsApproved(1);
		user4.setPassword("password");
		user4.setRole(4);
		user4.setUsername("Testing Bad Trainer");
		
		associate1 = new TfAssociate();
		associate1.setFirstName("RestAssuredAssociate");
		associate1.setLastName("Jerry");


		associate2 = new TfAssociate();
		associate2.setFirstName("Unhappy");
		associate2.setLastName("Test");

		trainer = new TfTrainer();
		trainer.setFirstName("RestAssuredTrainer");
		trainer.setLastName("Bob");
	}

	/**
	 * Unhappy path testing for create user. Because an associate/trainer should be
	 * created with createAssociate or createTrainer, this method should not be used
	 * to create either. This test ensures a 403 is returned when either of those
	 * actions are attempted
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = true, priority = 5)
	public void testCreateUser1() {
		given().contentType("application/json").body(user1).when().post(URL + "/newUser").then().assertThat()
				.statusCode(403);

		Response response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "associates") + "/allAssociates").then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
		assertFalse(response.asString().contains("A Very Unique Name"));

		user1.setRole(2);
		user1.setUsername("Another Very Unique Name");

		given().contentType("application/json").body(user1).when().post(URL + "/newUser").then().assertThat()
				.statusCode(403);

		response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "associates") + "/allAssociates").then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
		assertFalse(response.asString().contains("Another Very Unique Name"));
	}

	/**
	 * Happy path testing for create user. This should create an admin, staging
	 * manager or sales user when each of those specified roles is used.
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = true, priority = 10)
	public void testCreateUser2() {
		user1.setRole(1);
		user1.setUsername("ADAM CAN YOU HEAR ME");

		given().contentType("application/json").body(user1).when().post(URL + "/newUser").then().assertThat()
				.statusCode(201);

		Response response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "associates") + "/allAssociates").then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
	}

	@Test(enabled = true, priority = 13)
	public void testCreateUser3() {
		user2.setRole(3);
		given().contentType("application/json").body(user2).when().post(URL + "/newUser").then().assertThat()
				.statusCode(201);

		Response response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "associates") + "/allAssociates").then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
		assertFalse(response.asString().contains(user2.getUsername()));
	}

	/**
	 * More unhappy path testing to ensure that verbs and URLs return the
	 * appropriate status code
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = true, priority = 15)
	public void testCreateUser4() {
		given().contentType("application/json").body(user1).when().get(URL + "/newUser").then().assertThat()
				.statusCode(405);

		given().contentType("application/json").body(user1).when().post(URL + "/newUserBADURL").then().assertThat()
				.statusCode(404);
	}

	/**
	 * Test that createNewAssociate works properly when a valid associate is
	 * supplied. Test that a new associate with a role id other than 5 (Not actually
	 * an associate) is not entered into the associates table.
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = true, priority = 20)
	public void testCreateNewAssociate1() {
		user1.setRole(5);
		associate1.setUser(user1);
		given().contentType("application/json").body(associate1).when().post(URL + "/newAssociate").then().assertThat()
				.statusCode(201);

		Response response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "associates") + "/allAssociates").then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
		assertTrue(response.asString().contains(associate1.getFirstName()));
	}

	/**
	 * Test that a user with a role other than 5 cannot be made into an associate
	 */
	@Test(enabled = true, priority = 23)
	public void testCreateNewAssociate2() {
		user2.setRole(4);
		associate2.setUser(user2);
		given().contentType("application/json").body(associate2).when().post(URL + "/newAssociate").then().assertThat()
				.statusCode(403);

		Response response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "associates") + "/allAssociates").then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
		assertFalse(response.asString().contains(associate2.getFirstName()));
	}

	/**
	 * Unhappy path testing to ensure that verbs and URLs return the appropriate
	 * status code
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = true, priority = 25)
	public void testCreateNewAssociate3() {
		given().contentType("application/json").body(user1).when().get(URL + "/newAssociate").then().assertThat()
				.statusCode(405);

		given().contentType("application/json").body(user1).when().post(URL + "/newAssociateBADURL").then().assertThat()
				.statusCode(404);
	}

	/**
	 * Test to ensure that a new trainer can be created successfully and that when
	 * that trainer is visible with the getTrainer method in the trainerResource
	 * class.
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = true, priority = 30)
	public void testCreateNewTrainer1() {
		trainer.setTfUser(user3);
		given().contentType("application/json").body(trainer).when().post(URL + "/newTrainer").then().assertThat()
				.statusCode(201);

		Response response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "trainers") + "/59").then().extract().response();

		System.out.println(response.statusCode());
		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
		assertTrue(response.asString().contains("RestAssuredTrainer"));
	}

	/**
	 * Check that you cannot create a trainer with a non 2 role id
	 */
	@Test(enabled = true, priority = 32)
	public void testCreateNewTrainer2() {
		trainer.setTfUser(user4);
		given().contentType("application/json").body(trainer).when().post(URL + "/newTrainer").then().assertThat()
				.statusCode(403);
		
		trainer.getTfUser().setRole(5);
		given().contentType("application/json").body(trainer).when().post(URL + "/newTrainer").then().assertThat()
		.statusCode(403);
	}
	/**
	 * Unhappy path testing to ensure that verbs and URLs return the appropriate
	 * status code
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = true, priority = 35)
	public void testCreateNewTrainer3() {
		given().contentType("application/json").body(trainer).when().get(URL + "/newTrainer").then().assertThat()
				.statusCode(405);

		given().contentType("application/json").body(trainer).when().post(URL + "/newTrainerBAD").then().assertThat()
				.statusCode(404);
	}

	/**
	 * Test to ensure that a 200 is returned with valid credentials and a 403 is
	 * returned with invalid credentials. Also ensure that a valid username with an
	 * invalid password cannot log in.
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = true, priority = 40)
	public void testSubmitCredentials1() {
		given().contentType("application/json").body("{ \"username\": \"TestAdmin\", \"password\": \"TestAdmin\"}")
				.post(URL + "/login").then().assertThat().statusCode(200);

		given().contentType("application/json").body("{ \"username\": \"BadUsername\", \"password\": \"BadPassword\"}")
				.post(URL + "/login").then().assertThat().statusCode(401);

		given().contentType("application/json").body("{ \"username\": \"TestAdmin\", \"password\": \"BadPassword\"}")
				.post(URL + "/login").then().assertThat().statusCode(401);
	}

	/**
	 * Unhappy path testing to ensure that verbs and URLs return the appropriate
	 * status code
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = true, priority = 45)
	public void testSubmitCredentials2() {
		given().contentType("application/json").body("{ \"username\": \"TestAdmin\", \"password\": \"TestAdmin\"}")
				.when().get(URL + "/login").then().assertThat().statusCode(405);

		given().contentType("application/json").body("{ \"username\": \"TestAdmin\", \"password\": \"TestAdmin\"}")
				.when().post(URL + "/loginBad").then().assertThat().statusCode(404);
	}
}