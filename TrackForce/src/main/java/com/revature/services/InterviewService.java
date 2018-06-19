package com.revature.services;

import java.util.List;

import com.revature.dao.InterviewDao;
import com.revature.daoimpl.InterviewDaoImpl;
import com.revature.entity.TfInterview;

/**
 * @author Adam L. 
 * <p> </p>
 * @version v6.18.06.13
 *
 */
public class InterviewService {
	
	private InterviewDao dao = new InterviewDaoImpl();
	
	// public so it can be used for testing 
	public InterviewService() {};
	
	// Constructor so allow for Mockito
	public InterviewService(InterviewDao dao) {
		this.dao = dao;
	}
	/**
	 * 
	 * @author Adam L. 
	 * <p>Gets all the interviews from the database that match the given associate id</p>
	 * @version v6.18.06.13
	 * 
	 * @param associateId
	 * @return
	 */
	public List<TfInterview> getInterviewsByAssociate(int associateId){
		return dao.getInterviewsByAssociate(associateId);
	}
	
	/**
	 * @author Adam L. 
	 * <p>Gets all the interviews from the database</p>
	 * @version v6.18.06.13
	 * 
	 * @return
	 */
	public List<TfInterview> getAllInterviews(){
		return dao.getAllInterviews();
	}
	
	/**
	 * @author Adam L. 
	 * <p>Inserts / creates an interview in the database</p>
	 * @version v6.18.06.13
	 * 
	 * @param interview
	 * @return
	 */
	public boolean createInterview(TfInterview interview) {
		return dao.createInterview(interview);
	}
	
	/**
	 * @author Adam L. 
	 * <p>Updates the interview in the database.</p>
	 * 
	 * <p>Note: if you leave some fields empty in the TfInterview parameter, 
	 * 	it will be saved as such!</p>
	 * @version v6.18.06.13
	 * 
	 * @param interview
	 * @return
	 */
	public boolean updateInterview(TfInterview interview) {
		return dao.updateInterview(interview);
	}
	
	/**
	 * @author Adam L. 
	 * <p>Gets the interview based on it's id</p>
	 * @version v6.18.06.13
	 * 
	 * @param interviewId
	 * @return
	 */
	public TfInterview getInterviewById(int interviewId) {
		return dao.getInterviewById(interviewId);
	}
}