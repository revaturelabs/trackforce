package com.revature.services;

import static org.testng.Assert.assertNotNull;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;

public class BatchesServiceTest {

	BatchesService batchService = new BatchesService();

    @DataProvider(name = "batchName")
    public String[] batchNames() {
        String[] batchnames = new String[]{"1701 Jan09 Java", "1712 Dec11 Java AP-USF", "1709 Sep11 JTA"};
        return batchnames;
    }

    @DataProvider(name = "timeStamps")
    public Timestamp[][] timestamps() {
        Timestamp[][] timeStamps = new Timestamp[][]{
                {
                        timestampFromLocalDate(2017, 1,1),
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
     * some of the batches in the given time range, have null curriculumns, and
     * wasn't being properly handled; this test aims to make sure it is always handled properly
     * <p>
     * assumes we're using the data created by the StaticSalesForceData.sql script as of
     * this commit
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
        }
    }

    @Test(dataProvider = "timeStamps")
    public void getBatchChartInfo(Timestamp fromdate, Timestamp todate) {
        List<Map<String, Object>> result = batchService.getBatchChartInfo(fromdate.getTime(), todate.getTime());
        assertNotNull(result);
    }

	@Test(dataProvider="batchName")
	public void getBatchInfo(String batchName) {
		BatchInfo result = batchService.getBatchInfo(batchName);
		assertNotNull(result);
	}

	@Test(dataProvider="batchName")
	public void getMappedData(String batchName) {
		Map<String, Integer> result = batchService.getMappedData(batchName);
		assertNotNull(result);
	}

	@Test(dataProvider="timeStamps")
	public void getBatches(Timestamp fromdate, Timestamp todate) {
		List<BatchInfo> result = batchService.getBatches(fromdate.getTime(), todate.getTime());
		assertNotNull(result);
	}

	@Test(dataProvider="batchName")
	public void getAssociates(String batchName) {
		List<AssociateInfo> result = batchService.getAssociates(batchName);
		assertNotNull(result);
	}
}
