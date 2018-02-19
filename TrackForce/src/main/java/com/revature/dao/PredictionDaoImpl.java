package com.revature.dao;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.request.model.AssociatesWithTech;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class PredictionDaoImpl implements PredictionDao {
	
	public static void main(String[] args) {
		Date before = new Date(2018,12,30);
		Date after = new Date();
		System.out.println(new PredictionDaoImpl().getTotalAssociatesByTechBetweenDates(after, before));
	}

	
	@Override
	public List GET_COUNT_OF_ALL_BATCH_PER_TECH(int tech_id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
        	String sql = "select a.tf_batch_name,count(p.TF_ASSOCIATE_ID) as Associate FROM ADMIN.tf_batch a left "+
        			"join ADMIN.tf_batch_junction tf on tf.tf_batch_id = a.tf_batch_id left join ADMIN.tf_tech s on s.TF_TECH_ID = tf.TF_TECH_ID left join ADMIN.tf_associate p " +
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
        	String sql = "select a.tf_batch_name,count(p.TF_ASSOCIATE_ID) as Associate FROM ADMIN.tf_batch a left "+
        			"join ADMIN.tf_batch_junction tf on tf.tf_batch_id = a.tf_batch_id left join ADMIN.tf_tech s on s.TF_TECH_ID = tf.TF_TECH_ID left join ADMIN.tf_associate p " +
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
        	String sql = "Select a.tf_batch_name, count(p.TF_ASSOCIATE_ID) as Associate FROM ADMIN.tf_batch a left" + 
        			" join ADMIN.tf_batch_junction tf on tf.tf_batch_id = a.tf_batch_id left join ADMIN.tf_tech s on s.TF_TECH_ID = tf.TF_TECH_ID left join ADMIN.tf_associate p" + 
        			" on p.TF_BATCH_ID = tf.TF_BATCH_ID where a.tf_batch_end_date BETWEEN TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD')" + 
        			" and s.tf_tech_name =? group by a.TF_BATCH_NAME"; 
        			
		    Query query = session.createNativeQuery(sql);
		    query.setParameter(1, date1);
		    query.setParameter(2, date2);
		    query.setParameter(3, techname);
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
	    String sql = "select a.tf_batch_name, a.tf_batch_end_date, s.tf_tech_name from ADMIN.tf_batch a left " + 
	    		"join ADMIN.tf_batch_junction tf on tf.tf_batch_id = a.tf_batch_id left join ADMIN.tf_tech s on s.TF_TECH_ID "
	    		+ "= tf.TF_TECH_ID order by a.TF_batch_NAME asc";   		

		
		Query query = session.createNativeQuery(sql);
		List query_results = query.list();
		return query_results;
    } catch (NoResultException nre) {
        LogUtil.logger.error(nre);
      }
		return null;
	}
	
	public List getTotalAssociatesByTechBetweenDates(Date afterMe, Date beforeMe) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			// OLD QUERY WITH BATCH/TECH JUNCTION STUFF
//			String sql = "SELECT t.TF_TECH_NAME,count(*) FROM admin.tf_associate a " + 
//					"		  LEFT JOIN admin.tf_batch b ON a.tf_batch_id=b.tf_batch_id" + 
//					"		  LEFT JOIN admin.tf_batch_junction j ON j.tf_batch_id=b.tf_batch_id" + 
//					"		  LEFT JOIN admin.tf_tech t ON j.tf_tech_id = t.tf_tech_id" + 
//					"		  WHERE b.tf_batch_end_date >= TO_DATE(?, 'YYYY-MM-DD')" + 
//					"		  AND b.tf_batch_end_date <= TO_DATE(?, 'YYYY-MM-DD')" + 
//					"		  GROUP BY t.TF_TECH_NAME ORDER BY t.TF_TECH_NAME";
			
			String sql = "SELECT TF_CURRICULUM_NAME, count(*) FROM admin.tf_associate a" + 
					" LEFT JOIN admin.tf_batch b ON a.tf_batch_id=b.tf_batch_id" + 
					" LEFT JOIN admin.tf_curriculum c ON b.tf_curriculum_id=c.tf_curriculum_id" + 
					" WHERE b.tf_batch_end_date >= TO_DATE(?, 'YYYY-MM-DD')" + 
					" AND b.tf_batch_end_date <= TO_DATE(?, 'YYYY-MM-DD')" + 
					" GROUP BY TF_CURRICULUM_NAME ORDER BY TF_CURRICULUM_NAME";
			Query<AssociatesWithTech> query = session.createNativeQuery(sql);
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			String s1 = df.format(afterMe);
			query.setParameter(1, s1);
			String s2 = df.format(beforeMe);
		    query.setParameter(2, s2);
			List query_results = query.list();
			return query_results;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}