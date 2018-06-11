package com.revature.test.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.revature.model.AssociateInfo;
import com.revature.model.EndClientInfo;
import com.revature.model.PlacementInfo;

public class EndClientInfoTest {
	
	EndClientInfo eci = new EndClientInfo();
	
	@Before
	public void createObjectBeforeTest() throws Exception {
		
		eci.setId(1);
		eci.setTfEndClientName("John Doe");
		eci.setTfAssociates(new HashSet<AssociateInfo> ());
		eci.setTfPlacements(new HashSet<PlacementInfo> ());
	}
	
	@After 
	public void setObjectToDefault() throws Exception {
		
		eci.setId(0);
		eci.setTfEndClientName(null);
		eci.setTfAssociates(null);
		eci.setTfPlacements(null);
		
	}
	
	
	@Test
	public void TestEndClientInfoGetters(){
		
		assertTrue(eci.getId() == 1);
		assertFalse(eci.getId() == 2);
		assertTrue(eci.getTfEndClientName().equals("John Doe"));
		assertFalse(eci.getTfEndClientName().equals("John"));
		assertTrue(eci.getTfAssociates() instanceof HashSet);
		assertTrue(eci.getTfPlacements() instanceof HashSet);	
	}
	
	@Test 
	public void TestEndClientInfoSetters() {
		
		// Sets new Value
		eci.setId(2);
		eci.setTfEndClientName("John Doe");
		eci.setTfAssociates(new HashSet<AssociateInfo> ());		
		eci.setTfPlacements(new HashSet<PlacementInfo> ());
	
		//checks if the setters were set properly
		assertTrue(eci.getId() == 2);
		assertFalse(eci.getId() == 3);
		assertTrue(eci.getTfEndClientName().equals("John Doe"));
		assertFalse(eci.getTfEndClientName().equals("John"));
		assertTrue(eci.getTfAssociates() instanceof HashSet);		
		assertTrue(eci.getTfPlacements() instanceof HashSet);	
	}
	
}
	
	
	
	
	
