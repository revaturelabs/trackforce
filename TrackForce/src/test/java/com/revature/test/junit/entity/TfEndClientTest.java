package com.revature.test.junit.entity;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfEndClient;
import com.revature.entity.TfInterview;
import com.revature.entity.TfPlacement;

/**
 * Tests to test basic getter and setter functionality for TfEndClient
 * @author Jesse
 * @Since 6.18.06.11
 */
public class TfEndClientTest {

	TfEndClient endClient = new TfEndClient();
	
	@Test
	public void test1() {
		endClient.setAssociates(new HashSet<TfAssociate>());
		assertTrue(endClient.getAssociates() instanceof HashSet);
	}

	@Test
	public void test2() {
		endClient.setId(1);
		assertTrue(endClient.getId() == 1);
		assertFalse(endClient.getId() == 2);
	}
	
	@Test
	public void test3() {
		endClient.setName("Revature");
		assertTrue(endClient.getName().equals("Revature"));
		assertFalse(endClient.getName().equals("revature"));
	}
	
	@Test
	public void test4() {
		endClient.setInterviews(new HashSet<TfInterview>());
		assertTrue(endClient.getInterviews() instanceof HashSet);
	}
	
	@Test
	public void test5() {
		endClient.setPlacements(new HashSet<TfPlacement>());
		assertTrue(endClient.getPlacements() instanceof HashSet);
	}
}
