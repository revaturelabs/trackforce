package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.services.AssociateService;
import com.revature.services.JWTService;

import io.restassured.response.Response;

public class AssociateResourceTest {

	AssociateService associateService = new AssociateService();
	List<TfAssociate> associates;
	String token;

	static final String URL = "http://52.87.205.55:8086/TrackForce/associates";

	@BeforeClass
	public void beforeClass() {
		token = JWTService.createToken("TestAdmin", 1);
		System.out.println(token);
		// associates = associateService.getAllAssociates();
	}

	/**
	 * Test get all associates to make sure a valid token returns the correct status
	 * code. Ensure the correct content type, check the data from the path matches
	 * what is expected, check that a bad token gives a 401, and that a bad url
	 * gives a 404
	 */
	@Test(priority = 5)
	public void testGetAllAssociates() {
		Response response = given().header("Authorization", token).when().get(URL).then().extract().response();

		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));

		// given().header("Authorization",
		// token).when().get(URL).then().assertThat().body("firstName",
		// hasSize(associates.size()));

		given().header("Authorization", "Bad Token").when().get(URL).then().assertThat().statusCode(401);

		given().header("Authorization", "Bad Token").when().get(URL + "/notAURL").then().assertThat().statusCode(404);
	}

	/**
	 * Test get an associate to make sure a valid token returns the correct status
	 * code. Ensure the correct content type, check the data from the path matches
	 * what is expected, check that a bad token gives a 401, that a bad url gives a
	 * 404, and a bad userId gives a 204. Check that a field not specified by the
	 * JSON data returns null
	 */
	@Test(priority = 10)
	public void testGetAssociate() {
		Response response = given().header("Authorization", token).when().get(URL + "/" + 1).then().extract()
				.response();

		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));

		given().header("Authorization", token).when().get(URL + "/" + 1).then().assertThat().body("firstName",
				equalTo("Steven"));

		given().header("Authorization", token).when().get(URL + "/" + 1).then().assertThat().body("marketingStatus",
				equalTo("TERMINATED"));

		given().header("Authorization", "Bad Token").when().get(URL + "/" + 1).then().assertThat().statusCode(401);

		given().header("Authorization", token).when().get(URL + "/0").then().assertThat().statusCode(204);

		given().header("Authorization", token).when().get(URL + "/badURL").then().assertThat().statusCode(404);

		given().header("Authorization", token).when().get(URL + "/" + 1).then().assertThat().body("address",
				equalTo(null));
	}

	// /**
	// * Method to test that a valid response is issued when the correct path is
	// * supplied. Additionally, should only return a 200 when the media type is
	// valid
	// * for updating
	// */
	// @Test(priority = 20)
	// public void testUpdateAssociateMarketingStatus() {
	// for (TfAssociate ai : associates) {
	// Response response = given().header("Authorization", token).when()
	// .put(URL + "/" + ai.getTfAssociateId() +
	// "/marketing").then().extract().response();
	//
	// assertTrue(response.statusCode() == 200);
	//
	// Response response2 = given().header("Authorization",
	// token).contentType("text/plain").when()
	// .put(URL + "/" + ai.getTfAssociateId() +
	// "/marketing").then().extract().response();
	//
	// assertTrue(response2.statusCode() == 415);
	// break;
	// }
	// }

	// /**
	// * Method to test that the create user functionality works, that the new user
	// * can be retrieved and the data values are what we would expect. Additionally
	// * test that a token is needed for a new user to be created Commented out to
	// * prevent too many dummy users
	// */
	// @Test(priority = 25, enabled = false)
	// public void testCreateNewAssociate() {
	// Response response = given().header("Authorization",
	// token).contentType("application/json").body(
	// "{\"fname\": \"Testing\", \"password\":\"password\",
	// \"username\":\"username\", \"lname\":\"FourthUser\"}")
	// .when().post(URL).then().extract().response();
	//
	// assertTrue(response.statusCode() == 200);
	//
	// given().header("Authorization",
	// token).when().get(URL).then().assertThat().body("firstName",
	// hasSize(associates.size() + 1));
	//
	// given().header("Authorization", token).when().get(URL + "/" +
	// (associates.size() + 1)).then().assertThat()
	// .body("firstName", equalTo("Testing")).and().body("lastName",
	// equalTo("FourthUser"));
	//
	// Response response2 = given().header("Authorization", "Bad
	// Token").contentType("application/json").body(
	// "{\"fname\": \"Testing\", \"password\":\"password\",
	// \"username\":\"username\", \"lname\":\"FourthUser\"}")
	// .when().post(URL).then().extract().response();
	//
	// assertTrue(response.statusCode() == 401);
	// }
	
	@Test(priority = 40)
	public void testUpdateAssociates() {
//		 given().header("Authorization", token).contentType("application/json").body(
//		 "{\"mkStatus\": \"12\", \"id\":\"910\",\"startDateUnixTime\":\"1528156800006\", \"clientId\":\"5\"}")
//		 .when().put(URL + "/" + ai.getId()).then().assertThat().statusCode(200);
//
//		 System.out.println(response.statusCode());	
//		 assertTrue(response.statusCode() == 200);
//		 assertTrue(response.contentType().equals("application/json"));
		 }
}
