package com.revature.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.entity.TfAssociate;
import com.revature.entity.TfMarketingStatus;
import com.revature.model.AssociateInfo;
import com.revature.model.MarketingStatusInfo;
import com.revature.utils.Dao2DoMapper;
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
			Integer bd = new Integer(Integer.parseInt(status));
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

	@Override
	public Map<Integer, MarketingStatusInfo> getMarketingStatuses(Session session) throws HibernateException, IOException{
		List<TfMarketingStatus> marketingStatusEnts;
		Map<Integer, MarketingStatusInfo> map = new HashMap<>();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<TfMarketingStatus> cq = cb.createQuery(TfMarketingStatus.class);
		Root<TfMarketingStatus> from = cq.from(TfMarketingStatus.class);
		CriteriaQuery<TfMarketingStatus> all = cq.select(from);
		Query<TfMarketingStatus> tq = session.createQuery(all);

		marketingStatusEnts = tq.getResultList();
		for(TfMarketingStatus tfms : marketingStatusEnts) {
			map.put(tfms.getTfMarketingStatusId(), Dao2DoMapper.map(tfms));
		}
		
		return map;
	}
}