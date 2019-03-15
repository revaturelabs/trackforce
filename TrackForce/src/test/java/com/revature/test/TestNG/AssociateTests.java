package com.revature.test.TestNG;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.hibernate.Session;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.entity.TfInterview;
import com.revature.services.InterviewService;
import com.revature.test.pom.AssociateHome;
import com.revature.test.pom.Login;
import com.revature.test.pom.NavBar;
import com.revature.utils.HibernateUtil;

import oracle.sql.DATE;

public class AssociateTests {
	static WebDriver wd;
	static WebDriverWait wait;
	String username = "cyril";
	String password = "cyril";
	public final String url = "http://localhost:4200/";
//	public final String url = "http://34.227.178.103:8090/NGTrackForce/";


	@BeforeClass
	public void LoadWebpage() {
		File chrome = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		wd = new ChromeDriver();
		wd.get(url);// EnvManager.NGTrackForce_URL+"login/");
		wait = new WebDriverWait(wd, 10);
		wait.until(ExpectedConditions.urlContains(url));// EnvManager.NGTrackForce_URL+"login/"));
		
	}
	/**
	 * Login in as an associate 
	 */
	@Test(priority = 0)
	public void LoginAssociate() {
		wait.until(ExpectedConditions.elementToBeClickable(Login.getUsername(wd)));
		Login.getUsername(wd).sendKeys(username);
		Login.getPassword(wd).sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(Login.getSignInButton(wd)));
		Login.getSignInButton(wd).click();
		wait.until(ExpectedConditions.urlContains("/associate-view"));
		
	}

	/*
	 * Verifying that the tabs shows up on the associate's page when they log in 
	 */
	@Test(priority = 1)
	public void elementsOnPage() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText("My Interviews")));
	}
	
	/*
	 * Update first and last name, click submit and check if the 
	 * the names are indeed updated in associate information
	 * 
	 * Test pass
	 */
	@Test(priority = 2)
	public void updateInformation() {
		String first = "FirstName", last = "LasName";
		
		WebElement fName = AssociateHome.newFirstName(wd);
		WebElement lName = AssociateHome.newLastName(wd);
		WebElement submit = AssociateHome.submitName(wd);
		
		fName.clear();
		fName.sendKeys(first);
		fName.click();
		
		
		lName.clear();
		lName.sendKeys(last);
		lName.click();
		
		submit.click();
		
		String updatedFirst = AssociateHome.updatedFirstName(wd).getText();
		String updatedLast = AssociateHome.updatedLastName(wd).getText();
		Assert.assertEquals(updatedFirst, first);
		Assert.assertEquals(updatedLast, last);
		
	}
	
	/*
	 * Associate adding an interview.  Will take around a minute.  If interrupted and restarted,
	 * there is a large possibility of a failure due to an interview being generated at the same time
	 * (They are based on current time and date.), which isn't allowed.
	 */
	@Test(priority = 3)
	public void addInterview() {
		AssociateHome.interviewTab(wd).click();
		
		int beforeAddingInterview = new InterviewService().getAllInterviews().size();
		
		Select client = AssociateHome.chooseclient(wd);
		wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		client.selectByVisibleText("ADP");
		Select type = AssociateHome.chooseType(wd);
		type.selectByVisibleText("Online");
		
		WebElement date = wd.findElement(By.id("inputDate"));
		WebElement time = wd.findElement(By.id("inputTime"));
		
		Calendar cal = Calendar.getInstance();
		
		String month = "" + (cal.get(Calendar.MONTH) + 1);
		String day = "" + (cal.get(Calendar.DAY_OF_MONTH));
		String year = cal.get(Calendar.SECOND) + "" + (cal.get(Calendar.YEAR) % 100);
		if (month.length() == 1) {
			month = "0" + month;
		}
		if (day.length() == 1) {
			day = "0" + day;
		}
		
		String hour = "" + cal.get(Calendar.HOUR);
		String minute = "" + cal.get(Calendar.MINUTE);
		if (hour.length() == 1) {
			hour = "0" + hour;
		}
		if (minute.length() == 1) {
			minute = "0" + minute;
		}
		
		
		String dateInput = month + day + year;
		String timeInput = hour + minute + (cal.get(Calendar.AM_PM) == 0? "AM": "PM");
		date.sendKeys(dateInput);
		time.sendKeys(timeInput);
		wd.findElement(By.id("add-interview")).click();
		
		int afterAddingInterview = new InterviewService().getAllInterviews().size();
		
		
		Assert.assertEquals(afterAddingInterview, beforeAddingInterview + 1);
		
	}
	
	/*
	 * Verifying that associate can logout from their session
	 */
	@Test(priority = 4)
	public void LogOut() {
		wait.until(ExpectedConditions.elementToBeClickable(NavBar.getWelcomeDropdown(wd)));
		NavBar.getWelcomeDropdown(wd).click();
		wait.until(ExpectedConditions.elementToBeClickable(NavBar.getLogout(wd)));
		NavBar.getLogout(wd).click();
		wait.until(ExpectedConditions.urlContains(url));
	}

		
	@AfterSuite
	void quit() {
		wd.quit();
	}

}
