package com.revature.test.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.model.BatchInfo;

public class BatchInfoTest {

	/**
	 * Tests all the getters 
	 */
	@Test
	public void testBatchInfoGetters() {
		//Creates a new Object and sets all the values
		BatchInfo bi = new BatchInfo();
		bi.setId(1);
		bi.setBatchName("jta2018");
		bi.setCurriculumName("java");
		bi.setLocation("Reston");
		bi.setStartDate("06/07/2018");
		bi.setEndDate("07/08/2018");
		bi.setStartLong(new Long(100));
		bi.setEndLong(new Long(200));
		//bi.setStartTs(new Timestamp(System.currentTimeMillis()));
		//bi.setAssociates(associates);


		assertEquals(new Integer(1), bi.getId());
		assertEquals("jta2018" , bi.getBatchName());
		assertEquals("java", bi.getCurriculumName());
		assertEquals("Reston" ,bi.getLocation());
		assertEquals("06/07/2018", bi.getStartDate());
		assertEquals("07/08/2018", bi.getEndDate());
		assertEquals(new Long(100),bi.getStartLong());
		//assertEquals()
	}
	
	/** Tests the setters
	 * 
	 */
	@Test
	public void testBatchInfoSetters() {
		
		/*Creats a new Object */
		BatchInfo bi = new BatchInfo();
		bi.setId(1);
		bi.setBatchName("jta2018");
		bi.setCurriculumName("java");
		bi.setLocation("Reston");
		bi.setStartDate("06/07/2018");
		bi.setEndDate("07/08/2018");
		bi.setStartLong(new Long(100));
		bi.setEndLong(new Long(200));
		
		
		/*Change the value of the object*/
		bi.setId(2);
		bi.setBatchName("jta2018");
		bi.setCurriculumName("sql");
		bi.setLocation("Maspeth");
		bi.setStartDate("06/07/2019");
		bi.setEndDate("07/08/2019");
		bi.setStartLong(new Long(500));
		bi.setEndLong(new Long(600));
		
		/*Tests if the object has been set successfully*/
		assertEquals(new Integer(2), bi.getId());
		assertEquals("jta2018" , bi.getBatchName());
		assertEquals("sql", bi.getCurriculumName());
		assertEquals("Maspeth" ,bi.getLocation());
		assertEquals("06/07/2019", bi.getStartDate());
		assertEquals("07/08/2019", bi.getEndDate());
		assertEquals(new Long(500),bi.getStartLong());
		assertEquals(new Long(600),bi.getEndLong());
	
	}
	
}
