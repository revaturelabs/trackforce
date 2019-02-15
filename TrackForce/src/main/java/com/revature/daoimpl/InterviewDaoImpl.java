package com.revature.daoimpl;

import java.util.List;
import org.hibernate.Session;
import com.revature.dao.InterviewDao;
import com.revature.entity.TfInterview;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class InterviewDaoImpl implements InterviewDao {

	@Override
	public List<TfInterview> getInterviewsByAssociate(int associateId) {
		LogUtil.logger.trace("Hibernate Call to get Interviews by AssociateId: " + associateId);
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfInterview i where i.associate.id like :associateId", TfInterview.class)
				.setParameter("associateId", associateId).setCacheable(true).getResultList());
	}

	@Override
	public List<TfInterview> getAllInterviews() {
		LogUtil.logger.trace("Hibernate Call for ALL interviews.");
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfInterview", TfInterview.class).setCacheable(true).getResultList());
	}

	@Override
	public boolean createInterview(TfInterview interview) {
		LogUtil.logger.trace("Hibernate Call to create Interview: " + interview.getId());
		if (interview.getId() == null) {
			// System.out.println(getInterviewById(interview.getId()));
			return HibernateUtil.saveToDB(interview);
		}
		else {
			LogUtil.logger.error("Interview [" + getInterviewById(interview.getId()).getId() + "] already exists in the database.");
			return false;
		}
	}

	/*Nested if statements to prevent shortcircuit if statements from throwing a nullpointerexception on the hibernate transactions
	 * Reference AssociateDaoImpl.
	 * Currently updates all fields in TfInterview but as of, v1811, not all fields are sent as data from the front-end
	 * Current data received for updates are: Timestamp - interviewDate. String - associateFeedback. 
	 * 										String - questionGiven. String - clientFeedback. String - JobDescription.
	 */
	@Override
	public boolean updateInterview(TfInterview interview) {
		LogUtil.logger.trace("Hibernate Call to update Interview: " + interview.getId());
		return HibernateUtil.runHibernateTransaction((Session session, Object... args) -> {
			TfInterview temp = session.get(TfInterview.class, interview.getId());

			if (interview.getAssociate() != null) {
				temp.setAssociate(interview.getAssociate());
			}
			if (interview.getAssociateFeedback() != null) {
				if (!interview.getAssociateFeedback().equals("")) {
					temp.setAssociateFeedback(interview.getAssociateFeedback());
				}
			}
			if (interview.getClient() != null) {
				temp.setClient(interview.getClient());
			}
			if (interview.getClientFeedback() != null) {
				if (!interview.getClientFeedback().equals("")) {
					temp.setClientFeedback(interview.getClientFeedback());
				}
			}
			if (interview.getDateAssociateIssued() != null) {
				temp.setDateAssociateIssued(interview.getDateAssociateIssued());
			}
			if (interview.getEndClient() != null) {
				temp.setEndClient(interview.getEndClient());
			}
			if (interview.getDateAssociateIssued() != null) {
				temp.setDateSalesIssued(interview.getDateSalesIssued());
			}
			if (interview.getFlagReason() != null) {
				if (!interview.getFlagReason().equals("")) {
					temp.setFlagReason(interview.getFlagReason());
				}
			}
			if (interview.getInterviewDate() != null) {
				temp.setInterviewDate(interview.getInterviewDate());
			}
			if (interview.getInterviewType() != null) {
				temp.setInterviewType(interview.getInterviewType());
			}
			if (interview.getIsClientFeedbackVisible() != null) {
				temp.setIsClientFeedbackVisible(interview.getIsClientFeedbackVisible());
			}
			if (interview.getIsInterviewFlagged() != null) {
				temp.setIsInterviewFlagged(interview.getIsInterviewFlagged());
			}
			if (interview.getJobDescription() != null) {
				if (!interview.getJobDescription().equals("")) {
					temp.setJobDescription(interview.getJobDescription());
				}
			}
			if (interview.getQuestionGiven() != null) {
				if (!interview.getQuestionGiven().equals("")) {
					temp.setQuestionGiven(interview.getQuestionGiven());
				}
			}
			if (interview.getWas24HRNotice() != null) {
				temp.setWas24HRNotice(interview.getWas24HRNotice());
			}
			session.update(temp);
			return true;
		});
	}

	@Override
	public TfInterview getInterviewById(int interviewId) {
		LogUtil.logger.trace("Hibernate Call to get Interview by Id: " + interviewId);
		return HibernateUtil.runHibernate((Session session, Object... args) -> session
				.createQuery("from TfInterview i where i.id like :interviewId", TfInterview.class)
				.setParameter("interviewId", interviewId).setCacheable(true).getSingleResult());
	}
}