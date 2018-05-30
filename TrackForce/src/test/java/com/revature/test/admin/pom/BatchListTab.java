package com.revature.test.admin.pom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.revature.test.utils.WaitToLoad;

public class BatchListTab {
	static WebElement e = null;
	static List<String> start = new ArrayList<String>();
	static List<String> end = new ArrayList<String>();

	public static WebElement clickBatchListTab(WebDriver wd) {

		return WaitToLoad.findDynamicElement(wd, By.cssSelector("[href='/batch-listing']"), 10);
	}

	public static WebElement clickAssociateListTab(WebDriver wd) {

		return WaitToLoad.findDynamicElement(wd, By.cssSelector("[href='/associate-listing']"), 10);
	}

	public static String getCurrentURL(WebDriver d) {
		return d.getCurrentUrl();
	}

	public static WebElement findAllBatchesHeader(WebDriver wd) {

		return WaitToLoad.findDynamicElement(wd, By.xpath("/html/body/app/app-batch-list/div/div[2]/div[1]/h3"), 10);

	}

	public static List<WebElement> getBatchNames(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		List<WebElement> rows = table_element
				.findElements(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr"));
		return rows;
	}

	// Gets first Batch Link in Batch List Tab
	public static WebElement getFirstBatchName(WebDriver wd) {

		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		WebElement row = table_element
				.findElement(By.xpath("/html/body/app/app-batch-list/div/div[2]/div[1]/table/tbody/tr[1]/td[1]/a"));
		return row;
	}

	// Gets all rows under the first batch in Batch List Tab
	public static List<WebElement> getAssociatesInfo(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath("/html/body/app/app-batch-details/div/div/div[2]/table"), 30);
		List<WebElement> rows = table_element
				.findElements(By.xpath("/html/body/app/app-batch-details/div/div/div[2]/table/tbody/tr"));
		return rows;
	}

	// Gets all Associate IDs under the first batch in Batch List Tab
	public static List<WebElement> getAssociatesIDs(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		List<WebElement> rows = table_element
				.findElements(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr"));
		List<WebElement> ID = new ArrayList<WebElement>();
		for (WebElement e : rows) {
			ID.addAll(e.findElements(By.xpath("td[1]")));
		}
		return ID;
	}

	// Gets every Associate ID from ASSOCIATE LIST TAB
	public static List<WebElement> grabAssociatesIDs(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		List<WebElement> rows = table_element
				.findElements(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr"));
		List<WebElement> id = new ArrayList<WebElement>();
		for (WebElement e : rows) {
			id.addAll(e.findElements(By.xpath("td[2]")));
		}
		return id;
	}

	// Gets every Batch Name from ASSOCIATE LIST TAB
	public static List<WebElement> grabBatchNames(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath("//table[@class='table table-striped table-hover table-bordered']"), 30);
		List<WebElement> rows = table_element
				.findElements(By.xpath("//table[@class='table table-striped table-hover table-bordered']/tbody/tr"));
		List<WebElement> batchNames = new ArrayList<WebElement>();
		for (WebElement e : rows) {
			batchNames.addAll(e.findElements(By.xpath("td[7]")));
		}
		return batchNames;
	}

	// Gets EVERY ROW from ASSOCIATE LIST TAB
	public static List<WebElement> grabAssociatesBatchInfo(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath("/html/body/app/app-associate-list/div/div[2]/div/table"), 30);
		List<WebElement> rows = table_element
				.findElements(By.xpath("/html/body/app/app-associate-list/div/div[2]/div/table/tbody/tr"));
		return rows;
	}

	// finds the From date field using the id
	public static WebElement fromDateField(WebDriver wd) {
		return WaitToLoad.findDynamicElement(wd, By.xpath("//*[@id=\"startDate\"]"), 10);
	}

	// finds the To date field using the id
	public static WebElement toDateField(WebDriver wd) {
		return WaitToLoad.findDynamicElement(wd, By.xpath("//*[@id=\"endDate\"]"), 10);
		
	}

	// finds the submit button using the xpath
	public static WebElement submitButton(WebDriver wd) {
		return WaitToLoad.findDynamicElement(wd,
				By.xpath("/html/body/app/app-batch-list/div/div[2]/div[2]/form/div[3]/input[1]"), 10);
	}

	// finds the reset button using the xpath
	public static WebElement resetButton(WebDriver wd) {
		return WaitToLoad.findDynamicElement(wd,
				By.xpath("/html/body/app/app-batch-list/div/div[2]/div[2]/form/div[3]/input[2]"), 10);
	}

	// grabs the start dates of all batches in the batch list, and stores them in a
	// List of WebElements
	public static List<WebElement> getStartDates(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath("/html/body/app/app-batch-list/div/div[2]/div[1]/table"), 30);
		List<WebElement> rows = table_element
				.findElements(By.xpath("/html/body/app/app-batch-list/div/div[2]/div[1]/table/tbody/tr"));
		List<WebElement> columns = new ArrayList<WebElement>();
		for (WebElement e : rows) {
			columns.addAll(e.findElements(By.xpath("td[2]")));
		}
		return columns;
	}

	// grabs the end dates of all batches in the batch list, and stores them in a
	// List of WebElements
	public static List<WebElement> getEndDates(WebDriver wd) {
		WebElement table_element = WaitToLoad.findDynamicElement(wd,
				By.xpath("/html/body/app/app-batch-list/div/div[2]/div[1]/table"), 30);
		List<WebElement> rows = table_element
				.findElements(By.xpath("/html/body/app/app-batch-list/div/div[2]/div[1]/table/tbody/tr"));
		List<WebElement> columns = new ArrayList<WebElement>();
		for (WebElement e : rows) {
			columns.addAll(e.findElements(By.xpath("td[3]")));
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
				Date fromDate = new SimpleDateFormat("mm. dd, yyyy").parse("9/15/2017");
				Date toDate = new SimpleDateFormat("mm. dd, yyyy").parse("11/15/2017");

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

	// gives definitions to/inserts data into start and end array lists which were
	// declared at the top of the class
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

	// creates an array list of the current batches list and compares it to the
	// lists defined in the above method
	// to ensure that they contain the same data
	public static boolean allBatchesAfterReset(List<WebElement> Nfrom, List<WebElement> Nto, WebDriver wd)
			throws Throwable {
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
