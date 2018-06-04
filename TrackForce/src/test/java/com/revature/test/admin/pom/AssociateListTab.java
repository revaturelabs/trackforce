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
<<<<<<< HEAD
		return WaitToLoad.findDynamicElement(d, By.xpath(prop.getProperty("associateMarketingStatus")), 2);
=======
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("associateMarketingStatus")), 2);
>>>>>>> cb49ca481ec07ab41c005ee90ea88914e2a64696
	}

	// Curriculum drop down
	public static WebElement curriculumDropDown(WebDriver d) {
<<<<<<< HEAD
		return WaitToLoad.findDynamicElement(d, By.xpath(prop.getProperty("associateCurriculum")), 2);
=======
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("associateCurriculum")), 2);
>>>>>>> cb49ca481ec07ab41c005ee90ea88914e2a64696
	}

	// Client drop down
	public static WebElement clientDropDown(WebDriver d) {
<<<<<<< HEAD
		return WaitToLoad.findDynamicElement(d, By.xpath(prop.getProperty("associateClient")), 2);
=======
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("associateClient")), 2);
>>>>>>> cb49ca481ec07ab41c005ee90ea88914e2a64696

	}

	// ************************** UPDATE COMPONENTS ************************

	// Update by Marketing Status drop down
	public static WebElement updateByMarketingStatusDropDown(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
<<<<<<< HEAD
				By.xpath(prop.getProperty("associateUpdateMarketing")), 2);
=======
				By.cssSelector(prop.getProperty("associateUpdateMarketing")), 2);
>>>>>>> cb49ca481ec07ab41c005ee90ea88914e2a64696
	}

	// Client drop down
	public static WebElement clientUpdateDropDown(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, 
<<<<<<< HEAD
				By.xpath(prop.getProperty("associateUpdateClient")), 2);
=======
				By.cssSelector(prop.getProperty("associateUpdateClient")), 2);
>>>>>>> cb49ca481ec07ab41c005ee90ea88914e2a64696
	}

	// Update button
	public static WebElement updateButton(WebDriver d) {

		return WaitToLoad.findDynamicElement(d,
<<<<<<< HEAD
				By.xpath(prop.getProperty("associateUpdate")), 2);
=======
				By.cssSelector(prop.getProperty("associateUpdate")), 2);
>>>>>>> cb49ca481ec07ab41c005ee90ea88914e2a64696
	}

	// ****************** CHECKBOXES **************************

	// Edit check box for first row in the field
	public static WebElement editCheckBox(WebDriver d) {

		return d.findElement(By.xpath(prop.getProperty("associateEditCheckbox")));
	}

	// ****************** MARKETINGSTATUS TEXT **************************
	public static WebElement MarketingStatusText(WebDriver d) {

		return WaitToLoad.findDynamicElement(d,
<<<<<<< HEAD
				By.xpath(prop.getProperty("associateMarketingStatusText")), 2);
=======
				By.cssSelector(prop.getProperty("associateMarketingStatusText")), 2);
>>>>>>> cb49ca481ec07ab41c005ee90ea88914e2a64696
	}

	// ****************SORT COMPONENTS ******************************

	public static WebElement sortByAssociateId(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
<<<<<<< HEAD
				By.xpath(prop.getProperty("associateIdSort")), 2);
=======
				By.cssSelector(prop.getProperty("associateIdSort")), 2);
>>>>>>> cb49ca481ec07ab41c005ee90ea88914e2a64696
	}

	public static WebElement sortByFirstName(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
<<<<<<< HEAD
				By.xpath(prop.getProperty("associateFNameSort")), 2);
=======
				By.cssSelector(prop.getProperty("associateFNameSort")), 2);
>>>>>>> cb49ca481ec07ab41c005ee90ea88914e2a64696
	}

	public static WebElement sortByLastName(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
<<<<<<< HEAD
				By.xpath(prop.getProperty("associateLNameSort")), 2);
=======
				By.cssSelector(prop.getProperty("associateLNameSort")), 2);
>>>>>>> cb49ca481ec07ab41c005ee90ea88914e2a64696
	}

	public static WebElement sortByMarketingStatus(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
<<<<<<< HEAD
				By.xpath(prop.getProperty("associateMarketingStatusSort")), 2);
=======
				By.cssSelector(prop.getProperty("associateMarketingStatusSort")), 2);
>>>>>>> cb49ca481ec07ab41c005ee90ea88914e2a64696
	}

	public static WebElement sortByClient(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
<<<<<<< HEAD
				By.xpath(prop.getProperty("associateClientSort")), 2);
=======
				By.cssSelector(prop.getProperty("associateClientSort")), 2);
>>>>>>> cb49ca481ec07ab41c005ee90ea88914e2a64696
	}

	public static WebElement sortByBatch(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, 
<<<<<<< HEAD
				By.xpath(prop.getProperty("associateBatchSort")), 2);
=======
				By.cssSelector(prop.getProperty("associateBatchSort")), 2);
>>>>>>> cb49ca481ec07ab41c005ee90ea88914e2a64696
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
