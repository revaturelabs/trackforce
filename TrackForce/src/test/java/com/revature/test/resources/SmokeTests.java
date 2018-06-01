package com.revature.test.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;

import javax.ws.rs.core.Response.Status;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;
import com.revature.request.model.InterviewFromClient;
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
	@Test(enabled = true, priority = 1, groups = "GET")
	public void test1GetAllAssociates() {
		String URI = "TrackForce/api/associates";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus
		// , AssociateInfo.class
		);
	}

	/**
	 * URI and Status code.
	 */
	@Test(enabled = true, dependsOnMethods ="test1GetAllAssociates", groups = "GET")
	public void test2GetAssociate() {
		String URI = "TrackForce/api/associates/1";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus
		// , AssociateInfo.class
		);
	}

	@Test(enabled = true, groups = "GET")
	public void test3GetInterviewFromAssociate() {
		String URI = "TrackForce/api/associates/1/interviews";
		Status expectedStatus = Status.OK;

		testResource("GET", URI, expectedStatus);
	}
	
	@Test(groups = "POST", enabled = false)
	public void createInterview() {
		String URI = "TrackForce/api/associates/1/interviews";
		Status expectedStatus = Status.CREATED;
		// InterviewFromClient interview = new InterviewFromClient(1, 1, 1);

		String URL = domain + URI;
		logger.info("Testing POST URL = " + URL);
		HttpUriRequest request = RequestBuilder.create("POST").setUri(URL)
				.setEntity(new StringEntity("{\"a\":1,\"b\":2}", ContentType.APPLICATION_JSON))
				.addHeader("Authorization", token).build();

		HttpResponse response = respond(request);
		if (response == null) {
			return;
		}
		Status status = Status.fromStatusCode(response.getStatusLine().getStatusCode());

		Assert.assertEquals(status, expectedStatus);
	}

	@Test(enabled = true)
	public void verifyAssociate() {
		String URI = "TrackForce/api/associates/1/verify";
		Status expectedStatus = Status.OK;

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
	 */
	private boolean testResource(String method, String URI, Status expectedStatus
//	 , Class<T> type
	) {
		String URL = domain + URI;
		logger.info("Testing GET URL = " + URL);
		HttpUriRequest request = RequestBuilder.create(method).setUri(URL)
				// .setEntity(new StringEntity("{\"a\":1,\"b\":2}",
				// ContentType.APPLICATION_JSON))
				.addHeader("Authorization", token).build();

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

	/**
	 * Tests dummy resource. If it fails, the server may be off.
	 */
	@Test(priority = 1)
	public void adamTest() throws IOException {
		logger.info("Testing adam()...");
		String URL = domain + "TrackForce/api/batches/adam";
		logger.info("	URL = " + URL);

		HttpUriRequest request = new HttpGet(URL);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		int status = response.getStatusLine().getStatusCode();

		Assert.assertEquals(status, HttpStatus.SC_OK);
	}
}
