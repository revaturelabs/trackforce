package com.revature.test.services;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.daoimpl.MarketingStatusDaoImpl;
import com.revature.entity.TfMarketingStatus;
import com.revature.services.MarketingStatusService;
import com.revature.test.utils.Log;

public class MarketingStatusServicesTest {
	
	private MarketingStatusService service;
	private Properties props;

	@BeforeClass
	public void initialize() {
		
		service = new MarketingStatusService(new MarketingStatusDaoImpl());
		props = new Properties();

		try {
			FileInputStream propFile = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\database_entries.properties");
			props.load(propFile);
			propFile.close();
		} catch (FileNotFoundException e) {
			Log.Log.error(e.getMessage());
		} catch (IOException e) {
			Log.Log.error(e.getMessage());
		}
	}

	@Test
	public void testMarketingStatusGetAll() {
		
		List<TfMarketingStatus> allStatus = new ArrayList<TfMarketingStatus>();
		allStatus.addAll(service.getAllMarketingStatuses());
		
		assertNotNull(allStatus);
		assertFalse(allStatus.isEmpty());
	}

	@Test
	public void testMarketingStatusGetById() {
		TfMarketingStatus status = service.getMarketingStatusById(Integer.parseInt(props.getProperty("marketing_Id")));
	
		assertEquals(status.getName(), props.getProperty("marketingName"));
	}
}
