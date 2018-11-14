package com.revature.test.orm.entity;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfCurriculum;
import com.revature.entity.TfEndClient;
import com.revature.entity.TfInterview;
import com.revature.entity.TfPlacement;

/**
 * Tests to test basic getter and setter functionality for TfEndClient
 * 
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfEndClientTest {
	Set<TfAssociate> associates = new HashSet<>();
	Set<TfInterview> interviews = new HashSet<>();
	Set<TfPlacement> placements = new HashSet<>();

	TfEndClient endClient1 = new TfEndClient(1, "name", associates, placements, interviews);
	TfEndClient endClient2 = new TfEndClient(1, "name", associates, placements, interviews);
	TfEndClient endClient = new TfEndClient();

	@Test
	public void testEndClientAssociateGetSet() {
		endClient.setAssociates(new HashSet<TfAssociate>());
		assertTrue(endClient.getAssociates() instanceof HashSet);
	}

	@Test
	public void testEndClientIDGetSet() {
		endClient.setId(1);
		assertTrue(endClient.getId() == 1);
		assertFalse(endClient.getId() == 2);
	}

	@Test
	public void testEndClientNameGetSet() {
		endClient.setName("Revature");
		assertTrue(endClient.getName().equals("Revature"));
		assertFalse(endClient.getName().equals("revature"));
	}

	@Test
	public void testEndClientInterviewsGetSet() {
		endClient.setInterviews(new HashSet<TfInterview>());
		assertTrue(endClient.getInterviews() instanceof HashSet);
	}

	@Test
	public void testEndClientPlacementGetSet() {
		endClient.setPlacements(new HashSet<TfPlacement>());
		assertTrue(endClient.getPlacements() instanceof HashSet);
	}
	
	//Have to hard code SerialID for now as it is private static,
	//always verify this is using the correct SerialID as defined
	//in the TfEndClient.java file
	@Test
	public void testEndClientSerialIDGet() {
		long sid = -8077675564245631804L;
		assertTrue(TfEndClient.getSerialversionuid() == sid);
	}
	
	@Test
	public void testEndClientEquivalence() {
		assertTrue(endClient1.equals(endClient2));
		assertFalse(endClient1.equals(new TfEndClient()));
	}
	
	@Test
	public void testEndClientHashCode() {
		assertEquals(endClient1.hashCode(),endClient2.hashCode());
		assertNotEquals(endClient1.hashCode(),new TfEndClient().hashCode());
	}
	
	@Test
	public void testEndClientToString() {
		assertEquals(endClient1.toString(), endClient2.toString());
		assertNotEquals(endClient1.toString(), endClient.toString());
	}
}
