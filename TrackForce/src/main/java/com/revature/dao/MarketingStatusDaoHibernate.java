package com.revature.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.entity.TfMarketingStatus;
import com.revature.model.MarketingStatusInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;

public class MarketingStatusDaoHibernate implements MarketingStatusDao {

	@Override
	public MarketingStatusInfo getMarketingStatus(String status) {
		TfMarketingStatus marketingStatus = null;
		try(Session session = HibernateUtil.getSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TfMarketingStatus> criteriaQuery = builder.createQuery(TfMarketingStatus.class);
			Root<TfMarketingStatus> root = criteriaQuery.from(TfMarketingStatus.class);
			criteriaQuery.select(root).where(builder.equal(root.get("tfMarketingStatusName"), status));
			Query<TfMarketingStatus> query = session.createQuery(criteriaQuery);
			//query = session.createQuery("FROM TfMarketingStatus WHERE tfMarketingStatusId = ?");
			Integer bd = Integer.parseInt(status);
			query.setParameter(0, bd);
			marketingStatus = (TfMarketingStatus) query.uniqueResult();
		} catch (NoResultException nre) {
			LogUtil.logger.error(nre);
		}
		return Dao2DoMapper.map(marketingStatus);
	}
	
	public TfMarketingStatus getMarketingStatus(Integer id) {
		TfMarketingStatus tfMarketingStatus;
		try(Session session = HibernateUtil.getSession()){
			tfMarketingStatus = (TfMarketingStatus) session.load(TfMarketingStatus.class, id);
		}
		return tfMarketingStatus;
	}
	
	@Override
	public Map<Integer, MarketingStatusInfo> getMarketingStatus() {
		List<TfMarketingStatus> marketingStatusEnts;
		Map<Integer, MarketingStatusInfo> map = new HashMap<>();
		try(Session session = HibernateUtil.getSession()) {
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
			LogUtil.logger.error(e);
		}
		return new HashMap<Integer, MarketingStatusInfo>();
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