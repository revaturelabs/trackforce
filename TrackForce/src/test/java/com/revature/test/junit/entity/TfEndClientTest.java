package com.revature.test.junit.entity;

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
	
	@Test
	public void test6() {
		assertTrue(endClient1.equals(endClient2));
		assertFalse(endClient1.equals(new TfEndClient()));
	}
	@Test public void test7() {
		assertEquals(endClient1.hashCode(),endClient2.hashCode());
		assertNotEquals(endClient1.hashCode(),new TfEndClient().hashCode());
	}
}
