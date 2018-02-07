package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class AssociateListTab {

	static WebElement element = null;

	// Associate List tab on navbar
	public static boolean tab(WebDriver d) {
		
		try {
			element = WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/div/app-root/div/app-navbar/nav/div/ul[1]/li[4]/a"), 10);
			element.click();                                     
			System.out.println("Clicked Associate List tab");
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to click Associate List tab");
			return false;
		}
	}

	// ********************TEXT FIELDS ************************

	// Search By Text input field
	public static boolean searchByTextInputField(WebDriver d) {

		try {
			element = WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"name\"]"), 10);
			element.sendKeys("Test Name");
			System.out.println("Input value in Search By text input field");
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to Input value in Search By text input field");
			return false;
		}
	}

	// *************DROP DOWNS ****************************

	// Marketing Status drop down
	public static boolean marketingStatusDropDown(WebDriver d) {

		try {
			element = WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"mStatus\"]"), 10);
			element.sendKeys("MAPPED: TRAINING");
			System.out.println("Selected value from Marketing Status drop down");
			return true;
		} catch (Throwable e) {
			System.out.println("Failed to select value from Marketing Status drop down");
			return false;
		}
	}

	// Curriculum drop down
	public static boolean curriculumDropDown(WebDriver d) {
		
		try {
		element = WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"curriculum\"]"), 10);
		element.sendKeys("Java");
		System.out.println("Selected value from Curriculum drop down");
		return true;
		}catch (Throwable e) {
			System.out.println("Failed to select value from Curriculum drop down");
			return false;
		}
	}

	// Client drop down
	public static boolean clientDropDown(WebDriver d) {
		
		try {
		element = WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"client\"]"), 10);
		element.sendKeys("Accenture");
		System.out.println("Selected value from Client drop down");
		return true;
		}catch(Throwable e) {
			System.out.println("Failed to select value from Client drop down");
			return false;
		}
	}

	// Update by Marketing Status drop down
	public static boolean updateByMarketingStatusDropDown(WebDriver d) {
		
		try {
		element = WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"uStatus\"]"), 10);
		element.sendKeys("MAPPED: TRAINING");
		System.out.println("Selected value from Update By Marketing Status drop down");
		return true;
		}catch (Throwable e) {
			System.out.println("Failed to select value from Update By Marketing Status drop down");
			return false;
		}
	}

	// Client drop down
	public static boolean clientDropDrown(WebDriver d) {
		
		try {
		element = WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"uclient\"]"), 10);
		element.sendKeys("Accenture");
		System.out.println("Selected value from client drop down");
		return true;
		}catch (Throwable e) {
			System.out.println("Failed to select value from client drop down");
			return false;
		}
	}

	// ******************** BUTTONS **************************

	// Update button
	public static boolean updateButton(WebDriver d) {
		
		try {
		element = WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"submit\"] "), 10);
		element.click();
		System.out.println("Clicked update button");
		return true;
		}catch(Throwable e) {
			System.out.println("Failed to click update button");
		return false;
		}
	}

	// ****************** CHECKBOXES **************************

	// Edit check box for first row in the field
	public static boolean editCheckBox(WebDriver d) {
		
		try {
		element = d.findElement(By.xpath("//*[@id=\"70\"]"));
		element.click();
		return true;
		}catch(Throwable e) {
		return false;
		}
	}

	// ****************FILTER BUTTONS ******************************

	public static boolean editFilter(WebDriver d) {
		
		try {
		element = d
				.findElement(By.xpath("/html/body/app/div/app-associate-list/div/div[2]/div[1]/table/thead/tr/th[1] "));
		element.click();
		return true;
		} catch(Throwable e) {
			return false;
		}
	}

	public static boolean associateIdFilter(WebDriver d) {
		
		try {
		element = d
				.findElement(By.xpath("/html/body/app/div/app-associate-list/div/div[2]/div[1]/table/thead/tr/th[2] "));
		element.click();
		return true;
		}catch(Throwable e) {
			return false;
		}
	}

	public static boolean firstNameFilter(WebDriver d) {
		
		try {
		element = d
				.findElement(By.xpath("/html/body/app/div/app-associate-list/div/div[2]/div[1]/table/thead/tr/th[3] "));
		element.click();
		return true;
		}catch(Throwable e) {
			return false;
		}
	}

	public static boolean lastNameFilter(WebDriver d) {
		try {
		element = d
				.findElement(By.xpath("/html/body/app/div/app-associate-list/div/div[2]/div[1]/table/thead/tr/th[4] "));
		element.click();
		return true;
		}catch(Throwable e) {
			return false;
		}
	}

	public static boolean marketingStatusFilter(WebDriver d) {
		try {
		element = d
				.findElement(By.xpath("/html/body/app/div/app-associate-list/div/div[2]/div[1]/table/thead/tr/th[5] "));
		element.click();
		return true;
		}catch(Throwable e) {
			return false;
		}
	}

	public static boolean curriculumFilter(WebDriver d) {
		try {
		element = d
				.findElement(By.xpath("/html/body/app/div/app-associate-list/div/div[2]/div[1]/table/thead/tr/th[6] "));
		element.click();
		return true;
		}catch(Throwable e) {
			return false;
		}
	}

	public static boolean clientFilter(WebDriver d) {
		try {
		element = d
				.findElement(By.xpath("/html/body/app/div/app-associate-list/div/div[2]/div[1]/table/thead/tr/th[7] "));
		element.click();
		return true;
		}catch(Throwable e) {
			return false;
		}
	}

}
