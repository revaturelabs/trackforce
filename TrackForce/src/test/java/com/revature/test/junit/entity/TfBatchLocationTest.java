package com.revature.test.junit.entity;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import com.revature.entity.TfBatch;
import com.revature.entity.TfBatchLocation;

/**
 * Tests to test basic getter and setter functionality for TfBatchLocation
 * 
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfBatchLocationTest {
	BigDecimal big = new BigDecimal(5);
	Set<TfBatch> tfBatches = new HashSet<>();
	TfBatchLocation batchLoc1 = new TfBatchLocation(big, "Location", tfBatches);
	TfBatchLocation batchLoc2 = new TfBatchLocation(big, "Location", tfBatches);
	TfBatchLocation batLocTest = new TfBatchLocation();

	@Test
	public void test1() {
		batLocTest.setTfBatches(new HashSet());
		assertTrue(batLocTest.getTfBatches() instanceof HashSet);
	}

	@Test
	public void test2() {
		batLocTest.setTfBatchLocationId(new BigDecimal(89));
		assertTrue(batLocTest.getTfBatchLocationId() instanceof BigDecimal);
		assertTrue(batLocTest.getTfBatchLocationId().doubleValue() == 89);
		assertFalse(batLocTest.getTfBatchLocationId().doubleValue() == 123);
	}

	@Test
	public void test3() {
		batLocTest.setTfBatchLocationName("Virginia");
		assertTrue(batLocTest.getTfBatchLocationName().equals("Virginia"));
		assertFalse(batLocTest.getTfBatchLocationName().equals("virginia"));
	}

	@Test
	public void test4() {
		assertTrue(batchLoc1.equals(batchLoc2));
		assertFalse(batchLoc1.equals(new TfBatchLocation()));
	}

	@Test
	public void test5() {
		assertEquals(batchLoc1.hashCode(), batchLoc2.hashCode());
		assertNotEquals(batchLoc1.hashCode(), new TfBatchLocation().hashCode());
	}
}
