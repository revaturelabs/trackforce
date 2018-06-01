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
<<<<<<< HEAD
		return WaitToLoad.findDynamicElement(d, By.cssSelector("[href='/NGTrackForce/associate-listing']"), 2);
	}

	public static WebElement getAssociateListHeader(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/app-associate-list/div/h3"), 2);
=======
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("associatesTab")), 10);
	}

	public static WebElement getAssociateListHeader(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.tagName(prop.getProperty("associatesHeader")), 10);
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
	}

	public static String getCurrentURL(WebDriver d) {
		return d.getCurrentUrl();
	}

	// ********************TEXT FIELDS ************************

	// Search By Text input field
	public static WebElement searchByTextInputField(WebDriver d) {
<<<<<<< HEAD
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"name\"]"), 2);
=======
		return WaitToLoad.findDynamicElement(d, By.xpath(prop.getProperty("associateText")), 10);
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
	}

	// *************DROP DOWNS ****************************

	// Marketing Status drop down
	public static WebElement marketingStatusDropDown(WebDriver d) {
<<<<<<< HEAD
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"mStatus\"]"), 2);
=======
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("associateMarketingStatus")), 10);
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
	}

	// Curriculum drop down
	public static WebElement curriculumDropDown(WebDriver d) {
<<<<<<< HEAD
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"curriculum\"]"), 2);
=======
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("associateCurriculum")), 10);
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
	}

	// Client drop down
	public static WebElement clientDropDown(WebDriver d) {
<<<<<<< HEAD
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"client\"]"), 2);
=======
		return WaitToLoad.findDynamicElement(d, By.cssSelector(prop.getProperty("associateClient")), 10);
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a

	}

	// ************************** UPDATE COMPONENTS ************************

	// Update by Marketing Status drop down
	public static WebElement updateByMarketingStatusDropDown(WebDriver d) {
<<<<<<< HEAD

		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"uStatus\"]"), 2);

=======
		return WaitToLoad.findDynamicElement(d,
				By.cssSelector(prop.getProperty("associateUpdateMarketing")), 10);
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
	}

	// Client drop down
	public static WebElement clientUpdateDropDown(WebDriver d) {

<<<<<<< HEAD
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"uclient\"]"), 2);

=======
		return WaitToLoad.findDynamicElement(d, 
				By.cssSelector(prop.getProperty("associateUpdateClient")), 10);
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
	}

	// Update button
	public static WebElement updateButton(WebDriver d) {

<<<<<<< HEAD
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"submit\"] "), 2);

=======
		return WaitToLoad.findDynamicElement(d,
				By.cssSelector(prop.getProperty("associateUpdate")), 10);
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
	}

	// ****************** CHECKBOXES **************************

	// Edit check box for first row in the field
	public static WebElement editCheckBox(WebDriver d) {

		return d.findElement(By.cssSelector(prop.getProperty("associateEditCheckbox")));
	}

	// ****************** MARKETINGSTATUS TEXT **************************
	public static WebElement MarketingStatusText(WebDriver d) {

<<<<<<< HEAD
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/tbody/tr[3]/td[5]"), 2);

=======
		return WaitToLoad.findDynamicElement(d,
				By.cssSelector(prop.getProperty("associateMarketingStatusText")), 10);
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
	}

	// ****************SORT COMPONENTS ******************************

	public static WebElement sortByAssociateId(WebDriver d) {
<<<<<<< HEAD

		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[2]"), 2);

	}

	public static WebElement sortByFirstName(WebDriver d) {

		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[3]"), 2);

	}

	public static WebElement sortByLastName(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[4]"), 2);

	}

	public static WebElement sortByMarketingStatus(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[5]"), 2);

	}

	public static WebElement sortByClient(WebDriver d) {

		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[6]"), 2);

	}

	public static WebElement sortByBatch(WebDriver d) {

		return WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[7]"), 2);

=======
		return WaitToLoad.findDynamicElement(d,
				By.cssSelector(prop.getProperty("associateIdSort")), 10);
	}

	public static WebElement sortByFirstName(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
				By.cssSelector(prop.getProperty("associateFNameSort")), 10);
	}

	public static WebElement sortByLastName(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
				By.cssSelector(prop.getProperty("associateLNameSort")), 10);
	}

	public static WebElement sortByMarketingStatus(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
				By.cssSelector(prop.getProperty("associateMarketingStatusSort")), 10);
	}

	public static WebElement sortByClient(WebDriver d) {
		return WaitToLoad.findDynamicElement(d,
				By.cssSelector(prop.getProperty("associateClientSort")), 10);
	}

	public static WebElement sortByBatch(WebDriver d) {
		return WaitToLoad.findDynamicElement(d, 
				By.cssSelector(prop.getProperty("associateBatchSort")), 10);
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
	}

	// ************* NUMBER OF ROWS IN ASSOCIATE TABLE *************************
	
	public static List<WebElement> associateIdList(WebDriver d) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
		List<WebElement> list = d.findElements(By.xpath("//*[@id=\"info\"]/table/tbody/*/td[2]/a"));


=======
		List<WebElement> list = d.findElements(By.xpath(prop.getProperty("associateIdList")));
//		System.out.println("Retrieved associate id into list");
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
		return list;
	}

	public static List<WebElement> firstNameList(WebDriver d) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD

		List<WebElement> list = d.findElements(By.xpath(" //*[@id=\"info\"]/table/tbody/*/td[3]"));


=======
		List<WebElement> list = d.findElements(By.xpath(prop.getProperty("associateFNameList")));
//		System.out.println("Retrieved associate first name into list");
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
		return list;
	}

	public static List<WebElement> lastNameList(WebDriver d) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD

		List<WebElement> list = d.findElements(By.xpath("//*[@id=\"info\"]/table/tbody/*/td[4]"));


=======
		List<WebElement> list = d.findElements(By.xpath(prop.getProperty("associateLNameList")));
//		System.out.println("Retrieved associate last name into list");
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
		return list;
	}

	public static List<WebElement> marketingStatusList(WebDriver d) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD

		List<WebElement> list = d.findElements(By.xpath("//*[@id=\"info\"]/table/tbody/*/td[5]"));


=======
		List<WebElement> list = 
				d.findElements(By.xpath(prop.getProperty("associateMarketingStatusList")));
//		System.out.println("Retrieved associate marketing into list");
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
		return list;
	}

	public static List<WebElement> clientNameList(WebDriver d) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD

		List<WebElement> list = d.findElements(By.xpath("//*[@id=\"info\"]/table/tbody/*/td[6]"));


=======
		List<WebElement> list = d.findElements(By.xpath(prop.getProperty("associateClientList")));
//		System.out.println("Retrieved client name into list");
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
		return list;
	}

	public static List<WebElement> batchNameList(WebDriver d) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD

		List<WebElement> list = d.findElements(By.xpath("//*[@id=\"info\"]/table/tbody/*/td[7]"));


=======
		List<WebElement> list = d.findElements(By.xpath(prop.getProperty("associateBatchList")));
//		System.out.println("Retrieved batch name into list");
>>>>>>> 7a0ff14ae4f77ffa07d3cce1e8151107b9ce164a
		return list;
	}

}
