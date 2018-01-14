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
	public List<TfAssociate> getAllTfAssociates(Session session) throws HibernateException, IOException {
		if (associates == null || associates.isEmpty()) {
			CriteriaQuery<TfAssociate> cq = session.getCriteriaBuilder().createQuery(TfAssociate.class);
			cq.from(TfAssociate.class);
			associates = session.createQuery(cq).getResultList();
			for (TfAssociate associate : associates) {
				associate.getTfBatch();
				associate.getTfMarketingStatus();
				associate.getTfClient();
				associate.getTfAssociateId();
				associate.getTfAssociateFirstName();
				associate.getTfAssociateLastName();

				if (associate.getTfBatch() != null) {
					associate.getTfBatch().getTfCurriculum();
					associate.getTfBatch().getTfBatchName();

					if (associate.getTfBatch().getTfCurriculum() != null) {
						associate.getTfBatch().getTfCurriculum().getTfCurriculumName();
					}
				}
				if (associate.getTfMarketingStatus() != null) {
					associate.getTfMarketingStatus().getTfMarketingStatusName();
				}
				if (associate.getTfClient() != null) {
					associate.getTfClient().getTfClientName();
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
