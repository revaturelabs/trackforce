package com.revature.test.junit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import com.revature.model.AssociateInfo;
import com.revature.model.ClientInfo;
import com.revature.model.StatusInfo;

/**
 * @author Sonam Jyapa
 * @since 6.18.06.07 
 */

public class ClinetInfoTest {
	
	ClientInfo ci = new ClientInfo();
	
	@Before
	public void setUp() throws Exception {
		
		ci.setId(1);
		ci.setStats(new StatusInfo());
		ci.setTfAssociates(new HashSet<AssociateInfo>());
		ci.setTfClientId(5);
		ci.setTfClientName("John Doe");
		ci.setTfInterviews(new HashSet<>());
		ci.setTfPlacements(new HashSet<>());
	}

	@Test
	public void testClientInfoGetters() {
		//Creates a new Object 
		assertTrue(ci.getStats() instanceof  StatusInfo);
		assertTrue(ci.getTfAssociates() instanceof HashSet);
		
		assertTrue(ci.getTfClientName().equals("John Doe"));
		assertFalse(ci.getTfClientName().equals("John"));
		
		assertTrue(ci.getTfInterviews() instanceof HashSet);
		assertTrue(ci.getTfPlacements() instanceof HashSet);
			
	}
	
	@Test
	public void testClientInfoSetters() {
		
		ClientInfo ci2 = new ClientInfo();
		//sets new Value to check if the values were set properly
		ci2.setId(2);
		ci2.setStats(new StatusInfo("John"));
		ci2.setTfAssociates(new HashSet<AssociateInfo>());
		ci2.setTfClientId(6);
		ci2.setTfClientName("Jane Roe");
		ci2.setTfInterviews(new HashSet<>());
		ci2.setTfPlacements(new HashSet<>());
		
		
		assertTrue(ci2.getStats() instanceof  StatusInfo);
		assertTrue(ci2.getTfAssociates() instanceof HashSet);
		
		assertTrue(ci2.getTfClientName().equals("Jane Roe"));
		assertFalse(ci2.getTfClientName().equals("Jane"));
		
		assertTrue(ci2.getTfInterviews() instanceof HashSet);
		assertTrue(ci2.getTfPlacements() instanceof HashSet);
		
	}
	
	@Test
	public void testCompareTo() {
		
		//create an obj to compareTo
		ClientInfo ci3 = new ClientInfo();
		ci3.setTfClientId(5);
		
		//create an object to compareTo
		ClientInfo ci4 = new ClientInfo();
		ci4.setTfClientId(10);
		
		assertTrue(ci.compareTo(ci3) == 0);		//test for equal
		assertFalse(ci.compareTo(ci3) == 5);
		
		assertTrue(ci.compareTo(ci4) < 0);		//test for negative
		assertFalse(ci.compareTo(ci4) == 5);
		
		assertTrue(ci4.compareTo(ci) > 0);
		assertFalse(ci4.compareTo(ci) == 6);	//test for positive
	}


}
