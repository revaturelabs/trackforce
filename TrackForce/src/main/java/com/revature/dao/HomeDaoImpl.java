package com.revature.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.revature.entity.TfAssociate;
import com.revature.utils.HibernateUtil;

public class HomeDaoImpl implements HomeDao {

	@Override
	public List<TfAssociate> getAllTfAssociates() {
		Session session = HibernateUtil.getSession().openSession();
		CriteriaQuery<TfAssociate> cq = session.getCriteriaBuilder().createQuery(TfAssociate.class);
		cq.from(TfAssociate.class);
		List<TfAssociate> associates = session.createQuery(cq).getResultList();
		for (TfAssociate associate : associates) {
			Hibernate.initialize(associate.getTfMarketingStatus());
		}
		session.close();
		return associates;
	}

}
