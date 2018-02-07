package com.revature.test.admin.testclasses;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import com.revature.test.admin.pom.AssociateListTab;
import com.revature.test.admin.pom.CreateUserTab;

public class AssociateListTest extends AdminSuite{
	
	
	@Test(priority = 1, enabled = true)
	//Click associtate tab on navbar 
	public void goToAssociateListTab() {
		try {
			
			System.out.println("Associate tab return: " + AssociateListTab.tab(wd));
			assertTrue(AssociateListTab.tab(wd));
		} catch (Throwable e) {
			fail("Error: Failed to go to Associate List tab");
			e.printStackTrace();
		}
	}
	
	//@Test(priority = 2, enabled = false)
	
	
	
	
	

}
