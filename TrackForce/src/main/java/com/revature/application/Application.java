package com.revature.application;

import com.revature.entity.*;
import com.revature.services.*;
import com.revature.utils.PasswordStorage;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;

import static com.revature.utils.HibernateUtil.saveToDB;
import static com.revature.utils.PasswordStorage.createHash;


/**
 * 
 * @author Adam L. 
 * <p>Used for a few quick simple tests to ensure hibernate is making the calls it is supposed to</p>
 * @version.date v6.18.06.13
 *
 */
@SuppressWarnings("unused")
public class Application {

	// You're probably thinking, why would you ever do this? Why not just just make the methods all static in the service class?
	// This is to allow for Mockito tests, which have problems with static methods
	// This is here for a reason! 
	// - Adam 06.18.06.13
	static AssociateService associateService = new AssociateService();
	static BatchService batchService = new BatchService();
	static ClientService clientService = new ClientService();
	static CurriculumService curriculumService = new CurriculumService();
	static InterviewService interviewService = new InterviewService();
	static TrainerService trainerService = new TrainerService();
	static UserService userService = new UserService();
	static MarketingStatusService marketingStatusService = new MarketingStatusService();

	public static void main(String[] args) throws PasswordStorage.CannotPerformOperationException {
//
//		TfUser u = new TfUser();
//		u.setRole(5);
//		u.setUsername("AssociateTest");
//		u.setPassword(createHash("AssociateTest"));
//		u.setTfRole(userService.getRole(5));
//		u.setIsApproved(1);
//		saveToDB(u);

//		TfTrainer t = new TfTrainer();
//		t.setFirstName("Ava");
//		t.setLastName("Trains");
//		t.setId(3);
//		t.setTfUser(u);
//		t.setCoTrainer(new ArrayList<>());
//		t.setPrimary(new ArrayList<>());
//		System.out.println(trainerService.createTrainer(t));

		TfAssociate a = new TfAssociate();
//		a.setId(233);
		a.setFirstName("Roland");
		a.setLastName("Deschain");
		a.setUser(userService.getUser("AssociateTest"));
		//a.setClient(clientService.getClient(249));
		a.setBatch(batchService.getBatchById(3));
		a.setMarketingStatus(marketingStatusService.getMarketingStatusById(6));
		a.setClientStartDate(new Timestamp(150000000L));
		//a.setPlacement(new HashSet<>());
		//a.setEndClient(clientService.getEndClient(249));
		//a.setInterview(new HashSet<>());

//		for (TfTrainer tt : trainerService.getAllTrainers()) {
//			System.out.println(t);
//		}




//		associateService.updateAssociate(a);
		associateService.createAssociate(a);

		//System.out.println(associateService.getAssociate(920));
//				
		
		
		
		
		
//		List<TfUser> users = userService.getAllUsers();
//		for(TfUser u : users)
//			System.out.println(u);
		
		
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
		
		// make a user to be used by max in testing login
		
		
		
	}
}
