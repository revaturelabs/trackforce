package com.revature.test.utils;

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertFalse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;

import com.revature.dao.*;
import com.revature.model.*;
import com.revature.services.*;
import com.revature.test.BaseTest;
import com.revature.utils.PersistentStorage;
import org.hibernate.Session;
import org.mockito.*;
import org.testng.annotations.*;

public class PersistentStorageTest extends BaseTest {

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

    @BeforeTest
    public void beforeTests() throws IOException, SQLException {
        MockitoAnnotations.initMocks(this);

        HashMap<BigDecimal, AssociateInfo> associateMap = new HashMap<>();
        AssociateInfo aInfo = new AssociateInfo();
        aInfo.setId(new BigDecimal(-1));
        associateMap.put(new BigDecimal(-1), aInfo);

        BatchInfo bInfo = new BatchInfo();
        bInfo.setId(new BigDecimal(-1));
        HashMap<BigDecimal, BatchInfo> batchMap = new HashMap<>();
        batchMap.put(new BigDecimal(-1), bInfo);

        ClientInfo cInfo = new ClientInfo();
        cInfo.setId(new BigDecimal(-1));
        HashMap<BigDecimal, ClientInfo> clientMap = new HashMap<>();
        clientMap.put(new BigDecimal(-1), cInfo);

        CurriculumInfo currInfo = new CurriculumInfo();
        currInfo.setId(new BigDecimal(-1));
        HashMap<BigDecimal, CurriculumInfo> curriculumMap = new HashMap<>();
        curriculumMap.put(new BigDecimal(-1), currInfo);

        MarketingStatusInfo mInfo = new MarketingStatusInfo();
        mInfo.setId(new BigDecimal(-1));
        HashMap<BigDecimal, MarketingStatusInfo> marketingStatusMap = new HashMap<>();
        marketingStatusMap.put(new BigDecimal(-1), mInfo);

        Mockito.when(mockAssociateDao.getAssociates(Matchers.any(Session.class)))
                .thenReturn(associateMap);
        Mockito.when(mockBatchDao.getBatchDetails(Matchers.any(Session.class)))
                .thenReturn(batchMap);
        Mockito.when(mockClientDao.getAllTfClients(Matchers.any(Session.class)))
                .thenReturn(clientMap);
        Mockito.when(mockCurriculumDao.fetchCurriculums(Matchers.any(Session.class)))
                .thenReturn(curriculumMap);
        Mockito.when(mockMarketingStatusDao.getMarketingStatuses(Matchers.any(Session.class)))
                .thenReturn(marketingStatusMap);

        PersistentServiceDelegator serviceDelegator = new PersistentServiceDelegator(
                new AssociateService(mockAssociateDao, sessionFactory),
                new BatchesService(mockBatchDao, sessionFactory),
                new ClientResource(mockClientDao, sessionFactory),
                new CurriculumService(mockCurriculumDao, sessionFactory),
                new MarketingStatusService(mockMarketingStatusDao, sessionFactory)
        );

        // pull info from *database (*results mocked in this case)
        serviceDelegator.getAssociates();
        serviceDelegator.getBatches();
        serviceDelegator.getClients();
        serviceDelegator.getCurriculums();
        serviceDelegator.getMarketingStatuses();
    }

    @Test
    public void testActualPersistence() {
        assertNotNull(PersistentStorage.getStorage().getAssociates());
        assertFalse(PersistentStorage.getStorage().getAssociates().isEmpty());
        assertNotNull(PersistentStorage.getStorage().getBatches());
        assertFalse(PersistentStorage.getStorage().getBatches().isEmpty());
        assertNotNull(PersistentStorage.getStorage().getClients());
        assertFalse(PersistentStorage.getStorage().getClients().isEmpty());
        assertNotNull(PersistentStorage.getStorage().getCurriculums());
        assertFalse(PersistentStorage.getStorage().getCurriculums().isEmpty());
        assertNotNull(PersistentStorage.getStorage().getMarketingStatuses());
        assertFalse(PersistentStorage.getStorage().getMarketingStatuses().isEmpty());
    }

    @Test
    public void updateAssociate() throws NumberFormatException, IOException {
        System.err.println(PersistentStorage.getStorage().getAssociateAsMap());
//		AssociateInfo ai = PersistentStorage.getStorage().getAssociateAsMap();
//		AssociateInfo copy = new AssociateInfo();
//
//		// We need an associate with different info to test against
//		assertNotEquals(2, ai.getMsid());
//		assertNotEquals(1, ai.getClid());
//
//		copy.setBid(ai.getBid());
//		copy.setClid(ai.getClid());
//		copy.setCurid(ai.getCurid());
//		copy.setMarketingStatusId(ai.getMsid());
//		copy.setEcid(ai.getEcid());
//
//		AssociateService as = new AssociateService();
//		as.updateAssociate("1", "2", "1");
//
//		assertEquals(2, ai.getMsid());
//		assertEquals(1, ai.getClid());
    }
}
