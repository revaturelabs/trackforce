package com.revature.test.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

import com.revature.model.AssociateInfo;
import com.revature.model.ClientInfo;
import com.revature.model.StatusInfo;

public class ClinetInfoTest {

	@Test
	public void testClientInfoGetters() {
		//Creates a new Object 
		ClientInfo ci = new ClientInfo();
		ci.setId(1);
		ci.setStats(new StatusInfo());
		ci.setTfAssociates(new HashSet<AssociateInfo>());
		ci.setTfClientId(5);
		ci.setTfClientName("John Doe");
		ci.setTfInterviews(new HashSet<>());
		ci.setTfPlacements(new HashSet<>());
		
	
		assertTrue(ci.getStats() instanceof  StatusInfo);
		assertTrue(ci.getTfAssociates() instanceof HashSet);
		assertTrue(ci.getTfClientName().equals("John Doe"));
		assertFalse(ci.getTfClientName().equals("John"));
		assertTrue(ci.getTfInterviews() instanceof HashSet);
		assertTrue(ci.getTfPlacements() instanceof HashSet);
			
	}
	
	@Test
	public void testClientInfoSetters() {
		
		//Creates a new Object 
		ClientInfo ci = new ClientInfo();
		ci.setId(1);
		ci.setStats(new StatusInfo());
		ci.setTfAssociates(new HashSet<AssociateInfo>());
		ci.setTfClientId(5);
		ci.setTfClientName("John Doe");
		ci.setTfInterviews(new HashSet<>());
		ci.setTfPlacements(new HashSet<>());
		
		//sets new Value to check if the values were set properly
		ci.setId(2);
		ci.setStats(new StatusInfo("John"));
		ci.setTfAssociates(new HashSet<AssociateInfo>());
		ci.setTfClientId(6);
		ci.setTfClientName("Jane Roe");
		ci.setTfInterviews(new HashSet<>());
		ci.setTfPlacements(new HashSet<>());
		
		
		assertTrue(ci.getStats() instanceof  StatusInfo);
		assertTrue(ci.getTfAssociates() instanceof HashSet);
		assertTrue(ci.getTfClientName().equals("Jane Roe"));
		assertFalse(ci.getTfClientName().equals("Jane"));
		assertTrue(ci.getTfInterviews() instanceof HashSet);
		assertTrue(ci.getTfPlacements() instanceof HashSet);
		
		
	}


}
