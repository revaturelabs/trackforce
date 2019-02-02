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
		LogUtil.logger.trace("Hibernate Call to get Interviews by AssociateId: " + associateId );
		return HibernateUtil.runHibernate(
				(Session session, Object ... args) ->
				session.createQuery("from TfInterview i where i.associate.id like :associateId", TfInterview.class)
				.setParameter("associateId", associateId)
				.setCacheable(true)
				.getResultList());
	}
	

	@Override
	public List<TfInterview> getAllInterviews() {
		LogUtil.logger.trace("Hibernate Call for ALL interviews.");
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfInterview", TfInterview.class).setCacheable(true).getResultList());
	}

	@Override
	public boolean createInterview(TfInterview interview) {
		LogUtil.logger.trace("Hibernate Call to create Interview: " + interview.getId());
		if(interview.getId()!=null && getInterviewById(interview.getId())==null) {
			System.out.println(getInterviewById(interview.getId()));
			return HibernateUtil.saveToDB(interview);
		}
		LogUtil.logger.error("Interview ["+interview.getId()+"] was invalid or already exists in the database.");
		return false;
	}

	@Override
	public boolean updateInterview(TfInterview interview) {
		LogUtil.logger.trace("Hibernate Call to update Interview: " + interview.getId());
		return HibernateUtil.runHibernateTransaction((Session session, Object ... args) -> {
			TfInterview temp = session.get(TfInterview.class, interview.getId());
		
			temp.setAssociate(interview.getAssociate());
			temp.setAssociateFeedback(interview.getAssociateFeedback());
			temp.setClient(interview.getClient());
			temp.setClientFeedback(interview.getClientFeedback());
			temp.setDateAssociateIssued(interview.getDateAssociateIssued());
			temp.setEndClient(interview.getEndClient());
			temp.setDateSalesIssued(interview.getDateSalesIssued());
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
		LogUtil.logger.trace("Hibernate Call to get Interview by Id: " + interviewId);
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfInterview i where i.id like :interviewId", TfInterview.class)
		.setParameter("interviewId", interviewId).setCacheable(true).getSingleResult());
	}
}