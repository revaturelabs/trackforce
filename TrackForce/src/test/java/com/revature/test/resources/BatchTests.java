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
 * testNG tests for the BatchResource
 * NOTE: server must be running
 * 
 * @author Ian Buitrago
 *
 */
public class BatchTests {
	JWTService jService;
	UserService uService;
	String token;

	@BeforeClass
	public void testinit() {
		try {
			logger.info("testInit()...");
			uService = new UserService(); // throws SQLException???
			jService = new JWTService(); // throws SQLException???
			token = jService.createToken("Ian", 1);
			logger.info("token generated: " + token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAdam() throws IOException {
		logger.info("Testing adam()...");
		String URL = "http://localhost:8085/TrackForce/api/batches/adam";

		HttpUriRequest request = new HttpGet(URL);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		int status = response.getStatusLine().getStatusCode();

		Assert.assertEquals(status, HttpStatus.SC_OK);
	}

	@Test(enabled = true)
	public void testGetAllBatches1() throws ClientProtocolException, IOException {
		logger.info("Testing getAllBatches1()...");
		String URL = "http://localhost:8085/TrackForce/api/batches";
		HttpUriRequest request = new HttpGet(URL);
		request.addHeader("Authorization", token);

		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		int status = response.getStatusLine().getStatusCode();

		Assert.assertEquals(status, HttpStatus.SC_OK);
	}

	@Test(enabled = true)
	public void testGetAllBatches2() {
		logger.info("Testing getAllBatches2()...");
		String URL = "http://localhost:8085/TrackForce/api/associates/1";
		logger.info("	url = " + URL);
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
//			AssociateInfo associate = new ObjectMapper().readValue(response.getEntity().getContent(), 
//					AssociateInfo.class);
//			logger.info("	body: " + associate);
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
