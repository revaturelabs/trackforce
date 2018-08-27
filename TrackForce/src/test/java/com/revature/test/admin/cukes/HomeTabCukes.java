package com.revature.test.admin.cukes;
import com.revature.test.admin.pom.AssociateListTab;
import com.revature.test.admin.pom.HomeTab;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.TestConfig;
import com.revature.test.utils.WaitToLoad;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class HomeTabCukes{
	
	@Given("^I am on the Home Page$")
	public static void i_am_on_the_Home_Page() throws InterruptedException  {
			Thread.sleep(1500);
			assertEquals(ServiceHooks.driver.getCurrentUrl(),"http://34.227.178.103:8090/NGTrackForce/app-home");
	}

	@Given("^Home Tab loads$")
	public static void home_tab_loads() {
		try {
			Thread.sleep(500);
			if (HomeTab.getCurrentURL(ServiceHooks.driver).equals(TestConfig.getBaseURL()) || 
					HomeTab.getCurrentURL(ServiceHooks.driver).equals(TestConfig.getBaseURL() + "/") || 
					HomeTab.getCurrentURL(ServiceHooks.driver).equals(TestConfig.getBaseURL() + "/root")) {
				
			}
			fail("Current URL does not equal the base URL, or does not end with / or /root");
			
		} catch (Throwable e) {
			System.out.println("Failed to get current URL");
			fail("Failed to get current URL");
			
		}
	}
	
	@When("^I click on the telephone link$")
	public static void i_click_on_the_telephone_link()  {
		try {
			Thread.sleep(1000);
			HomeTab.phone(ServiceHooks.driver).click();
			Thread.sleep(2000);
		} catch (Throwable e) {
			System.out.println(("Failed click on the phone link"));
			fail(("Failed click on the phone link"));
		}

	}

	@Then("^I should see the telephone number link open on a browser$")
	public void i_should_see_the_telephone_number_link_open_on_a_browser(){
		
		/*
		WaitToLoad.AlertToBePresent(ServiceHooks.driver,5);
		Alert alert = ServiceHooks.driver.switchTo().alert();
		assertEquals(alert.getText(),"Open Pick an app?");	
	    */
	}

	@When("^I click on the email link$")
	public static void i_click_on_the_email_link()  {

		try {
			Thread.sleep(1000);
			HomeTab.email(ServiceHooks.driver).click();
			
		} catch (Throwable e) {
			System.out.println(("Failed click on the email link"));
			fail(("Failed click on the email link"));
			
		}

	}

	@Then("^I should see the email link open on a browser$")
	public void i_should_see_the_email_link_open_on_a_browser() throws InterruptedException  {
		Thread.sleep(2500);		
		//Email opens in a location based on where the default email service is on the computer
	}

	@When("^I click on the website link$")
	public static void i_click_on_the_website_link()  {
		
		try {
			Thread.sleep(1000);
			//ServiceHooks.driver.findElement(By.xpath("/html/body/app-component/app-footer/footer/div/div/div[3]/ul/li[2]/a")).click();
			HomeTab.website(ServiceHooks.driver).click();
			
		} catch (Throwable e) {
			System.out.println(("Failed click on the website link"));
			fail(("Failed click on the website link"));
		}
		
	}

	@Then("^I should see the Revature website link open on a browser$")
	public void i_should_see_the_Revature_website_link_open_on_a_browser() throws InterruptedException  {
		Thread.sleep(20000);
		try {
		//get window handlers as list
		List<String> browserTabs = new ArrayList<String> (ServiceHooks.driver.getWindowHandles());
		//switch to new tab
		ServiceHooks.driver.switchTo().window(browserTabs .get(1));
		//check is it correct page opened or not (e.g. check page's title)
		assertEquals(ServiceHooks.driver.getCurrentUrl(),"https://revature.com/");
		//then close tab and get back
		ServiceHooks.driver.close();
		ServiceHooks.driver.switchTo().window(browserTabs.get(0));
		}catch(Throwable e) {
			fail("I should see Revature Website link open");
		}
	}
	
	@When("^I click on the elements in the Mapped pie chart$")
	public static void i_click_on_the_elements_in_the_Mapped_pie_chart() {
		try {
		Actions builder= new Actions(ServiceHooks.driver);
		WebElement canvas = HomeTab.getChart(ServiceHooks.driver, "Mapped");
		builder.build();
		System.out.println(canvas.getSize());
		builder.moveToElement(canvas, canvas.getSize().getWidth()/3, canvas.getSize().getHeight()/3)
		.click();
		builder.perform();
		}
		catch(Throwable e) {
			System.out.println(("Failed click on the elements in the mapped pie chart"));
			fail(("Failed click on the elements in the mapped pie chart"));
		}
	}
	
	@Then("^Should open with Mapped graphs$")
	public static void Should_open_with_Mapped_graph() {
		try {
		assertEquals(ServiceHooks.driver.getCurrentUrl(),TestConfig.getBaseURL()+"/client-mapped/0");
		ServiceHooks.driver.navigate().back();
		}
		catch(Throwable e) {
			System.out.println(("Failed to open with mapped graphs"));
			fail(("Failed to open with mapped graphs"));
		}
	}
	
	@When("^I click on the elements in the UnMapped pie chart$")
	public static void i_click_on_the_elements_in_the_UnMapped_pie_chart() {
		try {
		Actions builder= new Actions(ServiceHooks.driver);
		WebElement canvas = HomeTab.getChart(ServiceHooks.driver, "Unmapped");
		System.out.println(canvas.getSize());
		builder.build();
		builder.moveToElement(canvas, canvas.getSize().getWidth()/3, canvas.getSize().getHeight()/3)
		.click();
		builder.perform();
		}
		catch(Throwable e) {
			System.out.println(("Failed click on the elements in the UnMapped pie chart"));
			fail(("Failed click on the elements in the UnMapped pie chart"));
		}
	}
	
	@Then("^Should open with UnMapped graphs$")
	public static void Should_open_with_UnMapped_graph() {
		try {
		assertEquals(ServiceHooks.driver.getCurrentUrl(),TestConfig.getBaseURL()+"/skillset/0");
		ServiceHooks.driver.navigate().back();
		}catch(Throwable e) {
			System.out.println(("Failed to open with Unmapped graphs"));
			fail(("Failed to open with Unmapped graphs"));
		}
	}
	
	@When("^I click on the elements in the Mapped vs Unmapped Not Deployed chart$")
	public static void i_click_on_the_elements_in_the_Mapped_Vs_Unmapped_Not_Deployed_chart() {
		try {
		Actions builder= new Actions(ServiceHooks.driver);
		WebElement canvas = HomeTab.getChart(ServiceHooks.driver, "Mapped vs. Unmapped (Not Deployed)");
		System.out.println(canvas.getSize());
		builder.build();
		builder.moveToElement(canvas, canvas.getSize().getWidth()/3, canvas.getSize().getHeight()/3)
		.click();
		builder.perform();
		}
		catch(Throwable e) {
			System.out.println(("Failed click on the elements in the Mapped vs Unmapped Not Deployed chart"));
			fail(("Failed click on the elements in the Mapped vs Unmapped Not Deployed chart"));
		}
	}
	
	@Then("^Should open with Mapped vs Unmapped Not Deployed graphs$")
	public static void Should_open_with_Mapped_Vs_UnMapped_Not_Deployed_graphs() {
		try {
		assertEquals(ServiceHooks.driver.getCurrentUrl(),TestConfig.getBaseURL()+"/skillset/0");
		ServiceHooks.driver.navigate().back();
		}catch(Throwable e) {
			System.out.println(("Failed to open Mapped vs Unmapped Not Deployed graphs"));
			fail(("Failed to open Mapped vs Unmapped Not Deployed graphs"));
		}
	}
	
	@When("^I click on the elements in the Mapped vs Unmapped Deployed chart$")
	public static void i_click_on_the_elements_in_the_Mapped_vs_Unmapped_Deployed_chart() {
		try {
		Actions builder= new Actions(ServiceHooks.driver);
		WebElement canvas = HomeTab.getChart(ServiceHooks.driver, "Mapped vs. Unmapped (Deployed)");
		System.out.println(canvas.getSize());
		builder.build();
		builder.moveToElement(canvas, canvas.getSize().getWidth()/3, canvas.getSize().getHeight()/3)
		.click();
		builder.perform();
		}
		catch(Throwable e) {
			System.out.println(("Failed click on the elements in the Mapped vs Unmapped Deployed chart"));
			fail(("Failed click on the elements in the Mapped vs Unmapped Deployed chart"));
		}
	}
	
	@Then("^Should open with Mapped vs Unmapped Deployed graphs$")
	public static void Should_open_with_Mapped_vs_Unmapped_Deployed_graphs() {
		try {
		assertEquals(ServiceHooks.driver.getCurrentUrl(),TestConfig.getBaseURL()+"/skillset/0");
		ServiceHooks.driver.navigate().back();
		}catch(Throwable e) {
			System.out.println(("Failed to open the Mapped vs Unmapped Deployed graphs"));
			fail(("Failed to open the Mapped vs Unmapped Deployed graphs"));
		}
	}
	
	@When("^I click on pie chart button$")
	public static void I_click_on_pie_chart_button() {
		try {
		WebElement element = WaitToLoad.findDynamicElement(ServiceHooks.driver, By.xpath("//*[contains(text(),'Pie Chart')]"), 5);
		element.click();
		}catch(Throwable e) {
			System.out.println(("Failed to click on pie chart button"));
			fail(("Failed to click on pie chart button"));
		}
	}
	
	@Then("^A pie chart will be shown$")
	public static void A_pie_chart_will_be_shown() {
		try {
		WebElement canvas = ServiceHooks.driver.findElement(By.id("the_graph"));
		assertEquals(canvas.getAttribute("ng-reflect-chart-type"),"pie");
		}catch(Throwable e) {
			System.out.println(("Failed to show pie chart"));
			fail(("Failed to show pie chart"));
		}finally {
			ServiceHooks.driver.navigate().back();
		}
	}
	
	@When("^I click on bar chart button$")
	public static void I_click_on_bar_chart_button() {
		try {
		WebElement element = WaitToLoad.findDynamicElement(ServiceHooks.driver, By.xpath("//*[contains(text(),'Bar Chart')]"), 5);
		element.click();
		}catch(Throwable e) {
			System.out.println(("Failed to click on bar chart button"));
			fail(("Failed to click on bar chart button"));
		}
	}
	
	@Then("^A bar chart will be shown$")
	public static void A_bar_chart_will_be_shown() {
		try {
		WebElement canvas = ServiceHooks.driver.findElement(By.id("the_graph"));
		assertEquals(canvas.getAttribute("ng-reflect-chart-type"),"bar");
		}catch(Throwable e) {
			System.out.println(("Failed to show bar chart"));
			fail(("Failed to show bar chart"));
		}finally {
			ServiceHooks.driver.navigate().back();
		}
	}
	
	@When("^I click on polar chart button$")
	public static void I_click_on_polar_chart_button() {
		try {
		WebElement element = WaitToLoad.findDynamicElement(ServiceHooks.driver, By.xpath("//*[contains(text(),'Polar Chart')]"), 5);
		element.click();
		}catch(Throwable e) {
			System.out.println(("Failed to click on polar chart button"));
			fail(("Failed to click on polar chart button"));
		}
	}
	
	@Then("^A polar chart will be shown$")
	public static void A_polar_chart_will_be_shown() {
		try {
		WebElement canvas = ServiceHooks.driver.findElement(By.id("the_graph"));
		assertEquals(canvas.getAttribute("ng-reflect-chart-type"),"polarArea");
		}catch(Throwable e) {
			System.out.println(("Failed to show polar chart"));
			fail(("Failed to show polar chart"));
		}finally {
			ServiceHooks.driver.navigate().back();
		}
	}
	
	@When("^I click a section of the pie chart$")
	public static void I_click_a_section_of_the_pie_chart() {
		try {
			WebElement canvas = WaitToLoad.findDynamicElement(ServiceHooks.driver,By.id("the_graph"), 2);
			Actions builder= new Actions(ServiceHooks.driver);
			System.out.println(canvas.getSize());
			builder.build();
			builder.moveToElement(canvas, canvas.getSize().getWidth()/2, canvas.getSize().getHeight()/2)
			.pause(1)
			.click();
			builder.perform();
		}catch(Throwable e) {
			System.out.println(("Failed to click secion of pie chart"));
			fail(("Failed to click secion of pie chart"));
		}
	}
	
	@Then("^I am on the Associate Page")
	public static void I_am_on_the_Associate_Page() {
		try {
			WebElement element = ServiceHooks.driver.findElement(By.className("active"));
			assertEquals(element.getText(),"Associate List");
		}catch(Throwable e) {
			System.out.println(("Failed to go to associate page"));
			fail(("Failed to go to associate page"));
		}
	}
	
	@Then("^The associate list associated with that section of the chart will be shown$")
	public static void The_associate_list_associated_with_that_section_of_the_pie_chart_will_be_shown() {
		try {
			List<WebElement> clientNames = AssociateListTab.clientNameList(ServiceHooks.driver);
			for(WebElement client : clientNames) {
				assertEquals(client.getText(),"Infosys");
			}
		}catch(Throwable e) {
			System.out.println(("Failed to retrieve list associated with that section of the pie chart"));
			fail(("Failed to retrieve list associated with that section of the pie chart"));
		}finally {
			ServiceHooks.driver.navigate().to(TestConfig.getBaseURL()+"/root");
		}
	}
	
	@Then("^the graphs should be visible$")
	public void the_graphs_should_be_visible() throws Throwable {
	  assertTrue(HomeTab.getChart(ServiceHooks.driver, "Mapped vs. Unmapped (Not Deployed)").isDisplayed());
	  assertTrue(HomeTab.getChart(ServiceHooks.driver, "Mapped vs. Unmapped (Deployed)").isDisplayed());
	  assertTrue(HomeTab.getChart(ServiceHooks.driver, "Mapped").isDisplayed());
	  assertTrue(HomeTab.getChart(ServiceHooks.driver, "unMapped").isDisplayed());
	   
	}

}
