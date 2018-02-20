package com.revature.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.request.model.AssociatesWithTech;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class PredictionDaoImpl implements PredictionDao {

	@Override
	public List getTotalAssociatesByTechBetweenDates(Date afterMe, Date beforeMe)  {

		try(Session session = HibernateUtil.getSessionFactory().openSession()){

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
			LogUtil.logger.error(e);
		}
		return null;
	}

}
