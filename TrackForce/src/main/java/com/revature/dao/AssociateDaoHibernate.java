package com.revature.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfBatch;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;
import com.revature.model.AssociateInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
import com.revature.utils.PersistentStorage;

public class AssociateDaoHibernate implements AssociateDao {
	Session session;
    

    /**
     * Get an associate from the database given its id
     * Added the method without the session parameter
     * @return Returns an AssociateInfo object
     */
	@Override
    public AssociateInfo getAssociate(Integer id) {
       //created getAssociate to actually get data from the cache
        return PersistentStorage.getStorage().getAssociate(id);
        //want to write a method that gets from db if not in cache
        //then throw exception if not found in db
    }

    /**
     * Updates an associate's marketing status and client in the database.
     * Removed the session parameter and the throws clause.
     *
     * @param id              - The ID of the associate to update.
     * @param marketingStatus - A TfMarketingStatus object with the status to change the
     *                        associate to.
     * @param client          - A TfClient object with what client the associate will be mapped
     *                        to.
     * @return 
     */
	public void updateAssociates(AssociateInfo[] associates){
		Session session = null;
		Transaction t = null;
		List<TfAssociate> tfAssociateList = new ArrayList<TfAssociate>();
		try{
			session = HibernateUtil.getSession();
			for (AssociateInfo associate : associates) {
				TfAssociate tfAssociate = (TfAssociate) session.load(TfAssociate.class, associate.getId());
				tfAssociateList.add(tfAssociate);
			}
				TfClient client = (TfClient) session.load(TfClient.class, associates[0].getClid());
				TfMarketingStatus status = (TfMarketingStatus) session.load(TfMarketingStatus.class, associates[0].getMsid());
				TfBatch batch = (TfBatch) session.load(TfBatch.class, associates[0].getBid());
			t = session.beginTransaction();
			for(TfAssociate associate : tfAssociateList) {
				associate.setTfClient(client);
				associate.setTfMarketingStatus(status);
				associate.setTfBatch(batch);
			}
			session.saveOrUpdate(associates);
			t.commit();
			System.out.println(associates);
		} catch(HibernateException e) {
			t.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
    @Override
    public void updateAssociates(Integer[] ids, Integer marketingStatus, Integer clientid) {
    	List<TfAssociate> associates = null;
    	Session session = null;
		try{
			session = HibernateUtil.getSession();
			TfClient client = (TfClient) session.load(TfClient.class, clientid);
			TfMarketingStatus status = (TfMarketingStatus) session.load(TfMarketingStatus.class, marketingStatus);
			for(Integer id : ids) {
				associates.add((TfAssociate) session.load(TfAssociate.class, id));
			}
			Transaction t = session.beginTransaction();
			for(TfAssociate associate : associates) {
				associate.setTfClient(client);
				associate.setTfMarketingStatus(status);
			}

			session.saveOrUpdate(associates);
			t.commit();
			System.out.println(associates);
		} finally {
			session.close();
		}


    }

    @Override
    public Map<Integer, AssociateInfo> getAssociates() {
        List<TfAssociate> associatesEnt;

        Map<Integer, AssociateInfo> map = new HashMap<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        	CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TfAssociate> cq = cb.createQuery(TfAssociate.class);
            Root<TfAssociate> from = cq.from(TfAssociate.class);
            CriteriaQuery<TfAssociate> all = cq.select(from);
            Query<TfAssociate> tq = session.createQuery(all);

            associatesEnt = tq.getResultList();
            if (associatesEnt != null) {
                for (TfAssociate tfa : associatesEnt) {
                    map.put(tfa.getTfAssociateId(), Dao2DoMapper.map(tfa));
                    AssociateInfo.appendToMap(tfa.getTfMarketingStatus());
                }
            }
        }
        catch(HibernateException e) {
        	e.printStackTrace();
        }
        return map;
    }
    //This is Robin's implementation, previous implementation is getAssociates (should be right above)

    /**
     * Returns data from the cache
     * @return The cached data
     */
    public static Set<AssociateInfo> getAllAssociates() {
    	if(PersistentStorage.getStorage().getAssociates() == null)
    		cacheAllAssociates();
    	return PersistentStorage.getStorage().getAssociates();

    }

    /**
     * Creates an AssociateInfo map to store data onto the cache
     * @param associateList
     * @return Returns the Map object
     */
    public static Map<Integer, AssociateInfo> createAssociatesMap(List<TfAssociate> associateList) {
	    	Map<Integer, AssociateInfo> map = new HashMap<>();
	    	for(TfAssociate tfa : associateList) {
	    		map.put(tfa.getTfAssociateId(), Dao2DoMapper.map(tfa));
	    		AssociateInfo.appendToMap(tfa.getTfMarketingStatus());
	    	}
	    	return map;
    }
    /**
     * Retrieves all associate records from the database and places them into the cache
     *
     */
    public static void cacheAllAssociates() {
    	Session session = HibernateUtil.getSession();
    	try {
	    	List<TfAssociate> associates;
	    	CriteriaBuilder cb = session.getCriteriaBuilder();
	        CriteriaQuery<TfAssociate> cq = cb.createQuery(TfAssociate.class);
	        Root<TfAssociate> from = cq.from(TfAssociate.class);
	        CriteriaQuery<TfAssociate> all = cq.select(from);
	        Query<TfAssociate> tq = session.createQuery(all);
	        associates = tq.getResultList();
	    	Map<Integer, AssociateInfo> map = new HashMap<>();
	    	if(associates != null) {
	    		map = createAssociatesMap(associates);
	    	}
    	PersistentStorage.getStorage().setAssociates(map);
    	PersistentStorage.getStorage().setTotals(AssociateInfo.getTotals());
    	} finally {
    		session.close();
    	}
    }
}
