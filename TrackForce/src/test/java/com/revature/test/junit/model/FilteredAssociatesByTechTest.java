package com.revature.test.junit.model;

import static org.junit.Assert.*;
import org.junit.Test;

import com.revature.model.FilteredAssociatesByTech;

/***
 * jUnit tests for FilteredAssociatesByTech class in com.revature.model
 * @author David Kim
 * @since 6.18.06.11
 */
public class FilteredAssociatesByTechTest {

	FilteredAssociatesByTech fabt = new FilteredAssociatesByTech("Appian", 20);
	
	@Test
	public void testGetTechnology() {
		fabt.setTechnology("Testing Automation");
		assertTrue(fabt.getTechnology().equals("Testing Automation"));
		assertFalse(fabt.getTechnology().equals("Appian"));
	}
	
	@Test
	public void testGetNumAssociates() {
		fabt.setNumAssociates(22);
		assertTrue(fabt.getNumAssociates() == 22);
		assertFalse(fabt.getNumAssociates() == 20);
	}
}
