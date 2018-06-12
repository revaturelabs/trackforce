package com.revature.test.junit;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import com.revature.model.CurriculumJSON;

/**
 * 
 * @author Sonam Jyapa
 * @since 6.6.4.2018
 *
 */

public class CurriculumJSONTest {

	CurriculumJSON cj = new CurriculumJSON();
	

	@Before
	public void setUp() throws Exception {
		cj.setCount(1);
		cj.setId(2);
		cj.setName("testString");
	}

	
	/**
	 * Tests all getters and setters
	 */
	@Test
	public void test() {
		int wrongIntValue = 1000;
		
		assertTrue(1 == cj.getCount());
		assertFalse(wrongIntValue == cj.getCount());
		
		assertTrue(2 == cj.getId());
		assertFalse(wrongIntValue == cj.getId());
		
		assertTrue(cj.getName().equals("testString"));
		assertFalse(cj.getName().equals("test"));
	}
	
	@Test
	public void testCompareTo() {
	
		int wrongValue = 1000;
		
		//create a second object
		CurriculumJSON cj2  = new CurriculumJSON();
		cj2.setCount(2);
		cj2.setId(2);
		cj2.setName("testString2");
		
		//create a third object
		CurriculumJSON cj3 = new CurriculumJSON();
		cj3.setCount(3);
		cj3.setId(4);
		cj3.setName("testString3");
		
		assertTrue(cj.compareTo(cj2) == 0);
		assertFalse(cj.compareTo(cj2) == wrongValue);
		
		assertTrue(cj.compareTo(cj3) < 0);
		assertFalse(cj.compareTo(cj3) > 0);
		
		assertTrue(cj3.compareTo(cj) > 0 );
		assertFalse(cj3.compareTo(cj) < 0);
		
		
	}

}
