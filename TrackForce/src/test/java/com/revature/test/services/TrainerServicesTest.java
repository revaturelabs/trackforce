package com.revature.test.services;

import static org.testng.AssertJUnit.assertNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.daoimpl.TrainerDaoImpl;
import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;
import com.revature.services.TrainerService;
import com.revature.test.utils.Log;

public class TrainerServicesTest {

	private TrainerService service;
	private Properties props;

	@BeforeClass
	public void initialize() {
		service = new TrainerService();
		props = new Properties();

		try {
			FileInputStream propFile = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\database_entries.properties");
			props.load(propFile);
			propFile.close();
		} catch (FileNotFoundException e) {
			Log.Log.error(e.getMessage());
		} catch (IOException e) {
			Log.Log.error(e.getMessage());
		}
	}

	@Test
	public void testTrainerGetById() {

		TfTrainer trainer = service.getTrainer(Integer.parseInt(props.getProperty("trainer_Id")));
		TfTrainer notATrainer = service.getTrainer(100);
		assertNull(notATrainer);
		assertEquals(trainer.getTfUser().getId(),6);
//		assertEquals(trainer.getLastName(), props.getProperty("trainerLastName"));
	}

	@Test
	public void testTrainerGetByUserId() {
		TfTrainer trainer = service.getTrainerByUserId(Integer.parseInt(props.getProperty("trainerUser_Id")));

		assertEquals(trainer.getId(), 0);
//		assertEquals(trainer.getLastName(), props.getProperty("trainerLastName"));
	}

	@Test
	public void testTrainerGetAll() {

		List<TfTrainer> allTrainers = service.getAllTrainers();

		assertNotNull(allTrainers);
		assertFalse(allTrainers.isEmpty());
	}

	/*
	 * Test currently when it tries to get the trainer's first name Throws a
	 * NullPointerException
	 */
	@Test(enabled=false)//disabled until able to properly stub dao insert method.
	public void testTrainerCreate() {

		TfTrainer createTrainer = new TfTrainer();
		TfUser user = new TfUser();
		createTrainer.setId(Integer.parseInt(props.getProperty("createTrainer_Id")));
		createTrainer.setFirstName(props.getProperty("createTrainer_firstName"));
		createTrainer.setLastName(props.getProperty("createTrainer_lastName"));
		createTrainer.setTfUser(user);
		user.setPassword("p");
		service.createTrainer(createTrainer);

//		assertEquals(service.getTrainer(Integer.parseInt(props.getProperty("createTrainer_Id"))).getFirstName(), 
//				props.getProperty("createTrainer_firstName"));
		assertEquals(service.getTrainer(Integer.parseInt(props.getProperty("createTrainer_Id"))).getLastName(),
				props.getProperty("createTrainer_lastName"));
	}

	/*
	 * Appears that the getTrainer returns null during the undo change Throws a
	 * NullPointerException
	 */
	@Test
	public void testTrainerUpdate() {

		TfTrainer trainerUpdate = service.getTrainer(Integer.parseInt(props.getProperty("updateTrainer_Id")));
		System.out.println(trainerUpdate.getFirstName());
		trainerUpdate.setFirstName(props.getProperty("updateTrainer_firstName"));
		trainerUpdate.setLastName(props.getProperty("updateTrainer_lastName"));
		service.updateTrainer(trainerUpdate);

		assertEquals(service.getTrainer(Integer.parseInt(props.getProperty("updateTrainer_Id"))).getFirstName(),
				props.getProperty("updateTrainer_firstName"));
		assertEquals(service.getTrainer(Integer.parseInt(props.getProperty("updateTrainer_Id"))).getLastName(),
				props.getProperty("updateTrainer_lastName"));

		// undo the change
		TfTrainer redo = service.getTrainer(Integer.parseInt(props.getProperty("updateTrainer_Id")));
		redo.setFirstName(props.getProperty("redo_first"));
		redo.setLastName(props.getProperty("redo_last"));
		service.updateTrainer(redo);
	}
}