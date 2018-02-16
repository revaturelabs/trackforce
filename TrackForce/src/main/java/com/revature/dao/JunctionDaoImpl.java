package com.revature.dao;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.entity.TfClient;
import com.revature.entity.TfTech;
import com.revature.model.ClientInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class JunctionDaoImpl implements JunctionDao {
	
	public static void main(String[] args) {
		List l = new JunctionDaoImpl().GET_COUNT_OF_ALL_BATCH_PER_TECH(1);
		System.out.println(l);
	}

	
	@Override
	public List GET_COUNT_OF_ALL_BATCH_PER_TECH(int tech_id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        	String sql = "select a.tf_batch_name,count(p.TF_ASSOCIATE_ID) as Associate FROM ADMIN.tf_batch a left\r\n"+
        			"join ADMIN.tf_batch_junction tf on tf.tf_batch_id = a.tf_batch_id left join ADMIN.tf_tech s on s.TF_TECH_ID = tf.TF_TECH_ID left join ADMIN.tf_associate p" +
        			"on p.TF_BATCH_ID = tf.TF_BATCH_ID where s.tf_tech_id= ? group by a.TF_BATCH_NAME";
		    Query query = session.createNativeQuery(sql);
		    query.setParameter(1, tech_id);
        	List query_results = query.list();
            return query_results;
        } catch (NoResultException nre) {
            LogUtil.logger.error(nre);
        }
        return null;
    
		
	}

	@Override
	public List GET_COUNT_OF_ALL_BATCH_PER_TECH(String techname) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        	String sql = "select a.tf_batch_name,count(p.TF_ASSOCIATE_ID) as Associate FROM ADMIN.tf_batch a left\r\n"+
        			"join ADMIN.tf_batch_junction tf on tf.tf_batch_id = a.tf_batch_id left join ADMIN.tf_tech s on s.TF_TECH_ID = tf.TF_TECH_ID left join ADMIN.tf_associate p" +
        			"on p.TF_BATCH_ID = tf.TF_BATCH_ID where s.tf_tech_name= ? group by a.TF_BATCH_NAME";
		    Query query = session.createNativeQuery(sql);
		    query.setParameter(0, techname);
        	List query_results = query.list();
            return query_results;
        } catch (NoResultException nre) {
            LogUtil.logger.error(nre);
        }
        return null;
    
		
	}
	

	@Override
	public List GET_COUNT_OF_ALL_BATCH_PER_DATE(Date date1,Date date2, String techname) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        	String sql = "Select a.tf_batch_name, count(p.TF_ASSOCIATE_ID) as Associate FROM ADMIN.tf_batch a left \r\n" + 
        			"    join ADMIN.tf_batch_junction tf on tf.tf_batch_id = a.tf_batch_id left join ADMIN.tf_tech s on s.TF_TECH_ID = tf.TF_TECH_ID left join ADMIN.tf_associate p \r\n" + 
        			"    on p.TF_BATCH_ID = tf.TF_BATCH_ID where a.tf_batch_end_date BETWEEN TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD')\r\n" + 
        			"    and s.tf_tech_name =? group by a.TF_BATCH_NAME;" ; 
        			
		    Query query = session.createNativeQuery(sql);
		    query.setParameter(0, date1);
		    query.setParameter(1, date2);
		    query.setParameter(2, techname);
        	List query_results = query.list();
            return query_results;
        } catch (NoResultException nre) {
            LogUtil.logger.error(nre);
        }
        return null;
    		
	  }

	@Override
	public List GET_ALL_TECH_PER_BATCH() throws IOException {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
	    String sql = "select a.tf_batch_name, a.tf_batch_end_date, s.tf_tech_name from ADMIN.tf_batch a left\r\n" + 
	    		"join ADMIN.tf_batch_junction tf on tf.tf_batch_id = a.tf_batch_id left join ADMIN.tf_tech s on s.TF_TECH_ID "
	    		+ "= tf.TF_TECH_ID order by a.TF_batch_NAME asc;\r\n";   		
		
		Query query = session.createNativeQuery(sql);
		List query_results = query.list();
		return query_results;
    } catch (NoResultException nre) {
        LogUtil.logger.error(nre);
      }
		return null;
	} 
}
	

	