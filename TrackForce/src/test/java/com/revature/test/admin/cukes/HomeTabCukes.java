package com.revature.test.admin.cukes;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.revature.test.admin.pom.AssociateListTab;
import com.revature.test.admin.pom.HomeTab;
import com.revature.test.admin.testclasses.AdminSuite;
import com.revature.test.utils.ServiceHooks;
import com.revature.test.utils.TestConfig;
import com.revature.test.utils.WaitToLoad;
import com.revature.utils.DriverUtil;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomeTabCukes extends AdminSuite {
	
	static WebDriver d=ServiceHooks.driver;
	@Given("^I am on the Home Page$")
	public static void i_am_on_the_Home_Page()  {
		try {
			Thread.sleep(1500);
			HomeTab.clickHomeTab(d).click();
			

		} catch (Throwable e) {
			System.out.println("Failed To click on HomeTab");
			fail(("Failed click on the Home Tab"));
		}

	}

	@Given("^Home Tab loads$")
	public static void home_tab_loads() {
		try {
			Thread.sleep(500);
			if (HomeTab.getCurrentURL(d).equals(TestConfig.getBaseURL()) || 
					HomeTab.getCurrentURL(d).equals(TestConfig.getBaseURL() + "/") || 
					HomeTab.getCurrentURL(d).equals(TestConfig.getBaseURL() + "/root")) {
				
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
			HomeTab.phone(d).click();
			
			Thread.sleep(1000);
			//Exits alert pop up asking to open in app
			Actions action = new Actions(d);
			action.sendKeys(Keys.ESCAPE).perform();
			
		} catch (Throwable e) {
			System.out.println(("Failed click on the phone link"));
			fail(("Failed click on the phone link"));
		}

	}

	@Then("^I should see the telephone number link open on a browser$")
	public void i_should_see_the_telephone_number_link_open_on_a_browser()  {

	}

	@When("^I click on the email link$")
	public static void i_click_on_the_email_link()  {

		try {
			Thread.sleep(1000);
			HomeTab.email(d).click();
			
		} catch (Throwable e) {
			System.out.println(("Failed click on the email link"));
			fail(("Failed click on the email link"));
			
		}

	}

	@Then("^I should see the email link open on a browser$")
	public void i_should_see_the_email_link_open_on_a_browser()  {
		//Email opens in a location based on where the default email service is on the computer
	}

	@When("^I click on the website link$")
	public static void i_click_on_the_website_link()  {
		
		try {
			Thread.sleep(1000);
			HomeTab.website(d).click();
			
		} catch (Throwable e) {
			System.out.println(("Failed click on the website link"));
			fail(("Failed click on the website link"));
		}
		
	}

	@Then("^I should see the Revature website link open on a browser$")
	public void i_should_see_the_Revature_website_link_open_on_a_browser()  {
		//get window handlers as list
		List<String> browserTabs = new ArrayList<String> (d.getWindowHandles());
		//switch to new tab
		d.switchTo().window(browserTabs .get(1));
		//check is it correct page opened or not (e.g. check page's title)
		assertEquals(d.getCurrentUrl(),"https://revature.com/");
		//then close tab and get back
		d.close();
		d.switchTo().window(browserTabs.get(0));
	}

	
	@When("^I click on the populate database button$")
	public static void i_click_on_the_populate_database_button()  {
		
		try {
			Thread.sleep(2000);
			HomeTab.populateDatabase(d).click();
			

		} catch (Throwable e) {
			System.out.println(("Failed click on the populate database button"));
			fail(("Failed click on the populate database button"));
		}
	    
	}

	@Then("^I should see a temporary blue border for the populate database button$")
	public void i_should_see_a_temporary_blue_border_for_the_populate_database_button()  {
	    
		
	}

	@When("^I click on the populate static salesforce button$")
	public static void i_click_on_the_populate_static_salesforce_button()  {
		try {
			Thread.sleep(2000);
			HomeTab.populateStaticSalesforce(d).click();
			

		} catch (Throwable e) {
			System.out.println(("Failed click on the static salesforce button"));
			fail(("Failed click on the static salesforce button"));
		}
	}

	@Then("^I should see a temporary blue border for the populate static salesforce button$")
	public void i_should_see_a_temporary_blue_border_for_the_populate_static_salesforce_button()  {
	   
	}

	@When("^I click on the empty database button$")
	public static void i_click_on_the_empty_database_button()  {
		
		try {
			Thread.sleep(2000);
			HomeTab.emptyDatabase(d).click();
			

		} catch (Throwable e) {
			System.out.println(("Failed click on the empty database button"));
			fail(("Failed click on the empty database button"));
		}
	    
	}

	@Then("^I should see a temporary blue border for the empty database button$")
	public void i_should_see_a_temporary_blue_border_for_the_empty_database_button()  {
	    
	}
	
	@When("^I click on the elements in the Mapped pie chart$")
	public static void i_click_on_the_elements_in_the_Mapped_pie_chart() {
		try {
		Actions builder= new Actions(d);
		WebElement canvas = HomeTab.getChart(d, "Mapped");
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
		assertEquals(d.getCurrentUrl(),TestConfig.getBaseURL()+"/client-mapped/0");
		d.navigate().back();
		}
		catch(Throwable e) {
			System.out.println(("Failed to open with mapped graphs"));
			fail(("Failed to open with mapped graphs"));
		}
	}
	
	@When("^I click on the elements in the UnMapped pie chart$")
	public static void i_click_on_the_elements_in_the_UnMapped_pie_chart() {
		try {
		Actions builder= new Actions(d);
		WebElement canvas = HomeTab.getChart(d, "Unmapped");
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
		assertEquals(d.getCurrentUrl(),TestConfig.getBaseURL()+"/skillset/0");
		d.navigate().back();
		}catch(Throwable e) {
			System.out.println(("Failed to open with Unmapped graphs"));
			fail(("Failed to open with Unmapped graphs"));
		}
	}
	
	@When("^I click on the elements in the Mapped vs Unmapped Not Deployed chart$")
	public static void i_click_on_the_elements_in_the_Mapped_Vs_Unmapped_Not_Deployed_chart() {
		try {
		Actions builder= new Actions(d);
		WebElement canvas = HomeTab.getChart(d, "Mapped vs. Unmapped (Not Deployed)");
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
		assertEquals(d.getCurrentUrl(),TestConfig.getBaseURL()+"/skillset/0");
		d.navigate().back();
		}catch(Throwable e) {
			System.out.println(("Failed to open Mapped vs Unmapped Not Deployed graphs"));
			fail(("Failed to open Mapped vs Unmapped Not Deployed graphs"));
		}
	}
	
	@When("^I click on the elements in the Mapped vs Unmapped Deployed chart$")
	public static void i_click_on_the_elements_in_the_Mapped_vs_Unmapped_Deployed_chart() {
		try {
		Actions builder= new Actions(d);
		WebElement canvas = HomeTab.getChart(d, "Mapped vs. Unmapped (Deployed)");
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
		assertEquals(d.getCurrentUrl(),TestConfig.getBaseURL()+"/skillset/0");
		d.navigate().back();
		}catch(Throwable e) {
			System.out.println(("Failed to open the Mapped vs Unmapped Deployed graphs"));
			fail(("Failed to open the Mapped vs Unmapped Deployed graphs"));
		}
	}
	
	@When("^I click on pie chart button$")
	public static void I_click_on_pie_chart_button() {
		System.out.println("Clicking pie chart button");
		try {
		WebElement element = WaitToLoad.findDynamicElement(d, By.xpath("//*[contains(text(),'Pie Chart')]"), 5);
		element.click();
		}catch(Throwable e) {
			System.out.println(("Failed to click on pie chart button"));
			fail(("Failed to click on pie chart button"));
		}
	}
	
	@Then("^A pie chart will be shown$")
	public static void A_pie_chart_will_be_shown() {
		try {
		WebElement canvas = d.findElement(By.id("the_graph"));
		assertEquals(canvas.getAttribute("ng-reflect-chart-type"),"pie");
		}catch(Throwable e) {
			System.out.println(("Failed to show pie chart"));
			fail(("Failed to show pie chart"));
		}finally {
			d.navigate().back();
		}
	}
	
	@When("^I click on bar chart button$")
	public static void I_click_on_bar_chart_button() {
		try {
		WebElement element = WaitToLoad.findDynamicElement(d, By.xpath("//*[contains(text(),'Bar Chart')]"), 5);
		element.click();
		}catch(Throwable e) {
			System.out.println(("Failed to click on bar chart button"));
			fail(("Failed to click on bar chart button"));
		}
	}
	
	@Then("^A bar chart will be shown$")
	public static void A_bar_chart_will_be_shown() {
		try {
		WebElement canvas = d.findElement(By.id("the_graph"));
		assertEquals(canvas.getAttribute("ng-reflect-chart-type"),"bar");
		}catch(Throwable e) {
			System.out.println(("Failed to show bar chart"));
			fail(("Failed to show bar chart"));
		}finally {
			d.navigate().back();
		}
	}
	
	@When("^I click on polar chart button$")
	public static void I_click_on_polar_chart_button() {
		try {
		WebElement element = WaitToLoad.findDynamicElement(d, By.xpath("//*[contains(text(),'Polar Chart')]"), 5);
		element.click();
		}catch(Throwable e) {
			System.out.println(("Failed to click on polar chart button"));
			fail(("Failed to click on polar chart button"));
		}
	}
	
	@Then("^A polar chart will be shown$")
	public static void A_polar_chart_will_be_shown() {
		try {
		WebElement canvas = d.findElement(By.id("the_graph"));
		assertEquals(canvas.getAttribute("ng-reflect-chart-type"),"polarArea");
		}catch(Throwable e) {
			System.out.println(("Failed to show polar chart"));
			fail(("Failed to show polar chart"));
		}finally {
			d.navigate().back();
		}
	}
	
	@When("^I click a section of the pie chart$")
	public static void I_click_a_section_of_the_pie_chart() {
		try {
			WebElement canvas = WaitToLoad.findDynamicElement(d,By.id("the_graph"), 2);
			Actions builder= new Actions(d);
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
			WebElement element = d.findElement(By.className("active"));
			assertEquals(element.getText(),"Associate List");
		}catch(Throwable e) {
			System.out.println(("Failed to go to associate page"));
			fail(("Failed to go to associate page"));
		}
	}
	
	@Then("^The associate list associated with that section of the chart will be shown$")
	public static void The_associate_list_associated_with_that_section_of_the_pie_chart_will_be_shown() {
		try {
			List<WebElement> clientNames = AssociateListTab.clientNameList(d);
			for(WebElement client : clientNames) {
				assertEquals(client.getText(),"Infosys");
			}
		}catch(Throwable e) {
			System.out.println(("Failed to retrieve list associated with that section of the pie chart"));
			fail(("Failed to retrieve list associated with that section of the pie chart"));
		}finally {
			d.navigate().to(TestConfig.getBaseURL()+"/root");
		}
	}
}
