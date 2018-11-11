package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.entity.TfClient;
import com.revature.services.ClientService;
import com.revature.services.JWTService;
import com.revature.utils.EnvManager;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Rest Assured for ClientResource 1808_Seth_L This test needs to test the
 * following endpoints: /associates/get/{id}, /mapped/get, /50
 * 
 * @author Jesse, Andy
 * @since 06.18.06.16
 */
public class ClientResourceTest {

	static final String URL = EnvManager.TomTrackForce_URL + "clients/";

	ClientService cs = new ClientService();
	List<TfClient> clients;
	String token;

	/**
	 * Set up before any tests. Need to generate a token and generate a list of
	 * clients.
	 * 
	 * @throws IOException
	 */
	@BeforeClass
	public void beforeClass() throws IOException {
		token = JWTService.createToken("TestAdmin", 1);
		System.out.println(token);
		clients = new ArrayList<>();
		clients = cs.getAllTfClients();
	}

	/**
	 * Test that the resource can be accessed properly. Check that the content type
	 * is what is expected. 
	 * Test that a bad token gives a 401.
	 * Test that a bad url gives a 404.
	 * Test that a bad method gives a 405.
	 * 
	 * @author Jesse
	 * @since 6.18.06.13
	 */
	@Test(priority = 5)
	public void testGetAllClientsHappyPath() {
		Response response = given().header("Authorization", token).when().get(URL).then().extract().response();

		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));

		given().header("Authorization", token).when().get(URL).then().assertThat().body("name",
				hasSize(clients.size()));
	}

	/**
	 * Unhappy path testing for testGetAllClients, tests that a 401 status code
	 * is given if a request is made with a bad token
	 * @author Katelyn B 
	 * Written Nov. 4, 2018, Batch 1809
	 */
	@Test(priority = 10)
	public void testGetAllClientsBadToken() {
		Response response = given().header("Authorization", "Bad Token").when().get(URL).then().extract().response();

		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("Unauthorized"));

		given().header("Authorization", token).when().get(URL + "notAURL/").then().assertThat().statusCode(404);

		given().header("Authorization", token).when().post(URL).then().assertThat().statusCode(405);
	}
	/**
	 * Unhappy path testing for testGetAllClient, tests that a 404 status code is generated for a 
	 * bad URL
	 * @author Katelyn B 
	 * Written Nov. 4, 2018, Batch 1809
	 */
	@Test(priority = 10)
	public void testGetAllClientsBadUrl() {
		Response response = given().header("Authorization", token).when().get(URL + "notAURL/").then().extract().response();

		assertTrue(response.statusCode() == 404);
	}
	
	/**
	 * Unhappy path testing for testGetAllClient, tests that a 405 status code is given for a
	 * bad html verb
	 * @author Katelyn B 
	 * Written Nov. 4, 2018, Batch 1809
	 */
	@Test(priority = 10)
	public void testGetAllClientsBadVerb() {
		Response response = given().header("Authorization", token).when().post(URL).then().extract().response();

		assertTrue(response.statusCode() == 405);
	}
	
	/**
	 * An unhappy test method that checks every method but login to assure that
	 * those that need security must block the user if he: 
	 * 1. uses an unsupported verb.
	 * 2. uses a service without a token
	 * 3. uses a bad token. 
	 * 4. the role in the
	 * token deemed the user unauthorized to use the service As this method of
	 * unhappy testing is consistent, I (Seth L.) suggest using this and its
	 * dependent methods to perform unhappy tests for all Jersey resources. Note:
	 * All unhappy tests failed. The ClientResource needs improved security. Same
	 * goes for /batch/{id}
	 * 
	 * @author Seth L.
	 * @param method   - comma-separated list of HTTP Verbs that the service uses
	 * @param url      - the uri for the service
	 * @param needAuth - if the user has to be a higher level of authorization than
	 *                 Associate like Admin to use the service, this value is true;
	 */
	@Test(enabled = true, priority = 11, dataProvider = "urls")
	public void unhappyPathTest(String method, String url, Boolean needAuth) {
		String adminToken = token;
		String assocToken = JWTService.createToken("TestAssociate", 5);
		String[] verbs = method.split(", ");
		url = URL + url;
		// test no token
		for (String verb : verbs) {
			sendRequest(verb, url, null).then().assertThat().statusCode(401);
			// test invalid token
			sendRequest(verb, url, new Header("Authorization", "badtoken")).then().assertThat().statusCode(401);
			// test with associate token
			if (needAuth)
				sendRequest(verb, url, new Header("Authorization", assocToken)).then().assertThat().statusCode(403);
		}
		// look for an HTTP verb not used
		String knownVerbs[] = new String[] { "GET", "POST", "PUT", "DELETE" };
		for (String verb : knownVerbs) {
			if (!method.contains(verb)) {
				// test unsupported verb
				sendRequest(verb, url, new Header("Authorization", adminToken)).then().assertThat().statusCode(405);
				break;
			}
		}

	}

	/*
	 * This method performs the appropriate request based on the HTTP verb and
	 * returns the response
	 */
	private Response sendRequest(String method, String url, Header h) {
		RequestSpecification given = given().contentType("application/json");
		if (h != null)
			given.header(h);
		switch (method) {
		case "GET":
			return given.when().get(url);
		case "POST":
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
		return new Object[][] { { "GET", "", new Boolean(true) }, { "GET", "associates/get/0/", new Boolean(true) },
				{ "GET", "mapped/get/", new Boolean(true) }, { "GET", "50/", new Boolean(true) } };
	}
}
