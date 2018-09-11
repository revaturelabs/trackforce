package com.revature.test.TestNG;

import org.testng.annotations.Test;

import com.revature.resources.AssociateResource;
import com.revature.resources.BatchResource;
import com.revature.resources.ClientResource;
import com.revature.resources.CurriculumResource;
import com.revature.resources.InitResource;
import com.revature.resources.InterviewResource;
import com.revature.resources.TrainerResource;
import com.revature.resources.UserResource;

public class ClassInstantiationTests {


	
	@Test
	public void instantiateAssociateResourceTest() {
		assert(new AssociateResource() != null);
	}
	
	@Test
	public void instantiateBatchResourceTest() {
		assert(new BatchResource() != null);
	}
	
	@Test
	public void instantiateClientResourceTest() {
		assert(new ClientResource() != null);
	}
	
	@Test
	public void instantiateCurriculumResourceTest() {
		assert(new CurriculumResource() != null);
	}
	
	@Test
	public void instantiateInitResourceTest() {
		assert(new InitResource() != null);
	}
	
	@Test
	public void instantiateInterviewResourceTest() {
		assert(new InterviewResource() != null);
	}
	
	@Test
	public void instantiateTrainerResourceTest() {
		assert(new TrainerResource() != null);
	}
	
	@Test
	public void instantiateUserResourceTest() {
		assert(new UserResource() != null);
	}
	
}
