package com.revature.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;

import com.revature.dao.AssociateDao;
import com.revature.dao.AssociateDaoHibernate;
import com.revature.dao.InterviewDao;
import com.revature.dao.InterviewDaoHibernate;
import com.revature.dao.TechDao;
import com.revature.dao.TechDaoHibernate;
import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;
import com.revature.model.ClientInfo;
import com.revature.model.ClientMappedJSON;
import com.revature.model.CurriculumInfo;
import com.revature.model.InterviewInfo;
import com.revature.model.TechInfo;
import com.revature.utils.PersistentStorage;

public class InterviewService implements Service {

    private InterviewDao interviewDao;
    private AssociateDao associateDao;

    public InterviewService() {
        this.interviewDao = new InterviewDaoHibernate();
        //this.associateDao = new AssociateDaoHibernate();
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
  	
    	//Set<InterviewInfo> interviews = PersistentStorage.getStorage().getInterviews();
    	Set<InterviewInfo> interviews = interviewDao.getInterviewFromCache();
        if (interviews == null || interviews.isEmpty()) {
            execute();
            return interviewDao.getInterviewFromCache();
        }

        return interviews;
	}

    
    public void addInterviewByAssociate(int associateId, InterviewInfo ii) {
    	interviewDao.addInterviewForAssociate(associateId, ii);
    }

	@Override
	public synchronized void execute() throws IOException {
		Set<InterviewInfo> ti = interviewDao.getInterviewFromCache();
		if(ti == null || ti.isEmpty()) {
			PersistentStorage.getStorage().setInterviews(new InterviewDaoHibernate().getAllInterviews());
			//InterviewDaoHibernate.cacheAllInterviews();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Set<T> read(String...args) throws IOException {
		return (Set<T>) getAllInterviews();
	}
}