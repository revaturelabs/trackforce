package com.revature.test.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;

import javax.ws.rs.core.Response.Status;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
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
 * TestNG tests for the resource layer against the a live server.
 * These are just a set of non-exhaustive tests that aim at ensuring that the most important functions work hence smoke test.
 * Mostly checks the status codes of the GET resources.
 * 
 * NOTE: tomcat server must be running for these tests to pass
 * 
 * Create environment variable TOMCAT_PORT to the port your tomcat server uses eg 8085
 * 
 * for Linux/mac: Set in these variables in your IDE(STS) in testng run configuration
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

		String port = System.getenv().get("TOMCAT_PORT");
		domain = "http://localhost:" + port + "/";
		logger.info("	domain = " + domain);

		token = JWTService.createToken("Ian", 1);
		logger.info("token generated: " + token);
	}
	
	// TESTS
	/**
	 * Tests dummy resource. If it fails, the server may be off.
	 */
	@Test
	public void adamTest() throws IOException {
		logger.info("Testing adam()...");
		String URL = domain + "TrackForce/api/batches/adam";
		logger.info("	URL = " + URL);

		HttpUriRequest request = new HttpGet(URL);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		int status = response.getStatusLine().getStatusCode();

		Assert.assertEquals(status, HttpStatus.SC_OK);
	}

	/**
	 * URI and Status code.
	 */
	@Test(priority = 0, groups = {"GET"})
	public void test1GetAllAssociates() {
		String URI = "TrackForce/api/associates";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}
	
	@Test(groups = "GET")
	public void test2GetAssociate() {
		String URI = "TrackForce/api/associates/1";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}
	@Test(groups = {"GET", "negative"})
	public void test2GetAssociateN() {
		String URI = "TrackForce/api/associates/0";
		Status expectedStatus = Status.NO_CONTENT;

		testResource("GET", URI, expectedStatus);
	}

	@Test(enabled = true, groups = "GET")
	public void test3GetInterviewsFromAssociate() {
		String URI = "TrackForce/api/associates/1/interviews";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}

	@Test(groups = "GET")
	public void test3_1GetInterviewFromAssociate() {
		String URI = "TrackForce/api/associates/1/interviews/1";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}
	
	@Test(groups = "POST", dependsOnMethods ="test2GetAssociate")
	public void test4CreateInterview() throws JsonProcessingException {
		String URI = "TrackForce/api/associates/1/interviews";
		Status expectedStatus = Status.CREATED;
		String interview = new ObjectMapper().writeValueAsString(new InterviewFromClient(1, 1, 1));		// marshals interview

		String URL = domain + URI;
		logger.info("Testing POST URL = " + URL);
		
		logger.info("	Body: " + prettifyJSON(interview));
		HttpUriRequest request = RequestBuilder.create("POST").setUri(URL)
				.setEntity(new StringEntity(interview, ContentType.APPLICATION_JSON))
				.addHeader("Authorization", token).build();

		HttpResponse response = respond(request);
		if (response == null) {
			return;
		}
		Status status = Status.fromStatusCode(response.getStatusLine().getStatusCode());

		Assert.assertEquals(status, expectedStatus);
	}

	@Test(enabled = true, groups = "PUT", dependsOnMethods ="test2GetAssociate")
	public void verifyAssociate() {
		String URI = "TrackForce/api/associates/1/verify";
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
	private boolean testResource(String method, String URI, Status expectedStatus){
		String URL = domain + URI;
		logger.info("Testing GET URL = " + URL);
		HttpUriRequest request = RequestBuilder.create(method).setUri(URL)
				.addHeader("Authorization", token).build();
		HttpResponse response = respond(request);
		if (response == null) {
			return false;
		}
		Status status = Status.fromStatusCode(response.getStatusLine().getStatusCode());

		Assert.assertEquals(status, expectedStatus);
		// prints body to log files
//		logger.info("	Body: " + prettifyJSON(interview));
//		String body = response.getEntity().getContent();
//		logger.debug("	response body = " + body);
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
		JsonElement je = jp.parse(JSON);

		return gson.toJson(je);
	}
}
