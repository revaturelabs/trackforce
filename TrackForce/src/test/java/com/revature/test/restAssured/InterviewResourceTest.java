package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.sql.Timestamp;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;
import com.revature.entity.TfInterview;
import com.revature.entity.TfInterviewType;
import com.revature.services.JWTService;

import io.restassured.response.Response;

/**
 * Rest Assured to ensure that this resource is functioning as intended.
 * @author Jesse
 * @since 06.18.06.16
 */
public class InterviewResourceTest {

	static final String URL = "http://52.87.205.55:8086/TrackForce/";

	String token;
	TfInterview interview;

	/**
	 * Set up before any tests. Need to generate a token
	 * 
	 * @throws IOException
	 */
	@BeforeClass
	public void beforeClass() throws IOException {
		interview = new TfInterview();
		token = JWTService.createToken("TestAdmin", 1);
		System.out.println(token);
	}

	/**
	 * Test that the resource can be accessed properly. Check that the content type
	 * is what is expected. Test that a bad token gives a 401. Test that a bad url
	 * gives a 404. Test that a bad method gives a 405.
	 * 
	 * @param ifc
	 *            - an interviewFromClient object returned from the data provider
	 * @author Jesse
	 * @since 6.18.06.13
	 */
	@Test(priority = 5, dataProvider = "interview", enabled = false)
	public void testCreateInterview(TfInterview interview) {

		Response response = given().header("Authorization", token).contentType("application/json").body(interview)
				.when().post(URL).then().extract().response();

		assertTrue(response.statusCode() == 201);

		response = given().header("Authorization", "Bad Token").contentType("application/json").body(interview).when()
				.post(URL).then().extract().response();

		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("401 – Unauthorized"));

		given().header("Authorization", token).contentType("application/json").body(interview).when()
				.put(URL + "/badurl").then().assertThat().statusCode(405);

		given().header("Authorization", token).contentType("application/json").body(interview).when().put(URL).then()
				.assertThat().statusCode(405);
	}

	/**
	 * Tests that you can get an associate interview by passing in the number of the
	 * associateId. Currently does not work and will respond with a 500 null pointer
	 * exception
	 * 
	 * @author Jesse
	 * @since 6.18.06.13
	 */
	@Test(priority = 10)
	public void testGetAllInterviews() {
		Response response = given().header("Authorization", token).when().get(URL + 1).then().extract().response();

		System.out.println(response.statusCode());
		assertTrue(response.statusCode() == 200);
	}

	/**
	 * Tests that you can get an associate interview by passing in the number of the
	 * interviewId. Currently does not work and will respond with a 500 null pointer
	 * exception
	 * 
	 * @author Jesse
	 * @since 6.18.06.13
	 */
	@Test(priority = 15)
	public void testGetAssociateInterview() {
		Response response = given().header("Authorization", token).when().get(URL + 3).then().extract().response();

		System.out.println(response.statusCode());
		assertTrue(response.statusCode() == 200);
	}

	/**
	 * This tests that the update request was properly processed and should return a
	 * 202. Also checks for a bad verb, a bad token and a bad url.
	 * 
	 * @param ifc
	 *            - the updated interviewFromClient to pass in for updating values
	 * @author Jesse
	 * @since 6.18.06.13
	 */
	@Test(priority = 20, dataProvider = "interview", enabled = true)
	public void testUpdateInterview(TfInterview interview) {
		Response response = given().header("Authorization", token).contentType("application/json").body(interview)
				.when().put(URL + 3).then().extract().response();

		assertTrue(response.statusCode() == 202);
		System.out.println(response.asString());

		response = given().header("Authorization", "Bad Token").contentType("application/json").body(interview).when()
				.put(URL + 3).then().extract().response();

		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("401 – Unauthorized"));

		given().header("Authorization", token).contentType("application/json").body(interview).when().put(URL + "three")
				.then().assertThat().statusCode(404);

		given().header("Authorization", token).contentType("application/json").body(interview).when()
				.post(URL + "three").then().assertThat().statusCode(405);
	}

	/**
	 * Data provider build an interview and then pass that interview in where needed. This data
	 * provider could easily be replaced in the before method since it is only being used once,
	 * but this allows for ease of reuse for any future resource testing.
	 * @return the interview built by the provider
	 */
	@DataProvider(name = "interview")
	public Object[][] provideInterview() {
		interview.setId(3);
		interview.setAssociate(new TfAssociate());
		interview.setClient(new TfClient());
		interview.setEndClient(new TfEndClient());
		interview.setInterviewType(new TfInterviewType());
		interview.setInterviewDate(new Timestamp(152500500L));
		interview.setAssociateFeedback("Interviewed well");
		interview.setQuestionGiven("Start Date?");
		interview.setClientFeedback("Strong Java knowledge");
		interview.setJobDescription("SDET");
		interview.setDateSalesIssued(new Timestamp(152500500L));
		interview.setDateAssociateIssued(new Timestamp(152500500L));
		interview.setWas24HRNotice(0);
		interview.setIsInterviewFlagged(1);
		interview.setFlagReason("Alert");
		interview.setIsClientFeedbackVisible(1);

		Object[][] object = new Object[1][1];
		object[0][0] = interview;
		return object;
	}
}
