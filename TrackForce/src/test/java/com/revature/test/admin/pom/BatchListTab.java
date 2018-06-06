package com.revature.test.admin.pom;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class BatchListTab {
	static WebElement e = null;
	static List<String> start = new ArrayList<String>();
	static List<String> end = new ArrayList<String>();
	private static Properties prop = new Properties();
	static {
		InputStream locProps = Login.class.getClassLoader()
				.getResourceAsStream("tests.properties");
		try {
			prop.load(locProps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WebElement clickBatchListTab(WebDriver wd) {

		return WaitToLoad.findDynamicElement(wd, By.cssSelector(prop.getProperty("batchListTab")), 2);
	}

	public static WebElement clickAssociateListTab(WebDriver wd) {

		return WaitToLoad.findDynamicElement(wd, By.cssSelector(prop.getProperty("associatesTab")), 2);
	}

	public static String getCurrentURL(WebDriver d) {
		return d.getCurrentUrl();
	}

	public static String findAllBatchesHeader(WebDriver wd) {

		return WaitToLoad.findDynamicElement(wd, By.tagName(prop.getProperty("batchAllBatches")), 2).getText();

	}

	public static List<WebElement> getBatchNames(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath(prop.getProperty("batchTable")), 2);
		List<WebElement> rows = table_element
				.findElements(By.cssSelector(prop.getProperty("batchRows")));
		return rows;
	}

	// Gets first Batch Link in Batch List Tab
	public static WebElement getFirstBatchName(WebDriver wd) {
		// Local Host
		// WebElement table_element = WaitToLoad.findDynamicElement(wd,
		// By.xpath("//table[@class='table table-striped table-hover table-bordered']"),
		// 30);
		// WebElement row =
		// table_element.findElement(By.xpath("/html/body/app/div/app-batch-list/div/div[2]/div[1]/table/tbody/tr[1]/td[1]/a"));

		// Static Website
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.cssSelector(prop.getProperty("batchTableName")), 2);
		WebElement row = table_element
				.findElement(By.cssSelector(prop.getProperty("batchFirstBatchName")));
		return row;
	}

	// Gets all rows under the first batch in Batch List Tab
	public static List<WebElement> getAssociatesInfo(WebDriver wd) {
		// Commented out lines are for Local Host
		// WebElement table_element = WaitToLoad.findDynamicElement(wd,
		// By.xpath("//table[@class='table table-striped table-hover table-bordered']"),
		// 30);
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath(prop.getProperty("batchTableElements")), 2);
		List<WebElement> rows = table_element
				.findElements(By.xpath(prop.getProperty("batchAssociates")));
		return rows;
	}

	// Gets all Associate IDs under the first batch in Batch List Tab
	public static List<WebElement> getAssociatesIDs(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.cssSelector(prop.getProperty("batchTableName")), 2);
		List<WebElement> rows = table_element
				.findElements(By.cssSelector(prop.getProperty("batchRows")));
		List<WebElement> ID = new ArrayList<WebElement>();
		for (WebElement e : rows) {
			ID.addAll(e.findElements(By.xpath(prop.getProperty("batchAssociateIds"))));
		}
		return ID;
	}

	// Gets every Associate ID from ASSOCIATE LIST TAB
	public static List<WebElement> grabAssociatesIDs(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.cssSelector(prop.getProperty("batchTableName")), 2);
		List<WebElement> rows = table_element
				.findElements(By.cssSelector(prop.getProperty("batchRows")));
		List<WebElement> id = new ArrayList<WebElement>();
		for (WebElement e : rows) {
			id.addAll(e.findElements(By.xpath(prop.getProperty("batchAssociateListIds"))));
		}
		return id;
	}

	// Gets every Batch Name from ASSOCIATE LIST TAB
	public static List<WebElement> grabBatchNames(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.cssSelector(prop.getProperty("batchTableName")), 2);
		List<WebElement> rows = table_element
				.findElements(By.cssSelector(prop.getProperty("batchRows")));
		List<WebElement> batchNames = new ArrayList<WebElement>();
		for (WebElement e : rows) {
			batchNames.addAll(e.findElements(By.xpath(prop.getProperty("batchAssociateBatches"))));
		}
		return batchNames;
	}

	// Gets EVERY ROW from ASSOCIATE LIST TAB
	public static List<WebElement> grabAssociatesBatchInfo(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath(prop.getProperty("batchAssociateTable")), 2);
		List<WebElement> rows = table_element
				.findElements(By.xpath(prop.getProperty("batchAssociateRows")));
		return rows;
	}

	// finds the From date field using the id
	public static WebElement fromDateField(WebDriver wd) {
		return WaitToLoad.findDynamicElement(wd, By.id(prop.getProperty("batchFromDate")), 2);
	}

	// finds the To date field using the id
	public static WebElement toDateField(WebDriver wd) {
		return WaitToLoad.findDynamicElement(wd, By.id(prop.getProperty("batchToDate")), 2);
		
	}

	// finds the contents of the "from" box
	public static String fromDateContents(WebDriver wd) {
		WebElement fromDate = fromDateField(wd);
		return fromDate.getCssValue(prop.getProperty("batchDateContents"));
	}

	// finds the contents of the "to" box
	public static String toDateContents(WebDriver wd) {
		WebElement toDate = toDateField(wd);
		return toDate.getCssValue(prop.getProperty("batchDateContents"));
	}
	
	// finds the submit button using the xpath
	public static WebElement submitButton(WebDriver wd) {
		return WaitToLoad.findDynamicElement(wd,
				By.cssSelector(prop.getProperty("batchSubmitButton")), 2);
	}

	// finds the reset button using the xpath
	public static WebElement resetButton(WebDriver wd) {
		return WaitToLoad.findDynamicElement(wd,
				By.cssSelector(prop.getProperty("batchResetButton")), 2);
	}

	// grabs the start dates of all batches in the batch list, and stores them in a
	// List of WebElements
	public static List<WebElement> getStartDates(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath(prop.getProperty("batchTableElement")), 2);
		List<WebElement> rows = table_element
				.findElements(By.xpath(prop.getProperty("batchElements")));
		List<WebElement> columns = new ArrayList<WebElement>();
		for (WebElement e : rows) {
			columns.addAll(e.findElements(By.xpath(prop.getProperty("batchStartDates"))));
		}
		return columns;
	}

	// grabs the end dates of all batches in the batch list, and stores them in a
	// List of WebElements
	public static List<WebElement> getEndDates(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath(prop.getProperty("batchTableElement")), 2);
		List<WebElement> rows = table_element
				.findElements(By.xpath(prop.getProperty("batchElements")));
		List<WebElement> columns = new ArrayList<WebElement>();
		for (WebElement e : rows) {
			columns.addAll(e.findElements(By.xpath(prop.getProperty("batchEndDates"))));
		}
		return columns;
	}

	// verifies that the correct batches are displayed in the batch list with
	// respect to the dates in the To and From fields
	public static boolean correctResults(List<WebElement> Nfrom, List<WebElement> Nto, WebDriver wd) throws Throwable {
		boolean done = true;
		
		// populates "start" arrayList with data to be used in a comparison later in this method
		List<String> start = new ArrayList<String>();
		for (WebElement col : Nfrom) {
			start.add(col.getText());
		}
		start.removeAll(Arrays.asList("", null));
		
		// populates "start" arrayList with data to be used in a comparison later in this method
		List<String> end = new ArrayList<String>();
		for (WebElement col : Nto) {
			end.add(col.getText());
		}
		end.removeAll(Arrays.asList("", null));

		SimpleDateFormat format = new SimpleDateFormat("mm. dd, yyyy");
		Date date = new Date();
		System.out.println(format.format(date));

		for (int i = 0; i < start.size(); i++) {
			try {
				Date startDate = new SimpleDateFormat("mm. dd, yyyy").parse(start.get(i));
				Date endDate = new SimpleDateFormat("mm. dd, yyyy").parse(end.get(i));
				Date fromDate = new SimpleDateFormat("mm. dd, yyyy").parse(prop.getProperty("batchTestDateStart"));
				Date toDate = new SimpleDateFormat("mm. dd, yyyy").parse(prop.getProperty("batchTestDateEnd"));

				// if startDate is after toDate OR endDate is before fromDate, set done to false
				if (startDate.after(toDate) || endDate.before(fromDate)) {
					done = false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return done;
	}
	
	// gives definitions to/inserts data into start and end array lists which were declared at the top of the class
	public static void allBatches(List<WebElement> Nfrom, List<WebElement> Nto, WebDriver wd) throws Throwable {
		// populates the "start" arrayList with data to be used in the next method
		List<String> start = new ArrayList<String>();
		for (WebElement col : Nfrom) {
			start.add(col.getText());
		}
		start.removeAll(Arrays.asList("", null));
		
		// populates the "end" arrayList with data to be used in the next method
		List<String> end = new ArrayList<String>();
		for (WebElement col : Nto) {
			end.add(col.getText());
		}
		end.removeAll(Arrays.asList("", null));
	}
		
	// creates an array list of the current batches list and compares it to the lists defined in the above method
	// to ensure that they contain the same data
	public static boolean allBatchesAfterReset(List<WebElement> Nfrom, List<WebElement> Nto, WebDriver wd) throws Throwable {
		boolean done = true;
		// creates array list containing the data in the From fields of the batches which are showing after resetting
		List<String> afterReset = new ArrayList<String>();
		for (WebElement col : Nfrom) {
			afterReset.add(col.getText());
		}
		// creates array list containing the data in the From fields of the batches which are showing after resetting
		afterReset.removeAll(Arrays.asList("", null));
		List<String> postReset = new ArrayList<String>();
		for (WebElement col : Nto) {
			postReset.add(col.getText());
		}
		postReset.removeAll(Arrays.asList("", null));
		
		SimpleDateFormat format = new SimpleDateFormat("mm. dd, yyyy");
		Date date = new Date();
		System.out.println(format.format(date));

		for (int i = 0; i < start.size(); i++) {
			try {
				Date startDate = new SimpleDateFormat("mm. dd, yyyy").parse(start.get(i));
				Date endDate = new SimpleDateFormat("mm. dd, yyyy").parse(end.get(i));
				Date fromDate = new SimpleDateFormat("mm. dd, yyyy").parse(afterReset.get(i));
				Date toDate = new SimpleDateFormat("mm. dd, yyyy").parse(postReset.get(i));

				// if start and end dates match the From and To dates, then the lists match and the proper results are displayed
				if (startDate.equals(fromDate) && endDate.equals(toDate)) {
					done = true;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return done;
	}
}
