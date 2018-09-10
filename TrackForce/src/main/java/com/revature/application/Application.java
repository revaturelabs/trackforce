package com.revature.application;

import java.util.ArrayList;
import java.util.List;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfRole;
import com.revature.entity.TfTrainer;
import com.revature.entity.TfUser;
import com.revature.services.AssociateService;
import com.revature.services.BatchService;
import com.revature.services.ClientService;
import com.revature.services.CurriculumService;
import com.revature.services.InterviewService;
import com.revature.services.MarketingStatusService;
import com.revature.services.TrainerService;
import com.revature.services.UserService;
import com.revature.utils.PasswordStorage;
import java.util.HashSet;
import java.util.List;
import static com.revature.utils.HibernateUtil.saveToDB;
import static com.revature.utils.PasswordStorage.createHash;
import net.sf.ehcache.CacheManager;

/**
 * <p>
 * Use the main mehthod for testing only. This class is unused in "production".
 * Old testing code is commented out below the class for reference. Move it into
 * main if you want it to run.
 * </p>
 * 
 * @author Adam L.
 *         <p>
 *         Used for a few quick simple tests to ensure hibernate is making the
 *         calls it is supposed to
 *         </p>
 * @version v6.18.06.13
 */
@SuppressWarnings("unused")
public class Application {

	// You're probably thinking, why would you ever do this? Why not just just make
	// the methods all static in the service class?
	// This is to allow for Mockito tests, which have problems with static methods
	// This is here for a reason!
	// ENABLE THESE AS NEEDED
	// - Adam 06.18.06.13
	// static AssociateService associateService = new AssociateService();
	// static BatchService batchService = new BatchService();
	// static ClientService clientService = new ClientService();
	// static CurriculumService curriculumService = new CurriculumService();
	// static InterviewService interviewService = new InterviewService();
	// static TrainerService trainerService = new TrainerService();
	// static UserService userService = new UserService();
	// static MarketingStatusService marketingStatusService = new
	// MarketingStatusService();

	public static void main(String[] args) throws PasswordStorage.CannotPerformOperationException {

//		System.out.println(userService.getAllUsers());

//end class Application

//		TfUser u = new TfUser();
//		u.setRole(5);
//		u.setUsername("AssociateTest");
//		u.setPassword(createHash("AssociateTest"));
//		u.setTfRole(userService.getRole(5));
//		u.setIsApproved(1);
//		saveToDB(u);

//		List<TfUser> users = userService.getAllUsers();
//		
//		for(TfUser u : users)
//			System.out.println(u);

//		List<TfInterview> interviews = interviewService.getAllInterviews();
//		for(TfInterview i : interviews)
//			System.out.println(i);

//		TfTrainer t = new TfTrainer();
//		t.setFirstName("Ava");
//		t.setLastName("Trains");
//		t.setId(3);
//		t.setTfUser(u);
//		t.setCoTrainer(new ArrayList<>());
//		t.setPrimary(new ArrayList<>());

//		System.out.println(trainerService.createTrainer(t));

//		TfAssociate a = new TfAssociate();
////		a.setId(233);
//		a.setFirstName("Roland");
//		a.setLastName("Deschain");
//		a.setUser(userService.getUser("AssociateTest"));
//		//a.setClient(clientService.getClient(249));
//		a.setBatch(batchService.getBatchById(3));
//		a.setMarketingStatus(marketingStatusService.getMarketingStatusById(6));
//		a.setClientStartDate(new Timestamp(150000000L));
		// a.setPlacement(new HashSet<>());
		// a.setEndClient(clientService.getEndClient(249));
		// a.setInterview(new HashSet<>());

////		System.out.println(trainerService.createTrainer(t));
////
//		TfAssociate a = new TfAssociate();
//////		a.setId(233);
//		a.setFirstName("Roland");
//		a.setLastName("Deschain");
//		a.setUser(userService.getUser("AssociateTest"));
////		//a.setClient(clientService.getClient(249));
//		a.setBatch(batchService.getBatchById(3));
//		a.setMarketingStatus(marketingStatusService.getMarketingStatusById(6));
//		a.setClientStartDate(new Timestamp(150000000L));
//
//		//a.setPlacement(new HashSet<>());
////		a.setEndClient(clientService.getEndClient(249));
//		//a.setInterview(new HashSet<>());
//		associateService.createAssociate(a);

//		for (TfTrainer tt : trainerService.getAllTrainers()) {
//			System.out.println(t);

//		TfRole role = new TfRole();
//		role = userService.getRole(1);
//		
//		TfUser user = new TfUser();
//		user.setIsApproved(1);
//		user.setPassword("password");
//		user.setUsername("TestUsername");
//		user.setTfRole(role);
//		user.setRole(1);
//		
//		TfAssociate associate = new TfAssociate();
//		associate.setFirstName("RestAssured");
//		associate.setLastName("Associate");
//		associate.setUser(user);
//		
//		System.out.println(associateService.createAssociate(associate));}}

	}
}
//		TfInterview interview = new TfInterview();
//

//		TfAssociate a = new TfAssociate();
//		a = associateService.getAssociate(392);
//		
//		TfClient c = new TfClient();
//		a.setId(5);
//		
//		TfEndClient ec = new TfEndClient();
//		ec.setId(6);
//		
//		TfInterviewType it = new TfInterviewType();
//		it.setId(7);

//		TfAssociate a = associateService.getAssociate(1);
//		
//		TfClient c = clientService.getClient(5);
//		
//		TfEndClient ec = clientService.getEndClient(250);
//				
//		TfInterviewType it = interviewService.getInterviewById(1).getInterviewType();

//		interview.setAssociate(a);
//		interview.setClient(c);
//		interview.setEndClient(ec);
//		interview.setInterviewType(it);
//		interview.setInterviewDate(new Timestamp(152500500L));
//		interview.setAssociateFeedback("Interviewed well");
//		interview.setQuestionGiven("Start Date?");
//		interview.setClientFeedback("Strong Java knowledge");
//		interview.setJobDescription("SDET");
//		interview.setDateSalesIssued(new Timestamp(152500500L));
//		interview.setDateAssociateIssued(new Timestamp(152500500L));
//		interview.setWas24HRNotice(0);
//		interview.setIsInterviewFlagged(1);
//		interview.setFlagReason("Alert");
//		interview.setIsClientFeedbackVisible(1);
//		
//		System.out.println(interview);

//		for (TfTrainer all : trainerService.getAllTrainers()) {
//			System.out.println(all);
//		}
//		
//		TfTrainer t = trainerService.getTrainer(24);
//		
//		List<TfBatch> list = new ArrayList<>();
//		TfBatch b = new TfBatch();
//		b.setBatchName("Java 1805");
//		b.setId(99);
//		b.setTrainer(t);
//		list.add(b);
//		
//		t.setPrimary(list);
//		
//		System.out.println(trainerService.updateTrainer(t));

//		System.out.println(interviewService.updateInterview(interview));
//		System.out.println(interviewService.createInterview(interview));

//		TfUser user = new TfUser();
//		user.setId(950);
//		user.setIsApproved(1);
//		user.setPassword("password");
//		user.setRole(5);
//		user.setUsername("A Very Unique Name");
//		
//		System.out.println(userService.insertUser(user));

//		TfUser u = new TfUser();
//		u.setUsername("Bob");
//		u.setPassword("BOB");
//		
//		System.out.println(userService.submitCredentials(u));

//		System.out.println(interviewService.createInterview(interview));

//		associateService.updateAssociate(a);

// System.out.println(associateService.getAssociate(920));
//				

//		List<TfUser> users = userService.getAllUsers();
//		for(TfUser u : users)
//			System.out.println(u);

//		TfTrainer t = new TfTrainer();
//		t.setFirstName("Test");
//		t.setLastName("Trainer");
//		t.setId(1);
//		t.setTfUser(new TfUser());
//		t.setCoTrainer(new ArrayList<>());
//		t.setPrimary(new ArrayList<>());
//		System.out.println(trainerService.createTrainer(t));
//		
//		for (TfTrainer tt : trainerService.getAllTrainers()) {
//			System.out.println(t);
//		}
//		
//		TfUser u = new TfUser();
//		u.setId(925);
//		u.setRole(5);
//		u.setUsername("Testing");
//		u.setPassword("Testing");
//		u.setTfRole(new TfRole());
//		u.setIsApproved(1);
//		
//		TfAssociate aa = new TfAssociate();
//		a.setFirstName("Jimbo");
//		a.setLastName("Malone");
//		a.setUser(u);
//		a.setClient(new TfClient());
//		a.setBatch(new TfBatch());
//		a.setMarketingStatus(new TfMarketingStatus());
//		a.setClientStartDate(new Timestamp(150000000L));
//		a.setPlacement(new HashSet<>());
//		a.setEndClient(new TfEndClient());
//		a.setInterview(new HashSet<>());
//		
//		associateService.createAssociate(a);
//		
//		System.out.println(associateService.getAssociate(920));

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

//TfAssociate associate = new TfAssociate();
//associate.setId(1);
//associate.setFirstName("Greg");
//
//System.out.println(associateService.updateAssociate(associate));