package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfInterview;
import com.revature.entity.TfInterviewType;
import com.revature.model.InterviewInfo;
import com.revature.request.model.InterviewFromClient;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;

public class InterviewDaoHibernate implements InterviewDao {

	public Map<Integer, InterviewInfo> getAllInterviews() {
        Map<Integer, InterviewInfo> techs = new HashMap<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TfInterview> cq = cb.createQuery(TfInterview.class);
            Root<TfInterview> from = cq.from(TfInterview.class);
            CriteriaQuery<TfInterview> all = cq.select(from);
            Query<TfInterview> tq = session.createQuery(all);
            return createInterviewMap(tq.getResultList());
        } catch(Exception e) {

            LogUtil.logger.error(e);
        }
        return techs;
    }

	@Override
	public Map<Integer, InterviewInfo> getInterviewsByAssociate(int associateId) throws IOException {
		 Map<Integer, InterviewInfo> interviews = null;
	        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
	            CriteriaBuilder builder = session.getCriteriaBuilder();
	            CriteriaQuery<TfInterview> criteriaQuery = builder.createQuery(TfInterview.class);
	            Root<TfInterview> root = criteriaQuery.from(TfInterview.class);
	            criteriaQuery.select(root).where(builder.equal(root.get("tfAssociate"), associateId));
	            Query<TfInterview> query = session.createQuery(criteriaQuery);
	            return createInterviewMap(query.getResultList());
	        } catch (NoResultException nre) {
	            LogUtil.logger.error(nre);
	        }
	        return interviews;
	}

	public Map<Integer, InterviewInfo> createInterviewMap(List<TfInterview> interviews){   // works in tandem with 'getInterviewByAssociate()' method
        Map<Integer, InterviewInfo> map = new HashMap<>();
        if (interviews != null) {
            for (TfInterview tfi : interviews) {
                map.put(tfi.getTfInterviewId(), Dao2DoMapper.map(tfi));
            }
        }
        return map;
    }

	public Set<InterviewInfo> getInterviewFromCache(){
		return PersistentStorage.getStorage().getInterviews();
	}

	public InterviewInfo getInterviewFromCacheByID(int id) {
		return PersistentStorage.getStorage().getInterviewsAsMap().get(new Integer(id));
	}



	public void cacheAllInterviews(){
		PersistentStorage.getStorage().setInterviews(new InterviewDaoHibernate().getAllInterviews());
	}

	@Override
	public void addInterviewForAssociate(int associateid, InterviewFromClient ifc) {
		Transaction t1 = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			t1 = session.beginTransaction();
			TfInterview tfi = new TfInterview();
			String sql = "SELECT MAX(tf_interview_id) FROM admin.tf_interview";
			Query q = session.createNativeQuery(sql);
			BigDecimal max = (BigDecimal) q.getSingleResult();
			Integer id = Integer.parseInt(max.toBigInteger().toString()) + 1;
			tfi.setTfInterviewId(id);
			tfi.setTfAssociate(session.get(TfAssociate.class, associateid));
			tfi.setTfInterviewDate(Timestamp.from(new Date(ifc.getInterviewDate()).toInstant()));
			tfi.setTfInterviewFeedback(ifc.getInterviewFeedback());
			tfi.setTfClient(session.get(TfClient.class, ifc.getClientId()));
			tfi.setTfInterviewType(session.load(TfInterviewType.class, ifc.getTypeId()));
			session.saveOrUpdate(tfi);
			t1.commit();
        } catch (NullPointerException e) {
            LogUtil.logger.error(e);
            e.printStackTrace();
            if (t1 != null) {
							t1.rollback();
						}
        } catch (Exception e) {
            LogUtil.logger.error(e);
            e.printStackTrace();
            if (t1 != null) {
							t1.rollback();
						}
        }
	}
}
