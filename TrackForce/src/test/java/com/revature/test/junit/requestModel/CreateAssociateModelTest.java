//package com.revature.test.junit.requestModel;
//
//import static org.junit.Assert.assertFalse;
//import static org.testng.Assert.assertTrue;
//
//import org.junit.Test;
//
//import com.revature.request.model.CreateAssociateModel;
//
///**
// * Tests to test basic getter and setter functionality for CreateAssociateModel
// * @author Jesse
// * @Since 6.18.06.11
// */
//public class CreateAssociateModelTest {
//
//	CreateAssociateModel cam = new CreateAssociateModel();
//	
//	@Test
//	public void test1() {
//		cam.setFname("Java");
//		assertTrue(cam.getFname().equals("Java"));
//		assertFalse(cam.getFname().equals("java"));
//	}
//	
//	@Test
//	public void test2() {
//		cam.setLname("8");
//		assertTrue(cam.getLname().equals("8"));
//		assertFalse(cam.getLname().equals("9"));
//	}
//	
//	@Test
//	public void test3() {
//		cam.setPassword("password");
//		assertTrue(cam.getPassword().equals("password"));
//		assertFalse(cam.getPassword().equals("PASSWORD"));
//	}
//	
//	@Test
//	public void test4() {
//		cam.setUsername("username");
//		assertTrue(cam.getUsername().equals("username"));
//		assertFalse(cam.getUsername().equals("USERNAME"));
//	}
//}
