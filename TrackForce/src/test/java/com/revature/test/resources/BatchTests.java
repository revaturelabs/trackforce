package com.revature.test.resources;

import static com.revature.utils.LogUtil.logger;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.AssociateInfo;

/**
 * testNG tests for the BatchResource
 * 
 * @author Ian Buitrago
 *
 */
public class BatchTests {
	@Test
	public void testGetAllBatches() throws IOException {
		logger.info("Testing getAllBatches()...");
		String URL = "http://localhost:8085/TrackForce/api/batches/adam";
		HttpUriRequest request = new HttpGet(URL);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		int status = response.getStatusLine().getStatusCode();

		Assert.assertEquals(status, HttpStatus.SC_OK);

		String jsonMimeType = "application/json";
		String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
//		AssociateInfo events = new ObjectMapper().readValue(response.getEntity().getContent(), AssociateInfo.class);
		
//		Assert.assertEquals(jsonMimeType, mimeType);

	}
}
