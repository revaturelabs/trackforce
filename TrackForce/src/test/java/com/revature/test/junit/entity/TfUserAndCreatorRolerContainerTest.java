package com.revature.test.junit.entity;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.revature.entity.TfUser;
import com.revature.entity.TfUserAndCreatorRoleContainer;

public class TfUserAndCreatorRolerContainerTest {
	
	TfUserAndCreatorRoleContainer tfuc1 = new TfUserAndCreatorRoleContainer(new TfUser(), 1);
	TfUserAndCreatorRoleContainer tfuc2 = new TfUserAndCreatorRoleContainer(new TfUser(), 1);
	TfUserAndCreatorRoleContainer tfuc = new TfUserAndCreatorRoleContainer();
	
  @Test
  public void testUserAndCreatorUserGetSet() {
	  tfuc.setUser(new TfUser());
	  assertTrue(tfuc.getUser() instanceof TfUser);
  }
  
  @Test
  public void testUserAndCreatorCreatorRoleGetSet() {
	  tfuc.setCreatorRole(2);
	  assertEquals(tfuc.getCreatorRole(), 2);
	  assertNotEquals(tfuc.getCreatorRole(), 1);
  }
  
  @Test
  public void testUserAndCreatorToString() {
	  assertEquals(tfuc1.toString(), tfuc2.toString());
	  assertNotEquals(tfuc.toString(), tfuc1.toString());
  }
}
