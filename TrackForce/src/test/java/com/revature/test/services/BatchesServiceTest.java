package com.revature.test.services;

import static org.mockito.Matchers.anyInt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.dao.BatchDao;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.services.BatchService;


/**
 * Tests meant to ensure proper functionality of the BatchService methods
 * 
 * Reviewed by Daniel Lani
 * 
 * @since 6.06.07.18
 */
public class BatchesServiceTest {
	@Mock
	private BatchDao mockBatchDao;
	@InjectMocks
	private BatchService service;
	private TfBatch batch1, batch2, batch3;
	private ArrayList<TfBatch> batches;

	/**
	 * initializes the batch service and dao method mocks
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@BeforeClass
	public void beforeTest(){
		service = new BatchService(mockBatchDao);
		MockitoAnnotations.initMocks(this);

		// creates three batch objects
		batch1 = new TfBatch();
		batch1.setId(1);
		batch1.setBatchName("Name1");
		batch2 = new TfBatch();
		batch2.setId(2);
		batch2.setBatchName("Name2");
		batch3 = new TfBatch();
		batch3.setId(3);
		batch3.setBatchName("Name3");

		Mockito.when(mockBatchDao.getBatchById(anyInt())).thenReturn(batch1);
		Mockito.when(mockBatchDao.getBatchById(-1)).thenReturn(null);
		Mockito.when(mockBatchDao.getBatch("Name1")).thenReturn(batch1);
		Mockito.when(mockBatchDao.getBatch("NotAName")).thenReturn(null);
		
	}

	
	/**
	 * Tests the get BatchByName method under
	 * normal conditions
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 1)
	public void testGetBatchByName(){
		TfBatch expected = service.getBatch("Name1");
		Assert.assertEquals(expected, batch1);
	}
	
	/**
	 * Tests the get BatchByName method when
	 * the name provided is not the name of a batch
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 2)
	public void testGetBatchByNonExistantName() {
		TfBatch expected = service.getBatch("NotAName");
		Assert.assertNull(expected);
	}
	
	/**
	 * Tests the get BatchByName method when
	 * the name provided is not the name of a batch
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 3)
	public void testGetBatchesByNameNullBatchName() {
		TfBatch expected = service.getBatch(null);
		Assert.assertNull(expected);
	}
	
	/**
	 * Tests the get BatchById method under 
	 * normal conditions
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 4)
	public void testGetBatchById(){
		TfBatch expected = service.getBatchById(1);
		Assert.assertEquals(expected, batch1);
	}
	
	/**
	 * Tests the get BatchById method when 
	 * the id provided does not match any known batch
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 5)
	public void testGetBatchByNonExistantId(){
		TfBatch expected = service.getBatchById(-1);
		Assert.assertNull(expected);
	}
	
	/**
	 * test the getAllBatches method when
	 * batch list is null
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 6)
	public void testGetAllBatchesNullBatchList() {
		Mockito.when(mockBatchDao.getAllBatches()).thenReturn(batches);
		List<TfBatch> expected = service.getAllBatches();
		Assert.assertNull(expected);
	}
	
	/**
	 * test the getAllBatches method when
	 * batch list is empty
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 7)
	public void testGetAllBatchesEmptyBatchList() {
		batches = new ArrayList<>();
		Mockito.when(mockBatchDao.getAllBatches()).thenReturn(batches);
		List<TfBatch> expected = service.getAllBatches();
		Assert.assertEquals(expected,new ArrayList<>());
	}

	/**
	 * test the getAllBatches method under
	 * normal conditions
	 * 
	 * @author Daniel Lani
	 * 
	 * @since 6.06.14.18
	 */
	@Test(priority = 8)
	public void testGetAllBatches() {
		batches.add(batch1);
		batches.add(batch2);
		batches.add(batch3);
		Mockito.when(mockBatchDao.getAllBatches()).thenReturn(batches);
		List<TfBatch> expected = service.getAllBatches();
		Assert.assertEquals(expected,batches);
	}
}