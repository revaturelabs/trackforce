package com.revature.test.admin.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class AssociateListTab {

	static WebElement e = null;

	// Associate List tab on navbar
	public static WebElement getAssociateListTab(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector("[href='/associate-listing']"), 10);
	}

	public static WebElement getAssociateListHeader(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/app-associate-list/div/h3"), 10);
	}

	public static String getCurrentURL(WebDriver d) {
		return d.getCurrentUrl();
	}

	// ********************TEXT FIELDS ************************

	// Search By Text input field
	public static WebElement searchByTextInputField(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"name\"]"), 10);
	}

	// *************DROP DOWNS ****************************

	// Marketing Status drop down
	public static WebElement marketingStatusDropDown(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"mStatus\"]"), 10);
	}

	// Curriculum drop down
	public static WebElement curriculumDropDown(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"curriculum\"]"), 10);
	}

	// Client drop down
	public static WebElement clientDropDown(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"client\"]"), 10);

	}

	// ************************** UPDATE COMPONENTS ************************

	// Update by Marketing Status drop down
	public static WebElement updateByMarketingStatusDropDown(WebDriver d) {

		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"uStatus\"]"), 10);

	}

	// Client drop down
	public static WebElement clientUpdateDropDown(WebDriver d) {

		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"uclient\"]"), 10);

	}

	// Update button
	public static WebElement updateButton(WebDriver d) {

		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"submit\"] "), 10);

	}

	// ****************** CHECKBOXES **************************

	// Edit check box for first row in the field
	public static WebElement editCheckBox(WebDriver d) {

		return d.findElement(By.xpath("//*[@id=\"2\"]"));

	}

	// ****************** MARKETINGSTATUS TEXT **************************
	public static WebElement MarketingStatusText(WebDriver d) {

		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/tbody/tr[3]/td[5]"), 10);

	}

	// ****************SORT COMPONENTS ******************************

	public static WebElement sortByAssociateId(WebDriver d) {

		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[2]"), 10);

	}

	public static WebElement sortByFirstName(WebDriver d) {

		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[3]"), 10);

	}

	public static WebElement sortByLastName(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[4]"), 10);

	}

	public static WebElement sortByMarketingStatus(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[5]"), 10);

	}

	public static WebElement sortByClient(WebDriver d) {

		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[6]"), 10);

	}

	public static WebElement sortByBatch(WebDriver d) {

		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[7]"), 10);

	}

	// ************* NUMBER OF ROWS IN ASSOCIATE TABLE *************************
	public static List<WebElement> associateIdList(WebDriver d) {
		// int sizreturn 0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> list = d.findElements(By.xpath("//*[@id=\"info\"]/table/tbody/*/td[2]/a"));

		System.out.println("Retrieved associat id into list");

		return list;
	}

	public static List<WebElement> firstNameList(WebDriver d) {
		// int size = 0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> list = d.findElements(By.xpath(" //*[@id=\"info\"]/table/tbody/*/td[3]"));

		System.out.println("Retrieved associate first name into list");

		return list;
	}

	public static List<WebElement> lastNameList(WebDriver d) {
		// int size = 0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> list = d.findElements(By.xpath("//*[@id=\"info\"]/table/tbody/*/td[4]"));

		System.out.println("Retrieved associate last name into list");

		return list;
	}

	public static List<WebElement> marketingStatusList(WebDriver d) {
		// int size = 0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> list = d.findElements(By.xpath("//*[@id=\"info\"]/table/tbody/*/td[5]"));

		System.out.println("Retrieved associate marketing into list");

		return list;
	}

	public static List<WebElement> clientNameList(WebDriver d) {
		// int size = 0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> list = d.findElements(By.xpath("//*[@id=\"info\"]/table/tbody/*/td[6]"));

		System.out.println("Retrieved client name into list");

		return list;
	}

	public static List<WebElement> batchNameList(WebDriver d) {
		// int size = 0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> list = d.findElements(By.xpath("//*[@id=\"info\"]/table/tbody/*/td[7]"));

		System.out.println("Retrieved batch name into list");

		return list;
	}

}
