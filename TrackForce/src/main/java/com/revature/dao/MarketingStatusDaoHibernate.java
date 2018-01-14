package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.entity.TfMarketingStatus;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class MarketingStatusDaoHibernate implements MarketingStatusDao {

	@Override
	public TfMarketingStatus getMarketingStatus(Session session, String status) throws IOException {

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TfMarketingStatus> criteriaQuery = builder.createQuery(TfMarketingStatus.class);
		Root<TfMarketingStatus> root = criteriaQuery.from(TfMarketingStatus.class);
		criteriaQuery.select(root).where(builder.equal(root.get("tfMarketingStatusName"), status));
		Query<TfMarketingStatus> query = session.createQuery(criteriaQuery);

		query = session.createQuery("FROM TfMarketingStatus WHERE tfMarketingStatusId = ?");
		
		TfMarketingStatus marketingStatus;
		try {
			BigDecimal bd = new BigDecimal(Integer.parseInt(status));
			query.setParameter(0, bd);
			marketingStatus = (TfMarketingStatus) query.uniqueResult();
			
		} catch (NoResultException nre) {
			LogUtil.logger.error(nre);
			return null;
		}
		
		System.out.println(marketingStatus);
		
		System.out.println("test");

		return marketingStatus;
	}
}