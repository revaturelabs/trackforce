package com.revature.test.services;

import com.revature.dao.BatchDao;
import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;
import com.revature.services.BatchesService;
import com.revature.test.BaseTest;
import org.hibernate.Session;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class BatchesServiceTest extends BaseTest {

    @Mock
    private BatchDao mockBatchDao;

    private BatchesService batchService;
    private AssociateInfo assoc1, assoc2, assoc3;
    private BatchInfo batch1, batch2, batch3;

    @BeforeTest
    public void beforeAll() throws IOException {
        MockitoAnnotations.initMocks(this);

        Map<Integer, BatchInfo> mockBatchMap = new HashMap();

        assoc1 = createAssocInfo(1, 1, "b1", 1, "c1", 1, "s1", 1, "c1");
        assoc2 = createAssocInfo(2, 2, "b2", 2, "c2", 2, "s2", 2, "c2");
        assoc3 = createAssocInfo(3, 3, "b3", 3, "c3", 3, "s3", 3, "c3");

        batch1 = createBatchInfo(1, "b1", "c1", 0, 100);
        batch1.setAssociates(new HashSet<>(Arrays.asList(assoc1)));

        batch2 = createBatchInfo(2, "b2", "c2", 100, 200);
        batch2.setAssociates(new HashSet<>(Arrays.asList(assoc2)));

        batch3 = createBatchInfo(3, "b3", "c3", 200, 300);
        batch3.setAssociates(new HashSet<>(Arrays.asList(assoc3)));

        mockBatchMap.put(batch1.getId(), batch1);
        mockBatchMap.put(batch2.getId(), batch2);
        mockBatchMap.put(batch3.getId(), batch3);

        //Mockito.when(mockBatchDao.getBatch(null))).thenReturn(mockBatchMap);

        // mock init batch cache
        new BatchesService().execute();

    }

    @BeforeMethod
    public void beforeEach() {
        batchService = new BatchesService();
    }

    @Test
    public void testGetBatchChartInfo() throws Exception {
        List<BatchInfo> actualChartInfo = batchService.getBatchChartInfo(0L, 1L);
        Assert.assertEquals(actualChartInfo.size(), 1);
        Assert.assertTrue(actualChartInfo.contains(batch1));
    }

    @Test
    public void testGetBatches() throws Exception {
        List<BatchInfo> actualChartInfo = batchService.getBatchChartInfo(0L, 100L);
        Assert.assertEquals(actualChartInfo.size(), 2);
        Assert.assertTrue(actualChartInfo.contains(batch1));
        Assert.assertTrue(actualChartInfo.contains(batch2));
    }

    @Test
    public void testGetAssociates() throws Exception {
        Set<AssociateInfo> assocs = batchService.getAssociatesForBranch(1);
        Assert.assertEquals(1, assocs.size());
        Assert.assertTrue(assocs.contains(assoc1));
    }

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