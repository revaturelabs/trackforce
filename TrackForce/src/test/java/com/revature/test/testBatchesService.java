package com.revature.test;

import java.sql.Timestamp;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.revature.model.BatchInfo;
import com.revature.services.BatchesService;

public class testBatchesService {

    @Test
    public void testGetBatches() {
        long firstDate = Timestamp.valueOf("2017-09-10 10:10:10.0").getTime();
        long secondDate = Timestamp.valueOf("2017-12-30 10:10:10.0").getTime();
        BatchesService batchesService = new BatchesService();
        List<BatchInfo> batches = batchesService.getBatches(firstDate, secondDate);
        for (BatchInfo bat : batches) {
            System.out.println(bat.getBatchName());
        }
        Assert.assertNotNull(batches);
    }
    
    @Test
    public void testGetBatchChartInfo() {

        long firstDate = Timestamp.valueOf("2017-09-10 10:10:10.0").getTime();
        long secondDate = Timestamp.valueOf("2017-12-30 10:10:10.0").getTime();

        BatchesService batchesService = new BatchesService();
        List<Map<String, Object>> results = batchesService.getBatchChartInfo(firstDate, secondDate);

        System.out.println(results);
    }

    @Test
    public void testGetBatchChartInfoNegative() {

        long firstDate = Timestamp.valueOf("2017-12-30 10:10:10.0").getTime();
        long secondDate = Timestamp.valueOf("2017-09-10 10:10:10.0").getTime();

        BatchesService batchesService = new BatchesService();
        List<Map<String, Object>> results = batchesService.getBatchChartInfo(firstDate, secondDate);

        System.out.println(results);
    }

}
