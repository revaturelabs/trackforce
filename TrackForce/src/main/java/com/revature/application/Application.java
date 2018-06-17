package com.revature.application;

import java.util.ArrayList;

import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;
import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;


/**
 * 
 * @author Adam L. 
 * <p>Used for a few quick simple tests to ensure hibernate is making the calls it is supposed to</p>
 * @version.date v06.2018.06.13
 *
 */
@SuppressWarnings("unused")
public class Application {

	// You're probably thinking, why would you ever do this? Why not just just make the methods all static in the service class?
	// This is to allow for Mokito tests, which have problems with static methods
	// This is here for a reason! 
	// - Adam 06.2018.06.13
	static AssociateService associateService = new AssociateService();
	static BatchService batchService = new BatchService();
	static ClientService clientService = new ClientService();
	static CurriculumService curriculumService = new CurriculumService();
	static InterviewService interviewService = new InterviewService();
	static TrainerService trainerService = new TrainerService();
	static UserService userService = new UserService();
	
	public static void main(String[] args) {
		
//		String token = JWTService.createToken("TestAdmin", 1);
//		System.out.println(token);
		
//		TfAssociate a = associateService.getAssociate(0);
		
//		TfUser user = userService.getUser("TestAdmin");
//		System.out.println(user);
//		
//				
//		List<TfUser> users = userService.getAllUsers();
//		for(TfUser u : users)
//			System.out.println(u);
		
		TfTrainer t = new TfTrainer();
		t.setFirstName("Test");
		t.setLastName("Trainer");
		t.setId(1);
		t.setTfUser(new TfUser());
		t.setCoTrainer(new ArrayList<>());
		t.setPrimary(new ArrayList<>());
		System.out.println(trainerService.createTrainer(t));
		
		for (TfTrainer tt : trainerService.getAllTrainers()) {
			System.out.println(t);
		}
		
		
		// test get all interviews
//		List<TfInterview> interviews = interviewService.getAllInterviews();
//		for(TfInterview interview : interviews)
//			System.out.println(interview);
		
//		// test a single interview
//		Integer interviewId = 215;
//		TfInterview interview = interviewService.getInterviewById(interviewId);
//		System.out.println(interview);
		
		// test get all associates 
//		List<TfAssociate> associates = AssociateService.getAllAssociates();
//		for(TfAssociate a : associates)
//			System.out.println(a.getTfBatch().getTfBatchName());
		
		// test getting an associate
//		TfAssociate associate = AssociateService.getAssociate(associateid)
		
		// test getting all users 
		
		
//		TfUser newUser = new TfUser();
//		TfRole role = new TfRole();
//		role.setTfRoleId(1);
//		role.setTfRoleName("Admin");
//		
//		newUser.setTf_isApproved(1);
//		newUser.setTfRole(role);
//		newUser.setTfUserHashpassword("cool");
//		newUser.setTfUserUsername("neat");
//		
//		UserService.insertUser(newUser);
		
	}
}
