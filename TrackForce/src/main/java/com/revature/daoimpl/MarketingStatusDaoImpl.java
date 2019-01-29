package com.revature.daoimpl;
import java.util.List;
import org.hibernate.Session;
import com.revature.dao.MarketingStatusDao;
import com.revature.entity.TfMarketingStatus;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;

public class MarketingStatusDaoImpl implements MarketingStatusDao {

	@Override
	public List<TfMarketingStatus> getAllMarketingStatuses() {
		LogUtil.logger.trace("Hibernate Call to get ALL Marketing Statuses.");
		return HibernateUtil.runHibernate((Session session, Object ... args) ->
		session.createQuery("from TfMarketingStatus", TfMarketingStatus.class).setCacheable(true).getResultList());
	}
	
	/*
	"MAPPED: TRAINING": 1,
    "MAPPED: RESERVED": 2,
    "MAPPED: SELECTED": 3,
    "MAPPED: CONFIRMED": 4,
    "MAPPED: DEPLOYED": 5,
    "UNMAPPED: TRAINING": 6,
    "UNMAPPED: OPEN": 7,
    "UNMAPPED: SELECTED": 8,
    "UNMAPPED: CONFIRMED": 9,
    "UNMAPPED: DEPLOYED": 10,
    "DIRECTLY PLACED": 11,
    "TERMINATED": 12
	 */
	@Override
	public TfMarketingStatus getMarketingStatusById(int id) {
		LogUtil.logger.trace("Hibernate Call to get Marketing Status by Id: " + id);
		return HibernateUtil.runHibernate((Session session, Object... args) ->
				session.createQuery("from TfMarketingStatus c where c.id like :id", TfMarketingStatus.class).setCacheable(true)
				.setParameter("id", id).getSingleResult());
	}
}