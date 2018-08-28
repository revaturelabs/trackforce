package com.revature.test.junit.entity;
import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;
import org.testng.annotations.Test;
import java.util.ArrayList;
import static org.testng.Assert.*;

/**
 * Tests to test basic getter and setter functionality for TfTrainer
 * @author Jesse
 * @Since 6.18.06.19
 */
public class TfTrainerTest {

	TfTrainer trainer = new TfTrainer();
	TfTrainer trainer2 = new TfTrainer(10, "Jimbo", "Jimbo", new ArrayList<>(), new ArrayList<>());
	TfTrainer trainer3 = new TfTrainer(10, "Jimbo", "Jimbo", new ArrayList<>(), new ArrayList<>());

	@Test
	public void test1() {
		trainer.setCoTrainer(new ArrayList<>());
		assertTrue(trainer.getCoTrainer() instanceof ArrayList);
	}

	@Test
	public void test2() {
		trainer.setFirstName("Jim");
		assertTrue(trainer.getFirstName().equals("Jim"));
		assertFalse(trainer.getFirstName().equals("jim"));
	}

	@Test
	public void test3() {
		trainer.setId(7);
		assertTrue(trainer.getId() == 7);
		assertFalse(trainer.getId() == 6);
	}

	@Test
	public void test4() {
		trainer.setLastName("Carl");
		assertTrue(trainer.getLastName().equals("Carl"));
		assertFalse(trainer.getLastName().equals("carl"));
	}

	@Test
	public void test5() {
		trainer.setPrimary(new ArrayList<>());
		assertTrue(trainer.getPrimary() instanceof ArrayList);
	}

	@Test
	public void test6() {
		trainer.setTfUser(new TfUser());
		assertTrue(trainer.getTfUser() instanceof TfUser);
	}

	@Test
	public void test7() {
		assertTrue(trainer2.equals(trainer3));
		assertFalse(trainer2.equals(trainer));
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void test8() {
		assertEquals(trainer2.hashCode(), trainer3.hashCode());
		assertNotEquals(trainer2.hashCode(), trainer.hashCode());
	}
}
