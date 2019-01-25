package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;
import com.revature.entity.TfMarketingStatus;
import com.revature.entity.TfUser;
import com.revature.services.AssociateService;
import com.revature.services.JWTService;
import com.revature.services.UserService;

import io.restassured.response.Response;

/**
 * Rest Assured to ensure that this resource is functioning properly.
 * 
 * Cleaned up by Katelyn Barnes 1809
 * 
 * @author Jesse
 * @since 06.18.06.16
 */
public class AssociateResourceTest {

	static final String URL = "http://52.87.205.55:8086/TrackForce/associates";
	//static final String URL = "http://localhost:8085/TrackForce/associates";
	
	AssociateService associateService = new AssociateService();
	List<TfAssociate> associates;
	String token;
	TfAssociate associate;
	TfAssociate toBeChanged;

	// added these new knownUserIds, may want to update -Ian M
	int knownUserId1 = 147;
	int knownUserId2 = 790; // Username: Harvey
	int knownUserId3 = 695; // Username: Tabitha, Associate id: 685
	
	int knownAssociateId = 685;
	
	@BeforeClass
	public void beforeClass() {
		// create a new JWT
		token = JWTService.createToken("TestAdmin", 1);
		
		// create array of Associates
		associates = new ArrayList<>();
		associates = associateService.getAllAssociates();
		
		// create new TrackForce User 
		TfUser u = new TfUser();
		u.setId(4501);
		
		// set the marketing status
		TfMarketingStatus ms = new TfMarketingStatus();
		ms.setId(4);
		ms.setName("MAPPED: CONFIRMED");
		
		// configure the associate
		associate = new TfAssociate();
		associate.setFirstName("Tom");
		associate.setLastName("Jerry");
		associate.setClientStartDate(new Timestamp(150000000L));
		associate.setUser(u);
		associate.setMarketingStatus(ms);
		associate.setEndClient(new TfEndClient());
		associate.setBatch(new TfBatch());
		associate.setId(876);
		associate.setClient(new TfClient());
		
		toBeChanged = associateService.getAssociate(knownAssociateId);
		associate.setFirstName("Tom");
		associate.setLastName("Jerry");
		
		
	}

	@AfterClass
	public void afterClass() {
		TfAssociate changed = associateService.getAssociate(knownAssociateId);
		
		changed.setFirstName("Roberto");
		changed.setLastName("Alvarez,Jr.");
		
		associateService.updateAssociate(changed);
		
		
	}
	/**
	 * Test for the happy path of getAllAssociates, gives a valid token and a valid URL.
	 * Tests that the response has the correct status code, and that the list of associates
	 * returned matches the list we made. 
	 */
	@Test(priority = 5, enabled = true)
	public void testGetAllAssociatesHappyPath() {
		Response response = given().header("Authorization", token).when().get(URL + "/allAssociates").then().extract()
				.response();

		assertTrue(response.getStatusCode() == 200);
		assertTrue(response.contentType().equals("application/json"));

		Assert.assertEquals(response.body().jsonPath().getList("id").size(), associates.size());
	}

	/**
	 * Test for the unhappy path of getAllAssociates where the web token is bad but the URL is valid.
	 * Tests that the response has the correct status code
	 */
	@Test(priority = 7, enabled = true)
	public void testGetAllAssociatesBadToken() {
		Response response = given().header("Authorization", "Bad Token").when().get(URL + "/allAssociates").then()
				.extract().response();

		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("Unauthorized"));
	}
	
	/**
	 * Test for the unhappy path of getAllAssociates where the web token is good but the URL is invalid.
	 * Tests that the response has the correct status code
	 */
	@Test(priority = 7, enabled = true)
	public void testGetAllAssociatesBadUrl() {
		Response response = given().header("Authorization", token).when().get(URL + "/notAURL").then()
				.extract().response();
		
		assertTrue(response.statusCode() == 404);
	}

	/**
	 * Test get an associate to make sure a valid token returns the correct status
	 * code. Ensure the correct content type, check the data from the path matches
	 * what is expected.
	 */
	@Test(priority = 10, enabled = true)
	public void testGetAssociateHappyPath() {
		Response response = given().header("Authorization", token).when().get(URL + "/" + knownUserId1).then().extract()
				.response();

		assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204);
		if (response.statusCode() == 200) {
			assertTrue(response.contentType().equals("application/json"));
		}

		Assert.assertEquals(response.body().jsonPath().getString("batch.trainer.firstName"), "updateTrainer");

				
		assertTrue(response.asString().contains("\"id\":3"));
		assertTrue(response.asString().contains("\"name\":\"Revature LLC, 11730 Plaza America Drive, 2nd Floor | Reston, VA 20190\""));
	}

	/**
	 * Unhappy path testing for getAssociate where the token is bad. Checks that the correct error code is returned.
	 */
	@Test(priority = 15, enabled = true)
	public void testGetAssociateBadToken() {
		Response response = given().header("Authorization", "Bad Token").when().get(URL + "/" + knownUserId1).then().extract()
				.response();
		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("Unauthorized"));
	}
	
	/**
	 * Unhappy path testing for getAssociate where the URL is bad. Checks that the correct error code is returned.
	 */
	@Test(priority = 15, enabled = true)
	public void testGetAssociateBadUrl() {
		Response response = given().header("Authorization", token).when().get(URL + "/badURL").then().extract()
				.response();
		assertTrue(response.statusCode() == 404);
	}
	
	/**
	 * Unhappy path testing for getAssociate where the UserId is invalid. Checks that the correct
	 * error code is given.
	 */
	@Test(priority = 15, enabled = true)
	public void testGetAssociateBadUserId() {
		Response response = given().header("Authorization", token).when().get(URL + "/0").then().extract()
				.response();
		assertTrue(response.statusCode() == 204);
	}

	/**
	 * Happy path testing for updateAssociate. This will check that the resource to
	 * update an associate can be accessed properly, that the resource will update
	 * the associate, and that the updated information is reflected by getting that
	 * particular associate.
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	// currently returns a status code of 500 and thus does not pass - Katelyn Barnes 1809
	@Test(priority = 40, enabled = true)
	public void testUpdateAssociateHappyPath() {
		AssociateService service = new AssociateService();
		
		Response response = given().header("Authorization", token).contentType("application/json")
				.body(toBeChanged).when().put(URL + "/update/" + knownAssociateId).then().extract()
				.response();
		// This is all we can test as of 11.18 since updateAssociate currently returns a 
		// response with a status code and nothing else
		// 1809_Katelyn_B
		assertEquals(response.statusCode(), 200);

	}

	/**
	 * Unhappy path testing for updateAssociate. Ensure that a bad token returns a
	 * 401
	 * 
	 * @author Jesse
	 * @since 06.18.06.16
	 */
	@Test(priority = 45, enabled = true)
	public void testUpdateAssociateBadToken() {
		Response response = given().header("Authorization", "Bad Token").when().get(URL + "/update/" + knownUserId2).then().extract()
				.response();

		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("Unauthorized"));
	}
	/**
	 * Unhappy path testing for updateAssociate. Ensure that a bad URL returns a 404
	 */
	@Test(priority = 45, enabled = true)
	public void testUpdateAssociateBadUrl() {
		Response response = given().header("Authorization", token).when().get(URL + "/update/" + "badURL").then().extract().response();
		
		assertEquals(response.getStatusCode(), 404);
	}
	/**
	 * Unhappy path testing for updateAssociate. Ensures that a nonexistent associate returns 200 (because
	 * its a put request)
	 */
	@Test(priority = 45, enabled = true)
	public void testUpdateAssociateBadAssociate() {
		Response response = given().header("Authorization", token).when().post(URL + "/update/" + knownUserId2).then().extract()
				.response();
		
		assertEquals(response.getStatusCode(), 405);

	}
}
