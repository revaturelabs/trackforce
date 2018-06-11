package com.revature.test.junit.entity;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashSet;

import org.junit.Test;

import com.revature.entity.TfBatchLocation;

public class TfBatchLocationTest {

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

}
