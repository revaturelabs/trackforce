package com.revature.test.admin.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.test.utils.WaitToLoad;

public class AssociateListTab {

	static WebElement element = null;

	// Associate List tab on navbar
	public static WebElement tab(WebDriver d) {
                                                   
			element = WaitToLoad.findDynamicElement(d, By.xpath("/html/body/app/app-root/div/app-navbar/nav/div/ul[1]/li[5]/a "), 10);
			return element;
	}

	// ********************TEXT FIELDS ************************

	// Search By Text input field
	public static WebElement searchByTextInputField(WebDriver d) {
			element = WaitToLoad.findDynamicElement(d, By.xpath("//*[@id=\"name\"]"), 10);
			return element;
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
	
	//************************** UPDATE COMPONENTS ************************

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
	public static boolean clientUpdateDropDown(WebDriver d) {
		
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

	// ****************SORT COMPONENTS ******************************

	public static WebElement sortByAssociateId(WebDriver d) {
		
		element = WaitToLoad                      
				.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[2]"), 10);
		return element;
	}

	public static WebElement sortByFirstName(WebDriver d) {
		
		element = WaitToLoad                      
				.findDynamicElement(d, By.xpath( "//*[@id=\"info\"]/table/thead/tr/th[3]"), 10);
		return element;
	}

	public static WebElement sortByLastName(WebDriver d) {
		element = WaitToLoad                      
				.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[4]"), 10);
		return element;
	}

	public static WebElement sortByMarketingStatus(WebDriver d) {
		element = WaitToLoad
				.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[5]"), 10);
		return element;
	}


	public static WebElement sortByClient(WebDriver d) {
	
		element = WaitToLoad
				.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[6]"), 10);
		
		return element;       
	}

	public static WebElement sortByBatch(WebDriver d) {
		
		element = WaitToLoad
				.findDynamicElement(d, By.xpath("//*[@id=\"info\"]/table/thead/tr/th[7]"), 10);
		return element;               
	}
	

	//************* NUMBER OF ROWS IN ASSOCIATE TABLE *************************
	public static List<WebElement> associateIdList(WebDriver d) {
		// int size = 0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		                                  
		List<WebElement> list = d         //*[@id="info"]/table/tbody//td[2]/a
				.findElements(By.xpath("//*[@id=\"info\"]/table/tbody/*/td[2]/a"));
		
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
		                                  
		List<WebElement> list = d        
				.findElements(By.xpath(" //*[@id=\"info\"]/table/tbody/*/td[3]"));
		
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
		                                  
		List<WebElement> list = d        
				.findElements(By.xpath("//*[@id=\"info\"]/table/tbody/*/td[4]"));
		
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
		                                  
		List<WebElement> list = d        
				.findElements(By.xpath("//*[@id=\"info\"]/table/tbody/*/td[5]"));
		
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
		                                  
		List<WebElement> list = d        
				.findElements(By.xpath("//*[@id=\"info\"]/table/tbody/*/td[6]"));
		
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
		                                  
		List<WebElement> list = d        
				.findElements(By.xpath("//*[@id=\"info\"]/table/tbody/*/td[7]"));
		
		System.out.println("Retrieved batch name into list");
		
		return list;             
	}  
	
	//*****************UNIQUE LIST ***********************************************
	//Get client names from drop down
	public static List<WebElement> uniqueClientList(WebDriver d) {
		// int size = 0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		                                  
		List<WebElement> list = d        
				.findElements(By.xpath("//*[@id=\"client\"]/*"));
		
		System.out.println("Retrieved unique clinet name into list");
		
		return list;             
	} 
	
	
}
