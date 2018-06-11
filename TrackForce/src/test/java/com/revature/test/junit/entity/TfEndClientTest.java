package com.revature.test.junit.entity;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfEndClient;
import com.revature.entity.TfInterview;
import com.revature.entity.TfPlacement;

public class TfEndClientTest {

	TfEndClient endClient = new TfEndClient();
	
	@Test
	public void test1() {
		endClient.setTfAssociates(new HashSet<TfAssociate>());
		assertTrue(endClient.getTfAssociates() instanceof HashSet);
	}

	@Test
	public void test2() {
		endClient.setTfEndClientId(1);
		assertTrue(endClient.getTfEndClientId() == 1);
		assertFalse(endClient.getTfEndClientId() == 2);
	}
	
	@Test
	public void test3() {
		endClient.setTfEndClientName("Revature");
		assertTrue(endClient.getTfEndClientName().equals("Revature"));
		assertFalse(endClient.getTfEndClientName().equals("revature"));
	}
	
	@Test
	public void test4() {
		endClient.setTfInterviews(new HashSet<TfInterview>());
		assertTrue(endClient.getTfInterviews() instanceof HashSet);
	}
	
	@Test
	public void test5() {
		endClient.setTfPlacements(new HashSet<TfPlacement>());
		assertTrue(endClient.getTfPlacements() instanceof HashSet);
	}
}
