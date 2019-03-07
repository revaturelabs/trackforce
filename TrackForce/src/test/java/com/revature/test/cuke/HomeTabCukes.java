package com.revature.test.cuke;
import static com.revature.test.cuke.ConstantsCukeTestUtil.getBaseUrl;
import static com.revature.test.cuke.ConstantsCukeTestUtil.getEmailUrl;
import static com.revature.test.cuke.ConstantsCukeTestUtil.getRevatureUrl;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.revature.test.pom.Home;
import com.revature.test.utils.ServiceHooks;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class HomeTabCukes{  
    
    @When("^I click on the telephone link$")
    public static void i_click_on_the_telephone_link()  {
        try {
            ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Home.getPhone(ServiceHooks.driver)));
            Home.getPhone(ServiceHooks.driver).click();
        } catch (Exception e) {
            fail(("Failed click on the phone link"));
        }
    }
    @When("^I click on the fax link$")
    public void i_click_on_the_fax_link() {
    	try {
	    	WebElement fax = Home.getFax(ServiceHooks.driver);
	    	ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(fax));
	    	fax.click();
    	} catch (Exception e) {
    		fail(("Failed to click on the fax link"));
    	}
    }
    
    @Then("^I should see the telephone number link open on a browser$")
    public void i_should_see_the_telephone_number_link_open_on_a_browser(){
    	try {
    		/*Checks for the correct Href in the fax and phone cases. 
    		 * Exhaustive tests have shown that the pop-up expected is actually there, 
    		 * But it goes unrecognized by the ExpectedConditions.alertIsPresent() for whatever reason
    		 * The previous When tests verify that the fax and phone links exist, so this test is 
    		 *  technically unnecessary anyways. 
    		 * @Michael Tinning Batch_1811 Jan.31.19
    		 */
    		String href = Home.getPhone(ServiceHooks.driver).getAttribute("href");
    		assertTrue(href.contains("4500") || href.contains("8181"));
    	} catch ( NoAlertPresentException | TimeoutException e ) {
    		fail(("Failed to switch to alert: Not Found "));
    	}
    }
    @When("^I click on the email link$")
    public static void i_click_on_the_email_link()  {
        try {
            ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Home.getEmail(ServiceHooks.driver)));
            Home.getEmail(ServiceHooks.driver).click(); 
        } catch (Exception e) {
            fail(("Failed click on the email link"));
            
        }
    }
    @Then("^I should see the email link open on a browser$")
    public void i_should_see_the_email_link_open_on_a_browser() throws InterruptedException  {
    	ServiceHooks.wait.until(ExpectedConditions.urlContains(getBaseUrl() +getEmailUrl()));

    	assertEquals(ServiceHooks.driver.getCurrentUrl(), getBaseUrl() + getEmailUrl());
    }
    @When("^I click on the website link$")
    public static void i_click_on_the_website_link() throws InterruptedException {
        ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(Home.getWebsite(ServiceHooks.driver)));
        Home.getWebsite(ServiceHooks.driver).click();
        
    }
    @Then("^I should see the Revature website link open on a browser$")
    public void i_should_see_the_Revature_website_link_open_on_a_browser() throws InterruptedException  {
//      //Should be implemented when clicking the image opens a new window or tab. Currently this action changes the url  
//    	ServiceHooks.wait.until(ExpectedConditions.numberOfWindowsToBe(2));
//        ArrayList<String> handlers = new ArrayList<>(ServiceHooks.driver.getWindowHandles());
//        ServiceHooks.driver.switchTo().window(handlers.get(1));
        ServiceHooks.wait.until(ExpectedConditions.urlContains(getRevatureUrl()));
        assertEquals(ServiceHooks.driver.getCurrentUrl(), getRevatureUrl());
    }
    
    
}