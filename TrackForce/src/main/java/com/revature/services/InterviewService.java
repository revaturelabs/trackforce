package com.revature.services;

import java.util.List;

import com.revature.dao.InterviewDao;
import com.revature.dao.InterviewDaoHibernate;
import com.revature.entity.TfInterview;

public class InterviewService {
	
	private static InterviewDao dao = new InterviewDaoHibernate();
	private InterviewService() {};
	
	public static List<TfInterview> getInterviewsByAssociate(int associateId){
		return dao.getInterviewsByAssociate(associateId);
	}
	public static List<TfInterview> getAllInterviews(){
		return dao.getAllInterviews();
	}
	public static boolean createInterview(TfInterview interview) {
		return dao.createInterview(interview);
	}
	public static boolean updateInterview(TfInterview interview) {
		return dao.updateInterview(interview);
	}
	public static TfInterview getInterviewById(int interviewId) {
		return dao.getInterviewById(interviewId);
	}
}