package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
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
	TfAssociate associate;

	static final String URL = "http://localhost:8085/TrackForce/associates";

	@BeforeClass
	public void beforeClass() {
		token = JWTService.createToken("TestAdmin", 1);
		System.out.println(token);
		associates = new ArrayList<>();
		associates = associateService.getAllAssociates();
		
		associate = new TfAssociate();
		associate.setId(910);
		associate.setFirstName("Tom");
		associate.setLastName("Jerry");
		associate.setClientStartDate(new Timestamp(50000000L));
	}

	/**
	 * Test get all associates to make sure a valid token returns the correct status
	 * code. Ensure the correct content type, check the data from the path matches
	 * what is expected, check that a bad token gives a 401, and that a bad url
	 * gives a 404
	 */
	@Test(priority = 5, enabled = true)
	public void testGetAllAssociates() {
		Response response = given().header("Authorization", token).when().get(URL + "/allAssociates").then().extract()
				.response();

		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));

		given().header("Authorization", token).when().get(URL + "/allAssociates").then().assertThat().body("id",
				hasSize(associates.size()));

		given().header("Authorization", "Bad Token").when().get(URL + "/allAssociates").then().assertThat()
				.statusCode(401);

		given().header("Authorization", token).when().get(URL + "/notAURL").then().assertThat().statusCode(404);
	}

	/**
	 * Test get an associate to make sure a valid token returns the correct status
	 * code. Ensure the correct content type, check the data from the path matches
	 * what is expected, check that a bad token gives a 401, that a bad url gives a
	 * 404, and a bad userId gives a 204. Check that a field not specified by the
	 * JSON data returns null
	 */
	@Test(priority = 10, enabled = true)
	public void testGetAssociate() {
		Response response = given().header("Authorization", token).when().get(URL + "/" + 900).then().extract()
				.response();

		System.out.println(response.getStatusCode());
		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));

		given().header("Authorization", token).when().get(URL + "/" + 900).then().assertThat().body("firstName",
				equalTo("Cameron"));

		given().header("Authorization", token).when().get(URL + "/" + 900).then().assertThat().body("marketingStatus",
				equalTo(null));

		given().header("Authorization", "Bad Token").when().get(URL + "/" + 900).then().assertThat().statusCode(401);

		given().header("Authorization", token).when().get(URL + "/0").then().assertThat().statusCode(204);

		given().header("Authorization", token).when().get(URL + "/badURL").then().assertThat().statusCode(404);

		given().header("Authorization", token).when().get(URL + "/" + 900).then().assertThat().body("address",
				equalTo(null));
	}
	
	/**
	 * Happy path testing for updateAssociate. This will check that the resource to update an
	 * associate can be accessed properly, that the resource will update the associate, and that
	 * the updated information is reflected by getting that particular associate.
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(priority = 40, enabled = true)
	public void testUpdateAssociates1() {
		given().header("Authorization", token).contentType("application/json").body(associate).when().put(URL + "/" + 910)
				.then().assertThat().statusCode(200);

		Response response = given().header("Authorization", token).when().get(URL + "/" + 910).then().extract()
				.response();

		assertTrue(response.statusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));

		assertTrue(response.asString().contains("Tom") && response.asString().contains("Jerry"));
	}

	/**
	 * Unhappy path testing for updateAssociate. Ensure that a bad verb returns a 405, a bad URL
	 * returns a 404, a nonexistent associate returns  204 (content not found), and that the content
	 * type matches what is expected.
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(priority = 45, enabled = true)
	public void testUpdateAssociates2() {
		given().header("Authorization", token).when().post(URL + "/" + 910).then().assertThat().statusCode(405);

		given().header("Authorization", token).when().put(URL + "/0").then().assertThat().statusCode(204);

		given().header("Authorization", token).when().get(URL + "/badURL").then().assertThat().statusCode(404);

		given().header("Authorization", token).when().get(URL + "/" + 910).then().assertThat().body("address",
				equalTo(null));
	}
}
