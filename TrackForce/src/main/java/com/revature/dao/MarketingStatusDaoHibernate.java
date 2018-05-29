package com.revature.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.entity.TfMarketingStatus;
import com.revature.model.MarketingStatusInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
import com.revature.utils.PersistentStorage;

public class MarketingStatusDaoHibernate implements MarketingStatusDao {
	static final Logger logger = Logger.getLogger(MarketingStatusDaoHibernate.class);
	
	@Override
	public MarketingStatusInfo getMarketingStatus(String status) {
		TfMarketingStatus marketingStatus = null;
		Session session = HibernateUtil.getSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TfMarketingStatus> criteriaQuery = builder.createQuery(TfMarketingStatus.class);
			Root<TfMarketingStatus> root = criteriaQuery.from(TfMarketingStatus.class);
			criteriaQuery.select(root).where(builder.equal(root.get("tfMarketingStatusName"), status));
			Query<TfMarketingStatus> query = session.createQuery(criteriaQuery);
			Integer bd = Integer.parseInt(status);
			query.setParameter(0, bd);
			marketingStatus = (TfMarketingStatus) query.uniqueResult();
		} catch (NoResultException nre) {
			logger.error(nre);
		}
		finally {
			session.close();
		}
		return Dao2DoMapper.map(marketingStatus);
	}
	
	public TfMarketingStatus getMarketingStatus(Integer id) {
		TfMarketingStatus tfMarketingStatus;
		Session session = HibernateUtil.getSession();
		try {
			tfMarketingStatus = (TfMarketingStatus) session.load(TfMarketingStatus.class, id);
		}
		finally {
			session.close();
		}
		return tfMarketingStatus;
	}
	
	@Override
	public Map<Integer, MarketingStatusInfo> getMarketingStatus() {
		List<TfMarketingStatus> marketingStatusEnts;
		Map<Integer, MarketingStatusInfo> map = new HashMap<>();
		Session session = HibernateUtil.getSession();
		try {
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
		} catch(Exception e) {
			logger.error(e);
		}
		finally {
			session.close();
		}
		return new HashMap<>();
	}
	
	@Override
	public Set<MarketingStatusInfo> getAllMarketingStatuses() {
		if(PersistentStorage.getStorage().getMarketingStatuses() == null)
			cacheAllMarketingStatuses();
		return PersistentStorage.getStorage().getMarketingStatuses();
	}

	public void cacheAllMarketingStatuses() {
		PersistentStorage.getStorage().setMarketingStatuses(getMarketingStatus());
	}
	
	
}