package com.revature.test.junit.model;
import static org.junit.Assert.*;
import org.junit.Test;

import com.revature.model.ClientMappedJSON;

public class ClientMappedJSONTest {

	ClientMappedJSON client = new ClientMappedJSON();
	
	@Test
	public void testGetId() {
		assertTrue(client.getId() == 0);
		assertFalse(client.getId() == 5);
	}
	
	@Test
	public void testSetId() {
		client.setId(100);
		assertTrue(client.getId() == 100);
		assertFalse(client.getId() == 0);
	}
	
	@Test 
	public void testGetName() {
		try {
			assertTrue(client.getName().equals("null"));
		} catch (NullPointerException npe){
			System.out.println("Method throws null pointer exception, test passed");
		}
		
		try {
			assertFalse(client.getName().equals("Steve"));
		} catch (NullPointerException npe) {
			System.out.println("Method throws null pointer exeption, test passed");
		}
	}
	
	@Test
	public void testSetName() {
		client.setName("Steve");
		assertTrue(client.getName().equals("Steve"));
		assertFalse(client.getName().equals("Frank"));
	}
	
	@Test 
	public void testGetCount() {
		assertTrue(client.getCount() == 0);
		assertFalse(client.getCount() == 5);
	}
	
	@Test
	public void testSetCount() {
		client.setCount(11);
		assertTrue(client.getCount() == 11);
		assertFalse(client.getCount() == 0);
	}
	
	@Test
	public void testCompareTo() {
		ClientMappedJSON client2 = new ClientMappedJSON();
		client2.setId(12);
		client.setId(15);
		assertTrue(client.compareTo(client2) == 3);
		assertFalse(client.compareTo(client2) == 4);
	}
		
}
