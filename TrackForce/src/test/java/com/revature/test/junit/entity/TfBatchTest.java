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
		tfbatch.setAssociates(new HashSet<TfAssociate>());
		assertTrue(tfbatch.getAssociates() instanceof HashSet);
	}
	@Test
	public void test2() {
		tfbatch.setEndDate(new Timestamp(1000L));
		assertTrue(tfbatch.getEndDate().getTime() == 1000L);
		assertFalse(tfbatch.getEndDate().getTime() == 2000L);
	}
	@Test
	public void test3() {
		tfbatch.setId(1);
		assertTrue(tfbatch.getId() == 1);
		assertFalse(tfbatch.getId() == 2);
	}
	@Test
	public void test4() {
		tfbatch.setLocation(new TfBatchLocation());
		assertTrue(tfbatch.getLocation() instanceof TfBatchLocation);
	}
	@Test
	public void test5() {
		tfbatch.setBatchName("TFbatch");
		assertTrue(tfbatch.getBatchName().equals("TFbatch"));
		assertFalse(tfbatch.getBatchName().equals("tfbatch"));
	}
	@Test
	public void test6() {
		tfbatch.setStartDate(new Timestamp(1000L));
		assertTrue(tfbatch.getStartDate().getTime() == 1000L);
		assertFalse(tfbatch.getStartDate().getTime() == 1001L);
	}
	@Test
	public void test7() {
		tfbatch.setCurriculumName(new TfCurriculum());
		assertTrue(tfbatch.getCurriculumName() instanceof TfCurriculum);
	}
}
