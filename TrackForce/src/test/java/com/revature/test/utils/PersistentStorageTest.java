package com.revature.test.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertFalse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.dao.AssociateDaoHibernate;
import com.revature.dao.BatchDaoHibernate;
import com.revature.dao.ClientDaoImpl;
import com.revature.dao.CurriculumDaoImpl;
import com.revature.dao.MarketingStatusDaoHibernate;

import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;
import com.revature.model.ClientInfo;
import com.revature.model.CurriculumInfo;
import com.revature.model.MarketingStatusInfo;

import com.revature.services.AssociateService;
//import com.revature.test.BaseTest;
import com.revature.utils.PersistentStorage;

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
    

    @BeforeTest
    public void beforeTests() throws IOException, SQLException {
        MockitoAnnotations.initMocks(this);

        HashMap<Integer, AssociateInfo> associateMap = new HashMap<>();
        AssociateInfo aInfo = new AssociateInfo();
        aInfo.setId(new Integer(-1));
        associateMap.put(new Integer(-1), aInfo);

        BatchInfo bInfo = new BatchInfo();
        bInfo.setId(new Integer(-1));
        HashMap<Integer, BatchInfo> batchMap = new HashMap<>();
        batchMap.put(new Integer(-1), bInfo);

        ClientInfo cInfo = new ClientInfo();
        cInfo.setId(new Integer(-1));
        HashMap<Integer, ClientInfo> clientMap = new HashMap<>();
        clientMap.put(new Integer(-1), cInfo);

        CurriculumInfo currInfo = new CurriculumInfo();
        currInfo.setId(new Integer(-1));
        HashMap<Integer, CurriculumInfo> curriculumMap = new HashMap<>();
        curriculumMap.put(new Integer(-1), currInfo);

        MarketingStatusInfo mInfo = new MarketingStatusInfo();
        mInfo.setId(new Integer(-1));
        HashMap<Integer, MarketingStatusInfo> marketingStatusMap = new HashMap<>();
        marketingStatusMap.put(new Integer(-1), mInfo);
        
      

        Mockito.when(mockAssociateDao.getAssociates())
                .thenReturn(associateMap);
        Mockito.when(mockBatchDao.getBatchDetails())
                .thenReturn(batchMap);
        Mockito.when(mockClientDao.getAllTfClients())
                .thenReturn(clientMap);
        Mockito.when(mockCurriculumDao.getAllCurriculums())
                .thenReturn(curriculumMap);
        Mockito.when(mockMarketingStatusDao.getMarketingStatus())
                .thenReturn(marketingStatusMap);
       
        
        // pull info from *database (*results mocked in this case)
        PersistentStorage.getStorage().setAssociates(mockAssociateDao.getAssociates());
        PersistentStorage.getStorage().setBatches(mockBatchDao.getBatchDetails());
        PersistentStorage.getStorage().setClients(mockClientDao.getAllTfClients());
        PersistentStorage.getStorage().setCurriculums(mockCurriculumDao.getAllCurriculums());
        PersistentStorage.getStorage().setMarketingStatuses(mockMarketingStatusDao.getMarketingStatus());
        
    }

    @Test(enabled = true)
    public void testAssociatePersistence() {
        assertNotNull(PersistentStorage.getStorage().getAssociates());
        assertFalse(PersistentStorage.getStorage().getAssociates().isEmpty());
    }
    
    @Test(enabled = true)
    public void testBatchesPersistence() {
    	assertNotNull(PersistentStorage.getStorage().getBatches());
        assertFalse(PersistentStorage.getStorage().getBatches().isEmpty());
    }
    
    @Test(enabled = true)
    public void testClientsPersistence() {
    	assertNotNull(PersistentStorage.getStorage().getClients());
        assertFalse(PersistentStorage.getStorage().getClients().isEmpty());
    }
    
    @Test(enabled = true)
    public void testCurriculumsPersistence() {
    	assertNotNull(PersistentStorage.getStorage().getCurriculums());
        assertFalse(PersistentStorage.getStorage().getCurriculums().isEmpty());
    }
    
    @Test(enabled = true)
    public void testMarketingPersistence() {
    	assertNotNull(PersistentStorage.getStorage().getMarketingStatuses());
        assertFalse(PersistentStorage.getStorage().getMarketingStatuses().isEmpty());
    }
    

    @Test(enabled = false)
    public void updateAssociate() throws NumberFormatException, IOException {
    	System.out.println("update Associate");
        System.err.println(PersistentStorage.getStorage().getAssociateAsMap());
        
		TreeSet<AssociateInfo> aiSet = (TreeSet<AssociateInfo>) PersistentStorage.getStorage().getAssociates();
		AssociateInfo ai = aiSet.first();
		AssociateInfo copy = new AssociateInfo();

		// We need an associate with different info to test against
		assertNotEquals(2, ai.getMsid().intValue());
		assertNotEquals(1, ai.getClid().intValue());

		copy.setBid(ai.getBid());
		copy.setClid(ai.getClid());
		copy.setCurid(ai.getCurid());
		copy.setMarketingStatusId(ai.getMsid());
		copy.setEcid(ai.getEcid());

		AssociateService as = new AssociateService();
		List<Integer> associateIds = new ArrayList<>(new Integer(ai.getId()));
		as.updateAssociates(associateIds , 2, 2);

		assertEquals(2, ai.getMsid().intValue());
		assertEquals(1, ai.getClid().intValue());
    }
}
