package com.revature.test.junit.model;

import static org.junit.Assert.*;
import org.junit.Test;

import com.revature.model.StatusInfo;

public class StatusInfoTest {

	StatusInfo status = new StatusInfo("Darren", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	
	
	@Test
	public void testGetName() {
		assertTrue(status.getName().equals("Darren"));
		assertFalse(status.getName().equals("Dan"));
	}
	
	@Test
	public void testSetName() {
		status.setName("Sam");
		assertTrue(status.getName().equals("Sam"));
		assertFalse(status.getName().equals("Dan"));
	}
	
	@Test
	public void testGetTrainingMapped() {
		assertTrue(status.getTrainingMapped() == 1);
		assertFalse(status.getTrainingMapped() == 7);
	}
	
	@Test
	public void testSetTraingingMapped() {
		status.setTrainingMapped(11);
		assertTrue(status.getTrainingMapped() == 11);
		assertFalse(status.getTrainingMapped() == 1);
	}
	
	@Test
	public void testGetTrainingUnmapped() {
		assertTrue(status.getTrainingUnmapped() == 2);
		assertFalse(status.getTrainingUnmapped() == 22);
	}
	
	@Test
	public void testSetTrainingUnmapped() {
		status.setTrainingUnmapped(22);
		assertTrue(status.getTrainingUnmapped() == 22);
		assertFalse(status.getTrainingUnmapped() == 2);
	}
	
	@Test
	public void testGetReservedMapped() {
		assertTrue(status.getReservedMapped() == 3);
		assertFalse(status.getReservedMapped() == 33);
	}
	
	@Test
	public void testSetReservedMapped() {
		status.setReservedMapped(33);
		assertTrue(status.getReservedMapped() == 33);
		assertFalse(status.getReservedMapped() == 3);
	}
	
	@Test
	public void testGetOpenUnmapped() {
		assertTrue(status.getOpenUnmapped() == 4);
		assertFalse(status.getOpenUnmapped() == 44);
	}
	
	@Test
	public void testSetOpenUnmapped() {
		status.setOpenUnmapped(44);
		assertTrue(status.getOpenUnmapped() == 44);
		assertFalse(status.getOpenUnmapped() == 4);
	}
	
	@Test
	public void testGetSelectMapped() {
		assertTrue(status.getSelectedMapped() == 5);
		assertFalse(status.getSelectedMapped() == 55);
	}
	
	@Test
	public void testSetSelectMapped() {
		status.setSelectedMapped(55);
		assertTrue(status.getSelectedMapped() == 55);
		assertFalse(status.getSelectedMapped() == 5);
	}
	
	@Test
	public void testGetSelectUnmapped() {
		assertTrue(status.getSelectedUnmapped() == 6);
		assertFalse(status.getSelectedUnmapped() == 66);
	}
	
	@Test
	public void testSetSelectUnmapped() {
		status.setSelectedUnmapped(66);
		assertTrue(status.getSelectedUnmapped() == 66);
		assertFalse(status.getSelectedUnmapped() == 6);
	}
	
	@Test
	public void testGetConfirmedMapped() {
		assertTrue(status.getConfirmedMapped() == 7);
		assertFalse(status.getConfirmedMapped() == 77);
	}
	
	@Test
	public void testSetConfirmedMapped() {
		status.setConfirmedMapped(77);
		assertTrue(status.getConfirmedMapped() == 77);
		assertFalse(status.getConfirmedMapped() == 7);
	}
	
	@Test
	public void testGetConfirmedUnmapped() {
		assertTrue(status.getConfirmedUnmapped() == 8);
		assertFalse(status.getConfirmedMapped() == 88);
	}
	
	@Test
	public void testSetConfirmedUnmapped() {
		status.setConfirmedUnmapped(88);
		assertTrue(status.getConfirmedUnmapped() == 88);
		assertFalse(status.getConfirmedUnmapped() == 8);
	}
	
	@Test
	public void testGetDeployedMapped() {
		assertTrue(status.getDeployedMapped() == 9);
		assertFalse(status.getDeployedMapped() == 99);
	}
	
	@Test
	public void testSetDeployedMapped() {
		status.setDeployedMapped(99);
		assertTrue(status.getDeployedMapped() == 99);
		assertFalse(status.getDeployedMapped() == 9);
	}
	
	@Test
	public void testGetDeployedUnmapped() {
		assertTrue(status.getDeployedUnmapped() == 10);
		assertFalse(status.getDeployedUnmapped() == 100);
	}
	
	@Test
	public void testSetDeployedUnmapped() {
		status.setDeployedUnmapped(100);
		assertTrue(status.getDeployedUnmapped() == 100);
		assertFalse(status.getDeployedUnmapped() == 10);
	}
	
	/*public StatusInfo(String name, int trainingMapped, int trainingUnmapped, int reservedMapped, int openUnmapped,
	int selectedMapped, int selectedUnmapped, int confirmedMapped, int confirmedUnmapped, int deployedMapped,
	int deployedUnmapped) */
	@Test
	public void testClear() {
		status.clear();
		assertTrue(status.getName().equals(""));
		assertTrue(status.getTrainingMapped() == 0);
		assertTrue(status.getTrainingUnmapped() == 0);
		assertTrue(status.getReservedMapped() == 0);
		assertTrue(status.getOpenUnmapped() == 0);
		assertTrue(status.getSelectedMapped() == 0);
		assertTrue(status.getSelectedUnmapped() == 0);
		assertTrue(status.getConfirmedMapped() == 0);
		assertTrue(status.getConfirmedUnmapped() == 0);
		assertTrue(status.getDeployedMapped() == 0);
		assertTrue(status.getDeployedUnmapped() == 0);
	}
}
