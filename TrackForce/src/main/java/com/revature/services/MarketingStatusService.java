package com.revature.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.MarketingStatusDaoHibernate;
import com.revature.entity.TfMarketingStatus;
import com.revature.model.MarketingStatusInfo;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;

public class MarketingStatusService implements Delegate {

	private Set<MarketingStatusInfo> getAllMarketingStatuses() throws IOException{
		Set<MarketingStatusInfo> tfms = PersistentStorage.getStorage().getMarketingStatuses();
		if(tfms == null || tfms.isEmpty()) {
			execute();
			return PersistentStorage.getStorage().getMarketingStatuses();
		}
		return tfms;
	}
	
	public Map<BigDecimal, MarketingStatusInfo> getMarketingServices() throws IOException{
		Session session = HibernateUtil.getSession().openSession();
		Transaction tx = session.beginTransaction();
		Map<BigDecimal, MarketingStatusInfo> map;
		try {
			map = new MarketingStatusDaoHibernate().getMarketingStatuses(session);
			
			session.flush();
			tx.commit();
			
			return map;
		} catch(Exception e) {
			e.printStackTrace();
			LogUtil.logger.error(e);
			session.flush();
			tx.rollback();
			throw new IOException("Could not get marketing statuses", e);
		} finally {
			session.close();
		}
	}
	
	@Override
	public synchronized void execute() throws IOException {
		Set<MarketingStatusInfo> msi = PersistentStorage.getStorage().getMarketingStatuses();
		if(msi == null || msi.isEmpty())
			PersistentStorage.getStorage().setMarketingStatuses(getMarketingServices());
	}

	@Override
	public <T> Set<T> read(String...args) throws IOException {
		return (Set<T>) getAllMarketingStatuses();
	}

}
