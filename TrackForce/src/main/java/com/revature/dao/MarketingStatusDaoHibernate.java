package com.revature.dao;

import java.io.IOException;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.entity.TfMarketingStatus;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class MarketingStatusDaoHibernate implements MarketingStatusDao {

    @Override
    public TfMarketingStatus getMarketingStatus(String status) throws IOException {
        SessionFactory sessionFactory = HibernateUtil.getSession();
        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TfMarketingStatus> criteriaQuery = builder.createQuery(TfMarketingStatus.class);
            Root<TfMarketingStatus> root = criteriaQuery.from(TfMarketingStatus.class);
            criteriaQuery.select(root).where(builder.equal(root.get("tfMarketingStatusName"), status));
            Query<TfMarketingStatus> query = session.createQuery(criteriaQuery);

            TfMarketingStatus marketingStatus;
            try {
                marketingStatus = query.getSingleResult();
            } catch (NoResultException nre) {
                marketingStatus = new TfMarketingStatus();
                LogUtil.logger.error(nre);
            }

            return marketingStatus;
        }
    }
}
