package com.revature.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import com.revature.dao.MarketingStatusDao;
import com.revature.dao.MarketingStatusDaoHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.model.MarketingStatusInfo;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;

public class MarketingStatusService implements Service {

    private MarketingStatusDao marketingStatusDao;
    private SessionFactory sessionFactory;

    public MarketingStatusService() {
        this.marketingStatusDao = new MarketingStatusDaoHibernate();
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    /**
     * cctor with injectable dao for easier testing
     *
     * @param marketingStatusDao
     */
    public MarketingStatusService(MarketingStatusDao marketingStatusDao, SessionFactory sessionFactory) {
        this.marketingStatusDao = marketingStatusDao;
        this.sessionFactory = sessionFactory;
    }

    /**
     * gets all marketing statuses using cache (Persistence Storage) mechanism
     * @return
     * @throws IOException
     */
    private Set<MarketingStatusInfo> getAllMarketingStatuses() throws IOException {
        Set<MarketingStatusInfo> tfms = PersistentStorage.getStorage().getMarketingStatuses();
        if (tfms == null || tfms.isEmpty()) {
            execute();
            return PersistentStorage.getStorage().getMarketingStatuses();
        }
        return tfms;
    }

    /**
     * fetches marketing statuses from database
     * @return
     * @throws IOException
     */
    public Map<Integer, MarketingStatusInfo> getMarketingStatuses() throws IOException {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Map<Integer, MarketingStatusInfo> map;
        try {
            map = marketingStatusDao.getMarketingStatuses(session);

            session.flush();
            tx.commit();

            return map;
        } catch (Exception e) {
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
        if (msi == null || msi.isEmpty())
            PersistentStorage.getStorage().setMarketingStatuses(getMarketingStatuses());
    }

    @Override
    public <T> Set<T> read(String... args) throws IOException {
        return (Set<T>) getAllMarketingStatuses();
    }

}
