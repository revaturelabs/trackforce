package com.revature.test.junit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertFalse;
import java.sql.Timestamp;
import java.util.TreeSet;
import org.junit.Before;
import org.junit.Test;
import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;

public class BatchInfoTest {
	
	/**
	 * @author Sonam Jyapa
	 * @since 6.18.06.07 
	 */

	BatchInfo bi = new BatchInfo();
	final int wrongValue = 10000;
	
	@Before
	public void setup() throws Exception {
		//Creates a new Object and sets all the values
			
			bi.setId(1);
			bi.setBatchName("jta2018");
			bi.setCurriculumName("java");
			bi.setLocation("Reston");
			bi.setStartDate("06/07/2018");
			bi.setEndDate("07/08/2018");
			bi.setStartLong(new Long(100));
			bi.setEndLong(new Long(200));
			bi.setStartTs(new Timestamp(1000));
			bi.setAssociates(new TreeSet<AssociateInfo>());
	}
	
	/**
	 * Tests all the getters from the class
	 * To test a new model just set a new instance variable
	 *
	 */
	@Test
	public void test() {
		
		
		String wrongString = "wrongString";
		
		assertEquals(new Integer(1), bi.getId());
		assertFalse(bi.getId() == wrongValue);
		
		assertEquals("jta2018" , bi.getBatchName());
		assertFalse(wrongString.equals(bi.getBatchName()));
		
		assertEquals("java", bi.getCurriculumName());
		assertFalse(wrongString.equals(bi.getCurriculumName()));
		
		assertEquals("Reston" ,bi.getLocation());
		assertFalse(wrongString.equals(bi.getLocation()));
		
		assertEquals("06/07/2018", bi.getStartDate());
		assertFalse(wrongString.equals(bi.getStartDate()));
		
		assertEquals("07/08/2018", bi.getEndDate());
		assertFalse(wrongString.equals(bi.getEndDate()));
		
		
		assertEquals(new Long(100),bi.getStartLong());
		assertFalse(new Long(wrongValue) == new Long(100));
		
		assertEquals(new Timestamp(1000), bi.getStartTs());
		assertFalse(new Timestamp(wrongValue) == bi.getStartTs());
		
		assertTrue(bi.getAssociates() instanceof TreeSet);
		
	}
	
	
	@Test
	public void testCompareTo() {
		
		BatchInfo bi2 = new BatchInfo();
		
		//create an object to compare to
		bi2.setId(1);
		bi2.setBatchName("jta2018");
		bi2.setCurriculumName("java");
		bi2.setLocation("Reston");
		bi2.setStartDate("06/07/2018");
		bi2.setEndDate("07/08/2018");
		bi2.setStartLong(new Long(100));
		bi2.setEndLong(new Long(200));
		bi2.setStartTs(new Timestamp(1000));
		
		//create an object to compare to
		BatchInfo bi3 = new BatchInfo();
		bi3.setId(3);
		
		assertTrue(bi.compareTo(bi2) == 0);				//test for equal
		assertFalse(bi.compareTo(bi2) == wrongValue);
		
		assertTrue(bi.compareTo(bi3) < 0);				// test for negative
		assertFalse(bi.compareTo(bi3) == wrongValue);
		
		assertTrue(bi3.compareTo(bi) > 0);				// test of positive
		assertFalse(bi3.compareTo(bi) == wrongValue);
		
	}
	
}
