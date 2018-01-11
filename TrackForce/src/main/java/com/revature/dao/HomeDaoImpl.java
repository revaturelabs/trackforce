package com.revature.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.entity.TfAssociate;
import com.revature.utils.HibernateUtil;

public class HomeDaoImpl implements HomeDao {

	private static List<TfAssociate> associates;

	@Override
	public List<TfAssociate> getAllTfAssociates() throws HibernateException, IOException {
		if (associates == null || associates.isEmpty()) {
			try (Session session = HibernateUtil.getSession().openSession()) {
				CriteriaQuery<TfAssociate> cq = session.getCriteriaBuilder().createQuery(TfAssociate.class);
				cq.from(TfAssociate.class);
				associates = session.createQuery(cq).getResultList();
				for (TfAssociate associate : associates) {
					Hibernate.initialize(associate.getTfBatch());
					Hibernate.initialize(associate.getTfMarketingStatus());
					Hibernate.initialize(associate.getTfClient());
					Hibernate.initialize(associate.getTfAssociateId());
					Hibernate.initialize(associate.getTfAssociateFirstName());
					Hibernate.initialize(associate.getTfAssociateLastName());

					if (associate.getTfBatch() != null) {
						Hibernate.initialize(associate.getTfBatch().getTfCurriculum());
						Hibernate.initialize(associate.getTfBatch().getTfBatchName());
						
						if(associate.getTfBatch().getTfCurriculum() != null) {
							Hibernate.initialize(associate.getTfBatch().getTfCurriculum().getTfCurriculumName());
						}
					}
					if (associate.getTfMarketingStatus() != null) {
						Hibernate.initialize(associate.getTfMarketingStatus().getTfMarketingStatusName());
					}
					if (associate.getTfClient() != null) {
						Hibernate.initialize(associate.getTfClient().getTfClientName());
					}
				}
			}
		}
		return associates;
	}

	/**
	 * Clears associates list in HomeDaoImpl class.
	 */
	public static void clearAssociates() {
		if (associates != null) {
			associates.clear();
		}
	}
}
