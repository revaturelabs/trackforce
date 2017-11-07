package com.revature.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.revature.entity.TfAssociate;
import com.revature.utils.HibernateUtil;

public class HomeDaoImpl implements HomeDao{

	@Override
	public List<TfAssociate> getAllTfAssociates() {
		Session session = HibernateUtil.getSession().openSession();
		CriteriaQuery<TfAssociate> cq = session.getCriteriaBuilder().createQuery(TfAssociate.class);
		cq.from(TfAssociate.class);
		List<TfAssociate> associates = session.createQuery(cq).getResultList();

		session.close();
		return associates;
	}

}
