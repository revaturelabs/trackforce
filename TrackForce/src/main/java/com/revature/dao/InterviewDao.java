package com.revature.dao;
import java.util.List;
import com.revature.entity.TfInterview;

public interface InterviewDao {
	List<TfInterview> getInterviewsByAssociate(int associateId);
	
	List<TfInterview> getAllInterviews();
	
	boolean createInterview(TfInterview interview);
	
	boolean updateInterview(	TfInterview interview);
	
	TfInterview getInterviewById(int interviewId);
}