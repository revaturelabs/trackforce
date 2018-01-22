package com.revature.utils;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;

import com.revature.dao.*;
import com.revature.services.*;
import org.hibernate.Session;
import org.mockito.*;
import org.testng.annotations.*;

public class PersistentStorageTest {

    @Mock
    private AssociateDaoHibernate mockAssociateDao;
    @Mock
    private BatchDaoHibernate mockBatchDao;
    @Mock
    private ClientDaoImpl mockClientDao;
    @Mock
    private CurriculumDaoImpl mockCurriculumDao;
    @Mock
    private MarketingStatusDaoHibernate mockMarketingStatusDao;


    @BeforeClass
    public void before() throws IOException {
        HibernateUtil.getSessionFactory();
        MockitoAnnotations.initMocks(this);

        Mockito.when(mockAssociateDao.getAssociates(Matchers.any(Session.class)))
                .thenReturn(new HashMap<>());
        Mockito.when(mockBatchDao.getBatchDetails(Matchers.any(Session.class)))
                .thenReturn(new HashMap<>());
        Mockito.when(mockClientDao.getAllTfClients(Matchers.any(Session.class)))
                .thenReturn(new HashMap<>());
        Mockito.when(mockCurriculumDao.fetchCurriculums(Matchers.any(Session.class)))
                .thenReturn(new HashMap<>());
        Mockito.when(mockMarketingStatusDao.getMarketingStatuses(Matchers.any(Session.class)))
                .thenReturn(new HashMap<>());


        PersistentServiceDelegator serviceDelegator = new PersistentServiceDelegator(
                new AssociateService(mockAssociateDao),
                new BatchesService(mockBatchDao),
                new ClientResource(mockClientDao),
                new CurriculumService(mockCurriculumDao),
                new MarketingStatusService(mockMarketingStatusDao)
        );

        // pull info from *database (*results mocked in this case)
        serviceDelegator.getAssociates();
        serviceDelegator.getBatches();
        serviceDelegator.getClients();
        serviceDelegator.getCurriculums();
        serviceDelegator.getMarketingStatuses();
    }

    @AfterClass
    public void after() {
        HibernateUtil.shutdown();
    }

    @Test
    public void testPersistence() {
        assertTrue(PersistentStorage.getStorage().getAssociates() != null);
        assertTrue(PersistentStorage.getStorage().getBatches() != null);
        assertTrue(PersistentStorage.getStorage().getClients() != null);
        assertTrue(PersistentStorage.getStorage().getCurriculums() != null);
        assertTrue(PersistentStorage.getStorage().getMarketingStatuses() != null);
    }
}
