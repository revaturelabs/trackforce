package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.entity.TfClient;
import com.revature.services.ClientService;
import com.revature.services.JWTService;

import io.restassured.response.Response;

public class ClientResourceTest {

	static final String URL = "http://52.87.205.55:8086/TrackForce/clients";

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
		clients = cs.getAllTfClients(); // Currently returns a size of 0.
	}

	/**
	 * Test that the resource can be accessed properly. Check that the content type
	 * is what is expected. Test that a bad token gives a 401. Test that a bad url
	 * gives a 404. Test that a bad method gives a 405.
	 * @author Jesse
	 * @since 6.18.06.13
	 */
	@Test(priority = 5)
	public void testGetAllClients() {
		Response response = given().header("Authorization", token).when().get(URL).then().extract().response();

		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));

		// given().header("Authorization",
		// token).when().get(URL).then().assertThat().body("tfClientName",
		// hasSize(clients.size()));

		given().header("Authorization", "Bad Token").when().get(URL).then().assertThat().statusCode(401);

		given().header("Authorization", token).when().get(URL + "/notAURL").then().assertThat().statusCode(404);

		given().header("Authorization", token).when().post(URL).then().assertThat().statusCode(405);
	}

	/**
	 * Test that the resource can be accessed properly. Check that the content type
	 * is what is expected. Test that a bad token gives a 401. Test that a bad url
	 * gives a 404. Test that a bad method gives a 405.
	 * @author Jesse
	 * @since 6.18.06.13
	 */
	@Test(priority = 10)
	public void testGetClientInfo() {
		Response response = given().header("Authorization", token).when().get(URL + "/" + 1).then().extract()
				.response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));

		given().header("Authorization", token).when().get(URL + "/" + 1).then().assertThat().body("tfClientName",
				equalTo("22nd Century Technologies"));
		
		given().header("Authorization", token).when().get(URL + "/" + 1).then().assertThat().body("stats.name",
				equalTo("22nd Century Technologies"));
		
		given().header("Authorization", token).when().get(URL + "/" + 1).then().assertThat().body("stats.trainingMapped",
				equalTo(1));

		given().header("Authorization", "Bad Token").when().get(URL).then().assertThat().statusCode(401);

		given().header("Authorization", token).when().get(URL + "/notAURL").then().assertThat().statusCode(404);

		given().header("Authorization", token).when().post(URL).then().assertThat().statusCode(405);
	}
}
