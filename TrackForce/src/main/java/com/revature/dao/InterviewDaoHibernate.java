package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static com.revature.utils.LogUtil.logger;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfEndClient;
import com.revature.entity.TfInterview;
import com.revature.entity.TfInterviewType;
import com.revature.model.InterviewInfo;
import com.revature.request.model.InterviewFromClient;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;

public class InterviewDaoHibernate implements InterviewDao {
	
	static private final String tablename = "TF_INTERVIEW";

	public Map<Integer, InterviewInfo> getAllInterviews() {
		Map<Integer, InterviewInfo> techs = new HashMap<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<TfInterview> cq = cb.createQuery(TfInterview.class);
			Root<TfInterview> from = cq.from(TfInterview.class);
			CriteriaQuery<TfInterview> all = cq.select(from);
			Query<TfInterview> tq = session.createQuery(all);
			return createInterviewMap(tq.getResultList());
		} catch (Exception e) {

			LogUtil.logger.error(e);
		} finally {
			session.close();
		}
		return techs;
	}

	@Override
	public Map<Integer, InterviewInfo> getInterviewsByAssociate(int associateId) throws IOException {
		Map<Integer, InterviewInfo> interviews = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TfInterview> criteriaQuery = builder.createQuery(TfInterview.class);
			Root<TfInterview> root = criteriaQuery.from(TfInterview.class);
			criteriaQuery.select(root).where(builder.equal(root.get("tfAssociate"), associateId));
			Query<TfInterview> query = session.createQuery(criteriaQuery);
			return createInterviewMap(query.getResultList());
		} catch (NoResultException nre) {
			LogUtil.logger.error(nre);
		} finally {
			session.close();
		}
		return interviews;
	}

	public Map<Integer, InterviewInfo> createInterviewMap(List<TfInterview> interviews) { // works in tandem with
																							// 'getInterviewByAssociate()'
																							// method
		Map<Integer, InterviewInfo> map = new HashMap<>();
		if (interviews != null) {
			for (TfInterview tfi : interviews) {
				map.put(tfi.getTfInterviewId(), Dao2DoMapper.map(tfi));
			}
		}
		return map;
	}

	public Set<InterviewInfo> getInterviewFromCache() {
		return PersistentStorage.getStorage().getInterviews();
	}

	public InterviewInfo getInterviewFromCacheByID(int id) {
		return PersistentStorage.getStorage().getInterviewsAsMap().get(id);
	}

	public void cacheAllInterviews() {
		PersistentStorage.getStorage().setInterviews(new InterviewDaoHibernate().getAllInterviews());
	}

	@Override
	public void addInterviewForAssociate(int associateid, InterviewFromClient ifc) {
		Transaction t1 = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			t1 = session.beginTransaction();
			TfInterview tfi = new TfInterview();
			String sql = "SELECT MAX(tf_interview_id) FROM admin.tf_interview";
			@SuppressWarnings("rawtypes")
			Query q = session.createNativeQuery(sql);
			BigDecimal max = (BigDecimal) q.getSingleResult();
			Integer id = Integer.parseInt(max.toBigInteger().toString()) + 1;
			tfi.setTfInterviewId(id);
			tfi.setTfAssociate(session.get(TfAssociate.class, associateid));

			// tfi.setTfInterviewFeedback(ifc.getInterviewFeedback());
			tfi.setTfClient(session.get(TfClient.class, ifc.getClientId()));
			tfi.setTfInterviewType(session.load(TfInterviewType.class, ifc.getTypeId()));
			tfi.setTfInterviewDate(Timestamp.from(new Date(ifc.getInterviewDate()).toInstant()));

			session.saveOrUpdate(tfi);
			t1.commit();
		} catch (NullPointerException e) {
			LogUtil.logger.error(e);
			if (t1 != null) {
				t1.rollback();
			}
		} catch (Exception e) {
			LogUtil.logger.error(e);
			if (t1 != null) {
				t1.rollback();
			}
		} finally {
			session.close();
		}
	}

	/**
	 * Send a Interview object and it gets put in the TF_Interview table
	 * 
	 * @Edboi
	 */

	@Override
	public boolean createInterview(TfInterview parmInterview) {
		Transaction dbTransaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			dbTransaction = session.beginTransaction();
			TfInterview databaseRow = new TfInterview();
			// --I think this gets the largest Interview ID and add one to that for the new
			// Object
			String sql = "SELECT MAX(tf_interview_id) FROM admin.tf_interview";
			Query<?> q = session.createNativeQuery(sql);
			BigDecimal max = (BigDecimal) q.getSingleResult();
			Integer id = Integer.parseInt(max.toBigInteger().toString()) + 1;
			// --End getting new Id
			databaseRow.setTfInterviewId(id);

			// ---Start Getting Objects from other Tables
			databaseRow.setTfAssociate(session.get(TfAssociate.class, parmInterview.getTfAssociate()));
			databaseRow.setTfClient(session.get(TfClient.class, parmInterview.getTfClient()));
			databaseRow.setTfEndClient(session.get(TfEndClient.class, parmInterview.getTfEndClient()));
			databaseRow.setTfInterviewType(session.load(TfInterviewType.class, parmInterview.getTfInterviewType()));
			// ---End Geting Objects From Other Tables

			databaseRow.setTfInterviewDate(parmInterview.getTfInterviewDate());
			// --1804 Fields
			databaseRow.setTfJobDescription(parmInterview.getTfJobDescription());
			databaseRow.setTfDateSalesIssued(parmInterview.getTfDateSalesIssued());
			databaseRow.setTfDateAssociateIssued(parmInterview.getTfDateAssociateIssued());
			databaseRow.setTfIsInterviewFlagged(parmInterview.getTfIsInterviewFlagged());
			databaseRow.setTfFlagReason(parmInterview.getTfFlagReason());
			databaseRow.setTfIsClientFeedbackVisible(parmInterview.getTfIsClientFeedbackVisible());
			//Additional requirements
			databaseRow.setTfWas24HRNotice(parmInterview.getTfWas24HRNotice());
			databaseRow.setTfQuestionGiven(parmInterview.getTfQuestionGiven());

			session.saveOrUpdate(databaseRow);
			dbTransaction.commit();
			return true;
		} catch (NullPointerException e) {
			LogUtil.logger.error(e);
			if (dbTransaction != null) {
				dbTransaction.rollback();
			}
		} catch (Exception e) {
			LogUtil.logger.error(e);
			if (dbTransaction != null) {
				dbTransaction.rollback();
			}
		} finally {
			session.close();
		}
		return false;
	}

	/**
	 * Send a Interview object and it gets updated in the TF_Interview table
	 * 
	 * @Edboi
	 */

	@Override
	public boolean updateInterview(TfInterview parmInterview) {
		Transaction dbTransaction = null;
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			dbTransaction = session.beginTransaction();
			TfInterview tobeUpdatedInteview = getInterviewById(parmInterview.getTfInterviewId());
			
			//The idea is you send it a new Intview Object only put the feilds want to change and leave the rest null
			//Then just compare each feild and if not null update that fe
			if (parmInterview.getTfInterviewId() != null)
				tobeUpdatedInteview.setTfInterviewId(parmInterview.getTfInterviewId());
			if (parmInterview.getTfAssociate() != null)
				tobeUpdatedInteview.setTfAssociate(session.get(TfAssociate.class, parmInterview.getTfAssociate()));
			if (parmInterview.getTfClient() != null)
				tobeUpdatedInteview.setTfClient(session.get(TfClient.class, parmInterview.getTfClient()));
			if (parmInterview.getTfEndClient() != null)
				tobeUpdatedInteview.setTfEndClient(session.get(TfEndClient.class, parmInterview.getTfEndClient()));
			if (parmInterview.getTfInterviewType() != null)
				tobeUpdatedInteview.setTfInterviewType(session.load(TfInterviewType.class, parmInterview.getTfInterviewType()));

			if (parmInterview.getTfInterviewDate() != null)
				tobeUpdatedInteview.setTfInterviewDate(parmInterview.getTfInterviewDate());
			if (parmInterview.getTfJobDescription() != null)
				tobeUpdatedInteview.setTfInterviewDate(parmInterview.getTfInterviewDate());
			if (parmInterview.getTfDateSalesIssued() != null)
				tobeUpdatedInteview.setTfDateSalesIssued(parmInterview.getTfDateSalesIssued());
			if (parmInterview.getTfDateAssociateIssued() != null)
				tobeUpdatedInteview.setTfDateAssociateIssued(parmInterview.getTfDateAssociateIssued());
			if (parmInterview.getTfIsInterviewFlagged() != null)
				tobeUpdatedInteview.setTfIsInterviewFlagged(parmInterview.getTfIsInterviewFlagged());
			if (parmInterview.getTfFlagReason() != null)
				tobeUpdatedInteview.setTfFlagReason(parmInterview.getTfFlagReason());
			if (parmInterview.getTfIsClientFeedbackVisible() != null)
				tobeUpdatedInteview.setTfIsClientFeedbackVisible(parmInterview.getTfIsClientFeedbackVisible());
			//Additional requirements
			if (parmInterview.getTfWas24HRNotice() != null)
				tobeUpdatedInteview.setTfWas24HRNotice(parmInterview.getTfWas24HRNotice());
			if (parmInterview.getTfQuestionGiven() != null)
				tobeUpdatedInteview.setTfQuestionGiven(parmInterview.getTfQuestionGiven());
			session.saveOrUpdate(tobeUpdatedInteview);
			dbTransaction.commit();
			return true;

		} catch (NullPointerException e) {
			LogUtil.logger.error(e);
			if (dbTransaction != null) {
				dbTransaction.rollback();
			}
		} catch (Exception e) {
			LogUtil.logger.error(e);
			if (dbTransaction != null) {
				dbTransaction.rollback();
			}
		} finally {
			session.close();
		}
		return false;
	}

	/**
	 * Send a Integer of a Id of a Interview and get a boolean if it really there.
	 * 
	 * @Edboi
	 */
	@Override
	public boolean isInterviewAtId(Integer parmInterviewId) {
		Session session = null;
		TfInterview temp = new TfInterview();
		try {
            session = HibernateUtil.getSessionFactory().openSession();
            temp =  (TfInterview) session.get(TfInterview.class, parmInterviewId);
            Hibernate.initialize(temp); // I don't think I need this, but it doesn't break anything soo...
           // try and get an Interviw matching the Id, If I get one it will not be null simple stuff
            if (temp != null) {
                return true;
             }else
            	 return false;
		} catch (NullPointerException e ) {
			LogUtil.logger.error(e);
		
		} catch (Exception e) {
			LogUtil.logger.error(e);
		
		} finally {
			session.close();
		}
		return false;
	}
	/**
	 * Send ID of Interview
	 * get an Interview at that ID
	 * 
	 * @Edboi
	 */
	@Override
	public TfInterview getInterviewById(Integer parmInterviewId) {
		Session session = null;
		TfInterview temp = new TfInterview();
		try {
            session = HibernateUtil.getSessionFactory().openSession();
            temp =  (TfInterview) session.get(TfInterview.class, parmInterviewId);
            Hibernate.initialize(temp); // I don't think I need this, but it doesn't break anything soo...
          
            if (temp != null) {
                return temp;
             }else
            	 return null;
		} catch (NullPointerException e ) {
			LogUtil.logger.error(e);
		
		} catch (Exception e) {
			LogUtil.logger.error(e);
		
		} finally {
			session.close();
		}
		return null;
	}
	/**
	 * Ask for all the Interview in the Table and you shall receive 
	 * 
	 * 
	 * @Edboi
	 * Side note I wanted to user //session.createCriteria(MyEntity.class).list(); 
	 * but is deprecated and thus had use the way below
	 */
	
	@Override
	public ArrayList<TfInterview> getAllInterviewsInArraylist() {
		Session session = null;
		TfInterview temp = new TfInterview();
		ArrayList<TfInterview> arrayListOfInterviews = new ArrayList<TfInterview>();
		try {
            session = HibernateUtil.getSessionFactory().openSession();
            //Get Criteria Builder
            CriteriaBuilder builder = session.getCriteriaBuilder();

            //Create Criteria
            CriteriaQuery<TfInterview> criteria = builder.createQuery(TfInterview.class);
            Root<TfInterview> contactRoot = criteria.from(TfInterview.class);
            criteria.select(contactRoot);

            //Use criteria to query with session to fetch all contacts
            arrayListOfInterviews = (ArrayList<TfInterview>) session.createQuery(criteria).getResultList();
            
            return arrayListOfInterviews;
      
		} catch (NullPointerException e ) {
			LogUtil.logger.error(e);
		
		} catch (Exception e) {
			LogUtil.logger.error(e);
		
		} finally {
			session.close();
		}
		return null;
	}
	
	


	

}
