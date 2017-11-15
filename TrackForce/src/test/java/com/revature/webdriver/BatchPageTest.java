package com.revature.webdriver;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BatchPageTest {
	private static WebDriver drive = null;
	static BatchPage batchPage = null;

	@BeforeClass
	public void initializeDrivers() {
		System.out.println("BeforeClass");
		File f1 = new File("../TrackForce/src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", f1.getAbsolutePath());
		drive = new ChromeDriver();
		drive.get("http://localhost:8080/TrackForce/html/index.html#!/batchListing"); // Hard coded for now	
	}
	
	
	@Test(enabled = false)
	public void testBatchTabLink() {
		System.out.println("Testing Batch Page...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Clicked login to test link");
		batchPage.batchPageTab.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(drive.getCurrentUrl());
		assertTrue(drive.getCurrentUrl().contains("http://localhost:8080/TrackForce/html/index.html#!/batchListing"));
	}
	
	//Click First Associate Link and then Click First
	@Test(enabled = true)
	public void testFirstBatch() {
		
		System.out.println("Testing Batch Table Result #1...");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Clicked Batch #1 to test link");
		
		batchPage.firstBatchListing.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(drive.getCurrentUrl());
		assertTrue(drive.getCurrentUrl().contains("http://localhost:8080/TrackForce/html/index.html#!/batchDetails/1708%20Aug21%20.Net%20AP-USF"));
		
		
		System.out.println("Testing Associate Result #1...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Clicked Associate #1 to test link");
		batchPage.firstAssociateListing.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(drive.getCurrentUrl());
		assertTrue(drive.getCurrentUrl().contains("http://localhost:8080/TrackForce/html/index.html#!/form/214"));
		
		System.out.println("Testing Batch Table Result #1...");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Clicked Batch #1 to test link back to page");
		
		batchPage.firstBatchListingBack.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(drive.getCurrentUrl());
		assertTrue(drive.getCurrentUrl().contains("http://localhost:8080/TrackForce/html/index.html#!/batchDetails/1708%20Aug21%20.Net%20AP-USF"));
		
		
	
	}	
	
	
	// Close browser when we're done with testing
	@AfterClass
	public void afterClass() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("AfterClass");
		drive.close();
	}
	
	}
