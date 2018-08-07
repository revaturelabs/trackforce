package com.revature.daoimpl;

import java.util.List;

import com.revature.entity.TfClient;
import org.hibernate.Session;

import com.revature.dao.MarketingStatusDao;
import com.revature.entity.TfMarketingStatus;
import com.revature.utils.HibernateUtil;

public class MarketingStatusDaoImpl implements MarketingStatusDao {

	@Override
	public List<TfMarketingStatus> getAllMarketingStatuses() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfMarketingStatus", TfMarketingStatus.class).getResultList());
	}
	@Override
	public TfMarketingStatus getMarketingStatusById(int id) {
		return HibernateUtil.runHibernate((Session session, Object... args) ->
				session.createQuery("from TfMarketingStatus c where c.id like :id", TfMarketingStatus.class).setParameter("id", id).getSingleResult());
	}




}