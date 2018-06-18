package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.services.JWTService;

import io.restassured.response.Response;

/**
 * Rest Assured testing to ensure that this resource is functioning properly.
 * 
 * @author Jesse
 * @since 06.18.06.16
 */
public class CurriculumResourceTest {

	static final String URL = "http://52.87.205.55:8086/TrackForce/skillset";

	String tokenAdmin;
	String tokenAssociate;

	/**
	 * Set up before any tests. Need to generate a token
	 * 
	 * @throws IOException
	 */
	@BeforeClass
	public void beforeClass() throws IOException {
		tokenAdmin = JWTService.createToken("TestAdmin", 1);
		System.out.println(tokenAdmin);
		tokenAssociate = JWTService.createToken("TestAssociate", 5);
	}

	/**
	 * Test that the resource can be accessed properly. Check that the content type
	 * is what is expected. Test that a bad token gives a 401. Test that a bad url
	 * gives a 404. Test that a bad method gives a 405.
	 * 
	 * @author Jesse
	 * @since 6.18.06.13
	 */
	@Test(priority = 5)
	public void testGetAllCurriculums1() {
		Response response = given().header("Authorization", tokenAdmin).when().get(URL).then().extract().response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));

		given().header("Authorization", tokenAdmin).when().get(URL).then().assertThat().body("id", hasSize(9));

		given().header("Authorization", tokenAdmin).when().get(URL).then().assertThat().body("id", notNullValue());

		given().header("Authorization", tokenAdmin).when().get(URL).then().assertThat().body("name", notNullValue());

	}

	@Test(priority = 10)
	public void testGetAllCurriculums2() {
		Response response = given().header("Authorization", "Bad token").when().get(URL).then().extract().response();

		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("Unauthorized"));

		given().header("Authorization", tokenAdmin).when().get(URL + "/badurl").then().assertThat().statusCode(404);

		given().header("Authorization", tokenAdmin).when().post(URL).then().assertThat().statusCode(405);

		given().header("Authorization", tokenAssociate).when().get(URL).then().assertThat().statusCode(403);
	}
}
