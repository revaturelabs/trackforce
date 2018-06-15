package com.revature.test.junit.entity;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

import com.revature.entity.TfBatch;
import com.revature.entity.TfCurriculum;

/**
 * Tests to test basic getter and setter functionality for TfCurriculum
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfCurriculumTest {
	
	TfCurriculum tfcur = new TfCurriculum();
	
	@Test
	public void test1() {
		tfcur.setBatches(new HashSet<TfBatch>());
		assertTrue(tfcur.getBatches() instanceof HashSet);
	}
	
	@Test
	public void test2() {
		tfcur.setId(66);
		assertTrue(tfcur.getId() == 66);
		assertFalse(tfcur.getId() == 55);
	}
	
	@Test
	public void test3() {
		tfcur.setName("Java");
		assertTrue(tfcur.getName().equals("Java"));
		assertFalse(tfcur.getName().equals("java"));
	}
}
