package com.revature.test.services;

import com.revature.dao.BatchDao;
import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;
import com.revature.services.BatchesService;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class BatchesServiceTest{

    @Mock
    private BatchDao mockBatchDao;

    private BatchesService batchService;
    private AssociateInfo assoc1, assoc2, assoc3;
    private BatchInfo batch1, batch2, batch3;
    private Set<BatchInfo> batchSet;
    private List<BatchInfo> batchList;
    private Set<AssociateInfo> associateSet;

    @BeforeTest
    public void beforeAll() throws IOException {
        MockitoAnnotations.initMocks(this);

//        Map<Integer, BatchInfo> mockBatchMap = new HashMap();
        assoc1 = createAssocInfo(1, 1, "b1", 1, "c1", 1, "s1", 1, "c1");
        assoc2 = createAssocInfo(2, 2, "b2", 2, "c2", 2, "s2", 2, "c2");
        assoc3 = createAssocInfo(3, 3, "b3", 3, "c3", 3, "s3", 3, "c3");

        batch1 = createBatchInfo(1, "b1", "c1", 0, 100);
        batch1.setAssociates(new HashSet<>(Arrays.asList(assoc1)));

        batch2 = createBatchInfo(2, "b2", "c2", 100, 200);
        batch2.setAssociates(new HashSet<>(Arrays.asList(assoc2)));

        batch3 = createBatchInfo(3, "b3", "c3", 200, 300);
        batch3.setAssociates(new HashSet<>(Arrays.asList(assoc3)));

//        mockBatchMap.put(batch1.getId(), batch1);
//        mockBatchMap.put(batch2.getId(), batch2);
//        mockBatchMap.put(batch3.getId(), batch3);
        
        batchSet = new TreeSet<>();
        batchSet.add(batch1);
        
        batchList = new ArrayList<>();
        batchList.add(batch1);
        batchList.add(batch2);
        batchList.add(batch3);
        
        associateSet = new TreeSet<>();
        associateSet.add(assoc1);

        System.out.println("setting mocks");
        Mockito.when(mockBatchDao.getAllBatches()).thenReturn(batchSet);
        Mockito.when(mockBatchDao.getBatchesSortedByDate()).thenReturn(batchList);
        Mockito.when(mockBatchDao.getBatchById(Matchers.anyInt())).thenReturn(batch1);
        Mockito.when(mockBatchDao.getBatchAssociates(Matchers.anyInt())).thenReturn(associateSet);
        

    }

    @BeforeMethod
    public void beforeEach() {
        batchService = new BatchesService(mockBatchDao);
    }

    //old test for a method that isn't used
//    @Test(enabled = false)
//    public void testGetBatchChartInfo() throws Exception {
//        List<BatchInfo> actualChartInfo = batchService.getBatchChartInfo(0L, 1L);
//        Assert.assertEquals(actualChartInfo.size(), 1);
//        Assert.assertTrue(actualChartInfo.contains(batch1));
//    }

    @Test(enabled = true)
    public void testGetBatches() throws Exception {
        List<BatchInfo> results = batchService.getBatches(new Long(50), new Long(250));
        List<BatchInfo> comparator = new ArrayList<>();
        comparator.add(batch1);
        comparator.add(batch2);
        Assert.assertEquals(comparator, results);
    }

    
//These method have no logic. They just call a method and return the result
//Are they worth implementing tests?
    
//    public void testGetAllBatches() throws Exception {
//    	
//    }
//    
//    public void getAllBatchesSortedByDate() throws Exception {
//    	
//    }
//    
//    public void getBatchById() throws Exception {
//    	
//    }
//    
//    @Test(enabled = false)
//    public void testGetAssociatesForBranch() throws Exception {
//        Set<AssociateInfo> associates = batchService.getAssociatesForBranch(1);
//        Assert.assertTrue(associateSet, associates);
//    }

    AssociateInfo createAssocInfo(int id, int batchId, String batchName, int clientId, String clientName,
                                  int msid, String msName, int currId, String currName) {
        AssociateInfo assoc = new AssociateInfo();
        assoc.setId(new Integer(id));
        assoc.setBatchId(new Integer(batchId));
        assoc.setBatchName(batchName);
        assoc.setBid(new Integer(batchId));
        assoc.setClid(new Integer(clientId));
        assoc.setClientId(new Integer(clientId));
        assoc.setClient(clientName);
        assoc.setMarketingStatusId(new Integer(msid));
        assoc.setMsid(new Integer(msid));
        assoc.setMarketingStatus(msName);
        assoc.setCurriculumId(new Integer(currId));
        assoc.setCurriculumName(currName);

        return assoc;
    }

    BatchInfo createBatchInfo(int id, String name, String curriculumName, long startLong, long endLong) {
        BatchInfo info = new BatchInfo();

        info.setId(new Integer(id));
        info.setBatchName(name);
        info.setCurriculumName(curriculumName);
        info.setStartLong(startLong);
        info.setStartTs(new Timestamp(startLong));
        info.setEndLong(endLong);

        return info;
    }
}