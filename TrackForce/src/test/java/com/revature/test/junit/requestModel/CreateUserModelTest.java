//package com.revature.test.junit.requestModel;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.fail;
//import static org.testng.Assert.assertTrue;
//
//import org.junit.Test;
//
//import com.revature.request.model.CreateUserModel;
//
///**
// * Tests to test basic getter and setter functionality for CreateUserModel
// * @author Jesse
// * @Since 6.18.06.11
// */
//public class CreateUserModelTest {
//
//	CreateUserModel model = new CreateUserModel();
//
//	@Test
//	public void test1() {
//		model.setPassword("password");
//		assertTrue(model.getPassword().equals("password"));
//		assertFalse(model.getPassword().equals("PASSWORD"));
//	}
//
//	@Test
//	public void test2() {
//		model.setRole(100);
//		assertTrue(model.getRole() == 100);
//		assertFalse(model.getRole() == 99);
//	}
//
//	@Test
//	public void test3() {
//		model.setUsername("username");
//		assertTrue(model.getUsername().equals("username"));
//		assertFalse(model.getUsername().equals("USERNAME"));
//	}
//}
