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
import com.revature.entity.TfTech;
import com.revature.model.TechInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;
public class TechDaoHibernate implements TechDao{
    
    /**
     * Get a batch from the database given its name.
     * 
     * @param batchName - The name of the batch to get information about
     * @throws IOException
     */
    public TfTech getTech(String techName) {
        TfTech tech = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TfTech> criteriaQuery = builder.createQuery(TfTech.class);
            Root<TfTech> root = criteriaQuery.from(TfTech.class);
            criteriaQuery.select(root).where(builder.equal(root.get("tfTechName"), techName));
            Query<TfTech> query = session.createQuery(criteriaQuery);
            tech = query.getSingleResult();
            return tech;
        } catch (NoResultException nre) {
            LogUtil.logger.error(nre);
        }
        return tech;
    }
    
    public TfTech getTechById(int id) {
        TfTech tech = null;
        try(Session s = HibernateUtil.getSessionFactory().openSession()) {
            tech = s.get(TfTech.class, id);
            return tech;
        } catch (NoResultException e) {
            LogUtil.logger.error(e);
        }
        return tech;
    }
    
    
    //2nd attempt at retrieving from DB into map
    
    /*@SuppressWarnings("deprecation")
    public Map<Integer, String> getAllTechnology() {
        
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        
        String queryString="select TF_TECH_ID, TF_TECH_NAME from TF_TECH ";
        @SuppressWarnings("unchecked")
        List<List<Object>> permission = session.createQuery(queryString)
              .setResultTransformer(Transformers.TO_LIST).list();
        //now you just expect two columns 
        HashMap<Integer,String> map = new HashMap<Integer,String>();
        for(List<Object> x: permission){ 
             map.put((Integer)x.get(0),(String)x.get(1));
            
                return map;
                }
        
        } catch (NoResultException nre) {
                LogUtil.logger.error(nre);
            }
            return map;
        
    }*/
    
    
    //retreive full list from cache
    
    public Map<Integer, TechInfo> getAllTechs() {
        Map<Integer, TechInfo> techs = new HashMap<Integer, TechInfo>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<TfTech> cq = cb.createQuery(TfTech.class);
            Root<TfTech> from = cq.from(TfTech.class);
            CriteriaQuery<TfTech> all = cq.select(from);
            Query<TfTech> tq = session.createQuery(all);
            return createTechMap(tq.getResultList());
        } catch(Exception e) {
            e.printStackTrace();
            LogUtil.logger.error(e);
        }
        return techs;
    }
    
    
    
    public Map<Integer, TechInfo> createTechMap(List<TfTech> techs){   // works in tandem with 'getAllTech()' method
        Map<Integer, TechInfo> map = new HashMap<>();
        if (techs != null) {
            for (TfTech tfa : techs) {
                map.put(tfa.getTfTechId(), Dao2DoMapper.map(tfa));
            }
        }
        return map;
    }

    public Set<TechInfo> getTechFromCache(){
		return PersistentStorage.getStorage().getTechs();
	}
	
	public TechInfo getTechFromCacheByID(int id) {
		return PersistentStorage.getStorage().getTechsAsMap().get(new Integer(id));
	}
	
	
	
	public static void cacheAllTechs(){
		PersistentStorage.getStorage().setTechs(new TechDaoHibernate().getAllTechs());			
	}

	
}