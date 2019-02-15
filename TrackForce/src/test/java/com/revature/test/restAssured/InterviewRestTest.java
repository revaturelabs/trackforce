package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
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
import com.revature.entity.TfUser;
import com.revature.services.AssociateService;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;

import io.restassured.response.Response;

/**
 * Rest Assured to ensure that this resource is functioning as intended.
 * 
 * 
 * @author Jesse
 * @since 06.18.06.16
 */
public class InterviewRestTest {

	static final String URL = "http://52.87.205.55:8086/TrackForce/interviews";
//	 static final String URL = "http://localhost:8085/TrackForce/interviews";

	String adminToken;
	String associateToken;
	String knownAssociateToken;
	TfInterview interview1;
	TfInterview interview2;
	InterviewService interviewService;
	AssociateService associateService;
	int knownAssociateId;

	/**
	 * Set up before any tests. Need to generate a token
	 * 
	 * @throws IOException
	 */
	@BeforeClass
	public void beforeClass() throws IOException {
		interviewService = new InterviewService();
		associateService = new AssociateService();
		interview1 = new TfInterview();
		interview2 = new TfInterview();

		adminToken = JWTService.createToken("TestAdmin", 1);
		associateToken = JWTService.createToken("AssociateTest", 5);

		knownAssociateId = 392;

		TfAssociate knownAssociate = associateService.getAssociate(392);
		TfUser knownUser = knownAssociate.getUser();

		knownAssociateToken = JWTService.createToken(knownUser.getUsername(), 5);

	}

	/**
	 * Test that the resource can be accessed properly. Check that the content type
	 * is what is expected. This should return a 201 for an admin
	 * 
	 * NOTE: As of Nov. 5, 2018 admins have interview creation power, as dictated by
	 * Ryan Lessley. Cleaned by Katelyn Barnes
	 * 
	 * @param ifc - an interviewFromClient object returned from the data provider
	 * @author Jesse
	 * @since 6.18.06.13
	 */
	@Test(priority = 5, dataProvider = "interview1", enabled = true)
	public void testCreateInterviewHappyPathAdmin(TfInterview interview) {
		Response response = given().header("Authorization", adminToken).contentType("application/json").body(interview)
				.when().post(URL + "/create/" + knownAssociateId).then().extract().response();
		assertEquals(response.statusCode(), 201);
	}

	/**
	 * Testing to ensure an associate can create interviews
	 * 
	 * @param interview
	 */
	@Test(priority = 6, dataProvider = "interview1", enabled = true)
	public void testCreateInterviewHappyPathAssoc(TfInterview interview) {
		Response response = given().header("Authorization", knownAssociateToken).contentType("application/json")
				.body(interview).when().post(URL + "/create/" + knownAssociateId).then().extract().response();
		assertEquals(response.statusCode(), 201);
	}

	/**
	 * Unhappy path testing for testCreateInterview. Test that a bad token gives a
	 * 401.
	 * 
	 */
	@Test(priority = 7, dataProvider = "interview1", enabled = true)
	public void testCreateInterviewBadToken(TfInterview interview) {
		Response response = given().header("Authorization", "Bad Token").contentType("application/json").body(interview)
				.when().post(URL + "/create/" + knownAssociateId).then().extract().response();

		assertEquals(response.statusCode(), 401);
		assertTrue(response.asString().contains("Unauthorized"));
	}

	/**
	 * Unhappy path testing for testCreateInterview. Test that a bad url gives a
	 * 404.
	 */
	@Test(priority = 7, dataProvider = "interview1", enabled = true)
	public void testCreateInterviewBadUrl(TfInterview interview) {
		Response response = given().header("Authorization", adminToken).contentType("application/json").body(interview)
				.when().post(URL + "/create/" + knownAssociateId + "BADURL").then().extract().response();

		assertEquals(response.statusCode(), 404);
	}

	/**
	 * Unhappy path testing for testCreateInterview. Test that a put request gives a
	 * 405.
	 * 
	 */
	@Test(priority = 7, dataProvider = "interview1", enabled = true)
	public void testCreateInterviewBadVerbPut(TfInterview interview) {
		Response response = given().header("Authorization", adminToken).contentType("application/json").body(interview)
				.when().put(URL + "/create/" + knownAssociateId).then().extract().response();
		assertEquals(response.statusCode(), 405);
	}

	/**
	 * Unhappy path testing for testCreateInterview. Test that a get request gives a
	 * 405.
	 * 
	 */
	@Test(priority = 7, dataProvider = "interview1", enabled = true)
	public void testCreateInterviewBadVerbGet(TfInterview interview) {
		Response response = given().header("Authorization", adminToken).contentType("application/json").body(interview)
				.when().get(URL + "/create/" + knownAssociateId).then().extract().response();

		assertEquals(response.statusCode(), 405);
	}

	/**
	 * Unhappy path testing for testCreateInterview. Test that a delete request
	 * gives a 405.
	 */
	@Test(priority = 7, dataProvider = "interview1", enabled = true)
	public void testCreateInterviewBadVerbDelete(TfInterview interview) {

		Response response = given().header("Authorization", adminToken).contentType("application/json").body(interview)
				.when().delete(URL + "/create/" + knownAssociateId).then().extract().response();
		assertEquals(response.statusCode(), 405);
	}

	/**
	 * Tests that you can get an associate interview by passing in the number of the
	 * associateId.
	 * 
	 * @author Jesse
	 * @since 6.18.06.13
	 */
	@Test(priority = 10)
	public void testGetAllInterviewsByAssociateIdHappyPathAdmin() {
		Response response = given().header("Authorization", adminToken).when().get(URL + "/associate/" + knownAssociateId).then()
				.extract().response();

		assertEquals(response.statusCode(), 200);

	}

	/**
	 * Tests that you can get an associate can get their own interviews.
	 * 
	 * @author Kateyln Barnes
	 */
	@Test(priority = 10)
	public void testGetAllInterviewsByAssociateIdHappyPathAssoc() {
		Response response = given().header("Authorization", knownAssociateToken).when()
				.get(URL + "/associate/" + knownAssociateId).then().extract().response();

		assertEquals(response.statusCode(), 200);

	}

	/**
	 * Tests that attempting to access an associate interview with a bad token gets
	 * a 401 status code
	 * 
	 * @author Katelyn Barnes
	 */
	@Test(priority = 15)
	public void testGetAssociateInterviewBadToken() {
		Response response = given().header("Authorization", "Bad Token").when().get(URL + "/associate/" + knownAssociateId).then()
				.extract().response();

		assertEquals(response.statusCode(), 401);
	}

	/**
	 * Test that attempting to access an associate interview with a bad url gets a
	 * 404 status code
	 * 
	 * @author Katelyn Barnes
	 */
	@Test(priority = 15)
	public void testGetAssociateInterviewBadUrl() {
		Response response = given().header("Authorization", adminToken).when().get(URL + "/associate/" + knownAssociateId + "BAD")
				.then().extract().response();

		assertEquals(response.statusCode(), 404);
	}

	/**
	 * Tests that attempting to use a post request gets a 405 response code
	 * 
	 */
	@Test(priority = 15)
	public void testGetAllInterviewsBadVerbPost() {
		Response response = given().header("Authorization", adminToken).when().post(URL + "/associate/" + knownAssociateId).then()
				.extract().response();

		assertEquals(response.statusCode(), 405);

	}

	/**
	 * Tests that attempting to use a put request gets a 405 response code
	 * 
	 */
	@Test(priority = 15)
	public void testGetAssociateInterviewBadVerbPut() {
		Response response = given().header("Authorization", adminToken).when().post(URL + "/associate/" + knownAssociateId).then()
				.extract().response();

		assertEquals(response.statusCode(), 405);

	}

	/**
	 * Tests that attempting to use a delete request gets a 405 response code
	 */
	@Test(priority = 15)
	public void testGetAllInterviewsBadVerbDelete() {

		Response response = given().header("Authorization", adminToken).when().delete(URL + "/associate/" + knownAssociateId).then()
				.extract().response();

		assertEquals(response.statusCode(), 405);

	}

	/**
	 * This tests that the update request was properly processed and should return a
	 * 202.
	 * 
	 * @param ifc - the updated interviewFromClient to pass in for updating values
	 * @author Jesse
	 * @since 6.18.06.13
	 */
	@Test(priority = 20, dataProvider = "interview2", enabled = true)
	public void testUpdateInterview(TfInterview interview) {
		Response response = given().header("Authorization", adminToken).contentType("application/json").body(interview)
				.when().put(URL + "/update/" + knownAssociateId).then().extract().response();

		assertEquals(response.statusCode(), 202);
		assertTrue(interview.getJobDescription().contains("MEME LORD"));

	}

	/**
	 * Unhappy path testing for testUpdateInterview. Checks that a bad token gets a
	 * 401 status code.
	 */
	@Test(priority = 25, dataProvider = "interview2", enabled = true)
	public void testUpdateInterviewBadToken(TfInterview interview) {
		Response response = given().header("Authorization", "Bad Token").contentType("application/json").body(interview)
				.when().put(URL + "/update/" + knownAssociateId).then().extract().response();

		assertEquals(response.statusCode(), 401);
		assertTrue(response.asString().contains("Unauthorized"));

	}

	/**
	 * Unhappy path testing for testUpdateInterview. Checks that a bad URL gets a
	 * 404 status code
	 *
	 */
	@Test(priority = 25, dataProvider = "interview2", enabled = true)
	public void testUpdateInterviewBadUrl(TfInterview interview) {
		Response response = given().header("Authorization", adminToken).contentType("application/json").body(interview)
				.when().put(URL + "/update/" + "three").then().extract().response();

		assertEquals(response.statusCode(), 404);
	}

	/**
	 * Unhappy path testing for testUpdateInterview. Checks that POST returns a 405
	 * error
	 * 
	 */
	@Test(priority = 25, dataProvider = "interview2", enabled = true)
	public void testUpdateInterviewBadVerbPost(TfInterview interview) {
		Response response = given().header("Authorization", adminToken).contentType("application/json").body(interview)
				.when().post(URL + "/update/" + knownAssociateId).then().extract().response();

		assertEquals(response.statusCode(), 405);
	}

	/**
	 * Unhappy path testing for testUpdateInterview. Tests that attempting to use a
	 * delete request gets a 405 response code
	 */
	@Test(priority = 25, dataProvider = "interview2", enabled = true)
	public void testUpdateInterviewBadVerbDelte(TfInterview interview) {
		Response response = given().header("Authorization", adminToken).contentType("application/json").body(interview)
				.when().post(URL + "/update/" + knownAssociateId).then().extract().response();

		assertEquals(response.statusCode(), 405);
	}

	/**
	 * Unhappy path testing for testUpdateInterview. Checks that GET returns a 405
	 * error
	 */
	@Test(priority = 25, dataProvider = "interview2", enabled = true)
	public void testUpdateInterviewBadVerbGet(TfInterview interview) {
		Response response = given().header("Authorization", adminToken).contentType("application/json").body(interview)
				.when().get(URL + "/update/" + knownAssociateId).then().extract().response();

		assertEquals(response.statusCode(), 405);
	}

	/**
	 * Data provider build an interview and then pass that interview in where
	 * needed. This data provider could easily be replaced in the before method
	 * since it is only being used once, but this allows for ease of reuse for any
	 * future resource testing.
	 * 
	 * @return the interview built by the provider
	 */
	@DataProvider(name = "interview1")
	public Object[][] provideInterview1() {

		TfAssociate a = associateService.getAssociate(392);

		TfClient c = new TfClient();

		TfEndClient ec = new TfEndClient();

		TfInterviewType it = new TfInterviewType();

		interview1.setAssociate(a);

		interview1.setClient(c);
		interview1.getClient().setId(7);
		interview1.getClient().setName("AAA Global Technologies, LLC");

		interview1.setEndClient(ec);
		interview1.getEndClient().setId(7);
		interview1.getEndClient().setName("AAA Global Technologies, LLC");

		interview1.setInterviewType(it);
		interview1.getInterviewType().setId(4);
		interview1.getInterviewType().setName("Skype");

		interview1.setInterviewDate(new Timestamp(152500500L));
		interview1.setAssociateFeedback("Interviewed well");
		interview1.setQuestionGiven("Start Date?");
		interview1.setClientFeedback("Strong Java knowledge");
		interview1.setJobDescription("SDET");
		interview1.setDateSalesIssued(new Timestamp(152500500L));
		interview1.setDateAssociateIssued(new Timestamp(152500500L));
		interview1.setWas24HRNotice(0);
		interview1.setIsInterviewFlagged(1);
		interview1.setFlagReason("Alert");
		interview1.setIsClientFeedbackVisible(1);

		Object[][] object = new Object[1][1];
		object[0][0] = interview1;
		return object;
	}

	/**
	 * Data provider build an interview and then pass that interview in where
	 * needed. This data provider could easily be replaced in the before method
	 * since it is only being used once, but this allows for ease of reuse for any
	 * future resource testing.
	 * 
	 * @return the interview built by the provider
	 */
	@DataProvider(name = "interview2")
	public Object[][] provideInterview2() {

		TfAssociate a = associateService.getAssociate(1);

		TfClient c = new TfClient();

		TfEndClient ec = new TfEndClient();

		TfInterviewType it = new TfInterviewType();

		interview2 = interviewService.getInterviewById(18);
		interview2.setAssociate(a);

		interview2.setClient(c);
		interview2.getClient().setId(7);
		interview2.getClient().setName("AAA Global Technologies, LLC");

		interview2.setEndClient(ec);
		interview2.getEndClient().setId(4);
		interview2.getEndClient().setName("9to9 Software Solutions, LLC");

		interview2.setInterviewType(it);
		interview2.getInterviewType().setId(4);
		interview2.getInterviewType().setName("Skype");

		interview2.setInterviewDate(new Timestamp(152500500L));
		interview2.setAssociateFeedback("Interviewed well");
		interview2.setQuestionGiven("Start Date?");
		interview2.setClientFeedback("Strong Java knowledge");
		interview2.setJobDescription("MEME LORD");
		interview2.setDateSalesIssued(new Timestamp(152500500L));
		interview2.setDateAssociateIssued(new Timestamp(152500500L));
		interview2.setWas24HRNotice(0);
		interview2.setIsInterviewFlagged(1);
		interview2.setFlagReason("Alert");
		interview2.setIsClientFeedbackVisible(1);

		Object[][] object = new Object[1][1];
		object[0][0] = interview2;
		return object;
	}
}
