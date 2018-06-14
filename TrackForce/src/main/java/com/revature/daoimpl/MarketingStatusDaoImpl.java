package com.revature.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static com.revature.utils.LogUtil.logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.dao.MarketingStatusDao;
import com.revature.entity.TfMarketingStatus;
import com.revature.model.MarketingStatusInfo;
import com.revature.utils.Dao2DoMapper;
import com.revature.utils.HibernateUtil;
import com.revature.utils.PersistentStorage;

public class MarketingStatusDaoImpl implements MarketingStatusDao {

	@Override
	public List<TfMarketingStatus> getAllMarketingStatuses() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfMarketingStatus", TfMarketingStatus.class).getResultList());
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