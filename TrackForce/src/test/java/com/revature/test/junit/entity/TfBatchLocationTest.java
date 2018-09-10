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
	TfBatchLocation batchLoc3 = new TfBatchLocation(big);
	TfBatchLocation batLocTest = new TfBatchLocation();

	@Test
	public void testBatchLocTfBatchGetSet() {
		batLocTest.setTfBatches(new HashSet<>());
		assertTrue(batLocTest.getTfBatches() instanceof HashSet);
	}

	@Test
	public void testBatchLocIDGetSet() {
		batLocTest.setTfBatchLocationId(new BigDecimal(89));
		assertTrue(batLocTest.getTfBatchLocationId() instanceof BigDecimal);
		assertTrue(batLocTest.getTfBatchLocationId().doubleValue() == 89);
	}

	@Test
	public void testBatchLocNameGetSet() {
		batLocTest.setTfBatchLocationName("Virginia");
		assertTrue(batLocTest.getTfBatchLocationName().equals("Virginia"));
		assertFalse(batLocTest.getTfBatchLocationName().equals("virginia"));
	}

	@Test
	public void testBatchLocEquivalence() {
		assertTrue(batchLoc1.equals(batchLoc2));
		assertFalse(batchLoc1.equals(new TfBatchLocation()));
	}

	@Test
	public void testBatchLocHashCode() {
		assertEquals(batchLoc1.hashCode(), batchLoc2.hashCode());
		assertNotEquals(batchLoc1.hashCode(), new TfBatchLocation().hashCode());
	}
	
	@Test
	public void testBatchLocToString() {
		assertEquals(batchLoc1.toString() ,batchLoc2.toString());
		assertNotEquals(batchLoc1.toString() ,batchLoc3.toString());
	}
}
