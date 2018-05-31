package com.revature.test.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;

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
import com.revature.services.JWTService;
import com.revature.services.UserService;

/**
 * testNG tests for the BatchResource against the a live server. NOTE: server
 * must be running Set system wide variable TOMCAT_PORT to eg 8085 for linux/mac
 * set in your IDE(STS) in testng run configuration
 * 
 * @author Ian Buitrago
 *
 */
public class BatchTests {
	JWTService jService;
	UserService uService;
	String token;
	String domain;

	@BeforeClass
	public void init() {
		logger.info("BatchTests.init()...");
		
		uService = new UserService();
		jService = new JWTService(); 	// throws SQLException???
		
		String port = System.getenv().get("TOMCAT_PORT");
		domain = "http://localhost:" + port + "/";
		logger.info("	domain = " + domain);
		
		
		token = jService.createToken("Ian", 1);
		logger.info("token generated: " + token);
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

	@Test(enabled = true)
	public void testGetAllBatches() throws ClientProtocolException, IOException {
		logger.info("Testing getAllBatches()...");
		String URL = domain + "TrackForce/api/batches";
		HttpUriRequest request = new HttpGet(URL);
		request.addHeader("Authorization", token);

		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		int status = response.getStatusLine().getStatusCode();

		Assert.assertEquals(status, HttpStatus.SC_OK);
	}

	@Test(enabled = true)
	public void testGetAssociate() {
		logger.info("Testing getAssociate()...");
		String URL = domain + "TrackForce/api/associates/1";
		logger.info("	URL = " + URL);
		HttpUriRequest request = new HttpGet(URL);
		request.addHeader("Authorization", token);
		try {
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			int status = response.getStatusLine().getStatusCode();

			Assert.assertEquals(status, HttpStatus.SC_OK);

			String jsonMimeType = "application/json";
			String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
			Assert.assertEquals(jsonMimeType, mimeType);

			// body
			AssociateInfo associate = new ObjectMapper().readValue(response.getEntity().getContent(),
					AssociateInfo.class);
			logger.info(" body: " + associate);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
