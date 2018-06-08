package com.revature.test.junit.entity;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

import com.revature.entity.TfInterview;
import com.revature.entity.TfInterviewType;

public class TfInterviewTypeTest {

	TfInterviewType tfit = new TfInterviewType();
	
	@Test
	public void test1() {
		tfit.setTfInterviews(new HashSet<TfInterview>());
		assertTrue(tfit.getTfInterviews() instanceof HashSet);
	}
	@Test
	public void test2() {
		tfit.setTfInterviewTypeId(54);
		assertTrue(tfit.getTfInterviewTypeId() == 54);
		assertFalse(tfit.getTfInterviewTypeId() == 47);
	}
	@Test
	public void test3() {
		tfit.setTfInterviewTypeName("Interview");
		assertTrue(tfit.getTfInterviewTypeName().equals("Interview"));
		assertFalse(tfit.getTfInterviewTypeName().equals("interview"));
	}
}
