package com.revature.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;
import com.revature.services.BatchesService;

public class testBatchesService {

    Properties properties = new Properties();


    @BeforeClass
    public void beforeClass() throws IOException {

        /*
         * Load properties file
         */
        File configFile = new File("src/main/resources/tests.properties");
        FileInputStream fileInput = new FileInputStream(configFile);
        properties.load(fileInput);
        fileInput.close();

    }

    @Test
    public void testGetBatchChartInfo() throws IOException {
        BatchesService batchesService = new BatchesService();

        List<Map<String, Object>> chartInfo = batchesService.getBatchChartInfo(
                Timestamp.valueOf(properties.getProperty("from_date")).getTime(),
                Timestamp.valueOf(properties.getProperty("to_date")).getTime());

        for (Map<String, Object> curriculumData : chartInfo) {
            Assert.assertNotNull(curriculumData.get("value"));
        }
    }

    @Test
    public void testGetBatchChartInfoNeg() throws IOException {

        BatchesService batchesService = new BatchesService();
        //dates given do not make a date range
        List<Map<String, Object>> chartInfo = batchesService.getBatchChartInfo(
                Timestamp.valueOf(properties.getProperty("to_date")).getTime(),
                Timestamp.valueOf(properties.getProperty("from_date")).getTime());
        Assert.assertTrue(chartInfo.isEmpty());


    }

    @Test(enabled = false)
    public void testGetBatchInfoNeg() throws IOException {
        try {
            BatchesService batchesService = new BatchesService();
            batchesService.getBatchInfo(-1L);
            Assert.fail("should throw exception");
        } catch (IOException e) {

        }
    }

    @Test
    public void testGetBatchInfo() throws IOException {
        BatchesService batchesService = new BatchesService();
        BatchInfo batch = batchesService.getBatchInfo(1L);

        Assert.assertEquals(1L, batch.getId().longValue());
    }


    @Test
    public void testGetMappedData() throws IOException {
        BatchesService batchesService = new BatchesService();

        Map<String, Integer> mappedOrUnmapped = batchesService.getMappedData(1L);
        Assert.assertTrue(mappedOrUnmapped.containsKey(properties.getProperty("mapped")));
        Assert.assertTrue(mappedOrUnmapped.containsKey(properties.getProperty("unmapped")));


    }

    @Test(expectedExceptions=IOException.class)
    public void testGetMappedDataNeg() throws IOException {
            BatchesService batchesService = new BatchesService();
            Map<String, Integer> mappedOrUnmapped = batchesService.getMappedData(-1L);
    }


    @Test
    public void testGetBatches() throws IOException {
        long firstDate = Timestamp.valueOf(properties.getProperty("from_date")).getTime();
        long secondDate = Timestamp.valueOf(properties.getProperty("to_date")).getTime();
        BatchesService batchesService = new BatchesService();
        List<BatchInfo> batches = batchesService.getBatches(firstDate, secondDate);

        for (BatchInfo batch : batches) {
            Assert.assertNotNull(batch.getBatchName());
        }

    }

    @Test
    public void testGetBatchesNeg() throws IOException {
        //dates in wrong order make it so it's not a date range
        long firstDate = Timestamp.valueOf(properties.getProperty("to_date")).getTime();
        long secondDate = Timestamp.valueOf(properties.getProperty("from_date")).getTime();
        BatchesService batchesService = new BatchesService();
        List<BatchInfo> batches = batchesService.getBatches(firstDate, secondDate);
        Assert.assertEquals(0, batches.size());
    }

    @Test
    public void testGetAssociates() throws IOException {

        try {
            BatchesService batchesService = new BatchesService();
            List<AssociateInfo> associates = batchesService.getAssociates(Long.parseLong(properties.getProperty("batch_id")));
            for (AssociateInfo associate : associates) {
                Assert.assertNotNull(associate.getId());
            }
        } catch (IOException e) {
            Assert.fail("[testGetAssociate] exception");
        }
    }

    @Test
    public void testGetAssociatesNeg() throws IOException {

        try {
            BatchesService batchesService = new BatchesService();
            batchesService.getAssociates(Long.parseLong(properties.getProperty("improper_batch_id")));
            Assert.fail();
        } catch (IOException supposedToHappen) {
        }
    }
}
