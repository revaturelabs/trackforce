package com.revature.daoimpl;

import java.util.List;

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
	
	
	
	
	
}