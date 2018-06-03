package com.revature.test.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;

import javax.ws.rs.core.Response.Status;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.revature.request.model.InterviewFromClient;
import com.revature.services.JWTService;

/**
 * TestNG tests for the resource layer against the a live server. These are just
 * a set of non-exhaustive tests that aim at ensuring that the most important
 * functions work hence smoke test. Mostly checks the status codes of the GET
 * resources.
 * 
 * NOTE: tomcat server must be running for these tests to pass
 * 
 * Create environment variable TOMCAT_PORT to the port your tomcat server uses
 * eg 8085
 * 
 * for Linux/mac: Set these variables in /etc/environment
 * 
 * @author Ian Buitrago
 *
 */
public class SmokeTests {
	String token;
	String domain;

	@BeforeClass
	public void init() {
		logger.info("BatchTests.init()...");

		String port = System.getenv().get("TOMCAT_PORT"); // you must create this env variable on your machine
		domain = "http://localhost:" + port + "/TrackForce/api/";
		logger.info("	domain = " + domain);

		token = JWTService.createToken("Ian", 1);
		logger.info("token generated: " + token);
	}
	// TESTS

	/**
	 * Tests dummy resource. If it fails, the server may be off.
	 */
	@Test(priority = 0)
	public void adamTest() throws IOException {
		String URI = "batches/adam";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}

	/**
	 * URI and Status code.
	 */
	@Test(priority = 0, groups = { "GET", "batch" })
	public void testGetAllBatches() {
		String URI = "batches";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}

	@Test(priority = 0, groups = { "GET", "batch" })
	public void testGetBatch() {
		String URI = "batches/1";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}

	@Test(priority = 0, groups = { "GET", "batch" })
	public void testGetBatchAssociates() {
		String URI = "batches/1/associates";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}

	@Test(priority = 0, groups = { "GET", "batch" })
	public void testGetBatchByCur() {
		String URI = "batches/curriculum/jta";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}

	@Test(priority = 0, groups = { "GET", "associate" })
	public void test1GetAllAssociates() {
		String URI = "associates";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}

	@Test(priority = 0, groups = { "GET", "associate" })
	public void test2GetAssociate() {
		String URI = "associates/1";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}

	@Test(priority = 0, groups = { "GET", "associate", "negative" })
	public void test2GetAssociateN() {
		String URI = "associates/0";
		Status expectedStatus = Status.NO_CONTENT;

		testResource("GET", URI, expectedStatus);
	}

	@Test(priority = 0, groups = { "GET", "interview" })
	public void test3GetInterviews() {
		String URI = "associates/1/interviews";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}

	@Test(priority = 0, groups = { "GET", "interview" })
	public void test3GetAllInterviews() {
		String URI = "interviews";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}

	@Test(priority = 0, groups = { "GET", "interview" })
	public void test3_1GetInterview() {
		String URI = "associates/1/interviews/1";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}

	@Test(priority = 0, groups = { "GET", "curriculum" })
	public void test3_2GetSkills() {
		String URI = "skillset";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}

	@Test(priority = 0, groups = { "GET", "client" })
	public void test3_3GetClients() {
		String URI = "clients";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}

	@Test(priority = 0, groups = { "GET", "client" })
	public void test3_4GetClient() {
		String URI = "clients/1";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}
	
	@Test(enabled = false, priority = 0, groups = { "POST", "user" })
	public void test4SubmitCred() {
		String URI = "users/login";
		Status expectedStatus = Status.OK; // interview
		String URL = domain + URI;
		logger.info("Testing POST URL = " + URL);
		//TODO input appropriate string here
		String requestBody = "LoginJSON [username=TestAdmin, password=TestAdmin]";

		logger.info("	request body: " + prettifyJSON(requestBody));
		HttpUriRequest request = RequestBuilder.create("POST").setUri(URL)
				.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON))
				.addHeader("Authorization", token).build();

		HttpResponse response = respond(request);
		if (response == null) {
			Assert.fail("response null");
			return;
		}
		Status status = Status.fromStatusCode(response.getStatusLine().getStatusCode());

		Assert.assertEquals(status, expectedStatus);
	}

	@Test(priority = 0, groups = "POST", dependsOnMethods = "test2GetAssociate")
	public void test4CreateInterview() throws JsonProcessingException {
		String URI = "associates/1/interviews";
		Status expectedStatus = Status.CREATED;
		String interview = new ObjectMapper().writeValueAsString(new InterviewFromClient(1, 1, 1)); // marshals
																									// interview
		String URL = domain + URI;
		logger.info("Testing POST URL = " + URL);

		logger.info("	request body: " + prettifyJSON(interview));
		HttpUriRequest request = RequestBuilder.create("POST").setUri(URL)
				.setEntity(new StringEntity(interview, ContentType.APPLICATION_JSON)).addHeader("Authorization", token)
				.build();

		HttpResponse response = respond(request);
		if (response == null) {
			return;
		}
		Status status = Status.fromStatusCode(response.getStatusLine().getStatusCode());

		Assert.assertEquals(status, expectedStatus);
	}

	@Test(enabled = true, groups = "PUT", dependsOnMethods = "test2GetAssociate")
	public void verifyAssociate() {
		String URI = "associates/1/verify";
		Status expectedStatus = Status.NO_CONTENT;

		testResource("PUT", URI, expectedStatus);
	}

	/**
	 * 
	 * @param URI
	 *            to be tested
	 * @param expectedStatus
	 *            returned in response
	 * @param type
	 *            of object expected in body or response
	 * @return
	 * @throws IOException
	 * @throws UnsupportedOperationException
	 */
	private boolean testResource(String method, String URI, Status expectedStatus) {
		logger.info("GET " + URI);
		String URL = domain + URI;
		HttpUriRequest request = RequestBuilder.create(method).setUri(URL).addHeader("Authorization", token).build();
		HttpResponse response = respond(request);
		if (response == null) {
			Assert.fail("Response is null. Possible IOException");
			return false;
		}
		Status status = Status.fromStatusCode(response.getStatusLine().getStatusCode());

		// Check status codes match
		Assert.assertEquals(status, expectedStatus);

		// Print response body
		if (response.getEntity() != null)
			try {
				String responseBody = prettifyJSON(EntityUtils.toString(response.getEntity()));
				logger.debug("	response body = " + responseBody); // prints to logs in target/ (too big for console)
				logger.info("	response body printed in log files.");
			} catch (ParseException | IOException e) {
				e.printStackTrace();
			}
		else
			logger.info("	null response entity");

		// Map JSON to pojo
		// T obj = mapObject(response, type);
		// if (obj == null) {
		// return false;
		// }else {
		// logger.info(" pojo: " + obj.getClass() + " = \n\t" + obj);
		// }

		return status == expectedStatus;
	}

	/**
	 * Processes an http request and catches exceptions
	 * 
	 * @param httlp
	 *            request
	 * @return http response, null if exception is thrown
	 */
	private HttpResponse respond(HttpUriRequest request) {
		try {
			return HttpClientBuilder.create().build().execute(request);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Maps JSON object to pojo and catches exceptions
	 * 
	 * @param request
	 * @return null if exception is thrown
	 */
	private <T extends Object> T mapObject(HttpResponse response, Class<T> c) {
		try {
			return (T) new ObjectMapper().readValue(response.getEntity().getContent(), c);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String prettifyJSON(String JSON) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();

		try {
			JsonElement je = jp.parse(JSON);
			return gson.toJson(je);
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Test(priority = 1)
	public void c() {
	}

	@Test(priority = 1)
	public void aa() {
	}

	@Test(priority = 1)
	public void aA() {
	}

	@Test(priority = 1)
	public void A() {
	}

	@Test(priority = 1)
	public void a() {
	} // uncomment to order tests????????
		// @Test (priority = 1) public void a_() {}
		// @Test(priority = 1) public void a1() {}
		// @Test(priority = 1) public void a2() {}
}
