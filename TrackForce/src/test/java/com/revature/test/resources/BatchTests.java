package com.revature.test.resources;

import static com.revature.utils.LogUtil.logger;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.testng.annotations.Test;
/**
 * testNG tests for the BatchResource
 * @author Ian Buitrago
 *
 */
public class BatchTests {
	@Test
	public void testGetAllBatches() {
		logger.info("Testing getAllBatches()...");

	     HttpUriRequest request = new HttpGet( "http://localhost:8085/TrackForce/api/batches/adam" );

	     HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );  
	}
}
