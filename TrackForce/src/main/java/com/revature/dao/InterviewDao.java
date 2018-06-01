package com.revature.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import com.revature.entity.TfInterview;
import com.revature.model.InterviewInfo;
import com.revature.request.model.InterviewFromClient;

public interface InterviewDao {
	
	Map<Integer, InterviewInfo> getInterviewsByAssociate(int associateId) throws IOException;
    Map<Integer, InterviewInfo> getAllInterviews();
    Set<InterviewInfo> getInterviewFromCache();
    boolean addInterviewForAssociate(int associateid, InterviewFromClient ifc);
    boolean createInterview(TfInterview parmInterview);
    boolean updateInterview(TfInterview parmInterview);
    void cacheAllInterviews();
    boolean isInterviewAtId(Integer parmInterviewId);
    TfInterview getInterviewById(Integer parmInterviewId);
    ArrayList<TfInterview> getAllInterviewsInArraylist();
    
}