package com.revature.test;

import java.io.IOException;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.mockito.Mock;

//import com.revature.dao.AssociateDaoHibernate;
//import com.revature.dao.BatchDaoHibernate;
//import com.revature.dao.ClientDaoImpl;
//import com.revature.dao.CurriculumDaoImpl;
//import com.revature.dao.MarketingStatusDaoHibernate;
//import com.revature.dao.TechDaoHibernate;
import com.revature.services.AssociateService;
import com.revature.services.ClientService;
import com.revature.services.MarketingStatusService;
//import com.revature.utils.PersistentStorage;
import com.revature.utils.TestHibernateUtil;

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
    	System.out.println("not implemented");
    	    
//        AssociateService associateService = new AssociateService();
//        ClientService clientResource = new ClientService();
//        MarketingStatusService marketingStatusService = new MarketingStatusService(new MarketingStatusDaoHibernate());

        //PersistentStorage.getStorage().setAssociates(associateService.getAssociates());
        //PersistentStorage.getStorage().setClients(clientResource.getClients());
        //PersistentStorage.getStorage().setMarketingStatuses(marketingStatusService.getMarketingStatuses());
    	
//    	AssociateDaoHibernate mockAssociateDao ;
//	    BatchDaoHibernate mockBatchDao;
//	    ClientDaoImpl mockClientDao;
//	    CurriculumDaoImpl mockCurriculumDao;
//	    MarketingStatusDaoHibernate mockMarketingStatusDao;
//	    TechDaoHibernate mockTechDao;
//	    
//    	mockAssociateDao.cacheAllAssociates();
//        mockBatchDao.cacheAllBatches();
//        mockClientDao.cacheAllClients();
//        mockCurriculumDao.cacheAllCurriculms();
//        mockMarketingStatusDao.cacheAllMarketingStatuses();
//        mockTechDao.cacheAllTechs();
        
    }


    /**
     * TODO: add a reset method in Delegator interface (or to the daos),
     * so that each type of resource can reset its part of the cache
     *
     * allows using of injected services to reset the cache
     *
     * @param mockAssocService
     * @param mockClientResource
     * @param mockMsService
     * @throws IOException
     */
    public void resetCaches(AssociateService mockAssocService, ClientService mockClientResource, MarketingStatusService mockMsService) throws IOException {
        if (mockAssocService == null)
            mockAssocService = new AssociateService();
        if (mockClientResource == null)
            mockClientResource = new ClientService();
        if (mockMsService == null)
            mockMsService = new MarketingStatusService();

        //PersistentStorage.getStorage().setAssociates(mockAssocService.getAssociates());
        //PersistentStorage.getStorage().setClients(mockClientResource.getClients());
        //PersistentStorage.getStorage().setMarketingStatuses(mockMsService.getMarketingStatuses());
    }
}
