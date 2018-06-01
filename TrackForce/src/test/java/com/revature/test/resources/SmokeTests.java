package com.revature.test.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;

import javax.ws.rs.core.Response.Status;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;
import com.revature.services.JWTService;
import com.revature.services.UserService;

/**
 * testNG tests for the BatchResource against the a live server.
 * 
 * NOTE: server must be runnin
 * 
 * Set system wide variable TOMCAT_PORT to eg 8085 for linux/mac
 * 
 * set in your IDE(STS) in testng run configuration
 * 
 * @author Ian Buitrago
 *
 */
public class SmokeTests {
	JWTService jService;
	UserService uService;
	String token;
	String domain;

	@BeforeClass
	public void init() {
		logger.info("BatchTests.init()...");

		String port = System.getenv().get("TOMCAT_PORT");
		domain = "http://localhost:" + port + "/";
		logger.info("	domain = " + domain);

		uService = new UserService();
		jService = new JWTService(); // throws SQLException???

		token = jService.createToken("Ian", 1);
		logger.info("token generated: " + token);
	}

	// TESTS
	@Test(enabled = true)
	public void getInterviewsForAssociate() {
		String URI = "TrackForce/api/associates/1/interviews";
		Status expectedStatus = Status.OK;

		boolean passed = testResource(URI, expectedStatus
		// , AssociateInfo.class
		);
		Assert.assertTrue(passed);
	}

	/**
	 * URI and Status code.
	 */
	@Test(enabled = true)
	public void getAssociatetest() {
		// logger.info("Testing getAssociate()...");

		String URI = "TrackForce/api/associates/1";
		Status expectedStatus = Status.OK;

		boolean passed = testResource(URI, expectedStatus
		// , AssociateInfo.class
		);
		Assert.assertTrue(passed);
	}

	@Test(enabled = true)
	public void getAllAssociatestest() {
		// logger.info("Testing getAllAssociates()...");

		String URI = "TrackForce/api/associates";
		Status expectedStatus = Status.OK;

		boolean passed = testResource(URI, expectedStatus
		// , AssociateInfo.class
		);
		Assert.assertTrue(passed);
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
	 */
	private boolean testResource(String URI, Status expectedStatus
	// , Class<T> type
	) {
		String URL = domain + URI;
		logger.info("Testing URL = " + URL);
		HttpUriRequest request = new HttpGet(URL);
		request.addHeader("Authorization", token);

		HttpResponse response = respond(request);
		if (response == null) {
			return false;
		}
		Status status = Status.fromStatusCode(response.getStatusLine().getStatusCode());

		Assert.assertEquals(status, expectedStatus);
		// prints body
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

	@Test(enabled = false)
	public void testAllBatches() {
		logger.info("Testing getAllBatches()...");
		String URL = domain + "TrackForce/api/batches";
		logger.info("	URL = " + URL);
		HttpUriRequest request = new HttpGet(URL);
		request.addHeader("Authorization", token);
		try {
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			int status = response.getStatusLine().getStatusCode();

			Assert.assertEquals(status, HttpStatus.SC_OK);

			// String jsonMimeType = "application/json";
			// String mimeType =
			// ContentType.getOrDefault(response.getEntity()).getMimeType();
			// Assert.assertEquals(jsonMimeType, mimeType);

			// body
			BatchInfo[] batches = new ObjectMapper().readValue(response.getEntity().getContent(), BatchInfo[].class);
			if (batches == null) {
				return;
			}
			logger.info("	batches.length: " + batches.length);
			if (batches.length < 1) {
				return;
			}
			logger.info("	batches[0]: " + batches[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests dummy resource. If it fails, the server may be off.
	 */
	@Test
	public void testAdam() throws IOException {
		logger.info("Testing adam()...");
		String URL = domain + "TrackForce/api/batches/adam";
		logger.info("	URL = " + URL);

		HttpUriRequest request = new HttpGet(URL);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		int status = response.getStatusLine().getStatusCode();

		Assert.assertEquals(status, HttpStatus.SC_OK);
	}

}
