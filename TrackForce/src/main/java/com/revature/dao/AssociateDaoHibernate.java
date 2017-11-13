package com.revature.dao;

import java.math.BigDecimal;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfClient;
import com.revature.entity.TfMarketingStatus;
import com.revature.utils.HibernateUtil;

public class AssociateDaoHibernate implements AssociateDao {

	/**
	 * Get a associate from the database given its id.
	 * 
	 * @param associateid
	 */
	@Override
	public TfAssociate getAssociate(BigDecimal associateid) {
		SessionFactory sessionFactory = HibernateUtil.getSession();
		Session session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TfAssociate> criteriaQuery = builder.createQuery(TfAssociate.class);
		Root<TfAssociate> root = criteriaQuery.from(TfAssociate.class);
		criteriaQuery.select(root).where(builder.equal(root.get("tfAssociateId"), associateid));
		Query<TfAssociate> query = session.createQuery(criteriaQuery);

		TfAssociate associate;
		try {
			associate = query.getSingleResult();

			Hibernate.initialize(associate.getTfMarketingStatus());
			Hibernate.initialize(associate.getTfClient());
			Hibernate.initialize(associate.getTfEndClient());
		} catch (NoResultException nre) {
			associate = new TfAssociate();
		} finally {
			session.close();
		}

		return associate;
	}

	/**
	 * Updates an associate's marketing status and client in the database.
	 * 
	 * @param id
	 *            - The ID of the associate to update.
	 * @param marketingStatus
	 *            - A TfMarketingStatus object with the status to change the
	 *            associate to.
	 * @param client
	 *            - A TfClient object with what client the associate will be mapped
	 *            to.
	 */
	@Override
	public void updateInfo(BigDecimal id, TfMarketingStatus marketingStatus, TfClient client) {

		SessionFactory factory = HibernateUtil.getSession();
		Session session = factory.openSession();

		TfMarketingStatus status = null;
		if (marketingStatus.getTfMarketingStatusId() != null) {
			status = session.get(TfMarketingStatus.class, marketingStatus.getTfMarketingStatusId());
		}

		TfClient tfclient = null;
		if (client.getTfClientId() != null) {
			tfclient = session.get(TfClient.class, client.getTfClientId());
		}

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			TfAssociate associate = session.load(TfAssociate.class, id);
			associate.setTfMarketingStatus(status);
			associate.setTfClient(tfclient);

			session.saveOrUpdate(associate);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

		} finally {
			session.close();
		}
	}
}
