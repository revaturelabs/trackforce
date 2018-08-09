package com.revature.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.InterviewDao;
import com.revature.entity.TfInterview;
import com.revature.utils.HibernateUtil;

public class InterviewDaoImpl implements InterviewDao {

	@Override
	public List<TfInterview> getInterviewsByAssociate(int associateId) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfInterview i where i.associate.id like :associateId", TfInterview.class).setParameter("associateId", associateId).getResultList());
	}
	

	@Override
	public List<TfInterview> getAllInterviews() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfInterview", TfInterview.class).getResultList());
	}

	@Override
	public boolean createInterview(TfInterview interview) {
//		 HibernateUtil.saveToDB(interview.getAssociate());
//		 HibernateUtil.saveToDB(interview.getClient());
		return HibernateUtil.saveToDB(interview);
	}

	@Override
	public boolean updateInterview(TfInterview interview) {
		return HibernateUtil.runHibernateTransaction((Session session, Object ... args) -> {
			TfInterview temp = session.get(TfInterview.class, interview.getId());

			temp.setAssociate(interview.getAssociate());
			temp.setAssociateFeedback(interview.getAssociateFeedback());
			temp.setClient(interview.getClient());
			temp.setClientFeedback(interview.getClientFeedback());
			temp.setDateAssociateIssued(interview.getDateAssociateIssued());
			temp.setEndClient(interview.getEndClient());
			temp.setDateSalesIssued(interview.getDateAssociateIssued());
			temp.setFlagReason(interview.getFlagReason());
			temp.setInterviewDate(interview.getInterviewDate());
			temp.setInterviewType(interview.getInterviewType());
			temp.setIsClientFeedbackVisible(interview.getIsClientFeedbackVisible());
			temp.setIsInterviewFlagged(interview.getIsInterviewFlagged());
			temp.setJobDescription(interview.getJobDescription());
			temp.setQuestionGiven(interview.getQuestionGiven());
			temp.setWas24HRNotice(interview.getWas24HRNotice());

			session.update(temp);
			return true;
		});
	}

	@Override
	public TfInterview getInterviewById(int interviewId) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfInterview i where i.id like :interviewId", TfInterview.class).setParameter("interviewId", interviewId).getSingleResult());
	}
	

}
