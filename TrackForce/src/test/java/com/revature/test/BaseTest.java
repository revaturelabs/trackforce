package com.revature.test;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.dao.ClientDaoImpl;
import com.revature.dao.MarketingStatusDaoHibernate;
import com.revature.services.AssociateService;
import com.revature.services.ClientResource;
import com.revature.services.MarketingStatusService;
import com.revature.utils.PersistentStorage;
import com.revature.utils.TestHibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.sql.SQLException;

public class BaseTest {
    protected SessionFactory sessionFactory;

    public BaseTest() {
        try {
            sessionFactory = TestHibernateUtil.getSessionFactory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollbackAndClose(Session session) {
        try {
            session.getTransaction().rollback();
        } catch (Exception ignored) {
        }

        try {
            session.close();
        } catch (Exception ignored) {
        }
    }

    public void resetCaches() throws IOException {
        AssociateService associateService = new AssociateService(new AssociateDaoHibernate(), sessionFactory);
        ClientResource clientResource = new ClientResource(new ClientDaoImpl(), sessionFactory);
        MarketingStatusService marketingStatusService = new MarketingStatusService(new MarketingStatusDaoHibernate(), sessionFactory);

        PersistentStorage.getStorage().setAssociates(associateService.getAssociates());
        PersistentStorage.getStorage().setClients(clientResource.getClients());
        PersistentStorage.getStorage().setMarketingStatuses(marketingStatusService.getMarketingStatuses());
    }
}
