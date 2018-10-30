package com.revature.test.orm.entity;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfInterview;
import com.revature.entity.TfPlacement;

public class TfClientTest {
	
	Set<TfPlacement> placement = new HashSet<TfPlacement>();
	Set<TfAssociate> associate = new HashSet<TfAssociate>();
	Set<TfInterview> interview = new HashSet<TfInterview>();
	
	TfClient client1 = new TfClient(1, "clientName", placement, associate, interview);
	TfClient client2 = new TfClient(1, "clientName", placement, associate, interview);
	TfClient client = new TfClient();	
	
	@Test
	public void testClientIdGetSet() {
		client.setId(1);
		assertEquals((int)client.getId(), 1);
		assertNotEquals(client.getId(), 0);
	}
	
	@Test
	public void testClientNameGetSet() {
		client.setName("name");
		assertEquals(client.getName(), "name");
		assertNotEquals(client.getName(), "");
	}
	
	@Test
	public void testClientPlacementGetSet() {
		client.setPlacement(new HashSet<TfPlacement>());
		assertNotEquals(client.getPlacement(), null);
		assertTrue(client.getPlacement() instanceof Set);
	}
	
	@Test
	public void testClientAssociateGetSet() {
		client.setAssociate(new HashSet<TfAssociate>());
		assertNotEquals(client.getAssociate(), null);
		assertTrue(client.getAssociate() instanceof Set);
	}
	
	@Test
	public void testClientInterviewGetSet() {
		client.setInterview(new HashSet<TfInterview>());
		assertNotEquals(client.getInterview(), null);
		assertTrue(client.getInterview() instanceof Set);
	}
	
	@Test
	public void testClientEquivalence() {
		assertNotEquals(client1.hashCode(), client.hashCode());
		assertTrue(client1.equals(client2));
		assertFalse(client1.equals(new TfClient()));
	}
	
	@Test
	public void testClientToString() {
		assertEquals(client1.toString(), client2.toString());
		assertNotEquals(client1.toString(), client.toString());
	}
}
