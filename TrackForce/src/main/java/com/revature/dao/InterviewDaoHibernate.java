package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.entity.TfInterview;
import com.revature.utils.HibernateUtil;

public class InterviewDaoHibernate implements InterviewDao {

	@Override
	public List<TfInterview> getInterviewsByAssociate(int associateId) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from Tf_Interview i where i.tf_associate_id like :associateId", TfInterview.class).setParameter("associateId", associateId).getResultList());
	}

	@Override
	public List<TfInterview> getAllInterviews() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from Tf_Interview", TfInterview.class).setCacheable(true).getResultList());
	}

	@Override
	public boolean createInterview(TfInterview interview) {
		return HibernateUtil.saveToDB(interview);
	}

	@Override
	public boolean updateInterview(TfInterview interview) {
		Session session = null;
		Transaction t = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			
			TfInterview temp = session.get(TfInterview.class, interview.getTfAssociate());
			
			temp.setTfAssociateFeedback(interview.getTfAssociateFeedback());
			temp.setTfClient(interview.getTfClient());
			temp.setTfClientFeedback(interview.getTfClientFeedback());
			temp.setTfDateAssociateIssued(interview.getTfDateAssociateIssued());
			temp.setTfEndClient(interview.getTfEndClient());
			temp.setTfDateSalesIssued(interview.getTfDateAssociateIssued());
			temp.setTfFlagReason(interview.getTfFlagReason());
			temp.setTfInterviewDate(interview.getTfInterviewDate());
			temp.setTfInterviewType(interview.getTfInterviewType());
			temp.setTfIsClientFeedbackVisible(interview.getTfIsClientFeedbackVisible());
			temp.setTfIsInterviewFlagged(interview.getTfIsInterviewFlagged());
			temp.setTfJobDescription(interview.getTfJobDescription());
			temp.setTfQuestionGiven(interview.getTfQuestionGiven());
			temp.setTfWas24HRNotice(interview.getTfWas24HRNotice());
			
			session.update(temp);
			t.commit();
			System.out.println(interview.getTfAssociate() + " interview successfully updated");
			return true;
		} catch (HibernateException hbe) {
			if (t != null) {
				t.rollback();
				System.out.println("Transaction successfully rolled back");
			}
			hbe.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
				System.out.println("Session successfully closed: " + !session.isOpen());
			}
		}
		return false;
	}

	@Override
	public TfInterview getInterviewById(int interviewId) {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from Tf_Interview i where i.tf_interview_id like :interviewId", TfInterview.class).setParameter("interviewId", interviewId).getSingleResult());
	}
	

}
