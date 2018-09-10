package com.revature.test.dao;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.revature.daoimpl.TrainerDaoImpl;
import com.revature.entity.TfTrainer;

/**
 * 
 * These tests depend on the full SQL Script on github
 *	
 */

public class TrainerDAOTest {
  
  @Test
  public void getAllTrainersDoesNotReturnEmptyList() {
	  Assert.assertNotEquals(0, new TrainerDaoImpl().getAllTrainers().size());
  }
  
  @Test
  public void getTrainerByIDHasCorrectFirstName() {
	Assert.assertEquals("Trainer1", new TrainerDaoImpl().getTrainer(1).getFirstName());
  }

  @Test
  public void getTrainerByIDHasCorrectLastName() {
	Assert.assertEquals("Trainer1", new TrainerDaoImpl().getTrainer(1).getLastName());
  }

  @Test
  public void getTrainerByIDHasCorrectUserID() {
	Assert.assertEquals(7, new TrainerDaoImpl().getTrainer(1).getTfUser().getId());
  }
  
  @Test
  public void canGetTrainerByUserID() {
	Assert.assertEquals(1, new TrainerDaoImpl().getTrainerByUserId(7).getId());
  }
  
  @Test
  public void updateTrainerReturnsTrue() {
	TfTrainer tf = new TrainerDaoImpl().getTrainer(94);
	tf.setFirstName("updatedFirstName");
	tf.setLastName("updatedLastName");
	Assert.assertEquals(true, new TrainerDaoImpl().updateTrainer(tf));
  }
  
  @Test(dependsOnMethods = "updateTrainerReturnsTrue")
  public void updateTrainerHasUpdatedFirstName() {
	TfTrainer tf = new TrainerDaoImpl().getTrainer(94);
	Assert.assertEquals("updatedFirstName", tf.getFirstName());
  }
  
  @Test(dependsOnMethods = "updateTrainerReturnsTrue")
  public void updateTrainerHasUpdatedLastName() {
	TfTrainer tf = new TrainerDaoImpl().getTrainer(94);
	Assert.assertEquals("updatedLastName", tf.getLastName());
  }
  
  @Test(expectedExceptions = NullPointerException.class)
  public void createDuplicateTrainerThrowsException() {
	TfTrainer tf = new TrainerDaoImpl().getTrainer(94);
	new TrainerDaoImpl().createTrainer(tf);
  }
  
}
