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
import com.revature.services.AssociateService;
import com.revature.services.InterviewService;
import com.revature.services.JWTService;

import io.restassured.response.Response;

/**
 * Rest Assured to ensure that this resource is functioning as intended.
 * 
 * @author Jesse
 * @since 06.18.06.16
 */
public class InterviewResourceTest {

	static final String URL = "http://52.87.205.55:8086/TrackForce/interviews";
	//static final String URL = "http://localhost:8085/TrackForce/interviews";

	String adminToken;
	String associateToken;
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
		
		adminToken = JWTService.createToken("TestAdmin", 1);
		System.out.println(adminToken);
		associateToken = JWTService.createToken("AssociateTest", 5);
		System.out.println(associateToken);
		
		knownAssociateId = 392;
	}
	

	/**
	 * Test that the resource can be accessed properly. Check that the content type
	 * is what is expected. Test that a bad token gives a 401. Test that a bad url
	 * gives a 404. Test that a bad method gives a 405. This should return a 401
	 * as admins are not allowed to create interviews
	 * 
	 * @param ifc
	 *            - an interviewFromClient object returned from the data provider
	 * @author Jesse
	 * @since 6.18.06.13
	 */
	@Test(priority = 5, dataProvider = "interview1", enabled = true)
	public void testCreateInterview1(TfInterview interview) {

		given().header("Authorization", adminToken).contentType("application/json").body(interview).when().post(URL + "/" + knownAssociateId)
				.then().assertThat().statusCode(401);
		
		Response response = given().header("Authorization", adminToken).contentType("application/json").body(interview)
				.when().get(URL + "/" + knownAssociateId).then().extract().response();
		
		assertTrue(response.asString().contains("Strong Java knowledge"));
	}
	
	/**
	 * Testing to ensure an associate can create interviews
	 * @param interview
	 */
	@Test(priority = 6, dataProvider = "interview1", enabled = true)
	public void testCreateInterview2(TfInterview interview) {

		given().header("Authorization", associateToken).contentType("application/json").body(interview).when().post(URL + "/" + knownAssociateId)
				.then().assertThat().statusCode(201);
		
		Response response = given().header("Authorization", associateToken).contentType("application/json").body(interview)
				.when().get(URL + "/" + knownAssociateId).then().extract().response();
		
		assertTrue(response.asString().contains("Strong Java knowledge"));
	}
	
	/**
	 * Unhappy path testing for testCreateInterview
	 */
	@Test(priority = 7, dataProvider = "interview1", enabled = true)
	public void testCreateInterview3(TfInterview interview) {
		Response response = given().header("Authorization", "Bad Token").contentType("application/json").body(interview)
				.when().post(URL + "/" + knownAssociateId).then().extract().response();

		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("Unauthorized"));

		given().header("Authorization", adminToken).contentType("application/json").body(interview).when()
				.post(URL + "/" + knownAssociateId + "BADURL").then().assertThat().statusCode(404);

		given().header("Authorization", adminToken).contentType("application/json").body(interview).when().put(URL).then()
				.assertThat().statusCode(405);
	}

	/**
	 * Tests that you can get an associate interview by passing in the number of the
	 * associateId. Also checks for a bad verb, a bad token and a bad url.
	 * 
	 * @author Jesse
	 * @since 6.18.06.13
	 */
	@Test(priority = 10)
	public void testGetAllInterviewsByAssociateId() {
		Response response = given().header("Authorization", adminToken).when().get(URL + "/" + 1).then().extract()
				.response();

		assertTrue(response.statusCode() == 200);

		given().header("Authorization", "Bad Token").when().get(URL + "/" + knownAssociateId).then().assertThat().statusCode(401);

		given().header("Authorization", adminToken).when().get(URL + "/" + knownAssociateId + "BAD").then().assertThat().statusCode(404);

		given().header("Authorization", adminToken).when().post(URL + "/" + knownAssociateId).then().assertThat().statusCode(415);
	}

	/**
	 * Tests that you can get an associate interview by passing in the number of the
	 * interviewId. Also checks for a bad verb, a bad token and a bad url.
	 * 
	 * @author Jesse
	 * @since 6.18.06.13
	 */
	@Test(priority = 15)
	public void testGetAssociateInterview() {
		Response response = given().header("Authorization", adminToken).when().get(URL + "/" + 3).then().extract().response();

		assertTrue(response.statusCode() == 200);

		given().header("Authorization", "Bad Token").when().get(URL + "/" + knownAssociateId).then().assertThat().statusCode(401);

		given().header("Authorization", adminToken).when().get(URL + "/" +  knownAssociateId + "BAD").then().assertThat().statusCode(404);

		given().header("Authorization", adminToken).when().post(URL + "/" +  knownAssociateId).then().assertThat().statusCode(415);
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
	@Test(priority = 20, dataProvider = "interview2", enabled = true)
	public void testUpdateInterview1(TfInterview interview) {
		Response response = given().header("Authorization", adminToken).contentType("application/json").body(interview)
				.when().put(URL + "/" +  knownAssociateId).then().extract().response();

		assertTrue(response.statusCode() == 202);
		
		response = given().header("Authorization", adminToken).contentType("application/json").body(interview)
				.when().get(URL + "/" +  knownAssociateId).then().extract().response();
		
		assertTrue(response.asString().contains("MEME LORD"));
	}
	
	/**
	 * Unhappy path testing for testUpdateInterview
	 */
	@Test(priority = 25, dataProvider = "interview2", enabled = true)
	public void testUpdateInterview2(TfInterview interview) {
		Response response = given().header("Authorization", "Bad Token").contentType("application/json").body(interview).when()
				.put(URL + "/" + knownAssociateId).then().extract().response();

		assertTrue(response.statusCode() == 401);
		assertTrue(response.asString().contains("Unauthorized"));

		given().header("Authorization", adminToken).contentType("application/json").body(interview).when().put(URL + "/" + "three")
				.then().assertThat().statusCode(404);
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

		TfAssociate a = associateService.getAssociate(392);

		TfClient c = new TfClient();

		TfEndClient ec = new TfEndClient();

		TfInterviewType it = new TfInterviewType();
		
		interview2 = interviewService.getInterviewById(1604);
		System.out.println(interview2);
		
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
		
		System.out.println(interview2);
		
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
