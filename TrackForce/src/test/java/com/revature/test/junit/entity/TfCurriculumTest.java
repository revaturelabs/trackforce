package com.revature.test.junit.entity;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

import com.revature.entity.TfBatch;
import com.revature.entity.TfCurriculum;

public class TfCurriculumTest {
	
	TfCurriculum tfcur = new TfCurriculum();
	
	@Test
	public void test1() {
		tfcur.setTfBatches(new HashSet<TfBatch>());
		assertTrue(tfcur.getTfBatches() instanceof HashSet);
	}
	
	@Test
	public void test2() {
		tfcur.setTfCurriculumId(66);
		assertTrue(tfcur.getTfCurriculumId() == 66);
		assertFalse(tfcur.getTfCurriculumId() == 55);
	}
	
	@Test
	public void test3() {
		tfcur.setTfCurriculumName("Java");
		assertTrue(tfcur.getTfCurriculumName().equals("Java"));
		assertFalse(tfcur.getTfCurriculumName().equals("java"));
	}
}
