package com.revature.dao;

import java.util.List;

import org.hibernate.Session;

import com.revature.entity.TfMarketingStatus;
import com.revature.utils.HibernateUtil;

public class MarketingStatusDaoHibernate implements MarketingStatusDao {

	@Override
	public List<TfMarketingStatus> getAllMarketingStatuses() {
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from Tf_Marketing_Status", TfMarketingStatus.class).setCacheable(true).getResultList());
	}
	
	
	
	
	
}