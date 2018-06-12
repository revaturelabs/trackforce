package com.revature.test.junit.model;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import org.junit.Before;
import org.junit.Test;
import com.revature.model.AssociateInfo;
import com.revature.model.PlacementInfo;

/**
 * 
 * @author Sonam Jyapa
 * @since 6.6.4.2018
 *
 */
public class PlacementInfoTest {
	
	PlacementInfo pi = new PlacementInfo();

	@Before
	public void setUp() throws Exception {
		
		pi.setId(1);
		pi.setTfAssociate(new AssociateInfo());
		pi.setTfPlacementStartDate(new Timestamp(1000L));
		pi.setTfPlacementEndDate(new Timestamp(2000L));
		
	}

	/**
	 * Tests both getters and setters.
	 * getting correct values from getters implies setters 
	 * were set properly.
	 * 
	 */
	@Test
	public void test() {
		
		// getID()
		assertTrue(pi.getId() == 1);
		assertFalse(pi.getId() == 2);
		// getTfAssociate
		assertTrue(pi.getTfAssociate() instanceof AssociateInfo);
		// getTfPlacementStartDate()
		assertTrue(pi.getTfPlacementStartDate().equals(new Timestamp(1000L)));
		assertFalse(pi.getTfPlacementStartDate().equals(new Timestamp(3000L)));
		// getTfPlacementEndDate
		assertTrue(pi.getTfPlacementEndDate().equals(new Timestamp(2000L)));
		assertFalse(pi.getTfPlacementEndDate().equals(new Timestamp(3000L)));
			
	}
	
	@Test
	public void testCompareTo() {
		
		int wrongValue = 10000;
		
		//created a second object
		PlacementInfo pi2 = new PlacementInfo();
		pi2.setId(10);
		//created a third object
		PlacementInfo pi3 = new PlacementInfo();
		pi3.setId(1);
		
		assertTrue(pi.compareTo(pi2) < 0 );		//test negative
		assertFalse(pi.compareTo(pi2) == wrongValue);
		
		assertTrue(pi.compareTo(pi3) == 0);		//test equal
		assertFalse(pi.compareTo(pi3) == wrongValue);
		
		assertTrue(pi2.compareTo(pi) > 0);		// test positive
		assertFalse(pi2.compareTo(pi) == wrongValue);
			
	}
	
}

