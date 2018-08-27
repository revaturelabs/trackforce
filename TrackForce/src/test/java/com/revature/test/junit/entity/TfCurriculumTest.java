package com.revature.test.junit.entity;
import com.revature.entity.TfBatch;
import com.revature.entity.TfCurriculum;
import org.testng.annotations.Test;
import java.util.HashSet;
import java.util.Set;
import static org.testng.Assert.*;

/**
 * Tests to test basic getter and setter functionality for TfCurriculum
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfCurriculumTest {
	Set<TfBatch> batches = new HashSet<>();

	TfCurriculum cur1 = new TfCurriculum(1, "name", batches);
	TfCurriculum cur2 = new TfCurriculum(1, "name", batches);
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

	@Test
	public void test4() {
		assertTrue(cur1.equals(cur2));
		assertFalse(cur1.equals(new TfCurriculum()));
	}

	@Test
	public void test5() {
		assertEquals(cur1.hashCode(), cur2.hashCode());
		assertNotEquals(cur1.hashCode(), new TfCurriculum().hashCode());
	}
}
