package com.revature.dao;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfInterview;
import com.revature.model.InterviewInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;

public class InterviewDaoHibernate implements InterviewDao {

	public Map<Integer, InterviewInfo> getAllInterviews() {
        Map<Integer, InterviewInfo> techs = new HashMap<Integer, InterviewInfo>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TfInterview> cq = cb.createQuery(TfInterview.class);
            Root<TfInterview> from = cq.from(TfInterview.class);
            CriteriaQuery<TfInterview> all = cq.select(from);
            Query<TfInterview> tq = session.createQuery(all);
            return createInterviewMap(tq.getResultList());
        } catch(Exception e) {
            e.printStackTrace();
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
	           // interviews = (Map<Integer, InterviewInfo>) query.getResultList();
	           // return interviews;
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
	public void addInterviewForAssociate(int associateid, InterviewInfo ii) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			TfInterview tfi = new TfInterview();
			tfi.setTfAssociate(session.get(TfAssociate.class, associateid));
			tfi.setTfInterviewDate(ii.getTfInterviewDate());
			tfi.setTfInterviewFeedback(ii.getTfInterviewFeedback());
			session.save(tfi);
        } catch (Exception e) {
            LogUtil.logger.error(e);
        }
	}
}