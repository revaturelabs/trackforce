package com.revature.test.junit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.revature.model.AssociateInfo;
import com.revature.model.EndClientInfo;
import com.revature.model.PlacementInfo;

/**
 * @author Sonam Jyapa
 * @since 6.18.06.07 
 */

public class EndClientInfoTest {
	
	EndClientInfo eci = new EndClientInfo();
	
	@Before
	public void createObjectBeforeTest() throws Exception {
		
		eci.setId(1);
		eci.setTfEndClientName("John Doe");
		eci.setTfAssociates(new HashSet<AssociateInfo> ());
		eci.setTfPlacements(new HashSet<PlacementInfo> ());
	}
	
	
	
	@Test
	public void testEndClientInfoGetters(){
		
		assertTrue(eci.getId() == 1);
		assertFalse(eci.getId() == 2);
		assertTrue(eci.getTfEndClientName().equals("John Doe"));
		assertFalse(eci.getTfEndClientName().equals("John"));
		assertTrue(eci.getTfAssociates() instanceof HashSet);
		assertTrue(eci.getTfPlacements() instanceof HashSet);	
	}
	
	@Test 
	public void testEndClientInfoSetters() {
		
		EndClientInfo eci2 = new EndClientInfo();
		
		// Sets new Value
		eci2.setId(2);
		eci2.setTfEndClientName("John Doe");
		eci2.setTfAssociates(new HashSet<AssociateInfo> ());		
		eci2.setTfPlacements(new HashSet<PlacementInfo> ());
	
		//checks if the setters were set properly
		assertTrue(eci2.getId() == 2);
		assertFalse(eci2.getId() == 3);
		assertTrue(eci2.getTfEndClientName().equals("John Doe"));
		assertFalse(eci2.getTfEndClientName().equals("John"));
		assertTrue(eci2.getTfAssociates() instanceof HashSet);		
		assertTrue(eci2.getTfPlacements() instanceof HashSet);	
	}
	
	@Test
	public void testCompareTo() {
		
		EndClientInfo eci3 = new EndClientInfo();
		eci3.setId(1);
		
		EndClientInfo eci4 = new EndClientInfo();
		eci4.setId(5);
		
		assertTrue(eci.compareTo(eci3) == 0);			//test for equality
		assertFalse(eci.compareTo(eci3) == 1);
		
		assertTrue(eci.compareTo(eci4) < 0 );			//test for negative
		assertFalse(eci.compareTo(eci4) == 1);
		
		assertTrue(eci4.compareTo(eci) > 0);			//test for positive
		assertFalse(eci4.compareTo(eci) == 10);
				
		
	}
	
}
	
	
	
	
	
