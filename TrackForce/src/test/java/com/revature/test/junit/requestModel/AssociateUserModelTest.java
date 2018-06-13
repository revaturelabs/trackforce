//package com.revature.test.junit.requestModel;
//
//import static org.junit.Assert.assertFalse;
//import static org.testng.Assert.assertTrue;
//
//import org.junit.Test;
//
//import com.revature.request.model.AssociateUserModel;
//
///**
// * Tests to test basic getter and setter functionality for AssociateUserModel
// * @author Jesse
// * @Since 6.18.06.11
// */
//public class AssociateUserModelTest {
//
//	AssociateUserModel aum = new AssociateUserModel();
//
//	@Test
//	public void test1() {
//		aum.setfname("Java");
//		assertTrue(aum.getfname().equals("Java"));
//		assertFalse(aum.getfname().equals("JAVA"));
//	}
//
//	@Test
//	public void test2() {
//		aum.setlname("8");
//		assertTrue(aum.getlname().equals("8"));
//		assertFalse(aum.getlname().equals("Eight"));
//	}
//
//	@Test
//	public void test3() {
//		aum.setPassword("password");
//		assertTrue(aum.getPassword().equals("password"));
//		assertFalse(aum.getPassword().equals("PASSWORD"));
//	}
//
//	@Test
//	public void test4() {
//		aum.setUsername("Username");
//		assertTrue(aum.getUsername().equals("Username"));
//		assertFalse(aum.getUsername().equals("username"));
//	}
//}
