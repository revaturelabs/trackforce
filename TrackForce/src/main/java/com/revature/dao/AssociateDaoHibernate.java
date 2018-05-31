package com.revature.dao;

import java.sql.Timestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static com.revature.utils.LogUtil.logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfInterview;
import com.revature.entity.TfMarketingStatus;
import com.revature.model.AssociateInfo;
import com.revature.model.InterviewInfo;
import com.revature.request.model.AssociateFromClient;
import com.revature.resources.BatchResource;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
import com.revature.utils.PersistentStorage;

public class AssociateDaoHibernate implements AssociateDao {
	
	private static AssociateDaoHibernate instance;
//somthing
// FOR ANDy
	private AssociateDaoHibernate() {
		logger.info("AssociateDao created.");
	}
	
	/** Singleton
	 * 
	 * @return an instance of AssociateDaoHibernate
	 */
	public static AssociateDaoHibernate getInstance() {
		if (instance == null) {
			instance = new AssociateDaoHibernate();
		}
		return instance;
	}
	
    /**
     * Get an associate from the database given its id
     * Added the method without the session parameter
     * @return Returns an AssociateInfo object
     */
	@Override
    public AssociateInfo getAssociate(Integer id) {
       //created getAssociate to actually get data from the cache
        return PersistentStorage.getStorage().getAssociateAsMap().get(id);
        //want to write a method that gets from db if not in cache
        //then throw exception if not found in db
    }

	@Override
    public AssociateInfo getAssociateFromDB(Integer id) {
        try(Session session = HibernateUtil.getSession()) {
            TfAssociate tfa = session.load(TfAssociate.class, id);
            AssociateInfo ai = Dao2DoMapper.map(tfa);
            return  ai;
        }
        catch(HibernateException e) {
        	logger.error(e);
        }
        return null;
    }

	@Override
    public void updateAssociates(List<Integer> ids, Integer marketingStatus, Integer clientid) {
    	List<TfAssociate> associates = new ArrayList<>();
    	Session session = HibernateUtil.getSession();
    	Transaction t = session.beginTransaction();
		try{
			for(Integer id : ids)
				associates.add((TfAssociate) session.load(TfAssociate.class, id));
			t = session.beginTransaction();
			for(TfAssociate associate : associates) {
				if(clientid != 0) {
					if(clientid != -1) {
						TfClient client = (TfClient) session.load(TfClient.class, clientid);
						associate.setTfClient(client);
					}
					else
						associate.setTfClient(null);
				}
				if(marketingStatus != 0) {
					TfMarketingStatus status = new MarketingStatusDaoHibernate().getMarketingStatus(marketingStatus);
					associate.setTfMarketingStatus(status);
				}
				session.saveOrUpdate(associate);
			}
			t.commit();
			PersistentStorage.getStorage().setAssociates(createAssociatesMap(associates));
		} catch (HibernateException e) {
			 logger.error(e);
			t.rollback();
		}
    }

	public void updateAssociate(AssociateFromClient afc) {
		Transaction t = null;
		try(Session session = HibernateUtil.getSession()) {
			t = session.beginTransaction();
			TfAssociate tfAssociate = (TfAssociate) session.load(TfAssociate.class, afc.getId());
			TfClient client = (TfClient) session.load(TfClient.class, afc.getClientId());
			TfMarketingStatus status = (TfMarketingStatus) session.load(TfMarketingStatus.class, afc.getMkStatus());
			tfAssociate.setTfClient(client);
			tfAssociate.setTfMarketingStatus(status);
			tfAssociate.setTfClientStartDate(Timestamp.from(Instant.ofEpochSecond(afc.getStartDateUnixTime())));
			logger.debug(tfAssociate);
			logger.debug(afc);
			PersistentStorage.getStorage().updateAssociate(afc.getId(),afc.getClientId(),afc.getMkStatus(),afc.getStartDateUnixTime());
			session.saveOrUpdate(tfAssociate);
			t.commit();
		} catch(HibernateException e) {
			if (t != null) {
				t.rollback();
			}
			logger.error(e);
		}
	}

    @Override
    public Map<Integer, AssociateInfo> getAssociates() {
        Map<Integer, AssociateInfo> map = new HashMap<>();
        try(Session session = HibernateUtil.getSession()) {
        	CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TfAssociate> cq = cb.createQuery(TfAssociate.class);
            Root<TfAssociate> from = cq.from(TfAssociate.class);
            CriteriaQuery<TfAssociate> all = cq.select(from);
            Query<TfAssociate> tq = session.createQuery(all);

            return createAssociatesMap(tq.getResultList());
        }
        catch(HibernateException e) {
        	logger.error(e);
        }
        return map;
    }
    //This is Robin's implementation, previous implementation is getAssociates (should be right above)

    /**
     * Returns data from the cache
     * @return The cached data
     */
    public Set<AssociateInfo> getAllAssociates() {
    	if(PersistentStorage.getStorage().getAssociates() == null)
    		cacheAllAssociates();
    	return PersistentStorage.getStorage().getAssociates();

    }

    /**
     * Creates an AssociateInfo map to store data onto the cache
     * @param associateList
     * @return Returns the Map object
     */
    public Map<Integer, AssociateInfo> createAssociatesMap(List<TfAssociate> associateList) {
	    	Map<Integer, AssociateInfo> map = new HashMap<>();
	    	for(TfAssociate tfa : associateList) {
	    		map.put(tfa.getTfAssociateId(), Dao2DoMapper.map(tfa));
	    	}
	    	return map;
    }
    /**
     * Retrieves all associate records from the database and places them into the cache
     *
     */
    public void cacheAllAssociates() {
    	Session session = HibernateUtil.getSession();
    	try {
	    	Map<Integer, AssociateInfo> map = getAssociates();
	    	PersistentStorage.getStorage().setAssociates(map);
    	} finally {
    		session.close();
    	}
    }


	@Override
	public Set<InterviewInfo> getInterviewsByAssociate(Integer associateId) {
		Set<TfInterview> setint = null;
		Set<InterviewInfo> setInfo = null;
    	try(Session session = HibernateUtil.getSession()) {
	    	TfAssociate tfa = session.load(TfAssociate.class, associateId);
	    	setint = tfa.getTfInterviews();
	    	Iterator<TfInterview> it = setint.iterator();
			setInfo = new HashSet<>();
			while (it.hasNext()) {
				InterviewInfo ii = Dao2DoMapper.map(it.next());
				setInfo.add(ii);
			}
    	} catch (Exception e) {
    		logger.error(e);
    	}
		return setInfo;
	}

}
