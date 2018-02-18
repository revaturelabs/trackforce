package com.revature.dao;

import java.io.IOException;

import java.util.Map;
import java.util.Set;

import com.revature.entity.TfInterview;
import com.revature.entity.TfTech;
import com.revature.model.InterviewInfo;

public interface InterviewDao {
	
	public Map<Integer, InterviewInfo> getInterviewsByAssociate(int associateId) throws IOException;
    public Map<Integer, InterviewInfo> getAllInterviews();
    public Set<InterviewInfo> getInterviewFromCache();
    public void addInterviewForAssociate(int associateid, InterviewInfo ii);
}