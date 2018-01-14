package com.revature.dao;

import java.io.IOException;
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
import com.revature.utils.LogUtil;

public class AssociateDaoHibernate implements AssociateDao {

	/**
	 * Get a associate from the database given its id.
	 * 
	 * @param associateid
	 * @throws IOException 
	 */
	@Override
	public TfAssociate getAssociate(BigDecimal associateid) throws IOException {
		TfAssociate associate;
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TfAssociate> criteriaQuery = builder.createQuery(TfAssociate.class);
		Root<TfAssociate> root = criteriaQuery.from(TfAssociate.class);
		criteriaQuery.select(root).where(builder.equal(root.get("tfAssociateId"), associateid));
		Query<TfAssociate> query = session.createQuery(criteriaQuery);

		try {
			associate = query.getSingleResult();

			Hibernate.initialize(associate.getTfMarketingStatus());
			Hibernate.initialize(associate.getTfClient());
			Hibernate.initialize(associate.getTfEndClient());
			Hibernate.initialize(associate.getTfBatch());
		} catch (NoResultException nre) {
			LogUtil.logger.error(nre);
			associate = new TfAssociate();
		} finally {
			session.flush();
			tx.commit();
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
	 * @throws IOException 
	 */
	@Override
	public void updateInfo(Session session, BigDecimal id, TfMarketingStatus marketingStatus, TfClient client) throws IOException {

		TfClient tfclient = null;
		if (client.getTfClientId() != null) {
			tfclient = session.get(TfClient.class, client.getTfClientId());
		}

		TfAssociate associate = session.load(TfAssociate.class, id);
		associate.setTfMarketingStatus(marketingStatus);
		associate.setTfClient(tfclient);
		session.saveOrUpdate(associate);

		//clear associates list to force update to stored list(s)
		HomeDaoImpl.clearAssociates();
	}
}