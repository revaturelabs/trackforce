package com.revature.test.admin.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AssociateListTab {

	static WebElement element = null;

	// Associate List tab on navbar
	public static boolean tab(WebDriver d) {
		
		try {
			element = d
					.findElement(By.xpath("/html/body/app/div/app-associate-list/app-navbar/nav/div/ul[1]/li[4]/a "));
			element.click();
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

	// ********************TEXT FIELDS ************************

	// Search By Text input field
	public static boolean searchByTextInputField(WebDriver d) {

		try {
			element = d.findElement(By.xpath("//*[@id=\"name\"]"));
			element.sendKeys("Test Name");
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

	// *************DROP DOWNS ****************************

	// Marketing Status drop down
	public static boolean marketingStatusDropDown(WebDriver d) {

		try {
			element = d.findElement(By.xpath("//*[@id=\"mStatus\"]"));
			element.sendKeys("MAPPED: TRAINING");
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

	// Curriculum drop down
	public static boolean curriculumDropDown(WebDriver d) {
		
		try {
		element = d.findElement(By.xpath("//*[@id=\"curriculum\"]"));
		element.sendKeys("Java");
		return true;
		}catch (Throwable e) {
			return false;
		}
	}

	// Client drop down
	public static boolean clientDropDown(WebDriver d) {
		
		try {
		element = d.findElement(By.xpath("//*[@id=\"client\"]"));
		element.sendKeys("Accenture");
		return true;
		}catch(Throwable e) {
			return false;
		}
	}

	// Update by Marketing Status drop down
	public static boolean updateByMarketingStatusDropDown(WebDriver d) {
		
		try {
		element = d.findElement(By.xpath("//*[@id=\"uStatus\"]"));
		element.sendKeys("MAPPED: TRAINING");
		return true;
		}catch (Throwable e) {
			return false;
		}
	}

	// Client drop down
	public static boolean clientDropDrown(WebDriver d) {
		
		try {
		element = d.findElement(By.xpath("//*[@id=\"uclient\"]"));
		element.sendKeys("Accenture");
		return true;
		}catch (Throwable e) {
			return false;
		}
	}

	// ******************** BUTTONS **************************

	// Update button
	public static boolean updateButton(WebDriver d) {
		
		try {
		element = d.findElement(By.xpath("//*[@id=\"submit\"] "));
		element.click();
		return true;
		}catch(Throwable e) {
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
