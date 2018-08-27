package com.revature.test.restAssured;
import com.revature.entity.*;
import com.revature.resources.UserResource;
import com.revature.services.JWTService;
import com.revature.services.MarketingStatusService;
import com.revature.services.UserService;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Rest assured tests for the login resource. Note that many of these tests are
 * creating users so they are by default set to not run. Only run these tests if
 * you are in a testing environment or are comfortable adding additional entries
 * to your tables!
 * @author Jesse
 * @since 06.18.06.16
 */
public class UserResourceTest {

	//static final String URL = "http://52.87.205.55:8086/TrackForce/users";
	static final String URL = "http://localhost:8085/TrackForce/users";

	String token;
	TfUser user;
	TfUserAndCreatorRoleContainer container;
	TfAssociate associate;
	TfTrainer trainer;
	TfMarketingStatus ms;
	TfRole role;
	MarketingStatusService marketService = new MarketingStatusService();
	UserService userService = new UserService();
	UserResource userResource = new UserResource();
	int knownTrainerId;

	@BeforeClass
	public void beforeClass() {
		token = JWTService.createToken("TestAdmin", 1);
		System.out.println(token);
		knownTrainerId = 64832;
		
		ms = marketService.getMarketingStatusById(1);
		
		role = new TfRole();
		role = userService.getRole(1);
		TfRole conRole = new TfRole();
		conRole.setTfRoleId(1);
		
		user = new TfUser();
		user.setIsApproved(1);
		user.setPassword("password");
		user.setUsername("TestUsernameChris");
		user.setTfRole(role);
		user.setRole(1);
		
		container = new TfUserAndCreatorRoleContainer(user, 1);
		
		associate = new TfAssociate();
		associate.setFirstName("RestAssured");
		associate.setLastName("Associate");

		trainer = new TfTrainer();
		trainer.setFirstName("RestAssured");
		trainer.setLastName("Trainer");
	}

	/**
	 * 1806_Chris_P: The way that the following methods are currently setup, you can NOT use a REST call. 
	 * If you do, the password will become null due to the @JSONIGNORE annotation on the password in the TfUser class.
	 * The rest call works with the Angular since the user passed in that way is not technically a TfUser Java class
	 * and therefore does not have its password nulled. 
	 * 
	 * Happy path testing for create user. This should create an admin, staging
	 * manager or sales user when each of those specified roles is used.
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = true, priority = 10)
	public void testCreateUser2() {
		user.setRole(1);
		user.getTfRole().setTfRoleId(1);
		
		userResource.createUser(container);
		
		Response response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "associates") + "/allAssociates").then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
	}

	@Test(enabled = true, priority = 13)
	public void testCreateUser3() {
		user.setRole(3);
		user.getTfRole().setTfRoleId(3);
		
		userResource.createUser(container);
		
		Response response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "associates") + "/allAssociates").then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
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
	@Test(enabled = true, priority = 20)
	public void testCreateNewAssociate1() {
		user.setRole(5);
		user.setUsername("Associate1");
		associate.setUser(user);
		
		userResource.createNewAssociate(associate);

		Response response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "associates") + "/allAssociates").then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
		assertTrue(response.asString().contains(associate.getFirstName()));
	}

	/**
	 * Test that a user with a role other than 5 cannot be made into an associate
	 */
	@Test(enabled = true, priority = 23)
	public void testCreateNewAssociate2() {
		user.setRole(4);
		user.setUsername("Associate2");
		associate.setUser(user);
		associate.setFirstName("Carlsbad");
		given().contentType("application/json").body(associate).when().post(URL + "/newAssociate").then().assertThat()
				.statusCode(403);

		Response response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "associates") + "/allAssociates").then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));
		assertFalse(response.asString().contains(associate.getFirstName()));
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
	@Test(enabled = true, priority = 30)
	public void testCreateNewTrainer1() {
		user.setRole(2);
		user.getTfRole().setTfRoleId(2);
		trainer.setTfUser(user);
		
		userResource.createTrainer(trainer);

		Response response = given().header("Authorization", token).when()
				.get(URL.replaceAll("users", "trainers") + "/" + knownTrainerId).then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.asString().contains("RestAssured"));
	}

	/**
	 * Check that you cannot create a trainer with a non 2 role id
	 */
	@Test(enabled = true, priority = 32)
	public void testCreateNewTrainer2() {
		user.setRole(3);
		user.getTfRole().setTfRoleId(3);
		trainer.setTfUser(user);
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
	 * 1806_Chris_P: This used to test invalid credentials as well, but this conflicted 
	 * with the way the front end was handling responses, so that section got pulled out.
	 * 
	 * 
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