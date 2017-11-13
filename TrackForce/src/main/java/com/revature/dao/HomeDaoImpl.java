package com.revature.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.revature.entity.TfAssociate;
import com.revature.utils.HibernateUtil;

public class HomeDaoImpl implements HomeDao {

	private static List<TfAssociate> associates;

	@Override
	public List<TfAssociate> getAllTfAssociates() {
		if (associates == null || associates.isEmpty()) {
			try (Session session = HibernateUtil.getSession().openSession()) {
				CriteriaQuery<TfAssociate> cq = session.getCriteriaBuilder().createQuery(TfAssociate.class);
				cq.from(TfAssociate.class);
				associates = session.createQuery(cq).getResultList();
				for (TfAssociate associate : associates) {
					Hibernate.initialize(associate.getTfBatch());
					Hibernate.initialize(associate.getTfMarketingStatus());
					Hibernate.initialize(associate.getTfClient());
					if (associate.getTfBatch() != null) {
						Hibernate.initialize(associate.getTfBatch().getTfCurriculum());
					}
				}
			}
		}
		return associates;
	}

	/**
	 * Clears associates list in HomeDaoImpl class.
	 */
	public void clearAssociates() {
		if (associates != null) {
			associates.clear();
		}
	}
}
