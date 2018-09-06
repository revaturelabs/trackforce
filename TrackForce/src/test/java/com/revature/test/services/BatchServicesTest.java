package com.revature.test.services;

import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.services.BatchService;

public class BatchServicesTest {
	
	@Mock
	private BatchService service;

	@BeforeClass
	public void beforeClass() {
		service = new BatchService();
	}
	
	@Test
	public void testGetBatch() {
		when(service.getBatch("1712 Dec04 AP-USF")).thenReturn(null);
	}

	@Test
	public void testGetAllBatches() {
		
	}

}
