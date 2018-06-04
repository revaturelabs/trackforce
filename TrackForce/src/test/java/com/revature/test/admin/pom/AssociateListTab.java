package com.revature.test.admin.pom;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class AssociateListTab {

	static WebElement e = null;
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

	// Associate List tab on navbar
	public static WebElement getAssociateListTab(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("associatesTab")), 2);
	}

	public static WebElement getAssociateListHeader(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.tagName(prop.getProperty("associatesHeader")), 2);
	}

	public static String getCurrentURL(WebDriver d) {
		return d.getCurrentUrl();
	}

	// ********************TEXT FIELDS ************************

	// Search By Text input field
	public static WebElement searchByTextInputField(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath(prop.getProperty("associateText")), 2);
	}

	// *************DROP DOWNS ****************************

	// Marketing Status drop down
	public static WebElement marketingStatusDropDown(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("associateMarketingStatus")), 2);
	}

	// Curriculum drop down
	public static WebElement curriculumDropDown(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("associateCurriculum")), 2);
	}

	// Client drop down
	public static WebElement clientDropDown(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("associateClient")), 2);

	}

	// ************************** UPDATE COMPONENTS ************************

	// Update by Marketing Status drop down
	public static WebElement updateByMarketingStatusDropDown(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
				By.cssSelector(prop.getProperty("associateUpdateMarketing")), 2);
	}

	// Client drop down
	public static WebElement clientUpdateDropDown(WebDriver d) {

		return WaitToLoad.findDynamicElement(d, 
				By.cssSelector(prop.getProperty("associateUpdateClient")), 2);
	}

	// Update button
	public static WebElement updateButton(WebDriver d) {

		return WaitToLoad.findDynamicElement(d,
				By.cssSelector(prop.getProperty("associateUpdate")), 2);
	}

	// ****************** CHECKBOXES **************************

	// Edit check box for first row in the field
	public static WebElement editCheckBox(WebDriver d) {

		return d.findElement(By.cssSelector(prop.getProperty("associateEditCheckbox")));
	}

	// ****************** MARKETINGSTATUS TEXT **************************
	public static WebElement MarketingStatusText(WebDriver d) {

		return WaitToLoad.findDynamicElement(d,
				By.cssSelector(prop.getProperty("associateMarketingStatusText")), 2);
	}

	// ****************SORT COMPONENTS ******************************

	public static WebElement sortByAssociateId(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
				By.cssSelector(prop.getProperty("associateIdSort")), 2);
	}

	public static WebElement sortByFirstName(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
				By.cssSelector(prop.getProperty("associateFNameSort")), 2);
	}

	public static WebElement sortByLastName(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
				By.cssSelector(prop.getProperty("associateLNameSort")), 2);
	}

	public static WebElement sortByMarketingStatus(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
				By.cssSelector(prop.getProperty("associateMarketingStatusSort")), 2);
	}

	public static WebElement sortByClient(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
				By.cssSelector(prop.getProperty("associateClientSort")), 2);
	}

	public static WebElement sortByBatch(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, 
				By.cssSelector(prop.getProperty("associateBatchSort")), 2);
	}

	// ************* NUMBER OF ROWS IN ASSOCIATE TABLE *************************
	public static List<WebElement> associateIdList(WebDriver d) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> list = d.findElements(By.xpath(prop.getProperty("associateIdList")));
//		System.out.println("Retrieved associate id into list");
		return list;
	}

	public static List<WebElement> firstNameList(WebDriver d) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> list = d.findElements(By.xpath(prop.getProperty("associateFNameList")));
//		System.out.println("Retrieved associate first name into list");
		return list;
	}

	public static List<WebElement> lastNameList(WebDriver d) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> list = d.findElements(By.xpath(prop.getProperty("associateLNameList")));
//		System.out.println("Retrieved associate last name into list");
		return list;
	}

	public static List<WebElement> marketingStatusList(WebDriver d) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> list = 
				d.findElements(By.xpath(prop.getProperty("associateMarketingStatusList")));
//		System.out.println("Retrieved associate marketing into list");
		return list;
	}

	public static List<WebElement> clientNameList(WebDriver d) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> list = d.findElements(By.xpath(prop.getProperty("associateClientList")));
//		System.out.println("Retrieved client name into list");
		return list;
	}

	public static List<WebElement> batchNameList(WebDriver d) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> list = d.findElements(By.xpath(prop.getProperty("associateBatchList")));
//		System.out.println("Retrieved batch name into list");
		return list;
	}

}
