package com.revature.services;

import java.io.IOException;
import java.util.Set;


import org.hibernate.HibernateException;

import com.revature.dao.InterviewDao;
import com.revature.dao.InterviewDaoHibernate;
import com.revature.model.InterviewInfo;
import com.revature.request.model.InterviewFromClient;

public class InterviewService{
    private InterviewDao interviewDao;

    public InterviewService() {
        this.interviewDao = new InterviewDaoHibernate();
    }

    /**
     * injectable dao for easier testing
     *
     * @param TechDao
     */
    public InterviewService(InterviewDao interviewDao) {
        this.interviewDao = interviewDao;
    }

    public Set<InterviewInfo> getAllInterviews() throws HibernateException, IOException{
  	
    	Set<InterviewInfo> interviews = interviewDao.getInterviewFromCache();
        if (interviews == null || interviews.isEmpty()) {
            interviewDao.cacheAllInterviews();
            return interviewDao.getInterviewFromCache();
        }

        return interviews;
	}

    
    public void addInterviewByAssociate(int associateId, InterviewFromClient ifc) {
    	interviewDao.addInterviewForAssociate(associateId, ifc);
    }

}