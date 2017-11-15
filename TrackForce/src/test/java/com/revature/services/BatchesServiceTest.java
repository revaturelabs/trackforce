package com.revature.services;

import static org.testng.Assert.assertNotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.model.AssociateInfo;
import com.revature.model.BatchInfo;

public class BatchesServiceTest {

	BatchesService bServ = new BatchesService();

	@DataProvider(name="batchName")
	public String[] batchNames()
	{
		String[] batchnames = new String[] {"1701 Jan09 Java", "1712 Dec11 Java AP-USF", "1709 Sep11 JTA"};
		return batchnames;
	}
	
	@DataProvider(name = "timeStamps")
	public Timestamp[][] timestamps() {
		Timestamp[][] timeStamps = new Timestamp[][] {
				{ new Timestamp(01 / 01 / 2017), new Timestamp(12 / 12 / 2017) } };
		return timeStamps;
	}

	@Test(dataProvider="timeStamps")
	public void getBatchChartInfo(Timestamp fromdate, Timestamp todate) {
		List<Map<String, Object>> result = bServ.getBatchChartInfo(fromdate.getTime(), todate.getTime());
		assertNotNull(result);
	}

	@Test(dataProvider="batchName")
	public void getBatchInfo(String batchName) {
		BatchInfo result = bServ.getBatchInfo(batchName);
		assertNotNull(result);
	}
	
	@Test(dataProvider="batchName")
	public void getMappedData(String batchName) {
		Map<String, Integer> result = bServ.getMappedData(batchName);
		assertNotNull(result);
	}
	
	@Test(dataProvider="timeStamps")
	public void getBatches(Timestamp fromdate, Timestamp todate) {
		List<BatchInfo> result = bServ.getBatches(fromdate.getTime(), todate.getTime());
		assertNotNull(result);
	}
	
	@Test(dataProvider="batchName")
	public void getAssociates(String batchName) {
		List<AssociateInfo> result = bServ.getAssociates(batchName);
		assertNotNull(result);
	}
}
