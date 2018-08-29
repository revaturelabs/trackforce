package com.revature.test.junit.entity;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;

public class TfTrainerTest {

	/**
	 * Tests to test basic getter and setter functionality for TfTrainer
	 * 
	 * @author Jesse
	 * @Since 6.18.06.19
	 */

	TfTrainer trainer = new TfTrainer();
	TfTrainer trainer2 = new TfTrainer(10, "Jimbo", "Jimbo", new ArrayList<>(), new ArrayList<>());
	TfTrainer trainer3 = new TfTrainer(10, "Jimbo", "Jimbo", new ArrayList<>(), new ArrayList<>());

	@Test
	public void testTrainerCoTrainerGetSet() {
		trainer.setCoTrainer(new ArrayList<>());
		assertTrue(trainer.getCoTrainer() instanceof ArrayList);
	}

	@Test
	public void testTrainerFirstNameGetSet() {
		trainer.setFirstName("Jim");
		assertTrue(trainer.getFirstName().equals("Jim"));
		assertFalse(trainer.getFirstName().equals("jim"));
	}

	@Test
	public void testTrainerIDGetSet() {
		trainer.setId(7);
		assertTrue(trainer.getId() == 7);
		assertFalse(trainer.getId() == 6);
	}

	@Test
	public void testTrainerLastNameGetSet() {
		trainer.setLastName("Carl");
		assertTrue(trainer.getLastName().equals("Carl"));
		assertFalse(trainer.getLastName().equals("carl"));
	}

	@Test
	public void testTrainerPrimaryGetSet() {
		trainer.setPrimary(new ArrayList<>());
		assertTrue(trainer.getPrimary() instanceof ArrayList);
	}

	@Test
	public void testTrainerTfUserGetSet() {
		trainer.setTfUser(new TfUser());
		assertTrue(trainer.getTfUser() instanceof TfUser);
	}

	@Test
	public void testTrainerEquivalence() {
		assertTrue(trainer2.equals(trainer3));
		assertFalse(trainer2.equals(trainer));
	}

	@Test
	public void testTrainerHashCode() {
		assertEquals(trainer2.hashCode(), trainer3.hashCode());
		assertNotEquals(trainer2.hashCode(), trainer.hashCode());
	}
	
	@Test
	public void testTrainerToString() {
		assertEquals(trainer2.toString(), trainer3.toString());
		assertNotEquals(trainer2.toString(), trainer.toString());
	}
}
