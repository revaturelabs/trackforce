package com.revature.services;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.revature.dao.MarketingStatusDao;
import com.revature.dao.MarketingStatusDaoHibernate;
import com.revature.model.MarketingStatusInfo;
import com.revature.utils.PersistentStorage;

public class MarketingStatusService {

    private MarketingStatusDao marketingStatusDao;

    public MarketingStatusService() {
        this.marketingStatusDao = new MarketingStatusDaoHibernate();
    }

    public MarketingStatusService(MarketingStatusDao marketingStatusDao) {
        this.marketingStatusDao = marketingStatusDao;
    }

    /**
     * gets all marketing statuses using cache (Persistence Storage) mechanism
     * @return
     * @throws IOException
     */
    public Set<MarketingStatusInfo> getAllMarketingStatuses() throws IOException {
        Set<MarketingStatusInfo> tfms = PersistentStorage.getStorage().getMarketingStatuses();
        if (tfms == null || tfms.isEmpty()) {
            updateCache();
            tfms = PersistentStorage.getStorage().getMarketingStatuses();
        }
        return tfms;
    }

    /**
     * fetches marketing statuses from database
     * @return
     * @throws IOException
     */
    private Map<Integer, MarketingStatusInfo> getMarketingStatuses() throws IOException {
        Map<Integer, MarketingStatusInfo> map = null;
        map = marketingStatusDao.getMarketingStatuses();
        return map;
    }

    public synchronized void updateCache() throws IOException {
        PersistentStorage.getStorage().setMarketingStatuses(getMarketingStatuses());
    }
}
