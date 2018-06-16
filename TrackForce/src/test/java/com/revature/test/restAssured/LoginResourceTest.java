package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;
import com.revature.services.JWTService;

import io.restassured.response.Response;

/**
 * Rest assured tests for the login resource
 * 
 * @author Jesse
 * @since 06.18.06.16
 */
public class LoginResourceTest {

	static final String URL = "http://52.87.205.55:8086/TrackForce/users";
	String token;
	TfUser user;
	TfAssociate associate;
	TfTrainer trainer;

	@BeforeClass
	public void beforeClass() {
		token = JWTService.createToken("TestAdmin", 1);
		System.out.println(token);
		user = new TfUser();
		user.setId(913);
		user.setIsApproved(1);
		user.setPassword("password");
		user.setRole(5);
		user.setUsername("A Very Unique Name");

		associate = new TfAssociate();
		associate.setId(915);
		associate.setFirstName("RestAssuredFirstNameTest");
		associate.setLastName("Jerry");

		trainer = new TfTrainer();
		trainer.setFirstName("RestAssuredTrainer");
		trainer.setLastName("Bob");
		trainer.setId(99);
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
	@Test(enabled = false)
	public void testCreateUser1() {
		given().contentType("application/json").body(user).when().post(URL + "/newUser").then().assertThat()
				.statusCode(403);

		Response response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "associates") + "/allAssociates").then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
		assertFalse(response.asString().contains("A Very Unique Name"));

		user.setRole(2);
		user.setUsername("Another Very Unique Name");

		given().contentType("application/json").body(user).when().post(URL + "/newUser").then().assertThat()
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
	@Test(enabled = false)
	public void testCreateUser2() {
		user.setRole(1);
		user.setUsername("AdminRestAssuredTest");

		given().contentType("application/json").body(user).when().post(URL + "/newUser").then().assertThat()
				.statusCode(201);

		Response response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "associates") + "/allAssociates").then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
		assertFalse(response.asString().contains("AdminRestAssuredTest"));

		user.setRole(1);
		user.setUsername("StagingManagerRestAssuredTest");

		given().contentType("application/json").body(user).when().post(URL + "/newUser").then().assertThat()
				.statusCode(201);

		response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "associates") + "/allAssociates").then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
		assertFalse(response.asString().contains("StagingManagerRestAssuredTest"));
	}

	/**
	 * More unhappy path testing to ensure that verbs and URLs return the
	 * appropriate status code
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = false)
	public void testCreateUser3() {
		given().contentType("application/json").body(user).when().get(URL + "/newUser").then().assertThat()
				.statusCode(405);

		given().contentType("application/json").body(user).when().post(URL + "/newUserBADURL").then().assertThat()
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
	@Test(enabled = false)
	public void testCreateNewAssociate1() {
		given().contentType("application/json").body(associate).when().post(URL + "/newAssociate").then().assertThat()
				.statusCode(201);

		Response response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "associates") + "/allAssociates").then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
		assertTrue(response.asString().contains("RestAssuredFirstNameTest"));

		user.setRole(4);
		associate.setUser(user);
		associate.setFirstName("RestAssuredFirstNameTestBAD");

		given().contentType("application/json").body(associate).when().post(URL + "/newAssociate").then().assertThat()
				.statusCode(201);

		response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "associates") + "/allAssociates").then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
		assertFalse(response.asString().contains("RestAssuredFirstNameTestBAD"));
	}

	/**
	 * v
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = false)
	public void testCreateNewAssociate2() {
		given().contentType("application/json").body(user).when().get(URL + "/newAssociate").then().assertThat()
				.statusCode(405);

		given().contentType("application/json").body(user).when().post(URL + "/newAssociateBADURL").then().assertThat()
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
	@Test(enabled = false)
	public void testCreateNewTrainer1() {
		given().contentType("application/json").body(trainer).when().post(URL + "/newTrainer").then().assertThat()
				.statusCode(201);

		Response response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "trainers") + "/99").then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
		assertTrue(response.asString().contains("RestAssuredTrainer"));
	}

	/**
	 * Unhappy path testing to ensure that verbs and URLs return the appropriate
	 * status code
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = false)
	public void testCreateNewTrainer2() {
		given().contentType("application/json").body(trainer).when().get(URL + "/newTrainer").then().assertThat()
				.statusCode(405);

		given().contentType("application/json").body(trainer).when().post(URL + "/newTrainerBAD").then().assertThat()
				.statusCode(404);
	}

	/**
	 * Test to ensure that a 200 is returned with valid credentials and a 403 is
	 * returned with invalid credentials.
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = true)
	public void testSubmitCredentials1() {
		given().contentType("application/json").body("{ \"username\": \"TestAdmin\", \"password\": \"TestAdmin\"}")
				.post(URL + "/login").then().assertThat().statusCode(200);

		given().contentType("application/json").body("{ \"username\": \"BadUsername\", \"password\": \"BadPassword\"}")
				.post(URL + "/login").then().assertThat().statusCode(403);
	}

	/**
	 * Unhappy path testing to ensure that verbs and URLs return the appropriate
	 * status code
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = true)
	public void testSubmitCredentials2() {
		given().contentType("application/json").body("{ \"username\": \"TestAdmin\", \"password\": \"TestAdmin\"}")
				.when().get(URL + "/login").then().assertThat().statusCode(405);

		given().contentType("application/json").body("{ \"username\": \"TestAdmin\", \"password\": \"TestAdmin\"}")
				.when().post(URL + "/loginBad").then().assertThat().statusCode(404);
	}
}