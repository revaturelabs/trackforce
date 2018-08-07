package com.revature.dao;

import java.util.List;

import com.revature.entity.TfInterview;

public interface InterviewDao {
	
	public List<TfInterview> getInterviewsByAssociate(int associateId);
	public List<TfInterview> getAllInterviews();
	public boolean createInterview(TfInterview interview);
	public boolean updateInterview(	TfInterview interview);
	public TfInterview getInterviewById(int interviewId);
        
}