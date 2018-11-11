package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.contains;
import static org.testng.Assert.assertEquals;
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
	//static final String URL = "http://localhost:8085/TrackForce/skillset";

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
	 * is what is expected. 
	 * 
	 * @author Jesse
	 * @since 6.18.06.13
	 */
	
	/*
	 * This test checks to see if the right response and status is coming back when ran. 
	 * Currently in the 10th iteration this test is now passing. 
	 * 
	 */
	@Test(priority = 5)
	public void testGetAllCurriculums() {
		Response response = given().header("Authorization", tokenAdmin).when().get(URL).then().extract().response();
		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));

		given().header("Authorization", tokenAdmin).when().get(URL).then().assertThat().body("id", hasSize(9));

		given().header("Authorization", tokenAdmin).when().get(URL).then().assertThat().body("id", notNullValue());

		given().header("Authorization", tokenAdmin).when().get(URL).then().assertThat().body("name", notNullValue());

	}

	/**
	 * Unhappy path testing for getAllCurriculums.  Test that a bad token gives a 401. Test that a bad url
	 * gives a 404. Test that a bad method gives a 405.
	 */
	
	/*
	 * 
	 * This test currently passes. 
	 */
	@Test(priority = 10)
	public void testGetAllCurriculumsUnhappyPath() {
		Response response = given().header("Authorization", "Bad token").when().get(URL).then().extract().response();

		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("Unauthorized"));

		given().header("Authorization", tokenAdmin).when().get(URL + "/badurl").then().assertThat().statusCode(404);

		given().header("Authorization", tokenAdmin).when().post(URL).then().assertThat().statusCode(405);

		given().header("Authorization", tokenAssociate).when().get(URL).then().assertThat().statusCode(403);
	}
	
	/**
	 * Test to to ensure that the appropriate response is returns, the appropriate JSON is 
	 * returned, and that values are what we would expect them to be
	 */
	
	/*
	 * This test is currently not passing. There is an issue with what the ID is actually referencing... when tracing back to when
	 * it was initially implemented it is not clear as to what "id" actually refers to, whether its a specific batch, person, etc. 
	 * The ID is returning empty when going through the debugging process as well when implementing a syso statement with response. 
	 * Reason for failing the response is coming back "expected true, but found false." Best of luck to the next team with this test case.
	 * You may want to implement this line for debugging purposes:
	 * System.out.println("RESPONSE ========" + response.asString());  Leaving this line for the next iteration for debugging purposes. 
	 * 
	 * 
	 */
	@Test(priority = 15)
	public void testGetUnmappedInfo() {

		Response response = given().header("Authorization", tokenAdmin).when().get(URL + "/unmapped/2").then().extract()
				.response();



		assertEquals(response.statusCode(), 200);
		assertEquals(response.contentType(), "application/json");
		assertTrue(response.asString().contains("\"id\":2"));
		assertTrue(response.asString().contains("\"name\":\"Java\""));
		
		given().header("Authorization", tokenAdmin).when().get(URL + "/unmapped/2").then().assertThat().body("id", hasSize(1));
	}
	
	/**
	 * Unhappy path testing for getUnmappedInfo
	 * 
	 * Currently the test passes. If a token goes bad you get the appropriate response such as a 404, 401, 403, etc. 
	 */
	@Test(priority = 20)
	public void testGetUnmappedInfoUnhappyPath() {
		Response response = given().header("Authorization", "Bad Token").when().get(URL + "/unmapped/4").then().extract().response();
		
		System.out.println(response.statusCode());
		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("Unauthorized"));
		
		given().header("Authorization", tokenAdmin).when().get(URL + "/unmapped/4badurl").then().assertThat().statusCode(404);

		given().header("Authorization", tokenAdmin).when().post(URL + "/unmapped/4").then().assertThat().statusCode(405);

		given().header("Authorization", tokenAssociate).when().get(URL + "/unmapped/4").then().assertThat().statusCode(403);
	}
}
