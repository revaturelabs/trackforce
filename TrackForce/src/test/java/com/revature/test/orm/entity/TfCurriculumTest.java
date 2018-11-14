package com.revature.test.orm.entity;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import com.revature.entity.TfBatch;
import com.revature.entity.TfCurriculum;

/**
 * Tests to test basic getter and setter functionality for TfCurriculum
 * 
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfCurriculumTest {
	Set<TfBatch> batches = new HashSet<>();

	TfCurriculum cur1 = new TfCurriculum(1, "name", batches);
	TfCurriculum cur2 = new TfCurriculum(1, "name", batches);
	TfCurriculum tfcur = new TfCurriculum();

	@Test
	public void testCurriculumBatchesGetSet() {
		tfcur.setBatches(new HashSet<TfBatch>());
		assertTrue(tfcur.getBatches() instanceof HashSet);
	}

	@Test
	public void testCurriculumIDGetSet() {
		tfcur.setId(66);
		assertTrue(tfcur.getId() == 66);
		assertFalse(tfcur.getId() == 55);
	}

	@Test
	public void testCurriculumNameGetSet() {
		tfcur.setName("Java");
		assertTrue(tfcur.getName().equals("Java"));
		assertFalse(tfcur.getName().equals("java"));
	}

	@Test
	public void testCurriculumEquivalence() {
		assertTrue(cur1.equals(cur2));
		assertFalse(cur1.equals(new TfCurriculum()));
	}

	@Test
	public void testCurriculumHashCode() {
		assertEquals(cur1.hashCode(), cur2.hashCode());
		assertNotEquals(cur1.hashCode(), new TfCurriculum().hashCode());
	}
	
	//Have to hard code SerialID for now as it is private static,
	//always verify this is using the correct SerialID as defined
	//in the TfCurriculum.java file
	@Test
	public void testCurriculumSerialIDGet() {
		long sid = 8213885869880424792L;
		assertTrue(TfCurriculum.getSerialversionuid() == sid);
	}
	
	@Test
	public void testCurriculumToString() {
		assertEquals(cur1.toString(), cur2.toString());
		assertNotEquals(cur1.toString(), tfcur.toString());
	}
}
