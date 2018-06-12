package com.revature.test.junit.entity;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.HashSet;

import org.junit.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfBatchLocation;
import com.revature.entity.TfCurriculum;

/**
 * Tests to test basic getter and setter functionality for TfBatchTest
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfBatchTest {

	TfBatch tfbatch =  new TfBatch();
	
	@Test
	public void test1() {
		tfbatch.setTfAssociates(new HashSet<TfAssociate>());
		assertTrue(tfbatch.getTfAssociates() instanceof HashSet);
	}
	@Test
	public void test2() {
		tfbatch.setTfBatchEndDate(new Timestamp(1000L));
		assertTrue(tfbatch.getTfBatchEndDate().getTime() == 1000L);
		assertFalse(tfbatch.getTfBatchEndDate().getTime() == 2000L);
	}
	@Test
	public void test3() {
		tfbatch.setTfBatchId(1);
		assertTrue(tfbatch.getTfBatchId() == 1);
		assertFalse(tfbatch.getTfBatchId() == 2);
	}
	@Test
	public void test4() {
		tfbatch.setTfBatchLocation(new TfBatchLocation());
		assertTrue(tfbatch.getTfBatchLocation() instanceof TfBatchLocation);
	}
	@Test
	public void test5() {
		tfbatch.setTfBatchName("TFbatch");
		assertTrue(tfbatch.getTfBatchName().equals("TFbatch"));
		assertFalse(tfbatch.getTfBatchName().equals("tfbatch"));
	}
	@Test
	public void test6() {
		tfbatch.setTfBatchStartDate(new Timestamp(1000L));
		assertTrue(tfbatch.getTfBatchStartDate().getTime() == 1000L);
		assertFalse(tfbatch.getTfBatchStartDate().getTime() == 1001L);
	}
	@Test
	public void test7() {
		tfbatch.setTfCurriculum(new TfCurriculum());
		assertTrue(tfbatch.getTfCurriculum() instanceof TfCurriculum);
	}
}
