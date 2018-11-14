package com.revature.test.orm.entity;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import com.revature.entity.TfInterview;
import com.revature.entity.TfInterviewType;

/**
 * Tests to test basic getter and setter functionality for TfInterviewType
 * 
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfInterviewTypeTest {
	Set<TfInterview> interviews = new HashSet<>();

	TfInterviewType type1 = new TfInterviewType(1, "name", interviews);
	TfInterviewType type2 = new TfInterviewType(1, "name", interviews);

	TfInterviewType tfit = new TfInterviewType();

	@Test
	public void testInterviewTypeInterviewsGetSet() {
		tfit.setInterviews(new HashSet<TfInterview>());
		assertTrue(tfit.getInterviews() instanceof HashSet);
	}

	@Test
	public void testInterviewTypeIDGetSet() {
		tfit.setId(54);
		assertTrue(tfit.getId() == 54);
		assertFalse(tfit.getId() == 47);
	}

	@Test
	public void testInterviewTypeNameGetSet() {
		tfit.setName("Interview");
		assertTrue(tfit.getName().equals("Interview"));
		assertFalse(tfit.getName().equals("interview"));
	}
	
	//Have to hard code SerialID for now as it is private static,
	//always verify this is using the correct SerialID as defined
	//in the TfInterviewType.java file
	@Test
	public void testInterviewTypeSerialIDGet() {
		long sid = -4949282863102956521L;
		assertTrue(TfInterviewType.getSerialversionuid() == sid);
	}

	@Test
	public void testInterviewTypeEquivalence() {
		assertTrue(type1.equals(type2));
		assertFalse(type1.equals(new TfInterviewType()));
	}

	@Test
	public void testInterviewTypeHashCode() {
		assertEquals(type1.hashCode(), type2.hashCode());
		assertNotEquals(type1.hashCode(), new TfInterviewType().hashCode());
	}
	
	@Test
	public void testInterviewTypeToString() {
		assertEquals(type1.toString(), type2.toString());
		assertNotEquals(type1.toString(), tfit.toString());
	}
}
