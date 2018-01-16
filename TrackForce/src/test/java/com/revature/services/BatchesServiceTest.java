package com.revature.services;

import static org.testng.Assert.assertNotNull;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import com.revature.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;

public class BatchesServiceTest {

    BatchesService batchService = new BatchesService();

    @DataProvider(name = "batchIds")
    public Long[] batchIds() {
        return new Long[]{1L, 2L, 3L, 4L};
    }

    @DataProvider(name = "timeStamps")
    public Timestamp[][] timestamps() {
        Timestamp[][] timeStamps = new Timestamp[][]{
                {
                        timestampFromLocalDate(2017, 1, 1),
                        timestampFromLocalDate(2017, 12, 12)
                }
        };
        return timeStamps;
    }

    private Timestamp timestampFromLocalDate(int year, int month, int day) {
        return new Timestamp(LocalDate.of(year, month, day)
                .atStartOfDay().atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli());
    }

    @DataProvider(name = "largerRangeTimestamps")
    public Timestamp[][] largerRangeTimestamps() {
        return new Timestamp[][]{
                {
                        timestampFromLocalDate(2017, 1, 1),
                        timestampFromLocalDate(2017, 12, 12)
                }
        };
    }

    /**
     * <p>
     * Some of the batches in the given time range, have null curriculumns, and previously this
     * wasn't being properly handled; this test aims to make sure it is always handled properly
     * </p>
     * <p>
     * <p>
     * assumes we're using the data created by the StaticSalesForceData.sql script as of
     * this commit
     * </p>
     *
     * @param fromDate
     * @param toDate
     */
    @Test(dataProvider = "largerRangeTimestamps")
    public void testNullCurriculums(Timestamp fromDate, Timestamp toDate) {
        try {
            List<Map<String, Object>> result = batchService.getBatchChartInfo(fromDate.getTime(), toDate.getTime());
            assertNotNull(result);
        } catch (NullPointerException ex) {
            Assert.fail("Null curriculum in batches should not result in NullPointerException");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "timeStamps")
    public void getBatchChartInfo(Timestamp fromdate, Timestamp todate) throws IOException {
        List<Map<String, Object>> result = batchService.getBatchChartInfo(fromdate.getTime(), todate.getTime());
        assertNotNull(result);
    }

    @Test(dataProvider = "batchIds")
    public void getBatchInfo(Long id) throws IOException {
        BatchInfo result = batchService.getBatchInfo(id);
        assertNotNull(result);
    }

    @Test(dataProvider = "batchIds")
    public void getMappedData(Long id) throws IOException {
        Map<String, Integer> result = batchService.getMappedData(id);
        assertNotNull(result);
    }

    @Test(dataProvider = "timeStamps")
    public void getBatches(Timestamp fromdate, Timestamp todate) throws IOException {
        List<BatchInfo> result = batchService.getBatches(fromdate.getTime(), todate.getTime());
        assertNotNull(result);
    }

    @Test(dataProvider = "batchIds")
    public void getAssociates(Long id) throws IOException {
        List<AssociateInfo> result = batchService.getAssociates(id);
        assertNotNull(result);
    }
}
