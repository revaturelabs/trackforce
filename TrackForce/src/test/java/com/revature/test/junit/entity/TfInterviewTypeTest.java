package com.revature.test.junit.entity;
import com.revature.entity.TfInterview;
import com.revature.entity.TfInterviewType;
import org.testng.annotations.Test;
import java.util.HashSet;
import java.util.Set;
import static org.testng.Assert.*;

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

	@Test
	public void test4() {
		assertTrue(type1.equals(type2));
		assertFalse(type1.equals(new TfInterviewType()));
	}

	@Test
	public void test5() {
		assertEquals(type1.hashCode(), type2.hashCode());
		assertNotEquals(type1.hashCode(), new TfInterviewType().hashCode());
	}
}
