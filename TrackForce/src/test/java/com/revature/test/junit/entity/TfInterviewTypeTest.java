package com.revature.test.junit.entity;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

import com.revature.entity.TfInterview;
import com.revature.entity.TfInterviewType;

/**
 * Tests to test basic getter and setter functionality for TfInterviewType
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfInterviewTypeTest {

	TfInterviewType tfit = new TfInterviewType();
	
	@Test
	public void test1() {
		tfit.setInterviews(new HashSet<TfInterview>());
		assertTrue(tfit.getInterviews() instanceof HashSet);
	}
	@Test
	public void test2() {
		tfit.setId(54);
		assertTrue(tfit.getId() == 54);
		assertFalse(tfit.getId() == 47);
	}
	@Test
	public void test3() {
		tfit.setName("Interview");
		assertTrue(tfit.getName().equals("Interview"));
		assertFalse(tfit.getName().equals("interview"));
	}
}
