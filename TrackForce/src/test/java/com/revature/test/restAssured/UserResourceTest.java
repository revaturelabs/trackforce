package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfMarketingStatus;
import com.revature.entity.TfRole;
import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;
import com.revature.entity.TfUserAndCreatorRoleContainer;
import com.revature.resources.UserResource;
import com.revature.services.JWTService;
import com.revature.services.MarketingStatusService;
import com.revature.services.UserService;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Rest assured tests for the login resource. Note that many of these tests are
 * creating users so they are by default set to not run. Only run these tests if
 * you are in a testing environment or are comfortable adding additional entries
 * to your tables!
 * 
 * @author Jesse
 * @since 06.18.06.16
 */
/* Note by Seth L.: Every unhappy path test should have these test scenarios:
 *   1. Using an unsupported method
 *   2. Not using a token
 *   3. Using a bad token
 *   4. Using an unauthorized token (i.e. associate token)
 *   5. Bad data
 */
public class UserResourceTest {

	//static final String URL = "http://52.87.205.55:8086/TrackForce/users";
	static final String URL = "http://localhost:8085/TrackForce/users";

	TfUser user;
	TfUserAndCreatorRoleContainer container;
	TfRole role;
	MarketingStatusService marketService = new MarketingStatusService();
	UserService userService = new UserService();
	UserResource userResource = new UserResource();
	int knownTrainerId;
	Header adminTokenHeader, assocTokenHeader, badTokenHeader;
	String username, password;
	@BeforeClass
	public void beforeClass() {
		username = "TestUsernameChris";
		password = "password";
		adminTokenHeader = new Header("Authorization", JWTService.createToken("TestAdmin", 1));
		badTokenHeader = new Header("Authorization", "badtoken");
		assocTokenHeader = new Header("Authorization", JWTService.createToken("cyril", 5));
		knownTrainerId = 64832;
		
		role = new TfRole();
		role = userService.getRole(1);
		
		user = new TfUser();
		user.setIsApproved(1);
		user.setPassword(password);
		user.setUsername(username);
		user.setTfRole(role);
		user.setRole(1);
		
		container = new TfUserAndCreatorRoleContainer(user, 1);
		

	}

	/**
	 * 1806_Chris_P: The way that the following methods are currently setup, you can NOT use a REST call. 
	 * If you do, the password will become null due to the @JSONIGNORE annotation on the password in the TfUser class.
	 * The rest call works with the Angular since the user passed in that way is not technically a TfUser Java class
	 * and therefore does not have its password nulled. 
	 * 
	 * 1808_Seth_L: Following the previous comment because of the @JSONIGNORE annotation, I have to manually create the JSON
	 * for the TFUser so I could pass the password directly.
	 * Unfortunately there is no way to delete the user once its created and there are some ways around this:
	 * 1. randomly generate a username string everytime.
	 * 		Pros: No time wasted reloading the database everytime a test needs to be made.
	 * 		Cons: Loads the database with dummy entries.  Not a problem with a test database but would be for a production one.
	 * 2. Reload the database before every test
	 * 		Pros: Fresh and "untouched" data reduces complications of running tests.
	 * 		Cons: time-consuming
	 * 3. Manually deleting the entry from the database.
	 * Happy path testing for create user. This should create an admin, staging
	 * manager or sales user when each of those specified roles is used.
	 * 
	 * @author Jesse and Seth L.
	 * @since 06.18.06.16
	 */
	@Test(enabled = true, priority = 10)
	public void testCreateUser1() {
		//manually constructed
		String jsonContainer = "{\"user\": { \"username\":\"" + container.getUser().getUsername() + "\", \"password\":\"" +
				container.getUser().getPassword() + "\", \"role\":"+ container.getUser().getRole() + "}, \"creatorRole\":" +
				container.getCreatorRole() + "}";
		given().header(adminTokenHeader).contentType("application/json").body(jsonContainer)
			.when().post(URL + "/newUser").then().assertThat().statusCode(201);
//		Response r = given().header(adminTokenHeader).contentType("application/json").body(jsonContainer).when().post(URL+"/newUser");
		TfUser newUser = userService.getUser(username);

		assertEquals(user.getUsername(), newUser.getUsername());
		//assertEquals(2, 1); //results in expected [1] but found [2]
		assertEquals(user.getTfRole().getTfRoleId(), newUser.getTfRole().getTfRoleId());
		assertEquals(user.getIsApproved(), newUser.getIsApproved());
		//should expect error if reentering the same data
		given().header(adminTokenHeader).contentType("application/json").body(container)
			.when().post(URL + "/newUser").then().assertThat().statusCode(417);
	}

//	@Test(enabled = true, priority = 13)
//	public void testCreateUser2() {
//		user.setRole(3);
//		user.getTfRole().setTfRoleId(3);
//		
//		userResource.createUser(container, adminToken);
//		
//		Response response = given().header("Authorization", adminToken).when()
//				.get(URL.replaceAll("users", "associates") + "/allAssociates").then().extract().response();
//
//		assertTrue(response.statusCode() == 200);
//		assertTrue(response.contentType().equals("application/json"));
//	}

	/**
	 * More unhappy path testing to ensure that verbs and URLs return the
	 * appropriate status code
	 * disabled because the unhappy Path Test tests this endpoint.
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = false, priority = 15)
	public void testCreateUserUnhappyPath() {
		
		//not using a token
		given().contentType("application/json").body(container).when().get(URL + "/newUser").then().assertThat()
				.statusCode(405);
		//using the wrong http method
		given().contentType("application/json").header(adminTokenHeader).body(container).when().post(URL + "/newUserBADURL").then().assertThat()
				.statusCode(404);
		//using an invalid token
		given().contentType("application/json").header(badTokenHeader).body(container).when().post(URL + "/newUser").then().assertThat()
				.statusCode(401);
		//using an unauthorized token
		given().contentType("application/json").header(assocTokenHeader).body(container).when().post(URL + "/newUser").then().assertThat()
				.statusCode(403);
	}
	/**
	 * An unhappy test method that checks every method but login to assure that those that need security must
	 * block the user if he:
	 * 1. uses an unsupported verb
	 * 2. uses a service without a token
	 * 3. uses a bad token
	 * 4. the role in the token deemed the user unauthorized to use the service
	 * @author Seth L.
	 * @param method - the HTTP Verb that the service requires
	 * @param url - the uri for the service
	 * @param needAuth - if the user has to be a higher level of authorization than Associate like Admin to use the service, this value is true;
	 */
	@Test(enabled = true, priority = 20, dataProvider = "urls")
	public void unhappyPathTest(String method, String url, Boolean needAuth) {
		url = URL + url;
		testNoToken(method, url);
		testBadVerb(method, url);
		testBadToken(method, url);
		if(needAuth)
			testUnauth(method, url);
	}
	private void testNoToken(String method, String url) {
		sendRequest(method,url, null).then().assertThat().statusCode(401);
	}
	private void testBadVerb(String method, String url) {
		sendRequest(method.equals("GET")?"POST":"GET", url, adminTokenHeader).then().assertThat().statusCode(405);
	}
	private void testBadToken(String method, String url) {
		sendRequest(method, url, badTokenHeader).then().assertThat().statusCode(401);
	}
	private void testUnauth(String method, String url) {
		sendRequest(method, url, assocTokenHeader).then().assertThat().statusCode(403);
	}
	/*
	 * This method performs the appropriate request based on the HTTP verb and returns the response
	 */
	private Response sendRequest(String method, String url, Header h) {
		RequestSpecification given = given().contentType("application/json").body(container);
		if(h != null)
			given.header(h);
		switch(method) {
		case "GET":
			return given.when().get(url);
		case "POST":
			System.out.println(url);
			return given.when().post(url);
		case "PUT":
			return given.when().put(url);
		case "DELETE":
			return given.when().delete(url);
		default:
			return given().when().get();
		}
	}
	@DataProvider(name = "urls") 
	public Object[][] getURLs() {
		return new Object[][] {{"POST", "/newUser", new Boolean(true)},
								{"GET", "/check", new Boolean(false)},
								{"GET", "/getUserRole", new Boolean(false)}};
	}
	/**
	 * 1806_Chris_P: This used to test invalid credentials as well, but this conflicted 
	 * with the way the front end was handling responses, so that section got pulled out.
	 * 
	 * 
	 * Test to ensure that a 200 is returned with valid credentials and a 401 UNAUTHORIZED is
	 * returned with invalid credentials. Also ensure that a valid username with an
	 * invalid password cannot log in.
	 * 
	 * @author Jesse and Seth L.
	 * @since 06.18.06.16
	 */
	@Test(enabled = true, priority = 40)
	public void testSubmitCredentials() {
		//positive test
		given().contentType("application/json").body("{ \"username\": \"TestAdmin\", \"password\": \"TestAdmin\"}")
				.post(URL + "/login").then().assertThat().statusCode(200);
		//wrong password should result in an unauthorized response
		given().contentType("application/json").body("{ \"username\": \"TestAdmin\", \"password\": \"badpassword\"}")
				.post(URL + "/login").then().assertThat().statusCode(401);
	}

	/**
	 * Unhappy path testing to ensure that verbs and URLs return the appropriate
	 * status code for the login
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(enabled = true, priority = 45)
	public void testSubmitCredentialsUnhappyPath() {
		//wrong http method
		given().contentType("application/json").body("{ \"username\": \"TestAdmin\", \"password\": \"TestAdmin\"}")
				.when().get(URL + "/login").then().assertThat().statusCode(405);
		//wrong password
		given().contentType("application/json").body("{ \"username\": \"TestAdmin\", \"password\": \"TestAdmin\"}")
				.when().post(URL + "/loginBad").then().assertThat().statusCode(404);
	}
	/**
	 * Simple test for the token validation method.  Assert that valid token results in an OK response and an invalid
	 * token results in an UNAUTHORIZED response.
	 * @author Seth Lemanek
	 */
	@Test(enabled = true, priority = 50)
	public void testTokenCheck() {
		//good token should pass
		given().header(assocTokenHeader).when().get(URL + "/check").then().assertThat().statusCode(200);
		//bad token should fail
		given().header(badTokenHeader).when().get(URL + "/check").then().assertThat().statusCode(401);
	}
	/**
	 * Verify that the getUserRole function returns the right role id and return 401 if the token is invalid
	 * @author Seth L.
	 */
	@Test(enabled = true, priority = 55)
	public void testRoleChecker() {
		Response adminTokenResponse = given().header(adminTokenHeader).when().get(URL + "/getUserRole").andReturn();
		assertEquals(adminTokenResponse.getStatusCode(), 200);
		assertEquals(adminTokenResponse.getBody().asString(), "1");
		System.out.println(adminTokenResponse.getHeader("Allow"));
		Response assocTokenResponse = given().header(assocTokenHeader).when().get(URL + "/getUserRole").andReturn();
		assertEquals(assocTokenResponse.getStatusCode(), 200);
		assertEquals(assocTokenResponse.getBody().asString(), "5");
		
		Response badTokenResponse = given().header(badTokenHeader).when().get(URL + "/getUserRole").andReturn();
		assertEquals(badTokenResponse.getStatusCode(), 401);
	}
}
