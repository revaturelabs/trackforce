package com.revature.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import com.revature.dao.MarketingStatusDao;
import com.revature.dao.MarketingStatusDaoHibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.model.MarketingStatusInfo;
import com.revature.utils.HibernateUtil;
import com.revature.utils.LogUtil;
import com.revature.utils.PersistentStorage;

public class MarketingStatusService implements Delegate {

    private MarketingStatusDao marketingStatusDao;

    public MarketingStatusService() {
        this.marketingStatusDao = new MarketingStatusDaoHibernate();
    }

    /**
     * cctor with injectable dao for easier testing
     *
     * @param marketingStatusDao
     */
    public MarketingStatusService(MarketingStatusDao marketingStatusDao) {
        this.marketingStatusDao = marketingStatusDao;
    }

    private Set<MarketingStatusInfo> getAllMarketingStatuses() throws IOException {
        Set<MarketingStatusInfo> tfms = PersistentStorage.getStorage().getMarketingStatuses();
        if (tfms == null || tfms.isEmpty()) {
            execute();
            return PersistentStorage.getStorage().getMarketingStatuses();
        }
        return tfms;
    }

    public Map<BigDecimal, MarketingStatusInfo> getMarketingServices() throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Map<BigDecimal, MarketingStatusInfo> map;
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
            PersistentStorage.getStorage().setMarketingStatuses(getMarketingServices());
    }

    @Override
    public <T> Set<T> read(String... args) throws IOException {
        return (Set<T>) getAllMarketingStatuses();
    }

}
